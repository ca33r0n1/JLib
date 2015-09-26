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
	private PluginManager pm;
	private ServicesManager sm;
	public void onEnable(){
        UpdateChecker checker = new UpdateChecker(6603, getDescription().getVersion());
        if (checker.checkUpdate()) {
            General.sendColoredMessage(this, "A new update is available!", ChatColor.GOLD);
            General.sendColoredMessage(this, "Version " + checker.getVersion() + " (Current: " + getDescription().getVersion() + ")", ChatColor.GOLD);
        } else {
            General.sendColoredMessage(this, "You are up to date!", ChatColor.GREEN);
        }
		pm = getServer().getPluginManager();
		sm = getServer().getServicesManager();
		if(pm.getPlugin("Vault") != null){
			if(sm.getRegistration(net.milkbowl.vault.permission.Permission.class) != null){
	            vaultPermission = true;
	            General.sendColoredMessage(this, "Successfully hooked into Vault Permissions for extended functionality", ChatColor.GREEN);
	        } else {
	        	General.sendColoredMessage(this, "No Vault Permission Registration found, some placeholders may not work!", ChatColor.GOLD);
				vaultPermission = false;
	        }
			if(sm.getRegistration(net.milkbowl.vault.chat.Chat.class) != null){
	            vaultChat = true;
	            General.sendColoredMessage(this, "Successfully hooked into Vault Chat for extended functionality", ChatColor.GREEN);
	        } else {
	        	General.sendColoredMessage(this, "No Vault Chat Registration found, some placeholders may not work!", ChatColor.GOLD);
				vaultChat = false;
	        }
			if(sm.getRegistration(net.milkbowl.vault.economy.Economy.class) != null){
	            vaultEco = true;
	            General.sendColoredMessage(this, "Successfully hooked into Vault Economy for extended functionality", ChatColor.GREEN);
	        } else {
	        	General.sendColoredMessage(this, "No Vault Economy Registration found, some placeholders may not work!", ChatColor.GOLD);
				vaultEco = false;
	        }
		} else {
			vaultPermission = false;
			vaultEco = false;
			vaultChat = false;
			General.sendColoredMessage(this, "No Vault Economy Registration found, some placeholders may not work!", ChatColor.RED);
		}
        if(pm.getPlugin("PlaceholderAPI") != null) {
            placeholderAPI = true;
            General.sendColoredMessage(this, "Successfully hooked into PlaceholderAPI for more Placeholders", ChatColor.GREEN);
        } else {
            placeholderAPI = false;
            General.sendColoredMessage(this, "PlaceholderAPI not found, switching over to default Placeholders", ChatColor.GOLD);
        }
        new Placeholders(this);
        MinigameAPI.setup(this);
        new JoinListener(this);
	}

    public boolean isVaultPermission() {
        return vaultPermission;
    }

    public boolean isVaultEco() {
        return vaultEco;
    }

    public boolean isVaultChat() {
        return vaultChat;
    }

    public boolean isPlaceholderAPI() {
        return placeholderAPI;
    }

    public net.milkbowl.vault.permission.Permission getPermission(){
		if(!vaultPermission){
			return null;
		}
		return sm.getRegistration(net.milkbowl.vault.permission.Permission.class).getProvider();
	}
	
	public net.milkbowl.vault.chat.Chat getChat(){
		if(!vaultChat){
			return null;
		}
		return sm.getRegistration(net.milkbowl.vault.chat.Chat.class).getProvider();
	}
	
	public net.milkbowl.vault.economy.Economy getEconomy(){
		if(!vaultEco){
			return null;
		}
		return sm.getRegistration(net.milkbowl.vault.economy.Economy.class).getProvider();
	}
}
