package me.hql.linhscript;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import me.hql.linhscript.psi.LinhScriptTypes;
import com.intellij.psi.TokenType;

%%

%class LinhScriptLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF=\R
WHITE_SPACE=[\ \n\t\f]+
NUMBER=[0-9]+(\.[0-9]+)?
STRING=\"[^\"]*\"
IDENTIFIER=[a-zA-Z_][a-zA-Z0-9_]*

%%

<YYINITIAL> {
  {WHITE_SPACE}      { return TokenType.WHITE_SPACE; }

  "print"            { return LinhScriptTypes.PRINT; }
  "input"            { return LinhScriptTypes.INPUT; }
  "if"               { return LinhScriptTypes.IF; }
  "else"             { return LinhScriptTypes.ELSE; }
  "while"            { return LinhScriptTypes.WHILE; }
  "for"              { return LinhScriptTypes.FOR; }
  "to"               { return LinhScriptTypes.TO; }
  "in"               { return LinhScriptTypes.IN; }
  "random"           { return LinhScriptTypes.RANDOM; }
  "func"             { return LinhScriptTypes.FUNC; }
  "call"             { return LinhScriptTypes.CALL; }
  "return"           { return LinhScriptTypes.RETURN; }
  "add"              { return LinhScriptTypes.ADD; }
  "size"             { return LinhScriptTypes.SIZE; }
  "len"              { return LinhScriptTypes.LEN; }
  "push"             { return LinhScriptTypes.PUSH; }
  "pop"              { return LinhScriptTypes.POP; }
  "type"             { return LinhScriptTypes.TYPE; }
  "str"               { return LinhScriptTypes.STR; }
  "num"               { return LinhScriptTypes.NUM; }
  "bool"              { return LinhScriptTypes.BOOL; }
  "read"              { return LinhScriptTypes.READ; }
  "write"             { return LinhScriptTypes.WRITE; }
  "true"              { return LinhScriptTypes.TRUE; }
  "false"             { return LinhScriptTypes.FALSE; }
  "null"              { return LinhScriptTypes.NULL; }
  "not"               { return LinhScriptTypes.NOT; }
  "import"            { return LinhScriptTypes.IMPORT; }
  "new"               { return LinhScriptTypes.NEW; }

  "="                { return LinhScriptTypes.OP_ASSIGN; }
  "+"                { return LinhScriptTypes.OP_PLUS; }
  "-"                { return LinhScriptTypes.OP_MINUS; }
  "*"                { return LinhScriptTypes.OP_MUL; }
  "/"                { return LinhScriptTypes.OP_DIV; }
  "("                { return LinhScriptTypes.L_PAREN; }
  ")"                { return LinhScriptTypes.R_PAREN; }
  "{"                { return LinhScriptTypes.L_BRACE; }
  "}"                { return LinhScriptTypes.R_BRACE; }
  "["                { return LinhScriptTypes.L_BRACKET; }
  "]"                { return LinhScriptTypes.R_BRACKET; }

  {NUMBER}           { return LinhScriptTypes.NUMBER; }
  {STRING}           { return LinhScriptTypes.STRING; }
  {IDENTIFIER}       { return LinhScriptTypes.IDENTIFIER; }
}

[^] { return TokenType.BAD_CHARACTER; }