package com.j0ach1mmall3.jlib.visual;

import com.j0ach1mmall3.jlib.player.JLibPlayer;
import org.bukkit.entity.Player;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/15
 * @deprecated {@link JLibPlayer#sendTab(String, String)}
 */
@Deprecated
public final class Tab {
    private Player player;
    private String header;
    private String footer;

    /**
     * Constructs a new Tab
     *
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
     *
     * @return The player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Sets the player of this Tab
     *
     * @param player The new player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Returns the Header of this Tab
     *
     * @return The Header
     */
    public String getHeader() {
        return this.header;
    }

    /**
     * Sets the Header of this Tab
     *
     * @param header The new Header
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * Returns the Footer of this Tab
     *
     * @return The Footer
     */
    public String getFooter() {
        return this.footer;
    }

    /**
     * Sets the Footer of this Tab
     *
     * @param footer The new Footer
     */
    public void setFooter(String footer) {
        this.footer = footer;
    }

    /**
     * Sends the Tab
     */
    public void send() {
        new JLibPlayer(this.player).sendTab(this.header, this.footer);
    }
}
