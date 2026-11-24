package me.hql.linhscript;

import com.intellij.codeInsight.CodeInsightSettings;
import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorModificationUtil;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class LinhScriptTypedHandler extends TypedHandlerDelegate {

    @NotNull
    @Override
    public Result beforeCharTyped(char c,
                                  @NotNull Project project,
                                  @NotNull Editor editor,
                                  @NotNull PsiFile file,
                                  @NotNull FileType fileType) {
        if (!isLinhScriptFile(file) || !CodeInsightSettings.getInstance().AUTOINSERT_PAIR_BRACKET) {
            return Result.CONTINUE;
        }

        String pair = switch (c) {
            case '(' -> "()";
            case '[' -> "[]";
            case '{' -> "{}";
            default -> null;
        };

        if (pair != null) {
            if (editor.getSelectionModel().hasSelection()) {
                wrapSelection(editor, pair.charAt(0), pair.charAt(1));
            } else {
                EditorModificationUtil.insertStringAtCaret(editor, pair, false, 1);
            }
            return Result.STOP;
        }

        if (isClosingBracket(c) && skipExistingClosingBracket(editor, c)) {
            return Result.STOP;
        }

        return Result.CONTINUE;
    }

    private static void wrapSelection(Editor editor, char open, char close) {
        SelectionModel selectionModel = editor.getSelectionModel();
        Document document = editor.getDocument();
        int start = selectionModel.getSelectionStart();
        int end = selectionModel.getSelectionEnd();

        document.insertString(end, String.valueOf(close));
        document.insertString(start, String.valueOf(open));
        selectionModel.setSelection(start + 1, end + 1);
    }

    private static boolean skipExistingClosingBracket(Editor editor, char closingBracket) {
        int offset = editor.getCaretModel().getOffset();
        CharSequence chars = editor.getDocument().getCharsSequence();
        if (offset < chars.length() && chars.charAt(offset) == closingBracket) {
            editor.getCaretModel().moveToOffset(offset + 1);
            return true;
        }
        return false;
    }

    private static boolean isClosingBracket(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    private static boolean isLinhScriptFile(PsiFile file) {
        return file.getLanguage().isKindOf(LinhScriptLanguage.INSTANCE);
    }
}
