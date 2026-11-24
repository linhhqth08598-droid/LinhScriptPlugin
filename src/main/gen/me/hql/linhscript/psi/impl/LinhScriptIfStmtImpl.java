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

public class LinhScriptIfStmtImpl extends ASTWrapperPsiElement implements LinhScriptIfStmt {

  public LinhScriptIfStmtImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LinhScriptVisitor visitor) {
    visitor.visitIfStmt(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LinhScriptVisitor) accept((LinhScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<LinhScriptBlock> getBlockList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, LinhScriptBlock.class);
  }

  @Override
  @Nullable
  public LinhScriptExpr getExpr() {
    return findChildByClass(LinhScriptExpr.class);
  }

  @Override
  @Nullable
  public LinhScriptIfStmt getIfStmt() {
    return findChildByClass(LinhScriptIfStmt.class);
  }

}
