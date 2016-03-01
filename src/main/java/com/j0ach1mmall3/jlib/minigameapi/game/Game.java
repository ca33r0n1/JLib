package com.j0ach1mmall3.jlib.minigameapi.game;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.minigameapi.classes.Class;
import com.j0ach1mmall3.jlib.minigameapi.game.events.GameEndCountdownEvent;
import com.j0ach1mmall3.jlib.minigameapi.game.events.GameEndEvent;
import com.j0ach1mmall3.jlib.minigameapi.game.events.GameStartCountdownEvent;
import com.j0ach1mmall3.jlib.minigameapi.game.events.GameStartEvent;
import com.j0ach1mmall3.jlib.minigameapi.game.events.PlayerChangeTeamEvent;
import com.j0ach1mmall3.jlib.minigameapi.game.events.PlayerJoinGameEvent;
import com.j0ach1mmall3.jlib.minigameapi.game.events.PlayerLeaveGameEvent;
import com.j0ach1mmall3.jlib.minigameapi.map.Map;
import com.j0ach1mmall3.jlib.minigameapi.map.RestockChest;
import com.j0ach1mmall3.jlib.minigameapi.team.Team;
import com.j0ach1mmall3.jlib.minigameapi.team.TeamProperties;
import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import com.j0ach1mmall3.jlib.visual.scoreboard.JScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/09/15
 */
public final class Game {
    private final Set<Team> teams = new HashSet<>();
    private final java.util.Map<Player, Team> players = new HashMap<>();
    private final java.util.Map<Player, Class> classes = new HashMap<>();
    private final String name;
    private final Map map;
    private final GameRuleSet ruleSet;
    private final GameChatType chatType;
    private final JScoreboard jScoreboard;
    private final TeamProperties teamProperties;

    private String gameState = GameState.WAITING;

    /**
     * Constructs a new Game
     * @param name The name of the Game
     * @param map The Map associated with the Game
     * @param ruleSet The GameRuleSet of the Game
     * @param chatType The GameChatType of the Game
     * @param jScoreboard The JScoreboard of the Game (null to disable)
     * @param teamProperties The TeamProperties of the Game (null to disable)
     */
    public Game(String name, Map map, GameRuleSet ruleSet, GameChatType chatType, JScoreboard jScoreboard, TeamProperties teamProperties) {
        this.name = name;
        this.map = map;
        this.ruleSet = ruleSet;
        this.chatType = chatType;
        this.jScoreboard = jScoreboard;
        this.teamProperties = teamProperties;
    }

    /**
     * Registers the Game to the API
     */
    public void register() {
        ((Main) Bukkit.getPluginManager().getPlugin("JLib")).getApi().registerGame(this);
    }

    /**
     * Unregisters the Game from the API
     */
    public void unregister() {
        ((Main) Bukkit.getPluginManager().getPlugin("JLib")).getApi().unregisterGame(this);
    }

