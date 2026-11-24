package me.hql.linhscript;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ProcessingContext;
import me.hql.linhscript.psi.LinhScriptTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinhScriptReferenceContributor extends PsiReferenceContributor {

    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        // 1. Ctrl + Click trên chuỗi đường dẫn của câu lệnh import "..." -> mở file hoặc thư mục
        registrar.registerReferenceProvider(
                PlatformPatterns.psiElement(LinhScriptTypes.STRING),
                new PsiReferenceProvider() {
                    @Override
                    public @NotNull PsiReference[] getReferencesByElement(@NotNull PsiElement element,
                                                                          @NotNull ProcessingContext context) {
                        PsiElement prev = PsiTreeUtil.prevVisibleLeaf(element);
                        if (prev == null || prev.getNode().getElementType() != LinhScriptTypes.IMPORT) {
                            return PsiReference.EMPTY_ARRAY;
                        }

                        String raw = element.getText();
                        if (raw.length() < 2 || !raw.startsWith("\"") || !raw.endsWith("\"")) {
                            return PsiReference.EMPTY_ARRAY;
                        }

                        String innerPath = raw.substring(1, raw.length() - 1);
                        if (innerPath.trim().isEmpty()) {
                            return PsiReference.EMPTY_ARRAY;
                        }

                        TextRange range = TextRange.from(1, innerPath.length());
                        return new PsiReference[]{new ImportPathReference(element, range, innerPath.trim())};
                    }
                }
        );

        // 2. Ctrl + Click trên @TenClass, @tenFunc, @TEN_BIEN hoặc tên class/func/var -> nhảy tới chỗ khai báo
        registrar.registerReferenceProvider(
                PlatformPatterns.psiElement(LinhScriptTypes.IDENTIFIER),
                new PsiReferenceProvider() {
                    @Override
                    public @NotNull PsiReference[] getReferencesByElement(@NotNull PsiElement element,
                                                                          @NotNull ProcessingContext context) {
                        String symbolName = element.getText();
                        if (symbolName == null || symbolName.isEmpty()) {
                            return PsiReference.EMPTY_ARRAY;
                        }
                        TextRange range = TextRange.from(0, element.getTextLength());
                        return new PsiReference[]{new SymbolDeclarationReference(element, range, symbolName)};
                    }
                }
        );
    }

    /** Tham chiếu đường dẫn trong lệnh import "..." tới VirtualFile / PsiFileSystemItem. */
    private static class ImportPathReference extends PsiReferenceBase<PsiElement> {
        private final String relPath;

        ImportPathReference(@NotNull PsiElement element, @NotNull TextRange rangeInElement, @NotNull String relPath) {
            super(element, rangeInElement, false);
            this.relPath = relPath;
        }

        @Override
        public @Nullable PsiElement resolve() {
            PsiFile containingFile = myElement.getContainingFile();
            if (containingFile == null) {
                return null;
            }
            Project project = containingFile.getProject();
            VirtualFile currentFile = containingFile.getVirtualFile();
            VirtualFile parentDir = (currentFile != null && currentFile.getParent() != null)
                    ? currentFile.getParent()
                    : null;
            VirtualFile baseDir = project.getBaseDir();

            VirtualFile target = resolveVirtualFile(parentDir, relPath);
            if (target == null) {
                target = resolveVirtualFile(baseDir, relPath);
            }
            if (target == null || !target.exists()) {
                return null;
            }
            if (target.isDirectory()) {
                return PsiManager.getInstance(project).findDirectory(target);
            }
            return PsiManager.getInstance(project).findFile(target);
        }

        private @Nullable VirtualFile resolveVirtualFile(@Nullable VirtualFile root, @NotNull String path) {
            if (root == null) {
                return null;
            }
            String normalized = path.replace('\\', '/');
            while (normalized.endsWith("/") && normalized.length() > 1) {
                normalized = normalized.substring(0, normalized.length() - 1);
            }
            return root.findFileByRelativePath(normalized);
        }
    }

    /** Tham chiếu symbol tới chỗ khai báo trong các file được import (ưu tiên @ public) hoặc file hiện tại. */
    private static class SymbolDeclarationReference extends PsiReferenceBase<PsiElement> {
        private final String symbolName;

        SymbolDeclarationReference(@NotNull PsiElement element, @NotNull TextRange rangeInElement, @NotNull String symbolName) {
            super(element, rangeInElement, false);
            this.symbolName = symbolName;
        }

        @Override
        public @Nullable PsiElement resolve() {
            PsiFile currentPsiFile = myElement.getContainingFile();
            if (currentPsiFile == null) {
                return null;
            }

            // 1. Tìm trong các file được import trước (ưu tiên khai báo @TenClass / @tenFunc / @TEN_BIEN)
            List<PsiFile> importedPsiFiles = collectImportedPsiFiles(currentPsiFile);
            for (PsiFile importedFile : importedPsiFiles) {
                int offset = findDeclarationOffset(importedFile.getText(), symbolName);
                if (offset >= 0) {
                    return new TargetOffsetElement(importedFile, offset, symbolName);
                }
            }

            // 2. Tìm trong chính file hiện tại
            int offsetInCurrent = findDeclarationOffset(currentPsiFile.getText(), symbolName);
            if (offsetInCurrent >= 0) {
                return new TargetOffsetElement(currentPsiFile, offsetInCurrent, symbolName);
            }

            return null;
        }

        private List<PsiFile> collectImportedPsiFiles(@NotNull PsiFile hostFile) {
            List<PsiFile> result = new ArrayList<>();
            VirtualFile vf = hostFile.getVirtualFile();
            if (vf == null || vf.getParent() == null) {
                return result;
            }
            VirtualFile baseDir = vf.getParent();
            Project project = hostFile.getProject();

            Matcher m = Pattern.compile("\\bimport\\s+\"([^\"]+)\"").matcher(hostFile.getText());
            while (m.find()) {
                String rel = m.group(1).replace('\\', '/');
                VirtualFile target = baseDir.findFileByRelativePath(rel);
                if (target != null && target.exists() && !target.isDirectory()) {
                    PsiFile psi = PsiManager.getInstance(project).findFile(target);
                    if (psi != null) {
                        result.add(psi);
                    }
                }
            }
            return result;
        }

        private int findDeclarationOffset(@NotNull String code, @NotNull String name) {
            String escaped = Pattern.quote(name);
            // Khớp: func @tenFunc(  |  class @TenClass  |  var @TEN_BIEN
            Matcher m = Pattern.compile("\\b(?:func|class|var)\\s+" + escaped + "(?=\\s*[=(:{]|\\s*$)").matcher(code);
            if (m.find()) {
                String match = m.group();
                int idx = match.lastIndexOf(name);
                return idx >= 0 ? m.start() + idx : m.start();
            }
            return -1;
        }
    }

    /** Điều hướng thẳng tới file và offset khai báo khi Ctrl + Click. */
    private static class TargetOffsetElement extends com.intellij.psi.impl.FakePsiElement {
        private final PsiFile targetFile;
        private final int offset;
        private final String name;

        TargetOffsetElement(@NotNull PsiFile targetFile, int offset, @NotNull String name) {
            this.targetFile = targetFile;
            this.offset = offset;
            this.name = name;
        }

        @Override
        public PsiElement getParent() {
            return targetFile;
        }

        @Override
        public @NotNull PsiFile getContainingFile() {
            return targetFile;
        }

        @Override
        public int getTextOffset() {
            return offset;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void navigate(boolean requestFocus) {
            com.intellij.openapi.fileEditor.OpenFileDescriptor descriptor =
                    new com.intellij.openapi.fileEditor.OpenFileDescriptor(
                            targetFile.getProject(), targetFile.getVirtualFile(), offset);
            descriptor.navigate(requestFocus);
        }

        @Override
        public boolean canNavigate() {
            return targetFile.getVirtualFile() != null && targetFile.getVirtualFile().isValid();
        }

        @Override
        public boolean canNavigateToSource() {
            return canNavigate();
        }
    }
}