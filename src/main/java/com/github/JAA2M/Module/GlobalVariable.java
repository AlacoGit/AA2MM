package com.github.JAA2M.Module;

import com.github.JAA2M.wString;

public record GlobalVariable(/*DWORD*/int id, Value.Types type, wString name, Value<?> defaultValue, Value<?> currentValue, boolean initialized){
    @Override
    public String toString() {
        return "GlobalVariable{" +
                "id=" + id +
                ", type=" + type +
                ", name=" + name +
                ", defaultValue=" + defaultValue +
                ", currentValue=" + currentValue +
                ", initialized=" + initialized +
                '}';
    }
}