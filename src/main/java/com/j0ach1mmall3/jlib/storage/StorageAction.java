package com.j0ach1mmall3.jlib.storage;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 16/01/16
 */
public class StorageAction {
    private final String timeStamp = new SimpleDateFormat("[dd-MM HH:mm:ss]").format(new Date(System.currentTimeMillis()));
    private final Type type;
    private final String[] data;
    private boolean success;

    public StorageAction(Type type, String... data) {
        this.type = type;
        this.data = data;
    }

    public Type getType() {
        return this.type;
    }

    public String[] getData() {
        return this.data;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String toString() {
        return this.timeStamp + " StorageAction: " + this.type + " Data: " + Arrays.toString(this.data) + " Success: " + this.success;
    }

    public enum Type {
        FILE_SAVE,
        FILE_SAVEDEFAULT,
        FILE_GET,
        FILE_RELOAD,
        JSON_SAVE,
        JSON_SAVEDEFAULT,
        JSON_GET,
        MONGO_CONNECT,
        MONGO_DISCONNECT,
        MONGO_COMMAND,
        MONGO_STORE,
        MONGO_GET,
        MONGO_UPDATE,
        MYSQL_CONNECT,
        REDIS_CONNECT,
        REDIS_DISCONNECT,
        REDIS_SET,
        REDIS_SETMULTIPLE,
        REDIS_GET,
        REDIS_GETMULTIPLE,
        REDIS_EXISTS,
        SQL_DISCONNECT,
        SQL_EXECUTE,
        SQL_UPDATE,
        SQL_QUERY,
        SQL_PREPARESTATEMENT,
        SQL_SETSTRING,
        SQL_SETINT,
        SQL_SETBOOLEAN,
        SQLITE_CONNECT,
    }
}
