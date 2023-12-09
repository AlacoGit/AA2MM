package com.github.JAA2M.ModuleMaker.parser;

import com.github.JAA2M.ModuleMaker.ParseError;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import static java.io.StreamTokenizer.*;

public class ModuleParser {
    private final ModuleTokenizer mt;
    public ModuleParser(ModuleTokenizer mt){
        this.mt = mt;
    }

    public static ParseTree parse(Path path){
        try {
            ModuleTokenizer mt = new ModuleTokenizer(new FileReader(path.toFile()));
            ModuleParser mp = new ModuleParser(mt);
            ParseTree pt = new ParseTree();
            StringToken token;
            do {
                token = mt.nextToken();
                switch (token.get()){
                    case "name" -> {
                        int lineNo = token.lineNo();
                        if(!pt.setName(mp.parseName())){
                            System.err.println("Found name declaration at line "+lineNo+" But name was already set. Using previous value");
                        }
                    }
                }

            } while (!token.isEOF());
            return pt;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String parseName() throws IOException {
        mt.expectNextToken('=');
        var token = mt.nextToken();
        if(token.isQuote()){
            String name = token.get();
            this.mt.toggleEOLIsSignificant(true);
            mt.expectNextToken(TT_EOL);
            this.mt.toggleEOLIsSignificant(false);
            return name;
        } else {
            throw new ParseError("Value of 'Name' must be quoted String(A String surrounded by either ' or \")");
        }
    }


    private void checkIfEOFIsExpected() {
    }

    private void parseTriggerStatement() {
    }

    private void parseGlobalStatement() {
    }

    private static void expectToken(StringToken token, int expected){
        if(token.get().codePointAt(0) != expected){
            throw new ParseError("Unexpected symbol at line " + token.lineNo() + ". Expected '" + (char)expected +"', got '" + token.get().codePointAt(0) + "'.");
        }
    }
    private static void expectTokens(StringToken token, String expected){
        if(!token.get().equals(expected)){
            throw new ParseError("Unexpected symbol at line " + token.lineNo() + ". Expected '" + expected +"', got '" + token.get() + "'.");
        }
    }
}
