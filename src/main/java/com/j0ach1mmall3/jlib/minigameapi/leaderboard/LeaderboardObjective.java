package com.j0ach1mmall3.jlib.minigameapi.leaderboard;

import com.j0ach1mmall3.jlib.integration.Placeholders;
import com.j0ach1mmall3.jlib.methods.PaginatedList;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 11/03/2016
 */
public final class LeaderboardObjective {
    private final String identifier;
    private final String name;
    private final int perPage;
    private final List<LeaderboardEntry> entries;

    /**
     * Constructs a new LeaderboardObjective
     *
     * @param identifier The identifier of the Objective
     * @param name       The Display Name of the Objective
     * @param perPage    How many entries per page should be shown
     * @param entries    The entries
     */
    public LeaderboardObjective(String identifier, String name, int perPage, List<LeaderboardEntry> entries) {
        this.identifier = identifier;
        this.name = name;
        this.perPage = perPage;
        this.entries = entries;
    }

    /**
     * Constructs a new LeaderboardObjective
     *
     * @param identifier The identifier of the Objective
     * @param name       The Display Name of the Objective
     * @param perPage    How many entries per page should be shown
     */
    public LeaderboardObjective(String identifier, String name, int perPage) {
        this(identifier, name, perPage, new ArrayList<>());
    }

    /**
     * Returns the identifier of the Objective
     *
     * @return The identifier
     */
    public String getIdentifier() {
        return this.identifier;
    }

    /**
     * Returns the Display Name of the Objective
     *
     * @return The Display Name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns how many entries per page should be shown
     *
     * @return how many entries per page should be shown
     */
    public int getPerPage() {
        return this.perPage;
    }

    /**
     * Returns the entries
     *
     * @return The entries
     */
    public List<LeaderboardEntry> getEntries() {
        return this.entries;
    }

    /**
     * Adds a LeaderboardEntry
     *
     * @param entry The LeaderboardEntry
     */
    public void addEntry(LeaderboardEntry entry) {
        this.entries.add(entry);
    }

    /**
     * Returns the entries sorted
     *
     * @return The entries
     */
    public List<LeaderboardEntry> getEntriesSorted() {
        List<LeaderboardEntry> sorted = new ArrayList<>(this.entries);
        Collections.sort(sorted, (l1, l2) -> l1.getScore() > l2.getScore() ? 1 : l1.getScore() < l2.getScore() ? -1 : 0);
        return sorted;
    }

    /**
     * Returns the entries sorted
     *
     * @return The entries
     */
    public List<String> getStringEntriesSorted() {
        List<String> sorted = this.getEntriesSorted().stream().map(LeaderboardEntry::getString).collect(Collectors.toList());
        return sorted;
    }

    /**
     * Shows the entries to a Player
     *
     * @param player The Player
     * @param page   The page to show
     */
    public void show(Player player, int page) {
        PaginatedList<String> paginatedList = new PaginatedList(this.getStringEntriesSorted(), this.perPage);
        for (String s : paginatedList.getPage(page)) {
            player.sendMessage(Placeholders.parse(s, player));
        }
    }
}
