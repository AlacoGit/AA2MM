package com.github.JAA2M.Module;

import com.github.JAA2M.wString;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public sealed interface Value<T> permits Value.invalidValue,Value.intValue,Value.floatValue,Value.boolValue,Value.strValue{
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

    static Value<?> of(Types type){
        return switch(type){
            case INVALID -> new invalidValue();
            case INT -> new intValue(0);
            case BOOL -> new boolValue(false);
            case FLOAT -> new floatValue(0f);
            case STRING -> new strValue("(default)");
        };
    }

    T getValue();

    static intValue of(int value){
        return new intValue(value);
    }
    static boolValue of(boolean value){
        return new boolValue(value);
    }
    static boolValue of(byte value){
        return new boolValue(value);
    }
    static floatValue of(float value){
        return new floatValue(value);
    }
    static strValue of(String value){
        return new strValue(value);
    }

    record intValue(int value) implements Value<Integer>{
        @Override
        public Integer getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof intValue intValue)) return false;
            return getValue().equals(intValue.getValue());
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(getValue());
        }
    }
    record boolValue(boolean value) implements Value<Boolean>{
        boolValue(byte value){
            this((int)value == 1);
        }

        @Override
        public Boolean getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof boolValue boolValue)) return false;
            return getValue() == boolValue.getValue();
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(getValue());
        }
    }
    record floatValue(float value) implements Value<Float>{
        @Override
        public Float getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof floatValue that)) return false;
            return Float.compare(getValue(), that.getValue()) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(getValue());
        }
    }
    record strValue(String value) implements Value<String>, wString {

        @Override
        public String getWString() {
            return new String(value.getBytes(StandardCharsets.UTF_16LE),StandardCharsets.UTF_16LE);
        }

        @Override
        public String toString() {
            return this.getValue();
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof strValue strValue)) return false;
            return Objects.equals(getValue(), strValue.getValue());
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(getValue());
        }
    }
    record invalidValue() implements Value<Void> {

        @Override
        public Void getValue() {
            throw new RuntimeException("Not meant to be called");
        }
    }

}
