package me.hql.linhscript.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.util.IncorrectOperationException;
import me.hql.linhscript.psi.LinhScriptTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class LinhScriptNamedElementImpl extends ASTWrapperPsiElement implements PsiNameIdentifierOwner {

    public LinhScriptNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public @Nullable PsiElement getNameIdentifier() {
        // Tìm chữ cái đại diện cho tên biến/hàm (chính là token IDENTIFIER)
        ASTNode keyNode = this.getNode().findChildByType(LinhScriptTypes.IDENTIFIER);
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @Override
    public @Nullable String getName() {
        PsiElement nameIdentifier = getNameIdentifier();
        return nameIdentifier != null ? nameIdentifier.getText() : null;
    }

    @Override
    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        // Sẽ code thêm nếu sau này bạn muốn làm tính năng Rename (Shift + F6)
        return this;
    }
}