package com.j0ach1mmall3.jlib.storage.mysql;

import com.j0ach1mmall3.jlib.methods.General;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.*;

public class MySQL {
    private final JavaPlugin plugin;
	private final String user;
	private final String database;
	private final String password;
	private final String port;
	private final String hostname;
    private Connection c;


	/**
	 * Creates a new MySQL instance
	 * 
	 * @param plugin
	 *            Plugin instance
	 * @param hostname
	 *            Name of the host
	 * @param port
	 *            Port number
	 * @param database
	 *            Database name
	 * @param user
	 *            Username
	 * @param password
	 *            Password
	 */
	public MySQL(JavaPlugin plugin, String hostname, String port, String database, String user, String password) {
        this.plugin = plugin;
		this.hostname = hostname;
		this.port = port;
		this.database = database;
		this.user = user;
		this.password = password;
        this.c = connect();

	}

    private Connection connect(){
        try {
            return DriverManager.getConnection("jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database, this.user, this.password);
        } catch(SQLException e) {
            General.sendColoredMessage(plugin, "Failed to connect to the MySQL Database using following credentials:", ChatColor.RED);
            General.sendColoredMessage(plugin, "HostName: " + hostname, ChatColor.GOLD);
            General.sendColoredMessage(plugin, "Port: " + port, ChatColor.GOLD);
            General.sendColoredMessage(plugin, "Database: " + database, ChatColor.GOLD);
            General.sendColoredMessage(plugin, "User: " + user, ChatColor.GOLD);
            General.sendColoredMessage(plugin, "Password: =REDACTED=", ChatColor.GOLD);
            return null;
        }
    }

    @SuppressWarnings("deprecation")
    public void execute(final String sql){
        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    if (c.isClosed()) {
                        c = connect();
                    }
                    Statement s = c.createStatement();
                    s.execute(sql);
                } catch (SQLException e) {
                    General.sendColoredMessage(plugin, "Failed to execute SQL- " + sql + " -for the MySQL Database!", ChatColor.RED);
                    e.printStackTrace();
                }
            }
        }, 0L);
    }

    @SuppressWarnings("deprecation")
    public void execute(final PreparedStatement ps){
        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    ps.execute();
                } catch (SQLException e) {
                    General.sendColoredMessage(plugin, "Failed to execute PreparedStatement- " + ps.toString() + " -for the MySQL Database!", ChatColor.RED);
                    e.printStackTrace();
                }
            }
        }, 0L);
    }

    @SuppressWarnings("deprecation")
    public void executeUpdate(final String sql){
        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    if (c.isClosed()) {
                        c = connect();
                    }
                    Statement s = c.createStatement();
                    s.executeUpdate(sql);
                } catch (SQLException e) {
                    General.sendColoredMessage(plugin, "Failed to update SQL- " + sql + " -for the MySQL Database!", ChatColor.RED);
                    e.printStackTrace();
                }
            }
        }, 0L);
    }

    @SuppressWarnings("deprecation")
    public void executeUpdate(final PreparedStatement ps){
        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    ps.executeUpdate();
                } catch (SQLException e) {
                    General.sendColoredMessage(plugin, "Failed to update PreparedStatement- " + ps.toString() + " -for the MySQL Database!", ChatColor.RED);
                    e.printStackTrace();
                }
            }
        }, 0L);
    }

    public ResultSet executeQuerry(String sql){
        ResultSet rs = null;
        try {
            if(c.isClosed()){
                this.c = connect();
            }
            Statement s = c.createStatement();
            rs =  s.executeQuery(sql);
        } catch(SQLException e) {
            General.sendColoredMessage(plugin, "Failed to querry SQL- " + sql + " -for the MySQL Database!", ChatColor.RED);
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet executeQuerry(PreparedStatement ps){
        ResultSet rs = null;
        try {
            rs =  ps.executeQuery();
        } catch(SQLException e) {
            General.sendColoredMessage(plugin, "Failed to querry PreparedStatement- " + ps.toString() + " -for the MySQL Database!", ChatColor.RED);
            e.printStackTrace();
        }
        return rs;
    }

    public PreparedStatement prepareStatement(String sql){
        PreparedStatement ps = null;
        try {
            if(c.isClosed()){
                this.c = connect();
            }
            ps = c.prepareStatement(sql);
        } catch(SQLException e) {
            General.sendColoredMessage(plugin, "Failed to prepare Statement- " + sql + " -for the MySQL Database!", ChatColor.RED);
            e.printStackTrace();
        }
        return ps;
    }

    public PreparedStatement setString(PreparedStatement ps, int index, String s){
        try {
            ps.setString(index, s);
        } catch(SQLException e) {
            General.sendColoredMessage(plugin, "Failed to set String " + s + " at " + index + "for PreparedStatement- " + ps.toString() + " -for the MySQL Database!", ChatColor.RED);
            e.printStackTrace();
        }
        return ps;
    }
}
