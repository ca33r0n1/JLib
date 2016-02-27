package com.j0ach1mmall3.jlib.minigameapi.team.events;

import com.j0ach1mmall3.jlib.minigameapi.team.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/02/16
 */
public final class PlayerSelectTeamEvent extends TeamEvent {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private boolean success = true;

    /**
     * Constructs a new PlayerSelectTeamEvent
     * @param team The Team the Player selected
     * @param player The Player
     */
    public PlayerSelectTeamEvent(Team team, Player player) {
        super(team);
        this.player = player;
    }

    /**
     * Returns the Player
     * @return The Player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Returns whether the Event is successful
     * @return Whether the Event is successful
     */
    public boolean isSuccess() {
        return this.success;
    }

    /**
     * Sets whether the Event is successful
     * @param success Whether the Event is successful
     */
    public void setSuccess(boolean success) {
        this.success = success;
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
