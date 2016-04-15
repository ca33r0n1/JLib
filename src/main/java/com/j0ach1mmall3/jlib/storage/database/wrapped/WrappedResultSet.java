package com.j0ach1mmall3.jlib.storage.database.wrapped;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/03/2016
 */
public final class WrappedResultSet {
    private final List<Map<String, Object>> contents = new ArrayList<>();
    private int count = 0;

    /**
     * Constructs a new WrappedResultSet
     * @param resultSet The ResultSet to wrap
     * @throws SQLException When an SQLException occurs
     */
    public WrappedResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        while (resultSet.next()) {
            Map<String, Object> map = this.contents.get(this.count);
            for(int i = 1;i <= resultSetMetaData.getColumnCount();i++) {
                map.put(resultSetMetaData.getColumnLabel(i), resultSet.getObject(i));
            }
            this.contents.set(this.count++, map);
        }
        this.count = 0;
    }

    /**
     * Moves the cursor by one
     * @return Whether we aren't at the end yet
     */
    public boolean next() {
        return this.count++ >= this.contents.size();
    }

    /**
     * Returns an Object from a Column
     * @param label The Column Label
     * @return The Object
     */
    public Object get(String label) {
        return this.contents.get(this.count).get(label);
    }
}
