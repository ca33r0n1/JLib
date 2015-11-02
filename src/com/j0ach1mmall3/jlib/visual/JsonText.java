package com.j0ach1mmall3.jlib.visual;

import com.j0ach1mmall3.jlib.methods.Parsing;
import com.j0ach1mmall3.jlib.integration.Placeholders;
import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

/**
 * Created by j0ach1mmall3 on 4:46 19/08/2015 using IntelliJ IDEA.
 */
public class JsonText {
    private Player player;
    private String json;

    public JsonText(Player player, String json) {
        this.player = player;
        this.json = json;
    }

    public String getJson() {
        return this.json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void send() {
        this.json = Placeholders.parse(this.json, this.player);
        if(this.json.startsWith("[text]")) {
            this.json = this.json.replace("[text]", "");
            this.player.sendMessage(this.json);
            return;
        }
        try {
            Constructor packetConstructor = ReflectionAPI.getNmsClass("PacketPlayOutChat").getConstructor(ReflectionAPI.getNmsClass("IChatBaseComponent"), byte.class);
            Object baseComponent = this.getSerializerClass().getMethod("a", String.class).invoke(null, this.json);
            Object packet = packetConstructor.newInstance(baseComponent, (byte) 0);
            ReflectionAPI.sendPacket(this.player, packet);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private Class<?> getSerializerClass() {
        if(this.verBiggerThan(1, 8) && this.verBiggerThan(2, 3)) {
            return ReflectionAPI.getNmsClass("IChatBaseComponent$ChatSerializer");
        } else {
            return ReflectionAPI.getNmsClass("ChatSerializer");
        }
    }

    private boolean verBiggerThan(int depth, int version) {
        return Parsing.parseString(Bukkit.getBukkitVersion().split("\\-")[0].split("\\.")[depth]) >= version;
    }
}
