package com.j0ach1mmall3.jlib.minigameapi.spectator;

import com.j0ach1mmall3.jlib.minigameapi.team.Team;
import org.bukkit.entity.Player;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 7/03/2016
 */
public abstract class SpectatorProperties {
    private final Team spectatorTeam;

    /**
     * Constructs a new SpectatorProperties
     * @param spectatorTeam The Spectator Team
     */
    protected SpectatorProperties(Team spectatorTeam) {
        this.spectatorTeam = spectatorTeam;
    }

    /**
     * Returns the Spectator Team
     * @return The Spectator Team
     */
    public Team getSpectatorTeam() {
        return this.spectatorTeam;
    }

    /**
     * Sets a player Spectating
     * @param player The player
     */
    public abstract void setSpectating(Player player);

    /**
     * Unsets a player Spectating
     * @param player The player
     */
    public abstract void unsetSpectating(Player player);
}
