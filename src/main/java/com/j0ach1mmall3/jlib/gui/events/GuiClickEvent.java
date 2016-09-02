package com.j0ach1mmall3.jlib.gui.events;

import com.j0ach1mmall3.jlib.gui.Gui;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 2/09/2016
 */
public final class GuiClickEvent extends GuiEvent {
    private static final HandlerList HANDLERS = new HandlerList();

    private final InventoryClickEvent inventoryClickEvent;

    /**
     * Constructs a new GuiClickEvent
     * @param who The player associated with this event
     * @param gui The Gui associated with this event
     * @param inventoryClickEvent The InventoryClickEvent associated with this event
     */
    public GuiClickEvent(Player who, Gui gui, InventoryClickEvent inventoryClickEvent) {
        super(who, gui);
        this.inventoryClickEvent = inventoryClickEvent;
    }

    /**
     * Returns the InventoryClickEvent associated with this event
     * @return The InventoryClickEvent
     */
    public InventoryClickEvent getInventoryClickEvent() {
        return this.inventoryClickEvent;
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
