package me.hql.linhscript.wizard;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import me.hql.linhscript.settings.LinhScriptSettingsService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class LinhScriptModuleBuilder extends ModuleBuilder {
    private String linhScriptPath = "C:\\Users\\User\\AppData\\Local\\Programs\\LinhScript";

    public void setLinhScriptPath(String linhScriptPath) {
        this.linhScriptPath = linhScriptPath;
    }

    public String getLinhScriptPath() {
        return linhScriptPath;
    }

    @Override
    public ModuleType<?> getModuleType() {
        return LinhScriptModuleType.getInstance();
    }

    @Override
    public @Nullable ModuleWizardStep getCustomOptionsStep(WizardContext context, Disposable parentDisposable) {
        return new LinhScriptPathStep(this);
    }

    @Override
    public void setupRootModel(@NotNull ModifiableRootModel modifiableRootModel) {
        // Lưu đường dẫn LinhScript đã cấu hình
        LinhScriptSettingsService.getInstance(modifiableRootModel.getProject())
                .setLinhScriptHome(linhScriptPath);

        String contentEntryPath = getContentEntryPath();
        if (contentEntryPath == null) {
            return;
        }

        VirtualFile rootDir = LocalFileSystem.getInstance().refreshAndFindFileByPath(contentEntryPath);
        if (rootDir != null) {
            modifiableRootModel.addContentEntry(rootDir);
            Path starterFile = Path.of(rootDir.getPath(), "main.hql");
            if (!Files.exists(starterFile)) {
                String sampleCode = """
                        // Chương trình LinhScript mới
                        use hql.io;
                        
                        print("Hello LinhScript!");
                        var ten = input("Nhập tên của bạn: ");
                        print("Xin chào " + ten + "!");
                        """;
                try {
                    Files.writeString(starterFile, sampleCode, StandardCharsets.UTF_8);
                    rootDir.refresh(false, true);
                    StartupManager.getInstance(modifiableRootModel.getProject()).runWhenProjectIsInitialized(() -> {
                        VirtualFile vf = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(starterFile.toFile());
                        if (vf != null) {
                            com.intellij.openapi.fileEditor.FileEditorManager.getInstance(modifiableRootModel.getProject())
                                    .openFile(vf, true);
                        }
                    });
                } catch (IOException ignored) {
                }
            }
        }
    }
}