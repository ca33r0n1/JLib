package com.j0ach1mmall3.jlib.minigameapi.arena;

import org.bukkit.Location;

/**
 * Created by j0ach1mmall3 on 9:00 6/09/2015 using IntelliJ IDEA.
 */
public final class ArenaSelection {
    private int x1;
    private int y1;
    private int z1;
    private int x2;
    private int y2;
    private int z2;

    public ArenaSelection(int x1, int y1, int z1, int x2, int y2, int z2) {
        if(x1 > x2) {
            this.x1 = x2;
            this.x2 = x1;
        } else {
            this.x1 = x1;
            this.x2 = x2;
        }
        if(y1 > y2) {
            this.y1 = y2;
            this.y2 = y1;
        } else {
            this.y1 = y1;
            this.y2 = y2;
        }
        if(z1 > z2) {
            this.z1 = z2;
            this.z2 = z1;
        } else {
            this.z1 = z1;
            this.z2 = z2;
        }
    }

    public ArenaSelection(Location l1, Location l2) {
        this(l1.getBlockX(), l1.getBlockY(), l1.getBlockZ(), l2.getBlockX(), l2.getBlockY(), l2.getBlockZ());
    }

    public int getX1() {
        return this.x1;
    }

    public int getY1() {
        return this.y1;
    }

    public int getZ1() {
        return this.z1;
    }

    public int getX2() {
        return this.x2;
    }

    public int getY2() {
        return this.y2;
    }

    public int getZ2() {
        return this.z2;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setZ1(int z1) {
        this.z1 = z1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public void setZ2(int z2) {
        this.z2 = z2;
    }

    public boolean isInArena(Location l) {
        return (l.getBlockX() > this.x1 && l.getBlockX() < this.x2) && (l.getBlockY() > this.y1 && l.getBlockY() < this.y2) && (l.getBlockZ() > this.y1 && l.getBlockZ() < this.y2);
    }
}
