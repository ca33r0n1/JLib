package com.j0ach1mmall3.jlib.minigameapi.arena;

import org.bukkit.Location;
import org.bukkit.World;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/09/15
 */
public final class Arena {
    private final ArenaBlockRestorer restorer = new ArenaBlockRestorer();
    private final ArenaSelection selection;

    /**
     * Constructs a new Arena
     * @param l1 The 1st corner bound of the Arena
     * @param l2 The 2nd corner bound of the Arena
     */
    public Arena(Location l1, Location l2) {
        this.selection = new ArenaSelection(l1, l2);
    }

    /**
     * Constructs a new Arena
     * @param world The world of the Arena
     */
    public Arena(World world) {
        this(new Location(world, 0, 0, 0), new Location(world, 0, 0, 0));
    }

    /**
     * Returns the ArenaBlockRestorer of the Arena
     * @return The ArenaBlockRestorer
     */
    public ArenaBlockRestorer getRestorer() {
        return this.restorer;
    }

    /**
     * Returns the ArenaSelection of the Arena
     * @return The ArenaSelection
     */
    public ArenaSelection getSelection() {
        return this.selection;
    }

    /**
     * Returns whether this Arena equals another Arena
     * @param arena The other Arena
     * @return Whether this Arena equals another Arena
     */
    public boolean equals(Arena arena) {
        return this.selection.equals(arena.selection);
    }
}
