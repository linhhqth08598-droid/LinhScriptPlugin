// This is a generated file. Not intended for manual editing.
package me.hql.linhscript.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import me.hql.linhscript.psi.impl.*;

public interface LinhScriptTypes {

  IElementType ADD_EXPR = new LinhScriptElementType("ADD_EXPR");
  IElementType AND_EXPR = new LinhScriptElementType("AND_EXPR");
  IElementType ARG_LIST = new LinhScriptElementType("ARG_LIST");
  IElementType ARRAY_EXPR = new LinhScriptElementType("ARRAY_EXPR");
  IElementType BLOCK = new LinhScriptElementType("BLOCK");
  IElementType BREAK_STMT = new LinhScriptElementType("BREAK_STMT");
  IElementType CALL_EXPR = new LinhScriptElementType("CALL_EXPR");
  IElementType CLASS_DEF_STMT = new LinhScriptElementType("CLASS_DEF_STMT");
  IElementType CLASS_FIELD_STMT = new LinhScriptElementType("CLASS_FIELD_STMT");
  IElementType CLASS_MEMBER = new LinhScriptElementType("CLASS_MEMBER");
  IElementType COMPARE_EXPR = new LinhScriptElementType("COMPARE_EXPR");
  IElementType CONTINUE_STMT = new LinhScriptElementType("CONTINUE_STMT");
  IElementType EXPR = new LinhScriptElementType("EXPR");
  IElementType EXPR_STMT = new LinhScriptElementType("EXPR_STMT");
  IElementType FOR_STMT = new LinhScriptElementType("FOR_STMT");
  IElementType FUNC_DEF_STMT = new LinhScriptElementType("FUNC_DEF_STMT");
  IElementType IF_STMT = new LinhScriptElementType("IF_STMT");
  IElementType IMPORT_STMT = new LinhScriptElementType("IMPORT_STMT");
  IElementType LITERAL_EXPR = new LinhScriptElementType("LITERAL_EXPR");
  IElementType MAP_ENTRY = new LinhScriptElementType("MAP_ENTRY");
  IElementType MAP_EXPR = new LinhScriptElementType("MAP_EXPR");
  IElementType MEMBER_NAME = new LinhScriptElementType("MEMBER_NAME");
  IElementType MUL_EXPR = new LinhScriptElementType("MUL_EXPR");
  IElementType NEW_EXPR = new LinhScriptElementType("NEW_EXPR");
  IElementType OR_EXPR = new LinhScriptElementType("OR_EXPR");
  IElementType PARAM_LIST = new LinhScriptElementType("PARAM_LIST");
  IElementType PAREN_EXPR = new LinhScriptElementType("PAREN_EXPR");
  IElementType POSTFIX_EXPR = new LinhScriptElementType("POSTFIX_EXPR");
  IElementType POSTFIX_STMT = new LinhScriptElementType("POSTFIX_STMT");
  IElementType POW_EXPR = new LinhScriptElementType("POW_EXPR");
  IElementType PRIMARY_EXPR = new LinhScriptElementType("PRIMARY_EXPR");
  IElementType PRINT_STMT = new LinhScriptElementType("PRINT_STMT");
  IElementType RANGE_EXPR = new LinhScriptElementType("RANGE_EXPR");
  IElementType RETURN_STMT = new LinhScriptElementType("RETURN_STMT");
  IElementType STMT = new LinhScriptElementType("STMT");
  IElementType SWITCH_CLAUSE = new LinhScriptElementType("SWITCH_CLAUSE");
  IElementType SWITCH_STMT = new LinhScriptElementType("SWITCH_STMT");
  IElementType TERNARY_EXPR = new LinhScriptElementType("TERNARY_EXPR");
  IElementType THROW_STMT = new LinhScriptElementType("THROW_STMT");
  IElementType TRY_STMT = new LinhScriptElementType("TRY_STMT");
  IElementType UNARY_EXPR = new LinhScriptElementType("UNARY_EXPR");
  IElementType USE_STMT = new LinhScriptElementType("USE_STMT");
  IElementType VARIABLE_EXPR = new LinhScriptElementType("VARIABLE_EXPR");
  IElementType VAR_ASSIGNMENT_STMT = new LinhScriptElementType("VAR_ASSIGNMENT_STMT");
  IElementType WHILE_STMT = new LinhScriptElementType("WHILE_STMT");

