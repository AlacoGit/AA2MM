package com.github.JAA2M.ModuleMaker.parser;

import com.github.JAA2M.ModuleMaker.CodeModel.ValueType;
import java.util.HashMap;
import java.util.Map;

public class ParseTree {

    private final Map<String, GlobalVariable> globalVariableMap;
    private final rootImpl root;

    public ParseTree(){
        this.globalVariableMap = new HashMap<>();
        this.root = new rootImpl();
    }
    Root getRoot(){
        return this.root;
    }

    public boolean setName(String name){
        if(this.root.getName() != null){
            return false;
        }
        Name _name = ParseTree.makeName(name);
        this.root.setName(_name);
        return true;
    }

    public void insert(){}

    public sealed interface parseTreeStatement permits ContinuingStatement, Define, NewVar, Root {
    }
    private sealed interface ContinuingStatement extends parseTreeStatement{
        /*Points to the next statement in the same scope
          Currently there are two scopes: Top-level(Module) and Trigger. The Module scope contains all things related to the specific Module
          and has no parent. The Trigger scope is bound to a specific Trigger and has the Module scope as its parent.
         */
        ContinuingStatement next();
    }
    public sealed interface parsedExpression extends ContinuingStatement{

    }
    private final class branchingExpression implements parsedExpression{

        @Override
        public ContinuingStatement next() {
            return null;
        }
    }
    // Top level statement, Root node in the parse tree
    public sealed interface Root extends parseTreeStatement {
        Name getName();
        Description getDesc();

    }
    private static final class rootImpl implements Root{
        private Name name;
        private Description desc;
        private final Map<String,Trigger> triggers;

        rootImpl(){
            this.triggers = new HashMap<>();
        }

        public Name getName() {
            return name;
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Description getDesc() {
            return desc;
        }

        public void setDesc(Description desc) {
            this.desc = desc;
        }
    }
    public sealed interface End extends ContinuingStatement permits endImpl{}
    private record endImpl() implements End {
        @Override
        public ContinuingStatement next() {
           throw new RuntimeException("next() call to End detected. Don't do that.");
        }
    }

    // First assignment to a variable, either local(Trigger) or global
    public sealed interface NewVar extends parseTreeStatement {
        ValueType var();
        parsedExpression value();
    }
    // Trigger scope variable
    public sealed interface LocalVariable extends NewVar {}
    private record localVariableImpl(ValueType var, parsedExpression value) implements LocalVariable{}
    // Global scope variable
    public sealed interface GlobalVariable extends NewVar {}
    private record globalVariableImpl(ValueType var, parsedExpression value) implements GlobalVariable{}
    //Defines top level code structures
    public sealed interface Define extends parseTreeStatement {
    }
    public sealed interface Trigger extends Define {
        String name();
    }
    private record TriggerImpl(String name) implements Trigger{}


    // Used when the Module name is defined in the file, if the name is given on the commandline or inferred from the filename this node might not be present.
    public sealed interface Name extends Define {
        ValueType.StringType value();
    }
    private record NameImpl(ValueType.StringType name) implements Name{

        @Override
        public ValueType.StringType value(){
            return name;
        }
    }
    public static Name makeName(String name){
        return new NameImpl(new ValueType.StringType(name));
    }

    public sealed interface Description extends Define {
        String desc();
    }
    private record DescriptionImpl(String desc) implements Description{}
}
