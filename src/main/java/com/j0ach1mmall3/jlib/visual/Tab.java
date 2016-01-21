package com.j0ach1mmall3.jlib.visual;

import com.j0ach1mmall3.jlib.integration.Placeholders;
import com.j0ach1mmall3.jlib.integration.protocolsupport.ProtocolSupportHook;
import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Collections;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public final class Tab {
    private Player player;
    private String header;
    private String footer;

    /**
     * Constructs a new Tab
     * @param player The player of this Tab
     * @param header The Header of this Tab
     * @param footer The Footer of this Tab
     */
    public Tab(Player player, String header, String footer) {
        this.player = player;
        this.header = header;
        this.footer = footer;
    }

    /**
     * Returns the player of this Tab
     * @return The player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Sets the player of this Tab
     * @param player The new player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Returns the Header of this Tab
     * @return The Header
     */
    public String getHeader() {
        return this.header;
    }

    /**
     * Sets the Header of this Tab
     * @param header The new Header
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * Returns the Footer of this Tab
     * @return The Footer
     */
    public String getFooter() {
        return this.footer;
    }

    /**
     * Sets the Footer of this Tab
     * @param footer The new Footer
     */
    public void setFooter(String footer) {
        this.footer = footer;
    }

    /**
     * Sends the Tab
     */
    public void send() {
        this.header = Placeholders.parse(this.header, this.player);
        this.footer = Placeholders.parse(this.footer, this.player);
        ProtocolSupportHook protocolSupportHook = new ProtocolSupportHook();
        if(protocolSupportHook.isPresent() && !Collections.singleton("1.8").contains(protocolSupportHook.getVersion(this.player))) return;
        try {
            Constructor packetTabConstructor = ReflectionAPI.getNmsClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(ReflectionAPI.getNmsClass("IChatBaseComponent"));
            Class<?> serializerClass = ReflectionAPI.getNmsClass("IChatBaseComponent$ChatSerializer");
            Object headerPacket = packetTabConstructor.newInstance(serializerClass.getMethod("a", String.class).invoke(null, "{\"text\": \"" + this.header + "\"}"));
            Field field = headerPacket.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(headerPacket, serializerClass.getMethod("a", String.class).invoke(null, "{\"text\": \"" + this.footer + "\"}"));
            ReflectionAPI.sendPacket(this.player, headerPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
