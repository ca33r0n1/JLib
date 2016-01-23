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
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/09/15
 */
public final class Game {
    private final String name;
    private final Arena arena;
    private final List<Team> teams = new ArrayList<>();
    private final GameRuleSet ruleSet;
    private final GameChatType chatType;
    private final GamePvPType pvpType;
    private GameState gameState;

    /**
     * Constructs a new Game
     * @param name The name of the Game
     * @param arena The Arena associated with the Game
     * @param ruleSet The GameRuleSet of the Game
     * @param chatType The GameChatType of the Game
     * @param pvpType The GamePvPType of the Game
     * @see Arena
     * @see GameRuleSet
     * @see GameChatType
     * @see GamePvPType
     */
    public Game(String name, Arena arena, GameRuleSet ruleSet, GameChatType chatType, GamePvPType pvpType) {
        this.name = name;
        this.arena = arena;
        this.ruleSet = ruleSet;
        this.chatType = chatType;
        this.pvpType = pvpType;
        this.gameState = GameState.WAITING;
    }

    /**
     * Registers the Game to the API
     * @see com.j0ach1mmall3.jlib.minigameapi.MinigameAPI
     */
    public void register() {
        ((Main) Bukkit.getPluginManager().getPlugin("JLib")).getApi().registerGame(this);
    }

    /**
     * Unregisters the Game from the API
     * @see com.j0ach1mmall3.jlib.minigameapi.MinigameAPI
     */
    public void unRegister() {
        ((Main) Bukkit.getPluginManager().getPlugin("JLib")).getApi().unregisterGame(this);
    }

    /**
     * Registers a Team to this Game
     * @param team The Team
     * @see Team
     */
    public void registerTeam(Team team) {
        this.teams.add(team);
    }

    /** Unregisters a Team from this Game
     * @param team The Team
     * @see Team
     */
    public void unregisterTeam(Team team) {
        this.teams.remove(team);
    }

    /**
     * Returns all the Teams associated with this Game
     * @return The Teams
     * @see Team
     */
    public List<Team> getTeams() {
        return this.teams;
    }

    /**
     * Adds a player to a Team
     * @param player The player
     * @param team The Team
     * @see Team
     */
    public void addPlayer(Player player, String team) {
        for(Team teamm : this.teams) {
            if(teamm.getIdentifier().equals(team)) teamm.addMember(player);
        }
    }

    /**
     * Removes a player from Team
     * @param player The player
     * @param team The Team
     * @see Team
     */
    public void removePlayer(Player player, String team) {
        for(Team teamm : this.teams) {
            if(teamm.getIdentifier().equals(team)) teamm.removeMember(player);
        }
    }

    /**
     * Returns the Team of a player
     * @param player The player
     * @return The Team
     * @see Team
     */
    public Team getTeam(Player player) {
        for(Team team : this.teams) {
            if(team.containsMember(player)) return team;
        }
        return null;
    }

    /**
     * Starts the Countdown for time seconds
     * @param time The amount of seconds to count down
     * @see GameStartCountdownEvent
     */
    public void startCountdown(int time) {
        GameStartCountdownEvent event = new GameStartCountdownEvent(this, time);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(!event.isCancelled()) this.gameState = GameState.COUNTDOWN;
    }

    /**
     * Starts the Game
     * @see GameStartEvent
     */
    public void startGame() {
        GameStartEvent event = new GameStartEvent(this);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(!event.isCancelled()) this.gameState = GameState.INGAME;
    }

    /**
     * Ends the Game
     * @see GameEndEvent
     */
    public void endGame() {
        GameEndEvent event = new GameEndEvent(this);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(!event.isCancelled()) {
            this.gameState = GameState.ENDING;
            this.arena.getRestorer().restore();
            this.gameState = GameState.WAITING;
        }
    }

    /**
     * Returns the name of the Game
     * @return The name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the Arena associated with this Game
     * @return The Arena
     * @see Arena
     */
    public Arena getArena() {
        return this.arena;
    }

    /**
     * Returns the GameRuleSet of this Game
     * @return The GameRuleSet
     * @see GameRuleSet
     */
    public GameRuleSet getRuleSet() {
        return this.ruleSet;
    }

    /**
     * Returns the GameChatType of this Game
     * @return The GameChatType
     * @see GameChatType
     */
    public GameChatType getChatType() {
        return this.chatType;
    }

    /**
     * Returns the GamePvPType of this Game
     * @return The GamePvPType
     * @see GamePvPType
     */
    public GamePvPType getPvpType() {
        return this.pvpType;
    }

    /**
     * Returns the GameState of this Game
     * @return The GameState
     * @see GameState
     */
    public GameState getGameState() {
        return this.gameState;
    }
}
