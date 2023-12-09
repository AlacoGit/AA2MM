package com.github.JAA2M;

/**
 * Used internally to indicate a String should always be encoded using the {@code UTF_16LE} Charset.
 *
 */
public sealed interface wString permits com.github.JAA2M.Module.Value.strValue{
    String get();

    static wString of(String in){
        return com.github.JAA2M.Module.Value.of(in);
    }
    static wString of(byte[] in){
        return com.github.JAA2M.Module.Value.of(in);
    }
}
