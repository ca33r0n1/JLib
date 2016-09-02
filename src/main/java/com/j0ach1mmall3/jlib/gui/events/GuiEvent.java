package com.j0ach1mmall3.jlib.gui.events;

import com.j0ach1mmall3.jlib.gui.Gui;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.player.PlayerEvent;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 2/09/2016
 */
public abstract class GuiEvent extends PlayerEvent implements Cancellable {
    private final Gui gui;
    private boolean cancelled;

    /**
     * Constructs a new GuiEvent
     * @param who The player associated with this event
     * @param gui The Gui associated with this event
     */
    protected GuiEvent(Player who, Gui gui) {
        super(who);
        this.gui = gui;
    }

    /**
     * Returns the Gui associated with this event
     * @return The Gui
     */
    public Gui getGui() {
        return this.gui;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
