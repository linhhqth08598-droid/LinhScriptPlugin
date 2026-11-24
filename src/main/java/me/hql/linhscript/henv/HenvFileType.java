package me.hql.linhscript.henv;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/** Nhận diện: .henv, .henv.local, .henv.production, .henv.example, ten.henv ... */
public final class HenvFileType extends LanguageFileType {
    public static final HenvFileType INSTANCE = new HenvFileType();

    private HenvFileType() {
        super(HenvLanguage.INSTANCE);
    }

    @NotNull @Override public String getName() { return "HQL Env File"; }

    @NotNull @Override public String getDescription() {
        return "Tệp cấu hình môi trường LinhScript (HQL Environment Configuration File)";
    }

    @NotNull @Override public String getDefaultExtension() { return "henv"; }

    @Nullable @Override public Icon getIcon() { return HenvIcons.FILE; }
}
