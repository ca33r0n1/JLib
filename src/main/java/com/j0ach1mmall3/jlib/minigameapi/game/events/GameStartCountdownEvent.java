package com.j0ach1mmall3.jlib.minigameapi.game.events;

import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/09/2015
 */
public class GameStartCountdownEvent extends Event implements Cancellable{
    private static final HandlerList HANDLERS = new HandlerList();
    private final Game game;
    private int time;
    private boolean isCancelled;

    /**
     * Constructs a new GameStartCountdownEvent, which is fired when a Game starts with the CountDown
     * @param game The Game that started the Countdown
     * @param time The time of the Countdown
     * @see Game
     */
    public GameStartCountdownEvent(Game game, int time) {
        this.game = game;
        this.time = time;
    }

    /**
     * Returns the Game that started with the CountDown
     * @return The Game
     * @see Game
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * Returns the time of the Countdown
     * @return The time
     */
    public int getTime() {
        return this.time;
    }

    /**
     * Sets the time of the Countdown
     * @param time The new time
     */
    public void setTime(int time) {
        this.time = time;
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
