package com.j0ach1mmall3.jlib.minigameapi.game.events;

import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/02/16
 */
public abstract class GameEvent extends Event implements Cancellable {
    private final Game game;
    private boolean cancelled;

    /**
     * Constructs a new GameEvent
     * @param game The Game
     */
    public GameEvent(Game game) {
        this.game = game;
    }

    /**
     * Returns the Game
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
