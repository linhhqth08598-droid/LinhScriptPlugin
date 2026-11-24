// This is a generated file. Not intended for manual editing.
package me.hql.linhscript.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static me.hql.linhscript.psi.LinhScriptTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class LinhScriptParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    parseLight(root_, builder_);
    return builder_.getTreeBuilt();
  }

  public void parseLight(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, null);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    result_ = parse_root_(root_, builder_);
    exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType root_, PsiBuilder builder_) {
    return parse_root_(root_, builder_, 0);
  }

  static boolean parse_root_(IElementType root_, PsiBuilder builder_, int level_) {
    return linhScriptFile(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // mul_expr ((OP_PLUS | OP_MINUS) mul_expr)*
  public static boolean add_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "add_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ADD_EXPR, "<add expr>");
    result_ = mul_expr(builder_, level_ + 1);
    result_ = result_ && add_expr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ((OP_PLUS | OP_MINUS) mul_expr)*
  private static boolean add_expr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "add_expr_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!add_expr_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "add_expr_1", pos_)) break;
    }
    return true;
  }

  // (OP_PLUS | OP_MINUS) mul_expr
  private static boolean add_expr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "add_expr_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = add_expr_1_0_0(builder_, level_ + 1);
    result_ = result_ && mul_expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // OP_PLUS | OP_MINUS
  private static boolean add_expr_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "add_expr_1_0_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, OP_PLUS);
    if (!result_) result_ = consumeToken(builder_, OP_MINUS);
    return result_;
  }

  /* ********************************************************** */
  // compare_expr (OP_AND compare_expr)*
  public static boolean and_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "and_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, AND_EXPR, "<and expr>");
    result_ = compare_expr(builder_, level_ + 1);
    result_ = result_ && and_expr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (OP_AND compare_expr)*
  private static boolean and_expr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "and_expr_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!and_expr_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "and_expr_1", pos_)) break;
    }
    return true;
  }

  // OP_AND compare_expr
  private static boolean and_expr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "and_expr_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OP_AND);
    result_ = result_ && compare_expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // expr (COMMA expr)*
  public static boolean arg_list(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_list")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ARG_LIST, "<arg list>");
    result_ = expr(builder_, level_ + 1);
    result_ = result_ && arg_list_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (COMMA expr)*
  private static boolean arg_list_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_list_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!arg_list_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "arg_list_1", pos_)) break;
    }
    return true;
  }

  // COMMA expr
  private static boolean arg_list_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_list_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // L_BRACKET (CRLF* expr (CRLF* COMMA CRLF* expr)* (CRLF* COMMA)? CRLF*)? R_BRACKET
  public static boolean array_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "array_expr")) return false;
    if (!nextTokenIs(builder_, L_BRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_BRACKET);
    result_ = result_ && array_expr_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_BRACKET);
    exit_section_(builder_, marker_, ARRAY_EXPR, result_);
    return result_;
  }

  // (CRLF* expr (CRLF* COMMA CRLF* expr)* (CRLF* COMMA)? CRLF*)?
  private static boolean array_expr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "array_expr_1")) return false;
    array_expr_1_0(builder_, level_ + 1);
    return true;
  }

  // CRLF* expr (CRLF* COMMA CRLF* expr)* (CRLF* COMMA)? CRLF*
  private static boolean array_expr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "array_expr_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = array_expr_1_0_0(builder_, level_ + 1);
    result_ = result_ && expr(builder_, level_ + 1);
    result_ = result_ && array_expr_1_0_2(builder_, level_ + 1);
    result_ = result_ && array_expr_1_0_3(builder_, level_ + 1);
    result_ = result_ && array_expr_1_0_4(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // CRLF*
  private static boolean array_expr_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "array_expr_1_0_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, CRLF)) break;
      if (!empty_element_parsed_guard_(builder_, "array_expr_1_0_0", pos_)) break;
    }
    return true;
  }

  // (CRLF* COMMA CRLF* expr)*
  private static boolean array_expr_1_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "array_expr_1_0_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!array_expr_1_0_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "array_expr_1_0_2", pos_)) break;
    }
    return true;
  }

  // CRLF* COMMA CRLF* expr
  private static boolean array_expr_1_0_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "array_expr_1_0_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = array_expr_1_0_2_0_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COMMA);
    result_ = result_ && array_expr_1_0_2_0_2(builder_, level_ + 1);
    result_ = result_ && expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // CRLF*
  private static boolean array_expr_1_0_2_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "array_expr_1_0_2_0_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, CRLF)) break;
      if (!empty_element_parsed_guard_(builder_, "array_expr_1_0_2_0_0", pos_)) break;
    }
    return true;
  }

  // CRLF*
  private static boolean array_expr_1_0_2_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "array_expr_1_0_2_0_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, CRLF)) break;
      if (!empty_element_parsed_guard_(builder_, "array_expr_1_0_2_0_2", pos_)) break;
    }
    return true;
  }

  // (CRLF* COMMA)?
  private static boolean array_expr_1_0_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "array_expr_1_0_3")) return false;
    array_expr_1_0_3_0(builder_, level_ + 1);
    return true;
  }

  // CRLF* COMMA
  private static boolean array_expr_1_0_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "array_expr_1_0_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = array_expr_1_0_3_0_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COMMA);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // CRLF*
  private static boolean array_expr_1_0_3_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "array_expr_1_0_3_0_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, CRLF)) break;
      if (!empty_element_parsed_guard_(builder_, "array_expr_1_0_3_0_0", pos_)) break;
    }
    return true;
  }

  // CRLF*
  private static boolean array_expr_1_0_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "array_expr_1_0_4")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, CRLF)) break;
      if (!empty_element_parsed_guard_(builder_, "array_expr_1_0_4", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // L_BRACE item_* R_BRACE
  public static boolean block(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "block")) return false;
    if (!nextTokenIs(builder_, L_BRACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_BRACE);
    result_ = result_ && block_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_BRACE);
    exit_section_(builder_, marker_, BLOCK, result_);
    return result_;
  }

  // item_*
  private static boolean block_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "block_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!item_(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "block_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // BREAK
  public static boolean break_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "break_stmt")) return false;
    if (!nextTokenIs(builder_, BREAK)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BREAK);
    exit_section_(builder_, marker_, BREAK_STMT, result_);
    return result_;
  }

  /* ********************************************************** */
  // CALL (IDENTIFIER | INPUT | PRINT | LEN | SIZE | TYPE | STR | NUM | BOOL | RANDOM | ADD | PUSH | POP | READ | WRITE) L_PAREN arg_list? R_PAREN
  //   | (IDENTIFIER | CALL | INPUT | PRINT | LEN | SIZE | TYPE | STR | NUM | BOOL | RANDOM | ADD | PUSH | POP | READ | WRITE) L_PAREN arg_list? R_PAREN
  public static boolean call_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "call_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CALL_EXPR, "<call expr>");
    result_ = call_expr_0(builder_, level_ + 1);
    if (!result_) result_ = call_expr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // CALL (IDENTIFIER | INPUT | PRINT | LEN | SIZE | TYPE | STR | NUM | BOOL | RANDOM | ADD | PUSH | POP | READ | WRITE) L_PAREN arg_list? R_PAREN
  private static boolean call_expr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "call_expr_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CALL);
    result_ = result_ && call_expr_0_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, L_PAREN);
    result_ = result_ && call_expr_0_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER | INPUT | PRINT | LEN | SIZE | TYPE | STR | NUM | BOOL | RANDOM | ADD | PUSH | POP | READ | WRITE
  private static boolean call_expr_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "call_expr_0_1")) return false;
    boolean result_;
    result_ = consumeToken(builder_, IDENTIFIER);
    if (!result_) result_ = consumeToken(builder_, INPUT);
    if (!result_) result_ = consumeToken(builder_, PRINT);
    if (!result_) result_ = consumeToken(builder_, LEN);
    if (!result_) result_ = consumeToken(builder_, SIZE);
    if (!result_) result_ = consumeToken(builder_, TYPE);
    if (!result_) result_ = consumeToken(builder_, STR);
    if (!result_) result_ = consumeToken(builder_, NUM);
    if (!result_) result_ = consumeToken(builder_, BOOL);
    if (!result_) result_ = consumeToken(builder_, RANDOM);
    if (!result_) result_ = consumeToken(builder_, ADD);
    if (!result_) result_ = consumeToken(builder_, PUSH);
    if (!result_) result_ = consumeToken(builder_, POP);
    if (!result_) result_ = consumeToken(builder_, READ);
    if (!result_) result_ = consumeToken(builder_, WRITE);
    return result_;
  }

  // arg_list?
  private static boolean call_expr_0_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "call_expr_0_3")) return false;
    arg_list(builder_, level_ + 1);
    return true;
  }

  // (IDENTIFIER | CALL | INPUT | PRINT | LEN | SIZE | TYPE | STR | NUM | BOOL | RANDOM | ADD | PUSH | POP | READ | WRITE) L_PAREN arg_list? R_PAREN
  private static boolean call_expr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "call_expr_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = call_expr_1_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, L_PAREN);
    result_ = result_ && call_expr_1_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // IDENTIFIER | CALL | INPUT | PRINT | LEN | SIZE | TYPE | STR | NUM | BOOL | RANDOM | ADD | PUSH | POP | READ | WRITE
  private static boolean call_expr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "call_expr_1_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, IDENTIFIER);
    if (!result_) result_ = consumeToken(builder_, CALL);
    if (!result_) result_ = consumeToken(builder_, INPUT);
    if (!result_) result_ = consumeToken(builder_, PRINT);
    if (!result_) result_ = consumeToken(builder_, LEN);
    if (!result_) result_ = consumeToken(builder_, SIZE);
    if (!result_) result_ = consumeToken(builder_, TYPE);
    if (!result_) result_ = consumeToken(builder_, STR);
    if (!result_) result_ = consumeToken(builder_, NUM);
    if (!result_) result_ = consumeToken(builder_, BOOL);
    if (!result_) result_ = consumeToken(builder_, RANDOM);
    if (!result_) result_ = consumeToken(builder_, ADD);
    if (!result_) result_ = consumeToken(builder_, PUSH);
    if (!result_) result_ = consumeToken(builder_, POP);
    if (!result_) result_ = consumeToken(builder_, READ);
    if (!result_) result_ = consumeToken(builder_, WRITE);
    return result_;
  }

  // arg_list?
  private static boolean call_expr_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "call_expr_1_2")) return false;
    arg_list(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // CLASS IDENTIFIER (EXTENDS IDENTIFIER)? L_BRACE class_item_* R_BRACE
  public static boolean class_def_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "class_def_stmt")) return false;
    if (!nextTokenIs(builder_, CLASS)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, CLASS, IDENTIFIER);
    result_ = result_ && class_def_stmt_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, L_BRACE);
    result_ = result_ && class_def_stmt_4(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_BRACE);
    exit_section_(builder_, marker_, CLASS_DEF_STMT, result_);
    return result_;
  }

  // (EXTENDS IDENTIFIER)?
  private static boolean class_def_stmt_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "class_def_stmt_2")) return false;
    class_def_stmt_2_0(builder_, level_ + 1);
    return true;
  }

  // EXTENDS IDENTIFIER
  private static boolean class_def_stmt_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "class_def_stmt_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, EXTENDS, IDENTIFIER);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // class_item_*
  private static boolean class_def_stmt_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "class_def_stmt_4")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!class_item_(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "class_def_stmt_4", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // VAR IDENTIFIER (OP_ASSIGN expr)?
  public static boolean class_field_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "class_field_stmt")) return false;
    if (!nextTokenIs(builder_, VAR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, VAR, IDENTIFIER);
    result_ = result_ && class_field_stmt_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, CLASS_FIELD_STMT, result_);
    return result_;
  }

  // (OP_ASSIGN expr)?
  private static boolean class_field_stmt_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "class_field_stmt_2")) return false;
    class_field_stmt_2_0(builder_, level_ + 1);
    return true;
  }

  // OP_ASSIGN expr
  private static boolean class_field_stmt_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "class_field_stmt_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OP_ASSIGN);
    result_ = result_ && expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // class_member SEMI? | COMMENT | CRLF | SEMI
  static boolean class_item_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "class_item_")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = class_item__0(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, COMMENT);
    if (!result_) result_ = consumeToken(builder_, CRLF);
    if (!result_) result_ = consumeToken(builder_, SEMI);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // class_member SEMI?
  private static boolean class_item__0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "class_item__0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = class_member(builder_, level_ + 1);
    result_ = result_ && class_item__0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // SEMI?
  private static boolean class_item__0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "class_item__0_1")) return false;
    consumeToken(builder_, SEMI);
    return true;
  }

  /* ********************************************************** */
  // func_def_stmt | class_field_stmt
  public static boolean class_member(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "class_member")) return false;
    if (!nextTokenIs(builder_, "<class member>", FUNC, VAR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CLASS_MEMBER, "<class member>");
    result_ = func_def_stmt(builder_, level_ + 1);
    if (!result_) result_ = class_field_stmt(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // add_expr ((OP_EQ | OP_NE | OP_GT | OP_LT | OP_GE | OP_LE) add_expr)*
  public static boolean compare_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "compare_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COMPARE_EXPR, "<compare expr>");
    result_ = add_expr(builder_, level_ + 1);
    result_ = result_ && compare_expr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ((OP_EQ | OP_NE | OP_GT | OP_LT | OP_GE | OP_LE) add_expr)*
  private static boolean compare_expr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "compare_expr_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!compare_expr_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "compare_expr_1", pos_)) break;
    }
    return true;
  }

  // (OP_EQ | OP_NE | OP_GT | OP_LT | OP_GE | OP_LE) add_expr
  private static boolean compare_expr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "compare_expr_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = compare_expr_1_0_0(builder_, level_ + 1);
    result_ = result_ && add_expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // OP_EQ | OP_NE | OP_GT | OP_LT | OP_GE | OP_LE
  private static boolean compare_expr_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "compare_expr_1_0_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, OP_EQ);
    if (!result_) result_ = consumeToken(builder_, OP_NE);
    if (!result_) result_ = consumeToken(builder_, OP_GT);
    if (!result_) result_ = consumeToken(builder_, OP_LT);
    if (!result_) result_ = consumeToken(builder_, OP_GE);
    if (!result_) result_ = consumeToken(builder_, OP_LE);
    return result_;
  }

  /* ********************************************************** */
  // CONTINUE
  public static boolean continue_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "continue_stmt")) return false;
    if (!nextTokenIs(builder_, CONTINUE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CONTINUE);
    exit_section_(builder_, marker_, CONTINUE_STMT, result_);
    return result_;
  }

  /* ********************************************************** */
  // ternary_expr
  //   | or_expr
  //   | and_expr
  //   | compare_expr
  //   | add_expr
  //   | mul_expr
  //   | pow_expr
  //   | unary_expr
  //   | postfix_expr
  //   | map_expr
  //   | array_expr
  //   | paren_expr
  //   | literal_expr
  public static boolean expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXPR, "<expr>");
    result_ = ternary_expr(builder_, level_ + 1);
    if (!result_) result_ = or_expr(builder_, level_ + 1);
    if (!result_) result_ = and_expr(builder_, level_ + 1);
    if (!result_) result_ = compare_expr(builder_, level_ + 1);
    if (!result_) result_ = add_expr(builder_, level_ + 1);
    if (!result_) result_ = mul_expr(builder_, level_ + 1);
    if (!result_) result_ = pow_expr(builder_, level_ + 1);
    if (!result_) result_ = unary_expr(builder_, level_ + 1);
    if (!result_) result_ = postfix_expr(builder_, level_ + 1);
    if (!result_) result_ = map_expr(builder_, level_ + 1);
    if (!result_) result_ = array_expr(builder_, level_ + 1);
    if (!result_) result_ = paren_expr(builder_, level_ + 1);
    if (!result_) result_ = literal_expr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // expr
  public static boolean expr_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expr_stmt")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXPR_STMT, "<expr stmt>");
    result_ = expr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // FOR IDENTIFIER IN range_expr block
  public static boolean for_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_stmt")) return false;
    if (!nextTokenIs(builder_, FOR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, FOR, IDENTIFIER, IN);
    result_ = result_ && range_expr(builder_, level_ + 1);
    result_ = result_ && block(builder_, level_ + 1);
    exit_section_(builder_, marker_, FOR_STMT, result_);
    return result_;
  }

  /* ********************************************************** */
  // FUNC IDENTIFIER L_PAREN param_list? R_PAREN block
  public static boolean func_def_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_def_stmt")) return false;
    if (!nextTokenIs(builder_, FUNC)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, FUNC, IDENTIFIER, L_PAREN);
    result_ = result_ && func_def_stmt_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_PAREN);
    result_ = result_ && block(builder_, level_ + 1);
    exit_section_(builder_, marker_, FUNC_DEF_STMT, result_);
    return result_;
  }

  // param_list?
  private static boolean func_def_stmt_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "func_def_stmt_3")) return false;
    param_list(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // IF L_PAREN expr R_PAREN block (CRLF* ELSE (CRLF* if_stmt | CRLF* block))?
  public static boolean if_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_stmt")) return false;
    if (!nextTokenIs(builder_, IF)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, IF_STMT, null);
    result_ = consumeTokens(builder_, 1, IF, L_PAREN);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, expr(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, R_PAREN)) && result_;
    result_ = pinned_ && report_error_(builder_, block(builder_, level_ + 1)) && result_;
    result_ = pinned_ && if_stmt_5(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (CRLF* ELSE (CRLF* if_stmt | CRLF* block))?
  private static boolean if_stmt_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_stmt_5")) return false;
    if_stmt_5_0(builder_, level_ + 1);
    return true;
  }

  // CRLF* ELSE (CRLF* if_stmt | CRLF* block)
  private static boolean if_stmt_5_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_stmt_5_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = if_stmt_5_0_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ELSE);
    result_ = result_ && if_stmt_5_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // CRLF*
  private static boolean if_stmt_5_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_stmt_5_0_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, CRLF)) break;
      if (!empty_element_parsed_guard_(builder_, "if_stmt_5_0_0", pos_)) break;
    }
    return true;
  }

  // CRLF* if_stmt | CRLF* block
  private static boolean if_stmt_5_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_stmt_5_0_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = if_stmt_5_0_2_0(builder_, level_ + 1);
    if (!result_) result_ = if_stmt_5_0_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // CRLF* if_stmt
  private static boolean if_stmt_5_0_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_stmt_5_0_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = if_stmt_5_0_2_0_0(builder_, level_ + 1);
    result_ = result_ && if_stmt(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // CRLF*
  private static boolean if_stmt_5_0_2_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_stmt_5_0_2_0_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, CRLF)) break;
      if (!empty_element_parsed_guard_(builder_, "if_stmt_5_0_2_0_0", pos_)) break;
    }
    return true;
  }

  // CRLF* block
  private static boolean if_stmt_5_0_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_stmt_5_0_2_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = if_stmt_5_0_2_1_0(builder_, level_ + 1);
    result_ = result_ && block(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // CRLF*
  private static boolean if_stmt_5_0_2_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_stmt_5_0_2_1_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, CRLF)) break;
      if (!empty_element_parsed_guard_(builder_, "if_stmt_5_0_2_1_0", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // IMPORT STRING SEMI?
  public static boolean import_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "import_stmt")) return false;
    if (!nextTokenIs(builder_, IMPORT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IMPORT, STRING);
    result_ = result_ && import_stmt_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, IMPORT_STMT, result_);
    return result_;
  }

  // SEMI?
  private static boolean import_stmt_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "import_stmt_2")) return false;
    consumeToken(builder_, SEMI);
    return true;
  }

  /* ********************************************************** */
  // stmt SEMI? | COMMENT | CRLF | SEMI
  static boolean item_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = item__0(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, COMMENT);
    if (!result_) result_ = consumeToken(builder_, CRLF);
    if (!result_) result_ = consumeToken(builder_, SEMI);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // stmt SEMI?
  private static boolean item__0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item__0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = stmt(builder_, level_ + 1);
    result_ = result_ && item__0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // SEMI?
  private static boolean item__0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item__0_1")) return false;
    consumeToken(builder_, SEMI);
    return true;
  }

  /* ********************************************************** */
  // item_*
  static boolean linhScriptFile(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "linhScriptFile")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!item_(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "linhScriptFile", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // NUMBER | STRING | TRUE | FALSE | NULL
  public static boolean literal_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "literal_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LITERAL_EXPR, "<literal expr>");
    result_ = consumeToken(builder_, NUMBER);
    if (!result_) result_ = consumeToken(builder_, STRING);
    if (!result_) result_ = consumeToken(builder_, TRUE);
    if (!result_) result_ = consumeToken(builder_, FALSE);
    if (!result_) result_ = consumeToken(builder_, NULL);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // (STRING | IDENTIFIER | NUMBER | L_BRACKET expr R_BRACKET) COLON expr
  public static boolean map_entry(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_entry")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MAP_ENTRY, "<map entry>");
    result_ = map_entry_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
    result_ = result_ && expr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // STRING | IDENTIFIER | NUMBER | L_BRACKET expr R_BRACKET
  private static boolean map_entry_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_entry_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, STRING);
    if (!result_) result_ = consumeToken(builder_, IDENTIFIER);
    if (!result_) result_ = consumeToken(builder_, NUMBER);
    if (!result_) result_ = map_entry_0_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // L_BRACKET expr R_BRACKET
  private static boolean map_entry_0_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_entry_0_3")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_BRACKET);
    result_ = result_ && expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_BRACKET);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // L_BRACE (CRLF* map_entry (CRLF* COMMA CRLF* map_entry)* (CRLF* COMMA)? CRLF*)? R_BRACE
  public static boolean map_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_expr")) return false;
    if (!nextTokenIs(builder_, L_BRACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_BRACE);
    result_ = result_ && map_expr_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_BRACE);
    exit_section_(builder_, marker_, MAP_EXPR, result_);
    return result_;
  }

  // (CRLF* map_entry (CRLF* COMMA CRLF* map_entry)* (CRLF* COMMA)? CRLF*)?
  private static boolean map_expr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_expr_1")) return false;
    map_expr_1_0(builder_, level_ + 1);
    return true;
  }

  // CRLF* map_entry (CRLF* COMMA CRLF* map_entry)* (CRLF* COMMA)? CRLF*
  private static boolean map_expr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_expr_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = map_expr_1_0_0(builder_, level_ + 1);
    result_ = result_ && map_entry(builder_, level_ + 1);
    result_ = result_ && map_expr_1_0_2(builder_, level_ + 1);
    result_ = result_ && map_expr_1_0_3(builder_, level_ + 1);
    result_ = result_ && map_expr_1_0_4(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // CRLF*
  private static boolean map_expr_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_expr_1_0_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, CRLF)) break;
      if (!empty_element_parsed_guard_(builder_, "map_expr_1_0_0", pos_)) break;
    }
    return true;
  }

  // (CRLF* COMMA CRLF* map_entry)*
  private static boolean map_expr_1_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_expr_1_0_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!map_expr_1_0_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "map_expr_1_0_2", pos_)) break;
    }
    return true;
  }

  // CRLF* COMMA CRLF* map_entry
  private static boolean map_expr_1_0_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_expr_1_0_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = map_expr_1_0_2_0_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COMMA);
    result_ = result_ && map_expr_1_0_2_0_2(builder_, level_ + 1);
    result_ = result_ && map_entry(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // CRLF*
  private static boolean map_expr_1_0_2_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_expr_1_0_2_0_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, CRLF)) break;
      if (!empty_element_parsed_guard_(builder_, "map_expr_1_0_2_0_0", pos_)) break;
    }
    return true;
  }

  // CRLF*
  private static boolean map_expr_1_0_2_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_expr_1_0_2_0_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, CRLF)) break;
      if (!empty_element_parsed_guard_(builder_, "map_expr_1_0_2_0_2", pos_)) break;
    }
    return true;
  }

  // (CRLF* COMMA)?
  private static boolean map_expr_1_0_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_expr_1_0_3")) return false;
    map_expr_1_0_3_0(builder_, level_ + 1);
    return true;
  }

  // CRLF* COMMA
  private static boolean map_expr_1_0_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_expr_1_0_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = map_expr_1_0_3_0_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COMMA);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // CRLF*
  private static boolean map_expr_1_0_3_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_expr_1_0_3_0_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, CRLF)) break;
      if (!empty_element_parsed_guard_(builder_, "map_expr_1_0_3_0_0", pos_)) break;
    }
    return true;
  }

  // CRLF*
  private static boolean map_expr_1_0_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_expr_1_0_4")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, CRLF)) break;
      if (!empty_element_parsed_guard_(builder_, "map_expr_1_0_4", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER | LEN | SIZE | TYPE | STR | NUM | BOOL | PRINT | INPUT | RANDOM | ADD | PUSH | POP | READ | WRITE
  public static boolean member_name(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "member_name")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MEMBER_NAME, "<member name>");
    result_ = consumeToken(builder_, IDENTIFIER);
    if (!result_) result_ = consumeToken(builder_, LEN);
    if (!result_) result_ = consumeToken(builder_, SIZE);
    if (!result_) result_ = consumeToken(builder_, TYPE);
    if (!result_) result_ = consumeToken(builder_, STR);
    if (!result_) result_ = consumeToken(builder_, NUM);
    if (!result_) result_ = consumeToken(builder_, BOOL);
    if (!result_) result_ = consumeToken(builder_, PRINT);
    if (!result_) result_ = consumeToken(builder_, INPUT);
    if (!result_) result_ = consumeToken(builder_, RANDOM);
    if (!result_) result_ = consumeToken(builder_, ADD);
    if (!result_) result_ = consumeToken(builder_, PUSH);
    if (!result_) result_ = consumeToken(builder_, POP);
    if (!result_) result_ = consumeToken(builder_, READ);
    if (!result_) result_ = consumeToken(builder_, WRITE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // pow_expr ((OP_MUL | OP_DIV | OP_MOD) pow_expr)*
  public static boolean mul_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mul_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MUL_EXPR, "<mul expr>");
    result_ = pow_expr(builder_, level_ + 1);
    result_ = result_ && mul_expr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ((OP_MUL | OP_DIV | OP_MOD) pow_expr)*
  private static boolean mul_expr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mul_expr_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!mul_expr_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "mul_expr_1", pos_)) break;
    }
    return true;
  }

  // (OP_MUL | OP_DIV | OP_MOD) pow_expr
  private static boolean mul_expr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mul_expr_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = mul_expr_1_0_0(builder_, level_ + 1);
    result_ = result_ && pow_expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // OP_MUL | OP_DIV | OP_MOD
  private static boolean mul_expr_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mul_expr_1_0_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, OP_MUL);
    if (!result_) result_ = consumeToken(builder_, OP_DIV);
    if (!result_) result_ = consumeToken(builder_, OP_MOD);
    return result_;
  }

  /* ********************************************************** */
  // NEW IDENTIFIER (L_PAREN arg_list? R_PAREN)?
  public static boolean new_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "new_expr")) return false;
    if (!nextTokenIs(builder_, NEW)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, NEW, IDENTIFIER);
    result_ = result_ && new_expr_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, NEW_EXPR, result_);
    return result_;
  }

  // (L_PAREN arg_list? R_PAREN)?
  private static boolean new_expr_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "new_expr_2")) return false;
    new_expr_2_0(builder_, level_ + 1);
    return true;
  }

  // L_PAREN arg_list? R_PAREN
  private static boolean new_expr_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "new_expr_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_PAREN);
    result_ = result_ && new_expr_2_0_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // arg_list?
  private static boolean new_expr_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "new_expr_2_0_1")) return false;
    arg_list(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // and_expr (OP_OR and_expr)*
  public static boolean or_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "or_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, OR_EXPR, "<or expr>");
    result_ = and_expr(builder_, level_ + 1);
    result_ = result_ && or_expr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (OP_OR and_expr)*
  private static boolean or_expr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "or_expr_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!or_expr_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "or_expr_1", pos_)) break;
    }
    return true;
  }

  // OP_OR and_expr
  private static boolean or_expr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "or_expr_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OP_OR);
    result_ = result_ && and_expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER (COMMA IDENTIFIER)*
  public static boolean param_list(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "param_list")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    result_ = result_ && param_list_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, PARAM_LIST, result_);
    return result_;
  }

  // (COMMA IDENTIFIER)*
  private static boolean param_list_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "param_list_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!param_list_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "param_list_1", pos_)) break;
    }
    return true;
  }

  // COMMA IDENTIFIER
  private static boolean param_list_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "param_list_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, COMMA, IDENTIFIER);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // L_PAREN expr R_PAREN
  public static boolean paren_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "paren_expr")) return false;
    if (!nextTokenIs(builder_, L_PAREN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_PAREN);
    result_ = result_ && expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_PAREN);
    exit_section_(builder_, marker_, PAREN_EXPR, result_);
    return result_;
  }

  /* ********************************************************** */
  // primary_expr (DOT member_name | L_BRACKET expr R_BRACKET | L_PAREN arg_list? R_PAREN)*
  public static boolean postfix_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postfix_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, POSTFIX_EXPR, "<postfix expr>");
    result_ = primary_expr(builder_, level_ + 1);
    result_ = result_ && postfix_expr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (DOT member_name | L_BRACKET expr R_BRACKET | L_PAREN arg_list? R_PAREN)*
  private static boolean postfix_expr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postfix_expr_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!postfix_expr_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "postfix_expr_1", pos_)) break;
    }
    return true;
  }

  // DOT member_name | L_BRACKET expr R_BRACKET | L_PAREN arg_list? R_PAREN
  private static boolean postfix_expr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postfix_expr_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = postfix_expr_1_0_0(builder_, level_ + 1);
    if (!result_) result_ = postfix_expr_1_0_1(builder_, level_ + 1);
    if (!result_) result_ = postfix_expr_1_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // DOT member_name
  private static boolean postfix_expr_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postfix_expr_1_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOT);
    result_ = result_ && member_name(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // L_BRACKET expr R_BRACKET
  private static boolean postfix_expr_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postfix_expr_1_0_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_BRACKET);
    result_ = result_ && expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_BRACKET);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // L_PAREN arg_list? R_PAREN
  private static boolean postfix_expr_1_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postfix_expr_1_0_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_PAREN);
    result_ = result_ && postfix_expr_1_0_2_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // arg_list?
  private static boolean postfix_expr_1_0_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postfix_expr_1_0_2_1")) return false;
    arg_list(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // postfix_expr (OP_INC | OP_DEC)
  public static boolean postfix_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postfix_stmt")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, POSTFIX_STMT, "<postfix stmt>");
    result_ = postfix_expr(builder_, level_ + 1);
    result_ = result_ && postfix_stmt_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // OP_INC | OP_DEC
  private static boolean postfix_stmt_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "postfix_stmt_1")) return false;
    boolean result_;
    result_ = consumeToken(builder_, OP_INC);
    if (!result_) result_ = consumeToken(builder_, OP_DEC);
    return result_;
  }

  /* ********************************************************** */
  // unary_expr (OP_POW unary_expr)*
  public static boolean pow_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pow_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, POW_EXPR, "<pow expr>");
    result_ = unary_expr(builder_, level_ + 1);
    result_ = result_ && pow_expr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (OP_POW unary_expr)*
  private static boolean pow_expr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pow_expr_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!pow_expr_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "pow_expr_1", pos_)) break;
    }
    return true;
  }

  // OP_POW unary_expr
  private static boolean pow_expr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pow_expr_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OP_POW);
    result_ = result_ && unary_expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // new_expr | call_expr | variable_expr | literal_expr | paren_expr
  public static boolean primary_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PRIMARY_EXPR, "<primary expr>");
    result_ = new_expr(builder_, level_ + 1);
    if (!result_) result_ = call_expr(builder_, level_ + 1);
    if (!result_) result_ = variable_expr(builder_, level_ + 1);
    if (!result_) result_ = literal_expr(builder_, level_ + 1);
    if (!result_) result_ = paren_expr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // PRINT L_PAREN expr R_PAREN
  public static boolean print_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "print_stmt")) return false;
    if (!nextTokenIs(builder_, PRINT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, PRINT, L_PAREN);
    result_ = result_ && expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_PAREN);
    exit_section_(builder_, marker_, PRINT_STMT, result_);
    return result_;
  }

  /* ********************************************************** */
  // add_expr (OP_RANGE add_expr)?
  public static boolean range_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "range_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, RANGE_EXPR, "<range expr>");
    result_ = add_expr(builder_, level_ + 1);
    result_ = result_ && range_expr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (OP_RANGE add_expr)?
  private static boolean range_expr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "range_expr_1")) return false;
    range_expr_1_0(builder_, level_ + 1);
    return true;
  }

  // OP_RANGE add_expr
  private static boolean range_expr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "range_expr_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OP_RANGE);
    result_ = result_ && add_expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // RETURN expr?
  public static boolean return_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "return_stmt")) return false;
    if (!nextTokenIs(builder_, RETURN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, RETURN);
    result_ = result_ && return_stmt_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, RETURN_STMT, result_);
    return result_;
  }

  // expr?
  private static boolean return_stmt_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "return_stmt_1")) return false;
    expr(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // use_stmt
  //   | import_stmt
  //   | class_def_stmt
  //   | func_def_stmt
  //   | try_stmt
  //   | throw_stmt
  //   | postfix_stmt
  //   | var_assignment_stmt
  //   | print_stmt
  //   | return_stmt
  //   | break_stmt
  //   | continue_stmt
  //   | if_stmt
  //   | switch_stmt
  //   | while_stmt
  //   | for_stmt
  //   | expr_stmt
  public static boolean stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "stmt")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STMT, "<stmt>");
    result_ = use_stmt(builder_, level_ + 1);
    if (!result_) result_ = import_stmt(builder_, level_ + 1);
    if (!result_) result_ = class_def_stmt(builder_, level_ + 1);
    if (!result_) result_ = func_def_stmt(builder_, level_ + 1);
    if (!result_) result_ = try_stmt(builder_, level_ + 1);
    if (!result_) result_ = throw_stmt(builder_, level_ + 1);
    if (!result_) result_ = postfix_stmt(builder_, level_ + 1);
    if (!result_) result_ = var_assignment_stmt(builder_, level_ + 1);
    if (!result_) result_ = print_stmt(builder_, level_ + 1);
    if (!result_) result_ = return_stmt(builder_, level_ + 1);
    if (!result_) result_ = break_stmt(builder_, level_ + 1);
    if (!result_) result_ = continue_stmt(builder_, level_ + 1);
    if (!result_) result_ = if_stmt(builder_, level_ + 1);
    if (!result_) result_ = switch_stmt(builder_, level_ + 1);
    if (!result_) result_ = while_stmt(builder_, level_ + 1);
    if (!result_) result_ = for_stmt(builder_, level_ + 1);
    if (!result_) result_ = expr_stmt(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // (CASE expr | DEFAULT) COLON
  public static boolean switch_clause(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switch_clause")) return false;
    if (!nextTokenIs(builder_, "<switch clause>", CASE, DEFAULT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SWITCH_CLAUSE, "<switch clause>");
    result_ = switch_clause_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // CASE expr | DEFAULT
  private static boolean switch_clause_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switch_clause_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = switch_clause_0_0(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, DEFAULT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // CASE expr
  private static boolean switch_clause_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switch_clause_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CASE);
    result_ = result_ && expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // switch_clause | item_
  static boolean switch_item_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switch_item_")) return false;
    boolean result_;
    result_ = switch_clause(builder_, level_ + 1);
    if (!result_) result_ = item_(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // SWITCH L_PAREN expr R_PAREN L_BRACE switch_item_* R_BRACE
  public static boolean switch_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switch_stmt")) return false;
    if (!nextTokenIs(builder_, SWITCH)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, SWITCH, L_PAREN);
    result_ = result_ && expr(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 0, R_PAREN, L_BRACE);
    result_ = result_ && switch_stmt_5(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_BRACE);
    exit_section_(builder_, marker_, SWITCH_STMT, result_);
    return result_;
  }

  // switch_item_*
  private static boolean switch_stmt_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switch_stmt_5")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!switch_item_(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "switch_stmt_5", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // or_expr (QUESTION expr COLON expr)?
  public static boolean ternary_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ternary_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TERNARY_EXPR, "<ternary expr>");
    result_ = or_expr(builder_, level_ + 1);
    result_ = result_ && ternary_expr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (QUESTION expr COLON expr)?
  private static boolean ternary_expr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ternary_expr_1")) return false;
    ternary_expr_1_0(builder_, level_ + 1);
    return true;
  }

  // QUESTION expr COLON expr
  private static boolean ternary_expr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ternary_expr_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, QUESTION);
    result_ = result_ && expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
    result_ = result_ && expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // THROW expr
  public static boolean throw_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "throw_stmt")) return false;
    if (!nextTokenIs(builder_, THROW)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, THROW);
    result_ = result_ && expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, THROW_STMT, result_);
    return result_;
  }

  /* ********************************************************** */
  // TRY block (CATCH (L_PAREN IDENTIFIER R_PAREN)? block)? (FINALLY block)?
  public static boolean try_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "try_stmt")) return false;
    if (!nextTokenIs(builder_, TRY)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, TRY);
    result_ = result_ && block(builder_, level_ + 1);
    result_ = result_ && try_stmt_2(builder_, level_ + 1);
    result_ = result_ && try_stmt_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, TRY_STMT, result_);
    return result_;
  }

  // (CATCH (L_PAREN IDENTIFIER R_PAREN)? block)?
  private static boolean try_stmt_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "try_stmt_2")) return false;
    try_stmt_2_0(builder_, level_ + 1);
    return true;
  }

  // CATCH (L_PAREN IDENTIFIER R_PAREN)? block
  private static boolean try_stmt_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "try_stmt_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CATCH);
    result_ = result_ && try_stmt_2_0_1(builder_, level_ + 1);
    result_ = result_ && block(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (L_PAREN IDENTIFIER R_PAREN)?
  private static boolean try_stmt_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "try_stmt_2_0_1")) return false;
    try_stmt_2_0_1_0(builder_, level_ + 1);
    return true;
  }

  // L_PAREN IDENTIFIER R_PAREN
  private static boolean try_stmt_2_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "try_stmt_2_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, L_PAREN, IDENTIFIER, R_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (FINALLY block)?
  private static boolean try_stmt_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "try_stmt_3")) return false;
    try_stmt_3_0(builder_, level_ + 1);
    return true;
  }

  // FINALLY block
  private static boolean try_stmt_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "try_stmt_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, FINALLY);
    result_ = result_ && block(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // (NOT | OP_NOT | OP_MINUS | OP_PLUS) unary_expr | postfix_expr
  public static boolean unary_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "unary_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, UNARY_EXPR, "<unary expr>");
    result_ = unary_expr_0(builder_, level_ + 1);
    if (!result_) result_ = postfix_expr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (NOT | OP_NOT | OP_MINUS | OP_PLUS) unary_expr
  private static boolean unary_expr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "unary_expr_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = unary_expr_0_0(builder_, level_ + 1);
    result_ = result_ && unary_expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // NOT | OP_NOT | OP_MINUS | OP_PLUS
  private static boolean unary_expr_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "unary_expr_0_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, NOT);
    if (!result_) result_ = consumeToken(builder_, OP_NOT);
    if (!result_) result_ = consumeToken(builder_, OP_MINUS);
    if (!result_) result_ = consumeToken(builder_, OP_PLUS);
    return result_;
  }

  /* ********************************************************** */
  // USE IDENTIFIER (DOT IDENTIFIER)* SEMI?
  public static boolean use_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_stmt")) return false;
    if (!nextTokenIs(builder_, USE)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, USE_STMT, null);
    result_ = consumeTokens(builder_, 1, USE, IDENTIFIER);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, use_stmt_2(builder_, level_ + 1));
    result_ = pinned_ && use_stmt_3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (DOT IDENTIFIER)*
  private static boolean use_stmt_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_stmt_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!use_stmt_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "use_stmt_2", pos_)) break;
    }
    return true;
  }

  // DOT IDENTIFIER
  private static boolean use_stmt_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_stmt_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, DOT, IDENTIFIER);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // SEMI?
  private static boolean use_stmt_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "use_stmt_3")) return false;
    consumeToken(builder_, SEMI);
    return true;
  }

  /* ********************************************************** */
  // VAR? postfix_expr (OP_ASSIGN | OP_PLUS_ASSIGN | OP_MINUS_ASSIGN | OP_MUL_ASSIGN | OP_DIV_ASSIGN) expr
  public static boolean var_assignment_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "var_assignment_stmt")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, VAR_ASSIGNMENT_STMT, "<var assignment stmt>");
    result_ = var_assignment_stmt_0(builder_, level_ + 1);
    result_ = result_ && postfix_expr(builder_, level_ + 1);
    result_ = result_ && var_assignment_stmt_2(builder_, level_ + 1);
    result_ = result_ && expr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // VAR?
  private static boolean var_assignment_stmt_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "var_assignment_stmt_0")) return false;
    consumeToken(builder_, VAR);
    return true;
  }

  // OP_ASSIGN | OP_PLUS_ASSIGN | OP_MINUS_ASSIGN | OP_MUL_ASSIGN | OP_DIV_ASSIGN
  private static boolean var_assignment_stmt_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "var_assignment_stmt_2")) return false;
    boolean result_;
    result_ = consumeToken(builder_, OP_ASSIGN);
    if (!result_) result_ = consumeToken(builder_, OP_PLUS_ASSIGN);
    if (!result_) result_ = consumeToken(builder_, OP_MINUS_ASSIGN);
    if (!result_) result_ = consumeToken(builder_, OP_MUL_ASSIGN);
    if (!result_) result_ = consumeToken(builder_, OP_DIV_ASSIGN);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean variable_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variable_expr")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, marker_, VARIABLE_EXPR, result_);
    return result_;
  }

  /* ********************************************************** */
  // WHILE L_PAREN expr R_PAREN block
  public static boolean while_stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "while_stmt")) return false;
    if (!nextTokenIs(builder_, WHILE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, WHILE, L_PAREN);
    result_ = result_ && expr(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_PAREN);
    result_ = result_ && block(builder_, level_ + 1);
    exit_section_(builder_, marker_, WHILE_STMT, result_);
    return result_;
  }

}
