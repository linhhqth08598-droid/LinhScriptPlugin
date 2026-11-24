// This is a generated file. Not intended for manual editing.
package me.hql.linhscript.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LinhScriptExpr extends PsiElement {

  @Nullable
  LinhScriptAddExpr getAddExpr();

  @Nullable
  LinhScriptAndExpr getAndExpr();

  @Nullable
  LinhScriptArrayExpr getArrayExpr();

  @Nullable
  LinhScriptCompareExpr getCompareExpr();

  @Nullable
  LinhScriptLiteralExpr getLiteralExpr();

  @Nullable
  LinhScriptMapExpr getMapExpr();

  @Nullable
  LinhScriptMulExpr getMulExpr();

  @Nullable
  LinhScriptOrExpr getOrExpr();

  @Nullable
  LinhScriptParenExpr getParenExpr();

  @Nullable
  LinhScriptPostfixExpr getPostfixExpr();

  @Nullable
  LinhScriptPowExpr getPowExpr();

  @Nullable
  LinhScriptTernaryExpr getTernaryExpr();

  @Nullable
  LinhScriptUnaryExpr getUnaryExpr();

}
