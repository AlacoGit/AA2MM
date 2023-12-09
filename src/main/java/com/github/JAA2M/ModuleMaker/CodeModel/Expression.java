package com.github.JAA2M.ModuleMaker.CodeModel;

public sealed interface Expression{

    sealed interface BinaryExpression extends Expression{}
    record Break() implements BinaryExpression{}
    record Continue() implements BinaryExpression{}

    sealed interface BiExpression extends Expression{}
    record Addition(ValueType lhs, ValueType rhs) implements BiExpression {}
    record Assignment(ConstantExpression target, Expression value) implements BiExpression{}
    record BooleanCondition(Expression lhs, ComparisonOperator op, Expression rhs) implements BiExpression{}
    record ConstantExpression(ValueType value) implements Expression{}

}
