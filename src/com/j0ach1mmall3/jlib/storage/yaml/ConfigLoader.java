package com.j0ach1mmall3.jlib.storage.yaml;

import com.j0ach1mmall3.jlib.methods.General;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

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
        if(config.getString("DoNotChange") == null) {
            createBackup(name);
        } else {
           checkOutdated(name);
        }
        this.config = customConfig.getConfig();
    }

    private void checkOutdated(String name) {
        String doNotChange = config.getString("DoNotChange");
        File file = new File(plugin.getDataFolder(), name);
        file.renameTo(new File(plugin.getDataFolder(), name.split("\\.")[0] + "_temp" + "." + name.split("\\.")[1]));
        File temp = new File(plugin.getDataFolder(), name.split("\\.")[0] + "_temp" + "." + name.split("\\.")[1]);
        customConfig.saveDefaultConfig();
        this.config = customConfig.getConfig();
        File old = new File(plugin.getDataFolder(), name);
        old.delete();
        temp.renameTo(new File(plugin.getDataFolder(), name));
        if(!config.getString("DoNotChange").equals(doNotChange)) createBackup(name);
    }

    private void createBackup(String name) {
        General.sendColoredMessage(plugin, "Found outdated config " + name + ". Creating a backup and then saving the new one!", ChatColor.RED);
        File file = new File(plugin.getDataFolder(), name);
        File old = new File(plugin.getDataFolder(), name.split("\\.")[0] + "_old" + "." + name.split("\\.")[1]);
        if(old.exists()) {
            General.sendColoredMessage(plugin, "Old config (" + name.split("\\.")[0] + "_old" + "." + name.split("\\.")[1] + " already exists! Aborting the backup!", ChatColor.RED);
            return;
        }
        file.renameTo(new File(plugin.getDataFolder(), name.split("\\.")[0] + "_old" + "." + name.split("\\.")[1]));
        customConfig.saveDefaultConfig();
    }

    protected int setValue(int val, String path) {
        return config.getInt((path.substring(0, 1).toUpperCase() + path.substring(1)).replace("\\_", "\\."));
    }

    protected boolean setValue(boolean val, String path) {
        return config.getBoolean((path.substring(0, 1).toUpperCase() + path.substring(1)).replace("\\_", "\\."));
    }

    protected String setValue(String i, String path) {
        return config.getString((path.substring(0, 1).toUpperCase() + path.substring(1)).replace("\\_", "\\."));
    }

    protected List<?> setValue(List i, String path) {
        return config.getList((path.substring(0, 1).toUpperCase() + path.substring(1)).replace("\\_", "\\."));
    }

    protected double setValue(double i, String path) {
        return config.getDouble((path.substring(0, 1).toUpperCase() + path.substring(1)).replace("\\_", "\\."));
    }

    protected long setValue(long i, String path) {
        return config.getLong((path.substring(0, 1).toUpperCase() + path.substring(1)).replace("\\_", "\\."));
    }
}
