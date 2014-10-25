package com.intellij.torquescript;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

/**
 * Created by Lukas on 22-10-2014.
 */
public class TSLexerAdapter extends FlexAdapter {
    public TSLexerAdapter() {
        super(new TSLexer((Reader) null));
    }
}
