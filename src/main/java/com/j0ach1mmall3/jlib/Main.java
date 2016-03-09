package com.j0ach1mmall3.jlib;

import com.j0ach1mmall3.jlib.integration.MetricsLite;
import com.j0ach1mmall3.jlib.integration.vault.ChatHook;
import com.j0ach1mmall3.jlib.integration.vault.EconomyHook;
import com.j0ach1mmall3.jlib.integration.vault.PermissionHook;
import com.j0ach1mmall3.jlib.logging.JLogger;
import com.j0ach1mmall3.jlib.minigameapi.MinigameAPI;
import com.j0ach1mmall3.jlib.player.tagchanger.TagChanger;
import com.j0ach1mmall3.jlib.plugin.JlibPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since Unknown
 */
public class Main extends JlibPlugin {
    private boolean placeholderAPI;
    private MinigameAPI api;
    private JoinListener joinListener;
    private TagChanger tagChanger;

    @Override
    public void onEnable() {
        this.checkUpdate(6603);
        MetricsLite metricsLite = new MetricsLite(this);
        metricsLite.start();
        if(Bukkit.getPluginManager().getPlugin("Vault") != null) {
            if(new PermissionHook().isRegistered()) this.jLogger.log(ChatColor.GREEN + "Successfully hooked into Vault Permissions for extended functionality", JLogger.LogLevel.NORMAL);
            else this.jLogger.log(ChatColor.GOLD + "No Vault Permission Registration found, some placeholders may not work!", JLogger.LogLevel.NORMAL);
            if(new ChatHook().isRegistered()) this.jLogger.log(ChatColor.GREEN + "Successfully hooked into Vault Chat for extended functionality", JLogger.LogLevel.NORMAL);
            else this.jLogger.log(ChatColor.GOLD + "No Vault Chat Registration found, some placeholders may not work!", JLogger.LogLevel.NORMAL);
            if(new EconomyHook().isRegistered()) this.jLogger.log(ChatColor.GREEN + "Successfully hooked into Vault Economy for extended functionality", JLogger.LogLevel.NORMAL);
            else this.jLogger.log(ChatColor.GOLD + "No Vault Economy Registration found, some placeholders may not work!", JLogger.LogLevel.NORMAL);
        } else this.jLogger.log(ChatColor.RED + "Vault not found, some placeholders may not work!", JLogger.LogLevel.NORMAL);
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            this.placeholderAPI = true;
            this.jLogger.log(ChatColor.GREEN + "Successfully hooked into PlaceholderAPI for more Placeholders", JLogger.LogLevel.NORMAL);
        } else {
            this.placeholderAPI = false;
            this.jLogger.log(ChatColor.GOLD + "PlaceholderAPI not found, switching over to default Placeholders", JLogger.LogLevel.NORMAL);
        }
        if(Bukkit.getPluginManager().getPlugin("ProtocolLib") != null) this.tagChanger = new TagChanger(this);
        this.api = new MinigameAPI(this);
        this.joinListener = new JoinListener(this);
    }

    @Override
    public void onDisable() {
        if(this.tagChanger != null) this.tagChanger.cleanup();
        // Just to be on the safe side
        for(Player p : Bukkit.getOnlinePlayers()) {
            Bukkit.getPluginManager().callEvent(new InventoryCloseEvent(p.getOpenInventory()));
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
