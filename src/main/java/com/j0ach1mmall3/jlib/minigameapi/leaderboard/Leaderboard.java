package com.j0ach1mmall3.jlib.minigameapi.leaderboard;

import java.util.HashSet;
import java.util.Set;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 11/03/2016
 */
public final class Leaderboard {
    private final Set<LeaderboardObjective> leaderboardObjectives;

    /**
     * Constructs a new Leaderboard
     *
     * @param leaderboardObjectives The objectives
     */
    public Leaderboard(Set<LeaderboardObjective> leaderboardObjectives) {
        this.leaderboardObjectives = leaderboardObjectives;
    }

    /**
     * Constructs a new Leaderboard
     */
    public Leaderboard() {
        this(new HashSet<>());
    }

    /**
     * Returns a LeaderboardObjective by identifier
     *
     * @param identifier The identifier
     * @return The LeaderboardObjective
     */
    public LeaderboardObjective getByIdentifier(String identifier) {
        for (LeaderboardObjective leaderboardObjective : this.leaderboardObjectives) {
            if (leaderboardObjective.getIdentifier().equals(identifier)) return leaderboardObjective;
        }
        return null;
    }

    /**
     * Returns the LeaderboardObjectives
     *
     * @return The LeaderboardObjectives
     */
    public Set<LeaderboardObjective> getLeaderboardObjectives() {
        return this.leaderboardObjectives;
    }

    /**
     * Adds a LeaderboardObjective
     *
     * @param objective The LeaderboardObjective
     */
    public void addObjective(LeaderboardObjective objective) {
        this.leaderboardObjectives.add(objective);
    }
}
