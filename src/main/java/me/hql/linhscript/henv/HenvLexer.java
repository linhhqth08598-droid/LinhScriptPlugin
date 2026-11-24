package me.hql.linhscript.henv;

import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Lexer cho .henv. Cắt token một lượt khi start() (tệp cấu hình nhỏ, nhanh);
 * getState() trả trạng thái tại ĐẦU token hiện tại (0 = đầu dòng → IDE khởi động lại an toàn).
 */
public class HenvLexer extends LexerBase {
    private CharSequence buffer = "";
    private int endOffset;
    private int initialState;
    private List<HenvTokenizer.Tok> toks = java.util.Collections.emptyList();
    private int idx;

    @Override
    public void start(@NotNull CharSequence buffer, int startOffset, int endOffset, int initialState) {
        this.buffer = buffer;
        this.endOffset = endOffset;
        this.initialState = initialState;
        this.toks = HenvTokenizer.tokenize(buffer, startOffset, endOffset, initialState);
        this.idx = 0;
    }

    @Override
    public int getState() {
        if (idx == 0) return initialState;
        return toks.get(Math.min(idx, toks.size()) - 1).stateAfter;
    }

    @Nullable
    @Override
    public IElementType getTokenType() {
        return idx < toks.size() ? HenvTokenTypes.of(toks.get(idx).kind) : null;
    }

    @Override public int getTokenStart() { return idx < toks.size() ? toks.get(idx).start : endOffset; }
    @Override public int getTokenEnd()   { return idx < toks.size() ? toks.get(idx).end : endOffset; }
    @Override public void advance()      { if (idx < toks.size()) idx++; }
    @NotNull @Override public CharSequence getBufferSequence() { return buffer; }
    @Override public int getBufferEnd()  { return endOffset; }
}
