package com.j0ach1mmall3.jlib.player;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.integration.Placeholders;
import com.j0ach1mmall3.jlib.integration.protocolsupport.ProtocolSupportHook;
import com.j0ach1mmall3.jlib.inventory.JLibItem;
import com.j0ach1mmall3.jlib.methods.General;
import com.j0ach1mmall3.jlib.methods.Random;
import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import com.j0ach1mmall3.jlib.player.tagchanger.TagChanger;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Constructor;
import java.util.Arrays;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 21/08/2016
 */
public final class JLibPlayer {
    private final Player player;

    /**
     * Constructs a new JLibPLayer
     * @param player The player
     */
    public JLibPlayer(Player player) {
        this.player = player;
    }

    /**
     * Returns the player
     * @return The player
     */
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
            ReflectionAPI.setField(headerPacket, "b", ReflectionAPI.getChatSerializerClass().getMethod("a", String.class).invoke(null, "{\"text\": \"" + footer + "\"}"));
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
     * Returns whether the Inventory contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return Wether the Inventory contains the provided ItemStack
     */
    public boolean inInventory(ItemStack itemStack) {
        return this.inInventory(new JLibItem(itemStack));
    }

    /**
     * Returns whether the Hotbar contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return Wether the Hotbar contains the provided ItemStack
     */
    public boolean inHotbar(ItemStack itemStack) {
        return this.inHotbar(new JLibItem(itemStack));
    }

    /**
     * Returns whether the Hand contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return Wether the Hand contains the provided ItemStack
     */
    public boolean inHand(ItemStack itemStack) {
        return this.inHand(new JLibItem(itemStack));
    }

    /**
     * Returns whether the Armor contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return Wether the Armor contains the provided ItemStack
     */
    public boolean inArmor(ItemStack itemStack) {
        return this.inArmor(new JLibItem(itemStack));
    }

    /**
     * Returns whether the Helmet contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return Wether the Helmet contains the provided ItemStack
     */
    public boolean inHelmet(ItemStack itemStack) {
        return this.inHelmet(new JLibItem(itemStack));
    }

    /**
     * Returns whether the Chestplate contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return Wether the Chestplate contains the provided ItemStack
     */
    public boolean inChestplate(ItemStack itemStack) {
        return this.inChestplate(new JLibItem(itemStack));
    }

    /**
     * Returns whether the Leggings contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return Wether the Leggings contains the provided ItemStack
     */
    public boolean inLeggings(ItemStack itemStack) {
        return this.inLeggings(new JLibItem(itemStack));
    }

    /**
     * Returns whether the Boots contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return Wether the Boots contains the provided ItemStack
     */
    public boolean inBoots(ItemStack itemStack) {
        return this.inBoots(new JLibItem(itemStack));
    }

    /**
     * Returns whether the Inventory contains the provided JLibItem
     * @param jLibItem The JLibItem
     * @return Wether the Inventory contains the provided JLibItem
     */
    public boolean inInventory(JLibItem jLibItem) {
        for(ItemStack item : this.player.getInventory().getContents()) {
            if(item != null && jLibItem.isSimilar(item)) return true;
        }
        return false;
    }

    /**
     * Returns whether the Hotbar contains the provided JLibItem
     * @param jLibItem The JLibItem
     * @return Wether the Hotbar contains the provided JLibItem
     */
    public boolean inHotbar(JLibItem jLibItem) {
        for(int i = 0; i < 9; i++) {
            ItemStack item = this.player.getInventory().getItem(i);
            if(item != null && jLibItem.isSimilar(item)) return true;
        }
        return false;
    }

    /**
     * Returns whether the Hand contains the provided JLibItem
     * @param jLibItem The JLibItem
     * @return Wether the Hand contains the provided JLibItem
     */
    @SuppressWarnings("deprecation")
    public boolean inHand(JLibItem jLibItem) {
        return jLibItem.isSimilar(this.player.getItemInHand());
    }

    /**
     * Returns whether the Armor contains the provided JLibItem
     * @param jLibItem The JLibItem
     * @return Wether the Armor contains the provided JLibItem
     */
    public boolean inArmor(JLibItem jLibItem) {
        return this.inHelmet(jLibItem) || this.inChestplate(jLibItem) || this.inLeggings(jLibItem) || this.inBoots(jLibItem);
    }

    /**
     * Returns whether the Helmet contains the provided JLibItem
     * @param jLibItem The JLibItem
     * @return Wether the Helmet contains the provided JLibItem
     */
    public boolean inHelmet(JLibItem jLibItem) {
        return jLibItem.isSimilar(this.player.getInventory().getHelmet());
    }

