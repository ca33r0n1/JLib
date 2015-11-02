package com.j0ach1mmall3.jlib.minigameapi;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import com.j0ach1mmall3.jlib.minigameapi.listeners.*;
import com.j0ach1mmall3.jlib.minigameapi.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by j0ach1mmall3 on 19:13 4/09/2015 using IntelliJ IDEA.
 */
public final class MinigameAPI {
    private Main plugin;
    private final List<Game> games = new ArrayList<>();
    public MinigameAPI(Main plugin) {
        this.plugin = plugin;
    }

    public void registerGame(Game game) {
        games.add(game);
    }

    public void unregisterGame(Game game) {
        games.remove(game);
    }

    public List<Game> getGames() {
        return games;
    }

    public Game getGame(Player p) {
        for(Game game : games) {
            for(Team team : game.getTeams()) {
                if(team.containsMember(p)) return game;
            }
        }
        return null;
    }

    public boolean isInGame(Player p) {
        for(Game game : games) {
            for(Team team : game.getTeams()) {
                if(team.containsMember(p)) return true;
            }
        }
        return false;
    }

    public void setup() {
        new BlockListener(plugin);
        if(!Bukkit.getBukkitVersion().split("\\-")[0].equals("1.2.5")) {
            new ChatListener(plugin);
        } else {
            new OldChatListener(plugin);
        }
        new PvPListener(plugin);
        new ItemListener(plugin);
    }
}
