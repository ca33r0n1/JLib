package com.j0ach1mmall3.jlib.gui.events;

import com.j0ach1mmall3.jlib.gui.Gui;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.player.PlayerEvent;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 2/09/2016
 */
public abstract class GuiEvent<G extends Gui> extends PlayerEvent implements Cancellable {
    private final G gui;
    private final int guiPage;
    private boolean cancelled;

    /**
     * Constructs a new GuiEvent
     *
     * @param who     The player associated with this event
     * @param gui     The Gui associated with this event
     * @param guiPage The GuiPage associated with this event
     */
    protected GuiEvent(Player who, G gui, int guiPage) {
        super(who);
        this.gui = gui;
        this.guiPage = guiPage;
    }

    /**
     * Returns the Gui associated with this event
     *
     * @return The Gui
     */
    public final G getGui() {
        return this.gui;
    }

    /**
     * Returns the GuiPage associated with this event
     *
     * @return The GuiPage
     */
    public final int getGuiPage() {
        return this.guiPage;
    }

    @Override
    public final boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public final void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
