package com.j0ach1mmall3.jlib.storage.serialization;

import com.j0ach1mmall3.jlib.methods.Parsing;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 2/11/2015
 */
public final class SerializedLocation implements JLibSerializable {
    private Location location;
    private String string;

    /**
     * Constructs a new SerializedLocation
     * @param location The Location
     */
    public SerializedLocation(Location location) {
        this.location = location;
        this.string = location.getWorld().getName() + "|" + location.getX() + "|" + location.getY() + "|" + location.getZ() + "|" + location.getYaw() + "|" + location.getPitch();
    }

    /**
     * Constructs a new SerializedLocation
     * @param string The String
     */
    public SerializedLocation(String string) {
        String[] splitted = string.split("\\|");
        this.location = new Location(Bukkit.getWorld(splitted[0]), Parsing.parseDouble(splitted[1]), Parsing.parseDouble(splitted[2]), Parsing.parseDouble(splitted[3]), Parsing.parseFloat(splitted[3]), Parsing.parseFloat(splitted[5]));
        this.string = string;
    }

    /**
     * Returns the Location
     * @return The Location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @see JLibSerializable#getString()
     */
    public String getString() {
        return string;
    }
}
