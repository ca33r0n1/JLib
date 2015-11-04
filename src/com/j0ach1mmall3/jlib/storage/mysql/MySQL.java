package com.j0ach1mmall3.jlib.storage.mysql;

import com.j0ach1mmall3.jlib.methods.General;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.*;

public final class MySQL {
    private final JavaPlugin plugin;
	private final String user;
	private final String database;
	private final String password;
	private final String port;
	private final String hostname;
    private Connection c;

	public MySQL(JavaPlugin plugin, String hostname, String port, String database, String user, String password) {
        this.plugin = plugin;
		this.hostname = hostname;
		this.port = port;
		this.database = database;
		this.user = user;
		this.password = password;
        this.c = this.connect();

	}

    private Connection connect(){
        try {
            return DriverManager.getConnection("jdbc:mysql://" + this.hostname + ":" + this.port + "/" + this.database, this.user, this.password);
        } catch(SQLException e) {
            General.sendColoredMessage(this.plugin, "Failed to connect to the MySQL Database using following credentials:", ChatColor.RED);
            General.sendColoredMessage(this.plugin, "HostName: " + this.hostname, ChatColor.GOLD);
            General.sendColoredMessage(this.plugin, "Port: " + this.port, ChatColor.GOLD);
            General.sendColoredMessage(this.plugin, "Database: " + this.database, ChatColor.GOLD);
            General.sendColoredMessage(this.plugin, "User: " + this.user, ChatColor.GOLD);
            General.sendColoredMessage(this.plugin, "Password: =REDACTED=", ChatColor.GOLD);
            return null;
        }
    }

    @SuppressWarnings("deprecation")
    public void execute(final String sql){
        execute(prepareStatement(sql));
    }

    @SuppressWarnings("deprecation")
    public void execute(final PreparedStatement ps){
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    if (ps.isClosed()) return;
                    ps.execute();
                } catch (SQLException e) {
                    General.sendColoredMessage(MySQL.this.plugin, "Failed to execute PreparedStatement- " + ps.toString() + " -for the MySQL Database!", ChatColor.RED);
                    e.printStackTrace();
                }
            }
        }, 0L);
    }

    @SuppressWarnings("deprecation")
    public void executeUpdate(final String sql){
        executeUpdate(prepareStatement(sql));
    }

    @SuppressWarnings("deprecation")
    public void executeUpdate(final PreparedStatement ps){
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    if(ps.isClosed()) return;
                    ps.executeUpdate();
                } catch (SQLException e) {
                    General.sendColoredMessage(MySQL.this.plugin, "Failed to update PreparedStatement- " + ps.toString() + " -for the MySQL Database!", ChatColor.RED);
                    e.printStackTrace();
                }
            }
        }, 0L);
    }

    public ResultSet executeQuerry(String sql){
        return executeQuerry(prepareStatement(sql));
    }

    public ResultSet executeQuerry(PreparedStatement ps){
        ResultSet rs = null;
        try {
            if(!ps.isClosed()) {
                rs =  ps.executeQuery();
            }
        } catch(SQLException e) {
            General.sendColoredMessage(this.plugin, "Failed to querry PreparedStatement- " + ps.toString() + " -for the MySQL Database!", ChatColor.RED);
            e.printStackTrace();
        }
        return rs;
    }

    public PreparedStatement prepareStatement(String sql){
        PreparedStatement ps = null;
        if(c == null) return null;
        try {
            if(c.isClosed()){
                c = connect();
            }
            if(c == null) return null;
            ps = c.prepareStatement(sql);
        } catch(SQLException e) {
            General.sendColoredMessage(this.plugin, "Failed to prepare Statement- " + sql + " -for the MySQL Database!", ChatColor.RED);
            e.printStackTrace();
        }
        return ps;
    }

    public PreparedStatement setString(PreparedStatement ps, int index, String s){
        try {
            ps.setString(index, s);
        } catch(SQLException e) {
            General.sendColoredMessage(this.plugin, "Failed to set String " + s + " at " + index + "for PreparedStatement- " + ps.toString() + " -for the MySQL Database!", ChatColor.RED);
            e.printStackTrace();
        }
        return ps;
    }

    public PreparedStatement setInt(PreparedStatement ps, int index, int i){
        try {
            ps.setInt(index, i);
        } catch(SQLException e) {
            General.sendColoredMessage(this.plugin, "Failed to set Int " + i + " at " + index + "for PreparedStatement- " + ps.toString() + " -for the MySQL Database!", ChatColor.RED);
            e.printStackTrace();
        }
        return ps;
    }

    public PreparedStatement setBoolean(PreparedStatement ps, int index, Boolean b){
        try {
            ps.setBoolean(index, b);
        } catch(SQLException e) {
            General.sendColoredMessage(this.plugin, "Failed to set Boolean " + b + " at " + index + "for PreparedStatement- " + ps.toString() + " -for the MySQL Database!", ChatColor.RED);
            e.printStackTrace();
        }
        return ps;
    }
}
