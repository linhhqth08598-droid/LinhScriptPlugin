package me.hql.linhscript;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import me.hql.linhscript.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.Color;
import java.awt.Font;
import java.util.*;

public class LinhScriptAnnotator implements Annotator {

    private static final Set<String> VALID_HQL_LIBS = Set.of(
            "io", "math", "net", "sys", "util", "ui", "crypto", "database",
            "time", "regex", "chart", "image", "game", "pc", "logging", "config",
            "csv", "xml", "binary", "color", "socket", "schedule", "env", "export",
            "web", "mail", "mang", "app"
    );

    private static final TextAttributesKey USE_LIB_NAME = TextAttributesKey.createTextAttributesKey(
            "LS_USE_LIB_NAME",
            new TextAttributes(new Color(0xC0, 0xCA, 0xF5), null, null, null, Font.PLAIN)
    );

    private static final TextAttributesKey CLASS_NAME = TextAttributesKey.createTextAttributesKey(
            "LS_CLASS_NAME",
            new TextAttributes(new Color(0x4E, 0xC9, 0xB0), null, null, null, Font.BOLD)
    );

    private static final TextAttributesKey EXTENDS_CLASS_NAME = TextAttributesKey.createTextAttributesKey(
            "LS_EXTENDS_CLASS_NAME",
            new TextAttributes(new Color(0xFF, 0xB8, 0x6C), null, null, null, Font.BOLD | Font.ITALIC)
    );

    private static final TextAttributesKey FUNC_DECL_NAME = TextAttributesKey.createTextAttributesKey(
            "LS_FUNC_DECL_NAME",
            new TextAttributes(new Color(0xFF, 0x8A, 0x80), null, null, null, Font.PLAIN)
    );

    private static final TextAttributesKey BUILTIN_FUNC_NAME = TextAttributesKey.createTextAttributesKey(
            "LS_BUILTIN_FUNC_NAME",
            new TextAttributes(new Color(0xD7, 0x7D, 0xBB), null, null, null, Font.PLAIN)
    );

    private static final TextAttributesKey FUNC_CALL_NAME = TextAttributesKey.createTextAttributesKey(
            "LS_FUNC_CALL_NAME",
            new TextAttributes(new Color(0x38, 0xBD, 0xF8), null, null, null, Font.PLAIN)
    );

    private static final TextAttributesKey CONSTANT_NAME = TextAttributesKey.createTextAttributesKey(
            "LS_CONSTANT_NAME",
            new TextAttributes(new Color(0xD1, 0x9A, 0x66), null, null, null, Font.BOLD)
    );

    private static final TextAttributesKey VAR_NAME = TextAttributesKey.createTextAttributesKey(
            "LS_VAR_NAME",
            new TextAttributes(new Color(0x94, 0xA3, 0xB8), null, null, null, Font.ITALIC)
    );

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        IElementType type = element.getNode().getElementType();

        if (type == LinhScriptTypes.USE) {
            PsiElement parent = element.getParent();
            if (parent instanceof LinhScriptUseStmt) {
                if (findFirstIdentifierElement(parent) == null) {
                    holder.newAnnotation(HighlightSeverity.ERROR, "Thiếu tên thư viện sau 'use'")
                            .range(element)
                            .create();
                }
            } else {
                holder.newAnnotation(HighlightSeverity.ERROR, "Cú pháp 'use' chưa hoàn chỉnh")
                        .range(element)
                        .create();
            }
            return;
        }

        if (type != LinhScriptTypes.IDENTIFIER) {
            return;
        }

        PsiFile file = element.getContainingFile();
        PsiElement prevElement = PsiTreeUtil.prevVisibleLeaf(element);
        PsiElement nextElement = PsiTreeUtil.nextVisibleLeaf(element);
        String text = element.getText();

        Set<String> declaredClasses = file != null ? collectDeclaredClasses(file) : Collections.emptySet();
        Set<String> declaredVars = file != null ? collectDeclaredVariables(file) : Collections.emptySet();
        Set<String> declaredFuncs = file != null ? collectDeclaredFunctions(file) : Collections.emptySet();

