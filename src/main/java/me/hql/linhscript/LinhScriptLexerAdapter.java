package me.hql.linhscript;

import com.intellij.lexer.FlexAdapter;

public class LinhScriptLexerAdapter extends FlexAdapter {
    public LinhScriptLexerAdapter() {
        super(new LinhScriptLexer(null));
    }
}