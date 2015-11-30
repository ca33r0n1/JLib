package com.j0ach1mmall3.jlib.storage.database;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 30/11/15
 */
public interface CallbackHandler<T> {
    /**
     * Calls back an Object
     * @param t The Object
     */
    void callback(T t);
}
