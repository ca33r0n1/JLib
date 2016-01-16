package com.j0ach1mmall3.jlib.storage.database;

import com.j0ach1mmall3.jlib.logging.JLogger;
import com.j0ach1mmall3.jlib.methods.General;
import com.j0ach1mmall3.jlib.storage.StorageAction;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/2015
 */
public abstract class SQLDatabase extends Database {
    private Connection c;
    private DatabaseThread thread = new DatabaseThread();

    /**
     * Constructs a new SQLDatabase instance, shouldn't be used externally
     */
    protected SQLDatabase(JavaPlugin plugin, String hostName, int port, String database, String user, String password) {
        super(plugin, hostName, port, database, user, password);
    }

    /**
     * Returns the Connection for the SQLDatabase
     * @return The Connection
     * @see Connection
     */
    protected abstract Connection getConnection();

    /**
     * Connects to the SQLDatabase
     */
    public void connect() {
        this.c = this.getConnection();
        this.thread.start();
    }

    /**
     * Disconnects from the SQLDatabase
     */
    public void disconnect() {
        StorageAction storageAction = new StorageAction(StorageAction.Type.SQL_DISCONNECT, this.hostName, String.valueOf(this.port), this.name, this.user);
        try {
            this.thread.stopThread();
            this.c.close();
            storageAction.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            storageAction.setSuccess(false);
        }
        this.actions.add(storageAction);
    }

    /**
     * Drops a Table
     * @param table The Table to drop
     */
    public void dropTable(String table) {
        this.execute("DROP TABLE " + table);
    }

    /**
     * Executes an SQL Statement
     * @param sql The SQL Statement
     */
    public void execute(String sql) {
        this.prepareStatement(sql, new CallbackHandler<PreparedStatement>() {
            @Override
            public void callback(PreparedStatement preparedStatement) {
                SQLDatabase.this.execute(preparedStatement);
            }
        });
    }

    /**
     * Executes PreparedStatement
     * @param ps The PreparedStatement
     * @see PreparedStatement
     */
    @SuppressWarnings("deprecation")
    public void execute(final PreparedStatement ps) {
        final StorageAction storageAction = new StorageAction(StorageAction.Type.SQL_EXECUTE);
        this.thread.addRunnable(new Runnable() {
            @Override
            public void run() {
                try {
                    ps.execute();
                    ps.close();
                    storageAction.setSuccess(true);
                } catch (SQLException e) {
                    General.sendColoredMessage(SQLDatabase.this.plugin, "Failed to execute PreparedStatement- " + ps + " -for the MySQL Database!", ChatColor.RED);
                    e.printStackTrace();
                    storageAction.setSuccess(false);
                }
                SQLDatabase.this.actions.add(storageAction);
            }
        });
    }

    /**
     * Execute an SQL Update
     * @param sql The SQL Update
     */
    public void executeUpdate(final String sql) {
        this.prepareStatement(sql, new CallbackHandler<PreparedStatement>() {
            @Override
            public void callback(PreparedStatement preparedStatement) {
                SQLDatabase.this.executeUpdate(preparedStatement);
            }
        });
    }

    /**
     * Executes a PreparedStatement Update
     * @param ps The PreparedStatement Update
     * @see PreparedStatement
     */
    @SuppressWarnings("deprecation")
    public void executeUpdate(final PreparedStatement ps) {
        final StorageAction storageAction = new StorageAction(StorageAction.Type.SQL_UPDATE);
        this.thread.addRunnable(new Runnable() {
            @Override
            public void run() {
                try {
                    ps.executeUpdate();
                    ps.close();
                    storageAction.setSuccess(true);
                } catch (SQLException e) {
                    General.sendColoredMessage(SQLDatabase.this.plugin, "Failed to update PreparedStatement- " + ps + " -for the MySQL Database!", ChatColor.RED);
                    e.printStackTrace();
                    storageAction.setSuccess(false);
                }
                SQLDatabase.this.actions.add(storageAction);
            }
        });
    }

    /**
     * Executes an SQL Querry
     * @param sql The SQL Querry
     * @deprecated {@link SQLDatabase#executeQuerry(PreparedStatement, CallbackHandler)}
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public ResultSet executeQuerry(String sql) {
        new JLogger().deprecation();
        return this.executeQuerry(this.prepareStatement(sql));
    }

    /**
     * Executes an SQL Querry
     * @param sql THe SQL Querry
     * @param callbackHandler The Callback Handler
     */
    public void executeQuerry(String sql, final CallbackHandler<ResultSet> callbackHandler) {
        this.prepareStatement(sql, new CallbackHandler<PreparedStatement>() {
            @Override
            public void callback(PreparedStatement preparedStatement) {
                SQLDatabase.this.executeQuerry(preparedStatement, callbackHandler);
            }
        });
    }

