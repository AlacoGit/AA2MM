package com.github.JAA2M.TestwString;

import java.nio.charset.StandardCharsets;

import com.github.JAA2M.Module.Value;
import com.github.JAA2M.wString;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWStringConversions {

    @Test
    void testDefaultToDefault(){
        String source = new String("foo".getBytes());
        Value.strValue temp = wString.of(source);
        String result = temp.getValue();
        assertEquals(source,result);
    }


    @Test
    void testUTF16LEToUTF16LE(){
        String source = new String("foo".getBytes(StandardCharsets.UTF_16LE), StandardCharsets.UTF_16LE);
        wString temp = wString.of(source);
        String result = temp.getWString();
        assertEquals(source,result);
    }

    @Test
    void testUTF16LEToDefault(){
        String source = new String("foo".getBytes(StandardCharsets.UTF_16LE), StandardCharsets.UTF_16LE);
        Value.strValue temp = wString.of(source);
        String result = temp.getValue();
        assertEquals(source,result);
    }
}
