package com.j0ach1mmall3.jlib.minigameapi.game.events;

import com.j0ach1mmall3.jlib.minigameapi.classes.Class;
import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/02/16
 */
public final class PlayerSelectClassEvent extends ClassEvent {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;

    /**
     * Constructs a new PlayerSelectClassEvent
     *
     * @param game   The Game
     * @param clazz  The Class
     * @param player The Player
     */
    public PlayerSelectClassEvent(Game game, Class clazz, Player player) {
        super(game, clazz);
        this.player = player;
    }

    /**
     * Returns the Player
     *
     * @return The Player
     */
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    /**
     * Returns the HandlerList (Bukkit method)
     *
     * @return The HandlerList
     */
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