    /**
     * Executes a PreparedStatement Querry
     * @param ps The PreparedStatement Querry
     * @deprecated {@link SQLDatabase#executeQuerry(PreparedStatement, CallbackHandler)}
     * @see PreparedStatement
     */
    @Deprecated
    public ResultSet executeQuerry(PreparedStatement ps) {
        new JLogger().deprecation();
        StorageAction storageAction = new StorageAction(StorageAction.Type.SQL_QUERY);
        ResultSet rs = null;
        try {
            rs =  ps.executeQuery();
            storageAction.setSuccess(true);
        } catch(SQLException e) {
            General.sendColoredMessage(this.plugin, "Failed to querry PreparedStatement- " + ps + " -for the MySQL Database!", ChatColor.RED);
            e.printStackTrace();
            storageAction.setSuccess(false);
        }
        this.actions.add(storageAction);
        return rs;
    }

    /**
     * Executes a PreparedStatement Querry
     * @param ps The PreparedStatement Querry
     * @param callbackHandler The Callback Handler
     * @see PreparedStatement
     */
    @SuppressWarnings("deprecation")
    public void executeQuerry(final PreparedStatement ps, final CallbackHandler<ResultSet> callbackHandler) {
        final StorageAction storageAction = new StorageAction(StorageAction.Type.SQL_QUERY);
        this.thread.addRunnable(new Runnable() {
            @Override
            public void run() {
                try {
                    ResultSet rs = ps.executeQuery();
                    ps.close();
                    storageAction.setSuccess(true);
                    callbackHandler.callback(rs);
                } catch(SQLException e) {
                    General.sendColoredMessage(SQLDatabase.this.plugin, "Failed to query PreparedStatement- " + ps + " -for the MySQL Database!", ChatColor.RED);
                    e.printStackTrace();
                    storageAction.setSuccess(false);
                }
                SQLDatabase.this.actions.add(storageAction);
            }
        });
    }

    /**
     * Creates a PreparedStatement
     * @param sql The SQL Code
     * @return The PreparedStatement
     * @deprecated {@link SQLDatabase#prepareStatement(String, CallbackHandler)}
     * @see PreparedStatement
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public PreparedStatement prepareStatement(String sql) {
        StorageAction storageAction = new StorageAction(StorageAction.Type.SQL_PREPARESTATEMENT, sql);
        new JLogger().deprecation();
        PreparedStatement ps = null;
        if(this.c == null) {
            this.connect();
            storageAction.setSuccess(false);
            this.actions.add(storageAction);
            return this.prepareStatement(sql);
        }
        try {
            if(this.c.isClosed()) this.connect();
            if(this.c == null) storageAction.setSuccess(false);
            else {
                ps = this.c.prepareStatement(sql);
                storageAction.setSuccess(true);
            }
        } catch(SQLException e) {
            General.sendColoredMessage(this.plugin, "Failed to prepare Statement- " + sql + " -for the MySQL Database!", ChatColor.RED);
            e.printStackTrace();
            storageAction.setSuccess(false);
        }
        this.actions.add(storageAction);
        return ps;
    }

    /**
     * Creates a PreparedStatement
     * @param sql The SQL Code
     * @param callbackHandler The Callback Handler
     * @see PreparedStatement
     */
    @SuppressWarnings("deprecation")
    public void prepareStatement(final String sql, final CallbackHandler<PreparedStatement> callbackHandler) {
        final StorageAction storageAction = new StorageAction(StorageAction.Type.SQL_PREPARESTATEMENT, sql);
        this.thread.addRunnable(new Runnable() {
            @Override
            public void run() {
                if(SQLDatabase.this.c == null) {
                    SQLDatabase.this.connect();
                    storageAction.setSuccess(false);
                    SQLDatabase.this.actions.add(storageAction);
                    SQLDatabase.this.prepareStatement(sql, callbackHandler);
                }
                try {
                    if(SQLDatabase.this.c.isClosed()) SQLDatabase.this.connect();
                    if(SQLDatabase.this.c == null) storageAction.setSuccess(false);
                    else {
                        PreparedStatement ps = SQLDatabase.this.c.prepareStatement(sql);
                        storageAction.setSuccess(true);
                        callbackHandler.callback(ps);
                    }
                } catch(SQLException e) {
                    General.sendColoredMessage(SQLDatabase.this.plugin, "Failed to prepare Statement- " + sql + " -for the MySQL Database!", ChatColor.RED);
                    e.printStackTrace();
                    storageAction.setSuccess(false);
                }
                SQLDatabase.this.actions.add(storageAction);
            }
        });
    }

