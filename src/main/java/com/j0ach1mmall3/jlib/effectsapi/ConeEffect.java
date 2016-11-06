package com.j0ach1mmall3.jlib.effectsapi;

import com.j0ach1mmall3.jlib.effectsapi.util.Util;
import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 13/03/2016
 */
public final class ConeEffect extends Effect {
    private final double radius;
    private final double height;
    private final int particleCount;

    private double i;
    private double y;

    /**
     * Constructs a new Cone Effect
     *
     * @param l             The Location to play the Effect at
     * @param effect        The Effect
     * @param id            The id
     * @param data          The data
     * @param speed         The speed
     * @param viewDistance  The view distance
     * @param radius        The radius of the Cone
     * @param particleCount The amount of Particles to display
     * @param height        The height of the Cone
     */
    public ConeEffect(Location l, org.bukkit.Effect effect, int id, int data, float speed, int viewDistance, double radius, int particleCount, double height) {
        super(l, effect, id, data, speed, viewDistance, 1);
        this.particleCount = particleCount;
        this.radius = radius;
        this.height = height;
    }

    @Override
    public void run() {
        this.y += this.height / this.particleCount;
        for (Vector v : Util.getCircle(this.radius - this.i, (int) (this.particleCount / this.height))) {
            this.l.getWorld().spigot().playEffect(this.l.clone().add(v).add(0, this.y, 0), this.effect, this.id, this.data, 0, 0, 0, this.speed, 1, this.viewDistance);
        }
        this.i += this.radius / this.particleCount;
        if (this.y >= this.height) this.cancel();
    }
}
