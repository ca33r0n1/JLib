package com.j0ach1mmall3.jlib.effectsapi.util;

import org.bukkit.util.Vector;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 13/03/2016
 */
public final class Util {
    /**
     * Let nobody instantiate this class
     */
    private Util() {
    }

    /**
     * Creates a Cirlce
     * @param radius The Radius
     * @param amount The amount of Locations
     * @return The Locations
     */
    public static Vector[] getCircle(double radius, int amount) {
        Vector[] vectors = new Vector[amount];
        for(int i=1;i < amount + 1;i++) {
            double angle = Math.PI * 2 * i / amount;
            double x = radius * Math.cos(angle);
            double z = radius * Math.sin(angle);
            vectors[i - 1] = new Vector(x, 0, z);
        }
        return vectors;
    }

    /**
     * Creates a Sphere
     * @param radius The Radius
     * @param amountRoot The root of the amount of Locations
     * @return The Locations
     */
    public static Vector[] getSphere(double radius, int amountRoot) {
        int amount = (int) Math.pow(amountRoot, 2);
        Vector[] vectors = new Vector[amount];
        int k = 0;
        for(int i=1;i < (int) (Math.sqrt(amount) + 1);i++) {
            for(int j=1; j < (int) (Math.sqrt(amount) + 1);j++) {
                double angle1 = Math.PI * 2 * i / Math.sqrt(amount);
                double angle2 = Math.PI * 2 * j / Math.sqrt(amount);
                double x = radius * Math.sin(angle1) * Math.cos(angle2);
                double y = radius * Math.cos(angle1);
                double z = radius * Math.sin(angle1) * Math.sin(angle2);
                vectors[k] = new Vector(x, y, z);
                k++;
            }
        }
        return vectors;
    }
}
