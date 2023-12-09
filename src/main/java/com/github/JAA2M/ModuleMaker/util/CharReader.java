package com.github.JAA2M.ModuleMaker.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class CharReader implements AutoCloseable {
    private final BufferedReader in;
    private boolean isEOF = false;

    public CharReader(Path path) throws FileNotFoundException {
        in = new BufferedReader(new FileReader(path.toFile()));
    }

    public int peek() throws IOException {
        in.mark(10);
        int peek = in.read();
        in.reset();
        return peek;
    }

    public int peek(char[] buff) throws IOException {
        in.mark(buff.length+1);
        int num = in.read(buff,0, buff.length);
        in.reset();
        return num;
    }

    public int read() throws IOException {
        return in.read();
    }

    public int read(char[] buff) throws IOException {
        return in.read(buff,0,buff.length);
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
