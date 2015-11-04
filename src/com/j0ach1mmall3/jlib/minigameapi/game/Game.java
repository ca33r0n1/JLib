package com.j0ach1mmall3.jlib.minigameapi.game;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.minigameapi.arena.Arena;
import com.j0ach1mmall3.jlib.minigameapi.game.events.GameEndEvent;
import com.j0ach1mmall3.jlib.minigameapi.game.events.GameStartCountdownEvent;
import com.j0ach1mmall3.jlib.minigameapi.game.events.GameStartEvent;
import com.j0ach1mmall3.jlib.minigameapi.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by j0ach1mmall3 on 19:13 4/09/2015 using IntelliJ IDEA.
 */
public final class Game {
    private final String name;
    private final Arena arena;
    private final List<Team> teams = new ArrayList<>();
    private final GameRuleSet ruleSet;
    private final GameChatType chatType;
    private final GamePvPType pvpType;
    private GameState gameState;

    public Game(String name, Arena arena, GameRuleSet ruleSet, GameChatType chatType, GamePvPType pvpType) {
        this.name = name;
        this.arena = arena;
        this.ruleSet = ruleSet;
        this.chatType = chatType;
        this.pvpType = pvpType;
        this.gameState = GameState.WAITING;
    }

    public void register() {
        ((Main) Bukkit.getPluginManager().getPlugin("JLib")).getApi().registerGame(this);
    }

    public void unRegister() {
        ((Main) Bukkit.getPluginManager().getPlugin("JLib")).getApi().unregisterGame(this);
    }

    public void registerTeam(Team team) {
        this.teams.add(team);
    }

    public void unregisterTeam(Team team) {
        this.teams.remove(team);
    }

    public List<Team> getTeams() {
        return this.teams;
    }

    public void addPlayer(Player p, String teamm) {
        for(Team team : this.teams) {
            if(team.getIdentifier().equals(teamm)) team.addMember(p);
        }
    }

    public void removePlayer(Player p, String teamm) {
        for(Team team : this.teams) {
            if(team.getIdentifier().equals(teamm)) team.removeMember(p);
        }
    }

    public Team getTeam(Player p) {
        for(Team team : this.teams) {
            if(team.containsMember(p)) return team;
        }
        return null;
    }

    public void startCountdown(int time) {
        GameStartCountdownEvent event = new GameStartCountdownEvent(this, time);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(!event.isCancelled()) this.gameState = GameState.COUNTDOWN;
    }

    public void startGame() {
        GameStartEvent event = new GameStartEvent(this);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(!event.isCancelled()) this.gameState = GameState.INGAME;
    }

    public void endGame() {
        GameEndEvent event = new GameEndEvent(this);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(!event.isCancelled()) {
            this.gameState = GameState.ENDING;
            this.arena.getRestorer().restore();
        }
    }

    public String getName() {
        return this.name;
    }

    public Arena getArena() {
        return this.arena;
    }

    public GameRuleSet getRuleSet() {
        return this.ruleSet;
    }

    public GameChatType getChatType() {
        return this.chatType;
    }

    public GamePvPType getPvpType() {
        return this.pvpType;
    }

    public GameState getGameState() {
        return this.gameState;
    }
}
