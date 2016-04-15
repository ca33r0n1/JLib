package com.j0ach1mmall3.jlib.logging;

import com.j0ach1mmall3.jlib.storage.StorageAction;
import com.j0ach1mmall3.jlib.storage.file.yaml.ConfigLoader;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 15/04/2016
 */
public final class DebugInfo {
    private final StorageAction[] storageActions;
    private final ConfigLoader[] configs;

    public DebugInfo(StorageAction[] storageActions, ConfigLoader[] configs) {
        this.storageActions = storageActions;
        this.configs = configs;
    }

    public StorageAction[] getStorageActions() {
        return this.storageActions;
    }

    public ConfigLoader[] getConfigs() {
        return this.configs;
    }
}
