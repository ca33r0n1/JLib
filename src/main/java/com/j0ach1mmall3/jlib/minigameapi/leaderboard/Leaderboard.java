package com.j0ach1mmall3.jlib.minigameapi.leaderboard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 11/03/2016
 */
public final class Leaderboard {
    private final List<LeaderboardObjective> leaderboardObjectives;

    /**
     * Constructs a new Leaderboard
     * @param leaderboardObjectives The objectives
     */
    public Leaderboard(List<LeaderboardObjective> leaderboardObjectives) {
        this.leaderboardObjectives = leaderboardObjectives;
    }

    /**
     * Constructs a new Leaderboard
     */
    public Leaderboard() {
        this(new ArrayList<LeaderboardObjective>());
    }

    /**
     * Returns a LeaderboardObjective by identifier
     * @param identifier The identifier
     * @return The LeaderboardObjective
     */
    public LeaderboardObjective getByIdentifier(String identifier) {
        for(LeaderboardObjective leaderboardObjective : this.leaderboardObjectives) {
            if(leaderboardObjective.getIdentifier().equals(identifier)) return leaderboardObjective;
        }
        return null;
    }
}
