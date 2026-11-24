package me.hql.linhscript.wizard;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBLabel;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class LinhScriptPathStep extends ModuleWizardStep {
    private final LinhScriptModuleBuilder builder;
    private final JPanel panel = new JPanel(new BorderLayout(0, 8));
    private final TextFieldWithBrowseButton pathField = new TextFieldWithBrowseButton();
    private final JBLabel statusLabel = new JBLabel();

    public LinhScriptPathStep(LinhScriptModuleBuilder builder) {
        this.builder = builder;

        String defaultDir = "C:\\Users\\User\\AppData\\Local\\Programs\\LinhScript";
        if (!new File(defaultDir).exists()) {
            String fallback = System.getProperty("user.home") + "\\AppData\\Local\\Programs\\LinhScript";
            if (new File(fallback).exists()) {
                defaultDir = fallback;
            }
        }
        pathField.setText(defaultDir);
        pathField.addBrowseFolderListener(
                new TextBrowseFolderListener(FileChooserDescriptorFactory.createSingleFolderDescriptor())
        );

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        formPanel.add(new JBLabel("LinhScript Path:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        formPanel.add(pathField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        formPanel.add(statusLabel, gbc);

        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        panel.add(formPanel, BorderLayout.NORTH);

        pathField.getTextField().getDocument().addDocumentListener((SimpleDocumentListener) this::checkPath);
        checkPath();
    }

    private void checkPath() {
        String dirPath = pathField.getText().trim();
        File dir = new File(dirPath);
        File exe = new File(dir, "LinhScript.exe");
        if (exe.isFile()) {
            statusLabel.setForeground(JBColor.GREEN);
            statusLabel.setText("Đã tìm thấy LinhScript.exe");
        } else if (dir.isDirectory()) {
            statusLabel.setForeground(JBColor.ORANGE);
            statusLabel.setText("Thư mục tồn tại nhưng chưa thấy LinhScript.exe — bạn có thể chọn lại thư mục cài đặt.");
        } else {
            statusLabel.setForeground(JBColor.RED);
            statusLabel.setText("Chưa tìm thấy thư mục LinhScript — vui lòng thêm/chọn đường dẫn cài đặt.");
        }
    }

    @Override
    public JComponent getComponent() {
        return panel;
    }

    @Override
    public void updateDataModel() {
        builder.setLinhScriptPath(pathField.getText().trim());
    }

    @FunctionalInterface
    private interface SimpleDocumentListener extends javax.swing.event.DocumentListener {
        void onChange();
        @Override default void insertUpdate(javax.swing.event.DocumentEvent e) { onChange(); }
        @Override default void removeUpdate(javax.swing.event.DocumentEvent e) { onChange(); }
        @Override default void changedUpdate(javax.swing.event.DocumentEvent e) { onChange(); }
    }
}