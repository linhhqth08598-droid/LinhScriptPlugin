package me.hql.linhscript.henv;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

/**
 * Màu mặc định mượn từ bảng màu của .properties / .env nên hợp mọi theme (Darcula, Light, ...).
 * Có thể chỉnh trong Settings → Editor → Color Scheme → HQL Env (.henv).
 */
public class HenvSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey COMMENT = createTextAttributesKey("HENV_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey EXPORT = createTextAttributesKey("HENV_EXPORT", DefaultLanguageHighlighterColors.METADATA);
    public static final TextAttributesKey KEY = createTextAttributesKey("HENV_KEY", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey SEPARATOR = createTextAttributesKey("HENV_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey VALUE = createTextAttributesKey("HENV_VALUE", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey NUMBER = createTextAttributesKey("HENV_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey BOOLEAN = createTextAttributesKey("HENV_BOOLEAN", DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey STRING = createTextAttributesKey("HENV_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey ESCAPE = createTextAttributesKey("HENV_ESCAPE", DefaultLanguageHighlighterColors.VALID_STRING_ESCAPE);
    public static final TextAttributesKey INTERP_BRACES = createTextAttributesKey("HENV_INTERP_BRACES", DefaultLanguageHighlighterColors.BRACES);
    public static final TextAttributesKey INTERP_NAME = createTextAttributesKey("HENV_INTERP_NAME", DefaultLanguageHighlighterColors.INSTANCE_FIELD);
    public static final TextAttributesKey INTERP_OP = createTextAttributesKey("HENV_INTERP_OP", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey INTERP_DEFAULT = createTextAttributesKey("HENV_INTERP_DEFAULT", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey BAD = createTextAttributesKey("HENV_BAD_LINE", HighlighterColors.BAD_CHARACTER);

    private static final TextAttributesKey[] NONE = new TextAttributesKey[0];

    @NotNull @Override
    public Lexer getHighlightingLexer() {
        return new HenvLexer();
    }

    @NotNull @Override
    public TextAttributesKey[] getTokenHighlights(IElementType t) {
        if (t == HenvTokenTypes.COMMENT) return pack(COMMENT);
        if (t == HenvTokenTypes.EXPORT) return pack(EXPORT);
        if (t == HenvTokenTypes.KEY) return pack(KEY);
        if (t == HenvTokenTypes.SEPARATOR) return pack(SEPARATOR);
        if (t == HenvTokenTypes.VALUE) return pack(VALUE);
        if (t == HenvTokenTypes.NUMBER) return pack(NUMBER);
        if (t == HenvTokenTypes.BOOLEAN) return pack(BOOLEAN);
        if (t == HenvTokenTypes.STRING) return pack(STRING);
        if (t == HenvTokenTypes.ESCAPE) return pack(ESCAPE);
        if (t == HenvTokenTypes.INTERP_OPEN || t == HenvTokenTypes.INTERP_CLOSE) return pack(INTERP_BRACES);
        if (t == HenvTokenTypes.INTERP_NAME) return pack(INTERP_NAME);
        if (t == HenvTokenTypes.INTERP_OP) return pack(INTERP_OP);
        if (t == HenvTokenTypes.INTERP_DEFAULT) return pack(INTERP_DEFAULT);
        if (t == HenvTokenTypes.BAD) return pack(BAD);
        return NONE;
    }
}
