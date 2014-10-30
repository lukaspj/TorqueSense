package com.intellij.torquescript;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.torquescript.psi.TSFnDeclStmt;
import com.intellij.torquescript.psi.impl.SimplePsiImplUtil;
import com.intellij.torquescript.psi.impl.TSFnDeclStmtImpl;
import com.intellij.util.ProcessingContext;
import org.apache.velocity.app.event.ReferenceInsertionEventHandler;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Lukas on 25-10-2014.
 */
public class TSReferenceContributor extends PsiReferenceContributor {

    @Override
    public void registerReferenceProviders(PsiReferenceRegistrar registrar) {
        register(registrar, new ReferenceType() {
            @Override
            public PsiReference createReference(PsiElement element) {
                return new TSReference(element, SimplePsiImplUtil.getTextRangeForReference(element));
            }

            @Override
            public Class getRefClass() {
                return TSFnDeclStmt.class;
            }

            @Override
            public boolean isInstanceOf(PsiElement element) {
                return element instanceof TSFnDeclStmt;
            }
        });
    }

    public static void register(PsiReferenceRegistrar registrar, final ReferenceType referenceType) {
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(referenceType.getRefClass()),
                new PsiReferenceProvider() {
                    public ReferenceType refType = referenceType;

                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext processingContext) {
                        return refType.isInstanceOf(element) ? new PsiReference[] { refType.createReference(element) } : PsiReference.EMPTY_ARRAY;
                    }
                });
    }
}
