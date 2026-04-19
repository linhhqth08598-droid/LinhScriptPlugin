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

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return linhScriptFile(b, l + 1);
  }

  /* ********************************************************** */
  // expr (OP_PLUS | OP_MINUS | OP_MUL | OP_DIV) expr
  public static boolean binary_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "binary_expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BINARY_EXPR, "<binary expr>");
    r = expr(b, l + 1);
    r = r && binary_expr_1(b, l + 1);
    r = r && expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // OP_PLUS | OP_MINUS | OP_MUL | OP_DIV
  private static boolean binary_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "binary_expr_1")) return false;
    boolean r;
    r = consumeToken(b, OP_PLUS);
    if (!r) r = consumeToken(b, OP_MINUS);
    if (!r) r = consumeToken(b, OP_MUL);
    if (!r) r = consumeToken(b, OP_DIV);
    return r;
  }

  /* ********************************************************** */
  // NUMBER | STRING | IDENTIFIER | TRUE | FALSE | NULL | binary_expr
  public static boolean expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPR, "<expr>");
    r = consumeToken(b, NUMBER);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, TRUE);
    if (!r) r = consumeToken(b, FALSE);
    if (!r) r = consumeToken(b, NULL);
    if (!r) r = binary_expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // expr
  public static boolean expr_stmt(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_stmt")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPR_STMT, "<expr stmt>");
    r = expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // stmt | COMMENT | CRLF
  static boolean item_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_")) return false;
    boolean r;
    r = stmt(b, l + 1);
    if (!r) r = consumeToken(b, COMMENT);
    if (!r) r = consumeToken(b, CRLF);
    return r;
  }

  /* ********************************************************** */
  // item_*
  static boolean linhScriptFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "linhScriptFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!item_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "linhScriptFile", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // PRINT L_PAREN expr R_PAREN
  public static boolean print_stmt(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "print_stmt")) return false;
    if (!nextTokenIs(b, PRINT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PRINT, L_PAREN);
    r = r && expr(b, l + 1);
    r = r && consumeToken(b, R_PAREN);
    exit_section_(b, m, PRINT_STMT, r);
    return r;
  }

  /* ********************************************************** */
  // var_assignment | print_stmt | expr_stmt
  public static boolean stmt(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "stmt")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STMT, "<stmt>");
    r = var_assignment(b, l + 1);
    if (!r) r = print_stmt(b, l + 1);
    if (!r) r = expr_stmt(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER OP_ASSIGN expr
  public static boolean var_assignment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "var_assignment")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, OP_ASSIGN);
    r = r && expr(b, l + 1);
    exit_section_(b, m, VAR_ASSIGNMENT, r);
    return r;
  }

}
