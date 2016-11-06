package com.j0ach1mmall3.jlib.nbsapi;

import com.j0ach1mmall3.jlib.player.JLibPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 6/03/2016
 */
public final class Tick {
    private final List<Note> notes = new ArrayList<>();

    /**
     * Constructs a new Tick, shouldn't be used externally
     */
    Tick() {
    }

    /**
     * Adds a Note to this Tick, shouldn't be used externally
     *
     * @param note The Note
     */
    void addNote(Note note) {
        this.notes.add(note);
    }

    /**
     * Plays the Notes of this Tick for a player, at a Location
     *
     * @param player   The player
     * @param location The Location
     */
    public void play(Player player, Location location) {
        for (Note note : this.notes) {
            new JLibPlayer(player).playNote(location, note.getInstrument(), note.getNote());
        }
    }
}
