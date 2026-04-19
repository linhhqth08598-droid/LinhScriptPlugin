// This is a generated file. Not intended for manual editing.
package me.hql.linhscript.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class LinhScriptVisitor extends PsiElementVisitor {

  public void visitBinaryExpr(@NotNull LinhScriptBinaryExpr o) {
    visitPsiElement(o);
  }

  public void visitExpr(@NotNull LinhScriptExpr o) {
    visitPsiElement(o);
  }

  public void visitExprStmt(@NotNull LinhScriptExprStmt o) {
    visitPsiElement(o);
  }

  public void visitPrintStmt(@NotNull LinhScriptPrintStmt o) {
    visitPsiElement(o);
  }

  public void visitStmt(@NotNull LinhScriptStmt o) {
    visitPsiElement(o);
  }

  public void visitVarAssignment(@NotNull LinhScriptVarAssignment o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
