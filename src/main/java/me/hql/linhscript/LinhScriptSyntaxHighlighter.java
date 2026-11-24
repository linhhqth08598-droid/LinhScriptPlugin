package me.hql.linhscript;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import me.hql.linhscript.psi.LinhScriptTypes;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;
import java.awt.Font;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class LinhScriptSyntaxHighlighter extends SyntaxHighlighterBase {
    private static final Color WHITE = new Color(0xFF, 0xFF, 0xFF);

    private static final TextAttributes COMMENT_ATTR =
            new TextAttributes(new Color(0x6B, 0x72, 0x80), null, null, null, Font.PLAIN);
    private static final TextAttributes USE_ATTR =
            new TextAttributes(new Color(0xF3, 0x66, 0x9A), null, null, null, Font.BOLD);
    private static final TextAttributes VAR_ATTR =
            new TextAttributes(new Color(0xCF, 0x8E, 0x6D), null, null, null, Font.PLAIN);
    private static final TextAttributes BUILTIN_FUNC_ATTR =
            new TextAttributes(new Color(0xD7, 0x7D, 0xBB), null, null, null, Font.PLAIN);
    private static final TextAttributes KEYWORD_ATTR =
            new TextAttributes(new Color(0xE5, 0xC0, 0x7B), null, null, null, Font.BOLD);
    private static final TextAttributes TRUE_ATTR =
            new TextAttributes(new Color(0x6A, 0xAB, 0x73), null, null, null, Font.ITALIC);
    private static final TextAttributes FALSE_ATTR =
            new TextAttributes(new Color(0xE0, 0x6C, 0x9F), null, null, null, Font.ITALIC);
    private static final TextAttributes STRING_ATTR =
            new TextAttributes(new Color(0x6A, 0xAB, 0x73), null, null, null, Font.PLAIN);
    private static final TextAttributes NUMBER_ATTR =
            new TextAttributes(new Color(0xD1, 0x9A, 0x66), null, null, null, Font.PLAIN);
    private static final TextAttributes OPERATOR_ATTR =
            new TextAttributes(WHITE, null, null, null, Font.PLAIN);
    private static final TextAttributes PUNCTUATION_ATTR =
            new TextAttributes(WHITE, null, null, null, Font.PLAIN);
    private static final TextAttributes IDENTIFIER_ATTR =
            new TextAttributes(new Color(0x9C, 0xDC, 0xFE), null, null, null, Font.PLAIN);

    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("LS_COMMENT", COMMENT_ATTR);
    public static final TextAttributesKey USE_KEY =
            createTextAttributesKey("LS_USE", USE_ATTR);
    public static final TextAttributesKey VAR_KEY =
            createTextAttributesKey("LS_VAR", VAR_ATTR);
    public static final TextAttributesKey BUILTIN_FUNC =
            createTextAttributesKey("LS_BUILTIN_FUNC", BUILTIN_FUNC_ATTR);
    public static final TextAttributesKey KEYWORD =
            createTextAttributesKey("LS_KEYWORD", KEYWORD_ATTR);
    public static final TextAttributesKey BOOLEAN_TRUE =
            createTextAttributesKey("LS_BOOLEAN_TRUE", TRUE_ATTR);
    public static final TextAttributesKey BOOLEAN_FALSE =
            createTextAttributesKey("LS_BOOLEAN_FALSE", FALSE_ATTR);
    public static final TextAttributesKey STRING =
            createTextAttributesKey("LS_STRING", STRING_ATTR);
    public static final TextAttributesKey NUMBER =
            createTextAttributesKey("LS_NUMBER", NUMBER_ATTR);
    public static final TextAttributesKey OPERATOR =
            createTextAttributesKey("LS_OPERATOR", OPERATOR_ATTR);
    public static final TextAttributesKey PUNCTUATION =
            createTextAttributesKey("LS_PUNCTUATION", PUNCTUATION_ATTR);
    public static final TextAttributesKey IDENTIFIER =
            createTextAttributesKey("LS_IDENTIFIER", IDENTIFIER_ATTR);

    public static final TextAttributesKey USER_FUNC =
            createTextAttributesKey("LS_USER_FUNC",
                    new TextAttributes(new Color(0x61, 0xAF, 0xEF), null, null, null, Font.PLAIN));
    public static final TextAttributesKey VARIABLE_ID =
            createTextAttributesKey("LS_VARIABLE_ID",
                    new TextAttributes(new Color(0xE0, 0x6C, 0x75), null, null, null, Font.PLAIN));

    private static final TokenSet BUILTIN_FUNCS = TokenSet.create(
            LinhScriptTypes.PRINT, LinhScriptTypes.INPUT, LinhScriptTypes.RANDOM,
            LinhScriptTypes.ADD, LinhScriptTypes.SIZE, LinhScriptTypes.LEN,
            LinhScriptTypes.PUSH, LinhScriptTypes.POP, LinhScriptTypes.TYPE,
            LinhScriptTypes.STR, LinhScriptTypes.NUM, LinhScriptTypes.BOOL,
            LinhScriptTypes.READ, LinhScriptTypes.WRITE
    );

    private static final TokenSet KEYWORDS = TokenSet.create(
            LinhScriptTypes.IF, LinhScriptTypes.ELSE, LinhScriptTypes.SWITCH,
            LinhScriptTypes.CASE, LinhScriptTypes.DEFAULT, LinhScriptTypes.BREAK,
            LinhScriptTypes.CONTINUE, LinhScriptTypes.WHILE, LinhScriptTypes.FOR,
            LinhScriptTypes.TO, LinhScriptTypes.IN, LinhScriptTypes.FUNC,
            LinhScriptTypes.CALL, LinhScriptTypes.RETURN, LinhScriptTypes.NULL,
            LinhScriptTypes.NOT, LinhScriptTypes.IMPORT, LinhScriptTypes.CLASS,
            LinhScriptTypes.EXTENDS, LinhScriptTypes.NEW, LinhScriptTypes.TRY,
            LinhScriptTypes.CATCH, LinhScriptTypes.FINALLY, LinhScriptTypes.THROW
    );

    private static final TokenSet OPERATORS = TokenSet.create(
            LinhScriptTypes.OP_NOT, LinhScriptTypes.OP_ASSIGN,
            LinhScriptTypes.OP_PLUS_ASSIGN, LinhScriptTypes.OP_MINUS_ASSIGN,
            LinhScriptTypes.OP_MUL_ASSIGN, LinhScriptTypes.OP_DIV_ASSIGN,
            LinhScriptTypes.OP_INC, LinhScriptTypes.OP_DEC,
            LinhScriptTypes.OP_PLUS, LinhScriptTypes.OP_MINUS,
            LinhScriptTypes.OP_MUL, LinhScriptTypes.OP_DIV,
            LinhScriptTypes.OP_MOD, LinhScriptTypes.OP_POW,
            LinhScriptTypes.OP_EQ, LinhScriptTypes.OP_NE,
            LinhScriptTypes.OP_GT, LinhScriptTypes.OP_LT,
            LinhScriptTypes.OP_GE, LinhScriptTypes.OP_LE,
            LinhScriptTypes.OP_AND, LinhScriptTypes.OP_OR,
            LinhScriptTypes.QUESTION, LinhScriptTypes.OP_RANGE
    );

    private static final TokenSet PUNCTUATIONS = TokenSet.create(
            LinhScriptTypes.COMMA, LinhScriptTypes.SEMI, LinhScriptTypes.COLON,
            LinhScriptTypes.DOT, LinhScriptTypes.L_PAREN, LinhScriptTypes.R_PAREN,
            LinhScriptTypes.L_BRACE, LinhScriptTypes.R_BRACE,
            LinhScriptTypes.L_BRACKET, LinhScriptTypes.R_BRACKET
    );

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new LinhScriptLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType == null) {
            return TextAttributesKey.EMPTY_ARRAY;
        }
        if (tokenType.equals(LinhScriptTypes.COMMENT)) {
            return new TextAttributesKey[]{COMMENT};
        }
        if (tokenType.equals(LinhScriptTypes.USE)) {
            return new TextAttributesKey[]{USE_KEY};
        }
        if (tokenType.equals(LinhScriptTypes.VAR)) {
            return new TextAttributesKey[]{VAR_KEY};
        }
        if (BUILTIN_FUNCS.contains(tokenType)) {
            return new TextAttributesKey[]{BUILTIN_FUNC};
        }
        if (tokenType.equals(LinhScriptTypes.TRUE)) {
            return new TextAttributesKey[]{BOOLEAN_TRUE};
        }
        if (tokenType.equals(LinhScriptTypes.FALSE)) {
            return new TextAttributesKey[]{BOOLEAN_FALSE};
        }
        if (KEYWORDS.contains(tokenType)) {
            return new TextAttributesKey[]{KEYWORD};
        }
        if (OPERATORS.contains(tokenType)) {
            return new TextAttributesKey[]{OPERATOR};
        }
        if (PUNCTUATIONS.contains(tokenType)) {
            return new TextAttributesKey[]{PUNCTUATION};
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
        return TextAttributesKey.EMPTY_ARRAY;
    }
}