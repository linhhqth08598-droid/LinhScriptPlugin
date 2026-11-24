package me.hql.linhscript;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class LinhScriptCreateFileAction extends CreateFileFromTemplateAction implements DumbAware {

    public LinhScriptCreateFileAction() {
        // Tên hiển thị trên menu, mô tả, và cái icon bé Robot siêu ngầu của bạn
        super("LinhScript File", "Tạo file ngôn ngữ HQL mới", LinhScriptIcons.FILE);
    }

    @Override
    protected void buildDialog(@NotNull Project project, @NotNull PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
        // Cửa sổ hiện lên khi bạn bấm chọn tạo file
        builder.setTitle("Tạo File HQL Mới")
                .addKind("HQL File", LinhScriptIcons.FILE, "LinhScript File");
    }

    @Override
    protected String getActionName(PsiDirectory directory, @NotNull @NonNls String newName, @NonNls String templateName) {
        return "Tạo file HQL: " + newName;
    }
}