package com.j0ach1mmall3.jlib.player.corpses;

import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 26/02/2016
 */
public final class Corpse {
    private final int id;
    private final Player player;
    private final Location location;

    /**
     * Constructs a new Corpse instance
     * @param player The player that represents this Corpse
     * @param location The location to spawn the corpse
     */
    public Corpse(Player player, Location location) {
        this.id = -player.getEntityId() + 1000;
        this.player = player;
        this.location = location;
    }

    /**
     * Constructs a new Corpse instance
     * @param player The player that represents this Corpse
     */
    public Corpse(Player player) {
        this(player, player.getLocation());
    }

    /**
     * Returns the player that represents this Corpse
     * @return The player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Returns the location to spawn the corpse
     * @return The location
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Spawns this Corpse
     */
    public void spawn() {
        try {
            for(Player p : Bukkit.getOnlinePlayers()) {
                ReflectionAPI.sendPacket(p, this.getNamedEntitySpawnPacket());
                ReflectionAPI.sendPacket(p, this.getBedPacket());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes this Corpse
     */
    public void remove() {
        try {
            for(Player p : Bukkit.getOnlinePlayers()) {
                ReflectionAPI.sendPacket(p, this.getEntityDestroyPacket());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Returns the NamedEntitySpawnPacket
     * @return The NamedEntitySpawnPacket
     * @throws Exception If an exception occurs
     */
    private Object getNamedEntitySpawnPacket() throws Exception {
        Constructor constructor = ReflectionAPI.getNmsClass("PacketPlayOutNamedEntitySpawn").getConstructor(ReflectionAPI.getNmsClass("EntityHuman"));
        Object packet = constructor.newInstance(ReflectionAPI.getHandle((Object) this.player));
        Field a = packet.getClass().getDeclaredField("a");
        a.setAccessible(true);
        a.set(packet, this.id);
        return packet;
    }

    /**
     * Returns the BedPacket
     * @return The BedPacket
     * @throws Exception If an exception occurs
     */
    private Object getBedPacket() throws Exception {
        Constructor constructor = ReflectionAPI.getNmsClass("PacketPlayOutBed").getConstructor();
        Object packet = constructor.newInstance();
        Field a = packet.getClass().getDeclaredField("a");
        a.setAccessible(true);
        a.set(packet, this.id);
        Field b = packet.getClass().getDeclaredField("b");
        b.setAccessible(true);
        b.set(packet, ReflectionAPI.getNmsClass("BlockPosition").getConstructors()[4].newInstance(this.location.getBlockX(), this.location.getBlockY(), this.location.getBlockZ()));
        return packet;
    }

    /**
     * Returns the EntityDestroyPacket
     * @return The EntityDestroyPacket
     * @throws Exception If an exception occurs
     */
    private Object getEntityDestroyPacket() throws Exception {
        Constructor constructor = ReflectionAPI.getNmsClass("PacketPlayOutEntityDestroy").getConstructors()[0];
        return constructor.newInstance(this.id);
    }
}
