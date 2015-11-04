package com.j0ach1mmall3.jlib.visual;

import com.j0ach1mmall3.jlib.integration.Placeholders;
import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * Created by j0ach1mmall3 on 3:45 19/08/2015 using IntelliJ IDEA.
 */
public final class Tab {
    private Player player;
    private String header;
    private String footer;

    public Tab(Player player, String header, String footer) {
        this.player = player;
        this.header = header;
        this.footer = footer;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFooter() {
        return this.footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    @SuppressWarnings("unchecked")
    public void send() {
        this.header = Placeholders.parse(this.header, this.player);
        this.footer = Placeholders.parse(this.footer, this.player);

        try {
            Constructor packetTabConstructor = ReflectionAPI.getNmsClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(ReflectionAPI.getNmsClass("IChatBaseComponent"));
            Class serializerClass = ReflectionAPI.getNmsClass("IChatBaseComponent$ChatSerializer");
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
