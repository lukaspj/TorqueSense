package com.intellij.torquescript;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.torquescript.psi.TSTypes;
import com.intellij.psi.TokenType;

%%

%class TSLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{
    return;
%eof}

DIGIT = [0-9]
INTEGER = {DIGIT}+
FLOAT = ({INTEGER}?\.{INTEGER})|({INTEGER}(\.{INTEGER})?[eE][+-]?{INTEGER})
LETTER = [A-Za-z_]
FILECHAR = [A-Za-z_\.]
VARMID =  [:A-Za-z0-9_]
IDTAIL =  [A-Za-z0-9_]
VARTAIL = {VARMID}*{IDTAIL}
LOCALVAR = "%"{LETTER}{VARTAIL}*
GLOBALVAR = "$"{LETTER}{VARTAIL}*
ID = {LETTER}{IDTAIL}*
ILID = [$%]{DIGIT}+{LETTER}{VARTAIL}*
FILENAME = {FILECHAR}+
SPACE = [ \t\v\f]
HEXDIGIT = [a-fA-F0-9]
CRLF = \r?\n
END_OF_LINE_COMMENT="//"[^\r\n]*
STRING="\"".*"\""
TAG="\'".*"\'"

%ignorecase

%%

<YYINITIAL> {
    {END_OF_LINE_COMMENT}                           { yybegin(YYINITIAL); return TSTypes.COMMENT; }
    {LOCALVAR}                                      { return TSTypes.LOCALVAR; }
    {GLOBALVAR}                                     { return TSTypes.GLOBALVAR; }
    "function"                                      { return TSTypes.FUNCTION; }
    "datablock"                                     { return TSTypes.DATABLOCK; }
    "singleton"                                     { return TSTypes.SINGLETON; }
    "new"                                           { return TSTypes.NEW; }
    "package"                                       { return TSTypes.PACKAGE; }
    "for"                                           { return TSTypes.FOR; }
    "foreach"                                       { return TSTypes.FOREACH; }
    "foreach$"                                      { return TSTypes.FOREACHSTR; }
    "while"                                         { return TSTypes.WHILE; }
    "switch"                                        { return TSTypes.SWITCH; }
    "switch$"                                       { return TSTypes.SWITCHSTR; }
    "case"                                          { return TSTypes.CASE; }
    "default"                                       { return TSTypes.DEFAULT; }
    "break"                                         { return TSTypes.BREAK; }
    "return"                                        { return TSTypes.RETURN; }
    "in"                                            { return TSTypes.IN; }
    "if"                                            { return TSTypes.IF; }
    "else"                                          { return TSTypes.ELSE; }
    "assert"                                        { return TSTypes.ASSERT; }

    "in"                                            { return TSTypes.IN; }

// Brackets
    "("                                             { return TSTypes.LPAREN; }
    ")"                                             { return TSTypes.RPAREN; }
    "{"                                             { return TSTypes.LBRACE; }
    "}"                                             { return TSTypes.RBRACE; }
    "["                                             { return TSTypes.LBRACK; }
    "]"                                             { return TSTypes.RBRACK; }

// Seperators
    "::"                                            { return TSTypes.COLONCOLON; }
    ":"                                             { return TSTypes.COLON; }
    ","                                             { return TSTypes.COMMA; }
    "."                                             { return TSTypes.DOT; }
    ";"                                             { return TSTypes.SEMICOLON; }
    "-->"                                           { return TSTypes.INTERNALNAMERECUR; }
    "->"                                            { return TSTypes.INTERNALNAME; }

// Binary operators

// Assignments
    "="                                             { return TSTypes.ASSIGN; }
    "+="                                            { return TSTypes.PLUSASSIGN; }
    "-="                                            { return TSTypes.MINUSASSIGN; }
    "/="                                            { return TSTypes.DIVIDEASSIGN; }
    "*="                                            { return TSTypes.TIMESASSIGN; }
    "%="                                            { return TSTypes.MODULOASSIGN; }
    "&="                                            { return TSTypes.ANDASSIGN; }
    "^="                                            { return TSTypes.XORASSIGN; }
    "|="                                            { return TSTypes.ORASSIGN; }
    "<<="                                            { return TSTypes.SHIFTLASSIGN; }
    ">>="                                            { return TSTypes.SHIFTRASSIGN; }

// Comparisons
    "=="                                            { return TSTypes.EQUAL; }
    "$="                                            { return TSTypes.STREQUAL; }
    "!="                                            { return TSTypes.NOTEQUAL; }
    "!$="                                           { return TSTypes.NOTSTREQUAL; }
    ">"                                             { return TSTypes.GT; }
    ">="                                            { return TSTypes.GE; }
    "<"                                             { return TSTypes.LT; }
    "<="                                            { return TSTypes.LE; }

// Arithmetics
    "+"                                             { return TSTypes.PLUS; }
    "-"                                             { return TSTypes.MINUS; }
    "++"                                            { return TSTypes.PLUSPLUS; }
    "--"                                            { return TSTypes.MINUSMINUS; }
    "*"                                             { return TSTypes.TIMES; }
    "/"                                             { return TSTypes.DIVIDE; }
    "%"                                             { return TSTypes.MODULO; }

// Logical
    "!"                                             { return TSTypes.EXCLAMATION; }
    "?"                                             { return TSTypes.QUESTION; }
    "&&"                                            { return TSTypes.LOGOR; }
    "||"                                            { return TSTypes.LOGAND; }

// Bitwise
    "~"                                             { return TSTypes.BITCOMPL; }
    "&"                                             { return TSTypes.BITAND; }
    "|"                                             { return TSTypes.BITOR; }
    "^"                                             { return TSTypes.BITXOR; }
    "<<"                                            { return TSTypes.BITLSHIFT; }
    ">>"                                            { return TSTypes.BITRSHIFT; }

// Concatenations
    "@"                                             { return TSTypes.CONCAT; }
    "TAB"                                           { return TSTypes.CONCAT; }
    "SPC"                                           { return TSTypes.CONCAT; }
    "NL"                                            { return TSTypes.CONCAT; } //TODO should this interpret everything as concats?

    {ID}                                            { return TSTypes.ID; }
    {INTEGER}                                       { return TSTypes.INTEGER; }
    {FLOAT}                                         { return TSTypes.FLOAT; }
    {STRING}                                        { return TSTypes.STRING; }
    {TAG}                                           { return TSTypes.TAG; }
}
    ({SPACE}|{CRLF})+                               { return TokenType.WHITE_SPACE; }
.                                                   { return TokenType.BAD_CHARACTER; }