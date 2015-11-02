package com.j0ach1mmall3.jlib.minigameapi.arena;

import org.bukkit.Location;

/**
 * Created by j0ach1mmall3 on 19:13 4/09/2015 using IntelliJ IDEA.
 */
public class Arena {
    private final String identifier;
    private final String name;
    private final ArenaBlockRestorer restorer;
    private final ArenaSelection selection;

    public Arena(String identifier, String name, Location l1, Location l2) {
        this.identifier = identifier;
        this.name = name;
        this.restorer = new ArenaBlockRestorer();
        this.selection = new ArenaSelection(l1, l2);
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public ArenaBlockRestorer getRestorer() {
        return restorer;
    }

    public ArenaSelection getSelection() {
        return selection;
    }
}
