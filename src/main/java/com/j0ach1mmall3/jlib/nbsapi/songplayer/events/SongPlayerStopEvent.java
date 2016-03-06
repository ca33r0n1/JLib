package com.j0ach1mmall3.jlib.nbsapi.songplayer.events;

import com.j0ach1mmall3.jlib.nbsapi.songplayer.SongPlayer;
import org.bukkit.event.HandlerList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/03/2016
 */
public final class SongPlayerStopEvent extends SongPlayerEvent {
    private static final HandlerList HANDLERS = new HandlerList();

    /**
     * Constructs a new SongPlayerStopEvent
     * @param songPlayer The SongPlayer
     */
    public SongPlayerStopEvent(SongPlayer songPlayer) {
        super(songPlayer);
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    /**
     * Returns the HandlerList (Bukkit method)
     * @return The HandlerList
     */
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
