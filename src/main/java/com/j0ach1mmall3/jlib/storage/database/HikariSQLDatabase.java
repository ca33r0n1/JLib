package com.j0ach1mmall3.jlib.storage.database;

import com.j0ach1mmall3.jlib.logging.JLogger;
import com.j0ach1mmall3.jlib.storage.StorageAction;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 27/09/2016
 */
public abstract class HikariSQLDatabase<P extends JavaPlugin> extends SQLDatabase<P> {
    protected final HikariDataSource dataSource = new HikariDataSource();

    /**
     * Constructs a new SQLDatabase instance, shouldn't be used externally
     * @param plugin The JavaPlugin associated with the SQL Database
     * @param hostName The host name of the SQL Server
     * @param port The port of the SQL Server
     * @param database The name of the SQL Database
     * @param user The user to use
     * @param password The password to use
     */
    protected HikariSQLDatabase(P plugin, String hostName, int port, String database, String user, String password) {
        super(plugin, hostName, port, database, user, password);
        this.dataSource.setUsername(user);
        this.dataSource.setPassword(password);

        this.dataSource.setMinimumIdle(2);
        this.dataSource.addDataSourceProperty("useUnicode", "true");
        this.dataSource.addDataSourceProperty("characterEncoding", "utf-8");
        this.dataSource.addDataSourceProperty("rewriteBatchedStatements", "true");
        this.dataSource.addDataSourceProperty("cachePrepStmts", "true");
        this.dataSource.addDataSourceProperty("prepStmtCacheSize", "250");
        this.dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    }

    /**
     * Returns the Connection for the HikariCP Database
     * @return The Connection
     */
    @Override
    protected final Connection getConnection() {
        StorageAction storageAction = new StorageAction(StorageAction.Type.SQL_GETCONNECTION, this.hostName, String.valueOf(this.port), this.name, this.user);
        Connection c = null;
        try {
            c = this.dataSource.getConnection();
            storageAction.setSuccess(true);
        } catch (SQLException e) {
            e.printStackTrace();
            this.jLogger.log(ChatColor.RED + "Failed to connect to the SQL Database using following credentials:", JLogger.LogLevel.MINIMAL);
            this.jLogger.log(ChatColor.RED + "HostName: " + this.hostName, JLogger.LogLevel.MINIMAL);
            this.jLogger.log(ChatColor.RED + "Port: " + this.port, JLogger.LogLevel.MINIMAL);
            this.jLogger.log(ChatColor.RED + "Database: " + this.name, JLogger.LogLevel.MINIMAL);
            this.jLogger.log(ChatColor.RED + "User: " + this.user, JLogger.LogLevel.MINIMAL);
            storageAction.setSuccess(false);
        }
        this.actions.add(storageAction);
        return c;
    }

    @Override
    public final void disconnect() {
        StorageAction storageAction = new StorageAction(StorageAction.Type.SQL_DISCONNECT, this.hostName, String.valueOf(this.port), this.name, this.user);
        try {
            this.executor.shutdown();
            this.dataSource.close();
            storageAction.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            storageAction.setSuccess(false);
        }
        this.actions.add(storageAction);
    }

    @Override
    public final void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
