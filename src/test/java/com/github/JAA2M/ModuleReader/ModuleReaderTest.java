package com.github.JAA2M.ModuleReader;

import com.github.JAA2M.wString;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ModuleReaderTest {
    private static Constructor<ModuleReader> mrConstructor;
    private

    @BeforeAll
    static void fetchConstructor(){
        try {
            mrConstructor = ModuleReader.class.getDeclaredConstructor(ByteBuffer.class);
            mrConstructor.setAccessible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest
    @MethodSource("readWstringSource")
    void readWstring(String input){
        try {
            ByteBuffer bb = toByteBuffer(input);
            ModuleReader mr = mrConstructor.newInstance(bb);
            Method readString = ModuleReader.class.getDeclaredMethod("readString", null);
            readString.setAccessible(true);
            wString ret = (wString)readString.invoke(mr);
            assertEquals(input,ret.get());
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }


    }
    static Stream<String> readWstringSource(){
        return Stream.of("a String","a String!", "12 a 3 String","");
    }
    static ByteBuffer toByteBuffer(String in){
        ByteBuffer bb = ByteBuffer.wrap(new byte[in.length()*2+5]);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.putInt(in.length());
        bb.put(in.getBytes(StandardCharsets.UTF_16LE));
        bb.flip();
        return bb;
    }
}