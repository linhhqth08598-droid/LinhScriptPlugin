package me.hql.linhscript;

import com.intellij.lang.Language;

public class LinhScriptLanguage extends Language {
    public static final LinhScriptLanguage INSTANCE = new LinhScriptLanguage();

    private LinhScriptLanguage() {
        super("LinhScript");
    }
}