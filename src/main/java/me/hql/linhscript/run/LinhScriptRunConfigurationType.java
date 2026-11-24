package me.hql.linhscript.run;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.components.BaseState;
import com.intellij.openapi.project.Project;
import me.hql.linhscript.LinhScriptIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LinhScriptRunConfigurationType extends ConfigurationTypeBase {
    public LinhScriptRunConfigurationType() {
        super("LINHSCRIPT_RUN_CONFIGURATION",
                "LinhScript",
                "Chạy tệp mã nguồn LinhScript (.hql)",
                LinhScriptIcons.FILE);
        addFactory(new ConfigurationFactory(this) {
            @Override
            public @NotNull String getId() {
                return "LinhScriptConfigurationFactory";
            }

            @Override
            public @NotNull RunConfiguration createTemplateConfiguration(@NotNull Project project) {
                return new LinhScriptRunConfiguration(project, this, "LinhScript");
            }

            @Override
            public @Nullable Class<? extends BaseState> getOptionsClass() {
                return LinhScriptRunStateOptions.class;
            }
        });
    }
}