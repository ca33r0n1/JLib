package com.j0ach1mmall3.jlib.visual;

import com.j0ach1mmall3.jlib.integration.Placeholders;
import com.j0ach1mmall3.jlib.integration.protocolsupport.ProtocolSupportHook;
import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.util.Collections;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/15
 */
public final class Actionbar {
    private Player player;
    private String message;

    /**
     * Constructs a new Actionbar
     * @param player The player of this Actionbar
     * @param message The message of this Actionbar
     */
    public Actionbar(Player player, String message) {
        this.player = player;
        this.message = message;
    }

    /**
     * Returns the player of this Actionbar
     * @return The player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Sets the player of this Actionbar
     * @param player The new player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Returns the message of this Actionbar
     * @return The message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets the message of this Actionbar
     * @param message The new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Sends the Actiobar
     */
    public void send() {
        this.message = Placeholders.parse(this.message, this.player);
        ProtocolSupportHook protocolSupportHook = new ProtocolSupportHook();
        if(protocolSupportHook.isPresent() && !Collections.singleton("1.8").contains(protocolSupportHook.getVersion(this.player))) return;
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
