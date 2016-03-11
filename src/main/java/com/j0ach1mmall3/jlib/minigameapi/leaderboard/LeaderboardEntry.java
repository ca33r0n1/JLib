package com.j0ach1mmall3.jlib.minigameapi.leaderboard;

import org.bukkit.ChatColor;

import java.util.UUID;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 11/03/2016
 */
public final class LeaderboardEntry implements Comparable<LeaderboardEntry> {
    private final String format;
    private final UUID uuid;
    private final int score;

    public LeaderboardEntry(String format, UUID uuid, int score) {
        this.format = format;
        this.uuid = uuid;
        this.score = score;
    }

    public String getString() {
        return ChatColor.translateAlternateColorCodes('&', this.format).replace("%uuid%", this.uuid.toString()).replace("%score%", String.valueOf(this.score));
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public int getScore() {
        return this.score;
    }

    @Override
    public int compareTo(LeaderboardEntry o) {
        return this.score > o.score ? 1 : this.score < o.score ? -1 : 0;
    }
}
