package com.j0ach1mmall3.jlib.storage.database.mysql;

import com.j0ach1mmall3.jlib.logging.JLogger;
import com.j0ach1mmall3.jlib.storage.StorageAction;
import com.j0ach1mmall3.jlib.storage.database.SQLDatabase;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since Unknown
 */
public final class MySQL<P extends JavaPlugin> extends SQLDatabase<P> {
    /**
     * Constructs a new MySQL instance, shouldn't be used externally, use {@link MySQLLoader} instead
     * @param plugin The JavaPlugin associated with the MySQL Database
     * @param hostName The host name of the MySQL Server
     * @param port The port of the MySQL Server
     * @param database The name of the MySQL Database
     * @param user The user to use
     * @param password The password to use
     */
    MySQL(P plugin, String hostName, int port, String database, String user, String password) {
        super(plugin, hostName, port, database, user, password, "com.mysql.jdbc.jdbc2.optional.MysqlDataSource");

        this.dataSource.setJdbcUrl("jdbc:mysql://" + hostName + ':' + port + '/' + database);
    }

    /**
     * Returns the Connection for the MySQL Database
     * @return The Connection
     */
    @Override
    protected Connection getConnection() {
        StorageAction storageAction = new StorageAction(StorageAction.Type.MYSQL_GETCONNECTION, this.hostName, String.valueOf(this.port), this.name, this.user);
        Connection c = null;
        try {
            c = this.dataSource.getConnection();
            storageAction.setSuccess(true);
        } catch (SQLException e) {
            e.printStackTrace();
            this.jLogger.log(ChatColor.RED + "Failed to connect to the MySQL Database using following credentials:", JLogger.LogLevel.MINIMAL);
            this.jLogger.log(ChatColor.RED + "HostName: " + this.hostName, JLogger.LogLevel.MINIMAL);
            this.jLogger.log(ChatColor.RED + "Port: " + this.port, JLogger.LogLevel.MINIMAL);
            this.jLogger.log(ChatColor.RED + "Database: " + this.name, JLogger.LogLevel.MINIMAL);
            this.jLogger.log(ChatColor.RED + "User: " + this.user, JLogger.LogLevel.MINIMAL);
            storageAction.setSuccess(false);
        }
        this.actions.add(storageAction);
        return c;
    }
}
