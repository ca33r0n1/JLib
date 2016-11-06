package com.j0ach1mmall3.jlib.minigameapi.game.events;

import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import com.j0ach1mmall3.jlib.minigameapi.spectator.SpectatorProperties;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 21/02/16
 */
public final class PlayerStartSpectatingEvent extends GameEvent {
    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final SpectatorProperties spectatorProperties;

    /**
     * Constructs a new PlayerStartSpectatingEvent, which is fired when a player starts spectating
     *
     * @param game                The Game
     * @param player              The player that joined the Game
     * @param spectatorProperties The SpectatorProperties
     */
    public PlayerStartSpectatingEvent(Game game, Player player, SpectatorProperties spectatorProperties) {
        super(game);
        this.player = player;
        this.spectatorProperties = spectatorProperties;
    }

    /**
     * Returns the player that joined the Game
     *
     * @return The player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Returns the SpectatorProperties
     *
     * @return The SpectatorProperties
     */
    public SpectatorProperties getSpectatorProperties() {
        return this.spectatorProperties;
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
