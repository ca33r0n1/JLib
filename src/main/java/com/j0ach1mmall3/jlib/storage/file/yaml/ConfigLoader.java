package com.j0ach1mmall3.jlib.storage.file.yaml;

import com.j0ach1mmall3.jlib.methods.General;
import com.j0ach1mmall3.jlib.storage.StorageLoader;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 21/08/15
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
        this(plugin, name, plugin.getDataFolder().getPath() + File.separator + name);
    }

    /**
     * Constructs a new ConfigLoader, use this by extending the ConfigLoader
     * @param plugin The JavaPlugin associated with the Config file
     * @param sourcePath The Source Path of the Config file
     * @param destinationPath The Destination Path of the Config file
     * @see Config
     */
    protected ConfigLoader(JavaPlugin plugin, String sourcePath, String destinationPath) {
        super(new Config(plugin, sourcePath, destinationPath));
        this.customConfig = (Config) this.storage;
        this.customConfig.saveDefaultConfig();
        this.config = this.customConfig.getConfig();
        if(this.config.getString("DoNotChange") == null) {
            General.sendColoredMessage(this.storage.getPlugin(), "Found outdated config " + destinationPath + ". Creating a backup and then saving the new one!", ChatColor.RED);
            this.createBackup(destinationPath, 0);
        }
        else this.checkOutdated(destinationPath);
        this.config = this.customConfig.getConfig();
    }

    /**
     * Checks whether the current Config file is outdated
     * @param path The path of the Config file
     */
    private void checkOutdated(String path) {
        String doNotChange = this.config.getString("DoNotChange");
        File file = new File(path);
        File temp = new File(path + "_temp.yml");
        file.renameTo(temp);
        this.customConfig.saveDefaultConfig();
        this.config = this.customConfig.getConfig();
        File old = new File(path);
        old.delete();
        temp.renameTo(file);
        if(!this.config.getString("DoNotChange").equals(doNotChange)) {
            General.sendColoredMessage(this.storage.getPlugin(), "Found outdated config " + path + ". Creating a backup and then saving the new one!", ChatColor.RED);
            this.createBackup(path, 0);
        }
    }

    /**
     * Constructs a backup of an existing Config file
     * @param path The path of the Config file
     * @param i The depth level
     */
    private void createBackup(String path, int i) {
        File file = new File(path);
        File old = new File(path + "_old" + i + ".yml");
        if (old.exists()) this.createBackup(path, ++i);
        else {
            file.renameTo(old);
            this.customConfig.saveDefaultConfig();
        }
    }

    /**
     * Returns the Config associated with this ConfigLoader
     * @return The Config
     */
    public final Config getCustomConfig() {
        return this.customConfig;
    }
}
