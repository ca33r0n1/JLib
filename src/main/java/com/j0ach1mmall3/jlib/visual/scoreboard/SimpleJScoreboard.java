package com.j0ach1mmall3.jlib.visual.scoreboard;

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
        this.objective.setDisplayName(name);
    }

    @Override
    public void update() {
        for(Map.Entry<Integer, String> entry : this.newEntries.entrySet()) {
            this.scoreboard.resetScores(this.entries.get(entry.getKey()));
            this.objective.getScore(entry.getValue()).setScore(15 - entry.getKey());
            this.entries.put(entry.getKey(), entry.getValue());
        }
        this.newEntries.clear();
    }
}
