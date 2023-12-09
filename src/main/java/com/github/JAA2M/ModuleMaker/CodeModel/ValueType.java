package com.github.JAA2M.ModuleMaker.CodeModel;

public sealed interface ValueType {
    record VariableType(String name) implements ValueType{}
    record StringType(String value) implements ValueType{}
    record FunctionType(String name) implements ValueType{}
    record IntType(int value) implements ValueType{}
    record FloatType(float value) implements ValueType{}
    record BooleanType(boolean value) implements ValueType{}
}
