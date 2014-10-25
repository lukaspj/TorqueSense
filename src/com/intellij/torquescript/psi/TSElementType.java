package com.intellij.torquescript.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.torquescript.TSLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Lukas on 22-10-2014.
 */
public class TSElementType extends IElementType {
    public TSElementType(@NotNull @NonNls String debugName) {
        super(debugName, TSLanguage.INSTANCE);
    }
}
