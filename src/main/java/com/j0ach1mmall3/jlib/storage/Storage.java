package com.j0ach1mmall3.jlib.storage;

import com.j0ach1mmall3.jlib.logging.JLogger;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 16/01/16
 */
public abstract class Storage<P extends JavaPlugin> {
    protected final P plugin;
    protected final JLogger jLogger;
    protected final String name;
    protected final List<StorageAction> actions = new ArrayList<>();

    /**
     * Constructs a new Storage instance, shouldn't be used externally
     *
     * @param plugin The JavaPlugin instance associated with this Storage
     * @param name   The name of this Storage
     */
    protected Storage(P plugin, String name) {
        this.plugin = plugin;
        this.jLogger = new JLogger(plugin);
        this.name = name;
    }

    /**
     * Connects this Storage
     */
    public abstract void connect();

    /**
     * Disconnects this Storage
     */
    public abstract void disconnect();

    /**
     * Returns the JavaPlugin instance associated with this Storage
     *
     * @return The JavaPlugin
     */
    public final P getPlugin() {
        return this.plugin;
    }

    /**
     * Returns the JLogger of this Storage
     *
     * @return The JLogger
     */
    public JLogger getjLogger() {
        return this.jLogger;
    }

    /**
     * Returns the name of this Storage
     *
     * @return The name of this Storage
     */
    public final String getName() {
        return this.name;
    }

    /**
     * Returns all StorageActions performed by this Storage
     *
     * @return The StorageActions
     */
    public final List<StorageAction> getActions() {
        return this.actions;
    }
}
