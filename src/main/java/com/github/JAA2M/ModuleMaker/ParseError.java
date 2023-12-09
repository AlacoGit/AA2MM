package com.github.JAA2M.ModuleMaker;

public class ParseError extends RuntimeException{
    public ParseError(String errorMessage){
        super(errorMessage);
    }
}
