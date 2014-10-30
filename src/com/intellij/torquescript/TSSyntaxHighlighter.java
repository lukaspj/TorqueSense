package com.intellij.torquescript;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.torquescript.psi.TSTypes;
import org.jetbrains.annotations.NotNull;

import java.io.Reader;

/**
 * Created by Lukas on 22-10-2014.
 */
public class TSSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey LOCALVARIABLE = TextAttributesKey.createTextAttributesKey("TS_LOCALVARIABLE", DefaultLanguageHighlighterColors.LOCAL_VARIABLE);
    public static final TextAttributesKey GLOBALVARIABLE = TextAttributesKey.createTextAttributesKey("TS_GLOBALVARIABLE", DefaultLanguageHighlighterColors.GLOBAL_VARIABLE);
    public static final TextAttributesKey COMMENT = TextAttributesKey.createTextAttributesKey("TS_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey FUNCTION = TextAttributesKey.createTextAttributesKey("TS_FUNCTION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    public static final TextAttributesKey ID = TextAttributesKey.createTextAttributesKey("TS_ID", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey NUMBER = TextAttributesKey.createTextAttributesKey("TS_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey STRING = TextAttributesKey.createTextAttributesKey("TS_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey KEYWORD = TextAttributesKey.createTextAttributesKey("TS_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey PARENTHESES = TextAttributesKey.createTextAttributesKey("TS_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey CLASSNAME = TextAttributesKey.createTextAttributesKey("TS_CLASSNAME", DefaultLanguageHighlighterColors.CLASS_NAME);

    public static final TextAttributesKey[] LOCALVARIABLE_KEYS = new TextAttributesKey[]{LOCALVARIABLE};
    public static final TextAttributesKey[] GLOBALVARIABLE_KEYS = new TextAttributesKey[]{GLOBALVARIABLE};
    public static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    public static final TextAttributesKey[] FUNCTION_KEYS = new TextAttributesKey[]{FUNCTION};
    public static final TextAttributesKey[] ID_KEYS = new TextAttributesKey[]{ID};
    public static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{NUMBER};
    public static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{STRING};
    public static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{KEYWORD};
    public static final TextAttributesKey[] PARENTHESES_KEYS = new TextAttributesKey[]{PARENTHESES};
    public static final TextAttributesKey[] CLASSNAME_KEYS = new TextAttributesKey[]{CLASSNAME};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new FlexAdapter(new TSLexer((Reader) (null)));
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType iElementType) {
        if(iElementType.equals(TSTypes.LOCALVAR)) {
            return LOCALVARIABLE_KEYS;
        } else if (iElementType.equals(TSTypes.GLOBALVAR)) {
            return GLOBALVARIABLE_KEYS;
        } else if (iElementType.equals(TSTypes.COMMENT)) {
            return COMMENT_KEYS;
        } else if (iElementType.equals(TSTypes.FUNCTION) || iElementType.equals(TSTypes.TAG) || iElementType.equals(TSTypes.PACKAGE)) {
            return FUNCTION_KEYS;
        } else if (iElementType.equals(TSTypes.ID)) {
            return ID_KEYS;
        } else if (iElementType.equals(TSTypes.INTEGER) || iElementType.equals(TSTypes.FLOAT)) {
            return NUMBER_KEYS;
        } else if (iElementType.equals(TSTypes.STRING)) {
            return STRING_KEYS;
        } else if (iElementType.equals(TSTypes.IF)
                || iElementType.equals(TSTypes.ELSE)
                || iElementType.equals(TSTypes.FOR)
                || iElementType.equals(TSTypes.FOREACH)
                || iElementType.equals(TSTypes.FOREACHSTR)
                || iElementType.equals(TSTypes.WHILE)
                || iElementType.equals(TSTypes.SWITCH)
                || iElementType.equals(TSTypes.SWITCHSTR)
                || iElementType.equals(TSTypes.CASE)
                || iElementType.equals(TSTypes.BREAK)
                || iElementType.equals(TSTypes.DEFAULT)
                || iElementType.equals(TSTypes.RETURN)
                || iElementType.equals(TSTypes.DATABLOCK)
                || iElementType.equals(TSTypes.SINGLETON)
                || iElementType.equals(TSTypes.IN)
                || iElementType.equals(TSTypes.NEW)) {
            return KEYWORD_KEYS;
        } else if (iElementType.equals(TSTypes.LPAREN) || iElementType.equals(TSTypes.RPAREN)) {
            return PARENTHESES_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}
