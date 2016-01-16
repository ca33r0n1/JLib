package com.j0ach1mmall3.jlib.storage;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/2015
 */
public abstract class StorageLoader {
    protected final Storage storage;

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
    public Storage getStorage() {
        return this.storage;
    }
}
