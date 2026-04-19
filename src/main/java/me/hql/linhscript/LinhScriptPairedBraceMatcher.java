package me.hql.linhscript;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import me.hql.linhscript.psi.LinhScriptTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LinhScriptPairedBraceMatcher implements PairedBraceMatcher {

    // Nơi khai báo các cặp đôi hoàn hảo
    private static final BracePair[] PAIRS = new BracePair[]{
            new BracePair(LinhScriptTypes.L_PAREN, LinhScriptTypes.R_PAREN, false), // Ngoặc đơn ( )
            new BracePair(LinhScriptTypes.L_BRACKET, LinhScriptTypes.R_BRACKET, false), // Ngoặc vuông [ ]
            new BracePair(LinhScriptTypes.L_BRACE, LinhScriptTypes.R_BRACE, true) // Ngoặc nhọn { } (Chữ true ở đây chính là phép thuật lùi đầu dòng khi ấn Enter đấy sếp!)
    };

    @NotNull
    @Override
    public BracePair[] getPairs() {
        return PAIRS;
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        return true; // Cho phép tự động điền ngoặc ở mọi nơi
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}