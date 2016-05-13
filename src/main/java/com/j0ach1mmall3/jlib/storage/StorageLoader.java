package com.j0ach1mmall3.jlib.storage;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/15
 */
public abstract class StorageLoader<S extends Storage<P>, P extends JavaPlugin> {
    protected final S storage;

    /**
     * Constructs a new StorageLoader instance, shouldn't be used externally
     * @param storage The Storage associated with this StorageLoader
     */
    protected StorageLoader(S storage) {
        this.storage = storage;
    }

    /**
     * Returns the Storage associated with this StorageLoader
     * @return The Storage
     */
    public final S getStorage() {
        return this.storage;
    }

    /**
     * Disconnects from the Storage
     */
    public void disconnect() {
        this.storage.disconnect();
    }
}
