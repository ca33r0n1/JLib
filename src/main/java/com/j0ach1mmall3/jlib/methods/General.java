package com.j0ach1mmall3.jlib.methods;

import com.j0ach1mmall3.jlib.logging.JLogger;
import com.j0ach1mmall3.jlib.player.JLibPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.UUID;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since Unknown
 */
public final class General {

    /**
     * Let nobody instantiate this class
     */
    private General() {
    }

    /**
     * Logs a colored message to the console
     * @param plugin The JavaPlugin instance (Used for the prefix)
     * @param message The message that should be logged
     * @param color The ChatColor used to color the message
     * @deprecated {@link JLogger#log(String)}
     */
    @Deprecated
    public static void sendColoredMessage(JavaPlugin plugin, String message, ChatColor color){
        ConsoleCommandSender c = plugin.getServer().getConsoleSender();
        c.sendMessage('[' + plugin.getDescription().getName() + "] " + color + message);
    }

    /**
     * Logs a message to the console
     * @param plugin The JavaPlugin instance (Used for the prefix)
     * @param message The message that should be logged
     * @deprecated {@link JLogger#log(String)}
     */
    @Deprecated
    public static void sendMessage(JavaPlugin plugin, String message) {
        ConsoleCommandSender c = plugin.getServer().getConsoleSender();
        c.sendMessage('[' + plugin.getDescription().getName() + "] " + message);
    }

    /**
     * Returns whether 2 ItemStacks are similar (Everything matches except amount)
     * @param item1 The 1st ItemStack
     * @param item2 The 2nd ItemStack
     * @return Whether they are similar
     */
    public static boolean areSimilar(ItemStack item1, ItemStack item2) {
        return areSimilar(item1, item2, false);
    }

    /**
     * Returns whether 2 ItemStacks are similar (Everything matches except amount)
     * @param item1 The 1st ItemStack
     * @param item2 The 2nd ItemStack
     * @param ignoreDurability Whether everything should match except amount and durability
     * @return Whether they are similar
     */
    public static boolean areSimilar(ItemStack item1, ItemStack item2, boolean ignoreDurability) {
        if(item1 == null || item2 == null) return Objects.equals(item1, item2);
        if(item1.getType() != item2.getType()) return false;
        if(item1.getDurability() != item2.getDurability() && !ignoreDurability) return false;
        if(item1.getItemMeta() instanceof org.bukkit.inventory.meta.SkullMeta || item2.getItemMeta() instanceof org.bukkit.inventory.meta.SkullMeta) return ((org.bukkit.inventory.meta.SkullMeta) item1.getItemMeta()).hasOwner() ? ((org.bukkit.inventory.meta.SkullMeta) item1.getItemMeta()).getOwner().equals(((org.bukkit.inventory.meta.SkullMeta) item2.getItemMeta()).getOwner()) : !((org.bukkit.inventory.meta.SkullMeta) item2.getItemMeta()).hasOwner();
        else if(item1.getItemMeta() instanceof org.bukkit.inventory.meta.PotionMeta || item2.getItemMeta() instanceof org.bukkit.inventory.meta.PotionMeta) return ((org.bukkit.inventory.meta.PotionMeta) item1.getItemMeta()).hasCustomEffects() ? ((org.bukkit.inventory.meta.PotionMeta) item1.getItemMeta()).getCustomEffects().equals(((org.bukkit.inventory.meta.PotionMeta) item2.getItemMeta()).getCustomEffects()) : !((org.bukkit.inventory.meta.PotionMeta) item2.getItemMeta()).hasCustomEffects();
        else if(item1.getItemMeta() instanceof org.bukkit.inventory.meta.BookMeta || item2.getItemMeta() instanceof org.bukkit.inventory.meta.BookMeta) {
            boolean title = ((org.bukkit.inventory.meta.BookMeta) item1.getItemMeta()).hasTitle() ? ((org.bukkit.inventory.meta.BookMeta) item1.getItemMeta()).getTitle().equals(((org.bukkit.inventory.meta.BookMeta) item2.getItemMeta()).getTitle()) : !((org.bukkit.inventory.meta.BookMeta) item2.getItemMeta()).hasTitle();
            boolean author = ((org.bukkit.inventory.meta.BookMeta) item1.getItemMeta()).hasAuthor() ? ((org.bukkit.inventory.meta.BookMeta) item1.getItemMeta()).getAuthor().equals(((org.bukkit.inventory.meta.BookMeta) item2.getItemMeta()).getAuthor()) : !((org.bukkit.inventory.meta.BookMeta) item2.getItemMeta()).hasAuthor();
            boolean pages = ((org.bukkit.inventory.meta.BookMeta) item1.getItemMeta()).hasPages() ? ((org.bukkit.inventory.meta.BookMeta) item1.getItemMeta()).getPages().equals(((org.bukkit.inventory.meta.BookMeta) item2.getItemMeta()).getPages()) : !((org.bukkit.inventory.meta.BookMeta) item2.getItemMeta()).hasPages();
            return title && author && pages;
        } else return Bukkit.getItemFactory().equals(item1.getItemMeta(), item2.getItemMeta());
    }

