package me.hql.linhscript;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import me.hql.linhscript.psi.LinhScriptTypes;
import org.jetbrains.annotations.NotNull;

public class LinhScriptAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        // Chỉ quan tâm đến các từ do sếp tự đặt (IDENTIFIER)
        if (element.getNode().getElementType() == LinhScriptTypes.IDENTIFIER) {

            // Nhìn sang phần tử tiếp theo bên phải (bỏ qua khoảng trắng)
            PsiElement nextElement = PsiTreeUtil.nextVisibleLeaf(element);

            // Nếu ngay sau nó là dấu ngoặc đơn '(' -> Đích thị là tên Hàm (Function Call)
            if (nextElement != null && nextElement.getNode().getElementType() == LinhScriptTypes.L_PAREN) {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .textAttributes(DefaultLanguageHighlighterColors.FUNCTION_CALL)
                        .create();
            }
            // Nếu không có ngoặc đơn -> Cho nó là Biến (Local Variable)
            else {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .textAttributes(DefaultLanguageHighlighterColors.LOCAL_VARIABLE)
                        .create();
            }
        }
    }
}