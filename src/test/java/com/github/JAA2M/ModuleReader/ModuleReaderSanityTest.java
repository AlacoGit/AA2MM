package com.github.JAA2M.ModuleReader;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import com.github.JAA2M.wString;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModuleReaderSanityTest {
    private static final String[] validStrings = {"Test String.","Longer String to simulate a description.","12345"};

    private static Stream<String> validStringStream(){
        return Stream.of(validStrings);
    }

    @ParameterizedTest
    @MethodSource("validStringStream")
    void correctlyReadsValidString(String _in){
        String in = new String(_in.getBytes(StandardCharsets.UTF_16LE),StandardCharsets.UTF_16LE);
        byte[] bytes = in.getBytes(StandardCharsets.UTF_16LE);
        int length = _in.length();
        ByteBuffer bb = ByteBuffer.allocate((length*2)+Integer.BYTES);
        bb.putInt(length);
        bb.put(bytes);
        bb.flip();
        ModuleReader mr = new ModuleReader(bb);
        try{
            wString ret = mr.readString();
            byte[] retBytes = ret.getWString().getBytes();
            assertEquals(in,ret.getWString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}