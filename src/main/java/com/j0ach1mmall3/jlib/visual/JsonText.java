package com.j0ach1mmall3.jlib.visual;

import com.j0ach1mmall3.jlib.player.JLibPlayer;
import org.bukkit.entity.Player;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/15
 * @deprecated {@link JLibPlayer#sendJsonText(String)}
 */
@Deprecated
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
        new JLibPlayer(this.player).sendJsonText(this.json);
    }
}
