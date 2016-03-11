package com.j0ach1mmall3.jlib.minigameapi.leaderboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 11/03/2016
 */
public final class LeaderboardObjective {
    private final String identifier;
    private final String name;
    private final List<LeaderboardEntry> entries;

    public LeaderboardObjective(String identifier, String name, List<LeaderboardEntry> entries) {
        this.identifier = identifier;
        this.name = name;
        this.entries = entries;
    }

    public LeaderboardObjective(String identifier, String name) {
        this(identifier, name, new ArrayList<LeaderboardEntry>());
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getName() {
        return this.name;
    }

    public List<LeaderboardEntry> getEntries() {
        return this.entries;
    }

    public List<LeaderboardEntry> getEntriesSorted() {
        List<LeaderboardEntry> sorted = new ArrayList<>(this.entries);
        Collections.sort(sorted);
        return sorted;
    }
}
