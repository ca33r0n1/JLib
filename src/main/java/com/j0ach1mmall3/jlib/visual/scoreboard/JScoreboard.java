package com.j0ach1mmall3.jlib.visual.scoreboard;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 3/02/16
 */
public abstract class JScoreboard {
    protected final Scoreboard scoreboard;
    protected final Objective objective;
    protected final Map<Integer, String> newEntries = new HashMap<>();
    protected final Map<Integer, String> entries = new HashMap<>();
    protected final List<Player> players = new ArrayList<>();

    /**
     * Constructs a new JScoreboard
     * @param name The DisplayName
     * @param entries The default entries
     */
    public JScoreboard(String name, Map<Integer, String> entries) {
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.objective = this.scoreboard.registerNewObjective("objective", "dummy");
        this.objective.setDisplayName(name);
        this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        for(int i=0;i<15;i++) {
            if(i > 15 || i < 0) throw new IllegalArgumentException("pos needs to be > 15 and < 0!");
            this.entries.put(i, entries.containsKey(i) ? entries.get(i) : String.valueOf(i));
        }
    }

    /**
     * Constructs a new JScoreboard
     * @param name The DisplayName
     */
    public JScoreboard(String name) {
        this(name, new HashMap<Integer, String>());
    }

    /**
     * Returns the DisplayName
     * @return The DisplayName
     */
    public String getName() {
        return this.objective.getDisplayName();
    }

    /**
     * Sets the DisplayName and updates immediately
     * @param name The new DisplayName
     */
    public void setNameAndUpdate(String name) {
        this.setName(name);
        this.update();
    }

    /**
     * Sets an Entry and updates immediately
     * @param pos The position of the Entry (0-15)
     * @param entry The Entry
     */
    public void setEntryAndUpdate(int pos, String entry) {
        this.setEntry(pos, entry);
        this.update();
    }

    /**
     * Sets an Entry
     * @param pos The position of the Entry (0-15)
     * @param entry The Entry
     */
    public void setEntry(int pos, String entry) {
        if(pos > 15 || pos < 0) throw new IllegalArgumentException("pos needs to be > 15 and < 0!");
        this.newEntries.put(pos, entry);
    }

    /**
     * Adds a Player to the Scoreboard
     * @param player The Player
     */
    public void addPlayer(Player player) {
        this.players.add(player);
        player.setScoreboard(this.scoreboard);
    }

    /**
     * Removes a Player from the Scoreboard
     * @param player The Player
     */
    public void removePlayer(Player player) {
        this.players.remove(player);
        player.setScoreboard(null);
    }

    /**
     * Updates the Scoreboard
     */
    public abstract void update();

    /**
     * Sets the DisplayName
     * @param name The new DisplayName
     */
    public abstract void setName(String name);
}
