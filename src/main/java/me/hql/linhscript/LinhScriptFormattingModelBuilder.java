package me.hql.linhscript;

import com.intellij.formatting.*;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;

public class LinhScriptFormattingModelBuilder implements FormattingModelBuilder {

    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
        return new SpacingBuilder(settings, LinhScriptLanguage.INSTANCE)
                .around(me.hql.linhscript.psi.LinhScriptTypes.OP_ASSIGN).spaceIf(true)
                .around(me.hql.linhscript.psi.LinhScriptTypes.OP_PLUS).spaceIf(true)
                .around(me.hql.linhscript.psi.LinhScriptTypes.OP_MINUS).spaceIf(true)
                .around(me.hql.linhscript.psi.LinhScriptTypes.OP_MUL).spaceIf(true)
                .around(me.hql.linhscript.psi.LinhScriptTypes.OP_DIV).spaceIf(true)
                .after(me.hql.linhscript.psi.LinhScriptTypes.COMMA).spaceIf(true)
                .before(me.hql.linhscript.psi.LinhScriptTypes.L_BRACE).spaceIf(true);
    }

    @NotNull
    @Override
    public FormattingModel createModel(@NotNull FormattingContext formattingContext) {
        CodeStyleSettings settings = formattingContext.getCodeStyleSettings();
        LinhScriptBlock block = new LinhScriptBlock(
                formattingContext.getNode(),
                Wrap.createWrap(WrapType.NONE, false),
                Alignment.createAlignment(),
                createSpaceBuilder(settings)
        );
        return FormattingModelProvider.createFormattingModelForPsiFile(
                formattingContext.getContainingFile(),
                block,
                settings
        );
    }
}
