// This is a generated file. Not intended for manual editing.
package me.hql.linhscript.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LinhScriptPrimaryExpr extends PsiElement {

  @Nullable
  LinhScriptCallExpr getCallExpr();

  @Nullable
  LinhScriptLiteralExpr getLiteralExpr();

  @Nullable
  LinhScriptNewExpr getNewExpr();

  @Nullable
  LinhScriptParenExpr getParenExpr();

  @Nullable
  LinhScriptVariableExpr getVariableExpr();

}
