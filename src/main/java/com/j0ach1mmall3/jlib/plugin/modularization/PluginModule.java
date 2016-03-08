package com.j0ach1mmall3.jlib.plugin.modularization;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 8/03/2016
 */
public abstract class PluginModule {
    private final ModularizedPlugin parent;
    private boolean enabled;

    /**
     * Constructs a new PluginModule
     * @param parent The parent of this PluginModule
     */
    public PluginModule(ModularizedPlugin parent) {
        this.parent = parent;
    }

    /**
     * Called when the PluginModule gets enabled
     */
    public void onEnable() {
        // NOP
    }

    /**
     * Called when the PluginModule gets disabled
     */
    public void onDisable() {
        // NOP
    }

    /**
     * Returns the parent of this PluginModule
     * @return The parent
     */
    public ModularizedPlugin getParent() {
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
}
