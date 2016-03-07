package com.j0ach1mmall3.jlib.minigameapi.spectator;

import com.j0ach1mmall3.jlib.minigameapi.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 7/03/2016
 */
public final class HidePlayerSpectatorProperties extends SpectatorProperties {
    /**
     * Constructs a new HidePlayerSpectatorProperties
     * @param spectatorTeam The Spectator Team
     */
    public HidePlayerSpectatorProperties(Team spectatorTeam) {
        super(spectatorTeam);
    }

    @Override
    public void setSpectating(Player player) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.hidePlayer(player);
        }
        player.setAllowFlight(true);
        player.setFlying(true);
        player.setNoDamageTicks(Integer.MAX_VALUE);
    }

    @Override
    public void unsetSpectating(Player player) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.showPlayer(player);
        }
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setNoDamageTicks(0);
    }
}
