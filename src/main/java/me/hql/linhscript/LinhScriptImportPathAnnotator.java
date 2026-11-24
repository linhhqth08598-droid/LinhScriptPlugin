package me.hql.linhscript;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import me.hql.linhscript.psi.LinhScriptTypes;
import org.jetbrains.annotations.NotNull;

public class LinhScriptImportPathAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element.getNode().getElementType() != LinhScriptTypes.STRING) {
            return;
        }

        PsiElement prev = PsiTreeUtil.prevVisibleLeaf(element);
        if (prev == null || prev.getNode().getElementType() != LinhScriptTypes.IMPORT) {
            return;
        }

        String rawText = element.getText();
        if (rawText.length() < 2 || !rawText.startsWith("\"") || !rawText.endsWith("\"")) {
            return;
        }

        String relPath = rawText.substring(1, rawText.length() - 1).trim();
        if (relPath.isEmpty()) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Đường dẫn import không được để trống")
                    .range(element.getTextRange())
                    .create();
            return;
        }

        PsiFile containingFile = element.getContainingFile();
        if (containingFile == null) {
            return;
        }

        VirtualFile currentFile = containingFile.getVirtualFile();
        Project project = containingFile.getProject();
        VirtualFile baseDir = project.getBaseDir();

        VirtualFile parentDir = (currentFile != null && currentFile.getParent() != null)
                ? currentFile.getParent()
                : null;

        boolean exists = resolveExists(parentDir, relPath) || resolveExists(baseDir, relPath);
        if (!exists) {
            holder.newAnnotation(HighlightSeverity.ERROR,
                            "Không tìm thấy file hoặc thư mục '" + relPath + "'")
                    .range(element.getTextRange())
                    .create();
        }
    }

    private boolean resolveExists(VirtualFile root, String relPath) {
        if (root == null) {
            return false;
        }
        String normalized = relPath.replace('\\', '/');
        while (normalized.endsWith("/") && normalized.length() > 1) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }
        VirtualFile found = root.findFileByRelativePath(normalized);
        return found != null && found.exists();
    }
}