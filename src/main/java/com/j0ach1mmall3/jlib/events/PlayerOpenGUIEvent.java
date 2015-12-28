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
public class PlayerOpenGUIEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private GUI gui;
    private boolean cancelled;

    public PlayerOpenGUIEvent(Player player, GUI gui) {
        super(player);
        this.gui = gui;
    }

    public GUI getGui() {
        return this.gui;
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    @Override
    public final boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public final void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public final HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}

