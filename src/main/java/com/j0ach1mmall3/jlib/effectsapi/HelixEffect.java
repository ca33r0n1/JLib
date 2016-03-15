package com.j0ach1mmall3.jlib.effectsapi;

import com.j0ach1mmall3.jlib.effectsapi.util.Util;
import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 13/03/2016
 */
public final class HelixEffect extends Effect {
    private final double height;
    private final int particleCount;
    private final Vector[] locations;

    private int i;
    private double y;

    public HelixEffect(Location l, org.bukkit.Effect effect, int id, int data, float speed, int viewDistance, double radius, int particleCount, int height) {
        super(l, effect, id, data, speed, viewDistance, 1);
        this.particleCount = particleCount;
        this.height = height;
        this.locations = Util.getCircle(radius, this.particleCount);
    }

    @Override
    public void run() {
        this.y += this.height / this.particleCount;
        this.l.getWorld().spigot().playEffect(this.l.clone().add(this.locations[this.i]).add(0, this.y, 0), this.effect, this.id, this.data, 0, 0, 0, this.speed, 1, this.viewDistance);
        this.i++;
        if(this.y >= this.height) this.cancel();
    }
}
