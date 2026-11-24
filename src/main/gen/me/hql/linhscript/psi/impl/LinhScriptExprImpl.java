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

public class LinhScriptExprImpl extends ASTWrapperPsiElement implements LinhScriptExpr {

  public LinhScriptExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LinhScriptVisitor visitor) {
    visitor.visitExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LinhScriptVisitor) accept((LinhScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public LinhScriptAddExpr getAddExpr() {
    return findChildByClass(LinhScriptAddExpr.class);
  }

  @Override
  @Nullable
  public LinhScriptAndExpr getAndExpr() {
    return findChildByClass(LinhScriptAndExpr.class);
  }

  @Override
  @Nullable
  public LinhScriptArrayExpr getArrayExpr() {
    return findChildByClass(LinhScriptArrayExpr.class);
  }

  @Override
  @Nullable
  public LinhScriptCompareExpr getCompareExpr() {
    return findChildByClass(LinhScriptCompareExpr.class);
  }

  @Override
  @Nullable
  public LinhScriptLiteralExpr getLiteralExpr() {
    return findChildByClass(LinhScriptLiteralExpr.class);
  }

  @Override
  @Nullable
  public LinhScriptMapExpr getMapExpr() {
    return findChildByClass(LinhScriptMapExpr.class);
  }

  @Override
  @Nullable
  public LinhScriptMulExpr getMulExpr() {
    return findChildByClass(LinhScriptMulExpr.class);
  }

  @Override
  @Nullable
  public LinhScriptOrExpr getOrExpr() {
    return findChildByClass(LinhScriptOrExpr.class);
  }

  @Override
  @Nullable
  public LinhScriptParenExpr getParenExpr() {
    return findChildByClass(LinhScriptParenExpr.class);
  }

  @Override
  @Nullable
  public LinhScriptPostfixExpr getPostfixExpr() {
    return findChildByClass(LinhScriptPostfixExpr.class);
  }

  @Override
  @Nullable
  public LinhScriptPowExpr getPowExpr() {
    return findChildByClass(LinhScriptPowExpr.class);
  }

  @Override
  @Nullable
  public LinhScriptTernaryExpr getTernaryExpr() {
    return findChildByClass(LinhScriptTernaryExpr.class);
  }

  @Override
  @Nullable
  public LinhScriptUnaryExpr getUnaryExpr() {
    return findChildByClass(LinhScriptUnaryExpr.class);
  }

}
