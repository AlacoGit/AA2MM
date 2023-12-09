package com.github.JAA2M.ModuleMaker.parser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class ModuleTokenizerSanityTests {

    @Test
    void ModuleTokenizerCorrectlyReadsIntVar(){
        var mt = new ModuleTokenizer(new StringReader("int test = 1\n"));
        try {
            var token = mt.nextToken();
            assertEquals("int", token.get());
            token = mt.nextToken();
            assertEquals("test",token.get());
            token = mt.nextToken();
            assertEquals("=",token.get());
            token = mt.nextToken();
            assertEquals("1",token.get());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}