package com.j0ach1mmall3.jlib.nbsapi.songplayer.events;

import com.j0ach1mmall3.jlib.nbsapi.songplayer.SongPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 05/03/16
 */
public abstract class SongPlayerEvent extends Event implements Cancellable {
    private final SongPlayer songPlayer;
    private boolean cancelled;

    /**
     * Constructs a new SongPlayerEvent
     * @param songPlayer The SongPlayer
     */
    public SongPlayerEvent(SongPlayer songPlayer) {
        this.songPlayer = songPlayer;
    }

    /**
     * Returns the SongPlayer
     * @return The SongPlayer
     */
    public final SongPlayer getSongPlayer() {
        return this.songPlayer;
    }

    /**
     * Returns whether the Event is cancelled
     * @return Wether the Event is cancelled
     */
    @Override
    public final boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * Sets whether the Event is cancelled
     * @param b If the Event is cancelled
     */
    @Override
    public final void setCancelled(boolean b) {
        this.cancelled = b;
    }
}
