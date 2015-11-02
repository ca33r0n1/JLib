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
        if(this.uuid == null) {
            ArmorStand am = (ArmorStand) this.location.getWorld().spawnEntity(this.location, EntityType.ARMOR_STAND);
            this.uuid = am.getUniqueId();
            am.setGravity(false);
            am.setSmall(true);
            am.setVisible(false);
            am.setCustomName(this.text);
            am.setCustomNameVisible(true);
            am.setNoDamageTicks(Integer.MAX_VALUE);
            am.setRemoveWhenFarAway(false);
        } else {
            ArmorStand am = this.getArmorStand();
            am.teleport(this.location);
            am.setCustomName(this.text);
        }
    }

    public void remove() {
        if(this.uuid != null) {
            ArmorStand am = this.getArmorStand();
            am.remove();
        }
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location l) {
        this.location = l;
    }

    private ArmorStand getArmorStand() {
        for(Entity ent : this.location.getWorld().getEntities()) {
            if(ent.getUniqueId().equals(this.uuid)) return (ArmorStand) ent;
        }
        return null;
    }

    public static Hologram getByArmorStand(ArmorStand am) {
        return new Hologram(am.getCustomName(), am.getLocation(), am.getUniqueId());
    }
}
