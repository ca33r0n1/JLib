package com.j0ach1mmall3.jlib.minigameapi.game.events;

import com.j0ach1mmall3.jlib.minigameapi.game.Game;
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
    private Result result = Result.SUCCESS;

    /**
     * Constructs a new PlayerSelectTeamEvent
     * @param game The Game
     * @param team The Team
     * @param player The Player
     */
    public PlayerSelectTeamEvent(Game game, Team team, Player player) {
        super(game, team);
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
     * Returns the Result
     * @return The Result
     */
    public Result getResult() {
        return this.result;
    }

    /**
     * Sets the Result
     * @param result The new Result
     */
    public void setResult(Result result) {
        this.result = result;
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

    public enum Result {
        SUCCESS,
        FULL,
        UNBALANCED
    }
}
