package com.j0ach1mmall3.jlib.minigameapi.game.events;

import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/09/2015
 */
public class GameEndEvent extends Event implements Cancellable{
    private static final HandlerList HANDLERS = new HandlerList();
    private final Game game;
    private boolean isCancelled;

    /**
     * Constructs a new GameEndEvent, which is fired when a Game ends
     * @param game The Game that ended
     * @see Game
     */
    public GameEndEvent(Game game) {
        this.game = game;
    }

    /**
     * Returns the Game that ended
     * @return The Game
     * @see Game
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * Returns whether the Event is cancelled
     * @return Wether the Event is cancelled
     */
    public boolean isCancelled() {
        return this.isCancelled;
    }

    /**
     * Sets whether the Event is cancelled
     * @param cancelled If the Event is cancelled
     */
    public void setCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
    }

    /**
     * Returns the Handlers List, Bukkit Method
     * @return The Handlers List
     */
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    /**
     * Returns the Handlers List, Bukkit Method
     * @return The Handlers List
     */
    public HandlerList getHandlerList() {
        return HANDLERS;
    }
}
