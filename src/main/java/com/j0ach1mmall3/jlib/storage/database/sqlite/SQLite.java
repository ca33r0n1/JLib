package com.j0ach1mmall3.jlib.storage.database.sqlite;

import com.j0ach1mmall3.jlib.methods.General;
import com.j0ach1mmall3.jlib.storage.database.SQLDatabase;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
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
    public SQLite(JavaPlugin plugin, String name) {
        super(plugin, null, 0, name, null, null);
    }

    /**
     * Returns the Connection for the SQLite Database
     * @return The Connection
     * @see Connection
     */
    protected Connection getConnection() {
        File file = new File(this.plugin.getDataFolder(), this.database + ".db");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            return DriverManager.getConnection("jdbc:sqlite:" + file);
        } catch (Exception e) {
            General.sendColoredMessage(this.plugin, "Failed to connect to the SQLite Database using following!", ChatColor.RED);
            return null;
        }
    }
}
