package com.j0ach1mmall3.jlib.nbsapi;

import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/03/2016
 */
public final class Song {
    private final Map<Integer, Tick> ticks;
    private final short length;
    private final String title;
    private final String author;
    private final String description;
    private final float speed;

    /**
     * Constructs a new Song, shouldn't be used externally
     *
     * @param ticks       The Ticks to play
     * @param length      The length of the Song
     * @param title       The title of the Song
     * @param author      The author of the Song
     * @param description The description of the Song
     * @param speed       The speed of the Song
     */
    Song(Map<Integer, Tick> ticks, short length, String title, String author, String description, float speed) {
        this.ticks = ticks;
        this.length = length;
        this.title = title;
        this.author = author;
        this.description = description;
        this.speed = speed;
    }

    /**
     * Returns the length of the Song
     *
     * @return The length
     */
    public short getLength() {
        return this.length;
    }

    /**
     * Returns the title of the Song
     *
     * @return The title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns the author of the Song
     *
     * @return The author
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Returns the description of the Song
     *
     * @return The description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the speed of the Song
     *
     * @return The speed
     */
    public float getSpeed() {
        return this.speed;
    }

    /**
     * Returns the Tick at a position
     *
     * @param tick The position
     * @return The Tick
     */
    public Tick getTick(int tick) {
        return this.ticks.get(tick);
    }
}
