package com.j0ach1mmall3.jlib;

import com.j0ach1mmall3.jlib.integration.MetricsLite;
import com.j0ach1mmall3.jlib.integration.updatechecker.AsyncUpdateChecker;
import com.j0ach1mmall3.jlib.integration.updatechecker.UpdateCheckerResult;
import com.j0ach1mmall3.jlib.integration.vault.ChatHook;
import com.j0ach1mmall3.jlib.integration.vault.EconomyHook;
import com.j0ach1mmall3.jlib.integration.vault.PermissionHook;
import com.j0ach1mmall3.jlib.integration.vault.VaultHook;
import com.j0ach1mmall3.jlib.logging.JLogger;
import com.j0ach1mmall3.jlib.minigameapi.MinigameAPI;
import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since Unknown
 */
public class Main extends JavaPlugin {
    private boolean placeholderAPI;
    private MinigameAPI api;
    private JoinListener joinListener;
    private final JLogger jLogger = new JLogger(this);

	public void onEnable() {
        AsyncUpdateChecker checker = new AsyncUpdateChecker(this, 6603, this.getDescription().getVersion());
        checker.checkUpdate(new CallbackHandler<UpdateCheckerResult>() {
            @Override
            public void callback(UpdateCheckerResult updateCheckerResult) {
                switch (updateCheckerResult.getType()) {
                    case NEW_UPDATE:
                        Main.this.jLogger.log(ChatColor.GOLD + "A new update is available!");
                        Main.this.jLogger.log(ChatColor.GOLD + "Version " + updateCheckerResult.getNewVersion() + " (Current: " + Main.this.getDescription().getVersion() + ")");
                        break;
                    case UP_TO_DATE:
                        Main.this.jLogger.log(ChatColor.GREEN + "You are up to date!");
                        break;
                    case ERROR:
                        Main.this.jLogger.log(ChatColor.RED + "An error occured while trying to check for updates on spigotmc.org!");
                        break;
                }
            }
        });
        try {
            MetricsLite metricsLite = new MetricsLite(this);
            metricsLite.start();
        } catch (Exception e) {
            this.jLogger.log(ChatColor.RED + "An error occured while starting MetricsLite!");
            e.printStackTrace();
        }
		if(Bukkit.getPluginManager().getPlugin("Vault") != null){
            VaultHook hook = new PermissionHook();
			if(hook.isRegistered()){
                this.jLogger.log(ChatColor.GREEN + "Successfully hooked into Vault Permissions for extended functionality");
	        } else {
                this.jLogger.log(ChatColor.GOLD + "No Vault Permission Registration found, some placeholders may not work!");
	        }
            hook = new ChatHook();
			if(hook.isRegistered()){
                this.jLogger.log(ChatColor.GREEN + "Successfully hooked into Vault Chat for extended functionality");
	        } else {
                this.jLogger.log(ChatColor.GOLD + "No Vault Chat Registration found, some placeholders may not work!");
	        }
            hook = new EconomyHook();
			if(hook.isRegistered()){
                this.jLogger.log(ChatColor.GREEN + "Successfully hooked into Vault Economy for extended functionality");
	        } else {
                this.jLogger.log(ChatColor.GOLD + "No Vault Economy Registration found, some placeholders may not work!");
	        }
		} else this.jLogger.log(ChatColor.RED + "Vault not found, some placeholders may not work!");
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            this.placeholderAPI = true;
            this.jLogger.log(ChatColor.GREEN + "Successfully hooked into PlaceholderAPI for more Placeholders");
        } else {
            this.placeholderAPI = false;
            this.jLogger.log(ChatColor.GOLD + "PlaceholderAPI not found, switching over to default Placeholders");
        }
        this.api = new MinigameAPI(this);
        this.joinListener = new JoinListener(this);
	}

    public void onDisable() {
        // Just to be on the safe side
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.closeInventory();
        }
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

    /**
     * Returns the JoinListener instance
     * @return The JoinListener instance
     */
    public JoinListener getJoinListener() {
        return this.joinListener;
    }
}
