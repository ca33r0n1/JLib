package com.j0ach1mmall3.jlib.nbsapi.songplayer;

import com.j0ach1mmall3.jlib.nbsapi.Song;
import com.j0ach1mmall3.jlib.nbsapi.Tick;
import com.j0ach1mmall3.jlib.nbsapi.songplayer.events.SongPlayerStartEvent;
import com.j0ach1mmall3.jlib.nbsapi.songplayer.events.SongPlayerStopEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/03/2016
 */
public abstract class SongPlayer {
    private final Set<Player> players = Collections.newSetFromMap(new ConcurrentHashMap<Player, Boolean>());
    private final Song song;
    private final boolean repeat;
    private final boolean stopWhenEmpty;

    private int tick = -1;
    private boolean running;


    /**
     * Constructs a new SongPlayer
     *
     * @param song          The Song to play
     * @param repeat        Whether we should repeat the Song when it ends
     * @param stopWhenEmpty Whether we should stop the SongPlayer if no more players are listening
     */
    protected SongPlayer(Song song, boolean repeat, boolean stopWhenEmpty) {
        this.song = song;
        this.repeat = repeat;
        this.stopWhenEmpty = stopWhenEmpty;
    }

    /**
     * Adds a player to the SongPlayer
     *
     * @param player The player to add
     */
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    /**
     * Removes a player from the SongPlayer
     *
     * @param player The player to remove
     */
    public void removePlayer(Player player) {
        this.players.remove(player);
        if (this.players.isEmpty() && this.stopWhenEmpty) this.stop();
    }

    /**
     * Starts playing
     */
    public void start() {
        if (this.running) return;
        SongPlayerStartEvent event = new SongPlayerStartEvent(this);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        this.running = true;
        new Thread(() -> {
            while (this.running) {
                long startTime = System.currentTimeMillis();
                if (++this.tick >= this.song.getLength()) {
                    if (this.repeat) this.tick = -1;
                    else this.stop();
                }
                Tick tick1 = this.song.getTick(this.tick);
                for (Player player : this.players) {
                    if (tick1 != null && player.isOnline()) tick1.play(player, this.getLocation(player));
                    else if (!player.isOnline()) this.removePlayer(player);
                }
                long duration = System.currentTimeMillis() - startTime;
                float delayMillis = 1000 / this.song.getSpeed();
                if (duration < delayMillis) {
                    try {
                        Thread.sleep((long) (delayMillis - duration));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * Stops playing
     */
    public void stop() {
        if (!this.running) return;
        SongPlayerStopEvent event = new SongPlayerStopEvent(this);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        this.running = false;
        this.tick = -1;
    }

    /**
     * Returns whether the SongPlayer is playing
     *
     * @return Whether the SongPlayer is playing
     */
    public boolean isPlaying() {
        return this.running;
    }

    /**
     * Returns the Location to play at for a player
     *
     * @param player The player
     * @return The Location
     */
    protected abstract Location getLocation(Player player);
}
