package com.j0ach1mmall3.jlib.storage.database.redis;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 29/11/15
 */
public interface RedisCallbackHandler {
    void callback(String value);

    void callback(List<String> values);

    void callback(boolean exists);
}
