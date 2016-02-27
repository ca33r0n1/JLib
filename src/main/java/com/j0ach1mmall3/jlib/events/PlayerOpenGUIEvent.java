package com.j0ach1mmall3.jlib.events;

import com.j0ach1mmall3.jlib.inventory.GUI;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 28/12/15
 */
public final class PlayerOpenGUIEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private GUI gui;
    private boolean cancelled;

    /**
     * Constructs a new PlayerOpenGUIEvent
     * @param player The Player
     * @param gui The GUI
     */
    public PlayerOpenGUIEvent(Player player, GUI gui) {
        super(player);
        this.gui = gui;
    }

    /**
     * Returns the GUI
     * @return The GUI
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * Sets the GUI
     * @param gui The new GUI
     */
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    /**
     * Returns the HandlerList (Bukkit method)
     * @return The HandlerList
     */
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}

