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
