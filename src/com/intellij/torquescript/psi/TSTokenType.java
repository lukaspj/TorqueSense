package com.intellij.torquescript.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.torquescript.TSLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Lukas on 22-10-2014.
 */
public class TSTokenType extends IElementType {
    public TSTokenType(@NotNull @NonNls String debugName) {
        super(debugName, TSLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "TSTokenType." + super.toString();
    }
}
