package com.j0ach1mmall3.jlib.storage.file.yaml;

import com.j0ach1mmall3.jlib.logging.JLogger;
import com.j0ach1mmall3.jlib.storage.StorageLoader;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 21/08/15
 */
public abstract class ConfigLoader<P extends JavaPlugin> extends StorageLoader<Config<P>, P> {
    protected final Config<P> customConfig;
    protected FileConfiguration config;

    /**
     * Constructs a new ConfigLoader, use this by extending the ConfigLoader
     *
     * @param name   The name of the Config file
     * @param plugin The JavaPlugin associated with the Config file
     */
    protected ConfigLoader(String name, P plugin) {
        this(plugin, name, plugin.getDataFolder().getPath() + File.separator + name);
    }

    /**
     * Constructs a new ConfigLoader, use this by extending the ConfigLoader
     *
     * @param plugin          The JavaPlugin associated with the Config file
     * @param sourcePath      The Source Path of the Config file
     * @param destinationPath The Destination Path of the Config file
     */
    protected ConfigLoader(P plugin, String sourcePath, String destinationPath) {
        super(new Config(plugin, sourcePath, destinationPath));
        this.customConfig = this.storage;
        this.storage.saveDefaultConfig();
        this.config = this.storage.getConfig();
        if (this.config.getString("DoNotChange") == null) {
            this.storage.getjLogger().log(ChatColor.RED + "Found outdated config " + destinationPath + ". Creating a backup and then saving the new one!", JLogger.LogLevel.NORMAL);
            this.createBackup(destinationPath, 0);
        } else this.checkOutdated(destinationPath);
        this.config = this.storage.getConfig();
    }

    /**
     * Checks whether the current Config file is outdated
     *
     * @param path The path of the Config file
     */
    private void checkOutdated(String path) {
        String doNotChange = this.config.getString("DoNotChange");
        File file = new File(path);
        File temp = new File(path + "_temp.yml");
        file.renameTo(temp);
        this.storage.saveDefaultConfig();
        this.config = this.storage.getConfig();
        File old = new File(path);
        old.delete();
        temp.renameTo(file);
        if (!this.config.getString("DoNotChange").equals(doNotChange)) {
            this.storage.getjLogger().log(ChatColor.RED + "Found outdated config " + path + ". Creating a backup and then saving the new one!", JLogger.LogLevel.NORMAL);
            this.createBackup(path, 0);
        }
    }

    /**
     * Constructs a backup of an existing Config file
     *
     * @param path The path of the Config file
     * @param i    The depth level
     */
    private void createBackup(String path, int i) {
        File file = new File(path);
        File old = new File(path + "_old" + i + ".yml");
        if (old.exists()) this.createBackup(path, ++i);
        else {
            file.renameTo(old);
            this.storage.saveDefaultConfig();
        }
    }

    /**
     * Returns the Config associated with this ConfigLoader
     *
     * @return The Config
     * @deprecated {@link StorageLoader#getStorage()}
     */
    @Deprecated
    public final Config getCustomConfig() {
        return this.storage;
    }
}
