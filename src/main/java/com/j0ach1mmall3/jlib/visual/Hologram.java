package com.j0ach1mmall3.jlib.visual;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.UUID;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/09/15
 */
public final class Hologram {
    private String text;
    private Location location;
    private UUID uuid;

    /**
     * Constructs a new Hologram
     *
     * @param text     The text of this Hologram
     * @param location The location of this Hologram
     */
    public Hologram(String text, Location location) {
        this(text, location, null);
    }

    /**
     * Constructs a new Hologram from an existing ArmorStand
     *
     * @param text     The text of this Hologram
     * @param location The location of this Hologram
     * @param uuid     The UUID of the ArmorStand
     */
    public Hologram(String text, Location location, UUID uuid) {
        this.text = text;
        this.location = location;
        this.uuid = uuid;
    }

    /**
     * Updates the Hologram to display the text
     */
    public void update() {
        ArmorStand am = this.getArmorStand();
        if (am == null) {
            am = (ArmorStand) this.location.getWorld().spawnEntity(this.location, EntityType.ARMOR_STAND);
            this.uuid = am.getUniqueId();
            am.setGravity(false);
            am.setSmall(true);
            am.setVisible(false);
            am.setCustomName(this.text);
            am.setCustomNameVisible(true);
            am.setNoDamageTicks(Integer.MAX_VALUE);
            am.setRemoveWhenFarAway(false);
        } else {
            am.teleport(this.location);
            am.setCustomName(this.text);
        }
    }

    /**
     * Removes the Hologram
     */
    public void remove() {
        ArmorStand am = this.getArmorStand();
        if (am != null) am.remove();
    }

    /**
     * Returns the text
     *
     * @return The text
     */
    public String getText() {
        return this.text;
    }

    /**
     * Sets the text
     *
     * @param text The new text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Returns the location
     *
     * @return The location
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Sets the location
     *
     * @param location The new location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Returns the ArmorStand entity associated with this Hologram
     *
     * @return The ArmorStand entity
     */
    private ArmorStand getArmorStand() {
        for (Entity ent : this.location.getWorld().getEntities()) {
            if (ent.getUniqueId().equals(this.uuid)) return (ArmorStand) ent;
        }
        return null;
    }

    /**
     * Returns a Hologram by an ArmorStand
     *
     * @param armorStand The ArmorStand
     * @return The Hologram
     */
    public static Hologram getByArmorStand(ArmorStand armorStand) {
        return new Hologram(armorStand.getCustomName(), armorStand.getLocation(), armorStand.getUniqueId());
    }
}
