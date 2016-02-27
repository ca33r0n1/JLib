package com.j0ach1mmall3.jlib.minigameapi.team.events;

import com.j0ach1mmall3.jlib.minigameapi.team.Team;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/02/16
 */
public abstract class TeamEvent extends Event implements Cancellable {
    private Team team;
    private boolean cancelled;

    /**
     * Constructs a new TeamEvent
     * @param team The Team
     */
    public TeamEvent(Team team) {
        this.team = team;
    }

    /**
     * Returns the Team
     * @return The Team
     */
    public Team getTeam() {
        return this.team;
    }

    /**
     * Sets the Team
     * @param team The Team
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * Returns whether the Event is cancelled
     * @return Wether the Event is cancelled
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * Sets whether the Event is cancelled
     * @param b If the Event is cancelled
     */
    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    @Override
    public abstract HandlerList getHandlers();
}
