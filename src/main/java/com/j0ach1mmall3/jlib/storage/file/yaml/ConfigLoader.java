package com.j0ach1mmall3.jlib.storage.file.yaml;

import com.j0ach1mmall3.jlib.methods.General;
import com.j0ach1mmall3.jlib.storage.StorageLoader;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 21/08/2015
 */
public abstract class ConfigLoader extends StorageLoader {
    protected final Config customConfig;
    protected FileConfiguration config;

    /**
     * Constructs a new ConfigLoader, use this by extending the ConfigLoader
     * @param name The name of the Config file
     * @param plugin The JavaPlugin associated with the Config file
     * @see Config
     */
    protected ConfigLoader(String name, JavaPlugin plugin) {
        super(plugin, name);
        this.customConfig = new Config(name, plugin);
        this.customConfig.saveDefaultConfig();
        this.config = this.customConfig.getConfig();
        if(this.config.getString("DoNotChange") == null) {
            this.createBackup(name);
        } else {
            this.checkOutdated(name);
        }
        this.config = this.customConfig.getConfig();
    }

    /**
     * Checks whether the current Config file is outdated
     * @param name The name of the Config file
     */
    private void checkOutdated(String name) {
        String doNotChange = this.config.getString("DoNotChange");
        File file = new File(this.plugin.getDataFolder(), name);
        file.renameTo(new File(this.plugin.getDataFolder(), name.split("\\.")[0] + "_temp" + "." + name.split("\\.")[1]));
        File temp = new File(this.plugin.getDataFolder(), name.split("\\.")[0] + "_temp" + "." + name.split("\\.")[1]);
        this.customConfig.saveDefaultConfig();
        this.config = this.customConfig.getConfig();
        File old = new File(this.plugin.getDataFolder(), name);
        old.delete();
        temp.renameTo(new File(this.plugin.getDataFolder(), name));
        if(!this.config.getString("DoNotChange").equals(doNotChange)) this.createBackup(name);
    }

    /**
     * Creates a backup of an existing Config file
     * @param name The name of the Config file
     */
    private void createBackup(String name) {
        General.sendColoredMessage(this.plugin, "Found outdated config " + name + ". Creating a backup and then saving the new one!", ChatColor.RED);
        File file = new File(this.plugin.getDataFolder(), name);
        File old = new File(this.plugin.getDataFolder(), name.split("\\.")[0] + "_old" + "." + name.split("\\.")[1]);
        if(old.exists()) {
            General.sendColoredMessage(this.plugin, "Old config (" + name.split("\\.")[0] + "_old" + "." + name.split("\\.")[1] + " already exists! Aborting the backup!", ChatColor.RED);
            return;
        }
        file.renameTo(new File(this.plugin.getDataFolder(), name.split("\\.")[0] + "_old" + "." + name.split("\\.")[1]));
        this.customConfig.saveDefaultConfig();
    }
}
