package com.j0ach1mmall3.jlib.nbsapi;

import com.j0ach1mmall3.jlib.methods.Notes;
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

    Tick() {
        // NOP
    }

    void addNote(Note note) {
        this.notes.add(note);
    }

    public void play(Player player, Location location) {
        for(Note note : this.notes) {
            Notes.playNote(player, location, note.getInstrument(), note.getNote());
        }
    }
}
