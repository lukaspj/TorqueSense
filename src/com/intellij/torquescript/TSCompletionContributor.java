package com.intellij.torquescript;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.torquescript.psi.TSTypes;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Lukas on 30-10-2014.
 */
public class TSCompletionContributor extends CompletionContributor {
    public TSCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(TSTypes.ID).withLanguage(TSLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters completionParameters, ProcessingContext processingContext, @NotNull CompletionResultSet completionResultSet) {
                        for(String key : keyWords)
                            completionResultSet.addElement(LookupElementBuilder.create(key));
                    }
                }
        );
    }

    String[] keyWords = new String[] {
            "datablock",
            "function",
            "assert",
            "singleton",
            "package",
            "switch",
            "switch$",
            "for",
            "foreach",
            "foreach$",
            "while"
    };
}
