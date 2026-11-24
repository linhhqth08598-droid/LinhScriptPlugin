package me.hql.linhscript.henv;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

/** Settings → Editor → Color Scheme → "HQL Env (.henv)" để tự chỉnh màu. */
public class HenvColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = {
            new AttributesDescriptor("Chú thích", HenvSyntaxHighlighter.COMMENT),
            new AttributesDescriptor("Từ khoá export", HenvSyntaxHighlighter.EXPORT),
            new AttributesDescriptor("Khoá", HenvSyntaxHighlighter.KEY),
            new AttributesDescriptor("Dấu = hoặc :", HenvSyntaxHighlighter.SEPARATOR),
            new AttributesDescriptor("Giá trị trần", HenvSyntaxHighlighter.VALUE),
            new AttributesDescriptor("Giá trị số", HenvSyntaxHighlighter.NUMBER),
            new AttributesDescriptor("Giá trị true/false/yes/no...", HenvSyntaxHighlighter.BOOLEAN),
            new AttributesDescriptor("Chuỗi trong nháy", HenvSyntaxHighlighter.STRING),
            new AttributesDescriptor("Ký tự thoát (\\n \\t \\\" \\\\ \\$)", HenvSyntaxHighlighter.ESCAPE),
            new AttributesDescriptor("Nối biến ${ }", HenvSyntaxHighlighter.INTERP_BRACES),
            new AttributesDescriptor("Nối biến//Tên biến", HenvSyntaxHighlighter.INTERP_NAME),
            new AttributesDescriptor("Nối biến//Dấu :-", HenvSyntaxHighlighter.INTERP_OP),
            new AttributesDescriptor("Nối biến//Giá trị dự phòng", HenvSyntaxHighlighter.INTERP_DEFAULT),
            new AttributesDescriptor("Dòng sai cú pháp (bị bỏ qua)", HenvSyntaxHighlighter.BAD),
    };

    @Nullable @Override public Icon getIcon() { return HenvIcons.FILE; }
    @NotNull @Override public SyntaxHighlighter getHighlighter() { return new HenvSyntaxHighlighter(); }
    @NotNull @Override public AttributesDescriptor[] getAttributeDescriptors() { return DESCRIPTORS; }
    @NotNull @Override public ColorDescriptor[] getColorDescriptors() { return ColorDescriptor.EMPTY_ARRAY; }
    @NotNull @Override public String getDisplayName() { return "HQL Env (.henv)"; }
    @Nullable @Override public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() { return null; }

    @NotNull @Override
    public String getDemoText() {
        return "# Tệp cấu hình LinhScript\n"
                + "APP_NAME=\"Thư Viện Lá Bàng\"      # chú thích cuối dòng\n"
                + "export APP_ENV=development\n"
                + "APP_DEBUG=true\n"
                + "SERVER_PORT=8080\n"
                + "SERVER_URL=http://localhost:${SERVER_PORT}\n"
                + "DB_USER=${DB_USER_MAC_DINH:-root}\n"
                + "DB_PASSWORD='giữ nguyên #${chữ này}'\n"
                + "LOI_CHAO=\"Xin chào\\n\\tBạn \\\"Linh\\\" \\${khong-noi-bien}\"\n"
                + "GHI_CHU=\"dòng một\n"
                + "dòng hai\"\n"
                + "db.hostname: localhost\n"
                + "dong-nay-thieu-dau-bang\n";
    }
}
