package com.j0ach1mmall3.jlib.integration;

import org.bukkit.ChatColor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since Unknown
 * @deprecated {@link com.j0ach1mmall3.jlib.integration.updatechecker.AsyncUpdateChecker}
 */
@Deprecated
public final class UpdateChecker {
    private int resourceID;
    private String currentVersion;
    private String version;

    /**
     * Constructs a new UpdateChecker
     * @param resourceID The Spigot Resource ID for this Update Checker
     * @param currentVersion The current version to compare the update to
     */
    public UpdateChecker(int resourceID, String currentVersion) {
        this.resourceID = resourceID;
        this.currentVersion = currentVersion;
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("http://www.spigotmc.org/api/general.php").openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.getOutputStream().write(("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=" + this.resourceID).getBytes("UTF-8"));
            this.version = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
        }catch (Exception e){
            this.version = ChatColor.RED + "Error!";
        }
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
     * @return Wether there is a new update available or not
     */
    public boolean checkUpdate(){
		return !this.version.equalsIgnoreCase(this.currentVersion);
	}

    /**
     * Returns the new version of the update
     * @return The update version
     */
	public String getVersion(){
        return this.version;
	}
}
