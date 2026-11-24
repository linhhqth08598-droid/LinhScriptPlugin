package me.hql.linhscript.settings;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
        name = "LinhScriptProjectSettings",
        storages = @Storage("linhscript.xml")
)
@Service(Service.Level.PROJECT)
public final class LinhScriptSettingsService implements PersistentStateComponent<LinhScriptSettingsService.State> {

    public static class State {
        public String linhScriptHome = "C:\\Users\\User\\AppData\\Local\\Programs\\LinhScript";
    }

    private State state = new State();

    public static LinhScriptSettingsService getInstance(@NotNull Project project) {
        return project.getService(LinhScriptSettingsService.class);
    }

    public String getLinhScriptHome() {
        return state.linhScriptHome;
    }

    public void setLinhScriptHome(String linhScriptHome) {
        this.state.linhScriptHome = linhScriptHome;
    }

    @Override
    public @Nullable State getState() {
        return state;
    }

    @Override
    public void loadState(@NotNull State state) {
        this.state = state;
    }
}