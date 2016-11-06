package com.j0ach1mmall3.jlib.minigameapi.spectator;

import com.j0ach1mmall3.jlib.minigameapi.team.Team;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 7/03/2016
 */
public final class GameModeSpectatorProperties extends SpectatorProperties {
    private final GameMode defaultGameMode;

    /**
     * Constructs a new GameModeSpectatorProperties
     *
     * @param spectatorTeam   The Spectator Team
     * @param defaultGameMode The default GameMode
     */
    public GameModeSpectatorProperties(Team spectatorTeam, GameMode defaultGameMode) {
        super(spectatorTeam);
        this.defaultGameMode = defaultGameMode;
    }

    @Override
    public void setSpectating(Player player) {
        player.setGameMode(GameMode.SPECTATOR);
    }

    @Override
    public void unsetSpectating(Player player) {
        player.setGameMode(this.defaultGameMode);
    }
}
