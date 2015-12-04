package com.j0ach1mmall3.jlib.minigameapi.arena;

import org.bukkit.Location;

/**
 * Created by j0ach1mmall3 (business.j0ach1mmall3@gmail.com) on 9:00 6/09/2015
 */
public final class ArenaSelection {
    private int x1;
    private int y1;
    private int z1;
    private int x2;
    private int y2;
    private int z2;

    /**
     * Constructs an ArenaSelection, shouldn't be used externally
     * @param x1 The 1st X position
     * @param y1 The 1st Y position
     * @param z1 The 1st Z position
     * @param x2 The 2nd X position
     * @param y2 The 2nd Y position
     * @param z2 The 2nd Z position
     */
    private ArenaSelection(int x1, int y1, int z1, int x2, int y2, int z2) {
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

    /**
     * Constructs an ArenaSelection, shouldn't be used externally
     * @param l1 The 1st Location
     * @param l2 The 2nd Location
     */
    ArenaSelection(Location l1, Location l2) {
        this(l1.getBlockX(), l1.getBlockY(), l1.getBlockZ(), l2.getBlockX(), l2.getBlockY(), l2.getBlockZ());
    }

    /**
     * Returns the 1st X Position
     * @return The 1st X Position
     */
    public int getX1() {
        return this.x1;
    }

    /**
     * Returns the 1st Y Position
     * @return The 1st Y Position
     */
    public int getY1() {
        return this.y1;
    }

    /**
     * Returns the 1st Z Position
     * @return The 1st Z Position
     */
    public int getZ1() {
        return this.z1;
    }

    /**
     * Returns the 2nd X Position
     * @return The 2nd X Position
     */
    public int getX2() {
        return this.x2;
    }

    /**
     * Returns the 2nd Y Position
     * @return The 2nd Y Position
     */
    public int getY2() {
        return this.y2;
    }

    /**
     * Returns the 2nd Z Position
     * @return The 2nd Z Position
     */
    public int getZ2() {
        return this.z2;
    }

    /**
     * Sets the 1st X position
     * @param x1 The new 1st X position
     */
    public void setX1(int x1) {
        this.x1 = x1;
    }

    /**
     * Sets the 1st Y position
     * @param y1 The new 1st Y position
     */
    public void setY1(int y1) {
        this.y1 = y1;
    }

    /**
     * Sets the 1st Z position
     * @param z1 The new 1st Z position
     */
    public void setZ1(int z1) {
        this.z1 = z1;
    }

    /**
     * Sets the 2nd X position
     * @param x2 The new 2nd X position
     */
    public void setX2(int x2) {
        this.x2 = x2;
    }

    /**
     * Sets the 2nd Y position
     * @param y2 The new 2nd Y position
     */
    public void setY2(int y2) {
        this.y2 = y2;
    }

    /**
     * Sets the 2nd Z position
     * @param z2 The new 2nd Z position
     */
    public void setZ2(int z2) {
        this.z2 = z2;
    }

    /**
     * Returns whether a Location is in the Arena
     * @param location The Location
     * @return Wether the Location is in the Arena
     */
    public boolean isInArena(Location location) {
        return (location.getBlockX() > this.x1 && location.getBlockX() < this.x2) && (location.getBlockY() > this.y1 && location.getBlockY() < this.y2) && (location.getBlockZ() > this.y1 && location.getBlockZ() < this.y2);
    }
}
