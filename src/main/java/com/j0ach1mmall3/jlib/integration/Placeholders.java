package com.j0ach1mmall3.jlib.integration;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.integration.vault.ChatHook;
import com.j0ach1mmall3.jlib.integration.vault.EconomyHook;
import com.j0ach1mmall3.jlib.integration.vault.PermissionHook;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * @author j0ach1mmall3 (businesmessage.j0ach1mmall3@gmail.com)
 * @since Unknown
 */
public final class Placeholders {
    /**
     * Parses player Placeholders for a message
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
     * Parses non player-specific Placeholders for a message
     * @param message The message that should be parsed
     * @return The parsed message
     */
	public static String parse(String message){
		return parse(message, null);
	}

    /**
     * Parses native player Placeholders for a message
     * @param player The player for which the player Placeholders are intended
     * @param message The message that should be parsed
     * @return The parsed message
     */
    private static String parseInternal(Player player, String message) {
        if(Bukkit.getBukkitVersion().split("\\-")[0].startsWith("1.2") || Bukkit.getBukkitVersion().split("\\-")[0].startsWith("1.3")) return ChatColor.translateAlternateColorCodes('&', message);
        message = message.replace("%serverip%", Bukkit.getIp())
                .replace("%motd%", Bukkit.getMotd())
                .replace("%servername%", Bukkit.getName())
                .replace("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()))
                .replace("%max%", String.valueOf(Bukkit.getMaxPlayers()));
        if(player != null) {
            message = message.replace("%playername%", player.getName())
                    .replace("%displayname%", player.getDisplayName())
                    .replace("%health%", String.valueOf(player.getHealth()))
                    .replace("%X%", String.valueOf(player.getLocation().getBlockX()))
                    .replace("%Y%", String.valueOf(player.getLocation().getBlockY()))
                    .replace("%Z%", String.valueOf(player.getLocation().getBlockZ()))
                    .replace("%world%", player.getWorld().getName())
                    .replace("%level%", String.valueOf(player.getLevel()))
                    .replace("%exp%", String.valueOf(player.getExp()))
                    .replace("%ip%", player.getAddress().toString());
            PermissionHook permissionHook = new PermissionHook();
            if (permissionHook.isRegistered()) {
                if(!Bukkit.getBukkitVersion().split("\\-")[0].equals("1.2.5")) {
                    if (permissionHook.getPermission().hasGroupSupport()) {
                        message = message.replace("%group%", permissionHook.getPermission().getPrimaryGroup(player));
                    }
                } else {
                    message = message.replace("%group%", permissionHook.getPermission().getPrimaryGroup(player));
                }
            }
            ChatHook chatHook = new ChatHook();
            if (chatHook.isRegistered()) {
                message = message.replace("%prefix%", chatHook.getChat().getPlayerSuffix(player))
                        .replace("%suffix%", chatHook.getChat().getPlayerSuffix(player));
            }
            EconomyHook economyHook = new EconomyHook();
            if (economyHook.isRegistered()) {
                message = message.replace("%balance%", String.valueOf(economyHook.getEconomy().getBalance(player)));
            }
        }
        message = ChatColor.translateAlternateColorCodes('&', message);
        return message;
    }
}
