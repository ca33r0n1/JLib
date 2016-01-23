package com.j0ach1mmall3.jlib.storage.serialization;

import com.j0ach1mmall3.jlib.logging.JLogger;
import com.j0ach1mmall3.jlib.methods.Parsing;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 2/11/15
 */
public final class SerializedLocation {
    private final Location location;
    private final String s;

    /**
     * Constructs a new SerializedLocation
     * @param location The Location
     */
    public SerializedLocation(Location location) {
        new JLogger().deprecation();
        this.location = location;
        this.s = location.getWorld().getName() + "|" + location.getX() + "|" + location.getY() + "|" + location.getZ() + "|" + location.getYaw() + "|" + location.getPitch();
    }

    /**
     * Constructs a new SerializedLocation
     * @param s The String
     */
    public SerializedLocation(String s) {
        new JLogger().deprecation();
        String[] splitted = s.split("\\|");
        this.location = new Location(Bukkit.getWorld(splitted[0]), Parsing.parseDouble(splitted[1]), Parsing.parseDouble(splitted[2]), Parsing.parseDouble(splitted[3]), Parsing.parseFloat(splitted[3]), Parsing.parseFloat(splitted[5]));
        this.s = s;
    }

    /**
     * Returns the Location
     * @return The Location
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Returns the String
     * @return The String
     */
    public String getString() {
        return this.s;
    }
}
