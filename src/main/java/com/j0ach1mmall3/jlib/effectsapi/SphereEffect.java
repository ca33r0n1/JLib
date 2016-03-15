package com.j0ach1mmall3.jlib.effectsapi;

import com.j0ach1mmall3.jlib.effectsapi.util.Util;
import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 13/03/2016
 */
public final class SphereEffect extends Effect {
    private final double radius;
    private final int particleCount;

    public SphereEffect(Location l, org.bukkit.Effect effect, int id, int data, float speed, int viewDistance, double radius, int particleCount) {
        super(l, effect, id, data, speed, viewDistance, 0);
        this.radius = radius;
        this.particleCount = particleCount;
    }

    @Override
    public void run() {
        for(Vector vector : Util.getSphere(this.radius, (int) Math.sqrt(this.particleCount))) {
            this.l.getWorld().spigot().playEffect(this.l.clone().add(vector), this.effect, this.id, this.data, 0, 0, 0, this.speed, 1, this.viewDistance);
        }
    }
}
