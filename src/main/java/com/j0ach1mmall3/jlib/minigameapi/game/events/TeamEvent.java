package com.j0ach1mmall3.jlib.minigameapi.game.events;

import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import com.j0ach1mmall3.jlib.minigameapi.team.Team;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/02/16
 */
public abstract class TeamEvent extends GameEvent {
    private final Team team;

    /**
     * Constructs a new TeamEvent
     *
     * @param game The Game
     * @param team The Team
     */
    protected TeamEvent(Game game, Team team) {
        super(game);
        this.team = team;
    }

    /**
     * Returns the Team
     *
     * @return The Team
     */
    public final Team getTeam() {
        return this.team;
    }
}
