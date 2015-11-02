package com.j0ach1mmall3.jlib;

import com.j0ach1mmall3.jlib.integration.Placeholders;
import com.j0ach1mmall3.jlib.integration.UpdateChecker;
import com.j0ach1mmall3.jlib.methods.General;
import com.j0ach1mmall3.jlib.minigameapi.MinigameAPI;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	private boolean vaultPermission;
    private boolean vaultEco;
    private boolean vaultChat;
    private boolean placeholderAPI;
    private MinigameAPI api;
	private PluginManager pm;
	private ServicesManager sm;
	public void onEnable(){
        UpdateChecker checker = new UpdateChecker(6603, this.getDescription().getVersion());
        if (checker.checkUpdate()) {
            General.sendColoredMessage(this, "A new update is available!", ChatColor.GOLD);
            General.sendColoredMessage(this, "Version " + checker.getVersion() + " (Current: " + this.getDescription().getVersion() + ")", ChatColor.GOLD);
        } else {
            General.sendColoredMessage(this, "You are up to date!", ChatColor.GREEN);
        }
        this.pm = this.getServer().getPluginManager();
        this.sm = this.getServer().getServicesManager();
		if(this.pm.getPlugin("Vault") != null){
			if(this.sm.getRegistration(net.milkbowl.vault.permission.Permission.class) != null){
                this.vaultPermission = true;
	            General.sendColoredMessage(this, "Successfully hooked into Vault Permissions for extended functionality", ChatColor.GREEN);
	        } else {
	        	General.sendColoredMessage(this, "No Vault Permission Registration found, some placeholders may not work!", ChatColor.GOLD);
                this.vaultPermission = false;
	        }
			if(this.sm.getRegistration(net.milkbowl.vault.chat.Chat.class) != null){
                this.vaultChat = true;
	            General.sendColoredMessage(this, "Successfully hooked into Vault Chat for extended functionality", ChatColor.GREEN);
	        } else {
	        	General.sendColoredMessage(this, "No Vault Chat Registration found, some placeholders may not work!", ChatColor.GOLD);
                this.vaultChat = false;
	        }
			if(this.sm.getRegistration(net.milkbowl.vault.economy.Economy.class) != null){
                this.vaultEco = true;
	            General.sendColoredMessage(this, "Successfully hooked into Vault Economy for extended functionality", ChatColor.GREEN);
	        } else {
	        	General.sendColoredMessage(this, "No Vault Economy Registration found, some placeholders may not work!", ChatColor.GOLD);
                this.vaultEco = false;
	        }
		} else {
            this.vaultPermission = false;
            this.vaultEco = false;
            this.vaultChat = false;
			General.sendColoredMessage(this, "No Vault Economy Registration found, some placeholders may not work!", ChatColor.RED);
		}
        if(this.pm.getPlugin("PlaceholderAPI") != null) {
            this.placeholderAPI = true;
            General.sendColoredMessage(this, "Successfully hooked into PlaceholderAPI for more Placeholders", ChatColor.GREEN);
        } else {
            this.placeholderAPI = false;
            General.sendColoredMessage(this, "PlaceholderAPI not found, switching over to default Placeholders", ChatColor.GOLD);
        }
        new Placeholders(this);
        this.api = new MinigameAPI(this);
        new JoinListener(this);
	}

    public boolean isVaultPermission() {
        return this.vaultPermission;
    }

    public boolean isVaultEco() {
        return this.vaultEco;
    }

    public boolean isVaultChat() {
        return this.vaultChat;
    }

    public boolean isPlaceholderAPI() {
        return this.placeholderAPI;
    }

    public net.milkbowl.vault.permission.Permission getPermission(){
		if(!this.vaultPermission){
			return null;
		}
		return this.sm.getRegistration(net.milkbowl.vault.permission.Permission.class).getProvider();
	}
	
	public net.milkbowl.vault.chat.Chat getChat(){
		if(!this.vaultChat){
			return null;
		}
		return this.sm.getRegistration(net.milkbowl.vault.chat.Chat.class).getProvider();
	}
	
	public net.milkbowl.vault.economy.Economy getEconomy(){
		if(!this.vaultEco){
			return null;
		}
		return this.sm.getRegistration(net.milkbowl.vault.economy.Economy.class).getProvider();
	}

    public MinigameAPI getApi() {
        return this.api;
    }
}
