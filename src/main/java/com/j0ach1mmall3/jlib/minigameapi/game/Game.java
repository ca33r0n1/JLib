package com.j0ach1mmall3.jlib.minigameapi.game;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.minigameapi.classes.Class;
import com.j0ach1mmall3.jlib.minigameapi.classes.ClassProperties;
import com.j0ach1mmall3.jlib.minigameapi.game.events.*;
import com.j0ach1mmall3.jlib.minigameapi.game.state.GameState;
import com.j0ach1mmall3.jlib.minigameapi.leaderboard.Leaderboard;
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
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/09/15
 */
public final class Game {
    private final List<GameState> gameStates = new ArrayList<>();
    private final Set<Team> teams = new HashSet<>();
    private final java.util.Map<Player, Team> players = new HashMap<>();
    private final java.util.Map<Player, Class> classes = new HashMap<>();

    private final Plugin plugin;
    private final String name;
    private final Map map;
    private final int minPlayers;
    private final int countdown;

    private JScoreboard jScoreboard;
    private TeamProperties teamProperties;
    private ClassProperties classProperties;
    private SpectatorProperties spectatorProperties;
    private GameCallbackHandlers gameCallbackHandlers;
    private Leaderboard leaderboard;
    private GameState currGameState;
    private int runnableId;

    /**
     * Constructs a new Game
     *
     * @param plugin     The Plugin instance associated with this Game
     * @param name       The name of the Game
     * @param map        The Map associated with the Game
     * @param minPlayers The minimum amount of Players to start with
     * @param countdown  The Countdown time
     */
    public Game(Plugin plugin, String name, Map map, int minPlayers, int countdown) {
        this.plugin = plugin;
        this.name = name;
        this.map = map;
        this.minPlayers = minPlayers;
        this.countdown = countdown;
    }

    /**
     * Returns the Plugin instance associated with this Game
     *
     * @return The Plugin instance
     */
    public Plugin getPlugin() {
        return this.plugin;
    }

    /**
     * Returns the name of the Game
     *
     * @return The name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the Map associated with this Game
     *
     * @return The Map
     */
    public Map getMap() {
        return this.map;
    }

    /**
     * Returns the minimum amount of players required for this Game to start
     *
     * @return The amount of players
     */
    public int getMinPlayers() {
        return this.minPlayers;
    }

    /**
     * Returns the registered GameStates
     *
     * @return The GameStates
     */
    public List<GameState> getGameStates() {
        return this.gameStates;
    }

    /**
     * Returns the current GameState
     *
     * @return The current GameState
     */
    public GameState getCurrGameState() {
        return this.currGameState;
    }

    /**
     * Sets the current GameState
     *
     * @param currGameState The new GameState
     */
    public void setCurrGameState(GameState currGameState) {
        this.currGameState = currGameState;
    }

    /**
     * Returns the JScoreboard of this game
     *
     * @return The JScoreboard
     */
    public JScoreboard getjScoreboard() {
        return this.jScoreboard;
    }

    /**
     * Returns the TeamProperties of this Game
     *
     * @return The TeamProperties
     */
    public TeamProperties getTeamProperties() {
        return this.teamProperties;
    }

    /**
     * Returns the ClassProperties of this Game
     *
     * @return The ClassProperties
     */
    public ClassProperties getClassProperties() {
        return this.classProperties;
    }

    /**
     * Registers the Game to the API
     */
    public void register() {
        JavaPlugin.getPlugin(Main.class).getApi().registerGame(this);
    }

    /**
     * Unregisters the Game from the API
     */
    public void unregister() {
        JavaPlugin.getPlugin(Main.class).getApi().unregisterGame(this);
    }

    /**
     * Registers a JScoreboard
     *
     * @param jScoreboard The JScoreboard
     */
    public void registerJScoreboard(JScoreboard jScoreboard) {
        if (this.jScoreboard != null) throw new IllegalStateException("can't redefine singleton!");
        this.jScoreboard = jScoreboard;
    }

    /**
     * Registers a TeamProperties
     *
     * @param teamProperties The TeamProperties
     */
    public void registerTeamProperties(TeamProperties teamProperties) {
        if (this.teamProperties != null) throw new IllegalStateException("can't redefine singleton!");
        this.teamProperties = teamProperties;
    }

    /**
     * Registers a ClassProperties
     *
     * @param classProperties The ClassProperties
     */
    public void registerClassProperties(ClassProperties classProperties) {
        if (this.classProperties != null) throw new IllegalStateException("can't redefine singleton!");
        this.classProperties = classProperties;
    }

