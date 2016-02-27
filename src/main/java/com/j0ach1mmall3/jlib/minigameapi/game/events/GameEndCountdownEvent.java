package com.j0ach1mmall3.jlib.minigameapi.game.events;

import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import org.bukkit.event.HandlerList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/09/15
 */
public final class GameEndCountdownEvent extends GameEvent {
    private static final HandlerList HANDLERS = new HandlerList();

    /**
     * Constructs a new GameEndCountdownEvent, which is fired when a Game ends the CountDown
     * @param game The Game that ended the Countdown
     * @see Game
     */
    public GameEndCountdownEvent(Game game) {
        super(game);
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
