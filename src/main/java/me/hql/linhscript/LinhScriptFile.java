package me.hql.linhscript;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class LinhScriptFile extends PsiFileBase {
    public LinhScriptFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, LinhScriptLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return LinhScriptFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "LinhScript File";
    }
}