package com.j0ach1mmall3.jlib;

import com.j0ach1mmall3.jlib.integration.MetricsLite;
import com.j0ach1mmall3.jlib.integration.updatechecker.AsyncUpdateChecker;
import com.j0ach1mmall3.jlib.integration.updatechecker.UpdateCheckerResult;
import com.j0ach1mmall3.jlib.integration.vault.ChatHook;
import com.j0ach1mmall3.jlib.integration.vault.EconomyHook;
import com.j0ach1mmall3.jlib.integration.vault.PermissionHook;
import com.j0ach1mmall3.jlib.integration.vault.VaultHook;
import com.j0ach1mmall3.jlib.methods.General;
import com.j0ach1mmall3.jlib.minigameapi.MinigameAPI;
import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since Unknown
 */
public class Main extends JavaPlugin{
    private boolean placeholderAPI;
    private MinigameAPI api;
	public void onEnable(){
        AsyncUpdateChecker checker = new AsyncUpdateChecker(this, 6603, this.getDescription().getVersion());
        checker.checkUpdate(new CallbackHandler<UpdateCheckerResult>() {
            @Override
            public void callback(UpdateCheckerResult updateCheckerResult) {
                switch (updateCheckerResult.getType()) {
                    case NEW_UPDATE:
                        General.sendColoredMessage(Main.this, "A new update is available!", ChatColor.GOLD);
                        General.sendColoredMessage(Main.this, "Version " + updateCheckerResult.getNewVersion() + " (Current: " + Main.this.getDescription().getVersion() + ")", ChatColor.GOLD);
                        break;
                    case UP_TO_DATE:
                        General.sendColoredMessage(Main.this, "You are up to date!", ChatColor.GREEN);
                        break;
                    case ERROR:
                        General.sendColoredMessage(Main.this, "An error occured while trying to check for updates on spigotmc.org!", ChatColor.RED);
                        break;
                }
            }
        });
        try {
            MetricsLite metricsLite = new MetricsLite(this);
            metricsLite.start();
        } catch (Exception e) {
            General.sendColoredMessage(Main.this, "An error occured while starting MetricsLite!", ChatColor.RED);
            e.printStackTrace();
        }
		if(Bukkit.getPluginManager().getPlugin("Vault") != null){
            VaultHook hook = new PermissionHook();
			if(hook.isRegistered()){
	            General.sendColoredMessage(this, "Successfully hooked into Vault Permissions for extended functionality", ChatColor.GREEN);
	        } else {
	        	General.sendColoredMessage(this, "No Vault Permission Registration found, some placeholders may not work!", ChatColor.GOLD);
	        }
            hook = new ChatHook();
			if(hook.isRegistered()){
	            General.sendColoredMessage(this, "Successfully hooked into Vault Chat for extended functionality", ChatColor.GREEN);
	        } else {
	        	General.sendColoredMessage(this, "No Vault Chat Registration found, some placeholders may not work!", ChatColor.GOLD);
	        }
            hook = new EconomyHook();
			if(hook.isRegistered()){
	            General.sendColoredMessage(this, "Successfully hooked into Vault Economy for extended functionality", ChatColor.GREEN);
	        } else {
	        	General.sendColoredMessage(this, "No Vault Economy Registration found, some placeholders may not work!", ChatColor.GOLD);
	        }
		} else {
			General.sendColoredMessage(this, "Vault not found, some placeholders may not work!", ChatColor.RED);
		}
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            this.placeholderAPI = true;
            General.sendColoredMessage(this, "Successfully hooked into PlaceholderAPI for more Placeholders", ChatColor.GREEN);
        } else {
            this.placeholderAPI = false;
            General.sendColoredMessage(this, "PlaceholderAPI not found, switching over to default Placeholders", ChatColor.GOLD);
        }
        this.api = new MinigameAPI(this);
        new JoinListener(this);
	}

    /**
     * Returns whether PlaceholderAPI is found
     * @return Wether PlaceholderAPI is found
     */
    public boolean isPlaceholderAPI() {
        return this.placeholderAPI;
    }

    /**
     * Returns the MinigameAPI instance
     * @return The MinigameAPI instance
     */
    public MinigameAPI getApi() {
        return this.api;
    }
}
