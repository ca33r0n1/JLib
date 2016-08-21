package com.j0ach1mmall3.jlib.integration;

import com.j0ach1mmall3.jlib.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author j0ach1mmall3 (businesmessage.j0ach1mmall3@gmail.com)
 * @since Unknown
 */
public final class Placeholders {

    /**
     * Let nobody instantiate this class
     */
    private Placeholders() {
    }

    /**
     * Parses player Placeholders for a message
     * @param message The message that should be parsed
     * @param player The player for which the player Placeholders are intended
     * @return The parsed message
     */
    public static String parse(String message, Player player){
        return JavaPlugin.getPlugin(Main.class).isPlaceholderAPI() ? me.clip.placeholderapi.PlaceholderAPI.setPlaceholders(player, message) : parseInternal(player, message);
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
        if(message == null) return null;
        if(Bukkit.getBukkitVersion().split("\\-")[0].startsWith("1.2") || Bukkit.getBukkitVersion().split("\\-")[0].startsWith("1.3")) return ChatColor.translateAlternateColorCodes('&', message);
        message = message
                .replace("%motd%", Bukkit.getMotd())
                .replace("%server_motd%", Bukkit.getMotd())
                .replace("%servername%", Bukkit.getName())
                .replace("%server_name%", Bukkit.getName())
                .replace("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()))
                .replace("%server_online%", String.valueOf(Bukkit.getOnlinePlayers().size()))
                .replace("%max%", String.valueOf(Bukkit.getMaxPlayers()))
                .replace("%server_max%", String.valueOf(Bukkit.getMaxPlayers()));
        if(player != null) {
            message = message
                    .replace("%playername%", player.getName())
                    .replace("%player_name%", player.getName())
                    .replace("%player_uuid%", player.getUniqueId().toString())
                    .replace("%player_uuid%", player.getUniqueId().toString())
                    .replace("%displayname%", player.getDisplayName())
                    .replace("%player_displayname%", player.getDisplayName())
                    .replace("%health%", String.valueOf(player.getHealth()))
                    .replace("%player_health%", String.valueOf(player.getHealth()))
                    .replace("%X%", String.valueOf(player.getLocation().getBlockX()))
                    .replace("%player_x%", String.valueOf(player.getLocation().getBlockX()))
                    .replace("%Y%", String.valueOf(player.getLocation().getBlockY()))
                    .replace("%player_y%", String.valueOf(player.getLocation().getBlockY()))
                    .replace("%Z%", String.valueOf(player.getLocation().getBlockZ()))
                    .replace("%player_z%", String.valueOf(player.getLocation().getBlockZ()))
                    .replace("%world%", player.getWorld().getName())
                    .replace("%player_world%", player.getWorld().getName())
                    .replace("%level%", String.valueOf(player.getLevel()))
                    .replace("%player_level%", String.valueOf(player.getLevel()))
                    .replace("%exp%", String.valueOf(player.getExp()))
                    .replace("%player_exp%", String.valueOf(player.getExp()))
                    .replace("%ip%", player.getAddress().toString())
                    .replace("%player_ip%", player.getAddress().toString());
        }
        message = ChatColor.translateAlternateColorCodes('&', message);
        return message;
    }
}
