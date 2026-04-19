package me.hql.linhscript;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import me.hql.linhscript.psi.LinhScriptTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class LinhScriptSyntaxHighlighter extends SyntaxHighlighterBase {
    // Định nghĩa 4 màu cơ bản như Java
    public static final TextAttributesKey KEYWORD = createTextAttributesKey("LS_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey STRING = createTextAttributesKey("LS_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey NUMBER = createTextAttributesKey("LS_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey IDENTIFIER = createTextAttributesKey("LS_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);

    private static final TokenSet KEYWORDS = TokenSet.create(
            LinhScriptTypes.PRINT, LinhScriptTypes.INPUT, LinhScriptTypes.IF, LinhScriptTypes.ELSE,
            LinhScriptTypes.WHILE, LinhScriptTypes.FOR, LinhScriptTypes.TO, LinhScriptTypes.IN,
            LinhScriptTypes.RANDOM, LinhScriptTypes.FUNC, LinhScriptTypes.CALL, LinhScriptTypes.RETURN,
            LinhScriptTypes.ADD, LinhScriptTypes.SIZE, LinhScriptTypes.LEN, LinhScriptTypes.PUSH,
            LinhScriptTypes.POP, LinhScriptTypes.TYPE, LinhScriptTypes.STR, LinhScriptTypes.NUM,
            LinhScriptTypes.BOOL, LinhScriptTypes.READ, LinhScriptTypes.WRITE, LinhScriptTypes.TRUE,
            LinhScriptTypes.FALSE, LinhScriptTypes.NULL, LinhScriptTypes.NOT, LinhScriptTypes.IMPORT,
            LinhScriptTypes.NEW
    );

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new LinhScriptLexerAdapter();
    }

    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        // Kiểm tra xem từ đang gõ có nằm trong giỏ KEYWORDS không
        if (KEYWORDS.contains(tokenType)) {
            return new TextAttributesKey[]{KEYWORD};
        }
        if (tokenType.equals(LinhScriptTypes.STRING)) {
            return new TextAttributesKey[]{STRING};
        }
        if (tokenType.equals(LinhScriptTypes.NUMBER)) {
            return new TextAttributesKey[]{NUMBER};
        }
        if (tokenType.equals(LinhScriptTypes.IDENTIFIER)) {
            return new TextAttributesKey[]{IDENTIFIER};
        }
        return new TextAttributesKey[0];
    }
}