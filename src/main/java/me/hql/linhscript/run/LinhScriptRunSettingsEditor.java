package me.hql.linhscript.run;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.components.JBLabel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class LinhScriptRunSettingsEditor extends SettingsEditor<LinhScriptRunConfiguration> {
    private final Project project;
    private final JPanel panel = new JPanel(new GridBagLayout());
    private final TextFieldWithBrowseButton scriptField = new TextFieldWithBrowseButton();

    public LinhScriptRunSettingsEditor(Project project) {
        this.project = project;

        // Tắt macro/path-variable popup mặc định của IntelliJ trên text field
        scriptField.getTextField().putClientProperty("JTextField.variant", null);

        scriptField.addActionListener(e -> chooseHqlFileFromProjectRoot());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panel.add(new JBLabel("Script file (.hql):"), gbc);

        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        panel.add(scriptField, gbc);
    }

    private void chooseHqlFileFromProjectRoot() {
        String basePath = project.getBasePath();
        VirtualFile rootDir = basePath != null
                ? LocalFileSystem.getInstance().refreshAndFindFileByPath(basePath)
                : null;

        FileChooserDescriptor descriptor = new FileChooserDescriptor(true, false, false, false, false, false) {
            @Override
            public boolean isFileVisible(VirtualFile file, boolean showHiddenFiles) {
                if (file.isDirectory()) {
                    String name = file.getName();
                    return !name.startsWith(".") && !name.equals("build") && !name.equals("out");
                }
                return "hql".equalsIgnoreCase(file.getExtension());
            }

            @Override
            public boolean isFileSelectable(VirtualFile file) {
                return !file.isDirectory() && "hql".equalsIgnoreCase(file.getExtension());
            }
        };
        descriptor.setTitle("Chọn tệp LinhScript (.hql)");
        descriptor.setDescription("Chọn tệp .hql tính từ thư mục gốc của dự án (vd: Test.hql hoặc folder/file.hql)");
        if (rootDir != null) {
            descriptor.setRoots(rootDir);
        }

        VirtualFile selected = FileChooser.chooseFile(descriptor, project, rootDir);
        if (selected != null) {
            scriptField.setText(toRelativeFromProjectRoot(selected.getPath(), basePath));
        }
    }

    private String toRelativeFromProjectRoot(String selectedPath, String basePath) {
        String normSelected = selectedPath.replace('\\', '/');
        if (basePath == null || basePath.isBlank()) {
            return normSelected;
        }
        String normRoot = basePath.replace('\\', '/');
        if (!normRoot.endsWith("/")) {
            normRoot += "/";
        }
        if (normSelected.startsWith(normRoot)) {
            return normSelected.substring(normRoot.length());
        }
        return normSelected;
    }

    @Override
    protected void resetEditorFrom(@NotNull LinhScriptRunConfiguration s) {
        String path = s.getScriptPath();
        scriptField.setText(path != null ? path : "");
    }

    @Override
    protected void applyEditorTo(@NotNull LinhScriptRunConfiguration s) {
        String raw = scriptField.getText().trim().replace('\\', '/');
        while (raw.startsWith("./")) {
            raw = raw.substring(2);
        }
        while (raw.startsWith("/")) {
            raw = raw.substring(1);
        }
        s.setScriptPath(raw);
    }

    @Override
    protected @NotNull JComponent createEditor() {
        return panel;
    }
}