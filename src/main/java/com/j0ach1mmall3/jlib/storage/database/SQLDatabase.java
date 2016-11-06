package com.j0ach1mmall3.jlib.storage.database;

import com.j0ach1mmall3.jlib.storage.StorageAction;
import com.j0ach1mmall3.jlib.storage.database.wrapped.WrappedParameters;
import com.j0ach1mmall3.jlib.storage.database.wrapped.WrappedResultSet;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/15
 */
public abstract class SQLDatabase<P extends JavaPlugin> extends Database<P> {
    /**
     * Constructs a new SQLDatabase instance, shouldn't be used externally
     *
     * @param plugin   The JavaPlugin associated with the SQL Database
     * @param hostName The host name of the SQL Server
     * @param port     The port of the SQL Server
     * @param database The name of the SQL Database
     * @param user     The user to use
     * @param password The password to use
     */
    protected SQLDatabase(P plugin, String hostName, int port, String database, String user, String password) {
        super(plugin, hostName, port, database, user, password);
    }

    /**
     * Executes an SQL Statement
     *
     * @param sql    The SQL statement
     * @param params The params for the Statement
     */
    public final void execute(String sql, WrappedParameters params) {
        StorageAction storageAction = new StorageAction(StorageAction.Type.SQL_EXECUTE, sql);
        this.executor.execute(() -> {
            Connection c = this.getConnection();
            try {
                PreparedStatement ps = c.prepareStatement(sql);
                params.populate(ps);
                ps.execute();
                storageAction.setSuccess(true);
            } catch (Exception e) {
                e.printStackTrace();
                storageAction.setSuccess(false);
            } finally {
                this.closeConnection(c);
            }
            this.actions.add(storageAction);
        });
    }

    /**
     * Executes an SQL Statement
     *
     * @param sql The SQL statement
     */
    public final void execute(String sql) {
        this.execute(sql, new WrappedParameters());
    }

    /**
     * Executes an SQL Statement update
     *
     * @param sql    The SQL statement
     * @param params The params for the Statement
     */
    public final void executeUpdate(String sql, WrappedParameters params) {
        StorageAction storageAction = new StorageAction(StorageAction.Type.SQL_EXECUTEUPDATE, sql);
        this.executor.execute(() -> {
            Connection c = this.getConnection();
            try {
                PreparedStatement ps = c.prepareStatement(sql);
                params.populate(ps);
                ps.executeUpdate();
                storageAction.setSuccess(true);
            } catch (Exception e) {
                e.printStackTrace();
                storageAction.setSuccess(false);
            } finally {
                this.closeConnection(c);
            }
            this.actions.add(storageAction);
        });
    }

    /**
     * Executes an SQL Statement update
     *
     * @param sql The SQL statement
     */
    public final void executeUpdate(String sql) {
        this.executeUpdate(sql, new WrappedParameters());
    }

    /**
     * Executes an SQL Statement query
     *
     * @param sql             The SQL statement
     * @param params          The params for the Statement
     * @param callbackHandler The CallbackHandler to call back to
     */
    public final void executeQuery(String sql, WrappedParameters params, CallbackHandler<WrappedResultSet> callbackHandler) {
        StorageAction storageAction = new StorageAction(StorageAction.Type.SQL_EXECUTEQUERY, sql);
        this.executor.execute(() -> {
            Connection c = this.getConnection();
            try {
                PreparedStatement ps = c.prepareStatement(sql);
                params.populate(ps);
                callbackHandler.callback(new WrappedResultSet(ps.executeQuery()));
                storageAction.setSuccess(true);
            } catch (Exception e) {
                e.printStackTrace();
                callbackHandler.callback(null);
                storageAction.setSuccess(false);
            } finally {
                this.closeConnection(c);
            }
            this.actions.add(storageAction);
        });
    }

    /**
     * Executes an SQL Statement query
     *
     * @param sql             The SQL statement
     * @param callbackHandler The CallbackHandler to call back to
     */
    public final void executeQuery(String sql, CallbackHandler<WrappedResultSet> callbackHandler) {
        this.executeQuery(sql, new WrappedParameters(), callbackHandler);
    }

    /**
     * Returns the Connection for the SQLDatabase
     *
     * @return The Connection
     */
    protected abstract Connection getConnection();

    /**
     * Closes a Connection
     *
     * @param connection The Connection to close
     */
    public abstract void closeConnection(Connection connection);
}
