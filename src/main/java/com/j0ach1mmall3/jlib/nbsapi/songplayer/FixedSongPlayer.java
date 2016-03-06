package com.j0ach1mmall3.jlib.nbsapi.songplayer;

import com.j0ach1mmall3.jlib.nbsapi.Song;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/03/2016
 */
public final class FixedSongPlayer extends SongPlayer {
    private final Location location;

    /**
     * Constructs a new DynamicSongPlayer, that always plays at a fixed Location
     * @param song The Song to play
     * @param repeat Whether we should repeat the Song when it ends
     * @param location The location to play at
     * @param stopWhenEmpty Whether we should stop the SongPlayer if no more players are listening
     */
    public FixedSongPlayer(Song song, boolean repeat, boolean stopWhenEmpty, Location location) {
        super(song, repeat, stopWhenEmpty);
        this.location = location;
    }

    @Override
    protected Location getLocation(Player player) {
        return this.location;
    }
}
