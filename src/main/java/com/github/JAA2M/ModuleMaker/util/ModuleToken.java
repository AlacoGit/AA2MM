package com.github.JAA2M.ModuleMaker.util;


public interface ModuleToken<T> {
    // The actual token value
    T get();

    // Should return True if there are no more tokens available
    boolean isEOF();
}
