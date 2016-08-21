package com.j0ach1mmall3.jlib.plugin;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.integration.updatechecker.AsyncUpdateChecker;
import com.j0ach1mmall3.jlib.integration.updatechecker.UpdateCheckerResult;
import com.j0ach1mmall3.jlib.logging.DebugInfo;
import com.j0ach1mmall3.jlib.logging.JLogger;
import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import com.j0ach1mmall3.jlib.storage.file.yaml.ConfigLoader;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 8/03/2016
 */
public abstract class JLibPlugin<C extends ConfigLoader> extends JavaPlugin {
    protected final JLogger jLogger = new JLogger(this, JLogger.LogLevel.NORMAL);

    protected C config;

    /**
     * Checks for an update on spigotmc
     * @param resourceId The resource id
     */
    protected final void checkUpdate(int resourceId) {
        AsyncUpdateChecker checker = new AsyncUpdateChecker(this, resourceId, this.getDescription().getVersion());
        checker.checkUpdate(new CallbackHandler<UpdateCheckerResult>() {
            @Override
            public void callback(UpdateCheckerResult o) {
                switch (o.getType()) {
                    case NEW_UPDATE:
                        JLibPlugin.this.jLogger.log(ChatColor.GOLD + "A new update is available!", JLogger.LogLevel.MINIMAL);
                        JLibPlugin.this.jLogger.log(ChatColor.GOLD + "Version " + o.getNewVersion() + " (Current: " + JLibPlugin.this.getDescription().getVersion() + ')', JLogger.LogLevel.MINIMAL);
                        break;
                    case UP_TO_DATE:
                        JLibPlugin.this.jLogger.log(ChatColor.GREEN + "You are up to date!", JLogger.LogLevel.NORMAL);
                        break;
                    case ERROR:
                        JLibPlugin.this.jLogger.log(ChatColor.RED + "An error occured while trying to check for updates on spigotmc.org!", JLogger.LogLevel.MINIMAL);
                        break;
                }
            }
        });
    }

    /**
     * Registers DebugInfo for this JLibPlugin
     * @param debugInfo The DebugInfo
     */
    protected final void registerDebugInfo(DebugInfo debugInfo) {
        JavaPlugin.getPlugin(Main.class).setDebugInfo(this, debugInfo);
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
    public final C getBabies() {
        return this.config;
    }
}