  IElementType ADD = new LinhScriptTokenType("add");
  IElementType BOOL = new LinhScriptTokenType("bool");
  IElementType BREAK = new LinhScriptTokenType("break");
  IElementType CALL = new LinhScriptTokenType("call");
  IElementType CASE = new LinhScriptTokenType("case");
  IElementType CATCH = new LinhScriptTokenType("catch");
  IElementType CLASS = new LinhScriptTokenType("class");
  IElementType COLON = new LinhScriptTokenType(":");
  IElementType COMMA = new LinhScriptTokenType(",");
  IElementType COMMENT = new LinhScriptTokenType("COMMENT");
  IElementType CONTINUE = new LinhScriptTokenType("continue");
  IElementType CRLF = new LinhScriptTokenType("CRLF");
  IElementType DEFAULT = new LinhScriptTokenType("default");
  IElementType DOT = new LinhScriptTokenType(".");
  IElementType ELSE = new LinhScriptTokenType("else");
  IElementType EXTENDS = new LinhScriptTokenType("extends");
  IElementType FALSE = new LinhScriptTokenType("false");
  IElementType FINALLY = new LinhScriptTokenType("finally");
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
  IElementType OP_AND = new LinhScriptTokenType("&&");
  IElementType OP_ASSIGN = new LinhScriptTokenType("=");
  IElementType OP_DEC = new LinhScriptTokenType("--");
  IElementType OP_DIV = new LinhScriptTokenType("/");
  IElementType OP_DIV_ASSIGN = new LinhScriptTokenType("/=");
  IElementType OP_EQ = new LinhScriptTokenType("==");
  IElementType OP_GE = new LinhScriptTokenType(">=");
  IElementType OP_GT = new LinhScriptTokenType(">");
  IElementType OP_INC = new LinhScriptTokenType("++");
  IElementType OP_LE = new LinhScriptTokenType("<=");
  IElementType OP_LT = new LinhScriptTokenType("<");
  IElementType OP_MINUS = new LinhScriptTokenType("-");
  IElementType OP_MINUS_ASSIGN = new LinhScriptTokenType("-=");
  IElementType OP_MOD = new LinhScriptTokenType("%");
  IElementType OP_MUL = new LinhScriptTokenType("*");
  IElementType OP_MUL_ASSIGN = new LinhScriptTokenType("*=");
  IElementType OP_NE = new LinhScriptTokenType("!=");
  IElementType OP_NOT = new LinhScriptTokenType("!");
  IElementType OP_OR = new LinhScriptTokenType("||");
  IElementType OP_PLUS = new LinhScriptTokenType("+");
  IElementType OP_PLUS_ASSIGN = new LinhScriptTokenType("+=");
  IElementType OP_POW = new LinhScriptTokenType("^");
  IElementType OP_RANGE = new LinhScriptTokenType("OP_RANGE");
  IElementType POP = new LinhScriptTokenType("pop");
  IElementType PRINT = new LinhScriptTokenType("print");
  IElementType PUSH = new LinhScriptTokenType("push");
  IElementType QUESTION = new LinhScriptTokenType("?");
  IElementType RANDOM = new LinhScriptTokenType("random");
  IElementType READ = new LinhScriptTokenType("read");
  IElementType RETURN = new LinhScriptTokenType("return");
  IElementType R_BRACE = new LinhScriptTokenType("}");
  IElementType R_BRACKET = new LinhScriptTokenType("]");
  IElementType R_PAREN = new LinhScriptTokenType(")");
  IElementType SEMI = new LinhScriptTokenType(";");
  IElementType SIZE = new LinhScriptTokenType("size");
  IElementType STR = new LinhScriptTokenType("str");
  IElementType STRING = new LinhScriptTokenType("STRING");
  IElementType SWITCH = new LinhScriptTokenType("switch");
  IElementType THROW = new LinhScriptTokenType("throw");
  IElementType TO = new LinhScriptTokenType("to");
  IElementType TRUE = new LinhScriptTokenType("true");
  IElementType TRY = new LinhScriptTokenType("try");
  IElementType TYPE = new LinhScriptTokenType("type");
  IElementType USE = new LinhScriptTokenType("use");
  IElementType VAR = new LinhScriptTokenType("var");
  IElementType WHILE = new LinhScriptTokenType("while");
  IElementType WRITE = new LinhScriptTokenType("write");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ADD_EXPR) {
        return new LinhScriptAddExprImpl(node);
      }
      else if (type == AND_EXPR) {
        return new LinhScriptAndExprImpl(node);
      }
      else if (type == ARG_LIST) {
        return new LinhScriptArgListImpl(node);
      }
      else if (type == ARRAY_EXPR) {
        return new LinhScriptArrayExprImpl(node);
      }
      else if (type == BLOCK) {
        return new LinhScriptBlockImpl(node);
      }
      else if (type == BREAK_STMT) {
        return new LinhScriptBreakStmtImpl(node);
      }
      else if (type == CALL_EXPR) {
        return new LinhScriptCallExprImpl(node);
      }
      else if (type == CLASS_DEF_STMT) {
        return new LinhScriptClassDefStmtImpl(node);
      }
      else if (type == CLASS_FIELD_STMT) {
        return new LinhScriptClassFieldStmtImpl(node);
      }
      else if (type == CLASS_MEMBER) {
        return new LinhScriptClassMemberImpl(node);
      }
      else if (type == COMPARE_EXPR) {
        return new LinhScriptCompareExprImpl(node);
      }
      else if (type == CONTINUE_STMT) {
        return new LinhScriptContinueStmtImpl(node);
      }
      else if (type == EXPR) {
        return new LinhScriptExprImpl(node);
      }
      else if (type == EXPR_STMT) {
        return new LinhScriptExprStmtImpl(node);
      }
      else if (type == FOR_STMT) {
        return new LinhScriptForStmtImpl(node);
      }
      else if (type == FUNC_DEF_STMT) {
        return new LinhScriptFuncDefStmtImpl(node);
      }
      else if (type == IF_STMT) {
        return new LinhScriptIfStmtImpl(node);
      }
      else if (type == IMPORT_STMT) {
        return new LinhScriptImportStmtImpl(node);
      }
      else if (type == LITERAL_EXPR) {
        return new LinhScriptLiteralExprImpl(node);
      }
      else if (type == MAP_ENTRY) {
        return new LinhScriptMapEntryImpl(node);
      }
      else if (type == MAP_EXPR) {
        return new LinhScriptMapExprImpl(node);
      }
      else if (type == MEMBER_NAME) {
        return new LinhScriptMemberNameImpl(node);
      }
      else if (type == MUL_EXPR) {
        return new LinhScriptMulExprImpl(node);
      }
      else if (type == NEW_EXPR) {
        return new LinhScriptNewExprImpl(node);
      }
      else if (type == OR_EXPR) {
        return new LinhScriptOrExprImpl(node);
      }
      else if (type == PARAM_LIST) {
        return new LinhScriptParamListImpl(node);
      }
      else if (type == PAREN_EXPR) {
        return new LinhScriptParenExprImpl(node);
      }
      else if (type == POSTFIX_EXPR) {
        return new LinhScriptPostfixExprImpl(node);
      }
      else if (type == POSTFIX_STMT) {
        return new LinhScriptPostfixStmtImpl(node);
      }
      else if (type == POW_EXPR) {
        return new LinhScriptPowExprImpl(node);
      }
      else if (type == PRIMARY_EXPR) {
        return new LinhScriptPrimaryExprImpl(node);
      }
      else if (type == PRINT_STMT) {
        return new LinhScriptPrintStmtImpl(node);
      }
      else if (type == RANGE_EXPR) {
        return new LinhScriptRangeExprImpl(node);
      }
      else if (type == RETURN_STMT) {
        return new LinhScriptReturnStmtImpl(node);
      }
      else if (type == STMT) {
        return new LinhScriptStmtImpl(node);
      }
      else if (type == SWITCH_CLAUSE) {
        return new LinhScriptSwitchClauseImpl(node);
      }
      else if (type == SWITCH_STMT) {
        return new LinhScriptSwitchStmtImpl(node);
      }
      else if (type == TERNARY_EXPR) {
        return new LinhScriptTernaryExprImpl(node);
      }
      else if (type == THROW_STMT) {
        return new LinhScriptThrowStmtImpl(node);
      }
      else if (type == TRY_STMT) {
        return new LinhScriptTryStmtImpl(node);
      }
      else if (type == UNARY_EXPR) {
        return new LinhScriptUnaryExprImpl(node);
      }
      else if (type == USE_STMT) {
        return new LinhScriptUseStmtImpl(node);
      }
      else if (type == VARIABLE_EXPR) {
        return new LinhScriptVariableExprImpl(node);
      }
      else if (type == VAR_ASSIGNMENT_STMT) {
        return new LinhScriptVarAssignmentStmtImpl(node);
      }
      else if (type == WHILE_STMT) {
        return new LinhScriptWhileStmtImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
