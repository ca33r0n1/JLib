package com.j0ach1mmall3.jlib.player.corpses;

import com.j0ach1mmall3.jlib.methods.Random;
import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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
        this.id = Random.getInt(10000, Integer.MAX_VALUE);
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
    @SuppressWarnings("deprecation")
    public void spawn() {
        Bukkit.broadcastMessage("spawning corpse");
        try {
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.sendBlockChange(this.location.clone().subtract(0, 2, 0), Material.BED_BLOCK, (byte) 0);
                ReflectionAPI.sendPacket(p, this.getNamedEntitySpawnPacket());
                ReflectionAPI.sendPacket(p, this.getBedPacket());
                ReflectionAPI.sendPacket(p, this.getRelEntityMovePacket());
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
        Field c = packet.getClass().getDeclaredField("c");
        c.setAccessible(true);
        c.setInt(packet, (int) Math.floor(this.location.getX() * 32.0D));
        Field d = packet.getClass().getDeclaredField("d");
        d.setAccessible(true);
        d.setInt(packet, (int) Math.floor((this.location.getY() + 2) * 32.0D));
        Field e = packet.getClass().getDeclaredField("e");
        e.setAccessible(true);
        e.setInt(packet, (int) Math.floor(this.location.getZ() * 32.0D));
        Field f = packet.getClass().getDeclaredField("f");
        f.setAccessible(true);
        f.setByte(packet, (byte) (this.location.getYaw() * 256.0F / 360.0F));
        Field g = packet.getClass().getDeclaredField("g");
        g.setAccessible(true);
        g.setByte(packet, (byte) (this.location.getPitch() * 256.0F / 360.0F));
        return packet;
    }

    /**
     * Returns the BedPacket
     * @return The BedPacket
     * @throws Exception If an exception occurs
     */
    private Object getBedPacket() throws Exception {
        Object packet = ReflectionAPI.getNmsClass("PacketPlayOutBed").getConstructor().newInstance();
        Field a = packet.getClass().getDeclaredField("a");
        a.setAccessible(true);
        a.setInt(packet, this.id);
        Field b = packet.getClass().getDeclaredField("b");
        b.setAccessible(true);
        b.set(packet, ReflectionAPI.getNmsClass("BlockPosition").getConstructor(Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(this.location.getBlockX(), this.location.getBlockY() - 2, this.location.getBlockZ()));
        return packet;
    }

    /**
     * Returns the RelEntityMovePacket
     * @return The RelEntityMovePacket
     * @throws Exception If an exception occurs
     */
    public Object getRelEntityMovePacket() throws Exception {
        Constructor constructor = ReflectionAPI.getNmsClass("PacketPlayOutEntity$PacketPlayOutRelEntityMove").getConstructor(Integer.TYPE, Byte.TYPE, Byte.TYPE, Byte.TYPE, Boolean.TYPE);
        return constructor.newInstance(this.id, (byte) 0, (byte) (-60.8), (byte) 0, false);
    }

    /**
     * Returns the EntityDestroyPacket
     * @return The EntityDestroyPacket
     * @throws Exception If an exception occurs
     */
    private Object getEntityDestroyPacket() throws Exception {
        Constructor constructor = ReflectionAPI.getNmsClass("PacketPlayOutEntityDestroy").getConstructor();
        Object packet = constructor.newInstance();
        Field a = packet.getClass().getDeclaredField("a");
        a.setAccessible(true);
        a.set(packet, new int[]{this.id});
        return packet;
    }
}
