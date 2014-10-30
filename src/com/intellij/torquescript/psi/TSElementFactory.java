package com.intellij.torquescript.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import com.intellij.torquescript.TSFileType;

/**
 * Created by Lukas on 25-10-2014.
 */
public class TSElementFactory {
    public static TSFnDeclStmt createFnDecl(Project project, String name) {
        final TSFile file = createFile(project, name);
        return (TSFnDeclStmt) file.getFirstChild();
    }

    public static TSFile createFile(Project project, String text) {
        String name = "dummy.simple";
        return (TSFile) PsiFileFactory.getInstance(project)
                .createFileFromText(name, TSFileType.INSTANCE, text);
    }
}
