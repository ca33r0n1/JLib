package com.j0ach1mmall3.jlib.integration;

import com.j0ach1mmall3.jlib.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since Unknown
 */
public final class Placeholders {
    /**
     * Parse player Placeholders for a message
     * @param message The message that should be parsed
     * @param player The player for which the player Placeholders are intended
     * @return The parsed message
     */
	public static String parse(String message, Player player){
        if(((Main) Bukkit.getPluginManager().getPlugin("JLib")).isPlaceholderAPI()) {
            return me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, message);
        } else {
            return parseInternal(player, message);
        }
	}

    /**
     * Parse non player-specific Placeholders for a message
     * @param message The message that should be parsed
     * @return The parsed message
     */
	public static String parse(String message){
		return parse(message, null);
	}

    private static String parseInternal(Player p, String s) {
        if(Bukkit.getBukkitVersion().split("\\-")[0].startsWith("1.2") || Bukkit.getBukkitVersion().split("\\-")[0].startsWith("1.3")) return ChatColor.translateAlternateColorCodes('&', s);
        Main plugin = (Main) Bukkit.getPluginManager().getPlugin("JLib");
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
                if(!Bukkit.getBukkitVersion().split("\\-")[0].equals("1.2.5")) {
                    if (plugin.getPermission().hasGroupSupport()) {
                        s = s.replace("%group%", plugin.getPermission().getPrimaryGroup(p));
                    }
                } else {
                    s = s.replace("%group%", plugin.getPermission().getPrimaryGroup(p));
                }
            }
            if (plugin.isVaultChat()) {
                s = s.replace("%prefix%", plugin.getChat().getPlayerSuffix(p))
                        .replace("%suffix%", plugin.getChat().getPlayerSuffix(p));
            }
            if (plugin.isVaultEco()) {
                s = s.replace("%balance%", String.valueOf(plugin.getEconomy().getBalance(p)));
            }
        }
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }
}
