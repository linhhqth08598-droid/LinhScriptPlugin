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

public class LinhScriptExprStmtImpl extends ASTWrapperPsiElement implements LinhScriptExprStmt {

  public LinhScriptExprStmtImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull LinhScriptVisitor visitor) {
    visitor.visitExprStmt(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof LinhScriptVisitor) accept((LinhScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public LinhScriptExpr getExpr() {
    return findNotNullChildByClass(LinhScriptExpr.class);
  }

}
