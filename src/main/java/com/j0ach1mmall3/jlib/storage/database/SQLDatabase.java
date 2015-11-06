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
        this.c = getConnection();
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
     * Creates a Table
     * @param table The Table to create
     * @see SQLTable
     */
    public void createTable(SQLTable table) {
        String sql = "CREATE TABLE";
        if(table.isIfNotExists()) sql = sql + " IF NOT EXISTS";
        sql = sql + " " + table.getTableName() + "(";
        for(SQLColumn column : table.getColumns()) {
            sql = sql + " " + column.getName() + " " + column.getType().name() + "(" + column.getSize() + "),";
        }
        sql = sql.substring(sql.length()-2);
        sql = sql + ")";
        execute(sql);
    }

    /**
     * Drops a Table
     * @param table The Table to drop
     */
    public void dropTable(String table) {
        execute("DROP TABLE " + table);
    }

    /**
     * Executes an SQL Statement
     * @param sql The SQL Statement
     */
    public void execute(String sql){
        execute(prepareStatement(sql));
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
                    General.sendColoredMessage(plugin, "Failed to execute PreparedStatement- " + ps.toString() + " -for the MySQL Database!", ChatColor.RED);
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
        executeUpdate(prepareStatement(sql));
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
                    General.sendColoredMessage(plugin, "Failed to update PreparedStatement- " + ps.toString() + " -for the MySQL Database!", ChatColor.RED);
                    e.printStackTrace();
                }
            }
        }, 0L);
    }

    /**
     * Executes an SQL Querry
     * @param sql The SQL Querry
     */
    public ResultSet executeQuerry(String sql){
        return executeQuerry(prepareStatement(sql));
    }

    /**
     * Executes a PreparedStatement Querry
     * @param ps The PreparedStatement Querry
     * @see PreparedStatement
     */
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
     * Creates a PreparedStatement
     * @param sql The SQL Code
     * @return The PreparedStatement
     * @see PreparedStatement
     */
    public PreparedStatement prepareStatement(String sql){
        PreparedStatement ps = null;
        if(c == null) return null;
        try {
            if(c.isClosed()) connect();
            if(c == null) return null;
            ps = c.prepareStatement(sql);
        } catch(SQLException e) {
            General.sendColoredMessage(this.plugin, "Failed to prepare Statement- " + sql + " -for the MySQL Database!", ChatColor.RED);
            e.printStackTrace();
        }
        return ps;
    }

    /**
     * Sets the String of a PreparedStatement
     * @param ps The PreparedStatement
     * @param index The index of the String
     * @param string The String
     * @return The PreparedStatement
     * @see PreparedStatement
     */
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
     * Sets the int of a PreparedStatement
     * @param ps The PreparedStatement
     * @param index The index of the int
     * @param i The int
     * @return The PreparedStatement
     * @see PreparedStatement
     */
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
     * Sets the boolean of a PreparedStatement
     * @param ps The PreparedStatement
     * @param index The index of the boolean
     * @param b The boolean
     * @return The PreparedStatement
     * @see PreparedStatement
     */
    public PreparedStatement setBoolean(PreparedStatement ps, int index, boolean b) {
        try {
            ps.setBoolean(index, b);
        } catch(SQLException e) {
            General.sendColoredMessage(this.plugin, "Failed to set boolean " + b + " at " + index + " for PreparedStatement- " + ps.toString() + " -for the MySQL Database!", ChatColor.RED);
            e.printStackTrace();
        }
        return ps;
    }
}
