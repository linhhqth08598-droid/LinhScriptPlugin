package me.hql.linhscript;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.psi.tree.IElementType;
import me.hql.linhscript.psi.LinhScriptTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LinhScriptBlock extends AbstractBlock {
    private final SpacingBuilder spacingBuilder;

    protected LinhScriptBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment, SpacingBuilder spacingBuilder) {
        super(node, wrap, alignment);
        this.spacingBuilder = spacingBuilder;
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<>();
        ASTNode child = myNode.getFirstChildNode();
        while (child != null) {
            if (child.getElementType() != TokenType.WHITE_SPACE) {
                Block block = new LinhScriptBlock(child, Wrap.createWrap(WrapType.NONE, false), null, spacingBuilder);
                blocks.add(block);
            }
            child = child.getTreeNext();
        }
        return blocks;
    }

    @Override
    public Indent getIndent() {
        IElementType elementType = myNode.getElementType();
        
        // Cốt lõi của thụt lề: Nằm trong khối block và không phải là dấu ngoặc nhọn
        if (myNode.getTreeParent() != null && myNode.getTreeParent().getElementType() == LinhScriptTypes.BLOCK) {
            if (elementType != LinhScriptTypes.L_BRACE && elementType != LinhScriptTypes.R_BRACE) {
                return Indent.getNormalIndent();
            }
        }
        return Indent.getNoneIndent();
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        return spacingBuilder.getSpacing(this, child1, child2);
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }
}
