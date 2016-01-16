package com.j0ach1mmall3.jlib.storage.database;

import com.j0ach1mmall3.jlib.storage.Storage;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/2015
 */
public abstract class Database extends Storage {
    protected final String hostName;
    protected final int port;
    protected final String user;
    protected final String password;

    /**
     * Constructs a new Database instance, shouldn't be used externally
     */
    protected Database(JavaPlugin plugin, String hostName, int port, String database, String user, String password) {
        super(plugin, database);
        this.hostName = hostName;
        this.port = port;
        this.user = user;
        this.password = password;
    }
}
