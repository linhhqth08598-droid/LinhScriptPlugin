// This is a generated file. Not intended for manual editing.
package me.hql.linhscript.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static me.hql.linhscript.psi.LinhScriptTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import me.hql.linhscript.psi.*;

public class LinhScriptStmtImpl extends ASTWrapperPsiElement implements LinhScriptStmt {

  public LinhScriptStmtImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LinhScriptVisitor visitor) {
    visitor.visitStmt(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LinhScriptVisitor) accept((LinhScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public LinhScriptBreakStmt getBreakStmt() {
    return findChildByClass(LinhScriptBreakStmt.class);
  }

  @Override
  @Nullable
  public LinhScriptClassDefStmt getClassDefStmt() {
    return findChildByClass(LinhScriptClassDefStmt.class);
  }

  @Override
  @Nullable
  public LinhScriptContinueStmt getContinueStmt() {
    return findChildByClass(LinhScriptContinueStmt.class);
  }

  @Override
  @Nullable
  public LinhScriptExprStmt getExprStmt() {
    return findChildByClass(LinhScriptExprStmt.class);
  }

  @Override
  @Nullable
  public LinhScriptForStmt getForStmt() {
    return findChildByClass(LinhScriptForStmt.class);
  }

  @Override
  @Nullable
  public LinhScriptFuncDefStmt getFuncDefStmt() {
    return findChildByClass(LinhScriptFuncDefStmt.class);
  }

  @Override
  @Nullable
  public LinhScriptIfStmt getIfStmt() {
    return findChildByClass(LinhScriptIfStmt.class);
  }

  @Override
  @Nullable
  public LinhScriptImportStmt getImportStmt() {
    return findChildByClass(LinhScriptImportStmt.class);
  }

  @Override
  @Nullable
  public LinhScriptPostfixStmt getPostfixStmt() {
    return findChildByClass(LinhScriptPostfixStmt.class);
  }

  @Override
  @Nullable
  public LinhScriptPrintStmt getPrintStmt() {
    return findChildByClass(LinhScriptPrintStmt.class);
  }

  @Override
  @Nullable
  public LinhScriptReturnStmt getReturnStmt() {
    return findChildByClass(LinhScriptReturnStmt.class);
  }

  @Override
  @Nullable
  public LinhScriptSwitchStmt getSwitchStmt() {
    return findChildByClass(LinhScriptSwitchStmt.class);
  }

  @Override
  @Nullable
  public LinhScriptThrowStmt getThrowStmt() {
    return findChildByClass(LinhScriptThrowStmt.class);
  }

  @Override
  @Nullable
  public LinhScriptTryStmt getTryStmt() {
    return findChildByClass(LinhScriptTryStmt.class);
  }

  @Override
  @Nullable
  public LinhScriptUseStmt getUseStmt() {
    return findChildByClass(LinhScriptUseStmt.class);
  }

  @Override
  @Nullable
  public LinhScriptVarAssignmentStmt getVarAssignmentStmt() {
    return findChildByClass(LinhScriptVarAssignmentStmt.class);
  }

  @Override
  @Nullable
  public LinhScriptWhileStmt getWhileStmt() {
    return findChildByClass(LinhScriptWhileStmt.class);
  }

}
