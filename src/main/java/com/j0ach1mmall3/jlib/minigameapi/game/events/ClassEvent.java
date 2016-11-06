package com.j0ach1mmall3.jlib.minigameapi.game.events;

import com.j0ach1mmall3.jlib.minigameapi.classes.Class;
import com.j0ach1mmall3.jlib.minigameapi.game.Game;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/02/16
 */
public abstract class ClassEvent extends GameEvent {
    private final Class clazz;

    /**
     * Constructs a new TeamEvent
     *
     * @param game  The Game
     * @param clazz The Class
     */
    protected ClassEvent(Game game, Class clazz) {
        super(game);
        this.clazz = clazz;
    }

    /**
     * Returns the Class
     *
     * @return The Class
     */
    public final Class getClazz() {
        return this.clazz;
    }
}
