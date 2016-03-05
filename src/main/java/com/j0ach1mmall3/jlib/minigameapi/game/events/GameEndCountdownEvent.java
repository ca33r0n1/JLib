package com.j0ach1mmall3.jlib.minigameapi.game.events;

import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import org.bukkit.event.HandlerList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/09/15
 */
public final class GameEndCountdownEvent extends GameEvent {
    private static final HandlerList HANDLERS = new HandlerList();

    private final Reason reason;

    /**
     * Constructs a new GameEndCountdownEvent, which is fired when a Game ends the Countdown
     * @param game The Game that ended the Countdown
     * @param reason The Reason the Countdown ended
     */
    public GameEndCountdownEvent(Game game, Reason reason) {
        super(game);
        this.reason = reason;
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

    public enum Reason {
        TIME,
        PLAYER_LEAVE,
        MANUAL
    }
}
