package com.j0ach1mmall3.jlib.plugin.modularization;

import com.j0ach1mmall3.jlib.storage.file.yaml.ConfigLoader;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 8/03/2016
 */
public abstract class PluginModule<M extends ModularizedPlugin, C extends ConfigLoader> {
    protected final M parent;
    protected C config;
    private boolean enabled;

    /**
     * Constructs a new PluginModule
     * @param parent The parent of this PluginModule
     */
    public PluginModule(M parent) {
        this.parent = parent;
    }

    /**
     * Called when the PluginModule gets enabled
     */
    public abstract void onEnable();

    /**
     * Called when the PluginModule gets disabled
     */
    public abstract void onDisable();

    /**
     * Returns the parent of this PluginModule
     * @return The parent
     */
    public final M getParent() {
        return this.parent;
    }

    /**
     * Sets whether the PluginModule is enabled
     * @param enabled Whether the PluginModule is enabled
     */
    public final void setEnabled(boolean enabled) {
        if(enabled == this.enabled) return;
        this.enabled = enabled;
        if(enabled) this.onEnable();
        else this.onDisable();
    }

    /**
     * Gets whether the PluginModule is enabled
     * @return Whether the PluginModule is enabled
     */
    public final boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Returns the Config of this PluginModule
     * @return The Config
     */
    public C getConfig() {
        return this.config;
    }
}
