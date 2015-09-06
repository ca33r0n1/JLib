package com.j0ach1mmall3.jlib.minigameapi.game;

import com.j0ach1mmall3.jlib.minigameapi.MinigameAPI;
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
public class Game {
    private String name;
    private Arena arena;
    private List<Team> teams = new ArrayList<>();
    private GameRuleSet ruleSet;
    private GameChatType chatType;
    private GamePvPType pvpType;
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
        MinigameAPI.registerGame(this);
    }

    public void unRegister() {
        MinigameAPI.unregisterGame(this);
    }

    public void registerTeam(Team team) {
        teams.add(team);
    }

    public void unregisterTeam(Team team) {
        teams.remove(team);
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void addPlayer(Player p, String teamm) {
        for(Team team : teams) {
            if(team.getIdentifier().equals(teamm)) team.addMember(p);
        }
    }

    public void removePlayer(Player p, String teamm) {
        for(Team team : teams) {
            if(team.getIdentifier().equals(teamm)) team.removeMember(p);
        }
    }

    public Team getTeam(Player p) {
        for(Team team : teams) {
            if(team.containsMember(p)) return team;
        }
        return null;
    }

    public void startCountdown(int time) {
        GameStartCountdownEvent event = new GameStartCountdownEvent(this, time);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(!event.isCancelled()) gameState = GameState.COUNTDOWN;
    }

    public void startGame() {
        GameStartEvent event = new GameStartEvent(this);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(!event.isCancelled()) gameState = GameState.INGAME;
    }

    public void endGame() {
        GameEndEvent event = new GameEndEvent(this);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(!event.isCancelled()) {
            gameState = GameState.ENDING;
            arena.getRestorer().restore();
        }
    }

    public String getName() {
        return name;
    }

    public Arena getArena() {
        return arena;
    }

    public GameRuleSet getRuleSet() {
        return ruleSet;
    }

    public GameChatType getChatType() {
        return chatType;
    }

    public GamePvPType getPvpType() {
        return pvpType;
    }

    public GameState getGameState() {
        return gameState;
    }
}
