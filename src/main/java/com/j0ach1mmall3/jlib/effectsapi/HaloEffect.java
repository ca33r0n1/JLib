package com.j0ach1mmall3.jlib.effectsapi;

import com.j0ach1mmall3.jlib.effectsapi.util.Util;
import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 13/03/2016
 */
public final class HaloEffect extends Effect {
    private final Vector[] locations;

    private int i;

    /**
     * Constructs a new Halo Effect
     *
     * @param l             The Location to play the Effect at
     * @param effect        The Effect
     * @param id            The id
     * @param data          The data
     * @param speed         The speed
     * @param viewDistance  The view distance
     * @param radius        The radius of the Halo
     * @param particleCount The amount of Particles to display
     */
    public HaloEffect(Location l, org.bukkit.Effect effect, int id, int data, float speed, int viewDistance, double radius, int particleCount) {
        super(l, effect, id, data, speed, viewDistance, 1);
        this.locations = Util.getCircle(radius, particleCount);
    }

    @Override
    public void run() {
        this.l.getWorld().spigot().playEffect(this.l.clone().add(this.locations[this.i++]), this.effect, this.id, this.data, 0, 0, 0, this.speed, 1, this.viewDistance);
        if (this.i >= this.locations.length) this.cancel();
    }
}