        if (isUseLibraryIdentifier(prevElement)) {
            if (prevElement != null && prevElement.getNode().getElementType() == LinhScriptTypes.DOT) {
                PsiElement beforeDot = PsiTreeUtil.prevVisibleLeaf(prevElement);
                if (beforeDot != null && "hql".equalsIgnoreCase(beforeDot.getText())
                        && !VALID_HQL_LIBS.contains(text.toLowerCase(Locale.ROOT))) {
                    holder.newAnnotation(HighlightSeverity.ERROR, "Không tồn tại thư viện 'hql." + text + "'")
                            .range(element)
                            .create();
                    return;
                }
            }
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element)
                    .textAttributes(USE_LIB_NAME)
                    .create();
            return;
        }

        if (prevElement != null && prevElement.getNode().getElementType() == LinhScriptTypes.EXTENDS) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element)
                    .textAttributes(EXTENDS_CLASS_NAME)
                    .create();
            return;
        }

        boolean isClassDecl = prevElement != null && prevElement.getNode().getElementType() == LinhScriptTypes.CLASS;
        boolean isNewClass = prevElement != null && prevElement.getNode().getElementType() == LinhScriptTypes.NEW;

        if (isClassDecl || isNewClass || (declaredClasses.contains(text) && !declaredVars.contains(text))) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element)
                    .textAttributes(CLASS_NAME)
                    .create();
            return;
        }

        if (prevElement != null && prevElement.getNode().getElementType() == LinhScriptTypes.FUNC) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element)
                    .textAttributes(FUNC_DECL_NAME)
                    .create();
            return;
        }

        if (nextElement != null && nextElement.getNode().getElementType() == LinhScriptTypes.L_PAREN
                && LinhScriptVocabulary.isBuiltinCallable(text)
                && !declaredVars.contains(text)) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element)
                    .textAttributes(BUILTIN_FUNC_NAME)
                    .create();
            return;
        }

        if (nextElement != null && nextElement.getNode().getElementType() == LinhScriptTypes.L_PAREN) {
            if (!declaredVars.contains(text) || declaredFuncs.contains(text)) {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .range(element)
                        .textAttributes(FUNC_CALL_NAME)
                        .create();
                return;
            }
        }

        if (LinhScriptVocabulary.isBuiltinConstant(text) && !declaredVars.contains(text)) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element)
                    .textAttributes(CONSTANT_NAME)
                    .create();
            return;
        }

        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element)
                .textAttributes(VAR_NAME)
                .create();
    }

    private boolean isUseLibraryIdentifier(@Nullable PsiElement prevElement) {
        if (prevElement == null) {
            return false;
        }
        IElementType prevType = prevElement.getNode().getElementType();
        if (prevType == LinhScriptTypes.USE) {
            return true;
        }
        if (prevType == LinhScriptTypes.DOT) {
            PsiElement beforeDot = PsiTreeUtil.prevVisibleLeaf(prevElement);
            if (beforeDot == null) {
                return false;
            }
            IElementType beforeDotType = beforeDot.getNode().getElementType();
            return beforeDotType == LinhScriptTypes.USE || beforeDotType == LinhScriptTypes.IDENTIFIER;
        }
        return false;
    }

    @Nullable
    private PsiElement findFirstIdentifierElement(@Nullable PsiElement container) {
        if (container == null) {
            return null;
        }
        for (PsiElement child : PsiTreeUtil.findChildrenOfType(container, PsiElement.class)) {
            if (child.getNode() != null && child.getNode().getElementType() == LinhScriptTypes.IDENTIFIER) {
                return child;
            }
        }
        return null;
    }

    @Nullable
    private String findFirstIdentifierText(@Nullable PsiElement container) {
        PsiElement id = findFirstIdentifierElement(container);
        return id != null ? id.getText() : null;
    }

    private Set<String> collectDeclaredClasses(PsiFile file) {
        Set<String> classes = new HashSet<>();
        for (LinhScriptClassDefStmt cls : PsiTreeUtil.findChildrenOfType(file, LinhScriptClassDefStmt.class)) {
            String name = findFirstIdentifierText(cls);
            if (name != null) {
                classes.add(name);
            }
        }
        return classes;
    }

    private Set<String> collectDeclaredFunctions(PsiFile file) {
        Set<String> funcs = new HashSet<>();
        for (LinhScriptFuncDefStmt fn : PsiTreeUtil.findChildrenOfType(file, LinhScriptFuncDefStmt.class)) {
            String name = findFirstIdentifierText(fn);
            if (name != null) {
                funcs.add(name);
            }
        }
        return funcs;
    }

    private Set<String> collectDeclaredVariables(PsiFile file) {
        Set<String> vars = new HashSet<>();
        for (LinhScriptVarAssignmentStmt stmt : PsiTreeUtil.findChildrenOfType(file, LinhScriptVarAssignmentStmt.class)) {
            String name = findFirstIdentifierText(stmt);
            if (name != null) {
                vars.add(name);
            }
        }
        for (LinhScriptClassFieldStmt field : PsiTreeUtil.findChildrenOfType(file, LinhScriptClassFieldStmt.class)) {
            String name = findFirstIdentifierText(field);
            if (name != null) {
                vars.add(name);
            }
        }
        for (LinhScriptForStmt forStmt : PsiTreeUtil.findChildrenOfType(file, LinhScriptForStmt.class)) {
            String name = findFirstIdentifierText(forStmt);
            if (name != null) {
                vars.add(name);
            }
        }
        for (LinhScriptTryStmt tryStmt : PsiTreeUtil.findChildrenOfType(file, LinhScriptTryStmt.class)) {
            for (PsiElement child : PsiTreeUtil.findChildrenOfType(tryStmt, PsiElement.class)) {
                if (child.getNode() != null && child.getNode().getElementType() == LinhScriptTypes.CATCH) {
                    PsiElement next = PsiTreeUtil.nextVisibleLeaf(child);
                    if (next != null && next.getNode().getElementType() == LinhScriptTypes.L_PAREN) {
                        PsiElement errIdent = PsiTreeUtil.nextVisibleLeaf(next);
                        if (errIdent != null && errIdent.getNode().getElementType() == LinhScriptTypes.IDENTIFIER) {
                            vars.add(errIdent.getText());
                        }
                    }
                }
            }
        }
        return vars;
    }
}