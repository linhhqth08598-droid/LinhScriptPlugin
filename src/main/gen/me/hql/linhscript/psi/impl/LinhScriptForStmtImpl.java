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

public class LinhScriptForStmtImpl extends ASTWrapperPsiElement implements LinhScriptForStmt {

  public LinhScriptForStmtImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LinhScriptVisitor visitor) {
    visitor.visitForStmt(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LinhScriptVisitor) accept((LinhScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public LinhScriptBlock getBlock() {
    return findNotNullChildByClass(LinhScriptBlock.class);
  }

  @Override
  @NotNull
  public LinhScriptRangeExpr getRangeExpr() {
    return findNotNullChildByClass(LinhScriptRangeExpr.class);
  }

  @Override
  @NotNull
  public PsiElement getIdentifier() {
    return findNotNullChildByType(IDENTIFIER);
  }

}
