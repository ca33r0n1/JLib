package com.j0ach1mmall3.jlib.storage.yaml;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by j0ach1mmall3 on 13:46 21/08/2015 using IntelliJ IDEA.
 */
public class ConfigLoader {
    protected JavaPlugin plugin;
    protected Config customConfig;
    protected FileConfiguration config;
    public ConfigLoader(String name, JavaPlugin plugin) {
        this.plugin = plugin;
        this.customConfig = new Config(name, plugin);
        customConfig.saveDefaultConfig();
        this.config = customConfig.getConfig();
    }
}