    /**
     * Sets the String of a PreparedStatement
     * @param ps The PreparedStatement
     * @param index The index of the String
     * @param s The String
     * @return The PreparedStatement
     * @see PreparedStatement
     */
    public PreparedStatement setString(PreparedStatement ps, int index, String s) {
        StorageAction storageAction = new StorageAction(StorageAction.Type.SQL_SETSTRING, String.valueOf(index), s);
        try {
            ps.setString(index, s);
            storageAction.setSuccess(true);
        } catch(SQLException e) {
            General.sendColoredMessage(this.plugin, "Failed to set String " + s + " at " + index + " for PreparedStatement- " + ps + " -for the MySQL Database!", ChatColor.RED);
            e.printStackTrace();
            storageAction.setSuccess(false);
        }
        this.actions.add(storageAction);
        return ps;
    }

    /**
     * Sets the String of a PreparedStatement
     * @param ps The PreparedStatement
     * @param index The index of the String
     * @param s The String
     * @param callbackHandler The Callback Handler
     */
    @SuppressWarnings("deprecation")
    public void setString(final PreparedStatement ps, final int index, final String s, final CallbackHandler<PreparedStatement> callbackHandler) {
        this.thread.addRunnable(new Runnable() {
            @Override
            public void run() {
                callbackHandler.callback(SQLDatabase.this.setString(ps, index, s));
            }
        });
    }


    /**
     * Sets the int of a PreparedStatement
     * @param ps The PreparedStatement
     * @param index The index of the int
     * @param i The int
     * @return The PreparedStatement
     * @see PreparedStatement
     */
    public PreparedStatement setInt(PreparedStatement ps, int index, int i) {
        StorageAction storageAction = new StorageAction(StorageAction.Type.SQL_SETINT, String.valueOf(index), String.valueOf(i));
        try {
            ps.setInt(index, i);
            storageAction.setSuccess(true);
        } catch(SQLException e) {
            General.sendColoredMessage(this.plugin, "Failed to set int " + i + " at " + index + " for PreparedStatement- " + ps + " -for the MySQL Database!", ChatColor.RED);
            e.printStackTrace();
            storageAction.setSuccess(false);
        }
        this.actions.add(storageAction);
        return ps;
    }

    /**
     * Sets the int of a PreparedStatement
     * @param ps The PreparedStatement
     * @param index The index of the int
     * @param i The int
     * @param callbackHandler The Callback Handler
     */
    @SuppressWarnings("deprecation")
    public void setInt(final PreparedStatement ps, final int index, final int i, final CallbackHandler<PreparedStatement> callbackHandler) {
        this.thread.addRunnable(new Runnable() {
            @Override
            public void run() {
                callbackHandler.callback(SQLDatabase.this.setInt(ps, index, i));
            }
        });
    }

    /**
     * Sets the boolean of a PreparedStatement
     * @param ps The PreparedStatement
     * @param index The index of the boolean
     * @param b The boolean
     * @return The PreparedStatement
     * @see PreparedStatement
     */
    public PreparedStatement setBoolean(PreparedStatement ps, int index, boolean b) {
        StorageAction storageAction = new StorageAction(StorageAction.Type.SQL_SETBOOLEAN, String.valueOf(index), String.valueOf(b));
        try {
            ps.setBoolean(index, b);
            storageAction.setSuccess(true);
        } catch(SQLException e) {
            General.sendColoredMessage(this.plugin, "Failed to set boolean " + b + " at " + index + " for PreparedStatement- " + ps + " -for the MySQL Database!", ChatColor.RED);
            e.printStackTrace();
            storageAction.setSuccess(false);
        }
        this.actions.add(storageAction);
        return ps;
    }

    /**
     * Sets the boolean of a PreparedStatement
     * @param ps The PreparedStatement
     * @param index The index of the boolean
     * @param b The boolean
     * @param callbackHandler The Callback Handler
     */
    @SuppressWarnings("deprecation")
    public void setBoolean(final PreparedStatement ps, final int index, final boolean b, final CallbackHandler<PreparedStatement> callbackHandler) {
        this.thread.addRunnable(new Runnable() {
            @Override
            public void run() {
                callbackHandler.callback(SQLDatabase.this.setBoolean(ps, index, b));
            }
        });
    }
}
