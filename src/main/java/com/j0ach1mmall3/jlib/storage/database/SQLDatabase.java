package com.j0ach1mmall3.jlib.storage.database;

import com.j0ach1mmall3.jlib.methods.General;
import org.bukkit.Bukkit;
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
    }

    /**
     * Disconnects from the SQLDatabase
     */
    public void disconnect() {
        try {
            this.c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void execute(String sql){
        this.prepareStatement(sql, new SQLCallbackHandler() {
            @Override
            public void callback(ResultSet resultSet) {
            }

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
    public void execute(final PreparedStatement ps){
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    ps.execute();
                    ps.close();
                } catch (SQLException e) {
                    General.sendColoredMessage(SQLDatabase.this.plugin, "Failed to execute PreparedStatement- " + ps.toString() + " -for the MySQL Database!", ChatColor.RED);
                    e.printStackTrace();
                }
            }
        }, 0L);
    }

    /**
     * Execute an SQL Update
     * @param sql The SQL Update
     */
    public void executeUpdate(final String sql){
        this.prepareStatement(sql, new SQLCallbackHandler() {
            @Override
            public void callback(ResultSet resultSet) {
            }

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
    public void executeUpdate(final PreparedStatement ps){
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    ps.executeUpdate();
                    ps.close();
                } catch (SQLException e) {
                    General.sendColoredMessage(SQLDatabase.this.plugin, "Failed to update PreparedStatement- " + ps.toString() + " -for the MySQL Database!", ChatColor.RED);
                    e.printStackTrace();
                }
            }
        }, 0L);
    }

    /**
     * Executes an SQL Querry
     * @param sql The SQL Querry
     * @deprecated {@link SQLDatabase#executeQuerry(PreparedStatement, SQLCallbackHandler)}
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public ResultSet executeQuerry(String sql){
        return this.executeQuerry(this.prepareStatement(sql));
    }

    /**
     * Executes an SQL Querry
     * @param sql THe SQL Querry
     * @param callbackHandler The Callback Handler
     */
    public void executeQuerry(String sql, final SQLCallbackHandler callbackHandler) {
        this.prepareStatement(sql, new SQLCallbackHandler() {
            @Override
            public void callback(ResultSet resultSet) {
            }

            @Override
            public void callback(PreparedStatement preparedStatement) {
                SQLDatabase.this.executeQuerry(preparedStatement, callbackHandler);
            }
        });
    }

    /**
     * Executes a PreparedStatement Querry
     * @param ps The PreparedStatement Querry
     * @deprecated {@link SQLDatabase#executeQuerry(PreparedStatement, SQLCallbackHandler)}
     * @see PreparedStatement
     */
    @Deprecated
    public ResultSet executeQuerry(PreparedStatement ps){
        ResultSet rs = null;
        try {
            rs =  ps.executeQuery();
        } catch(SQLException e) {
            General.sendColoredMessage(this.plugin, "Failed to querry PreparedStatement- " + ps.toString() + " -for the MySQL Database!", ChatColor.RED);
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * Executes a PreparedStatement Querry
     * @param ps The PreparedStatement Querry
     * @param callbackHandler The Callback Handler
     * @see PreparedStatement
     */
    @SuppressWarnings("deprecation")
    public void executeQuerry(final PreparedStatement ps, final SQLCallbackHandler callbackHandler) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    callbackHandler.callback(ps.executeQuery());
                } catch(SQLException e) {
                    General.sendColoredMessage(SQLDatabase.this.plugin, "Failed to querry PreparedStatement- " + ps.toString() + " -for the MySQL Database!", ChatColor.RED);
                    e.printStackTrace();
                }
            }
        }, 0L);
    }

    /**
     * Creates a PreparedStatement
     * @param sql The SQL Code
     * @return The PreparedStatement
     * @deprecated {@link SQLDatabase#prepareStatement(String, SQLCallbackHandler)}
     * @see PreparedStatement
     */
    @Deprecated
    public PreparedStatement prepareStatement(String sql) {
        PreparedStatement ps = null;
        if(this.c == null) return null;
        try {
            if(this.c.isClosed()) this.connect();
            if(this.c == null) return null;
            ps = this.c.prepareStatement(sql);
        } catch(SQLException e) {
            General.sendColoredMessage(this.plugin, "Failed to prepare Statement- " + sql + " -for the MySQL Database!", ChatColor.RED);
            e.printStackTrace();
        }
        return ps;
    }

    /**
     * Creates a PreparedStatement
     * @param sql The SQL Code
     * @param callbackHandler The Callback Handler
     * @see PreparedStatement
     */
    @SuppressWarnings("deprecation")
    public void prepareStatement(final String sql, final SQLCallbackHandler callbackHandler) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                if(SQLDatabase.this.c == null) return;
                try {
                    if(SQLDatabase.this.c.isClosed()) SQLDatabase.this.connect();
                    if(SQLDatabase.this.c == null) return;
                    callbackHandler.callback(SQLDatabase.this.c.prepareStatement(sql));
                } catch(SQLException e) {
                    General.sendColoredMessage(SQLDatabase.this.plugin, "Failed to prepare Statement- " + sql + " -for the MySQL Database!", ChatColor.RED);
                    e.printStackTrace();
                }
            }
        }, 0L);
    }

    /**
     * Sets the String of a PreparedStatement
     * @param ps The PreparedStatement
     * @param index The index of the String
     * @param string The String
     * @return The PreparedStatement
     * @deprecated {@link SQLDatabase#setString(PreparedStatement, int, String, SQLCallbackHandler)}
     * @see PreparedStatement
     */
    @Deprecated
    public PreparedStatement setString(PreparedStatement ps, int index, String string) {
        try {
            ps.setString(index, string);
        } catch(SQLException e) {
            General.sendColoredMessage(this.plugin, "Failed to set String " + string + " at " + index + " for PreparedStatement- " + ps.toString() + " -for the MySQL Database!", ChatColor.RED);
            e.printStackTrace();
        }
        return ps;
    }

    /**
     * Sets the String of a PreparedStatement
     * @param ps The PreparedStatement
     * @param index The index of the String
     * @param string The String
     * @param callbackHandler The Callback Handler
     */
    @SuppressWarnings("deprecation")
    public void setString(final PreparedStatement ps, final int index, final String string, final SQLCallbackHandler callbackHandler) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    ps.setString(index, string);
                    callbackHandler.callback(ps);
                } catch(SQLException e) {
                    General.sendColoredMessage(SQLDatabase.this.plugin, "Failed to set String " + string + " at " + index + " for PreparedStatement- " + ps.toString() + " -for the MySQL Database!", ChatColor.RED);
                    e.printStackTrace();
                }
            }
        }, 0L);
    }


    /**
     * Sets the int of a PreparedStatement
     * @param ps The PreparedStatement
     * @param index The index of the int
     * @param i The int
     * @return The PreparedStatement
     * @deprecated {@link SQLDatabase#setInt(PreparedStatement, int, int, SQLCallbackHandler)}
     * @see PreparedStatement
     */
    @Deprecated
    public PreparedStatement setInt(PreparedStatement ps, int index, int i) {
        try {
            ps.setInt(index, i);
        } catch(SQLException e) {
            General.sendColoredMessage(this.plugin, "Failed to set int " + i + " at " + index + " for PreparedStatement- " + ps.toString() + " -for the MySQL Database!", ChatColor.RED);
            e.printStackTrace();
        }
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
    public void setInt(final PreparedStatement ps, final int index, final int i, final SQLCallbackHandler callbackHandler) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    ps.setInt(index, i);
                    callbackHandler.callback(ps);
                } catch(SQLException e) {
                    General.sendColoredMessage(SQLDatabase.this.plugin, "Failed to set int " + i + " at " + index + " for PreparedStatement- " + ps.toString() + " -for the MySQL Database!", ChatColor.RED);
                    e.printStackTrace();
                }
            }
        }, 0L);
    }

    /**
     * Sets the boolean of a PreparedStatement
     * @param ps The PreparedStatement
     * @param index The index of the boolean
     * @param b The boolean
     * @return The PreparedStatement
     * @deprecated {@link SQLDatabase#setBoolean(PreparedStatement, int, boolean, SQLCallbackHandler)}
     * @see PreparedStatement
     */
    @Deprecated
    public PreparedStatement setBoolean(PreparedStatement ps, int index, boolean b) {
        try {
            ps.setBoolean(index, b);
        } catch(SQLException e) {
            General.sendColoredMessage(this.plugin, "Failed to set boolean " + b + " at " + index + " for PreparedStatement- " + ps.toString() + " -for the MySQL Database!", ChatColor.RED);
            e.printStackTrace();
        }
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
    public void setBoolean(final PreparedStatement ps, final int index, final boolean b, final SQLCallbackHandler callbackHandler) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    ps.setBoolean(index, b);
                    callbackHandler.callback(ps);
                } catch(SQLException e) {
                    General.sendColoredMessage(SQLDatabase.this.plugin, "Failed to set boolean " + b + " at " + index + " for PreparedStatement- " + ps.toString() + " -for the MySQL Database!", ChatColor.RED);
                    e.printStackTrace();
                }
            }
        }, 0L);
    }
}
