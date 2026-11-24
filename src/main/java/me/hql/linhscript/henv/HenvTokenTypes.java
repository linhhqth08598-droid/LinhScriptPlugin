package me.hql.linhscript.henv;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;

import me.hql.linhscript.henv.HenvTokenizer.Kind;

public final class HenvTokenTypes {

    public static final class HenvTokenType extends IElementType {
        public HenvTokenType(@NotNull @NonNls String debugName) {
            super(debugName, HenvLanguage.INSTANCE);
        }
    }

    public static final IElementType COMMENT        = new HenvTokenType("HENV_COMMENT");
    public static final IElementType EXPORT         = new HenvTokenType("HENV_EXPORT");
    public static final IElementType KEY            = new HenvTokenType("HENV_KEY");
    public static final IElementType SEPARATOR      = new HenvTokenType("HENV_SEPARATOR");
    public static final IElementType VALUE          = new HenvTokenType("HENV_VALUE");
    public static final IElementType NUMBER         = new HenvTokenType("HENV_NUMBER");
    public static final IElementType BOOLEAN        = new HenvTokenType("HENV_BOOLEAN");
    public static final IElementType STRING         = new HenvTokenType("HENV_STRING");
    public static final IElementType ESCAPE         = new HenvTokenType("HENV_ESCAPE");
    public static final IElementType INTERP_OPEN    = new HenvTokenType("HENV_INTERP_OPEN");
    public static final IElementType INTERP_NAME    = new HenvTokenType("HENV_INTERP_NAME");
    public static final IElementType INTERP_OP      = new HenvTokenType("HENV_INTERP_OP");
    public static final IElementType INTERP_DEFAULT = new HenvTokenType("HENV_INTERP_DEFAULT");
    public static final IElementType INTERP_CLOSE   = new HenvTokenType("HENV_INTERP_CLOSE");
    public static final IElementType BAD            = new HenvTokenType("HENV_BAD_LINE");

    public static final TokenSet COMMENTS = TokenSet.create(COMMENT);
    public static final TokenSet STRINGS  = TokenSet.create(STRING);

    private static final Map<Kind, IElementType> MAP = new EnumMap<>(Kind.class);
    static {
        MAP.put(Kind.WHITESPACE, TokenType.WHITE_SPACE);
        MAP.put(Kind.COMMENT, COMMENT);
        MAP.put(Kind.EXPORT, EXPORT);
        MAP.put(Kind.KEY, KEY);
        MAP.put(Kind.SEPARATOR, SEPARATOR);
        MAP.put(Kind.VALUE, VALUE);
        MAP.put(Kind.NUMBER, NUMBER);
        MAP.put(Kind.BOOLEAN, BOOLEAN);
        MAP.put(Kind.STRING, STRING);
        MAP.put(Kind.ESCAPE, ESCAPE);
        MAP.put(Kind.INTERP_OPEN, INTERP_OPEN);
        MAP.put(Kind.INTERP_NAME, INTERP_NAME);
        MAP.put(Kind.INTERP_OP, INTERP_OP);
        MAP.put(Kind.INTERP_DEFAULT, INTERP_DEFAULT);
        MAP.put(Kind.INTERP_CLOSE, INTERP_CLOSE);
        MAP.put(Kind.BAD, BAD);
    }

    public static IElementType of(Kind kind) { return MAP.get(kind); }

    private HenvTokenTypes() {}
}
