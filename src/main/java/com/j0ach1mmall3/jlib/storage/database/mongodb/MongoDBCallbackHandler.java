package com.j0ach1mmall3.jlib.storage.database.mongodb;

import com.mongodb.DBObject;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 29/11/15
 */
public interface MongoDBCallbackHandler {
    void callback(DBObject object);

    void callback(List<DBObject> objects);
}
