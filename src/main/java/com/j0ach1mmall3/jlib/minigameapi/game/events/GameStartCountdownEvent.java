package com.j0ach1mmall3.jlib.minigameapi.game.events;

import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import org.bukkit.event.HandlerList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/09/15
 */
public final class GameStartCountdownEvent extends GameEvent {
    private static final HandlerList HANDLERS = new HandlerList();
    private int time;

    /**
     * Constructs a new GameStartCountdownEvent, which is fired when a Game starts with the CountDown
     * @param game The Game that started the Countdown
     * @param time The time of the Countdown
     * @see Game
     */
    public GameStartCountdownEvent(Game game, int time) {
        super(game);
        this.time = time;
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
