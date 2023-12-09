package com.github.JAA2M.ModuleReader;


import com.github.JAA2M.Module.*;
import com.github.JAA2M.Module.Module;
import com.github.JAA2M.wString;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * This class reads a AA2U Module file and returns a {@link Module} object representing the Module file.
 * This class assumes that the source file is properly formatted; Passing an improperly formatted file to this class
 * results in undefined behaviour.
 */
public class ModuleReader {
    private final ByteBuffer bb;

    public ModuleReader(String path){
        Path p = Path.of(path);
        try (FileChannel fc = FileChannel.open(p, StandardOpenOption.READ)) {
            this.bb = fc.map(FileChannel.MapMode.READ_ONLY,0,p.toFile().length());
            this.bb.order(ByteOrder.LITTLE_ENDIAN);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Private Constructor to facilitate testing
     * @param bb
     */
    private ModuleReader(ByteBuffer bb){
        this.bb = bb;
    }

    public static Module parse(String path){
        ModuleReader mr = new ModuleReader(path);
        return mr.readModule();
    }

    private boolean readBoolean(){
        byte raw = this.bb.get();
        //00000001 - True | 00000000 - False
        return (raw & 0b1) != 0;
    }

    private Value.Types readType(){
        int id = this.bb.getInt();
        return Value.Types.values()[id];
    }

    private wString readString(){
        int len = this.bb.getInt();
        byte[] strbuff = new byte[len*2];
        this.bb.get(strbuff);
        return wString.of(strbuff);
    }

    private Expression.ParameterisedExpression readExpression(){
        Value.Types type = readType();
        int id = bb.getInt();

        if(id == Expression.ExpressionTypes.CONSTANT.id){
            return new Expression.ParameterisedExpression(type,this.readValue());
        } else if(id == Expression.ExpressionTypes.VAR.id){
            return new Expression.ParameterisedExpression(type,this.readString());
        } else if(id == Expression.ExpressionTypes.NAMEDCONSTANT.id){
            int idnc = this.bb.getInt();
            Expression exp = Expression.fromID(type,id);
            return new Expression.ParameterisedExpression(exp,idnc);
        } else {
            return new Expression.ParameterisedExpression(type,id,this.readListOf(this::readExpression));
        }

    }

    private Variable readVariable(){
        return new Variable(-5,this.readType(),this.readString(),this.readExpression());
    }

    private Event.ParameterisedEvent readEvent(){
        int id = bb.getInt();
        List<Expression.ParameterisedExpression> actualParameters = readListOf(this::readExpression);
        return new Event.ParameterisedEvent(Event.fromID(id),actualParameters);
    }

    private Value readValue(){
        Value.Types type = readType();
        return switch (type){
            case INVALID -> throw new RuntimeException("Read invalid type at" + this.bb.position());
            case INT -> Value.of(this.bb.getInt());
            case BOOL -> Value.of(this.bb.get());
            case FLOAT -> Value.of((this.bb.getFloat()));
            case STRING -> Value.of(this.readString());
        };
    }

    private Action.ParameterisedAction readAction(){
        int id = this.bb.getInt();
        return new Action.ParameterisedAction(Action.fromId(id),this.readListOf(this::readExpression));
    }


    private GUIAction readGUIAction(){
        Action.ParameterisedAction action = this.readAction();
        List<GUIAction> subActions = this.readListOf(this::readGUIAction);
        GUIAction ret = new GUIAction(action,subActions);
        for(GUIAction ac : subActions){
            ac.setParent(ret);
        }
        return ret;
    }

    private <T> List<T> readListOf(Supplier<T> supplier){
        int length = this.bb.getInt();
        List<T> ret = new ArrayList<T>(length);
        for(int i = 0; i < length;i++){
            ret.add(supplier.get());
        }
        return ret;
    }

    private Trigger readTrigger(){
        wString name = readString();
        List<Event.ParameterisedEvent> events = readListOf(this::readEvent);
        List<Variable> vars = readListOf(this::readVariable);
        List<GUIAction> guiAction = readListOf(this::readGUIAction);
        return new Trigger(name,events,vars,guiAction);
    }

    private GlobalVariable readGlobalVar(){
        Value.Types type = this.readType();
        wString name = this.readString();
        Value defaultValue = this.readValue();
        Value currentValue = this.readValue();
        boolean initialized = this.readBoolean();
        return new GlobalVariable(-0,type,name,defaultValue,currentValue,initialized);
    }

    private Module readModule(){
        wString name = this.readString();
        wString desc = this.readString();
        List<Trigger> triggers = this.readListOf(this::readTrigger);
        List<GlobalVariable> globalVariables = this.readListOf(this::readGlobalVar);
        List<wString> dependencies = this.readListOf(this::readString);
        return new Module(name,desc,globalVariables,triggers,dependencies);
    }


}
