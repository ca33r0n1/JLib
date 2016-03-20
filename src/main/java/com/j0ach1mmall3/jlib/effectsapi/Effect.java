package com.j0ach1mmall3.jlib.effectsapi;

import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 13/03/2016
 */
public abstract class Effect extends BukkitRunnable {
    protected final Location l;
    protected final org.bukkit.Effect effect;
    protected final int id;
    protected final int data;
    protected final float speed;
    protected final int viewDistance;
    protected final int updateInterval;

    /**
     * Constructs a new Effect
     * @param l The Location to play the Effect at
     * @param effect The Effect
     * @param id The id
     * @param data The data
     * @param speed The speed
     * @param viewDistance The view distance
     * @param updateInterval The update interval
     */
    public Effect(Location l, org.bukkit.Effect effect, int id, int data, float speed, int viewDistance, int updateInterval) {
        this.l = l;
        this.effect = effect;
        this.id = id;
        this.data = data;
        this.speed = speed;
        this.viewDistance = viewDistance;
        this.updateInterval = updateInterval;
    }

    /**
     * Starts playing the Effect
     * @param plugin The Plugin to play the Effect with
     */
    public final void play(Plugin plugin) {
        if(this.updateInterval > 0) this.runTaskTimerAsynchronously(plugin, this.updateInterval, this.updateInterval);
        else this.runTaskAsynchronously(plugin);
    }
}
