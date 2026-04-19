// This is a generated file. Not intended for manual editing.
package me.hql.linhscript.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LinhScriptStmt extends PsiElement {

  @Nullable
  LinhScriptExprStmt getExprStmt();

  @Nullable
  LinhScriptPrintStmt getPrintStmt();

  @Nullable
  LinhScriptVarAssignment getVarAssignment();

}
