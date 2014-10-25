package com.intellij.torquescript;

/**
 * Created by Lukas on 22-10-2014.
 */

import com.intellij.lang.Language;

public class TSLanguage extends Language {
    public static final TSLanguage INSTANCE = new TSLanguage();

    private TSLanguage() {
        super("TorqueScript");
    }
}
