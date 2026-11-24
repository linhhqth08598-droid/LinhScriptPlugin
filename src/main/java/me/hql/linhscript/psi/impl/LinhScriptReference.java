package me.hql.linhscript.psi.impl;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import me.hql.linhscript.psi.LinhScriptFuncDefStmt;
import me.hql.linhscript.psi.LinhScriptVarAssignmentStmt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LinhScriptReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
    private final String key;

    public LinhScriptReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        key = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        List<ResolveResult> results = new ArrayList<>();
        PsiFile file = myElement.getContainingFile();

        // 1. Tìm trong các khai báo biến (Hỗ trợ cả ten = "Linh" và var ten = "Linh")
        LinhScriptVarAssignmentStmt[] vars = PsiTreeUtil.getChildrenOfType(file, LinhScriptVarAssignmentStmt.class);
        if (vars != null) {
            for (LinhScriptVarAssignmentStmt var : vars) {
                // Ép kiểu về PsiNamedElement để gọi getName()
                if (var instanceof PsiNamedElement && key.equals(((PsiNamedElement) var).getName())) {
                    results.add(new PsiElementResolveResult(var));
                }
            }
        }

        // 2. Tìm trong các khai báo hàm
        LinhScriptFuncDefStmt[] funcs = PsiTreeUtil.getChildrenOfType(file, LinhScriptFuncDefStmt.class);
        if (funcs != null) {
            for (LinhScriptFuncDefStmt func : funcs) {
                if (func instanceof PsiNamedElement && key.equals(((PsiNamedElement) func).getName())) {
                    results.add(new PsiElementResolveResult(func));
                }
            }
        }

        return results.toArray(new ResolveResult[0]);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }
}