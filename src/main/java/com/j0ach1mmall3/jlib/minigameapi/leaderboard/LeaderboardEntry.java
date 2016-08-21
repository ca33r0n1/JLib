package com.j0ach1mmall3.jlib.minigameapi.leaderboard;

import org.bukkit.ChatColor;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 11/03/2016
 */
public final class LeaderboardEntry implements Comparable<LeaderboardEntry>, Serializable {
    private final String format;
    private final UUID uuid;
    private final int score;

    /**
     * Constructs a new LeaderboardEntry
     * @param format The String format, %uuid% for uuid, %score% for score
     * @param uuid The uuid
     * @param score The score
     */
    public LeaderboardEntry(String format, UUID uuid, int score) {
        this.format = format;
        this.uuid = uuid;
        this.score = score;
    }

    /**
     * Returns the String format
     * @return The String format
     */
    public String getString() {
        return ChatColor.translateAlternateColorCodes('&', this.format).replace("%uuid%", this.uuid.toString()).replace("%score%", String.valueOf(this.score));
    }

    /**
     * Returns the uuid
     * @return The uuid
     */
    public UUID getUuid() {
        return this.uuid;
    }

    /**
     * Returns the score
     * @return The score
     */
    public int getScore() {
        return this.score;
    }

    @Override
    public int compareTo(LeaderboardEntry o) {
        return this.score > o.score ? 1 : this.score < o.score ? -1 : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        LeaderboardEntry that = (LeaderboardEntry) o;

        return this.score == that.score && (this.format == null ? that.format == null : this.format.equals(that.format) && (this.uuid == null ? that.uuid == null : this.uuid.equals(that.uuid)));

    }

    @Override
    public int hashCode() {
        int result = this.format == null ? 0 : this.format.hashCode();
        result = 31 * result + (this.uuid == null ? 0 : this.uuid.hashCode());
        result = 31 * result + this.score;
        return result;
    }
}
