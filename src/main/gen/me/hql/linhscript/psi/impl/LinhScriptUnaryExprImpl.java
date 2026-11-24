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

public class LinhScriptUnaryExprImpl extends ASTWrapperPsiElement implements LinhScriptUnaryExpr {

  public LinhScriptUnaryExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LinhScriptVisitor visitor) {
    visitor.visitUnaryExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LinhScriptVisitor) accept((LinhScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public LinhScriptPostfixExpr getPostfixExpr() {
    return findChildByClass(LinhScriptPostfixExpr.class);
  }

  @Override
  @Nullable
  public LinhScriptUnaryExpr getUnaryExpr() {
    return findChildByClass(LinhScriptUnaryExpr.class);
  }

}
