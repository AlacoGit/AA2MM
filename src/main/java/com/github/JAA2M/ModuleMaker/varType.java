package com.github.JAA2M.ModuleMaker;

public sealed interface varType permits stringType, intType, floatType, boolType, unknownType{
    public String getName();
}
record stringType(String name, String value) implements varType {
    @Override
    public String getName() {
        return name;
    }
}

record intType(String name, int value) implements varType {
    @Override
    public String getName() {
        return name;
    }
}
record floatType(String name, float value) implements varType {
    @Override
    public String getName() {
        return name;
    }
}
record boolType(String name, boolean value) implements varType {
    @Override
    public String getName() {
        return name;
    }
}

// Used for Globals where we don't yet know the type
record unknownType(String name, String value) implements varType {
    @Override
    public String getName() {
        return name;
    }
}
