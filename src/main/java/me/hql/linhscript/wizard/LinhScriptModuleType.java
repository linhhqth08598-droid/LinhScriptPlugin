package me.hql.linhscript.wizard;

import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import me.hql.linhscript.LinhScriptIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.Icon;

public class LinhScriptModuleType extends ModuleType<LinhScriptModuleBuilder> {
    public static final String ID = "LINHSCRIPT_MODULE_TYPE";

    public LinhScriptModuleType() {
        super(ID);
    }

    public static LinhScriptModuleType getInstance() {
        return (LinhScriptModuleType) ModuleTypeManager.getInstance().findByID(ID);
    }

    @Override
    public @NotNull LinhScriptModuleBuilder createModuleBuilder() {
        return new LinhScriptModuleBuilder();
    }

    @Override
    public @NotNull String getName() {
        return "LinhScript";
    }

    @Override
    public @NotNull String getDescription() {
        return "Tạo dự án mới cho ngôn ngữ lập trình LinhScript (.hql)";
    }

    @Override
    public Icon getNodeIcon(boolean isOpened) {
        return LinhScriptIcons.FILE;
    }
}