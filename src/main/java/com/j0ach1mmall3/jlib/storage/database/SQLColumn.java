package com.j0ach1mmall3.jlib.storage.database;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 6/11/2015
 */
public class SQLColumn {
    private String name;
    private SQLDataType type;
    private int size;

    /**
     * Constructs a new SQLColumn
     * @param name The name of the Column
     * @param type The DataType of the Column
     * @param size The size of the Column
     * @see com.j0ach1mmall3.jlib.storage.database.SQLColumn.SQLDataType
     */
    public SQLColumn(String name, SQLDataType type, int size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    /**
     * Returns the name of the Colunm
     * @return The name of the Column
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the Type of the Column
     * @return The Type of the Column
     * @see com.j0ach1mmall3.jlib.storage.database.SQLColumn.SQLDataType
     */
    public SQLDataType getType() {
        return type;
    }

    /**
     * Returns the size of the Column
     * @return The size of the Column
     */
    public int getSize() {
        return size;
    }

    public enum SQLDataType {
        VARCHAR,
        INTEGER,
        BOOLEAN,
        SMALLINT,
        BIGINT,
        ARRAY;

        SQLDataType() {

        }
    }
}
