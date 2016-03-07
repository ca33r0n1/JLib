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
import com.j0ach1mmall3.jlib.minigameapi.game.events.PlayerStartSpectatingEvent;
import com.j0ach1mmall3.jlib.minigameapi.game.events.PlayerStopSpectatingEvent;
import com.j0ach1mmall3.jlib.minigameapi.map.Map;
import com.j0ach1mmall3.jlib.minigameapi.map.RestockChest;
import com.j0ach1mmall3.jlib.minigameapi.spectator.SpectatorProperties;
import com.j0ach1mmall3.jlib.minigameapi.team.Team;
import com.j0ach1mmall3.jlib.minigameapi.team.TeamProperties;
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

    private final Plugin plugin;
    private final String name;
    private final Map map;
    private final int minPlayers;
    private final int countdown;
    private final GameRuleSet ruleSet;
    private final GameChatType chatType;
    private final JScoreboard jScoreboard;
    private final TeamProperties teamProperties;
    private final SpectatorProperties spectatorProperties;
    private final GameCallbackHandlers gameCallbackHandlers;

    private String gameState = GameState.WAITING;
    private int runnableId;

    /**
     * Constructs a new Game
     * @param plugin The Plugin instance associated with this Game
     * @param name The name of the Game
     * @param map The Map associated with the Game
     * @param minPlayers The minimum amount of Players to start with
     * @param countdown The Countdown time
     * @param ruleSet The GameRuleSet of the Game
     * @param chatType The GameChatType of the Game
     * @param jScoreboard The JScoreboard of the Game
     * @param teamProperties The TeamProperties of the Game
     * @param spectatorProperties The SpectatorProperties of the Game
     * @param gameCallbackHandlers The Game CallbackHandlers
     */
    public Game(Plugin plugin, String name, Map map, int minPlayers, int countdown, GameRuleSet ruleSet, GameChatType chatType, JScoreboard jScoreboard, TeamProperties teamProperties, SpectatorProperties spectatorProperties, GameCallbackHandlers gameCallbackHandlers) {
        this.plugin = plugin;
        this.name = name;
        this.map = map;
        this.minPlayers = minPlayers;
        this.countdown = countdown;
        this.ruleSet = ruleSet;
        this.chatType = chatType;
        this.jScoreboard = jScoreboard;
        this.teamProperties = teamProperties;
        this.spectatorProperties = spectatorProperties;
        this.gameCallbackHandlers = gameCallbackHandlers;
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
        if(this.players.size() >= this.minPlayers) this.startCountdown();
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
        if(this.players.size() < this.minPlayers) this.endCountdown(GameEndCountdownEvent.Reason.PLAYER_LEAVE);
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
        PlayerChangeTeamEvent event = new PlayerChangeTeamEvent(this, this.players.get(player), player, team);
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
     * Adds a spectator
     * @param player The spectator
     */
    public void addSpectator(Player player) {
        if(this.getTeam(player).equals(this.spectatorProperties.getSpectatorTeam())) return;
        PlayerStartSpectatingEvent event = new PlayerStartSpectatingEvent(this, player, this.spectatorProperties);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled()) return;
        this.setTeam(player, this.spectatorProperties.getSpectatorTeam());
        this.spectatorProperties.setSpectating(player);
    }

    /**
     * Removes a spectator
     * @param player The spectator
     * @param newTeam The new Team
     */
    public void removeSpectator(Player player, Team newTeam) {
        if(!this.getTeam(player).equals(this.spectatorProperties.getSpectatorTeam())) return;
        PlayerStopSpectatingEvent event = new PlayerStopSpectatingEvent(this, player, this.spectatorProperties);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled()) return;
        this.spectatorProperties.unsetSpectating(player);
        this.setTeam(player, newTeam);
    }

    /**
     * Starts the countdown
     */
    public void startCountdown() {
        final GameStartCountdownEvent event = new GameStartCountdownEvent(this, this.countdown);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(!event.isCancelled()){
            this.gameState = GameState.COUNTDOWN;
            this.runnableId = new BukkitRunnable() {
                private int count;

                @Override
                public void run() {
                    if(this.count >= event.getTime()) {
                        this.cancel();
                        GameEndCountdownEvent event = new GameEndCountdownEvent(Game.this, GameEndCountdownEvent.Reason.TIME);
                        Bukkit.getServer().getPluginManager().callEvent(event);
                        Game.this.runnableId = 0;
                        Game.this.startGame();
                    } else Game.this.gameCallbackHandlers.getCountdownCallbackHandler().callback(event.getTime() - ++this.count);
                }
            }.runTaskTimer(this.plugin, 0, 20).getTaskId();
        }
    }

    /**
     * Stops the countdown
     * @param reason The Reason to end the Countdown
     */
    public void endCountdown(GameEndCountdownEvent.Reason reason) {
        if(!this.gameState.equals(GameState.COUNTDOWN)) return;
        GameEndCountdownEvent event = new GameEndCountdownEvent(this, reason);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if(!event.isCancelled()) {
            this.gameState = GameState.WAITING;
            if(this.runnableId != 0) Bukkit.getScheduler().cancelTask(this.runnableId);
        }
    }

    /**
     * Starts the Game
     */
    public void startGame() {
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
                Game.this.gameCallbackHandlers.getGameTickCallbackHandler().callback(++this.count);
            }
        }.runTaskTimer(this.plugin, 0, 20);
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
     * Returns the Plugin instance associated with this Game
     * @return The Plugin instance
     */
    public Plugin getPlugin() {
        return this.plugin;
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
     * Returns the minimum amount of players required for this Game to start
     * @return The amount of players
     */
    public int getMinPlayers() {
        return this.minPlayers;
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
     * Returns the Game Callbackhandlers
     * @return The Game Callbackhandlers
     */
    public GameCallbackHandlers getGameCallbackHandlers() {
        return this.gameCallbackHandlers;
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
        player.setExp(0);
        player.setLevel(0);
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