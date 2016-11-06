package com.j0ach1mmall3.jlib.gui.events;

import com.j0ach1mmall3.jlib.gui.Gui;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 2/09/2016
 */
public final class GuiCloseEvent<G extends Gui> extends GuiEvent<G> {
    private static final HandlerList HANDLERS = new HandlerList();

    /**
     * Constructs a new GuiCloseEvent
     *
     * @param who     The player associated with this event
     * @param gui     The Gui associated with this event
     * @param guiPage The GuiPage associated with this event
     */
    public GuiCloseEvent(Player who, G gui, int guiPage) {
        super(who, gui, guiPage);
    }


    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    /**
     * Returns the HandlerList (Bukkit method)
     *
     * @return The HandlerList
     */
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
