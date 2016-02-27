package com.j0ach1mmall3.jlib.integration.updatechecker;

import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 6/12/15
 */
public final class AsyncUpdateChecker {
    private final Plugin plugin;
    private int resourceID;
    private String currentVersion;

    /**
     * Constructs a new AsyncUpdateChecker
     * @param plugin The JavaPlugin instance associated with this AsyncUpdateChecker
     * @param resourceID The Spigot Resource ID for this Update Checker
     * @param currentVersion The current version to compare the update to
     */
    public AsyncUpdateChecker(JavaPlugin plugin, int resourceID, String currentVersion) {
        this.plugin = plugin;
        this.resourceID = resourceID;
        this.currentVersion = currentVersion;
    }

    /**
     * Returns the Plugin instance associated with this AsyncUpdateChecker
     * @return The Plugin instance
     */
    public Plugin getPlugin() {
        return this.plugin;
    }

    /**
     * Returns the Resource ID of this UpdateChecker
     * @return The Resource ID
     */
    public int getResourceID() {
        return this.resourceID;
    }

    /**
     * Sets the Resource ID of this UpdateChecker
     * @param resourceID The Resource ID
     */
    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    /**
     * Returns the Current Version of this UpdateChecker
     * @return The current version
     */
    public String getCurrentVersion() {
        return this.currentVersion;
    }

    /**
     * Sets the Current Version of this UpdateChecker
     * @param currentVersion The current version
     */
    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    /**
     * Check for an Update
     * @param callbackHandler The CallbackHandler to callback the result
     */
    @SuppressWarnings("deprecation")
    public void checkUpdate(final CallbackHandler<UpdateCheckerResult> callbackHandler) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection con = (HttpURLConnection) new URL("http://www.spigotmc.org/api/general.php").openConnection();
                    con.setDoOutput(true);
                    con.setRequestMethod("POST");
                    con.getOutputStream().write(("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=" + AsyncUpdateChecker.this.resourceID).getBytes("UTF-8"));
                    String version = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
                    if(version.equalsIgnoreCase(AsyncUpdateChecker.this.currentVersion)) callbackHandler.callback(new UpdateCheckerResult(UpdateCheckerResult.ResultType.UP_TO_DATE, ""));
                    else callbackHandler.callback(new UpdateCheckerResult(UpdateCheckerResult.ResultType.NEW_UPDATE, version));
                }catch (Exception e){
                    callbackHandler.callback(new UpdateCheckerResult(UpdateCheckerResult.ResultType.ERROR, ""));
                }
            }
        }, 0L);
    }
}
