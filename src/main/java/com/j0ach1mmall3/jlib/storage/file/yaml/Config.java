package com.j0ach1mmall3.jlib.storage.file.yaml;

import com.google.common.collect.Lists;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 29/06/2015
 */
public final class Config {
    private final JavaPlugin plugin;
    private final String sourcePath;
    private final File file;

    /**
     * Constructs a new Config, shouldn't be used externally, use ConfigLoader instead
     */
    Config(JavaPlugin plugin, String sourcePath, String targetPath) {
        this.plugin = plugin;
        this.sourcePath = sourcePath;
        this.file = new File(targetPath);
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
    public void reloadConfig() {
        if(this.plugin.getResource(this.sourcePath) != null){
            FileConfiguration config = YamlConfiguration.loadConfiguration(this.file);
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(this.plugin.getResource(this.sourcePath)));
            config.setDefaults(defConfig);
        }
    }

    /**
     * Saves the default Config
     */
    public void saveDefaultConfig(){
        if (!this.file.exists()) {
            try {
                File parent = new File(this.file.getParent());
                if(!parent.exists()) parent.mkdirs();
                InputStream in = this.plugin.getResource(this.sourcePath);
                OutputStream out = new FileOutputStream(this.file);
                byte[] buf = new byte[1024];
                int len;
                while((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns the Keys in the Config file of a Section
     * @param section The Section
     * @return The Keys
     */
    public List<String> getKeys(String section){
        ConfigurationSection cfgsection = this.getConfig().getConfigurationSection(section);
        if(cfgsection == null ) return new ArrayList<>();
        return Lists.newArrayList(cfgsection.getKeys(false));
    }
}
