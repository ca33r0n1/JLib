package com.j0ach1mmall3.jlib.storage.database.mongodb;

import com.j0ach1mmall3.jlib.methods.General;
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
    private DB mongoDatabase;

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
        this.mongoDatabase = getConnection();
    }

    /**
     * Disconnects from the MongoDB Database
     */
    public void disconnect() {
        this.mongoDatabase.getMongo().close();
    }

    /**
     * Returns the Connection for the MongoDB Database
     * @return The Connection
     * @see DB
     */
    private DB getConnection() {
        try {
            return new MongoClient(new ServerAddress(hostName, port), Collections.singletonList(MongoCredential.createCredential(user, database, password.toCharArray()))).getDB(database);
        } catch (Exception e) {
            General.sendColoredMessage(plugin, "Failed to connect to the MongoDB Database using following credentials:", ChatColor.RED);
            General.sendColoredMessage(plugin, "HostName: " + this.hostName, ChatColor.GOLD);
            General.sendColoredMessage(plugin, "Port: " + this.port, ChatColor.GOLD);
            General.sendColoredMessage(plugin, "Database: " + this.database, ChatColor.GOLD);
            General.sendColoredMessage(plugin, "User: " + this.user, ChatColor.GOLD);
            General.sendColoredMessage(plugin, "Password: =REDACTED=", ChatColor.GOLD);
            return null;
        }
    }

    /**
     * Creates a Collection with the given name
     * @param name The name of the Collection
     * @see DBCollection
     */
    @SuppressWarnings("deprecation")
    public void createCollection(final String name) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                mongoDatabase.createCollection(name, null);
            }
        }, 0L);
    }

    /**
     * Performs a command on the Database
     * @param command The command to perform
     */
    @SuppressWarnings("deprecation")
    public void performCommand(final String command) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                mongoDatabase.command(command);
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
        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                mongoDatabase.getCollection(collection).insert(object);
            }
        }, 0L);
    }

    /**
     * Returns an Object in a Collection, based on a reference Object
     * @param reference The reference Object
     * @param collection The Collection
     * @return The found Object
     * @see DBObject
     */
    public DBObject getObject(DBObject reference, String collection) {
        return mongoDatabase.getCollection(collection).findOne(reference);
    }

    /**
     * Returns multiple Objects in a Collection, based on a reference Object
     * @param reference The reference Object
     * @param collection The Collection
     * @return The found Object
     * @see DBObject
     */
    public List<DBObject> getObjects(DBObject reference, String collection) {
        DBCursor cursor = mongoDatabase.getCollection(collection).find(reference);
        List<DBObject> objects = new ArrayList<>();
        while(cursor.hasNext()) {
            objects.add(cursor.next());
        }
        return objects;
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
        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                DBObject found = getObject(reference, collection);
                if(found == null) {
                    storeObject(object, collection);
                    return;
                }
                mongoDatabase.getCollection(collection).update(found, object);

            }
        }, 0L);
    }
}
