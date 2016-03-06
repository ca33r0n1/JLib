package com.j0ach1mmall3.jlib.nbsapi.songplayer;

import com.j0ach1mmall3.jlib.nbsapi.Song;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/03/2016
 */
public final class DynamicSongPlayer extends SongPlayer {
    public DynamicSongPlayer(Song song, boolean repeat) {
        super(song, repeat);
    }

    @Override
    protected Location getLocation(Player player) {
        return player.getEyeLocation();
    }
}
