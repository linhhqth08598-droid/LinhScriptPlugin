package me.hql.linhscript.psi;

import com.intellij.psi.tree.IElementType;
import me.hql.linhscript.LinhScriptLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class LinhScriptTokenType extends IElementType {
    public LinhScriptTokenType(@NotNull @NonNls String debugName) {
        super(debugName, LinhScriptLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "LinhScriptTokenType." + super.toString();
    }
}