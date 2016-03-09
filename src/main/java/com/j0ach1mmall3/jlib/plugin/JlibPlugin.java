package com.j0ach1mmall3.jlib.plugin;

import com.j0ach1mmall3.jlib.integration.updatechecker.AsyncUpdateChecker;
import com.j0ach1mmall3.jlib.integration.updatechecker.UpdateCheckerResult;
import com.j0ach1mmall3.jlib.logging.JLogger;
import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import com.j0ach1mmall3.jlib.storage.file.yaml.ConfigLoader;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 8/03/2016
 */
public abstract class JlibPlugin extends JavaPlugin {
    protected final JLogger jLogger = new JLogger(this);

    protected ConfigLoader config;

    /**
     * Checks for an update on spigotmc
     * @param resourceId The resource id
     */
    protected void checkUpdate(int resourceId) {
        AsyncUpdateChecker checker = new AsyncUpdateChecker(this, resourceId, this.getDescription().getVersion());
        checker.checkUpdate(new CallbackHandler<UpdateCheckerResult>() {
            @Override
            public void callback(UpdateCheckerResult o) {
                switch (o.getType()) {
                    case NEW_UPDATE:
                        JlibPlugin.this.jLogger.log(ChatColor.GOLD + "A new update is available!");
                        JlibPlugin.this.jLogger.log(ChatColor.GOLD + "Version " + o.getNewVersion() + " (Current: " + JlibPlugin.this.getDescription().getVersion() + ')');
                        break;
                    case UP_TO_DATE:
                        JlibPlugin.this.jLogger.log(ChatColor.GREEN + "You are up to date!");
                        break;
                    case ERROR:
                        JlibPlugin.this.jLogger.log(ChatColor.RED + "An error occured while trying to check for updates on spigotmc.org!");
                        break;
                }
            }
        });
    }

    /**
     * Returns the JLogger for this JLibPlugin
     * @return The JLogger
     */
    public final JLogger getjLogger() {
        return this.jLogger;
    }

    /**
     * Returns the default Config
     * @return The default Config
     */
    public ConfigLoader getBabies() {
        return this.config;
    }
}
