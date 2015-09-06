package com.j0ach1mmall3.jlib.integration;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import com.j0ach1mmall3.jlib.Main;

public class Placeholders {
    private static Main plugin;
    public Placeholders(Main plugin) {
        this.plugin = plugin;
    }
	public static String parse(String s, Player p){
        if(plugin.isPlaceholderAPI()) {
            return me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(p, s);
        } else {
            return parseInternal(p, s);
        }
	}
	
	public static String parse(String s){
		return parse(s, null);
	}

    private static String parseInternal(Player p, String s) {
        s = s.replace("%serverip%", Bukkit.getIp())
                .replace("%motd%", Bukkit.getMotd())
                .replace("%servername%", Bukkit.getName())
                .replace("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()))
                .replace("%max%", String.valueOf(Bukkit.getMaxPlayers()));
        if(p != null) {
            s = s.replace("%playername%", p.getName())
                    .replace("%displayname%", p.getDisplayName())
                    .replace("%health%", String.valueOf(p.getHealth()))
                    .replace("%X%", String.valueOf(p.getLocation().getBlockX()))
                    .replace("%Y%", String.valueOf(p.getLocation().getBlockY()))
                    .replace("%Z%", String.valueOf(p.getLocation().getBlockZ()))
                    .replace("%world%", p.getWorld().getName())
                    .replace("%level%", String.valueOf(p.getLevel()))
                    .replace("%exp%", String.valueOf(p.getExp()))
                    .replace("%ip%", p.getAddress().toString());
            if (plugin.isVaultPermission()) {
                if (((net.milkbowl.vault.permission.Permission) plugin.getPermission()).hasGroupSupport()) {
                    s = s.replace("%group%", String.valueOf(((net.milkbowl.vault.permission.Permission) plugin.getPermission()).getPrimaryGroup(p)));
                }
            }
            if (plugin.isVaultChat()) {
                s = s.replace("%prefix%", ((net.milkbowl.vault.chat.Chat) plugin.getChat()).getPlayerSuffix(p))
                        .replace("%suffix%", ((net.milkbowl.vault.chat.Chat) plugin.getChat()).getPlayerSuffix(p));
            }
            if (plugin.isVaultEco()) {
                s = s.replace("%balance%", String.valueOf(((net.milkbowl.vault.economy.Economy) plugin.getEconomy()).getBalance(p)));
            }
        }
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }
}
