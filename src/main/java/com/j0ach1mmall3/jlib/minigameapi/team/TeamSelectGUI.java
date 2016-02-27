package com.j0ach1mmall3.jlib.minigameapi.team;

import com.j0ach1mmall3.jlib.inventory.GUI;

import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/02/16
 */
public final class TeamSelectGUI {
    private final GUI gui;
    private final Map<Integer, Team> teams;

    /**
     * Constructs a new TeamSelectGUI
     * @param gui The GUI
     * @param teams The inventory position Team mapping
     */
    public TeamSelectGUI(GUI gui, Map<Integer, Team> teams) {
        this.gui = gui;
        this.teams = teams;
    }

    /**
     * Returns the GUI
     * @return The GUI
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * Returns a Team from a clicked slot
     * @param slot The clicked slot
     * @return The Team
     */
    public Team getTeam(int slot) {
        return this.teams.get(slot);
    }
}
