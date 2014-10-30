package com.intellij.torquescript.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.torquescript.psi.TSElementFactory;
import com.intellij.torquescript.psi.TSFnDeclStmt;
import com.intellij.torquescript.psi.TSTypes;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Lukas on 25-10-2014.
 */
public class SimplePsiImplUtil {
    public static String getKey(TSFnDeclStmt element) {
        ASTNode keyNode = element.getNode().findChildByType(TSTypes.ID);
        if (keyNode != null) {
            return keyNode.getText();
        } else {
            return null;
        }
    }

    public static String getName(TSFnDeclStmt element) {
        return getKey(element);
    }

    public static PsiElement setName(TSFnDeclStmt element, String newName) {
        ASTNode keyNode = element.getNode().findChildByType(TSTypes.ID);
        if(keyNode != null) {
            TSFnDeclStmt property = TSElementFactory.createFnDecl(element.getProject(), newName);
            ASTNode newKeyNode = property.getFirstChild().getNode();
            element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(PsiElement element) {
        ASTNode keyNode = element.getNode().findChildByType(TSTypes.ID);
        if(keyNode != null) {
            return keyNode.getPsi();
        } else {
            return null;
        }
    }

    @NotNull
    public static TextRange getTextRangeForReference(@NotNull PsiElement element) {
        return rangeInParent(element.getTextRange(), getNameIdentifier(element).getTextRange());
    }

    @NotNull
    private static TextRange rangeInParent(@NotNull TextRange parent, @NotNull TextRange child) {
        int start = child.getStartOffset() - parent.getStartOffset();
        return TextRange.create(start, start + child.getLength());
    }
}
