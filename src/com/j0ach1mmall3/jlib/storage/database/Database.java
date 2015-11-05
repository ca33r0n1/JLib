package com.j0ach1mmall3.jlib.storage.database;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/2015
 */
public abstract class Database {
    protected final JavaPlugin plugin;
    protected final String hostName;
    protected final int port;
    protected final String database;
    protected final String user;
    protected final String password;

    /**
     * Constructs a new Database instance, shouldn't be used externally
     */
    protected Database(JavaPlugin plugin, String hostName, int port, String database, String user, String password) {
        this.plugin = plugin;
        this.hostName = hostName;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    /**
     * Connects to the Database
     */
    public abstract void connect();

    /**
     * Disconnects from the Database
     */
    public abstract void disconnect();
}
