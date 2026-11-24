package me.hql.linhscript;

import com.intellij.codeInsight.editorActions.BackspaceHandlerDelegate;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class LinhScriptBackspaceHandler extends BackspaceHandlerDelegate {

    private char closingCharToDelete;

    @Override
    public void beforeCharDeleted(char c, @NotNull PsiFile file, @NotNull Editor editor) {
        closingCharToDelete = matchingClosingChar(c);
    }

    @Override
    public boolean charDeleted(char c, @NotNull PsiFile file, @NotNull Editor editor) {
        if (!file.getLanguage().isKindOf(LinhScriptLanguage.INSTANCE) || closingCharToDelete == 0) {
            return false;
        }

        int offset = editor.getCaretModel().getOffset();
        CharSequence chars = editor.getDocument().getCharsSequence();
        if (offset < chars.length() && chars.charAt(offset) == closingCharToDelete) {
            editor.getDocument().deleteString(offset, offset + 1);
            return true;
        }

        return false;
    }

    private static char matchingClosingChar(char c) {
        return switch (c) {
            case '(' -> ')';
            case '[' -> ']';
            case '{' -> '}';
            default -> 0;
        };
    }
}
