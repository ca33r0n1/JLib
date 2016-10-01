package com.j0ach1mmall3.jlib.plugin.modularization;

import com.j0ach1mmall3.jlib.storage.file.yaml.ConfigLoader;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.HashSet;
import java.util.Set;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 8/03/2016
 */
public abstract class PluginModule<M extends ModularizedPlugin, C extends ConfigLoader> {
    protected final M parent;
    protected final Set<Listener> listeners = new HashSet<>();
    protected C config;
    private boolean enabled;

    /**
     * Constructs a new PluginModule
     * @param parent The parent of this PluginModule
     */
    protected PluginModule(M parent) {
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
     * Returns the registered Listeners
     * @return The Listeners
     */
    public final Set<Listener> getListeners() {
        return this.listeners;
    }

    /**
     * Returns the Config of this PluginModule
     * @return The Config
     */
    public final C getConfig() {
        return this.config;
    }


    /**
     * Gets whether the PluginModule is enabled
     * @return Whether the PluginModule is enabled
     */
    public final boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Sets whether the PluginModule is enabled
     * @param enabled Whether the PluginModule is enabled
     */
    public final void setEnabled(boolean enabled) {
        if (enabled == this.enabled) return;
        this.enabled = enabled;
        if (enabled) this.onEnable();
        else this.onDisable();
    }

    /**
     * Registers a specified Listener
     * @param listener The Listener
     */
    protected final void registerListener(Listener listener) {
        this.listeners.add(listener);
        this.parent.getServer().getPluginManager().registerEvents(listener, this.parent);
    }

    /**
     * Unregisters a specified Listener
     * @param listener The Listener
     */
    protected final void unregisterListener(Listener listener) {
        HandlerList.unregisterAll(listener);
        this.listeners.remove(listener);
    }

    /**
     * Unregisters all the Listeners
     */
    protected final void unregisterListeners() {
        for(Listener listener : this.listeners) {
            HandlerList.unregisterAll(listener);
        }
        this.listeners.clear();
    }
}
