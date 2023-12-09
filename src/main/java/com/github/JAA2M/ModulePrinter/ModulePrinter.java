package com.github.JAA2M.ModulePrinter;

import com.github.JAA2M.Module.Module;

import java.io.IOException;
import java.io.PrintStream;

public class ModulePrinter {

    /**
     * Prints a string representation of a {@link com.github.JAA2M.Module.Module} to a given {@link PrintStream}.
     * @param module {@link Module} to be printed.
     * @param out {@link PrintStream} to write to.
     * @throws IOException Propagates any Exceptions thrown while writing to the given {@link PrintStream}.
     * @see Module
     */
    public static void Print(Module module, PrintStream out) throws IOException {
        out.println(module.name);
        out.println(module.desc);
        out.println(module.globals);
    }

    /**
     * Prints a string representation of a {@link com.github.JAA2M.Module.Module} to {@link java.lang.System#out}.
     * Equivalent to calling {@code ModulePrinter.Print(module,System.out)}.
     * @param module {@link com.github.JAA2M.Module.Module} to be printed.
     * @throws IOException Propagates any Exceptions thrown while writing to {@link java.lang.System#out}.
     * @see Module
     * @see java.lang.System#out
     */
    public static void Print(Module module) throws IOException {
        Print(module,System.out);
    }
}
