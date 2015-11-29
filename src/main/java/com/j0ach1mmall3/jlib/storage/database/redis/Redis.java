package com.j0ach1mmall3.jlib.storage.database.redis;

import com.j0ach1mmall3.jlib.methods.General;
import com.j0ach1mmall3.jlib.storage.database.Database;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.Jedis;

import java.util.List;

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
        this.jedis = this.getConnection();
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
            Jedis j = new Jedis(this.hostName, this.port);
            j.auth(this.password);
            return j;
        } catch (Exception e) {
            General.sendColoredMessage(this.plugin, "Failed to connect to the Redis Database using following credentials:", ChatColor.RED);
            General.sendColoredMessage(this.plugin, "HostName: " + this.hostName, ChatColor.GOLD);
            General.sendColoredMessage(this.plugin, "Port: " + this.port, ChatColor.GOLD);
            General.sendColoredMessage(this.plugin, "Password: =REDACTED=", ChatColor.GOLD);
            return null;
        }
    }

    /**
     * Sets a Key to a value
     * @param key The Key to set
     * @param value The value to set to the Key
     */
    @SuppressWarnings("deprecation")
    public void set(final String key, final String value) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                Redis.this.jedis.set(key, value);
            }
        }, 0L);
    }

    /**
     * Sets Keys to values
     * @param keysvalues The Keys and values to set
     */
    @SuppressWarnings("deprecation")
    public void set(final String... keysvalues) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                Redis.this.jedis.mset(keysvalues);
            }
        }, 0L);
    }

    /**
     * Returns a value from a Key
     * @param key The Key of which to get the value
     * @return The value
     * @deprecated {@link Redis#get(String, RedisCallbackHandler)}
     */
    @Deprecated
    public String get(String key) {
        return this.jedis.get(key);
    }

    /**
     * Returns a value from a Key
     * @param key The Key of which to get the value
     * @param callbackHandler The Callback Handler
     */
    @SuppressWarnings("deprecation")
    public void get(final String key, final RedisCallbackHandler callbackHandler) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                callbackHandler.callback(Redis.this.jedis.get(key));
            }
        }, 0L);
    }

    /**
     * Returns all the values of the Keys
     * @param keys They keys of which to get the values
     * @return The values
     * @deprecated {@link Redis#get(RedisCallbackHandler, String...)}
     */
    @Deprecated
    public List<String> get(String... keys) {
        return this.jedis.mget(keys);
    }

    /**
     * Returns a value from a Key
     * @param callbackHandler The Callback Handler
     * @param keys They keys of which to get the values
     */
    @SuppressWarnings("deprecation")
    public void get(final RedisCallbackHandler callbackHandler, final String... keys) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                callbackHandler.callback(Redis.this.jedis.mget(keys));
            }
        }, 0L);
    }

    /**
     * Returns wether a Key exists
     * @param key The Key to check
     * @deprecated {@link Redis#exists(String, RedisCallbackHandler)}
     */
    @Deprecated
    public boolean exists(String key) {
        return this.jedis.exists(key);
    }

    /**
     * Returns wether a Key exists
     * @param key The Key to check
     * @param callbackHandler The Callback Handler
     */
    @SuppressWarnings("deprecation")
    public void exists(final String key, final RedisCallbackHandler callbackHandler) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                callbackHandler.callback(Redis.this.jedis.exists(key));
            }
        }, 0L);
    }
}
