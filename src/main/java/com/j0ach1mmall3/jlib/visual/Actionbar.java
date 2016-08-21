package com.j0ach1mmall3.jlib.visual;

import com.j0ach1mmall3.jlib.player.JLibPlayer;
import org.bukkit.entity.Player;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/15
 * @deprecated {@link JLibPlayer#sendActionBar(String)}
 */
@Deprecated
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
        new JLibPlayer(this.player).sendActionBar(this.message);
    }
}
