package com.j0ach1mmall3.jlib.player;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.events.PlayerOpenGUIEvent;
import com.j0ach1mmall3.jlib.integration.Placeholders;
import com.j0ach1mmall3.jlib.integration.protocolsupport.ProtocolSupportHook;
import com.j0ach1mmall3.jlib.inventory.GUI;
import com.j0ach1mmall3.jlib.inventory.PlayerInventory;
import com.j0ach1mmall3.jlib.methods.Random;
import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import com.j0ach1mmall3.jlib.player.tagchanger.TagChanger;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 21/08/2016
 */
public final class JLibPlayer {

    private final Player player;

    public JLibPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    /**
     * Sends an ActionBar to this player
     * @param message The message
     */
    public void sendActionBar(String message) {
        message = Placeholders.parse(message, this.player);
        ProtocolSupportHook protocolSupportHook = new ProtocolSupportHook();
        if(protocolSupportHook.isPresent() && !Arrays.asList("1.9", "1.8").contains(protocolSupportHook.getVersion(this.player))) return;

        try {
            Constructor packetConstructor = ReflectionAPI.getNmsClass("PacketPlayOutChat").getConstructor(ReflectionAPI.getNmsClass("IChatBaseComponent"), byte.class);
            Object baseComponent = ReflectionAPI.getChatSerializerClass().getMethod("a", String.class).invoke(null, "{\"text\": \"" + message + "\"}");
            Object packet = packetConstructor.newInstance(baseComponent, (byte) 2);
            ReflectionAPI.sendPacket(this.player, packet);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Sends a JsonText to this player
     * @param message The message (In Json, or prefixed with [text])
     */
    public void sendJsonText(String message) {
        message = Placeholders.parse(message, this.player);

        if(message.startsWith("[text]")) {
            message = message.replace("[text]", "");
            this.player.sendMessage(message);
            return;
        }

        ProtocolSupportHook protocolSupportHook = new ProtocolSupportHook();
        if(protocolSupportHook.isPresent() && !Arrays.asList("1.9", "1.8", "1.7.10", "1.7.5").contains(protocolSupportHook.getVersion(this.player))) return;

        try {
            Constructor packetConstructor = ReflectionAPI.getNmsClass("PacketPlayOutChat").getConstructor(ReflectionAPI.getNmsClass("IChatBaseComponent"));
            Object baseComponent = ReflectionAPI.getChatSerializerClass().getMethod("a", String.class).invoke(null, message);
            Object packet = packetConstructor.newInstance(baseComponent);
            ReflectionAPI.sendPacket(this.player, packet);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Sends a Title to this player
     * @param fadeIn The fade in time
     * @param fadeOut The fade out time
     * @param stay The staying time
     * @param message The message
     */
    public void sendTitle(int fadeIn, int fadeOut, int stay, String message) {
        message = Placeholders.parse(message, this.player);
        ProtocolSupportHook protocolSupportHook = new ProtocolSupportHook();
        if(protocolSupportHook.isPresent() && !Arrays.asList("1.9", "1.8").contains(protocolSupportHook.getVersion(this.player))) return;

        try {
            Constructor packetConstructor = ReflectionAPI.getNmsClass("PacketPlayOutTitle").getConstructor(ReflectionAPI.getNmsClass("PacketPlayOutTitle$EnumTitleAction"), ReflectionAPI.getNmsClass("IChatBaseComponent"), int.class, int.class, int.class);
            Object baseComponent = ReflectionAPI.getChatSerializerClass().getMethod("a", String.class).invoke(null, "{\"text\": \"" + message + "\"}");
            Object titlePacket = packetConstructor.newInstance(ReflectionAPI.getNmsClass("PacketPlayOutTitle$EnumTitleAction").getEnumConstants()[0], baseComponent, fadeIn, stay, fadeOut);
            ReflectionAPI.sendPacket(this.player, titlePacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a SubTitle to this player
     * @param fadeIn The fade in time
     * @param fadeOut The fade out time
     * @param stay The staying time
     * @param message The message
     */
    public void sendSubTitle(int fadeIn, int fadeOut, int stay, String message) {
        message = Placeholders.parse(message, this.player);
        ProtocolSupportHook protocolSupportHook = new ProtocolSupportHook();
        if(protocolSupportHook.isPresent() && !Arrays.asList("1.9", "1.8").contains(protocolSupportHook.getVersion(this.player))) return;

        try {
            Constructor packetConstructor = ReflectionAPI.getNmsClass("PacketPlayOutTitle").getConstructor(ReflectionAPI.getNmsClass("PacketPlayOutTitle$EnumTitleAction"), ReflectionAPI.getNmsClass("IChatBaseComponent"), int.class, int.class, int.class);
            Object baseComponent = ReflectionAPI.getChatSerializerClass().getMethod("a", String.class).invoke(null, "{\"text\": \"" + message + "\"}");
            Object titlePacket = packetConstructor.newInstance(ReflectionAPI.getNmsClass("PacketPlayOutTitle$EnumTitleAction").getEnumConstants()[1], baseComponent, fadeIn, stay, fadeOut);
            ReflectionAPI.sendPacket(this.player, titlePacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends a Tab to this player
     * @param header The header
     * @param footer The footer
     */
    public void sendTab(String header, String footer) {
        header = Placeholders.parse(header, this.player);
        footer = Placeholders.parse(footer, this.player);
        ProtocolSupportHook protocolSupportHook = new ProtocolSupportHook();
        if(protocolSupportHook.isPresent() && !Arrays.asList("1.9", "1.8").contains(protocolSupportHook.getVersion(this.player))) return;

        try {
            Constructor packetTabConstructor = ReflectionAPI.getNmsClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(ReflectionAPI.getNmsClass("IChatBaseComponent"));
            Object headerPacket = packetTabConstructor.newInstance(ReflectionAPI.getChatSerializerClass().getMethod("a", String.class).invoke(null, "{\"text\": \"" + header + "\"}"));
            Field field = headerPacket.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(headerPacket, ReflectionAPI.getChatSerializerClass().getMethod("a", String.class).invoke(null, "{\"text\": \"" + footer + "\"}"));
            ReflectionAPI.sendPacket(this.player, headerPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Freezes this player
     * @param jump Whether the player should still be able to jump
     */
    public void freeze(boolean jump) {
        this.player.setWalkSpeed(0);
        if(!jump) this.player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 128, true, false));
    }

    /**
     * Unfreezes this player
     */
    public void unFreeze() {
        this.player.setWalkSpeed(0.2F);
        this.player.removePotionEffect(PotionEffectType.JUMP);
    }

    /**
     * Returns whether this player is frozen
     * @return Whether this player is frozen
     */
    public boolean isFrozen() {
        return this.player.getWalkSpeed() == 0;
    }

    /**
     * Returns the last time (in milliseconds) this player has moved
     * @return The last time this player has moved
     */
    public long getLastMoved() {
        return JavaPlugin.getPlugin(Main.class).getPlayerListener().getLastMoved(this.player);
    }

    /**
     * Returns the last time (in milliseconds) this player has walked (This does not include head rotations)
     * @return The last time this player has walked
     */
    public long getLastWalked() {
        return JavaPlugin.getPlugin(Main.class).getPlayerListener().getLastWalked(this.player);
    }

    /**
     * Plays a Note for at a Location
     * @param location The Location where the Note should be played
     * @param instrument The Instrument of the Note
     * @param note The Note that should be played
     */
    public void playNote(Location location, Instrument instrument, Note note) {
        this.player.playNote(location, instrument, note);
    }

    /**
     * Plays a Note at this player's current Location
     * @param instrument The Instrument of the Note
     * @param note The Note that should be played
     */
    public void playNote(Instrument instrument, Note note) {
        this.playNote(this.player.getEyeLocation(), instrument, note);
    }

    /**
     * Plays a Note at a Location
     * @param location The Location where the Note should be played
     * @param instrument The Instrument of the Note
     * @param tone The Tone of the Note
     */
    public void playNote(Location location, Instrument instrument, Note.Tone tone){
        this.playNote(location, instrument, Note.natural(1, tone));
    }

    /**
     * Plays a Note at this player's current Location
     * @param instrument The Instrument of the Note
     * @param tone The Tone of the Note
     */
    public void playNote(Instrument instrument, Note.Tone tone){
        this.playNote(this.player.getEyeLocation(), instrument, tone);
    }

    /**
     * Plays a Sound at a Location
     * @param sound The Sound that should be played
     * @param location The Location where the Sound should be played
     */
    public void playSound(Sound sound, Location location){
        this.player.playSound(location, sound, 1, 1);
    }

    /**
     * Plays a Sound at this player's current Location
     * @param sound The Sound that should be played
     */
    public void playSound(Sound sound){
        this.playSound(sound, this.player.getEyeLocation());
    }

    /**
     * Returns whether this player has a 'Custom Permission'
     * Custom Permissions are permissions not defined in the plugin.yml
     * However, this means that the '*' symbol normally doesn't work
     * This method will test for every possible combination with the '*' symbol
     * @param permission The permission node to test
     * @return Wether the player has the permission or one of it's parents
     */
    public boolean hasCustomPermission(String permission) {
        if (this.player.hasPermission(permission) || this.player.hasPermission("*")) return true;
        String[] components = permission.split("\\.");
        String perm = components[0] + '.';
        for (int i = 1; i < components.length; i++) {
            if (this.player.hasPermission(perm + '*')) return true;
            if (this.player.hasPermission('-' + perm + '*')) return false;
            perm = perm + components[i] + '.';
        }
        return false;
    }

    /**
     * Sets the NameTag of this player
     * @param nameTag The NameTag
     */
    public void setNameTag(String nameTag) {
        TagChanger.registerTag(this.player.getUniqueId(), nameTag);
    }

    /**
     * Resets the NameTag of this player
     */
    public void resetNameTag() {
        TagChanger.unregisterTag(this.player.getUniqueId());
    }

    /**
     * Opens a GUI for this player
     * @param gui The GUI
     */
    public void openGUI(GUI gui) {
        PlayerOpenGUIEvent event = new PlayerOpenGUIEvent(this.player, gui);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled()) return;
        Inventory inventory = event.getGui().getInventory();
        this.player.openInventory(inventory);
    }

    /**
     * Returns this player's PlayerInventory
     * @return This player's PlayerInventory
     */
    public PlayerInventory getPlayerInventory() {
        return new PlayerInventory(this.player);
    }


    /**
     * Spawns a Corpse of this player at the specified location
     * @param location The location
     * @return The ID of the Corpse (Keep this for removal purposes)
     */
    @SuppressWarnings("deprecation")
    public int spawnCorpse(Location location) {
        int id = Random.getInt(10000, Integer.MAX_VALUE);

        try {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendBlockChange(location.clone().subtract(0, 2, 0), Material.BED_BLOCK, (byte) 0);

                Object packet1 = ReflectionAPI.getNmsClass("PacketPlayOutNamedEntitySpawn").getConstructor(ReflectionAPI.getNmsClass("EntityHuman")).newInstance(ReflectionAPI.getHandle((Object) this.player));
                ReflectionAPI.setField(packet1, "a", id);
                ReflectionAPI.setField(packet1, "c", (int) Math.floor(location.getX() * 32));
                ReflectionAPI.setField(packet1, "d", (int) Math.floor((location.getY() + 2) * 32));
                ReflectionAPI.setField(packet1, "e", (int) Math.floor(location.getZ() * 32));
                ReflectionAPI.setField(packet1, "f", (byte) (location.getYaw() * 32 / 45));
                ReflectionAPI.setField(packet1, "g", (byte) (location.getPitch() * 32 / 45));
                ReflectionAPI.sendPacket(p, packet1);

                Object packet2 = ReflectionAPI.getNmsClass("PacketPlayOutBed").newInstance();
                ReflectionAPI.setField(packet2, "a", id);
                ReflectionAPI.setField(packet2, "b", ReflectionAPI.getNmsClass("BlockPosition").getConstructor(int.class, int.class, int.class).newInstance(location.getBlockX(), location.getBlockY() - 2, location.getBlockZ()));
                ReflectionAPI.sendPacket(p, packet2);

                ReflectionAPI.sendPacket(p, ReflectionAPI.getNmsClass("PacketPlayOutEntity$PacketPlayOutRelEntityMove").getConstructor(int.class, byte.class, byte.class, byte.class, boolean.class).newInstance(id, (byte) 0, (byte) -60.8, (byte) 0, false));
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
