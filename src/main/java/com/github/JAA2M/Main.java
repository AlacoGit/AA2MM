package com.github.JAA2M;

import com.github.JAA2M.ModulePrinter.ModulePrinter;
import com.github.JAA2M.ModuleReader.ModuleReader;
import com.github.JAA2M.Module.Module;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args){
        Module module = ModuleReader.parse("E:\\AA2MM\\src\\test\\modules\\Evening Fashion");
        try(PrintStream ps = new PrintStream("test.txt")) {
            ModulePrinter.Print(module,ps);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

