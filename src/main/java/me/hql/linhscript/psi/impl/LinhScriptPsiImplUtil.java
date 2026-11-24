package me.hql.linhscript.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import me.hql.linhscript.psi.LinhScriptFuncDefStmt;
import me.hql.linhscript.psi.LinhScriptTypes;
import me.hql.linhscript.psi.LinhScriptVarAssignmentStmt;

public class LinhScriptPsiImplUtil {

    // Lấy tên của biến (từ IDENTIFIER)
    public static PsiElement getNameIdentifier(LinhScriptVarAssignmentStmt element) {
        ASTNode node = element.getNode().findChildByType(LinhScriptTypes.IDENTIFIER);
        return node != null ? node.getPsi() : null;
    }

    // Lấy tên của hàm (từ IDENTIFIER)
    public static PsiElement getNameIdentifier(LinhScriptFuncDefStmt element) {
        ASTNode node = element.getNode().findChildByType(LinhScriptTypes.IDENTIFIER);
        return node != null ? node.getPsi() : null;
    }
}