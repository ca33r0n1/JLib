package com.j0ach1mmall3.jlib.effectsapi;

import com.j0ach1mmall3.jlib.effectsapi.util.Util;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 13/03/2016
 */
public final class SphereEffect extends Effect {
    private final double radius;
    private final int particleCount;
    private final List<Vector[]> vectors;

    private int i;

    /**
     * Constructs a new Sphere Effect
     * @param l The Location to play the Effect at
     * @param effect The Effect
     * @param id The id
     * @param data The data
     * @param speed The speed
     * @param viewDistance The view distance
     * @param radius The radius of the Sphere
     * @param particleCount The amount of Particles to display
     */
    public SphereEffect(Location l, org.bukkit.Effect effect, int id, int data, float speed, int viewDistance, double radius, int particleCount) {
        super(l, effect, id, data, speed, viewDistance, 1);
        this.radius = radius;
        this.particleCount = particleCount;
        this.vectors = Util.getSphere(this.radius, (int) Math.sqrt(this.particleCount) * 2);
    }

    @Override
    public void run() {
        for(Vector vector : this.vectors.get(this.i++)) {
            this.l.getWorld().spigot().playEffect(this.l.clone().add(vector), this.effect, this.id, this.data, 0, 0, 0, this.speed, 1, this.viewDistance);
        }
        if(this.i >= this.vectors.size()) this.cancel();
    }
}
