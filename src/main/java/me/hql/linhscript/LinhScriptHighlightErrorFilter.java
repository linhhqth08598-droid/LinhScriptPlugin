package me.hql.linhscript;

import com.intellij.codeInsight.highlighting.HighlightErrorFilter;
import com.intellij.psi.PsiErrorElement;
import org.jetbrains.annotations.NotNull;

public class LinhScriptHighlightErrorFilter extends HighlightErrorFilter {

    @Override
    public boolean shouldHighlightErrorElement(@NotNull PsiErrorElement element) {
        return !element.getContainingFile().getLanguage().isKindOf(LinhScriptLanguage.INSTANCE);
    }
}
