package com.j0ach1mmall3.jlib.minigameapi.map;

import com.j0ach1mmall3.jlib.minigameapi.arena.Arena;
import com.j0ach1mmall3.jlib.minigameapi.team.Team;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/02/16
 */
public final class Map {
    private final java.util.Map<Team, Location> teamSpawns = new HashMap<>();
    private final java.util.Map<String, List<Location>> customLocations = new HashMap<>();
    private final World world;
    private final Location lobbySpawn;
    private final Arena arena;
    private final Set<RestockChest> restockChests;

    /**
     * Constructs a new Map
     * @param world The World of the Map
     * @param lobbySpawn The Lobby Spawn
     * @param arena The Arena inside the Map
     * @param restockChests The Restock Chests
     */
    public Map(World world, Location lobbySpawn, Arena arena, Set<RestockChest> restockChests) {
        this.world = world;
        this.lobbySpawn = lobbySpawn;
        this.arena = arena;
        this.restockChests = restockChests;
    }

    /**
     * Registers a new Team Spawn
     * @param team The Team
     * @param location The Spawn
     */
    public void registerTeamSpawn(Team team, Location location) {
        this.teamSpawns.put(team, location);
    }

    /**
     * Returns a Team Spawn
     * @param team The Team
     * @return The Spawn
     */
    public Location getTeamSpawn(Team team) {
        return this.teamSpawns.get(team);
    }

    /**
     * Registers custom locations with a certain identifier
     * @param identifier The identifier
     * @param locations The locations
     */
    public void registerCustomLocations(String identifier, List<Location> locations) {
        this.customLocations.put(identifier, locations);
    }

    /**
     * Returns the custom locations with a certain identifier
     * @param identifier The identifier
     * @return The locations
     */
    public List<Location> getCustomLocations(String identifier) {
        return this.customLocations.get(identifier);
    }

    /**
     * Returns the World of the Map
     * @return The World
     */
    public World getWorld() {
        return this.world;
    }

    /**
     * Returns the Lobby Spawn
     * @return The Lobby Spawn
     */
    public Location getLobbySpawn() {
        return this.lobbySpawn;
    }

    /**
     * Returns the Arena inside the Map
     * @return The Arena
     */
    public Arena getArena() {
        return this.arena;
    }

    /**
     * Returns the Restock Chests
     * @return The Restock Chests
     */
    public Set<RestockChest> getRestockChests() {
        return this.restockChests;
    }

    /**
     * Returns whether this Map equals another Map
     * @param map The other Map
     * @return Whether this Map equals another Map
     */
    public boolean equals(Map map) {
        return this.world.getName().equals(map.world.getName()) && this.lobbySpawn.equals(map.lobbySpawn) && this.arena.equals(map.arena) && this.restockChests.equals(map.restockChests);
    }
}
