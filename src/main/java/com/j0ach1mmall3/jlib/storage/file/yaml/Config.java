package com.j0ach1mmall3.jlib.storage.file.yaml;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 29/06/2015
 */
public final class Config {
    private final JavaPlugin plugin;
    private final File path;
    private final String name;
    private final File file;

    /**
     * Constructs a new Config, shouldn't be used externally, use ConfigLoader instead
     */
    private Config(String name, String path, JavaPlugin plugin) {
        this.plugin = plugin;
        this.path = new File(path);
        this.name = name;
        this.file = new File(path, name);
    }

    /**
     * Constructs a new Config, shouldn't be used externally, use ConfigLoader instead
     */
    Config(String name, JavaPlugin plugin){
        this(name, plugin.getDataFolder().getPath(), plugin);
    }

    /**
     * Returns the Bukkit FileConfiguration instance
     * @return The Bukkit FileConfiguration instance
     * @see FileConfiguration
     */
    public FileConfiguration getConfig() {
        return YamlConfiguration.loadConfiguration(this.file);
    }

    /**
     * Saves the Config
     * @param config The Config file to save
     * @see FileConfiguration
     */
    public void saveConfig(FileConfiguration config) {
        try {
            config.save(this.file);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reloads the Config
     */
    @SuppressWarnings("deprecation")
    public void reloadConfig() {
        if(this.plugin.getResource(this.name) != null){
            FileConfiguration config = YamlConfiguration.loadConfiguration(this.file);
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(this.plugin.getResource(this.name));
            config.setDefaults(defConfig);
        }
    }

    /**
     * Saves the default Config
     */
    public void saveDefaultConfig(){
        if (!this.file.exists()) {
            this.plugin.saveResource(this.name, false);
        }
    }

    /**
     * Returns the Keys in the Config file of a Section
     * @param section The Section
     * @return The Keys
     */
    public List<String> getKeys(String section){
        List<String> keysList = new ArrayList<>();
        Set<String> keys = this.getConfig().getConfigurationSection(section).getKeys(false);
        for(String key : keys){
            keysList.add(key);
        }
        return keysList;
    }
}
