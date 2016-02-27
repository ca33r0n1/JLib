package com.j0ach1mmall3.jlib.minigameapi.team;

import com.j0ach1mmall3.jlib.inventory.GuiItem;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 24/02/2016
 */
public final class TeamProperties {
    private final TeamSelectGUI teamSelectGUI;
    private final GuiItem teamSelectItem;
    private final boolean giveSelectItem;
    private final boolean dropSelectItem;
    private final boolean moveSelectItem;
    private final boolean balanceTeams;

    /**
     * Constructs a new TeamProperties instance
     * @param teamSelectGUI The TeamSelectGUI
     * @param teamSelectItem The TeamSelectItem
     * @param giveSelectItem Whether we should give the SelectItem on join
     * @param dropSelectItem Whether dropping the SelectItem should be allowed
     * @param moveSelectItem Whether moving the SelectItem should be allowed
     * @param balanceTeams Whether we should balance Teams
     */
    public TeamProperties(TeamSelectGUI teamSelectGUI, GuiItem teamSelectItem, boolean giveSelectItem, boolean dropSelectItem, boolean moveSelectItem, boolean balanceTeams) {
        this.teamSelectGUI = teamSelectGUI;
        this.teamSelectItem = teamSelectItem;
        this.giveSelectItem = giveSelectItem;
        this.dropSelectItem = dropSelectItem;
        this.moveSelectItem = moveSelectItem;
        this.balanceTeams = balanceTeams;
    }

    /**
     * Returns the TeamSelectGUI
     * @return The TeamSelectGUI
     */
    public TeamSelectGUI getTeamSelectGUI() {
        return this.teamSelectGUI;
    }

    /**
     * Returns the TeamSelectItem
     * @return The TeamSelectItem
     */
    public GuiItem getTeamSelectItem() {
        return this.teamSelectItem;
    }

    /**
     * Returns whether we should give the SelectItem on join
     * @return Whether we should give the SelectItem on join
     */
    public boolean isGiveSelectItem() {
        return this.giveSelectItem;
    }

    /**
     * Returns whether dropping the SelectItem should be allowed
     * @return Whether dropping the SelectItem should be allowed
     */
    public boolean isDropSelectItem() {
        return this.dropSelectItem;
    }

    /**
     * Returns whether moving the SelectItem should be allowed
     * @return Whether moving the SelectItem should be allowed
     */
    public boolean isMoveSelectItem() {
        return this.moveSelectItem;
    }

    /**
     * Returns whether we should balance Teams
     * @return Whether we should balance Teams
     */
    public boolean isBalanceTeams() {
        return this.balanceTeams;
    }
}
