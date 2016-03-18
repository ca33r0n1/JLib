package com.j0ach1mmall3.jlib.storage.database.redis;

import com.j0ach1mmall3.jlib.storage.StorageAction;
import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import com.j0ach1mmall3.jlib.storage.database.Database;
import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/15
 */
public final class Redis extends Database {
    private final JedisPool jedisPool;
    private final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    /**
     * Constructs a new Redis instance, shouldn't be used externally, use {@link RedisLoader} instead
     * @param plugin The JavaPlugin associated with the Redis Database
     * @param hostName The host name of the Redis Server
     * @param port The port of the Redis Server
     * @param password The password to use
     */
    Redis(JavaPlugin plugin, String hostName, int port, String password) {
        super(plugin, hostName, port, "Redis Database", null, password);
        this.jedisPool = new JedisPool(hostName, port);
    }

    /**
     * Returns the Connection for the Redis Database
     * @return The Connection
     */
    private Jedis getConnection() {
        StorageAction storageAction = new StorageAction(StorageAction.Type.REDIS_GETCONNECTION, this.hostName, String.valueOf(this.port), this.name);
        try {
            Jedis jedis = this.jedisPool.getResource();
            jedis.auth(this.password);
            storageAction.setSuccess(true);
            return jedis;
        } catch (Exception e) {
            e.printStackTrace();
            storageAction.setSuccess(false);
            return null;
        }
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
            this.jedisPool.close();
            storageAction.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            storageAction.setSuccess(false);
        }
        this.actions.add(storageAction);
    }

    /**
     * Sets a Key to a value
     * @param key The Key to set
     * @param value The value to set to the Key
     */
    @SuppressWarnings("deprecation")
    public void set(final String key, final String value) {
        final StorageAction storageAction = new StorageAction(StorageAction.Type.REDIS_SET, key, value);
        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                Redis.this.getConnection().set(key, value);
                storageAction.setSuccess(true);
                Redis.this.actions.add(storageAction);
            }
        });
    }

    /**
     * Sets Keys to values
     * @param keysvalues The Keys and values to set
     */
    @SuppressWarnings("deprecation")
    public void set(final String... keysvalues) {
        final StorageAction storageAction = new StorageAction(StorageAction.Type.REDIS_SETMULTIPLE, Arrays.toString(keysvalues));
        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                Redis.this.getConnection().mset(keysvalues);
                storageAction.setSuccess(true);
                Redis.this.actions.add(storageAction);
            }
        });
    }

    /**
     * Returns a value from a Key
     * @param key The Key of which to get the value
     * @return The value
     * @deprecated {@link Redis#get(String, CallbackHandler)}
     */
    @Deprecated
    public String get(String key) {
        this.jLogger.deprecation();
        this.jLogger.warnIfSync();
        StorageAction storageAction = new StorageAction(StorageAction.Type.REDIS_GET, key);
        storageAction.setSuccess(true);
        this.actions.add(storageAction);
        return this.getConnection().get(key);
    }

    /**
     * Returns a value from a Key
     * @param key The Key of which to get the value
     * @param callbackHandler The Callback Handler
     */
    @SuppressWarnings("deprecation")
    public void get(final String key, final CallbackHandler<String> callbackHandler) {
        final StorageAction storageAction = new StorageAction(StorageAction.Type.REDIS_GET, key);
        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                callbackHandler.callback(Redis.this.getConnection().get(key));
                storageAction.setSuccess(true);
                Redis.this.actions.add(storageAction);
            }
        });
    }

    /**
     * Returns all the values of the Keys
     * @param keys They keys of which to get the values
     * @return The values
     * @deprecated {@link Redis#get(CallbackHandler, String...)}
     */
    @Deprecated
    public List<String> get(String... keys) {
        this.jLogger.deprecation();
        this.jLogger.warnIfSync();
        StorageAction storageAction = new StorageAction(StorageAction.Type.REDIS_GETMULTIPLE, Arrays.toString(keys));
        storageAction.setSuccess(true);
        this.actions.add(storageAction);
        return this.getConnection().mget(keys);
    }

    /**
     * Returns a value from a Key
     * @param callbackHandler The Callback Handler
     * @param keys They keys of which to get the values
     */
    @SuppressWarnings("deprecation")
    public void get(final CallbackHandler<List<String>> callbackHandler, final String... keys) {
        final StorageAction storageAction = new StorageAction(StorageAction.Type.REDIS_GETMULTIPLE, Arrays.toString(keys));
        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                callbackHandler.callback(Redis.this.getConnection().mget(keys));
                storageAction.setSuccess(true);
                Redis.this.actions.add(storageAction);
            }
        });
    }

    /**
     * Returns whether a Key exists
     * @param key The Key to check
     * @return Whether the Key exists
     * @deprecated {@link Redis#exists(String, CallbackHandler)}
     */
    @Deprecated
    public boolean exists(String key) {
        this.jLogger.deprecation();
        this.jLogger.warnIfSync();
        StorageAction storageAction = new StorageAction(StorageAction.Type.REDIS_EXISTS, key);
        storageAction.setSuccess(true);
        this.actions.add(storageAction);
        return this.getConnection().exists(key);
    }

    /**
     * Returns whether a Key exists
     * @param key The Key to check
     * @param callbackHandler The Callback Handler
     */
    @SuppressWarnings("deprecation")
    public void exists(final String key, final CallbackHandler<Boolean> callbackHandler) {
        final StorageAction storageAction = new StorageAction(StorageAction.Type.REDIS_EXISTS, key);
        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                callbackHandler.callback(Redis.this.getConnection().exists(key));
                storageAction.setSuccess(true);
                Redis.this.actions.add(storageAction);
            }
        });
    }
}
