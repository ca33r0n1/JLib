package com.j0ach1mmall3.jlib.minigameapi.game.events;

import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/09/2015
 */
public class GameStartEvent extends Event implements Cancellable{
    private static final HandlerList HANDLERS = new HandlerList();
    private final Game game;
    private boolean isCancelled;

    /**
     * Constructs a new GameStartEvent, which is fired when a Game starts
     * @param game The Game that ended
     * @see Game
     */
    public GameStartEvent(Game game) {
        this.game = game;
    }

    /**
     * Returns the Game that started
     * @return The Game
     * @see Game
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * Returns if the Event is cancelled
     * @return If the Event is cancelled
     */
    public boolean isCancelled() {
        return this.isCancelled;
    }

    /**
     * Sets if the Event is cancelled
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
