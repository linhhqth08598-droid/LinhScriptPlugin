package me.hql.linhscript;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ProcessingContext;
import me.hql.linhscript.psi.LinhScriptTypes;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinhScriptCompletionContributor extends CompletionContributor {

    private static final List<String> HQL_LIBRARIES = List.of(
            "io", "math", "net", "sys", "util", "ui", "crypto", "database",
            "time", "regex", "chart", "image", "game", "pc", "logging", "config",
            "csv", "xml", "binary", "color", "socket", "schedule", "env", "export",
            "web", "mail", "mang", "app"
    );

    public LinhScriptCompletionContributor() {
        // 0. Gợi ý riêng tên thư viện khi đang gõ câu lệnh: use ... hoặc use hql.<lib>
        extend(
                CompletionType.BASIC,
                PlatformPatterns.psiElement(),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters,
                                                  @NotNull ProcessingContext context,
                                                  @NotNull CompletionResultSet resultSet) {
                        PsiElement pos = parameters.getPosition();
                        if (!isInsideUseStatement(parameters, pos)) {
                            return;
                        }

                        resultSet.stopHere();

                        PsiElement prev = PsiTreeUtil.prevVisibleLeaf(pos);
                        if (prev != null && prev.getNode().getElementType() == LinhScriptTypes.DOT) {
                            PsiElement beforeDot = PsiTreeUtil.prevVisibleLeaf(prev);
                            if (beforeDot != null && "hql".equalsIgnoreCase(beforeDot.getText())) {
                                for (String lib : HQL_LIBRARIES) {
                                    resultSet.addElement(LookupElementBuilder.create(lib)
                                            .withTypeText("hql." + lib, true));
                                }
                            }
                            return;
                        }

                        resultSet.addElement(LookupElementBuilder.create("hql")
                                .withTypeText("LinhScript Library Root", true));
                        for (String lib : HQL_LIBRARIES) {
                            resultSet.addElement(LookupElementBuilder.create("hql." + lib)
                                    .withTypeText("LinhScript Library", true));
                        }
                    }
                }
        );

        // 1. Gợi ý thư mục & file .hql khi đang gõ bên trong chuỗi của câu lệnh import "..."
        extend(
                CompletionType.BASIC,
                PlatformPatterns.psiElement(LinhScriptTypes.STRING),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters,
                                                  @NotNull ProcessingContext context,
                                                  @NotNull CompletionResultSet resultSet) {
                        PsiFile file = parameters.getOriginalFile();
                        String text = file.getText();
                        int offset = Math.min(parameters.getOffset(), text.length());

                        int lineStart = text.lastIndexOf('\n', Math.max(0, offset - 1)) + 1;
                        String linePrefix = text.substring(lineStart, offset).trim();
                        if (!linePrefix.startsWith("import")) {
                            return;
                        }

                        int quoteIdx = linePrefix.lastIndexOf('"');
                        if (quoteIdx < 0) {
                            return;
                        }

                        String typedPath = linePrefix.substring(quoteIdx + 1);
                        Project project = file.getProject();
                        VirtualFile baseDir = project.getBaseDir();
                        VirtualFile currentFile = file.getVirtualFile();
                        VirtualFile rootDir = (currentFile != null && currentFile.getParent() != null)
                                ? currentFile.getParent()
                                : baseDir;
                        if (rootDir == null) {
                            return;
                        }

                        VirtualFile targetDir = rootDir;
                        String dirPrefix = "";
                        String filePrefix = typedPath;

                        int lastSlash = typedPath.lastIndexOf('/');
                        if (lastSlash >= 0) {
                            dirPrefix = typedPath.substring(0, lastSlash + 1);
                            filePrefix = typedPath.substring(lastSlash + 1);
                            VirtualFile subDir = rootDir.findFileByRelativePath(typedPath.substring(0, lastSlash));
                            if (subDir == null || !subDir.isDirectory()) {
                                resultSet.stopHere();
                                return;
                            }
                            targetDir = subDir;
                        }

                        List<EntryCandidate> candidates = new ArrayList<>();
                        collectEntries(targetDir, dirPrefix, candidates);

                        resultSet.stopHere();
                        CompletionResultSet scopedSet = resultSet.withPrefixMatcher(filePrefix);

                        for (EntryCandidate entry : candidates) {
                            LookupElementBuilder builder = LookupElementBuilder.create(entry.insertText())
                                    .withLookupString(entry.presentableText())
                                    .withPresentableText(entry.presentableText())
                                    .withTypeText(entry.isDir() ? "Folder" : "LinhScript", true)
                                    .withIcon(entry.isDir()
                                            ? com.intellij.icons.AllIcons.Nodes.Folder
                                            : LinhScriptIcons.FILE);

                            if (entry.isDir()) {
                                builder = builder.withInsertHandler((ctx, item) ->
                                        ctx.setLaterRunnable(() -> new CodeCompletionHandlerBase(CompletionType.BASIC)
                                                .invokeCompletion(project, ctx.getEditor(), 1, false)));
                            }
                            scopedSet.addElement(builder);
                        }
                    }
                }
        );

        // 2. Gợi ý từ khóa / hàm / thư viện & các symbol (func, class, var @) từ file đã import
        extend(
                CompletionType.BASIC,
                PlatformPatterns.psiElement(),
                new CompletionProvider<CompletionParameters>() {
                    @Override
                    protected void addCompletions(@NotNull CompletionParameters parameters,
                                                  @NotNull ProcessingContext context,
                                                  @NotNull CompletionResultSet resultSet) {
                        addImportedSymbols(parameters, resultSet);

                        for (LinhScriptVocabulary.CompletionItem item : LinhScriptVocabulary.completionItems()) {
                            LookupElementBuilder element = LookupElementBuilder.create(item.text())
                                    .withTypeText(item.category());

                            if (item.category().equals("keyword") || item.category().equals("core")) {
                                element = element.withBoldness(true);
                            }
                            resultSet.addElement(element);
                        }
                    }
                }
        );
    }

    private boolean isInsideUseStatement(@NotNull CompletionParameters parameters, @NotNull PsiElement pos) {
        PsiElement cur = pos.getParent();
        while (cur != null) {
            if (cur.getClass().getSimpleName().contains("UseStmt")) {
                return true;
            }
            cur = cur.getParent();
        }

        String text = parameters.getOriginalFile().getText();
        int offset = Math.min(parameters.getOffset(), text.length());
        int lineStart = text.lastIndexOf('\n', Math.max(0, offset - 1)) + 1;
        String linePrefix = text.substring(lineStart, offset).trim();
        if (linePrefix.startsWith("use ") || linePrefix.equals("use")) {
            return true;
        }

        PsiElement prev = PsiTreeUtil.prevVisibleLeaf(pos);
        if (prev == null) return false;
        if (prev.getNode().getElementType() == LinhScriptTypes.USE) return true;
        if (prev.getNode().getElementType() == LinhScriptTypes.DOT) {
            PsiElement beforeDot = PsiTreeUtil.prevVisibleLeaf(prev);
            return beforeDot != null && "hql".equalsIgnoreCase(beforeDot.getText());
        }
        return false;
    }

    private void collectEntries(@NotNull VirtualFile dir,
                                @NotNull String dirPrefix,
                                @NotNull List<EntryCandidate> out) {
        for (VirtualFile child : dir.getChildren()) {
            String name = child.getName();
            if (name.startsWith(".") || name.equals("build") || name.equals("out") || name.equals("gen")) {
                continue;
            }
            if (child.isDirectory()) {
                out.add(new EntryCandidate(dirPrefix + name + "/", name + "/", true));
            } else if (name.toLowerCase().endsWith(".hql")) {
                out.add(new EntryCandidate(dirPrefix + name, name, false));
            }
        }
    }

    private void addImportedSymbols(@NotNull CompletionParameters parameters,
                                    @NotNull CompletionResultSet resultSet) {
        PsiFile file = parameters.getOriginalFile();
        String text = file.getText();
        VirtualFile currentFile = file.getVirtualFile();
        if (currentFile == null || currentFile.getParent() == null) {
            return;
        }

        VirtualFile baseDir = currentFile.getParent();
        Matcher matcher = Pattern
                .compile("\\bimport\\s+\"([^\"]+)\"")
                .matcher(text);

        while (matcher.find()) {
            String relPath = matcher.group(1).replace('\\', '/');
            VirtualFile target = baseDir.findFileByRelativePath(relPath);
            if (target == null || !target.exists() || target.isDirectory()) {
                continue;
            }
            try {
                String importedCode = new String(target.contentsToByteArray(), StandardCharsets.UTF_8);

                // Lấy hàm ngoài cùng: func @tenHam(...) hoặc func tenHam(...)
                Matcher fnMatcher = Pattern
                        .compile("(?m)^\\s*func\\s+([@\\p{L}_][\\p{L}0-9_]*)\\s*\\(")
                        .matcher(importedCode);
                while (fnMatcher.find()) {
                    String fnName = fnMatcher.group(1);
                    LookupElementBuilder builder = LookupElementBuilder.create(fnName)
                            .withTypeText("import func (" + target.getName() + ")", true)
                            .withBoldness(fnName.startsWith("@"));
                    if (fnName.startsWith("@")) {
                        builder = builder.withLookupString(fnName.substring(1));
                    }
                    resultSet.addElement(builder);
                }

                // Lấy class: class @TenClass hoặc class TenClass
                Matcher clsMatcher = Pattern
                        .compile("(?m)^\\s*class\\s+([@\\p{L}_][\\p{L}0-9_]*)")
                        .matcher(importedCode);
                while (clsMatcher.find()) {
                    String clsName = clsMatcher.group(1);
                    LookupElementBuilder builder = LookupElementBuilder.create(clsName)
                            .withTypeText("import class (" + target.getName() + ")", true)
                            .withBoldness(clsName.startsWith("@"));
                    if (clsName.startsWith("@")) {
                        builder = builder.withLookupString(clsName.substring(1));
                    }
                    resultSet.addElement(builder);
                }

                // Lấy biến ngoài cùng có @: var @TEN = ...
                Matcher varMatcher = Pattern
                        .compile("(?m)^\\s*var\\s+(@[\\p{L}_][\\p{L}0-9_]*)")
                        .matcher(importedCode);
                while (varMatcher.find()) {
                    String varName = varMatcher.group(1);
                    LookupElementBuilder builder = LookupElementBuilder.create(varName)
                            .withTypeText("import var (" + target.getName() + ")", true)
                            .withLookupString(varName.substring(1));
                    resultSet.addElement(builder);
                }
            } catch (Exception ignored) {
            }
        }
    }

    private record EntryCandidate(String insertText, String presentableText, boolean isDir) {
    }
}