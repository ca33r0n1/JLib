package com.j0ach1mmall3.jlib.minigameapi.game.events;

import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import com.j0ach1mmall3.jlib.minigameapi.team.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 21/02/16
 */
public final class PlayerJoinGameEvent extends GameEvent {
    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private Team team;

    /**
     * Constructs a new PlayerJoinGameEvent, which is fired when a player joins a Game
     * @param player The player that joined the Game
     * @param game The Game
     * @param team The Team the player joins
     */
    public PlayerJoinGameEvent(Player player, Game game, Team team) {
        super(game);
        this.player = player;
        this.team = team;
    }

    /**
     * Returns the player that joined the Game
     * @return The player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Returns the Team the player joins
     * @return The Team
     */
    public Team getTeam() {
        return this.team;
    }

    /**
     * Sets the Team the player joins
     * @param team The Team
     */
    public void setTeam(Team team) {
        this.team = team;
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
