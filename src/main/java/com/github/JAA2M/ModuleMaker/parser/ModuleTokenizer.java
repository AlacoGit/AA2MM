package com.github.JAA2M.ModuleMaker.parser;

import com.github.JAA2M.ModuleMaker.ParseError;

import java.io.*;

import static java.io.StreamTokenizer.*;

/**
 * A wrapper around an underlying {@code StreamTokenizer}
 */
public class ModuleTokenizer {

    private static final int DOUBLE_QUOTE = '"';
    private static final int SINGLE_QUOTE = '\'';
    private static final int PLUS_SIGN = '+';
    private static final int EQUALS = '=';
    private static final int LEFT_BRACKET = '[';
    private static final int RIGHT_BRACKET = ']';
    private static final int LEFT_cBRACKET = '{';
    private static final int RIGHT_cBRACKET = '}';

    private final StreamTokenizer tokenizer;
    private StringToken current;
    public ModuleTokenizer(Reader reader){
        this.tokenizer = new StreamTokenizer(reader);
        /*
        StreamTokenizer makes several regrettable default syntax choices, we reset them here
         */
        tokenizer.resetSyntax();

        tokenizer.wordChars('a', 'z');
        tokenizer.wordChars('A', 'Z');
        tokenizer.wordChars(128 + 32, 255);
        tokenizer.whitespaceChars(0, ' ');

        //tokenizer.eolIsSignificant(true);
        tokenizer.slashSlashComments(true);
        tokenizer.slashStarComments(true);
        tokenizer.lowerCaseMode(true);
        tokenizer.quoteChar(DOUBLE_QUOTE);
        tokenizer.quoteChar(SINGLE_QUOTE);


    }
    private record parsedToken(String value, int lineNo, boolean isEOF, boolean isQuote, boolean isEOL) implements StringToken{

        @Override
        public String get() {
            return this.value;
        }

        @Override
        public boolean isEOF() {
            return isEOF;
        }

        @Override
        public boolean isQuote() {
            return isQuote;
        }

        @Override
        public int lineNo() {
            return lineNo;
        }

        @Override
        public boolean isEOL(){
            return isEOL;
        }
    }



    /**
     * Reads a value from the backing {@code StreamTokenizer} via {@code StreamTokenizer#nextToken()} and then pushes the same value back, effectively 'peek'ing the next token.
     * @return A {@code StringToken} describing the token that was read by this classes backing {@code StreamTokenizer}
     * @throws IOException - Propagated from this classes backing {@code StreamTokenizer#nextToken()}
     * @see StreamTokenizer#pushBack()
     * @see StreamTokenizer#nextToken()
     */
    public StringToken peek() throws IOException {
        int token = this.tokenizer.nextToken();
        this.tokenizer.pushBack();
        boolean isQuote = isQuoteChar(token);
        String value = token == TT_WORD ? tokenizer.sval : Character.toString(token);
        return newStringToken(value, tokenizer.lineno(), token == TT_EOF, isQuote, token == TT_EOL);

    }

    /**
     * Calls {@code nextToken()} on this classes backing {@code StreamTokenizer} instance.
     * @return A {@code StringToken} describing the token that was read by this classes backing {@code StreamTokenizer}
     * @throws IOException - Propagated from this classes backing {@code StreamTokenizer#nextToken()}
     * @see StreamTokenizer#nextToken()
     */
    public StringToken nextToken() throws IOException {
        int token = this.tokenizer.nextToken();
        boolean isQuote = isQuoteChar(token);
        String value = token == TT_WORD ? tokenizer.sval : Character.toString(token);
        StringToken ret = newStringToken(value, tokenizer.lineno(), token == TT_EOF, isQuote, token == TT_EOL);
        this.current = ret;
        return ret;


    }

    /**
     * Returns the last token that was returned from {code nextToken()}
     * @return The last {@code StringToken} that was returned from {@code nextToken()}
     * @see ModuleTokenizer#nextToken()
     */
    public StringToken getCurrent(){
        return this.current;
    }

    private boolean isQuoteChar(int token){
        return (token == DOUBLE_QUOTE || token == SINGLE_QUOTE);
    }

    public void toggleEOLIsSignificant(boolean flag){
        this.tokenizer.eolIsSignificant(flag);
    }
    String translateToken(){
        return switch(tokenizer.ttype){
            case TT_EOF -> "EOF";
            case TT_EOL -> "EOL";
            case TT_WORD -> tokenizer.sval;
            case TT_NUMBER -> Double.toString(tokenizer.nval);
            case DOUBLE_QUOTE -> "\"";
            case SINGLE_QUOTE -> "'";
            default -> throw new RuntimeException("Should not reach here.");
        };
    }

    private StringToken newStringToken(String value, int lineno, boolean isEOF, boolean isQuote, boolean isEOL){
        return new parsedToken(value, lineno, isEOF, isQuote, isEOL);
    }
    private void expectSymbol(String expected, String actual){
        if(!expected.equalsIgnoreCase(actual)){
            throw new ParseError("Unexpected symbol at line " + tokenizer.lineno() + ". Expected '" + expected +"', got '" + actual + "'.");
        }
    }

    private void expectSymbol(int expected, int actual){
        if(expected != actual) {
            throwUnexpectedSymbolError(expected,actual);
        }
    }

    public void expectNextToken(String expected) throws IOException {
        var token = this.nextToken();
        if(token.isQuote()){
            this.expectSymbol(expected,token.get());
        }
    }

    public void expectNextToken(int expected) throws IOException{
        var token = this.nextToken();
        int intToken = token.get().codePointAt(0);
        if((isQuoteChar(expected) && !token.isQuote()) ||
                (expected == TT_EOL && (!token.isEOL())) ||
                (expected == TT_EOF && (!token.isEOF()))){
            throwUnexpectedSymbolError(expected,intToken);
        } else {
            expectSymbol(expected,intToken);
        }

    }

    private void throwUnexpectedSymbolError(int expected, int actual){
        throw new ParseError("Unexpected symbol at line " + tokenizer.lineno() + ". Expected '" + (char)expected +"', got '" + (char)actual + "'.");
    }
}
