package com.j0ach1mmall3.jlib.minigameapi.arena;

import org.bukkit.Location;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/09/15
 */
public final class Arena {
    private final String identifier;
    private final String name;
    private final ArenaBlockRestorer restorer;
    private final ArenaSelection selection;

    /**
     * Constructs a new Arena
     * @param identifier The identifier of the Arena
     * @param name The name of the Arena
     * @param l1 The 1st corner bound of the Arena
     * @param l2 The 2nd corner bound of the Arena
     */
    public Arena(String identifier, String name, Location l1, Location l2) {
        this.identifier = identifier;
        this.name = name;
        this.restorer = new ArenaBlockRestorer();
        this.selection = new ArenaSelection(l1, l2);
    }

    /**
     * Returns the identifier of the Arena
     * @return The identifier of the Arena
     */
    public String getIdentifier() {
        return this.identifier;
    }

    /**
     * Returns the name of the Arena
     * @return The name of the Arena
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the ArenaBlockRestorer of the Arena
     * @return The ArenaBlockRestorer
     * @see ArenaBlockRestorer
     */
    public ArenaBlockRestorer getRestorer() {
        return this.restorer;
    }

    /**
     * Returns the ArenaSelection of the Arena
     * @return The ArenaSelection
     * @see ArenaSelection
     */
    public ArenaSelection getSelection() {
        return this.selection;
    }
}
