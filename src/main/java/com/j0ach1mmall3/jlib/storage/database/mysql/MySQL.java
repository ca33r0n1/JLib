package com.j0ach1mmall3.jlib.storage.database.mysql;

import com.j0ach1mmall3.jlib.methods.General;
import com.j0ach1mmall3.jlib.storage.StorageAction;
import com.j0ach1mmall3.jlib.storage.database.SQLDatabase;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since Unknown
 */
public final class MySQL extends SQLDatabase {
    /**
     * Constructs a new MySQL instance, shouldn't be used externally, use {@link MySQLLoader} instead
     */
	MySQL(JavaPlugin plugin, String hostName, int port, String database, String user, String password) {
        super(plugin, hostName, port, database, user, password);
	}

    /**
     * Returns the Connection for the MySQL Database
     * @return The Connection
     * @see Connection
     */
    protected Connection getConnection() {
        StorageAction storageAction = new StorageAction(StorageAction.Type.MYSQL_CONNECT, this.hostName, String.valueOf(this.port), this.name, this.user);
        Connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://" + this.hostName + ":" + this.port + "/" + this.name, this.user, this.password);
            storageAction.setSuccess(true);
        } catch(SQLException e) {
            General.sendColoredMessage(this.plugin, "Failed to connect to the MySQL Database using following credentials:", ChatColor.RED);
            General.sendColoredMessage(this.plugin, "HostName: " + this.hostName, ChatColor.GOLD);
            General.sendColoredMessage(this.plugin, "Port: " + this.port, ChatColor.GOLD);
            General.sendColoredMessage(this.plugin, "Database: " + this.name, ChatColor.GOLD);
            General.sendColoredMessage(this.plugin, "User: " + this.user, ChatColor.GOLD);
            General.sendColoredMessage(this.plugin, "Password: =REDACTED=", ChatColor.GOLD);
            storageAction.setSuccess(false);
        }
        this.actions.add(storageAction);
        return c;
    }
}
