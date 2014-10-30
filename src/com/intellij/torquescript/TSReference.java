package com.intellij.torquescript;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.torquescript.psi.TSFnDeclStmt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lukas on 25-10-2014.
 */
public class TSReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
    private String key;

    public TSReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        key = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        final List<TSFnDeclStmt> fnDecls = TSUtil.findProperties(project, key);
        List<ResolveResult> results = new ArrayList<ResolveResult>();
        for(TSFnDeclStmt fnDecl : fnDecls) {
            results.add(new PsiElementResolveResult(fnDecl));
        }
        return results.toArray(new ResolveResult[results.size()]);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        Project project = myElement.getProject();
        List<TSFnDeclStmt> fnDecls = TSUtil.findProperties(project);
        List<LookupElement> variants = new ArrayList<LookupElement>();
        for(final TSFnDeclStmt fnDecl : fnDecls) {
            if(fnDecl.getKey() != null && fnDecl.getKey().length() > 0) {
                variants.add(LookupElementBuilder.create(fnDecl)
                    .withIcon(TSIcons.FILE)
                    .withTypeText(fnDecl.getContainingFile().getName())
                );
            }
        }
        return variants.toArray();
    }
}
