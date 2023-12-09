package com.github.JAA2M.ModuleMaker.parser;

import com.github.JAA2M.ModuleMaker.util.ModuleToken;

public interface StringToken extends ModuleToken<String> {
    boolean isQuote();
    int lineNo();
    boolean isEOL();
}
