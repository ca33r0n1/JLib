package com.j0ach1mmall3.jlib.storage.database;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 6/11/2015
 */
public class SQLTable {
    private String tableName;
    private SQLColumn[] columns;
    private boolean ifNotExists;

    /**
     * Constructs a new SQLTable
     * @param tableName The name of the Table
     * @param ifNotExists If this is set to false, the Table will always be created
     * @param columns The SQLColumns of the Table
     * @see SQLColumn
     */
    public SQLTable(String tableName, boolean ifNotExists, SQLColumn... columns) {
        this.tableName = tableName;
        this.columns = columns;
        this.ifNotExists = ifNotExists;
    }

    /**
     * Constructs a new SQLTable
     * @param tableName The name of the Table
     * @param columns The SQLColumns of the Table
     * @see SQLColumn
     */
    public SQLTable(String tableName, SQLColumn... columns) {
        this(tableName, true, columns);
    }

    /**
     * Returns the Table name
     * @return The Table name
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Returns the List of SQLColumns
     * @return The List of SQLColumns
     * @see SQLColumn
     */
    public SQLColumn[] getColumns() {
        return columns;
    }

    /**
     * Returns if not exists
     * @return If not exists
     */
    public boolean isIfNotExists() {
        return ifNotExists;
    }
}
