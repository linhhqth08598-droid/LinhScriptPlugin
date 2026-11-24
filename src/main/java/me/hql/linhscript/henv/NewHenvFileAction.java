package me.hql.linhscript.henv;

import com.intellij.ide.IdeView;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.InputValidator;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Chuột phải thư mục → New → "Tệp .henv mẫu (HQL Env)".
 * Chọn (hoặc gõ) tên tệp, plugin tạo tệp có dữ liệu mẫu rồi mở ra luôn. Không bao giờ ghi đè tệp đã có.
 */
public class NewHenvFileAction extends AnAction implements DumbAware {

    private static final String[] NAMES = {
            ".henv", ".henv.local", ".henv.development", ".henv.production", ".henv.test", ".henv.example"
    };

    @NotNull
    @Override
    public ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        IdeView view = e.getData(LangDataKeys.IDE_VIEW);
        boolean ok = e.getProject() != null && view != null && view.getDirectories().length > 0;
        e.getPresentation().setEnabledAndVisible(ok);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        IdeView view = e.getData(LangDataKeys.IDE_VIEW);
        if (project == null || view == null) return;
        PsiDirectory dir = view.getOrChooseDirectory();
        if (dir == null) return;

        String picked = Messages.showEditableChooseDialog(
                "Chọn hoặc gõ tên tệp cần tạo:",
                "Tạo tệp .henv mẫu",
                HenvIcons.FILE,
                NAMES,
                NAMES[0],
                new InputValidator() {
                    @Override public boolean checkInput(String s) {
                        return s != null && !s.trim().isEmpty() && !s.contains("/") && !s.contains("\\");
                    }
                    @Override public boolean canClose(String s) { return checkInput(s); }
                });
        if (picked == null) return;
        final String name = picked.trim();

        VirtualFile folder = dir.getVirtualFile();
        VirtualFile existing = folder.findChild(name);
        if (existing != null) {
            Messages.showInfoMessage(project,
                    "Tệp \"" + name + "\" đã có sẵn nên mình không ghi đè — mở nó ra cho bạn xem.",
                    "Tệp đã tồn tại");
            FileEditorManager.getInstance(project).openFile(existing, true);
            return;
        }

        final VirtualFile[] created = {null};
        final IOException[] error = {null};
        ApplicationManager.getApplication().runWriteAction(() -> {
            try {
                VirtualFile vf = folder.createChildData(this, name);
                vf.setBinaryContent(HenvTemplates.build(name).getBytes(StandardCharsets.UTF_8));
                created[0] = vf;
            } catch (IOException ex) {
                error[0] = ex;
            }
        });

        if (error[0] != null) {
            Messages.showErrorDialog(project, "Không tạo được tệp: " + error[0].getMessage(), "Lỗi tạo .henv");
            return;
        }
        FileEditorManager.getInstance(project).openFile(created[0], true);
    }
}
