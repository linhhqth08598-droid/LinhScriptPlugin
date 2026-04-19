package me.hql.linhscript;

import com.intellij.lang.*;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.tree.*;
import me.hql.linhscript.parser.LinhScriptParser;
import me.hql.linhscript.psi.LinhScriptTypes;
import org.jetbrains.annotations.NotNull;

public class LinhScriptParserDefinition implements ParserDefinition {
    public static final IFileElementType FILE = new IFileElementType(LinhScriptLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new LinhScriptLexerAdapter();
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() { return TokenSet.EMPTY; }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() { return TokenSet.EMPTY; }

    @Override
    public PsiParser createParser(final Project project) {
        return new LinhScriptParser();
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType() { return FILE; }

    @NotNull
    @Override
    public PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new LinhScriptFile(viewProvider);
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return LinhScriptTypes.Factory.createElement(node);
    }
}