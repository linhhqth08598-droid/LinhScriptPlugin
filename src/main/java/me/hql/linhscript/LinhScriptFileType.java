package me.hql.linhscript;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import javax.swing.*;

public class LinhScriptFileType extends LanguageFileType {
    public static final LinhScriptFileType INSTANCE = new LinhScriptFileType();

    private LinhScriptFileType() {
        super(LinhScriptLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "LinhScript File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Ngôn ngữ lập trình do đại ca Linh sáng tạo";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "hql";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return LinhScriptIcons.FILE;
    }
}