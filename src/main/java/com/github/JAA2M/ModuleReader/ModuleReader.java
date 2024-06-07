package com.github.JAA2M.ModuleReader;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.JAA2M.Module.Action;
import com.github.JAA2M.Module.Event;
import com.github.JAA2M.Module.Expression;
import com.github.JAA2M.Module.GUIAction;
import com.github.JAA2M.Module.GlobalVariable;
import com.github.JAA2M.Module.Module;
import com.github.JAA2M.Module.Trigger;
import com.github.JAA2M.Module.Value;
import com.github.JAA2M.Module.Variable;
import com.github.JAA2M.wString;

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
            Logger.getLogger("com.github.JAA2M.ModuleReader").log(Level.SEVERE,"Failed to load file %s with the following error.".formatted(path));
            throw new RuntimeException(ex);
        }
    }

    /*
        NOTE: This class will modify the position of the given ByteBuffer
     */
    public ModuleReader(ByteBuffer bb){
        this.bb = bb;
    }

    public static Module parse(String path){
        ModuleReader mr = new ModuleReader(path);
        return mr.readModule();
    }

    int readInt() throws MissingElementException{
        try {
            return this.bb.getInt();
        } catch (BufferUnderflowException bue){
            throw new MissingElementException(bue);
        }
    }

   boolean readBoolean(){
        byte raw = this.bb.get();
        //00000001 - True | 00000000 - False
        return (raw & 0b1) != 0;
    }

    Value.Types readType(){
        try{
            int id = this.readInt();
            return Value.Types.values()[id];
        } catch (MissingElementException mee) {
            Logger.getLogger("com.github.JAA2M.ModuleReader").log(Level.SEVERE,"Terminating:\n Failed to read Type starting at %d: \n Expected int at %<d".formatted(this.bb.position()));
            throw new RuntimeException(mee);
        }
    }

    Value.strValue readString() throws MissingElementException{
            int len = this.readInt();
            byte[] strbuff = new byte[len * 2];
            try{
                this.bb.get(strbuff);
            } catch (BufferUnderflowException bue){
                throw new MissingElementException(bue);
            }
            String ret = new String(strbuff, StandardCharsets.UTF_16LE);
            return wString.of(ret);
    }

    private Expression.ParameterisedExpression readExpression(){
        Value.Types type = readType();
        int id = bb.getInt();

        if(id == Expression.ExpressionTypes.CONSTANT.id){
            return new Expression.ParameterisedExpression(type,this.readValue());
        } else if(id == Expression.ExpressionTypes.VAR.id){
            try{
                return new Expression.ParameterisedExpression(type,this.readString());
            } catch (MissingElementException mee){
                Logger.getLogger("com.github.JAA2M.ModuleReader").log(Level.SEVERE,"Terminating:\n Failed to read Expression starting at %d: \n Expected int at %<d".formatted(this.bb.position()));
                throw new RuntimeException(mee);
            }
        } else if(id == Expression.ExpressionTypes.NAMEDCONSTANT.id){
            try{
                int idnc = this.readInt();
                Expression exp = Expression.fromID(type,id);
                return new Expression.ParameterisedExpression(exp,idnc);
            } catch (MissingElementException mee){
                Logger.getLogger("com.github.JAA2M.ModuleReader").log(Level.SEVERE,"Terminating:\n Failed to read Expression starting at %d: \n Expected constant ID at %<d".formatted(this.bb.position()));
                throw new RuntimeException(mee);
            }
        } else {
            return new Expression.ParameterisedExpression(type,id,this.readListOf(this::readExpression));
        }

    }

    private Variable readVariable(){
        try{
            var type = this.readType();
            var name = this.readString();
            var exp = this.readExpression();
            return new Variable(-5,type,name,exp);
        } catch (MissingElementException mee){
            Logger.getLogger("com.github.JAA2M.ModuleReader").log(Level.SEVERE,"Terminating:\n Failed to read Variable name starting at %d: \n Expected int at %<d".formatted(this.bb.position()));
            throw new RuntimeException(mee);
        }
    }

    private Event.ParameterisedEvent readEvent(){
        int id = bb.getInt();
        List<Expression.ParameterisedExpression> actualParameters = readListOf(this::readExpression);
        return new Event.ParameterisedEvent(Event.fromID(id),actualParameters);
    }

    private Value<?> readValue(){
        Value.Types type = readType();
        try {
            return switch (type) {
                case INVALID -> throw new RuntimeException("Read invalid type at" + this.bb.position());
                case INT -> Value.of(this.readInt());
                case BOOL -> Value.of(this.readBoolean());
                case FLOAT -> Value.of((this.bb.getFloat()));
                case STRING -> this.readString();
            };
        } catch (MissingElementException | BufferUnderflowException ex){
            Logger.getLogger("com.github.JAA2M.ModuleReader").log(Level.SEVERE,"Terminating:\n Failed to read value starting at %d: \n Expected int at %<d".formatted(this.bb.position()));
            throw new RuntimeException(ex);
        }
    }

    private Action.ParameterisedAction readAction(){
        try {
            int id = this.readInt();
            return new Action.ParameterisedAction(Action.fromId(id), this.readListOf(this::readExpression));
        } catch (MissingElementException mee){
            Logger.getLogger("com.github.JAA2M.ModuleReader").log(Level.SEVERE,"Terminating:\n Failed to read Action starting at %d: \n Expected int at %<d".formatted(this.bb.position()));
            throw new RuntimeException(mee);
        }
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
        int length;
        try {
            length = this.readInt();
        } catch (MissingElementException mee){
            Logger.getLogger("com.github.JAA2M.ModuleReader").log(Level.SEVERE,"Terminating:\n Failed to read List length starting at %d: \n Expected int at %<d".formatted(this.bb.position()));
            throw new RuntimeException(mee);
        }
        List<T> ret = new ArrayList<>(length);
        for(int i = 0; i < length;i++){
                ret.add(supplier.get());
        }
        return ret;
    }

    private Trigger readTrigger(){
        Value.strValue name;
        try{
           name = readString();
        } catch (MissingElementException mee){
            Logger.getLogger("com.github.JAA2M.ModuleReader").log(Level.SEVERE,"Terminating:\n Failed to read Trigger name starting at %d: \n Expected int at %<d".formatted(this.bb.position()));
            throw new RuntimeException(mee);
        }
        List<Event.ParameterisedEvent> events = readListOf(this::readEvent);
        List<Variable> vars = readListOf(this::readVariable);
        List<GUIAction> guiAction = readListOf(this::readGUIAction);
        return new Trigger(name,events,vars,guiAction);
    }

    private GlobalVariable readGlobalVar(){
        Value.Types type = this.readType();
        wString name;
        try{
            name= this.readString();
        } catch (MissingElementException mee){
            Logger.getLogger("com.github.JAA2M.ModuleReader").log(Level.SEVERE,"Terminating:\n Failed to read Global Variable name starting at %d: \n Expected int at %<d".formatted(this.bb.position()));
            throw new RuntimeException(mee);
        }
        Value<?> defaultValue = this.readValue();
        Value<?> currentValue = this.readValue();
        boolean initialized = this.readBoolean();
        return new GlobalVariable(-0,type,name,defaultValue,currentValue,initialized);
    }

    private Module readModule(){
        Value.strValue name;
        Value.strValue desc;
        try {
            name = this.readString();
        } catch (MissingElementException mee){
            Logger.getLogger("com.github.JAA2M.ModuleReader").log(Level.SEVERE,"Terminating:\n Failed to read Module name starting at %d: \n Expected String at %<d".formatted(this.bb.position()));
            throw new RuntimeException(mee);
        }
        try {
            desc = this.readString();
        } catch (MissingElementException mee) {
            Logger.getLogger("com.github.JAA2M.ModuleReader").log(Level.WARNING,"Failed to read Module description, consider adding one to your Module");
            desc = Value.of("");
        }
        List<Trigger> triggers = this.readListOf(this::readTrigger);
        List<GlobalVariable> globalVariables = this.readListOf(this::readGlobalVar);
        List<Value.strValue> dependencies;
        try{
            dependencies = this.readListOf(() -> {
                try {
                    return readString();
                } catch (MissingElementException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return new Module(name,desc,globalVariables,triggers,dependencies);
    }
}
