package com.j0ach1mmall3.jlib.minigameapi.game.events;

import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 21/02/16
 */
public final class PlayerLeaveGameEvent extends GameEvent {
    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final Reason reason;

    /**
     * Constructs a new PlayerLeaveGameEvent, which is fired when a player leaves a Game
     *
     * @param player The player that left the Game
     * @param game   The Game
     * @param reason The Reason why the player left the Game
     */
    public PlayerLeaveGameEvent(Player player, Game game, Reason reason) {
        super(game);
        this.player = player;
        this.reason = reason;
    }

    /**
     * Returns the player that left the Game
     *
     * @return The player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Returns the Reason why the player left the Game
     *
     * @return The Reason
     */
    public Reason getReason() {
        return this.reason;
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

    public enum Reason {
        QUIT,
        KICK,
        MANUAL
    }
}
