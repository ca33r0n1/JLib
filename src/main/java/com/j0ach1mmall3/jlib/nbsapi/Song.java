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

    Song(Map<Integer, Tick> ticks, short length, String title, String author, String description, float speed) {
        this.ticks = ticks;
        this.length = length;
        this.title = title;
        this.author = author;
        this.description = description;
        this.speed = speed;
    }

    public short getLength() {
        return this.length;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getDescription() {
        return this.description;
    }

    public float getSpeed() {
        return this.speed;
    }

    public Tick getTick(int tick) {
        return this.ticks.get(tick);
    }
}
