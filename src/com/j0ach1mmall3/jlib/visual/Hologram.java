package com.j0ach1mmall3.jlib.visual;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.UUID;

/**
 * Created by j0ach1mmall3 on 18:28 5/09/2015 using IntelliJ IDEA.
 */
public class Hologram {
    private String text;
    private Location location;
    private UUID uuid;

    public Hologram(String text, Location l) {
        this.text = text;
        this.location = l;
    }

    public Hologram(String text, Location l, UUID uuid) {
        this.text = text;
        this.location = l;
        this.uuid = uuid;
    }

    public void update() {
        if(uuid == null) {
            ArmorStand am = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
            this.uuid = am.getUniqueId();
            am.setGravity(false);
            am.setSmall(true);
            am.setVisible(false);
            am.setCustomName(text);
            am.setCustomNameVisible(true);
            am.setNoDamageTicks(Integer.MAX_VALUE);
            am.setRemoveWhenFarAway(false);
        } else {
            ArmorStand am = getArmorStand();
            am.teleport(location);
            am.setCustomName(text);
        }
    }

    public void remove() {
        if(uuid != null) {
            ArmorStand am = getArmorStand();
            am.remove();
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location l) {
        this.location = l;
    }

    private ArmorStand getArmorStand() {
        for(Entity ent : location.getWorld().getEntities()) {
            if(ent.getUniqueId().equals(uuid)) return (ArmorStand) ent;
        }
        return null;
    }

    public static Hologram getByArmorStand(ArmorStand am) {
        return new Hologram(am.getCustomName(), am.getLocation(), am.getUniqueId());
    }
}
