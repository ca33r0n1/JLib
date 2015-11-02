package com.j0ach1mmall3.jlib.visual;

import com.j0ach1mmall3.jlib.integration.Placeholders;
import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

/**
 * Created by j0ach1mmall3 on 1:15 19/08/2015 using IntelliJ IDEA.
 */
public class Actionbar {
    private Player player;
    private String message;

    public Actionbar(Player player, String message) {
        this.player = player;
        this.message = message;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void send() {
        this.message = Placeholders.parse(this.message, this.player);
        try {
            Constructor packetConstructor = ReflectionAPI.getNmsClass("PacketPlayOutChat").getConstructor(ReflectionAPI.getNmsClass("IChatBaseComponent"), byte.class);
            Object baseComponent = ReflectionAPI.getNmsClass("IChatBaseComponent$ChatSerializer").getMethod("a", String.class).invoke(null, "{\"text\": \"" + this.message + "\"}");
            Object packet = packetConstructor.newInstance(baseComponent, (byte) 2);
            ReflectionAPI.sendPacket(this.player, packet);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
