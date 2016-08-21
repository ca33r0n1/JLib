package com.j0ach1mmall3.jlib.storage.database.mongodb;

import com.j0ach1mmall3.jlib.logging.JLogger;
import com.j0ach1mmall3.jlib.storage.StorageAction;
import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import com.j0ach1mmall3.jlib.storage.database.Database;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/15
 */
public final class MongoDB<P extends JavaPlugin> extends Database<P> {
    private MongoClient client;

    /**
     * Constructs a new MongoDB instance, shouldn't be used externally, use {@link MongoDBLoader} instead
     * @param plugin The JavaPlugin associated with the MongoDB Database
     * @param hostName The host name of the MongoDB Server
     * @param port The port of the MongoDB Server
     * @param database The name of the MongoDB Database
     * @param user The user to use
     * @param password The password to use
     */
    MongoDB(P plugin, String hostName, int port, String database, String user, String password) {
        super(plugin, hostName, port, database, user, password);
    }

    /**
     * Connects to the MongoDB Database
     */
    @Override
    public void connect() {
        this.client = this.getConnection();
    }

    /**
     * Disconnects from the MongoDB Database
     */
    @Override
    public void disconnect() {
        StorageAction storageAction = new StorageAction(StorageAction.Type.MONGO_DISCONNECT, this.hostName, String.valueOf(this.port), this.name, this.user);
        try {
            this.executor.shutdown();
            this.client.close();
            storageAction.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            storageAction.setSuccess(false);
        }
        this.actions.add(storageAction);
    }

    /**
     * Returns the MongoClient for the MongoDB Database
     * @return The MongoClient
     */
    private MongoClient getConnection() {
        StorageAction storageAction = new StorageAction(StorageAction.Type.MONGO_GETCONNECTION, this.hostName, String.valueOf(this.port), this.name, this.user);
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient(new ServerAddress(this.hostName, this.port), Collections.singletonList(MongoCredential.createCredential(this.user, this.name, this.password.toCharArray())));
            storageAction.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            this.jLogger.log(ChatColor.RED + "Failed to connect to the MongoDB Database using following credentials:", JLogger.LogLevel.MINIMAL);
            this.jLogger.log(ChatColor.RED + "HostName: " + this.hostName, JLogger.LogLevel.MINIMAL);
            this.jLogger.log(ChatColor.RED + "Port: " + this.port, JLogger.LogLevel.MINIMAL);
            this.jLogger.log(ChatColor.RED + "Database: " + this.name, JLogger.LogLevel.MINIMAL);
            this.jLogger.log(ChatColor.RED + "User: " + this.user, JLogger.LogLevel.MINIMAL);
            storageAction.setSuccess(false);
        }
        this.actions.add(storageAction);
        return mongoClient;
    }

    /**
     * Performs a command on the Database
     * @param command The command to perform
     */
    @SuppressWarnings("deprecation")
    public void performCommand(final String command) {
        final StorageAction storageAction = new StorageAction(StorageAction.Type.MONGO_COMMAND, command);
        storageAction.setSuccess(true);
        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                MongoDB.this.client.getDB(MongoDB.this.name).command(command);
                MongoDB.this.actions.add(storageAction);
            }
        });
    }

    /**
     * Stores an Object in a Collection
     * @param object The Object to store
     * @param collection The Collection to store it in
     */
    @SuppressWarnings("deprecation")
    public void storeObject(final DBObject object, final String collection) {
        final StorageAction storageAction = new StorageAction(StorageAction.Type.MONGO_STORE, Arrays.toString(object.toMap().entrySet().toArray()), collection);
        storageAction.setSuccess(true);
        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                MongoDB.this.client.getDB(MongoDB.this.name).getCollection(collection).insert(object);
                MongoDB.this.actions.add(storageAction);
            }
        });
    }

    /**
     * Returns an Object in a Collection, based on a reference Object
     * @param reference The reference Object
     * @param collection The Collection
     * @param callbackHandler The Callback Handler
     */
    @SuppressWarnings("deprecation")
    public void getObject(final DBObject reference, final String collection, final CallbackHandler<DBObject> callbackHandler) {
        final StorageAction storageAction = new StorageAction(StorageAction.Type.MONGO_GET, Arrays.toString(reference.toMap().entrySet().toArray()), collection);
        storageAction.setSuccess(true);
        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                callbackHandler.callback(MongoDB.this.client.getDB(MongoDB.this.name).getCollection(collection).findOne(reference));
                MongoDB.this.actions.add(storageAction);
            }
        });
    }

    /**
     * Returns multiple Objects in a Collection, based on a reference Object
     * @param reference The reference Object
     * @param collection The Collection
     * @param callbackHandler The Callback Handler
     */
    @SuppressWarnings("deprecation")
    public void getObjects(final DBObject reference, final String collection, final CallbackHandler<List<DBObject>> callbackHandler) {
        final StorageAction storageAction = new StorageAction(StorageAction.Type.MONGO_GET, Arrays.toString(reference.toMap().entrySet().toArray()), collection);
        storageAction.setSuccess(true);
        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                try(DBCursor cursor = MongoDB.this.client.getDB(MongoDB.this.name).getCollection(collection).find(reference)) {
                    List<DBObject> objects = new ArrayList<>();
                    while(cursor.hasNext()) {
                        objects.add(cursor.next());
                    }
                    MongoDB.this.actions.add(storageAction);
                    callbackHandler.callback(objects);
                }
            }
        });
    }

    /**
     * Updates an Object in a Collection, based on a reference Object
     * @param object The Object to update
     * @param reference The reference Object
     * @param collection The Collection
     */
    @SuppressWarnings("deprecation")
    public void updateObject(final DBObject object, final DBObject reference, final String collection) {
        final StorageAction storageAction = new StorageAction(StorageAction.Type.MONGO_UPDATE, Arrays.toString(object.toMap().entrySet().toArray()), Arrays.toString(reference.toMap().entrySet().toArray()), collection);
        storageAction.setSuccess(true);
        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                MongoDB.this.getObject(reference, collection, new CallbackHandler<DBObject>() {
                    @Override
                    public void callback(DBObject o) {
                        if(o == null) MongoDB.this.storeObject(object, collection);
                        else MongoDB.this.client.getDB(MongoDB.this.name).getCollection(collection).update(o, object);
                        MongoDB.this.actions.add(storageAction);
                    }
                });
            }
        });
    }
}