    /**
     * Registers a SpectatorProperties
     *
     * @param spectatorProperties The SpectatorProperties
     */
    public void registerSpectatorProperties(SpectatorProperties spectatorProperties) {
        if (this.spectatorProperties != null) throw new IllegalStateException("can't redefine singleton!");
        this.spectatorProperties = spectatorProperties;
    }

    /**
     * Registers a GameCallbackHandlers
     *
     * @param gameCallbackHandlers The GameCallbackHandlers
     */
    public void registerGameCallbackHandlers(GameCallbackHandlers gameCallbackHandlers) {
        if (this.gameCallbackHandlers != null) throw new IllegalStateException("can't redefine singleton!");
        this.gameCallbackHandlers = gameCallbackHandlers;
    }

    /**
     * Registers a Leaderboard
     *
     * @param leaderboard The Leaderboard
     */
    public void registerLeaderboard(Leaderboard leaderboard) {
        if (this.leaderboard != null) throw new IllegalStateException("can't redefine singleton!");
        this.leaderboard = leaderboard;
    }

    /**
     * Registers a GameState
     *
     * @param gameState The GameState
     */
    public void registerGameState(GameState gameState) {
        this.gameStates.add(gameState);
    }

    /**
     * Adds a player to the Game
     *
     * @param player The player
     * @param team   The Team
     */
    public void addPlayer(Player player, Team team) {
        PlayerJoinGameEvent event = new PlayerJoinGameEvent(player, this, team);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        this.players.put(player, team);
        this.jScoreboard.addPlayer(team.getIdentifier(), player);
        if (this.players.size() >= this.minPlayers) this.startCountdown();
    }

    /**
     * Removes a player from the Game
     *
     * @param player The player
     * @param reason The Reason
     */
    public void removePlayer(Player player, PlayerLeaveGameEvent.Reason reason) {
        PlayerLeaveGameEvent event = new PlayerLeaveGameEvent(player, this, reason);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        this.players.remove(player);
        this.jScoreboard.removePlayer(player);
        player.teleport(this.map.getLobbySpawn());
        if (this.players.size() < this.minPlayers) this.endCountdown(GameEndCountdownEvent.Reason.PLAYER_LEAVE);
    }

    /**
     * Returns whether the Game contains a player
     *
     * @param player The player
     * @return Whether the Game contains the player
     */
    public boolean containsPlayer(Player player) {
        return this.players.containsKey(player);
    }

    /**
     * Teleports a player to their spawn
     *
     * @param player The player
     */
    public void teleportPlayerToSpawn(Player player) {
        player.teleport(this.map.getTeamSpawn(this.players.get(player)));
    }

    /**
     * Returns every player in a Team
     *
     * @param team The Team
     * @return The players
     */
    public Set<Player> getPlayersInTeam(Team team) {
        Set<Player> players = this.players.entrySet().stream().filter(entry -> entry.getValue().equals(team)).map(java.util.Map.Entry::getKey).collect(Collectors.toSet());
        return players;
    }

    /**
     * Returns the 'alive' players in this Game
     *
     * @return The 'alive' players in this Game
     */
    public Set<Player> getAlivePlayers() {
        Set<Player> players = this.players.entrySet().stream().filter(entry -> !entry.getValue().equals(this.spectatorProperties.getSpectatorTeam())).map(java.util.Map.Entry::getKey).collect(Collectors.toSet());
        return players;
    }

    /**
     * Returns all the players in this Game
     *
     * @return The players in this Game
     */
    public Set<Player> getAllPlayers() {
        return this.players.keySet();
    }

    /**
     * Returns the Team of a player
     *
     * @param player The player
     * @return The Team
     */
    public Team getTeam(Player player) {
        return this.players.get(player);
    }

    /**
     * Sets the Team of a player
     *
     * @param player The player
     * @param team   The Team
     */
    public void setTeam(Player player, Team team) {
        PlayerChangeTeamEvent event = new PlayerChangeTeamEvent(this, this.players.get(player), player, team);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        this.players.put(player, event.getNewTeam());
        this.jScoreboard.setTeam(player, event.getNewTeam().getIdentifier());
    }

    /**
     * Returns whether 2 players are in the same team
     *
     * @param player1 The 1st player
     * @param player2 The 2nd player
     * @return Whether they are in the same team
     */
    public boolean areInSameTeam(Player player1, Player player2) {
        return this.players.get(player1).equals(this.players.get(player2));
    }

    /**
     * Returns all the Teams in this Game
     *
     * @return The Teams
     */
    public Set<Team> getTeams() {
        return this.teams;
    }

