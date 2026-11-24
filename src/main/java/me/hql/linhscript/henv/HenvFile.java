package me.hql.linhscript.henv;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class HenvFile extends PsiFileBase {
    public HenvFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, HenvLanguage.INSTANCE);
    }

    @NotNull @Override public FileType getFileType() { return HenvFileType.INSTANCE; }

    @Override public String toString() { return "HQL Env File"; }
}
