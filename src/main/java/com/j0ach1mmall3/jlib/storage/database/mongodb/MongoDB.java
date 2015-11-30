package com.j0ach1mmall3.jlib.storage.database.mongodb;

import com.j0ach1mmall3.jlib.methods.General;
import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import com.j0ach1mmall3.jlib.storage.database.Database;
import com.mongodb.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/2015
 */
public final class MongoDB extends Database {
    private MongoClient client;

    /**
     * Constructs a new MongoDB instance, shouldn't be used externally, use {@link MongoDBLoader} instead
     */
    MongoDB(JavaPlugin plugin, String hostName, int port, String database, String user, String password) {
        super(plugin, hostName, port, database, user, password);
    }

    /**
     * Connects to the MongoDB Database
     */
    public void connect() {
        this.client = this.getConnection();
    }

    /**
     * Disconnects from the MongoDB Database
     */
    public void disconnect() {
        this.client.close();
    }

    /**
     * Returns the Connection for the MongoDB Database
     * @return The Connection
     * @see DB
     */
    private MongoClient getConnection() {
        try {
            return new MongoClient(new ServerAddress(this.hostName, this.port), Collections.singletonList(MongoCredential.createCredential(this.user, this.database, this.password.toCharArray())));
        } catch (Exception e) {
            General.sendColoredMessage(this.plugin, "Failed to connect to the MongoDB Database using following credentials:", ChatColor.RED);
            General.sendColoredMessage(this.plugin, "HostName: " + this.hostName, ChatColor.GOLD);
            General.sendColoredMessage(this.plugin, "Port: " + this.port, ChatColor.GOLD);
            General.sendColoredMessage(this.plugin, "Database: " + this.database, ChatColor.GOLD);
            General.sendColoredMessage(this.plugin, "User: " + this.user, ChatColor.GOLD);
            General.sendColoredMessage(this.plugin, "Password: =REDACTED=", ChatColor.GOLD);
            return null;
        }
    }

    /**
     * Performs a command on the Database
     * @param command The command to perform
     */
    @SuppressWarnings("deprecation")
    public void performCommand(final String command) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                MongoDB.this.client.getDB(MongoDB.this.database).command(command);
            }
        }, 0L);
    }

    /**
     * Stores an Object in a Collection
     * @param object The Object to store
     * @param collection The Collection to store it in
     * @see DBObject
     */
    @SuppressWarnings("deprecation")
    public void storeObject(final DBObject object, final String collection) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                MongoDB.this.client.getDB(MongoDB.this.database).getCollection(collection).insert(object);
            }
        }, 0L);
    }

    /**
     * Returns an Object in a Collection, based on a reference Object
     * @param reference The reference Object
     * @param collection The Collection
     * @return The found Object
     * @deprecated {@link MongoDB#getObject(DBObject, String, CallbackHandler)}
     * @see DBObject
     */
    @Deprecated
    public DBObject getObject(DBObject reference, String collection) {
        return this.client.getDB(this.database).getCollection(collection).findOne(reference);
    }

    /**
     * Returns an Object in a Collection, based on a reference Object
     * @param reference The reference Object
     * @param collection The Collection
     * @param callbackHandler The Callback Handler
     * @see DBObject
     */
    @SuppressWarnings("deprecation")
    public void getObject(final DBObject reference, final String collection, final CallbackHandler<DBObject> callbackHandler) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                callbackHandler.callback(MongoDB.this.client.getDB(MongoDB.this.database).getCollection(collection).findOne(reference));
            }
        }, 0L);
    }

    /**
     * Returns multiple Objects in a Collection, based on a reference Object
     * @param reference The reference Object
     * @param collection The Collection
     * @return The found Object
     * @deprecated {@link MongoDB#getObjects(DBObject, String, CallbackHandler)}
     * @see DBObject
     */
    @Deprecated
    public List<DBObject> getObjects(DBObject reference, String collection) {
        DBCursor cursor = this.client.getDB(this.database).getCollection(collection).find(reference);
        List<DBObject> objects = new ArrayList<>();
        while(cursor.hasNext()) {
            objects.add(cursor.next());
        }
        return objects;
    }

    /**
     * Returns multiple Objects in a Collection, based on a reference Object
     * @param reference The reference Object
     * @param collection The Collection
     * @param callbackHandler The Callback Handler
     * @see DBObject
     */
    @SuppressWarnings("deprecation")
    public void getObjects(final DBObject reference, final String collection, final CallbackHandler<List<DBObject>> callbackHandler) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                DBCursor cursor = MongoDB.this.client.getDB(MongoDB.this.database).getCollection(collection).find(reference);
                List<DBObject> objects = new ArrayList<>();
                while(cursor.hasNext()) {
                    objects.add(cursor.next());
                }
                callbackHandler.callback(objects);
            }
        }, 0L);
    }

    /**
     * Updates an Object in a Collection, based on a reference Object
     * @param object The Object to update
     * @param reference The reference Object
     * @param collection The Collection
     * @see DBObject
     */
    @SuppressWarnings("deprecation")
    public void updateObject(final DBObject object, final DBObject reference, final String collection) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                DBObject found = MongoDB.this.getObject(reference, collection);
                if(found == null) {
                    MongoDB.this.storeObject(object, collection);
                    return;
                }
                MongoDB.this.client.getDB(MongoDB.this.database).getCollection(collection).update(found, object);
            }
        }, 0L);
    }
}
