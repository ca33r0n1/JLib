package com.j0ach1mmall3.jlib.visual;

import com.j0ach1mmall3.jlib.player.JLibPlayer;
import org.bukkit.entity.Player;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/15
 * @deprecated {@link JLibPlayer#sendTitle(int, int, int, String)}
 */
@Deprecated
public final class Title {
    private Player player;
    private String message;
    private int fadeIn;
    private int stay;
    private int fadeOut;

    /**
     * Constructs a new Title
     * @param player The player of this Title
     * @param message The message of this Title
     * @param fadeIn The fade-in time in ticks of this Title
     * @param stay The stay time in ticks of this Title
     * @param fadeOut The fade-out time in ticks of this Title
     */
    public Title(Player player, String message, int fadeIn, int stay, int fadeOut) {
        this.player = player;
        this.message = message;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    /**
     * Returns the player of this Title
     * @return The player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Sets the player of this Title
     * @param player The new player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Returns the message of this Title
     * @return The message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets the message of this Title
     * @param message The new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the fade-in time of this Title
     * @return The fade-in time
     */
    public int getFadeIn() {
        return this.fadeIn;
    }

    /**
     * Sets the fade-in time of this Title
     * @param fadeIn The new fade-in time
     */
    public void setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
    }

    /**
     * Returns the stay time of this Title
     * @return The stay time
     */
    public int getStay() {
        return this.stay;
    }

    /**
     * Sets the stay time of this Title
     * @param stay The new stay time
     */
    public void setStay(int stay) {
        this.stay = stay;
    }

    /**
     * Returns the fade-out time of this Title
     * @return The fade-out time
     */
    public int getFadeOut() {
        return this.fadeOut;
    }

    /**
     * Sets the fade-out time of this Title
     * @param fadeOut The new stay time
     */
    public void setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
    }

    /**
     * Sends the Title
     */
    public void send() {
        new JLibPlayer(this.player).sendTitle(this.fadeIn, this.fadeOut, this.stay, this.message);
    }
}
