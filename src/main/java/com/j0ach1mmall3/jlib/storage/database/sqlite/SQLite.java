package com.j0ach1mmall3.jlib.storage.database.sqlite;

import com.j0ach1mmall3.jlib.methods.General;
import com.j0ach1mmall3.jlib.storage.StorageAction;
import com.j0ach1mmall3.jlib.storage.database.SQLDatabase;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/2015
 */
public final class SQLite extends SQLDatabase {

    /**
     * Constructs a new SQLite instance, shouldn't be used externally, use {@link SQLiteLoader} instead
     */
    SQLite(JavaPlugin plugin, String name) {
        super(plugin, null, 0, name, null, null);
    }

    /**
     * Returns the Connection for the SQLite Database
     * @return The Connection
     * @see Connection
     */
    protected Connection getConnection() {
        StorageAction storageAction = new StorageAction(StorageAction.Type.SQLITE_CONNECT, this.name);
        Connection c = null;
        File file = new File(this.plugin.getDataFolder(), this.name.endsWith(".db")?this.name:this.name + ".db");
        try {
            if(!file.exists()) file.createNewFile();
            c = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
            storageAction.setSuccess(true);
        } catch (Exception e) {
            General.sendColoredMessage(this.plugin, "Failed to connect to the SQLite Database using " + file.getAbsolutePath() + "!", ChatColor.RED);
            storageAction.setSuccess(false);
        }
        this.actions.add(storageAction);
        return c;
    }
}
