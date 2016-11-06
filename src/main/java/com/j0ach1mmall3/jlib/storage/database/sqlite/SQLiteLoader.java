package com.j0ach1mmall3.jlib.storage.database.sqlite;

import com.j0ach1mmall3.jlib.storage.StorageLoader;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/15
 */
public abstract class SQLiteLoader<P extends JavaPlugin> extends StorageLoader<SQLite<P>, P> {
    @Deprecated
    protected final SQLite<P> sqLite;

    /**
     * Constructs a new SQLiteLoader, use this by extending the SQLiteLoader
     *
     * @param plugin   The JavaPlugin associated with the SQLite Database
     * @param name     The name of the SQLite file
     * @param user     The user to use
     * @param password The password to use
     */
    protected SQLiteLoader(P plugin, String name, String user, String password) {
        super(new SQLite<>(plugin, name, user, password));
        this.sqLite = this.storage;
    }

    /**
     * Constructs a new SQLiteLoader, use this by extending the SQLiteLoader
     *
     * @param plugin The JavaPlugin associated with the SQLite Database
     * @param name   The name of the SQLite file
     */
    protected SQLiteLoader(P plugin, String name) {
        super(new SQLite<>(plugin, name, null, null));
        this.sqLite = this.storage;
    }
}
