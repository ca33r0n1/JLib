package com.j0ach1mmall3.jlib.minigameapi.team.events;

import com.j0ach1mmall3.jlib.minigameapi.team.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by j0ach1mmall3 on 19:36 4/09/2015 using IntelliJ IDEA.
 */
public class PlayerJoinTeamEvent extends Event implements Cancellable{
    private static final HandlerList HANDLERS = new HandlerList();
    private Player player;
    private Team team;
    private boolean isCancelled;
    public PlayerJoinTeamEvent(Player player, Team team) {
        this.player = player;
        this.team = team;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Team getTeam() {
        return this.team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public boolean isCancelled() {
        return this.isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public HandlerList getHandlerList() {
        return HANDLERS;
    }
}
