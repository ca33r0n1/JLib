package com.j0ach1mmall3.jlib.storage;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/2015
 */
public abstract class StorageLoader {
    protected final JavaPlugin plugin;
    protected final String storageName;

    /**
     * Constructs a new StorageLoader instance, shouldn't be used externally
     */
    protected StorageLoader(JavaPlugin plugin, String storageName) {
        this.plugin = plugin;
        this.storageName = storageName;
    }

    /**
     * Returns the name of the Storage File/Database associated with this Storage
     * @return The name of the Storage File/Database
     */
    public String getStorageName() {
        return storageName;
    }
}
