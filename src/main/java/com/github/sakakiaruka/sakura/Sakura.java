package com.github.sakakiaruka.sakura;

import com.github.sakakiaruka.sakura.sakura.listeners.Cut;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Sakura extends JavaPlugin {

    public static FileConfiguration DEFAULT_CONFIG;
    public static Sakura instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        setInstance();
        getServer().getPluginManager().registerEvents(new Cut(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

//    private void setInstance() {
//        DEFAULT_CONFIG = this.getConfig();
//    }
    private void setInstance() {
        instance = this;
    }
}
