package com.j0ach1mmall3.jlib.nbsapi;

import org.bukkit.Instrument;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/03/2016
 */
public final class Note {
    private final Instrument instrument;
    private final org.bukkit.Note note;

    /**
     * Constructs a new Note, shouldn't be used externally
     * @param instrument The Instrument
     * @param note The Note to play
     */
    Note(byte instrument, int note) {
        this.instrument = this.getBukkitInstrument(instrument);
        this.note = new org.bukkit.Note(note);
    }

    /**
     * Returns the Instrument
     * @return The Instrument
     */
    public Instrument getInstrument() {
        return this.instrument;
    }

    /**
     * Returns the Note
     * @return The Note
     */
    public org.bukkit.Note getNote() {
        return this.note;
    }

    /**
     * Calculates the Bukkit Instrument from byte notation
     * @param instrument The byte notation
     * @return The Bukkit Instrument
     */
    private Instrument getBukkitInstrument(byte instrument) {
        switch (instrument) {
            case 0:
                return Instrument.PIANO;
            case 1:
                return Instrument.BASS_GUITAR;
            case 2:
                return Instrument.BASS_DRUM;
            case 3:
                return Instrument.SNARE_DRUM;
            case 4:
                return Instrument.STICKS;
            default:
                return Instrument.PIANO;
        }
    }
}
