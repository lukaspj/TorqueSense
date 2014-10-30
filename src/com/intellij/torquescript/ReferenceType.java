package com.intellij.torquescript;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

/**
 * Created by Lukas on 26-10-2014.
 */

public interface ReferenceType {
    public PsiReference createReference (PsiElement element);
    public Class getRefClass();
    public boolean isInstanceOf(PsiElement element);
}
