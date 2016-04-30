package com.j0ach1mmall3.jlib.effectsapi.util;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

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
    public static List<Vector[]> getSphere(double radius, int amountRoot) {
        List<Vector[]> vectorz = new ArrayList<>();
        for(int i=1;i < amountRoot + 1;i++) {
            Vector[] vectors = new Vector[amountRoot];
            for(int j=1; j < amountRoot + 1;j++) {
                double angle1 = Math.PI * 2 * i / amountRoot;
                double angle2 = Math.PI * 2 * j / amountRoot;
                double x = radius * Math.sin(angle1) * Math.cos(angle2);
                double y = radius * Math.cos(angle1);
                double z = radius * Math.sin(angle1) * Math.sin(angle2);
                vectors[j - 1] = new Vector(x, y, z);
            }
            vectorz.add(vectors);
        }
        return vectorz;
    }

    public static List<Location> get2dPositions(Location location, String[] shape) {
        List<Location> locations = new ArrayList<>();

        double space = 0.1;
        double defx = location.getX() - (shape[0].length() * space / 2) - space;
        double x = defx;
        double y = location.getY();
        double d = -(location.getYaw() + 180) / 60;
        d += (location.getYaw() < -180 ? 3.25 : 2.985);

        for (String s : shape) {
            for (char c : s.toCharArray()) {
                x += space;
                if (c == 'x') {
                    Location target = location.clone();
                    target.setX(x);
                    target.setY(y);

                    Vector targetVector = target.toVector().subtract(location.toVector());
                    Vector backVector = getBackVector(target);

                    rotateAroundAxisY(targetVector, d);

                    target.add(targetVector);
                    target.add(backVector.multiply(-0.5));

                    locations.add(target);
                }
            }
            x = defx;
            y -= space;
        }

        return locations;
    }

    private static void rotateAroundAxisY(Vector v, double d) {
        double x = v.getX() * Math.cos(d) + v.getZ() * Math.sin(d);
        double z = v.getX() * -Math.sin(d) + v.getZ() * Math.cos(d);
        v.setX(x).setZ(z);
    }

    private static Vector getBackVector(Location location) {
        return new Vector(Math.cos(Math.toRadians(location.getYaw() + 90)), 0, Math.sin(Math.toRadians(location.getYaw() + 90)));
    }
}