    /**
     * Returns whether the Chestplate contains the provided JLibItem
     * @param jLibItem The JLibItem
     * @return Wether the Chestplate contains the provided JLibItem
     */
    public boolean inChestplate(JLibItem jLibItem) {
        return jLibItem.isSimilar(this.player.getInventory().getChestplate());
    }

    /**
     * Returns whether the Leggings contains the provided JLibItem
     * @param jLibItem The JLibItem
     * @return Wether the Leggings contains the provided JLibItem
     */
    public boolean inLeggings(JLibItem jLibItem) {
        return jLibItem.isSimilar(this.player.getInventory().getLeggings());
    }

    /**
     * Returns whether the Boots contains the provided JLibItem
     * @param jLibItem The JLibItem
     * @return Wether the Boots contains the provided JLibItem
     */
    public boolean inBoots(JLibItem jLibItem) {
        return jLibItem.isSimilar(this.player.getInventory().getBoots());
    }


    /**
     * Spawns a Corpse of this player at the specified location
     * @param location The location
     * @return The ID of the Corpse (Keep this for removal purposes)
     */
    public int spawnCorpse(Location location) {
        int id = Random.getInt(10000, Integer.MAX_VALUE);

        try {
            Object packet1 = ReflectionAPI.getNmsClass("PacketPlayOutNamedEntitySpawn").getConstructor(ReflectionAPI.getNmsClass("EntityHuman")).newInstance(ReflectionAPI.getHandle((Object) this.player));
            ReflectionAPI.setField(packet1, "a", id);
            ReflectionAPI.setField(packet1, "c", location.getX());
            ReflectionAPI.setField(packet1, "d", location.getY());
            ReflectionAPI.setField(packet1, "e", location.getZ());
            ReflectionAPI.setField(packet1, "f", (byte) (location.getYaw() * 256 / 360));
            ReflectionAPI.setField(packet1, "g", (byte) (location.getPitch() * 256 / 360));

            Object packet2 = ReflectionAPI.getNmsClass("PacketPlayOutBed").newInstance();
            ReflectionAPI.setField(packet2, "a", id);
            ReflectionAPI.setField(packet2, "b", ReflectionAPI.getNmsClass("BlockPosition").getConstructor(int.class, int.class, int.class).newInstance(location.getBlockX(), location.getBlockY() - 2, location.getBlockZ()));

            Object packet3 = ReflectionAPI.getNmsClass("PacketPlayOutEntity$PacketPlayOutRelEntityMove").getConstructor(int.class, long.class, long.class, long.class, boolean.class).newInstance(id, 0L, (long) -60.8, 0L, false);

            General.broadcastBlockChange(location.clone().subtract(0, 2, 0), Material.BED_BLOCK, (byte) 0);
            for (Player p : Bukkit.getOnlinePlayers()) {
                ReflectionAPI.sendPacket(p, packet1);
                ReflectionAPI.sendPacket(p, packet2);
                ReflectionAPI.sendPacket(p, packet3);
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Removes a Corpse
     * @param id The id of the Corpse
     */
    public void remove(int id) {
        try {
            Object packet = ReflectionAPI.getNmsClass("PacketPlayOutEntityDestroy").newInstance();
            ReflectionAPI.setField(packet, "a", new int[]{id});

            for(Player p : Bukkit.getOnlinePlayers()) {
                ReflectionAPI.sendPacket(p, packet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the WorldBorder tint of this player
     * @param percentage The percentage
     */
    public void setWorldborderTint(int percentage) {
        if(percentage < 0) percentage = 0;
        if(percentage > 100) percentage = 100;
        try {
            Object worldBorder = ReflectionAPI.getNmsClass("WorldBorder").newInstance();
            worldBorder.getClass().getMethod("setCenter", double.class, double.class).invoke(worldBorder, this.player.getLocation().getX(), this.player.getLocation().getZ());
            // There's absolutely no math behind any of these numbers
            worldBorder.getClass().getMethod("setWarningDistance", int.class).invoke(worldBorder, 5000000 + percentage * 2000000);
            worldBorder.getClass().getMethod("setWarningTime", int.class).invoke(worldBorder, 0);
            worldBorder.getClass().getMethod("transitionSizeBetween", double.class, double.class, long.class).invoke(worldBorder, 60000000.0D, 60000000.0D, 0L);

            Object packet = ReflectionAPI.getNmsClass("PacketPlayOutWorldBorder").getConstructor(worldBorder.getClass(), ReflectionAPI.getEnumWorldBorderActionClass()).newInstance(worldBorder, ReflectionAPI.getEnumWorldBorderActionClass().getEnumConstants()[3]);

            ReflectionAPI.sendPacket(this.player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
