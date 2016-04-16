package com.j0ach1mmall3.jlib.logging;

import com.j0ach1mmall3.jlib.storage.StorageAction;
import com.j0ach1mmall3.jlib.storage.StorageLoader;
import com.j0ach1mmall3.jlib.storage.file.yaml.ConfigLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 15/04/2016
 */
public final class DebugInfo {
    private final StorageLoader storageLoader;
    private final ConfigLoader[] configs;

    /**
     * Constructs a new DebugInfo
     * @param storageLoader The StorageLoader
     * @param configs The Config files
     */
    public DebugInfo(StorageLoader storageLoader, ConfigLoader[] configs) {
        this.storageLoader = storageLoader;
        this.configs = configs;
    }

    /**
     * Returns the StorageActions
     * @return The StorageActions
     */
    public List<StorageAction> getStorageActions() {
        return this.storageLoader == null ? new ArrayList<StorageAction>() : this.storageLoader.getStorage().getActions();
    }

    /**
     * Returns the Config files
     * @return The Config files
     */
    public ConfigLoader[] getConfigs() {
        return this.configs;
    }
}
