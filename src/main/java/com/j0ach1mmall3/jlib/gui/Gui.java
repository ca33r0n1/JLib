package com.j0ach1mmall3.jlib.gui;

import com.j0ach1mmall3.jlib.gui.events.GuiOpenEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 2/09/2016
 */
public class Gui {
    protected final List<GuiPage> guiPages = new ArrayList<>();

    /**
     * Constructs a new Gui
     *
     * @param guiPages The GuiPages in this Gui
     */
    public Gui(GuiPage... guiPages) {
        this.guiPages.addAll(Arrays.asList(guiPages));
    }

    /**
     * Returns the GuiPages in this Gui
     *
     * @return The GuiPages
     */
    public final List<GuiPage> getGuiPages() {
        return this.guiPages;
    }

    /**
     * Adds GuiPages to this Gui
     *
     * @param guiPages The GuiPages
     */
    public final void addGuiPages(GuiPage... guiPages) {
        this.guiPages.addAll(Arrays.asList(guiPages));
    }

    /**
     * Removes GuiPages from this Gui
     *
     * @param guiPages The indexes of the GuiPages
     */
    public final void removeGuiPages(int... guiPages) {
        for (int guiPage : guiPages) {
            this.guiPages.remove(guiPage);
        }
    }

    /**
     * Sets a GuiPage in this Gui
     *
     * @param index   The index
     * @param guiPage The GuiPage
     */
    public final void setGuiPage(int index, GuiPage guiPage) {
        this.guiPages.set(index, guiPage);
    }

    /**
     * Returns a GuiPage in this Gui
     *
     * @param index The index
     * @return The GuiPage
     */
    public final GuiPage getGuiPage(int index) {
        return this.guiPages.get(index);
    }

    /**
     * Opens the Gui for a player
     *
     * @param player The player
     */
    public final void open(Player player) {
        this.open(player, 0);
    }

    /**
     * Opens the Gui for a player
     *
     * @param player The player
     * @param page   The page
     */
    public void open(Player player, int page) {
        GuiOpenEvent event = new GuiOpenEvent(player, this, page);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) player.openInventory(this.guiPages.get(page).getInventory());
    }

    /**
     * Returns the index of the GuiPage before the GuiPage
     *
     * @param guiPage The GuiPage
     * @return The index
     */
    public int getPreviousPage(int guiPage) {
        return (guiPage == 0 ? this.guiPages.size() : guiPage) - 1;
    }

    /**
     * Returns the index of the GuiPage after the GuiPage
     *
     * @param guiPage The GuiPage
     * @return The index
     */
    public int getNextPage(int guiPage) {
        return guiPage == this.guiPages.size() - 1 ? 0 : guiPage + 1;
    }
}
