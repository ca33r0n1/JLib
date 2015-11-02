package com.j0ach1mmall3.jlib.storage.yaml;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by j0ach1mmall3 on 8:55 29/06/2015 using IntelliJ IDEA.
 */
public class Config {
    private JavaPlugin plugin;
    private final File path;
    private String name;
    private final File file;

    public Config(String name, JavaPlugin plugin){
        this.plugin = plugin;
        this.path = plugin.getDataFolder();
        this.name = name;
        this.file = new File(this.path, name);
    }

    public Config(String name, String path, JavaPlugin plugin) {
        this.plugin = plugin;
        this.path = new File(path);
        this.plugin = plugin;
        this.file = new File(path, name);
    }

    public FileConfiguration getConfig() {
        return YamlConfiguration.loadConfiguration(this.file);
    }

    public void saveConfig(FileConfiguration config) {
        try {
            config.save(this.file);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    public void reloadConfig() {
        if(this.plugin.getResource(this.name) != null){
            FileConfiguration config = YamlConfiguration.loadConfiguration(this.file);
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(this.plugin.getResource(this.name));
            config.setDefaults(defConfig);
        }
    }

    public void saveDefaultConfig(){
        if (!this.file.exists()) {
            this.plugin.saveResource(this.name, false);
        }
    }

    public List<String> getKeys(String section){
        List<String> keysList = new ArrayList<>();
        Set<String> keys = this.getConfig().getConfigurationSection(section).getKeys(false);
        for(String key : keys){
            keysList.add(key);
        }
        return keysList;
    }
}
