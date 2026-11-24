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
WHITE_SPACE=[ \t\f]+
COMMENT=("//"[^\r\n]*|"#"[^\r\n]*|"/*"[\s\S]*?"*/")
NUMBER=[0-9]+(\.[0-9]+)?
TEXT_BLOCK=\"\"\"([^\"]|\"[^\"]|\"\"[^\"])*\"\"\"
STRING=\"([^\"\\]|\\.)*\"
IDENTIFIER=@?[a-zA-Z_][a-zA-Z0-9_]*

%%

<YYINITIAL> {
  {WHITE_SPACE}      { return TokenType.WHITE_SPACE; }
  {CRLF}             { return LinhScriptTypes.CRLF; }
  {COMMENT}          { return LinhScriptTypes.COMMENT; }

  "use"              { return LinhScriptTypes.USE; }
  "var"              { return LinhScriptTypes.VAR; }
  "print"            { return LinhScriptTypes.PRINT; }
  "input"            { return LinhScriptTypes.INPUT; }
  "if"               { return LinhScriptTypes.IF; }
  "else"             { return LinhScriptTypes.ELSE; }
  "switch"           { return LinhScriptTypes.SWITCH; }
  "case"             { return LinhScriptTypes.CASE; }
  "default"          { return LinhScriptTypes.DEFAULT; }
  "break"            { return LinhScriptTypes.BREAK; }
  "continue"         { return LinhScriptTypes.CONTINUE; }
  "while"            { return LinhScriptTypes.WHILE; }
  "for"              { return LinhScriptTypes.FOR; }
  "to"               { return LinhScriptTypes.TO; }
  "in"               { return LinhScriptTypes.IN; }
  "random"           { return LinhScriptTypes.RANDOM; }
  "func"             { return LinhScriptTypes.FUNC; }
  "call"             { return LinhScriptTypes.CALL; }
  "return"           { return LinhScriptTypes.RETURN; }
  "class"            { return LinhScriptTypes.CLASS; }
  "extends"          { return LinhScriptTypes.EXTENDS; }
  "add"              { return LinhScriptTypes.ADD; }
  "size"             { return LinhScriptTypes.SIZE; }
  "len"              { return LinhScriptTypes.LEN; }
  "push"             { return LinhScriptTypes.PUSH; }
  "pop"              { return LinhScriptTypes.POP; }
  "type"             { return LinhScriptTypes.TYPE; }
  "str"              { return LinhScriptTypes.STR; }
  "num"              { return LinhScriptTypes.NUM; }
  "bool"             { return LinhScriptTypes.BOOL; }
  "read"             { return LinhScriptTypes.READ; }
  "write"            { return LinhScriptTypes.WRITE; }
  "true"             { return LinhScriptTypes.TRUE; }
  "false"            { return LinhScriptTypes.FALSE; }
  "null"             { return LinhScriptTypes.NULL; }
  "not"              { return LinhScriptTypes.NOT; }
  "import"           { return LinhScriptTypes.IMPORT; }
  "new"              { return LinhScriptTypes.NEW; }
  "try"              { return LinhScriptTypes.TRY; }
  "catch"            { return LinhScriptTypes.CATCH; }
  "finally"          { return LinhScriptTypes.FINALLY; }
  "throw"            { return LinhScriptTypes.THROW; }

  ","                { return LinhScriptTypes.COMMA; }
  ".."               { return LinhScriptTypes.OP_RANGE; }
  "."                { return LinhScriptTypes.DOT; }
  ";"                { return LinhScriptTypes.SEMI; }
  ":"                { return LinhScriptTypes.COLON; }
  "?"                { return LinhScriptTypes.QUESTION; }
  "!"                { return LinhScriptTypes.OP_NOT; }
  "="                { return LinhScriptTypes.OP_ASSIGN; }
  "++"               { return LinhScriptTypes.OP_INC; }
  "--"               { return LinhScriptTypes.OP_DEC; }
  "+="               { return LinhScriptTypes.OP_PLUS_ASSIGN; }
  "-="               { return LinhScriptTypes.OP_MINUS_ASSIGN; }
  "*="               { return LinhScriptTypes.OP_MUL_ASSIGN; }
  "/="               { return LinhScriptTypes.OP_DIV_ASSIGN; }
  "=="               { return LinhScriptTypes.OP_EQ; }
  "!="               { return LinhScriptTypes.OP_NE; }
  ">="               { return LinhScriptTypes.OP_GE; }
  "<="               { return LinhScriptTypes.OP_LE; }
  ">"                { return LinhScriptTypes.OP_GT; }
  "<"                { return LinhScriptTypes.OP_LT; }
  "&&"               { return LinhScriptTypes.OP_AND; }
  "||"               { return LinhScriptTypes.OP_OR; }
  "+"                { return LinhScriptTypes.OP_PLUS; }
  "-"                { return LinhScriptTypes.OP_MINUS; }
  "*"                { return LinhScriptTypes.OP_MUL; }
  "%"                { return LinhScriptTypes.OP_MOD; }
  "^"                { return LinhScriptTypes.OP_POW; }
  "/"                { return LinhScriptTypes.OP_DIV; }
  "("                { return LinhScriptTypes.L_PAREN; }
  ")"                { return LinhScriptTypes.R_PAREN; }
  "{"                { return LinhScriptTypes.L_BRACE; }
  "}"                { return LinhScriptTypes.R_BRACE; }
  "["                { return LinhScriptTypes.L_BRACKET; }
  "]"                { return LinhScriptTypes.R_BRACKET; }

  {NUMBER}           { return LinhScriptTypes.NUMBER; }
  {TEXT_BLOCK}       { return LinhScriptTypes.STRING; }
  {STRING}           { return LinhScriptTypes.STRING; }
  {IDENTIFIER}       { return LinhScriptTypes.IDENTIFIER; }
}

[^] { return TokenType.BAD_CHARACTER; }