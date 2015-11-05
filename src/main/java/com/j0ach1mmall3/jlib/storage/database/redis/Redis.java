package com.j0ach1mmall3.jlib.storage.database.redis;

import com.j0ach1mmall3.jlib.methods.General;
import com.j0ach1mmall3.jlib.storage.database.Database;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.Jedis;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/2015
 */
public class Redis extends Database {
    private Jedis jedis;

    /**
     * Constructs a new Redis instance, shouldn't be used externally, use RedisLoader instead
     */
    Redis(JavaPlugin plugin, String hostName, int port, String password) {
        super(plugin, hostName, port, null, null, password);
    }

    /**
     * Connects to the Redis Database
     */
    public void connect() {
        this.jedis = getConnection();
    }

    /**
     * Disconnects from the Redis Database
     */
    public void disconnect() {
        this.jedis.close();
    }

    /**
     * Returns the Connection for the Redis Database
     * @return The Connection
     */
    private Jedis getConnection() {
        try {
            Jedis j = new Jedis(hostName, port);
            j.auth(password);
            return j;
        } catch (Exception e) {
            General.sendColoredMessage(plugin, "Failed to connect to the Redis Database using following credentials:", ChatColor.RED);
            General.sendColoredMessage(plugin, "HostName: " + this.hostName, ChatColor.GOLD);
            General.sendColoredMessage(plugin, "Port: " + this.port, ChatColor.GOLD);
            General.sendColoredMessage(plugin, "Password: =REDACTED=", ChatColor.GOLD);
            return null;
        }
    }

    /**
     * Not implemented yet
     * @param key The Key to set
     * @param value The value to set to the Key
     */
    public void set(String key, String value) {
        throw new NotImplementedException();
    }
}
