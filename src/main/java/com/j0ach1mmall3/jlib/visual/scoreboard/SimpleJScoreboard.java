package com.j0ach1mmall3.jlib.visual.scoreboard;

import org.bukkit.scoreboard.Objective;

import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/02/16
 */
public final class SimpleJScoreboard extends JScoreboard {
    /**
     * Constructs a new SimpleJScoreboard
     * @param name The name of the Scoreboard
     * @param entries The default entries
     */
    public SimpleJScoreboard(String name, Map<Integer, String> entries) {
        super(name, entries);
    }

    /**
     * Constructs a new SimpleJScoreboard
     * @param name The name of the Scoreboard
     */
    public SimpleJScoreboard(String name) {
        super(name);
    }

    @Override
    public void setName(String name) {
        this.scoreboard.getObjective("objective").setDisplayName(name);
    }

    @Override
    public void update() {
        Objective objective = this.scoreboard.getObjective("objective");
        for(Map.Entry<Integer, String> entry : this.newEntries.entrySet()) {
            int key = entry.getKey();
            String value = entry.getValue();
            this.scoreboard.resetScores(this.entries.get(key));
            String reduced = this.getReducedString(value);
            objective.getScore(reduced).setScore(15 - key);
            this.entries.put(key, reduced);
        }
        this.newEntries.clear();
    }
}
