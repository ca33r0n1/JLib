package com.j0ach1mmall3.jlib.minigameapi.arena;

import org.bukkit.Location;

/**
 * Created by j0ach1mmall3 on 19:13 4/09/2015 using IntelliJ IDEA.
 */
public class Arena {
    private String identifier;
    private String name;
    private Location l1;
    private Location l2;
    private ArenaBlockRestorer restorer;

    public Arena(String identifier, String name) {
        this.identifier = identifier;
        this.name = name;
        this.restorer = new ArenaBlockRestorer();
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

    public Location getL1() {
        return l1;
    }

    public Location getL2() {
        return l2;
    }

    public void setL2(Location l2) {
        this.l2 = l2;
    }

    public void setL1(Location l1) {
        this.l1 = l1;
    }

    public boolean isInArena(Location l) {
        return (l.getBlockX() > l1.getBlockX() && l.getBlockX() < l2.getBlockX()) && (l.getBlockY() > l1.getBlockY() && l.getBlockY() < l2.getBlockY()) && (l.getBlockZ() > l1.getBlockZ() && l.getBlockZ() < l2.getBlockZ());
    }
}
