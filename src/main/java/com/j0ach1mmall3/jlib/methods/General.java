package com.j0ach1mmall3.jlib.methods;

import com.j0ach1mmall3.jlib.logging.JLogger;
import org.bukkit.*;
import org.bukkit.Note.Tone;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since Unknown
 */
public final class General {
    /**
     * Logs a colored message to the console
     * @param plugin The JavaPlugin instance (Used for the prefix)
     * @param message The message that should be logged
     * @param color The ChatColor used to color the message
     * Replaced by {@link JLogger#log(String)}
     */
	public static void sendColoredMessage(JavaPlugin plugin, String message, ChatColor color){
		ConsoleCommandSender c = plugin.getServer().getConsoleSender();
		c.sendMessage("[" + plugin.getDescription().getName() + "] " + color + message);
	}

    /**
     * Logs a message to the console
     * @param plugin The JavaPlugin instance (Used for the prefix)
     * @param message The message that should be logged
     * Replaced by {@link JLogger#log(String)}
     */
    public static void sendMessage(JavaPlugin plugin, String message) {
        sendColoredMessage(plugin, message, ChatColor.RESET);
    }

    /**
     * Returns whether a player has a 'Custom Permission'
     * Custom Permissions are permissions not defined in the plugin.yml
     * However, this means that the '*' symbol normally doesn't work
     * This method will test for every possible combination with the '*' symbol
     * @param player The player to test
     * @param permission The permission node to test
     * @return Wether the player has the permission or one of it's parents
     */
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
     * Plays a Sound for a player at a Location
     * @param player The player for whom the Sound would play
     * @param sound The Sound that should be played
     * @param location The Location where the Sound should be played
     * @see Sound
     * @deprecated Replaced by {@link Sounds#playSound(Player, Sound, Location)}
     */
    @Deprecated
    public static void playSound(Player player, Sound sound, Location location) {
        new JLogger().deprecation();
        Sounds.playSound(player, sound, location);
	}

    /**
     * Broadcasts a Sound at a Location
     * @param sound The Sound that should be played
     * @param location The Location where the Sound should be played
     * @see Sound
     * @deprecated Replaced by {@link Sounds#broadcastSound(Sound, Location)}
     */
    @Deprecated
    public static void broadcastSound(Sound sound, Location location) {
        new JLogger().deprecation();
        Sounds.broadcastSound(sound, location);
	}

    /**
     * Plays a Sound for a player at his current Location
     * @param player The player for whom the Sound would play
     * @param sound The Sound that should be played
     * @see Sound
     * @deprecated Replaced by {@link Sounds#playSound(Player, Sound)}
     */
    @Deprecated
	public static void playSound(Player player, Sound sound) {
        new JLogger().deprecation();
		Sounds.playSound(player, sound);
	}

    /**
     * Broadcasts a Sound
     * @param sound The Sound that should be played
     * @see Sound
     * @deprecated Replaced by {@link Sounds#broadcastSound(Sound)}
     */
    @Deprecated
	public static void broadcastSound(Sound sound) {
        new JLogger().deprecation();
		Sounds.broadcastSound(sound);
	}

    /**
     * Plays a Note for a player at a Location
     * @param player The player for whom the Note would play
     * @param location The Location where the Note should be played
     * @param instrument The Instrument of the Note
     * @param note The Note that should be played
     * @see Instrument
     * @see Note
     * @deprecated Replaced by {@link Notes#playNote(Player, Location, Instrument, Note)}
     */
    @Deprecated
    public static void playNote(Player player, Location location, Instrument instrument, Note note) {
        new JLogger().deprecation();
        Notes.playNote(player, location, instrument, note);
    }

    /**
     * Broadcasts a Note at a Location
     * @param location The Location where the Note should be played
     * @param instrument The Instrument of the Note
     * @param note The Note
     * @see Instrument
     * @see Note
     * @deprecated Replaced by {@link Notes#broadcastNote(Location, Instrument, Note)}
     */
    @Deprecated
    public static void broadcastNote(Location location, Instrument instrument, Note note) {
        new JLogger().deprecation();
        Notes.broadcastNote(location, instrument, note);
    }

    /**
     * Plays a Note for a player at his current Location
     * @param player The player for whom the Note would play
     * @param instrument The Instrument of the Note
     * @param note The Note that should be played
     * @see Instrument
     * @see Note
     * @deprecated Replaced by {@link Notes#playNote(Player, Instrument, Note)}
     */
    @Deprecated
    public static void playNote(Player player, Instrument instrument, Note note) {
        new JLogger().deprecation();
        Notes.playNote(player, instrument, note);
    }

    /**
     * Broadcasts a Note
     * @param instrument The Instrument of the Note
     * @param note The Note
     * @see Instrument
     * @see Note
     * @deprecated Replaced by {@link Notes#broadcastNote(Instrument, Note)}
     */
    @Deprecated
    public static void broadcastNote(Instrument instrument, Note note) {
        new JLogger().deprecation();
        Notes.broadcastNote(instrument, note);
    }

    /**
     * Plays a Note for a player at a Location
     * @param player The player for whom the Note would play
     * @param location The Location where the Note should be played
     * @param instrument The Instrument of the Note
     * @param tone The Tone of the Note
     * @see Instrument
     * @see Tone
     * @deprecated Replaced by {@link Notes#playNote(Player, Location, Instrument, Tone)}
     */
    @Deprecated
    public static void playNote(Player player, Location location, Instrument instrument, Tone tone) {
        new JLogger().deprecation();
		Notes.playNote(player, location, instrument, tone);
	}

    /**
     * Broadcasts a Note at a Location
     * @param location The Location where the Note should be played
     * @param instrument The Instrument of the Note
     * @param tone The Tone of the Note
     * @see Instrument
     * @see Tone
     * @deprecated Replaced by {@link Notes#broadcastNote(Location, Instrument, Tone)}
     */
    @Deprecated
	public static void broadcastNote(Location location, Instrument instrument, Tone tone) {
        new JLogger().deprecation();
		Notes.broadcastNote(location, instrument, tone);
	}

    /**
     * Plays a Note for a player at his current Location
     * @param player The player for whom the Note would play
     * @param instrument The Instrument of the Note
     * @param tone The Tone of the Note
     * @see Instrument
     * @see Tone
     * @deprecated Replaced by {@link Notes#playNote(Player, Instrument, Tone)}
     */
    @Deprecated
    public static void playNote(Player player, Instrument instrument, Tone tone) {
        new JLogger().deprecation();
		Notes.playNote(player, instrument, tone);
	}

    /**
     * Broadcasts a Note
     * @param instrument The Instrument of the Note
     * @param tone The Tone of the Note
     * @see Instrument
     * @see Tone
     * @deprecated Replaced by {@link Notes#broadcastNote(Instrument, Tone)}
     */
    @Deprecated
	public static void broadcastNote(Instrument instrument, Tone tone) {
        new JLogger().deprecation();
		Notes.broadcastNote(instrument, tone);
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
}
