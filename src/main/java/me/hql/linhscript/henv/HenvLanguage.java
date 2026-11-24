package me.hql.linhscript.henv;

import com.intellij.lang.Language;

/** Ngôn ngữ của tệp cấu hình môi trường LinhScript (.henv). */
public class HenvLanguage extends Language {
    public static final HenvLanguage INSTANCE = new HenvLanguage();

    private HenvLanguage() {
        super("HqlEnv");
    }
}
