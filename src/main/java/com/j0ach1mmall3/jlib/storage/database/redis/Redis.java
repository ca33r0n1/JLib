package com.j0ach1mmall3.jlib.storage.database.redis;

import com.j0ach1mmall3.jlib.logging.JLogger;
import com.j0ach1mmall3.jlib.storage.StorageAction;
import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import com.j0ach1mmall3.jlib.storage.database.Database;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Arrays;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/15
 */
public final class Redis<P extends JavaPlugin> extends Database<P> {
    private final JedisPool jedisPool;

    /**
     * Constructs a new Redis instance, shouldn't be used externally, use {@link RedisLoader} instead
     *
     * @param plugin   The JavaPlugin associated with the Redis Database
     * @param hostName The host name of the Redis Server
     * @param port     The port of the Redis Server
     * @param password The password to use
     */
    Redis(P plugin, String hostName, int port, String password) {
        super(plugin, hostName, port, "Redis Database", null, password);
        this.jedisPool = new JedisPool(hostName, port);
    }

    /**
     * Connects to the Redis Database
     */
    @Override
    public void connect() {
        // NOP
    }

    /**
     * Disconnects from the Redis Database
     */
    @Override
    public void disconnect() {
        StorageAction storageAction = new StorageAction(StorageAction.Type.REDIS_DISCONNECT, this.hostName, String.valueOf(this.port), this.name);
        try {
            this.executor.shutdown();
            this.jedisPool.destroy();
            storageAction.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            storageAction.setSuccess(false);
        }
        this.actions.add(storageAction);
    }

    /**
     * Returns the Connection for the Redis Database
     *
     * @return The Connection
     */
    private Jedis getConnection() {
        StorageAction storageAction = new StorageAction(StorageAction.Type.REDIS_GETCONNECTION, this.hostName, String.valueOf(this.port), this.name);
        try {
            Jedis jedis = this.jedisPool.getResource();
            jedis.auth(this.password);
            return jedis;
        } catch (Exception e) {
            e.printStackTrace();
            this.jLogger.log(ChatColor.RED + "Failed to connect to the Redis Database using following credentials:", JLogger.LogLevel.MINIMAL);
            this.jLogger.log(ChatColor.RED + "HostName: " + this.hostName, JLogger.LogLevel.MINIMAL);
            this.jLogger.log(ChatColor.RED + "Port: " + this.port, JLogger.LogLevel.MINIMAL);
            storageAction.setSuccess(false);
            return null;
        }
    }

    /**
     * Sets a Key to a value
     *
     * @param key   The Key to set
     * @param value The value to set to the Key
     */
    public void set(String key, String value) {
        StorageAction storageAction = new StorageAction(StorageAction.Type.REDIS_SET, key, value);
        this.executor.execute(() -> {
            try (Jedis jedis = this.getConnection()) {
                jedis.set(key, value);
                storageAction.setSuccess(true);
            } catch (Exception e) {
                e.printStackTrace();
                storageAction.setSuccess(false);
            }
            this.actions.add(storageAction);
        });
    }

    /**
     * Sets Keys to values
     *
     * @param keysvalues The Keys and values to set
     */
    public void set(String... keysvalues) {
        StorageAction storageAction = new StorageAction(StorageAction.Type.REDIS_SETMULTIPLE, Arrays.toString(keysvalues));
        this.executor.execute(() -> {
            try (Jedis jedis = this.getConnection()) {
                jedis.mset(keysvalues);
                storageAction.setSuccess(true);
            } catch (Exception e) {
                e.printStackTrace();
                storageAction.setSuccess(false);
            }
            this.actions.add(storageAction);
        });
    }

    /**
     * Returns a value from a Key
     *
     * @param key             The Key of which to get the value
     * @param callbackHandler The Callback Handler
     */
    public void get(String key, CallbackHandler<String> callbackHandler) {
        StorageAction storageAction = new StorageAction(StorageAction.Type.REDIS_GET, key);
        this.executor.execute(() -> {
            try (Jedis jedis = this.getConnection()) {
                callbackHandler.callback(jedis.get(key));
                storageAction.setSuccess(true);
            } catch (Exception e) {
                e.printStackTrace();
                storageAction.setSuccess(false);
            }
            this.actions.add(storageAction);
        });
    }

    /**
     * Returns a value from a Key
     *
     * @param callbackHandler The Callback Handler
     * @param keys            They keys of which to get the values
     */
    public void get(CallbackHandler<List<String>> callbackHandler, String... keys) {
        StorageAction storageAction = new StorageAction(StorageAction.Type.REDIS_GETMULTIPLE, Arrays.toString(keys));
        this.executor.execute(() -> {
            try (Jedis jedis = this.getConnection()) {
                callbackHandler.callback(jedis.mget(keys));
                storageAction.setSuccess(true);
            } catch (Exception e) {
                e.printStackTrace();
                storageAction.setSuccess(false);
            }
            this.actions.add(storageAction);
        });
    }

    /**
     * Returns whether a Key exists
     *
     * @param key             The Key to check
     * @param callbackHandler The Callback Handler
     */
    public void exists(String key, CallbackHandler<Boolean> callbackHandler) {
        StorageAction storageAction = new StorageAction(StorageAction.Type.REDIS_EXISTS, key);
        this.executor.execute(() -> {
            try (Jedis jedis = this.getConnection()) {
                callbackHandler.callback(jedis.exists(key));
                storageAction.setSuccess(true);
            } catch (Exception e) {
                e.printStackTrace();
                storageAction.setSuccess(false);
            }
            this.actions.add(storageAction);
        });
    }
}
