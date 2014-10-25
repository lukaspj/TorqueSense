package com.intellij.torquescript.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.torquescript.TSFileType;
import com.intellij.torquescript.TSLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by Lukas on 22-10-2014.
 */
public class TSFile extends PsiFileBase {
    public TSFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, TSLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return TSFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "TorqueScript File";
    }

    @Nullable
    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}
