package com.github.JAA2M.Module;

import com.github.JAA2M.wString;

import java.nio.charset.StandardCharsets;

public sealed interface Value permits Value.invalidValue,Value.intValue,Value.floatValue,Value.boolValue,Value.strValue{
    enum Types{
        INVALID("error type"),
        INT("int"),
        BOOL("bool"),
        FLOAT("float"),
        STRING("string");

        public final String name;
        Types(String name){
            this.name = name;
        }
    }

    static Value of(Types type){
        return switch(type){
            case INVALID -> new invalidValue();
            case INT -> new intValue(0);
            case BOOL -> new boolValue(false);
            case FLOAT -> new floatValue(0f);
            case STRING -> new strValue("(default)");
        };
    }

    static intValue of(int value){
        return new intValue(value);
    }
    static boolValue of(boolean value){
        return new boolValue(value);
    }
    static boolValue of(byte value){return new boolValue(value);};
    static floatValue of(float value){
        return new floatValue(value);
    }
    static strValue of(byte[] value){
        return new strValue(value);
    }

    static strValue of(String value){
        return new strValue((value.getBytes(StandardCharsets.UTF_16LE)));
    }
    static strValue of(wString value){return new strValue(value.get().getBytes());}

    record intValue(int value) implements Value{}
    record boolValue(boolean value) implements Value{
        boolValue(byte value){
            this((int)value == 1);
        }
    }
    record floatValue(float value) implements Value{}
    record strValue(byte[] value) implements Value, wString {
        strValue(String value){
            this(value.getBytes(StandardCharsets.UTF_16LE));
        }

        @Override
        public String get() {
            return new String(value,StandardCharsets.UTF_16LE);
        }

        @Override
        public String toString(){
            return this.get();
        }
    }
    record invalidValue() implements Value {}

}
