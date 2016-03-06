package com.j0ach1mmall3.jlib.minigameapi.game.events;

import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import org.bukkit.event.HandlerList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/09/15
 */
public final class GameStartEvent extends GameEvent {
    private static final HandlerList HANDLERS = new HandlerList();

    /**
     * Constructs a new GameStartEvent, which is fired when a Game starts
     * @param game The Game that ended
     */
    public GameStartEvent(Game game) {
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
