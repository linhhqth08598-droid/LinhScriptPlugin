// This is a generated file. Not intended for manual editing.
package me.hql.linhscript.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LinhScriptIfStmt extends PsiElement {

  @NotNull
  List<LinhScriptBlock> getBlockList();

  @Nullable
  LinhScriptExpr getExpr();

  @Nullable
  LinhScriptIfStmt getIfStmt();

}
