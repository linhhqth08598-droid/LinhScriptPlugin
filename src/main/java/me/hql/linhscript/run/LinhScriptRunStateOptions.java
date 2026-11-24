package me.hql.linhscript.run;

import com.intellij.execution.configurations.RunConfigurationOptions;
import com.intellij.openapi.components.StoredProperty;

public class LinhScriptRunStateOptions extends RunConfigurationOptions {
    private final StoredProperty<String> scriptPath =
            string("main.hql").provideDelegate(this, "scriptPath");

    public String getScriptPath() {
        return scriptPath.getValue(this);
    }

    public void setScriptPath(String value) {
        scriptPath.setValue(this, value);
    }
}