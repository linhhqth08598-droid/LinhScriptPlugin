// This is a generated file. Not intended for manual editing.
package me.hql.linhscript.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import me.hql.linhscript.psi.impl.*;

public interface LinhScriptTypes {

  IElementType BINARY_EXPR = new LinhScriptElementType("BINARY_EXPR");
  IElementType EXPR = new LinhScriptElementType("EXPR");
  IElementType EXPR_STMT = new LinhScriptElementType("EXPR_STMT");
  IElementType PRINT_STMT = new LinhScriptElementType("PRINT_STMT");
  IElementType STMT = new LinhScriptElementType("STMT");
  IElementType VAR_ASSIGNMENT = new LinhScriptElementType("VAR_ASSIGNMENT");

  IElementType ADD = new LinhScriptTokenType("add");
  IElementType BOOL = new LinhScriptTokenType("bool");
  IElementType CALL = new LinhScriptTokenType("call");
  IElementType COMMENT = new LinhScriptTokenType("COMMENT");
  IElementType CRLF = new LinhScriptTokenType("CRLF");
  IElementType ELSE = new LinhScriptTokenType("else");
  IElementType FALSE = new LinhScriptTokenType("false");
  IElementType FOR = new LinhScriptTokenType("for");
  IElementType FUNC = new LinhScriptTokenType("func");
  IElementType IDENTIFIER = new LinhScriptTokenType("IDENTIFIER");
  IElementType IF = new LinhScriptTokenType("if");
  IElementType IMPORT = new LinhScriptTokenType("import");
  IElementType IN = new LinhScriptTokenType("in");
  IElementType INPUT = new LinhScriptTokenType("input");
  IElementType LEN = new LinhScriptTokenType("len");
  IElementType L_BRACE = new LinhScriptTokenType("{");
  IElementType L_BRACKET = new LinhScriptTokenType("[");
  IElementType L_PAREN = new LinhScriptTokenType("(");
  IElementType NEW = new LinhScriptTokenType("new");
  IElementType NOT = new LinhScriptTokenType("not");
  IElementType NULL = new LinhScriptTokenType("null");
  IElementType NUM = new LinhScriptTokenType("num");
  IElementType NUMBER = new LinhScriptTokenType("NUMBER");
  IElementType OP_ASSIGN = new LinhScriptTokenType("=");
  IElementType OP_DIV = new LinhScriptTokenType("/");
  IElementType OP_MINUS = new LinhScriptTokenType("-");
  IElementType OP_MUL = new LinhScriptTokenType("*");
  IElementType OP_PLUS = new LinhScriptTokenType("+");
  IElementType POP = new LinhScriptTokenType("pop");
  IElementType PRINT = new LinhScriptTokenType("print");
  IElementType PUSH = new LinhScriptTokenType("push");
  IElementType RANDOM = new LinhScriptTokenType("random");
  IElementType READ = new LinhScriptTokenType("read");
  IElementType RETURN = new LinhScriptTokenType("return");
  IElementType R_BRACE = new LinhScriptTokenType("}");
  IElementType R_BRACKET = new LinhScriptTokenType("]");
  IElementType R_PAREN = new LinhScriptTokenType(")");
  IElementType SIZE = new LinhScriptTokenType("size");
  IElementType STR = new LinhScriptTokenType("str");
  IElementType STRING = new LinhScriptTokenType("STRING");
  IElementType TO = new LinhScriptTokenType("to");
  IElementType TRUE = new LinhScriptTokenType("true");
  IElementType TYPE = new LinhScriptTokenType("type");
  IElementType WHILE = new LinhScriptTokenType("while");
  IElementType WRITE = new LinhScriptTokenType("write");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == BINARY_EXPR) {
        return new LinhScriptBinaryExprImpl(node);
      }
      else if (type == EXPR) {
        return new LinhScriptExprImpl(node);
      }
      else if (type == EXPR_STMT) {
        return new LinhScriptExprStmtImpl(node);
      }
      else if (type == PRINT_STMT) {
        return new LinhScriptPrintStmtImpl(node);
      }
      else if (type == STMT) {
        return new LinhScriptStmtImpl(node);
      }
      else if (type == VAR_ASSIGNMENT) {
        return new LinhScriptVarAssignmentImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
