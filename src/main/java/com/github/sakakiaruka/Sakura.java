package com.github.sakakiaruka;

import com.github.sakakiaruka.listeners.Cut;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Sakura extends JavaPlugin {

    public static FileConfiguration DEFAULT_CONFIG;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new Cut(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void setInstance() {
        DEFAULT_CONFIG = this.getConfig();
    }
}
