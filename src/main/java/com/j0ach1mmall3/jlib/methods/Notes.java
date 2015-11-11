package com.j0ach1mmall3.jlib.methods;

import org.bukkit.Bukkit;
import org.bukkit.Note;
import org.bukkit.Location;
import org.bukkit.Instrument;
import org.bukkit.Note.Tone;
import org.bukkit.entity.Player;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/11/2015
 */
public final class Notes {
    /**
     * Plays a Note for a player at a Location
     * @param player The player for whom the Note would play
     * @param location The Location where the Note should be played
     * @param instrument The Instrument of the Note
     * @param note The Note that should be played
     * @see Instrument
     * @see Note
     */
    public static void playNote(Player player, Location location, Instrument instrument, Note note) {
        player.playNote(location, instrument, note);
    }

    /**
     * Broadcasts a Note at a Location
     * @param location The Location where the Note should be played
     * @param instrument The Instrument of the Note
     * @param note The Note
     * @see Instrument
     * @see Note
     */
    public static void broadcastNote(Location location, Instrument instrument, Note note){
        for(Player player : Bukkit.getOnlinePlayers()) {
            playNote(player, location, instrument, note);
        }
    }

    /**
     * Plays a Note for a player at his current Location
     * @param player The player for whom the Note would play
     * @param instrument The Instrument of the Note
     * @param note The Note that should be played
     * @see Instrument
     * @see Note
     */
    public static void playNote(Player player, Instrument instrument, Note note) {
        playNote(player, player.getEyeLocation(), instrument, note);
    }

    /**
     * Broadcasts a Note
     * @param instrument The Instrument of the Note
     * @param note The Note
     * @see Instrument
     * @see Note
     */
    public static void broadcastNote(Instrument instrument, Note note){
        for(Player player : Bukkit.getOnlinePlayers()) {
            playNote(player, instrument, note);
        }
    }

    /**
     * Plays a Note for a player at a Location
     * @param player The player for whom the Note would play
     * @param location The Location where the Note should be played
     * @param instrument The Instrument of the Note
     * @param tone The Tone of the Note
     * @see Instrument
     * @see Tone
     */
    public static void playNote(Player player, Location location, Instrument instrument, Tone tone){
		playNote(player, location, instrument, Note.natural(1, tone));
	}

    /**
     * Broadcasts a Note at a Location
     * @param location The Location where the Note should be played
     * @param instrument The Instrument of the Note
     * @param tone The Tone of the Note
     * @see Instrument
     * @see Tone
     */
	public static void broadcastNote(Location location, Instrument instrument, Tone tone){
        for(Player player : Bukkit.getOnlinePlayers()) {
            playNote(player, location, instrument, tone);
        }
	}

    /**
     * Plays a Note for a player at his current Location
     * @param player The player for whom the Note would play
     * @param instrument The Instrument of the Note
     * @param tone The Tone of the Note
     * @see Instrument
     * @see Tone
     */
    public static void playNote(Player player, Instrument instrument, Tone tone){
		playNote(player, player.getEyeLocation(), instrument, tone);
	}

    /**
     * Broadcasts a Note
     * @param instrument The Instrument of the Note
     * @param tone The Tone of the Note
     * @see Instrument
     * @see Tone
     */
	public static void broadcastNote(Instrument instrument, Tone tone){
        for(Player player : Bukkit.getOnlinePlayers()) {
            playNote(player, instrument, tone);
        }
	}
}
