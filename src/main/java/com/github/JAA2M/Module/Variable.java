package com.github.JAA2M.Module;

import com.github.JAA2M.wString;

/**
 * Represents a variable in a {@link Trigger}
 * @param id Unused? Note: this value is actually a c++ {@code DWORD}, eg a 32-bit unsigned integer.
 * @param type The {@link Value.Types} of value this variable contains.
 * @param name The user-given name of this variable.
 * @param defaultValue The default value of this variable
 */
public record Variable(/*DWORD*/int id, Value.Types type, wString name, Expression.ParameterisedExpression defaultValue){
    public record VariableInstance(Variable variable, Value currValue){}
}

