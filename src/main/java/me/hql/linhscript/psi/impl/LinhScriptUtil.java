package me.hql.linhscript.psi.impl;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiReference;
import me.hql.linhscript.psi.LinhScriptCallExpr;
import me.hql.linhscript.psi.LinhScriptVariableExpr;

public class LinhScriptUtil {
    // Gắn reference cho việc gọi biến
    // Trong LinhScriptUtil.java
    public static PsiReference getReference(LinhScriptVariableExpr element) {
        return new LinhScriptReference(element, new TextRange(0, element.getTextLength()));
    }

    // Gắn reference cho việc gọi hàm
    public static PsiReference getReference(LinhScriptCallExpr element) {
        // Chỉ lấy phần tên hàm trước dấu ngoặc đơn
        String text = element.getFirstChild().getText();
        return new LinhScriptReference(element, new TextRange(0, text.length()));
    }
}