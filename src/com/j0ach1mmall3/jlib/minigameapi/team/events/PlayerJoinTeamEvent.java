package com.j0ach1mmall3.jlib.minigameapi.team.events;

import com.j0ach1mmall3.jlib.minigameapi.team.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/09/2015
 */
public class PlayerJoinTeamEvent extends Event implements Cancellable{
    private static final HandlerList HANDLERS = new HandlerList();
    private Player player;
    private Team team;
    private boolean isCancelled;

    /**
     * Constructs a new PlayerJoinTeamEvent, which is fired when a Player joins a Team
     * @param player The Player
     * @param team The Team
     * @see Team
     */
    public PlayerJoinTeamEvent(Player player, Team team) {
        this.player = player;
        this.team = team;
    }

    /**
     * Returns the Player that joined the Team
     * @return The Player that joined the Team
     * @see Team
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Returns the Team the player joined
     * @return The Team the player joined
     * @see Team
     */
    public Team getTeam() {
        return this.team;
    }

    /**
     * Returns if the Event is cancelled
     * @return If the Event is cancelled
     */
    public boolean isCancelled() {
        return this.isCancelled;
    }

    /**
     * Sets if the Event is cancelled
     * @param cancelled If the Event is cancelled
     */
    public void setCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
    }

    /**
     * Returns the Handlers List, Bukkit Method
     * @return The Handlers List
     */
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    /**
     * Returns the Handlers List, Bukkit Method
     * @return The Handlers List
     */
    public HandlerList getHandlerList() {
        return HANDLERS;
    }
}