    /**
     * Returns whether a player has a 'Custom Permission'
     * Custom Permissions are permissions not defined in the plugin.yml
     * However, this means that the '*' symbol normally doesn't work
     * This method will test for every possible combination with the '*' symbol
     * @param player The player to test
     * @param permission The permission node to test
     * @return Wether the player has the permission or one of it's parents
     * @deprecated {@link JLibPlayer#hasCustomPermission(String)}
     */
    @Deprecated
    public static boolean hasCustomPermission(Player player, String permission) {
        if (player.hasPermission(permission) || player.hasPermission("*")) return true;
        String[] components = permission.split("\\.");
        String perm = components[0] + '.';
        for (int i = 1; i < components.length; i++) {
            if (player.hasPermission(perm + '*')) return true;
            if (player.hasPermission('-' + perm + '*')) return false;
            perm = perm + components[i] + '.';
        }
        return false;
    }

    /**
     * Broadcasts a Block Change
     * @param location The Location of the Block Change
     * @param material The Material of the Block Change
     * @param data The Block Data of the Block Change
     */
    @SuppressWarnings("deprecation")
    public static void broadcastBlockChange(Location location, Material material, byte data) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.sendBlockChange(location, material, data);
        }
    }

    /**
     * Returns an online player by name
     * @param name The name of the player
     * @param caseSensitive Should the checking be Case Sensitive?
     * @return The player that was found, or null if no player is found
     */
    public static Player getPlayerByName(String name, boolean caseSensitive) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(caseSensitive && p.getName().equals(name)) return p;
            if(!caseSensitive && p.getName().equalsIgnoreCase(name)) return p;
        }
        return null;
    }

    /**
     * Returns an online player by uuid
     * @param uuid The uuid of the player
     * @return The player that was found, or null if no player is found
     */
    public static Player getPlayerByUuid(UUID uuid) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.getUniqueId().equals(uuid)) return p;
        }
        return null;
    }

    /**
     * Rounds up a number
     * @param from The starting number
     * @param to The number you want to round up to
     * @return The rounded up number
     */
    public static int roundUp(int from, int to) {
        return (from + (to-1)) / to * to;
    }

    /**
     * Freezes a player
     * @param player The player to freeze
     * @deprecated {@link JLibPlayer#freeze(boolean)}
     */
    @Deprecated
    public static void freezePlayer(Player player) {
        new JLibPlayer(player).freeze(false);
    }

    /**
     * Unfreezes a player
     * @param player The player to unfreeze
     * @deprecated {@link JLibPlayer#unFreeze()}
     */
    @Deprecated
    public static void unfreezePlayer(Player player) {
        new JLibPlayer(player).unFreeze();
    }

    /**
     * Returns whether a player is frozen
     * @param player The player to check
     * @return Whether the player is frozen
     * @deprecated {@link JLibPlayer#isFrozen()}
     */
    @Deprecated
    public static boolean isFrozen(Player player) {
        return new JLibPlayer(player).isFrozen();
    }
}
