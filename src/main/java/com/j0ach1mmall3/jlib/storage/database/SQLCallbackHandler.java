package com.j0ach1mmall3.jlib.storage.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 29/11/15
 */
public interface SQLCallbackHandler {
    void callback(ResultSet resultSet);

    void callback(PreparedStatement preparedStatement);
}
