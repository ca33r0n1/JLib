package com.j0ach1mmall3.jlib.visual.scoreboard;


import com.j0ach1mmall3.jlib.methods.Random;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

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
    protected final Map<Integer, String> newEntries = new HashMap<>();
    protected final Map<Integer, String> entries = new HashMap<>();
    protected final List<Player> players = new ArrayList<>();

    /**
     * Constructs a new JScoreboard
     * @param name The DisplayName
     * @param entries The default entries
     */
    protected JScoreboard(String name, Map<Integer, String> entries) {
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = this.scoreboard.registerNewObjective("objective", "dummy");
        objective.setDisplayName(name);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        for(int i = 0; i  < 15; i++) {
            this.entries.put(i, entries.containsKey(i) ? entries.get(i) : String.valueOf(i));
            this.newEntries.put(i, entries.containsKey(i) ? entries.get(i) : String.valueOf(i));
        }
    }

    /**
     * Constructs a new JScoreboard
     * @param name The DisplayName
     */
    protected JScoreboard(String name) {
        this(name, new HashMap<Integer, String>());
    }

    /**
     * Returns the DisplayName
     * @return The DisplayName
     */
    public String getName() {
        return this.scoreboard.getObjective("objective").getDisplayName();
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
        if(pos > 14 || pos < 0) throw new IllegalArgumentException("invalid position!");
        this.newEntries.put(pos, entry);
    }

    /**
     * Adds a player to the Scoreboard
     * @param team The Team
     * @param player The player
     */
    public void addPlayer(String team, Player player) {
        this.players.add(player);
        player.setScoreboard(this.scoreboard);
        this.scoreboard.getTeam(team).addEntry(player.getName());
    }

    /**
     * Adds a player to the Scoreboard
     * @param player The player
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
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
        for(Team team : this.scoreboard.getTeams()) {
            team.removeEntry(player.getName());
        }
    }

    /**
     * Sets the Team of a player
     * @param player The player
     * @param identifier The identifier
     */
    public void setTeam(Player player, String identifier) {
        for(Team t : this.scoreboard.getTeams()) {
            t.removeEntry(player.getName());
        }
        this.scoreboard.getTeam(identifier).addEntry(player.getName());
    }

    /**
     * Adds a Team to the Scoreboard
     * @param identifier The identifier
     * @return The Team instance
     */
    public Team addTeam(String identifier) {
        return this.scoreboard.registerNewTeam(identifier);
    }

    /**
     * Adds a MinigameAPI Team to the Scoreboard
     * @param team The Team
     */
    @SuppressWarnings("deprecation")
    public void addTeam(com.j0ach1mmall3.jlib.minigameapi.team.Team team) {
        Team newTeam = this.scoreboard.registerNewTeam(team.getIdentifier());
        newTeam.setDisplayName(team.getName());
        newTeam.setPrefix(team.getPrefix());
        newTeam.setSuffix(team.getSuffix());
        newTeam.setAllowFriendlyFire(team.isFriendlyFire());
        newTeam.setCanSeeFriendlyInvisibles(team.isSeeFriendlyInvisibles());
        newTeam.setNameTagVisibility(team.getNameTagVisibility());
    }

    /**
     * Removes a Team from the Scoreboard
     * @param identifier The Team identifier
     */
    public void removeTeam(String identifier) {
        this.scoreboard.getTeams().remove(this.scoreboard.getTeam(identifier));
    }

    /**
     * Adds an Objective to the scoreboard
     * @param identifier The Objective identifier
     * @return The Objective
     */
    public Objective addObjective(String identifier) {
        return this.scoreboard.registerNewObjective(identifier, "dummy");
    }

    /**
     * Removes an Objective from the Scoreboard
     * @param identifier The Objective identifier
     */
    public void removeObjective(String identifier) {
        this.scoreboard.getObjectives().remove(this.scoreboard.getObjective(identifier));
    }

    /**
     * Reduces a String to max 16 chars
     * @param s The String
     * @return The reduced String
     */
    protected String getReducedString(String s) {
        String prefix = "";
        String suffix = "";
        String reduced = "";
        if(s.length() <= 16) return s;
        if(s.length() > 16 && s.length() <= 32) {
            prefix = s.substring(0, 16);
            reduced = s.substring(16);
        }
        if(s.length() > 32 && s.length() <= 48) {
            prefix = s.substring(0, 16);
            reduced = s.substring(16, 32);
            suffix = s.substring(32);
        }
        if(s.length() > 48) {
            prefix = s.substring(0, 16);
            reduced = s.substring(16, 32);
            suffix = s.substring(32, 48);
        }
        Team team = this.addTeam(Random.getString(16, true, true));
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.addEntry(reduced);
        return reduced;
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
