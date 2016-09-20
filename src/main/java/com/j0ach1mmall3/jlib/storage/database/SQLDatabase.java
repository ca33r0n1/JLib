package com.j0ach1mmall3.jlib.storage.database;

import com.j0ach1mmall3.jlib.storage.StorageAction;
import com.j0ach1mmall3.jlib.storage.database.wrapped.WrappedParameters;
import com.j0ach1mmall3.jlib.storage.database.wrapped.WrappedResultSet;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/15
 */
public abstract class SQLDatabase<P extends JavaPlugin> extends Database<P> {
    protected final HikariDataSource dataSource = new HikariDataSource();

    /**
     * Constructs a new SQLDatabase instance, shouldn't be used externally
     * @param plugin The JavaPlugin associated with the MySQL Database
     * @param hostName The host name of the MySQL Server
     * @param port The port of the MySQL Server
     * @param database The name of the MySQL Database
     * @param user The user to use
     * @param password The password to use
     * @param dataSourceName The DataSource to use
     */
    protected SQLDatabase(P plugin, String hostName, int port, String database, String user, String password, String dataSourceName) {
        super(plugin, hostName, port, database, user, password);
        try {
            Class.forName(dataSourceName);
            this.dataSource.setDataSourceClassName(dataSourceName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(hostName != null) this.dataSource.addDataSourceProperty("dataSource.serverName", hostName);
        if(port != 0) this.dataSource.addDataSourceProperty("dataSource.portNumber", port);
        if(database != null) this.dataSource.addDataSourceProperty("dataSource.databaseName", database);
        if(user != null) this.dataSource.setUsername(user);
        if(password != null) this.dataSource.setPassword(password);

        this.dataSource.setMinimumIdle(2);
        this.dataSource.addDataSourceProperty("useUnicode", "true");
        this.dataSource.addDataSourceProperty("characterEncoding", "utf-8");
        this.dataSource.addDataSourceProperty("rewriteBatchedStatements", "true");
        this.dataSource.addDataSourceProperty("cachePrepStmts", "true");
        this.dataSource.addDataSourceProperty("prepStmtCacheSize", "250");
        this.dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    }

    /**
     * Returns the Connection for the SQLDatabase
     * @return The Connection
     */
    protected abstract Connection getConnection();

    /**
     * Connects to the SQLDatabase
     */
    @Override
    public final void connect() {
        // NOP
    }

    /**
     * Disconnects from the SQLDatabase
     */
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

    /**
     * Executes an SQL Statement
     * @param sql The SQL statement
     * @param params The params for the Statement
     */
    public void execute(final String sql, final WrappedParameters params) {
        final StorageAction storageAction = new StorageAction(StorageAction.Type.SQL_EXECUTE, sql);
        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                try (Connection c = SQLDatabase.this.getConnection()) {
                    PreparedStatement ps = c.prepareStatement(sql);
                    params.populate(ps);
                    ps.execute();
                    storageAction.setSuccess(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    storageAction.setSuccess(false);
                }
                SQLDatabase.this.actions.add(storageAction);
            }
        });
    }

    /**
     * Executes an SQL Statement
     * @param sql The SQL statement
     */
    public void execute(String sql) {
        this.execute(sql, new WrappedParameters());
    }

    /**
     * Executes an SQL Statement update
     * @param sql The SQL statement
     * @param params The params for the Statement
     */
    public void executeUpdate(final String sql, final WrappedParameters params) {
        final StorageAction storageAction = new StorageAction(StorageAction.Type.SQL_EXECUTEUPDATE, sql);
        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                try (Connection c = SQLDatabase.this.getConnection()) {
                    PreparedStatement ps = c.prepareStatement(sql);
                    params.populate(ps);
                    ps.executeUpdate();
                    storageAction.setSuccess(true);
                } catch (Exception e) {
                    //e.printStackTrace();
                    storageAction.setSuccess(false);
                }
                SQLDatabase.this.actions.add(storageAction);
            }
        });
    }

    /**
     * Executes an SQL Statement update
     * @param sql The SQL statement
     */
    public void executeUpdate(String sql) {
        this.executeUpdate(sql, new WrappedParameters());
    }

    /**
     * Executes an SQL Statement query
     * @param sql The SQL statement
     * @param params The params for the Statement
     * @param callbackHandler The CallbackHandler to call back to
     */
    public void executeQuery(final String sql, final WrappedParameters params, final CallbackHandler<WrappedResultSet> callbackHandler) {
        final StorageAction storageAction = new StorageAction(StorageAction.Type.SQL_EXECUTEQUERY, sql);
        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                try (Connection c = SQLDatabase.this.getConnection()) {
                    PreparedStatement ps = c.prepareStatement(sql);
                    params.populate(ps);
                    callbackHandler.callback(new WrappedResultSet(ps.executeQuery()));
                    storageAction.setSuccess(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    callbackHandler.callback(null);
                    storageAction.setSuccess(false);
                }
                SQLDatabase.this.actions.add(storageAction);
            }
        });
    }

    /**
     * Executes an SQL Statement query
     * @param sql The SQL statement
     * @param callbackHandler The CallbackHandler to call back to
     */
    public void executeQuery(String sql, CallbackHandler<WrappedResultSet> callbackHandler) {
        this.executeQuery(sql, new WrappedParameters(), callbackHandler);
    }
}
