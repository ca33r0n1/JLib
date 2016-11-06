package com.j0ach1mmall3.jlib.storage.file.yaml;

import com.j0ach1mmall3.jlib.storage.DataType;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 21/11/15
 */
public final class StorageConfigLoader<P extends JavaPlugin> extends ConfigLoader<P> {
    private DataType type;
    private final String databaseHost;
    private final int databasePort;
    private final String databaseDatabase;
    private final String databaseUser;
    private final String databasePassword;
    private final String databasePrefix;

    /**
     * Constructs a new Storage ConfigLoader
     *
     * @param plugin The JavaPlugin associated with this Storage Config
     */
    public StorageConfigLoader(P plugin) {
        super("storage.yml", plugin);
        try {
            this.type = DataType.valueOf(this.config.getString("Type"));
        } catch (IllegalArgumentException e) {
            this.type = DataType.FILE;
        }
        this.databaseHost = this.config.getString("Database.Host");
        this.databasePort = this.config.getInt("Database.Port");
        this.databaseDatabase = this.config.getString("Database.Database");
        this.databaseUser = this.config.getString("Database.User");
        this.databasePassword = this.config.getString("Database.Password");
        this.databasePrefix = this.config.getString("Database.Prefix");
    }

    /**
     * Returns the selected DataType
     *
     * @return The DataType
     */
    public DataType getType() {
        return this.type;
    }

    /**
     * Returns the Database Host Address
     *
     * @return The Database Host Address
     */
    public String getDatabaseHost() {
        return this.databaseHost;
    }

    /**
     * Returns the Database Port
     *
     * @return The Database Port
     */
    public int getDatabasePort() {
        return this.databasePort;
    }

    /**
     * Returns the Database Database Name
     *
     * @return The Database Database Name
     */
    public String getDatabaseDatabase() {
        return this.databaseDatabase;
    }

    /**
     * Returns the Database User
     *
     * @return The Database User
     */
    public String getDatabaseUser() {
        return this.databaseUser;
    }

    /**
     * Returns the Database Password
     *
     * @return The Database Password
     */
    public String getDatabasePassword() {
        return this.databasePassword;
    }

    /**
     * Returns the Database Table prefix
     *
     * @return The Database Table prefix
     */
    public String getDatabasePrefix() {
        return this.databasePrefix;
    }
}
