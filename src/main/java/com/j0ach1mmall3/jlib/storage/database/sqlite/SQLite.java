package com.j0ach1mmall3.jlib.storage.database.sqlite;

import com.j0ach1mmall3.jlib.logging.JLogger;
import com.j0ach1mmall3.jlib.storage.StorageAction;
import com.j0ach1mmall3.jlib.storage.database.SQLDatabase;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/15
 */
public final class SQLite<P extends JavaPlugin> extends SQLDatabase<P> {
    private Connection connection;

    /**
     * Constructs a new SQLite instance, shouldn't be used externally, use {@link SQLiteLoader} instead
     * @param plugin The JavaPlugin associated with the SQLite Database
     * @param name The name of the SQLite file
     * @param user The user to use
     * @param password The password to use
     */
    SQLite(P plugin, String name, String user, String password) {
        super(plugin, null, 0, new File(plugin.getDataFolder(), name.endsWith(".db") ? name : name + ".db").getAbsolutePath(), user, password);
        File file = new File(this.name);
        try {
            if (!file.exists()) file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the Connection for the SQLite Database
     * @return The Connection
     */
    @Override
    protected Connection getConnection() {
        StorageAction storageAction = new StorageAction(StorageAction.Type.SQL_GETCONNECTION, this.name);
        try {
            Class.forName("org.sqlite.JDBC");
            if(this.connection == null || this.connection.isClosed()) this.connection = DriverManager.getConnection("jdbc:sqlite:" + this.name);
            storageAction.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            this.jLogger.log(ChatColor.RED + "Failed to connect to the SQLite Database using " + this.name + '!', JLogger.LogLevel.MINIMAL);
            storageAction.setSuccess(false);
        }
        this.actions.add(storageAction);

        return this.connection;
    }

    /**
     * Disconnects from the SQLDatabase
     */
    @Override
    public void disconnect() {
        StorageAction storageAction = new StorageAction(StorageAction.Type.SQL_DISCONNECT, this.hostName, String.valueOf(this.port), this.name, this.user);
        try {
            this.connection.close();
            storageAction.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            storageAction.setSuccess(false);
        }
        this.actions.add(storageAction);
    }
}
