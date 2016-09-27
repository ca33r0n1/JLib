package com.j0ach1mmall3.jlib.storage.database.mongodb;

import com.j0ach1mmall3.jlib.storage.StorageLoader;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/15
 */
public abstract class MongoDBLoader<P extends JavaPlugin> extends StorageLoader<MongoDB<P>, P> {
    @Deprecated
    protected final MongoDB<P> mongoDB;

    /**
     * Constructs a new MongoDBLoader, use this by extending the MongoDBLoader
     * @param plugin The JavaPlugin associated with the MongoDB Database
     * @param hostName The host name of the MongoDB Server
     * @param port The port of the MongoDB Server
     * @param database The name of the MongoDB Database
     * @param user The user to use
     * @param password The password to use
     */
    protected MongoDBLoader(P plugin, String hostName, int port, String database, String user, String password) {
        super(new MongoDB<>(plugin, hostName, port, database, user, password));
        this.mongoDB = this.storage;
        this.storage.connect();
    }
}
