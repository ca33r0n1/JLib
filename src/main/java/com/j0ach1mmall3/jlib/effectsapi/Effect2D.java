package com.j0ach1mmall3.jlib.effectsapi;

import com.j0ach1mmall3.jlib.effectsapi.util.Util;
import org.bukkit.Location;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 29/04/2016
 */
public final class Effect2D extends Effect {
    private final List<Location> locations;

    /**
     * Constructs a new 2D Effect
     * @param l The Location to play the Effect at
     * @param effect The Effect
     * @param id The id
     * @param data The data
     * @param speed The speed
     * @param viewDistance The view distance
     * @param shape The shape (x represents a particle)
     */
    public Effect2D(Location l, org.bukkit.Effect effect, int id, int data, float speed, int viewDistance, String[] shape) {
        super(l, effect, id, data, speed, viewDistance, 0);
        this.locations = Util.get2dPositions(l, shape);
    }

    @Override
    public void run() {
        for(Location location : this.locations) {
            this.l.getWorld().spigot().playEffect(location, this.effect, this.id, this.data, 0, 0, 0, this.speed, 1, this.viewDistance);
        }
    }
}
