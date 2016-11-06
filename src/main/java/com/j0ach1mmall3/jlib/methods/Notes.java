package com.j0ach1mmall3.jlib.methods;

import com.j0ach1mmall3.jlib.player.JLibPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Note;
import org.bukkit.Note.Tone;
import org.bukkit.entity.Player;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @see JLibPlayer
 * @since 4/11/15
 */
public final class Notes {

    /**
     * Let nobody instantiate this class
     */
    private Notes() {
    }

    /**
     * Plays a Note for a player at a Location
     *
     * @param player     The player for whom the Note would play
     * @param location   The Location where the Note should be played
     * @param instrument The Instrument of the Note
     * @param note       The Note that should be played
     */
    @Deprecated
    public static void playNote(Player player, Location location, Instrument instrument, Note note) {
        new JLibPlayer(player).playNote(location, instrument, note);
    }

    /**
     * Broadcasts a Note at a Location
     *
     * @param location   The Location where the Note should be played
     * @param instrument The Instrument of the Note
     * @param note       The Note
     */
    public static void broadcastNote(Location location, Instrument instrument, Note note) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            new JLibPlayer(player).playNote(location, instrument, note);
        }
    }

    /**
     * Plays a Note for a player at his current Location
     *
     * @param player     The player for whom the Note would play
     * @param instrument The Instrument of the Note
     * @param note       The Note that should be played
     */
    @Deprecated
    public static void playNote(Player player, Instrument instrument, Note note) {
        new JLibPlayer(player).playNote(instrument, note);
    }

    /**
     * Broadcasts a Note
     *
     * @param instrument The Instrument of the Note
     * @param note       The Note
     */
    public static void broadcastNote(Instrument instrument, Note note) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            new JLibPlayer(player).playNote(instrument, note);
        }
    }

    /**
     * Plays a Note for a player at a Location
     *
     * @param player     The player for whom the Note would play
     * @param location   The Location where the Note should be played
     * @param instrument The Instrument of the Note
     * @param tone       The Tone of the Note
     */
    @Deprecated
    public static void playNote(Player player, Location location, Instrument instrument, Tone tone) {
        new JLibPlayer(player).playNote(location, instrument, tone);
    }

    /**
     * Broadcasts a Note at a Location
     *
     * @param location   The Location where the Note should be played
     * @param instrument The Instrument of the Note
     * @param tone       The Tone of the Note
     */
    public static void broadcastNote(Location location, Instrument instrument, Tone tone) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            new JLibPlayer(player).playNote(location, instrument, tone);
        }
    }

    /**
     * Plays a Note for a player at his current Location
     *
     * @param player     The player for whom the Note would play
     * @param instrument The Instrument of the Note
     * @param tone       The Tone of the Note
     */
    @Deprecated
    public static void playNote(Player player, Instrument instrument, Tone tone) {
        new JLibPlayer(player).playNote(instrument, tone);
    }

    /**
     * Broadcasts a Note
     *
     * @param instrument The Instrument of the Note
     * @param tone       The Tone of the Note
     */
    public static void broadcastNote(Instrument instrument, Tone tone) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            new JLibPlayer(player).playNote(instrument, tone);
        }
    }
}
