package com.j0ach1mmall3.jlib.storage.database.sqlite;

import com.j0ach1mmall3.jlib.storage.StorageLoader;
import com.j0ach1mmall3.jlib.storage.database.SQLDatabase;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/2015
 */
public abstract class SQLiteLoader extends StorageLoader {
    protected final SQLDatabase sqLite;

    /**
     * Constructs a new SQLiteLoader, use this by extending the SQLiteLoader
     * @param plugin The JavaPlugin associated with the SQLite Database
     * @param name The name of the SQLite file
     * @see SQLite
     */
    public SQLiteLoader(JavaPlugin plugin, String name) {
        super(new SQLite(plugin, name));
        this.sqLite = (SQLite) this.storage;
        this.sqLite.connect();
    }

    /**
     * Disconnects from the SQLite Database
     */
    public void disconnect() {
        this.sqLite.disconnect();
    }
}
