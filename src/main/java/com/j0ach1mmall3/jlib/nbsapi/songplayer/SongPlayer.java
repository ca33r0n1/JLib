package com.j0ach1mmall3.jlib.nbsapi.songplayer;

import com.j0ach1mmall3.jlib.nbsapi.Song;
import com.j0ach1mmall3.jlib.nbsapi.Tick;
import com.j0ach1mmall3.jlib.nbsapi.songplayer.events.SongPlayerStartEvent;
import com.j0ach1mmall3.jlib.nbsapi.songplayer.events.SongPlayerStopEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashSet;
import java.util.Set;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/03/2016
 */
public abstract class SongPlayer {
    protected final Set<Player> players = new HashSet<>();
    protected final Song song;
    protected final boolean repeat;

    private int tick = -1;
    private boolean running;
    private Thread thread;


    public SongPlayer(Song song, boolean repeat) {
        this.song = song;
        this.repeat = repeat;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    public void start(Plugin plugin) {
        if(this.isPlaying()) return;
        SongPlayerStartEvent event = new SongPlayerStartEvent(this);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled()) return;
        this.running = true;
        this.thread = this.getThread();
    }

    public void stop() {
        if(!this.isPlaying()) return;
        SongPlayerStopEvent event = new SongPlayerStopEvent(this);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled()) return;
        this.running = false;
        this.tick = -1;
    }

    public boolean isPlaying() {
        return this.running;
    }

    protected abstract Location getLocation(Player player);

    private Thread getThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (SongPlayer.this.running) {
                    long startTime = System.currentTimeMillis();
                    SongPlayer.this.tick++;
                    if(SongPlayer.this.tick >= SongPlayer.this.song.getLength()) {
                        if(SongPlayer.this.repeat) SongPlayer.this.tick = -1;
                        else SongPlayer.this.stop();
                    }
                    for (Player player : SongPlayer.this.players) {
                        Tick tick = SongPlayer.this.song.getTick(SongPlayer.this.tick);
                        if(tick == null) continue;
                        tick.play(player, SongPlayer.this.getLocation(player));
                    }
                    long duration = System.currentTimeMillis() - startTime;
                    float delayMillis = 1000 / SongPlayer.this.song.getSpeed();
                    if (duration < delayMillis) {
                        try {
                            Thread.sleep((long) (delayMillis - duration));
                        } catch (InterruptedException e) {
                            // NOP
                        }
                    }
                }
            }
        });
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
        return thread;
    }
}
