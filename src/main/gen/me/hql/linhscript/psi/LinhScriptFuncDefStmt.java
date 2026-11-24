// This is a generated file. Not intended for manual editing.
package me.hql.linhscript.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LinhScriptFuncDefStmt extends PsiElement {

  @NotNull
  LinhScriptBlock getBlock();

  @Nullable
  LinhScriptParamList getParamList();

  @NotNull
  PsiElement getIdentifier();

  //WARNING: getNameIdentifier(...) is skipped
  //matching getNameIdentifier(LinhScriptFuncDefStmt, ...)
  //methods are not found in LinhScriptPsiImplUtil

}
