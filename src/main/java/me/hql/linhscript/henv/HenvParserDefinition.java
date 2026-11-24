package me.hql.linhscript.henv;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.jetbrains.annotations.NotNull;

/** PSI phẳng: cả tệp là một cây lá token — đủ để tô màu, comment (Ctrl+/) và tìm kiếm. */
public class HenvParserDefinition implements ParserDefinition {
    public static final IFileElementType FILE = new IFileElementType(HenvLanguage.INSTANCE);

    @NotNull @Override public Lexer createLexer(Project project) { return new HenvLexer(); }

    @NotNull @Override public PsiParser createParser(Project project) {
        return new PsiParser() {
            @NotNull @Override
            public ASTNode parse(@NotNull IElementType root, @NotNull PsiBuilder builder) {
                PsiBuilder.Marker m = builder.mark();
                while (!builder.eof()) builder.advanceLexer();
                m.done(root);
                return builder.getTreeBuilt();
            }
        };
    }

    @NotNull @Override public IFileElementType getFileNodeType() { return FILE; }
    @NotNull @Override public TokenSet getCommentTokens() { return HenvTokenTypes.COMMENTS; }
    @NotNull @Override public TokenSet getStringLiteralElements() { return HenvTokenTypes.STRINGS; }
    @NotNull @Override public PsiElement createElement(ASTNode node) { return new ASTWrapperPsiElement(node); }
    @NotNull @Override public PsiFile createFile(@NotNull FileViewProvider viewProvider) { return new HenvFile(viewProvider); }
}
