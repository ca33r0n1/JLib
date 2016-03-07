package com.j0ach1mmall3.jlib.minigameapi.spectator;

import com.j0ach1mmall3.jlib.minigameapi.team.Team;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 7/03/2016
 */
public final class InvisibilitySpectatorProperties extends SpectatorProperties {
    /**
     * Constructs a new InvisibilitySpectatorProperties
     * @param spectatorTeam The Spectator Team
     */
    public InvisibilitySpectatorProperties(Team spectatorTeam) {
        super(spectatorTeam);
    }

    @Override
    public void setSpectating(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
        player.setAllowFlight(true);
        player.setFlying(true);
        player.setNoDamageTicks(Integer.MAX_VALUE);
    }

    @Override
    public void unsetSpectating(Player player) {
        player.removePotionEffect(PotionEffectType.INVISIBILITY);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setNoDamageTicks(0);
    }
}
