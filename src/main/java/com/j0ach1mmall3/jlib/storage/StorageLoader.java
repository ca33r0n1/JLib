package com.j0ach1mmall3.jlib.storage;

import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/2015
 */
public abstract class StorageLoader {
    protected final Storage storage;
    protected Map<String, Cache> caches;

    /**
     * Constructs a new StorageLoader instance, shouldn't be used externally
     */
    protected StorageLoader(Storage storage) {
        this.storage = storage;
    }

    /**
     * Returns the Storage associated with this StorageLoader
     * @return The Storage
     */
    public final Storage getStorage() {
        return this.storage;
    }

    /**
     * Returns a Cache associated with this StorageLoader
     * @param identifier The identifier of the Cache
     * @return The Cache
     */
    public final Cache getCache(String identifier) {
        return this.caches.get(identifier);
    }

    /**
     * Registers a Cache to this StorageLoader
     * @param identifier The identifier to register the Cache under
     * @param cache The Cache to register
     */
    public final void registerCache(String identifier, Cache cache) {
        this.caches.put(identifier, cache);
    }
}
