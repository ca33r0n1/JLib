package com.j0ach1mmall3.jlib.visual;

import com.j0ach1mmall3.jlib.integration.Placeholders;
import com.j0ach1mmall3.jlib.integration.protocolsupport.ProtocolSupportHook;
import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.util.Arrays;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/15
 */
public final class JsonText {
    private Player player;
    private String json;

    /**
     * Constructs a new JsonText
     * @param player The player of this JsonText
     * @param json The Json that should be displayed (If this starts with [text], the text after the prefix will be displayed normally)
     */
    public JsonText(Player player, String json) {
        this.player = player;
        this.json = json;
    }

    /**
     * Returns the Json of this JsonText
     * @return The Json
     */
    public String getJson() {
        return this.json;
    }

    /**
     * Sets the Json of this JsonText
     * @param json The new Json
     */
    public void setJson(String json) {
        this.json = json;
    }

    /**
     * Returns the player of this JsonText
     * @return The player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Sets the player of this JsonText
     * @param player The new player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Sends the JsonText
     */
    public void send() {
        this.json = Placeholders.parse(this.json, this.player);
        if(this.json.startsWith("[text]")) {
            this.json = this.json.replace("[text]", "");
            this.player.sendMessage(Placeholders.parse(this.json, this.player));
            return;
        }
        ProtocolSupportHook protocolSupportHook = new ProtocolSupportHook();
        if(protocolSupportHook.isPresent() && !Arrays.asList("1.9", "1.8", "1.7.10", "1.7.5").contains(protocolSupportHook.getVersion(this.player))) return;
        try {
            Constructor packetConstructor = ReflectionAPI.getNmsClass("PacketPlayOutChat").getConstructor(ReflectionAPI.getNmsClass("IChatBaseComponent"), byte.class);
            Object baseComponent = this.getSerializerClass().getMethod("a", String.class).invoke(null, this.json);
            Object packet = packetConstructor.newInstance(baseComponent, (byte) 0);
            ReflectionAPI.sendPacket(this.player, packet);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Private Method to get the ChatSerializer Class
     * @return The ChatSerializer Class
     */
    private Class<?> getSerializerClass() {
        if(ReflectionAPI.verBiggerThan(1, 9) || (ReflectionAPI.verBiggerThan(1, 8) && ReflectionAPI.verBiggerThan(2, 3))) return ReflectionAPI.getNmsClass("IChatBaseComponent$ChatSerializer");
        else return ReflectionAPI.getNmsClass("ChatSerializer");
    }
}
