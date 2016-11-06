package com.j0ach1mmall3.jlib.minigameapi.game.events;

import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import com.j0ach1mmall3.jlib.minigameapi.team.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 21/02/16
 */
public final class PlayerChangeTeamEvent extends TeamEvent {
    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private Team newTeam;

    /**
     * Constructs a new PlayerChangeTeamEvent, which is fired when a player changes Teams
     *
     * @param player  The player that changed Teams
     * @param game    The Game
     * @param team    The old Team
     * @param newTeam The new Team
     */
    public PlayerChangeTeamEvent(Game game, Team team, Player player, Team newTeam) {
        super(game, team);
        this.player = player;
        this.newTeam = newTeam;
    }

    /**
     * Returns the new Team
     *
     * @return The new Team
     */
    public Team getNewTeam() {
        return this.newTeam;
    }

    /**
     * Sets the new Team
     *
     * @param newTeam The new Team
     */
    public void setNewTeam(Team newTeam) {
        this.newTeam = newTeam;
    }

    /**
     * Returns the player that joined the Game
     *
     * @return The player
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
