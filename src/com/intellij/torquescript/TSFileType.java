package com.intellij.torquescript;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by Lukas on 22-10-2014.
 */
public class TSFileType extends LanguageFileType {
    public static final TSFileType INSTANCE = new TSFileType();

    private TSFileType() {
        super(TSLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "TorqueScript File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Script for the Torque3D Game Engine";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "t3d";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return TSIcons.FILE;
    }
}
