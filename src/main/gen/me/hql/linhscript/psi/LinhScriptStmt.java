// This is a generated file. Not intended for manual editing.
package me.hql.linhscript.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LinhScriptStmt extends PsiElement {

  @Nullable
  LinhScriptBreakStmt getBreakStmt();

  @Nullable
  LinhScriptClassDefStmt getClassDefStmt();

  @Nullable
  LinhScriptContinueStmt getContinueStmt();

  @Nullable
  LinhScriptExprStmt getExprStmt();

  @Nullable
  LinhScriptForStmt getForStmt();

  @Nullable
  LinhScriptFuncDefStmt getFuncDefStmt();

  @Nullable
  LinhScriptIfStmt getIfStmt();

  @Nullable
  LinhScriptImportStmt getImportStmt();

  @Nullable
  LinhScriptPostfixStmt getPostfixStmt();

  @Nullable
  LinhScriptPrintStmt getPrintStmt();

  @Nullable
  LinhScriptReturnStmt getReturnStmt();

  @Nullable
  LinhScriptSwitchStmt getSwitchStmt();

  @Nullable
  LinhScriptThrowStmt getThrowStmt();

  @Nullable
  LinhScriptTryStmt getTryStmt();

  @Nullable
  LinhScriptUseStmt getUseStmt();

  @Nullable
  LinhScriptVarAssignmentStmt getVarAssignmentStmt();

  @Nullable
  LinhScriptWhileStmt getWhileStmt();

}
