package com.j0ach1mmall3.jlib.nbsapi.songplayer;

import com.j0ach1mmall3.jlib.nbsapi.Song;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/03/2016
 */
public final class DynamicSongPlayer extends SongPlayer {
    /**
     * Constructs a new DynamicSongPlayer, that always plays at the player's Location
     *
     * @param song          The Song to play
     * @param repeat        Whether we should repeat the Song when it ends
     * @param stopWhenEmpty Whether we should stop the SongPlayer if no more players are listening
     */
    public DynamicSongPlayer(Song song, boolean repeat, boolean stopWhenEmpty) {
        super(song, repeat, stopWhenEmpty);
    }

    @Override
    protected Location getLocation(Player player) {
        return player.getEyeLocation();
    }
}
