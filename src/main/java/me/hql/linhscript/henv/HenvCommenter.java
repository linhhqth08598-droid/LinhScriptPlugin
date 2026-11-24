package me.hql.linhscript.henv;

import com.intellij.lang.Commenter;
import org.jetbrains.annotations.Nullable;

/** Ctrl + / bật tắt chú thích bằng dấu # cho dòng đang chọn. */
public class HenvCommenter implements Commenter {
    @Nullable @Override public String getLineCommentPrefix() { return "# "; }
    @Nullable @Override public String getBlockCommentPrefix() { return null; }
    @Nullable @Override public String getBlockCommentSuffix() { return null; }
    @Nullable @Override public String getCommentedBlockCommentPrefix() { return null; }
    @Nullable @Override public String getCommentedBlockCommentSuffix() { return null; }
}
