package me.hql.linhscript.run;

import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessListener;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import me.hql.linhscript.settings.LinhScriptSettingsService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class LinhScriptRunConfiguration extends RunConfigurationBase<LinhScriptRunStateOptions> {

    protected LinhScriptRunConfiguration(@NotNull Project project, @NotNull ConfigurationFactory factory, String name) {
        super(project, factory, name);
    }

    @Override
    protected @NotNull LinhScriptRunStateOptions getOptions() {
        return (LinhScriptRunStateOptions) super.getOptions();
    }

    public String getScriptPath() {
        return getOptions().getScriptPath();
    }

    public void setScriptPath(String scriptPath) {
        getOptions().setScriptPath(scriptPath);
    }

    @Override
    public @NotNull SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new LinhScriptRunSettingsEditor(getProject());
    }

    @Override
    public @Nullable RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment environment) {
        return new CommandLineState(environment) {
            @Override
            protected @NotNull ProcessHandler startProcess() throws com.intellij.execution.ExecutionException {
                String basePath = getProject().getBasePath();
                File workDir = (basePath != null && !basePath.isBlank()) ? new File(basePath) : new File(".");

                String home = LinhScriptSettingsService.getInstance(getProject()).getLinhScriptHome();
                File exeInHome = (home != null && !home.isBlank()) ? new File(home, "LinhScript.exe") : null;

                GeneralCommandLine cmd;
                if (exeInHome != null && exeInHome.isFile()) {
                    cmd = new GeneralCommandLine(exeInHome.getAbsolutePath(), relFileOrThrow());
                } else {
                    cmd = new GeneralCommandLine("cmd.exe", "/c", "LinhScript", relFileOrThrow());
                }
                cmd.setWorkDirectory(workDir);
                cmd.setCharset(java.nio.charset.StandardCharsets.UTF_8);

                OSProcessHandler handler = new OSProcessHandler(cmd);
                handler.setShouldDestroyProcessRecursively(true);
                handler.addProcessListener(new ProcessListener() {
                    @Override
                    public void onTextAvailable(@NotNull ProcessEvent event, @NotNull Key outputType) {
                        // Sau khi đã nhận dòng chào kết thúc hoặc luồng chờ input, đóng stdin an toàn nếu cần
                        if (event.getText().contains("Xin chào ")) {
                            OutputStream stdin = handler.getProcessInput();
                            if (stdin != null) {
                                try {
                                    stdin.close();
                                } catch (IOException ignored) {
                                }
                            }
                        }
                    }
                });
                handler.startNotify();
                return handler;
            }

            private String relFileOrThrow() throws com.intellij.execution.ExecutionException {
                String currentPath = getScriptPath();
                String relFile = currentPath != null ? currentPath.trim().replace('\\', '/') : "";
                while (relFile.startsWith("./")) {
                    relFile = relFile.substring(2);
                }
                while (relFile.startsWith("/")) {
                    relFile = relFile.substring(1);
                }
                if (relFile.isEmpty()) {
                    throw new com.intellij.execution.ExecutionException("Vui lòng chọn tệp .hql cần chạy.");
                }
                return relFile;
            }
        };
    }
}