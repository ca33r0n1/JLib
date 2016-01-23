package com.j0ach1mmall3.jlib.minigameapi;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import com.j0ach1mmall3.jlib.minigameapi.listeners.*;
import com.j0ach1mmall3.jlib.minigameapi.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

/**
 * @author j0ach1mmall3
 * @since 4/09/15
 */
public final class MinigameAPI {
    private final Main plugin;
    private final Set<Game> games = new HashSet<>();

    /**
     * Initialises the MinigameAPI
     * @param plugin Main plugin
     */
    public MinigameAPI(Main plugin) {
        this.plugin = plugin;
        new BlockListener(plugin);
        if(!Bukkit.getBukkitVersion().split("\\-")[0].equals("1.2.5")) {
            new ChatListener(plugin);
        } else {
            new OldChatListener(plugin);
        }
        new PvPListener(plugin);
        new ItemListener(plugin);
    }

    /**
     * Registers a Game
     * @param game The Game
     */
    public void registerGame(Game game) {
        this.games.add(game);
    }

    /**
     * Unregisters a Game
     * @param game The Game
     */
    public void unregisterGame(Game game) {
        this.games.remove(game);
    }

    /**
     * Returns the registered Games
     * @return The registered Games
     */
    public Set<Game> getGames() {
        return this.games;
    }

    /**
     * Returns the Game a player is in
     * @param player The player
     * @return The Game
     */
    public Game getGame(Player player) {
        for(Game game : this.games) {
            for(Team team : game.getTeams()) {
                if(team.containsMember(player)) return game;
            }
        }
        return null;
    }

    /**
     * Returns whether a player is in a Game
     * @param player The player
     * @return Whether the player is in a Game
     */
    public boolean isInGame(Player player) {
        return this.getGame(player) != null;
    }
}
