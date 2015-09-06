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
    private File path;
    private String name;
    private File file;

    public Config(String name, JavaPlugin plugin){
        this.plugin = plugin;
        this.path = plugin.getDataFolder();
        this.name = name;
        this.file = new File(path, name);
    }

    public Config(String name, String path, JavaPlugin plugin) {
        this.plugin = plugin;
        this.path = new File(path);
        this.plugin = plugin;
        this.file = new File(path, name);
    }

    public FileConfiguration getConfig() {
        return YamlConfiguration.loadConfiguration(file);
    }

    public void saveConfig(FileConfiguration config) {
        try {
            config.save(file);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    public void reloadConfig() {
        if(plugin.getResource(name) != null){
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(plugin.getResource(name));
            config.setDefaults(defConfig);
        }
    }

    public void saveDefaultConfig(){
        if (!file.exists()) {
            plugin.saveResource(name, false);
        }
    }

    public List<String> getKeys(String section){
        List<String> keysList = new ArrayList<>();
        Set<String> keys = getConfig().getConfigurationSection(section).getKeys(false);
        for(String key : keys){
            keysList.add(key);
        }
        return keysList;
    }
}