    /**
     * Registers a Team to this Game
     *
     * @param team The Team
     */
    public void registerTeam(Team team) {
        this.teams.add(team);
        this.jScoreboard.addTeam(team);
    }


    /**
     * Sets the Class of a player
     *
     * @param player The player
     * @param clazz  The Class
     */
    public void setClass(Player player, Class clazz) {
        this.classes.put(player, clazz);
    }

    /**
     * Returns the Class of a player
     *
     * @param player The player
     * @return The Class
     */
    public Class getClass(Player player) {
        return this.classes.get(player);
    }

    /**
     * Adds a spectator
     *
     * @param player The spectator
     */
    public void addSpectator(Player player) {
        if (this.spectatorProperties == null)
            throw new UnsupportedOperationException("no spectatorProperties are registered!");
        if (this.getTeam(player).equals(this.spectatorProperties.getSpectatorTeam())) return;
        PlayerStartSpectatingEvent event = new PlayerStartSpectatingEvent(this, player, this.spectatorProperties);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        this.setTeam(player, this.spectatorProperties.getSpectatorTeam());
        this.spectatorProperties.setSpectating(player);
    }

    /**
     * Removes a spectator
     *
     * @param player  The spectator
     * @param newTeam The new Team
     */
    public void removeSpectator(Player player, Team newTeam) {
        if (this.spectatorProperties == null)
            throw new UnsupportedOperationException("no spectatorProperties are registered!");
        if (!this.getTeam(player).equals(this.spectatorProperties.getSpectatorTeam())) return;
        PlayerStopSpectatingEvent event = new PlayerStopSpectatingEvent(this, player, this.spectatorProperties);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        this.spectatorProperties.unsetSpectating(player);
        this.setTeam(player, newTeam);
    }

    /**
     * Starts the countdown
     */
    public void startCountdown() {
        if (this.gameCallbackHandlers == null)
            throw new UnsupportedOperationException("no gameCallbackHandlers are registered!");
        GameStartCountdownEvent event = new GameStartCountdownEvent(this, this.countdown);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            this.runnableId = new BukkitRunnable() {
                private int count;

                @Override
                public void run() {
                    if (this.count >= event.getTime()) {
                        this.cancel();
                        GameEndCountdownEvent event = new GameEndCountdownEvent(Game.this, GameEndCountdownEvent.Reason.TIME);
                        Bukkit.getServer().getPluginManager().callEvent(event);
                        Game.this.runnableId = 0;
                        Game.this.startGame();
                    } else
                        Game.this.gameCallbackHandlers.getCountdownCallbackHandler().callback(event.getTime() - ++this.count);
                }
            }.runTaskTimer(this.plugin, 0, 20).getTaskId();
        }
    }

    /**
     * Stops the countdown
     *
     * @param reason The Reason to end the Countdown
     */
    public void endCountdown(GameEndCountdownEvent.Reason reason) {
        GameEndCountdownEvent event = new GameEndCountdownEvent(this, reason);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if (!event.isCancelled() && this.runnableId != 0) Bukkit.getScheduler().cancelTask(this.runnableId);
    }

    /**
     * Starts the Game
     */
    public void startGame() {
        if (this.gameCallbackHandlers == null)
            throw new UnsupportedOperationException("no gameCallbackHandlers are registered!");
        GameStartEvent event = new GameStartEvent(this);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            for (java.util.Map.Entry<Player, Team> entry : this.players.entrySet()) {
                this.teleportPlayerToSpawn(entry.getKey());
            }
            for (java.util.Map.Entry<Player, Class> entry : this.classes.entrySet()) {
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
        if (!event.isCancelled()) {
            this.map.getArena().getRestorer().restore();
            this.map.getRestockChests().forEach(RestockChest::restock);
        }
    }

    /**
     * Returns whether the Teams are balanced if a player selects the provided Team
     *
     * @param selectedTeam The Team the player selects
     * @return Whether the Teams are balanced
     */
    public boolean areTeamsBalanced(Team selectedTeam) {
        int playersInSelected = this.getPlayersInTeam(selectedTeam).size();
        for (Team team : this.teams) {
            if (this.getPlayersInTeam(team).size() < playersInSelected && !team.equals(selectedTeam)) return false;
        }
        return true;
    }

    /**
     * Cleans a player of all it's Inventory items, potion effects etc
     *
     * @param player         The player
     * @param gameMode       The GameMode to set the player to when finished
     * @param clearInventory Whether we should clear the player's inventory
     */
    public static void cleanPlayer(Player player, GameMode gameMode, boolean clearInventory) {
        if (clearInventory) player.getInventory().clear();
        for (PotionEffect potionEffect : player.getActivePotionEffects()) {
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
}