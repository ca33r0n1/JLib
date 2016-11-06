package com.j0ach1mmall3.jlib.storage.database.mysql;

import com.j0ach1mmall3.jlib.storage.database.HikariSQLDatabase;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since Unknown
 */
public final class MySQL<P extends JavaPlugin> extends HikariSQLDatabase<P> {
    /**
     * Constructs a new MySQL instance, shouldn't be used externally, use {@link MySQLLoader} instead
     *
     * @param plugin   The JavaPlugin associated with the MySQL Database
     * @param hostName The host name of the MySQL Server
     * @param port     The port of the MySQL Server
     * @param database The name of the MySQL Database
     * @param user     The user to use
     * @param password The password to use
     */
    MySQL(P plugin, String hostName, int port, String database, String user, String password) {
        super(plugin, hostName, port, database, user, password);
        this.dataSource.setJdbcUrl("jdbc:mysql://" + hostName + ':' + port + '/' + database);
    }
}
