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

public class LinhScriptPostfixExprImpl extends ASTWrapperPsiElement implements LinhScriptPostfixExpr {

  public LinhScriptPostfixExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LinhScriptVisitor visitor) {
    visitor.visitPostfixExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LinhScriptVisitor) accept((LinhScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<LinhScriptArgList> getArgListList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LinhScriptArgList.class);
  }

  @Override
  @NotNull
  public List<LinhScriptExpr> getExprList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LinhScriptExpr.class);
  }

  @Override
  @NotNull
  public List<LinhScriptMemberName> getMemberNameList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LinhScriptMemberName.class);
  }

  @Override
  @NotNull
  public LinhScriptPrimaryExpr getPrimaryExpr() {
    return findNotNullChildByClass(LinhScriptPrimaryExpr.class);
  }

}
