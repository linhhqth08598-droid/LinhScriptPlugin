// This is a generated file. Not intended for manual editing.
package me.hql.linhscript.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface LinhScriptSwitchStmt extends PsiElement {

  @NotNull
  LinhScriptExpr getExpr();

  @NotNull
  List<LinhScriptStmt> getStmtList();

  @NotNull
  List<LinhScriptSwitchClause> getSwitchClauseList();

}