    /**
     * Adds a player to the Game
     * @param player The player
     * @param team The Team
     */
    public void addPlayer(Player player, Team team) {
        PlayerJoinGameEvent event = new PlayerJoinGameEvent(player, this, team);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled()) return;
        this.players.put(player, team);
        this.jScoreboard.addPlayer(team.getIdentifier(), player);
    }

    /**
     * Removes a player from the Game
     * @param player The player
     * @param reason The Reason
     */
    public void removePlayer(Player player, PlayerLeaveGameEvent.Reason reason) {
        PlayerLeaveGameEvent event = new PlayerLeaveGameEvent(player, this, reason);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled()) return;
        this.players.remove(player);
        this.jScoreboard.removePlayer(player);
        player.teleport(this.map.getLobbySpawn());
    }

    /**
     * Sets the Class of a player
     * @param player The player
     * @param clazz The Class
     */
    public void setClass(Player player, Class clazz) {
        this.classes.put(player, clazz);
    }

    /**
     * Returns the Class of a player
     * @param player The player
     * @return The Class
     */
    public Class getClass(Player player) {
        return this.classes.get(player);
    }

    /**
     * Returns whether the Game contains a player
     * @param player The player
     * @return Whether the Game contains the player
     */
    public boolean containsPlayer(Player player) {
        return this.players.containsKey(player);
    }

    /**
     * Teleports a player to their spawn
     * @param player The player
     */
    public void teleportPlayerToSpawn(Player player) {
        player.teleport(this.map.getTeamSpawn(this.players.get(player)));
    }

    /**
     * Returns every player in a Team
     * @param team The Team
     * @return The players
     */
    public Set<Player> getPlayersInTeam(Team team) {
        Set<Player> players = new HashSet<>();
        for(java.util.Map.Entry<Player, Team> entry : this.players.entrySet()) {
            if(entry.getValue().equals(team)) players.add(entry.getKey());
        }
        return players;
    }

    /**
     * Returns all the players in this Game
     * @return The players in this Game
     */
    public Set<Player> getAllPlayers() {
        return this.players.keySet();
    }

    /**
     * Returns the Team of a player
     * @param player The player
     * @return The Team
     */
    public Team getTeam(Player player) {
        return this.players.get(player);
    }

    /**
     * Sets the Team of a player
     * @param player The player
     * @param team The Team
     */
    public void setTeam(Player player, Team team) {
        PlayerChangeTeamEvent event = new PlayerChangeTeamEvent(player, this, this.players.get(player), team);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled()) return;
        this.players.put(player, event.getNewTeam());
        this.jScoreboard.setTeam(player, event.getNewTeam().getIdentifier());
    }

    /**
     * Returns whether 2 players are in the same team
     * @param player1 The 1st player
     * @param player2 The 2nd player
     * @return Whether they are in the same team
     */
    public boolean areInSameTeam(Player player1, Player player2) {
        return this.players.get(player1).equals(this.players.get(player2));
    }

    /**
     * Returns all the Teams in this Game
     * @return The Teams
     */
    public Set<Team> getTeams() {
        return this.teams;
    }

    /**
     * Registers a Team to this Game
     * @param team The Team
     */
    public void registerTeam(Team team) {
        this.teams.add(team);
        this.jScoreboard.addTeam(team);
    }

    /**
     * Starts the countdown for time seconds
     * @param time The amount of seconds to count down
     * @param plugin The Plugin instance to count down with
     * @param callbackHandler The CallbackHandler to call back to when a second elapsed
     */
    public void startCountdown(final int time, Plugin plugin, final CallbackHandler<Integer> callbackHandler) {
        GameStartCountdownEvent event = new GameStartCountdownEvent(this, time);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(!event.isCancelled()){
            this.gameState = GameState.COUNTDOWN;
            new BukkitRunnable() {
                private int count;

                @Override
                public void run() {
                    if(this.count >= time) {
                        this.cancel();
                        GameEndCountdownEvent event = new GameEndCountdownEvent(Game.this);
                        Bukkit.getServer().getPluginManager().callEvent(event);
                    } else callbackHandler.callback(++this.count);
                }
            }.runTaskTimer(plugin, 20, 20);
        }
    }

    /**
     * Starts the Game
     * @param plugin the plugin to start the Game with
     * @param callbackHandler The CallbackHandler to call back to every second
     */
    public void startGame(Plugin plugin, final CallbackHandler<Integer> callbackHandler) {
        GameStartEvent event = new GameStartEvent(this);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(!event.isCancelled()) {
            this.gameState = GameState.INGAME;
            for(java.util.Map.Entry<Player, Team> entry : this.players.entrySet()) {
                this.teleportPlayerToSpawn(entry.getKey());
            }
            for(java.util.Map.Entry<Player, Class> entry : this.classes.entrySet()) {
                entry.getValue().give(entry.getKey());
            }
        }
        new BukkitRunnable() {
            private int count;

            @Override
            public void run() {
                callbackHandler.callback(++this.count);
            }
        }.runTaskTimer(plugin, 20L, 20L);
    }

    /**
     * Ends the Game
     */
    public void endGame() {
        GameEndEvent event = new GameEndEvent(this);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(!event.isCancelled()) {
            this.gameState = GameState.ENDING;
            this.map.getArena().getRestorer().restore();
            for(RestockChest restockChest : this.map.getRestockChests()) {
                restockChest.restock();
            }
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
     * Returns the Map associated with this Game
     * @return The Map
     */
    public Map getMap() {
        return this.map;
    }

    /**
     * Returns the GameRuleSet of this Game
     * @return The GameRuleSet
     */
    public GameRuleSet getRuleSet() {
        return this.ruleSet;
    }

    /**
     * Returns the GameChatType of this Game
     * @return The GameChatType
     */
    public GameChatType getChatType() {
        return this.chatType;
    }

    /**
     * Returns the GameState of this Game
     * @return The GameState
     */
    public String getGameState() {
        return this.gameState;
    }

    /**
     * Returns the JScoreboard of this game
     * @return The JScoreboard
     */
    public JScoreboard getjScoreboard() {
        return this.jScoreboard;
    }

    /**
     * Returns the TeamProperties of this Game
     * @return The TeamProperties
     */
    public TeamProperties getTeamProperties() {
        return this.teamProperties;
    }

    /**
     * Cleans a player of all it's Inventory items, potion effects etc
     * @param player The player
     * @param gameMode The GameMode to set the player to when finished
     * @param clearInventory Whether we should clear the player's inventory
     */
    public static void cleanPlayer(Player player, GameMode gameMode, boolean clearInventory) {
        if(clearInventory) player.getInventory().clear();
        for(PotionEffect potionEffect : player.getActivePotionEffects()) {
            player.removePotionEffect(potionEffect.getType());
        }
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.setTotalExperience(0);
        player.setExhaustion(0);
        player.setFireTicks(0);
        player.resetPlayerTime();
        player.resetPlayerWeather();
        player.setMaximumAir(0);
        player.setPassenger(null);
        player.setMaxHealth(20.0);
        player.setHealth(20.0);
        player.setGameMode(gameMode);
    }

    public interface GameState {
        String WAITING = "waiting";
        String COUNTDOWN = "countdown";
        String INGAME = "ingame";
        String ENDING = "ending";
    }
}
