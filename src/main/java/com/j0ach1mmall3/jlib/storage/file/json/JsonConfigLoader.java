package com.j0ach1mmall3.jlib.storage.file.json;

import com.j0ach1mmall3.jlib.logging.JLogger;
import com.j0ach1mmall3.jlib.storage.StorageLoader;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/15
 */
public abstract class JsonConfigLoader extends StorageLoader {
    protected final JsonConfig customConfig;
    protected JsonConfiguration config;

    /**
     * Constructs a new JsonConfigLoader, use this by extending the JsonConfigLoader
     * @param name The name of the Json file
     * @param plugin The JavaPlugin associated with the Json file
     * @param reference The JsonConfiguration reference to use with this JsonConfigLoader
     */
    protected JsonConfigLoader(JavaPlugin plugin, String name, Class<? extends JsonConfiguration> reference) {
        this(plugin, name, plugin.getDataFolder().getPath() + File.separator + name, reference);
    }

    /**
     * Constructs a new JsonConfigLoader, use this by extending the JsonConfigLoader
     * @param plugin The JavaPlugin associated with the JsonConfig file
     * @param sourcePath The Source Path of the JsonConfig file
     * @param destinationPath The Destination Path of the JsonConfig file
     * @param reference The JsonConfiguration reference to use with this JsonConfigLoader
     */
    protected JsonConfigLoader(JavaPlugin plugin, String sourcePath, String destinationPath, Class<? extends JsonConfiguration> reference) {
        super(new JsonConfig(plugin, sourcePath, destinationPath));
        this.customConfig = (JsonConfig) this.storage;
        this.customConfig.saveDefaultConfig();
        this.reload(reference);
        if(this.config.getDoNotChange() == null) {
            this.storage.getjLogger().log(ChatColor.RED + "Found outdated jsonconfig " + destinationPath + ". Creating a backup and then saving the new one!", JLogger.LogLevel.NORMAL);
            this.createBackup(destinationPath, 0);
        }
        else this.checkOutdated(destinationPath);
        this.reload(reference);
    }

    /**
     * Checks whether the current JsonConfig file is outdated
     * @param path The path of the JsonConfig file
     */
    private void checkOutdated(String path) {
        String doNotChange = this.config.getDoNotChange();
        File file = new File(path);
        File temp = new File(path + "_temp.json");
        file.renameTo(temp);
        this.customConfig.saveDefaultConfig();
        this.config = this.customConfig.getConfig(this.config.getClass());
        File old = new File(path);
        old.delete();
        temp.renameTo(file);
        if(!this.config.getDoNotChange().equals(doNotChange)) {
            this.storage.getjLogger().log(ChatColor.RED + "Found outdated jsonconfig " + path + ". Creating a backup and then saving the new one!", JLogger.LogLevel.NORMAL);
            this.createBackup(path, 0);
        }
    }

    /**
     * Constructs a backup of an existing JsonConfig file
     * @param path The path of the JsonConfig file
     * @param i The depth level
     */
    private void createBackup(String path, int i) {
        File file = new File(path);
        File old = new File(path + "_old" + i + ".json");
        if (old.exists()) this.createBackup(path, ++i);
        else {
            file.renameTo(old);
            this.customConfig.saveDefaultConfig();
        }
    }

    /**
     * Returns the JsonConfig associated with this ConfigLoader
     * @return The JsonConfig
     */
    public final JsonConfig getCustomConfig() {
        return this.customConfig;
    }

    /**
     * Reloads the JsonConfig
     * @param reference The reference Class
     */
    protected final void reload(Class<? extends JsonConfiguration> reference) {
        this.config = this.customConfig.getConfig(reference);
    }
}
