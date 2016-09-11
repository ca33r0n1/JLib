package com.j0ach1mmall3.jlib.gui;

import com.j0ach1mmall3.jlib.gui.events.GuiOpenEvent;
import com.j0ach1mmall3.jlib.inventory.JLibItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 2/09/2016
 */
public class Gui {
    private final Map<Integer, JLibItem> items = new HashMap<>();
    private String name;
    private int rows;

    /**
     * Constructs a new Gui
     * @param name The name of the Gui
     * @param rows The amount of rows of the Gui
     */
    public Gui(String name, int rows) {
        this.name = name;
        this.rows = rows;
    }

    /**
     * Returns the registered items
     * @return The registered items
     */
    public final Map<Integer, JLibItem> getItems() {
        return this.items;
    }

    /**
     * Returns the name of the Gui
     * @return The name
     */
    public final String getName() {
        return this.name;
    }

    /**
     * Sets the name of the Gui
     * @param name The name
     */
    public final void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the amount of rows of the Gui
     * @return The amount of rows
     */
    public final int getRows() {
        return this.rows;
    }

    /**
     * Sets the amount of rows of the Gui
     * @param rows The amount of rows
     */
    public final void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Adds a JLibItem to this GUI
     * @param jLibItem The JLibItem
     */
    public final void setItem(JLibItem jLibItem) {
        this.addItem(jLibItem);
    }

    /**
     * Adds a JLibItem to this GUI
     * @param position The position of the JLibItem
     * @param jLibItem The JLibItem
     */
    public final void setItem(int position, JLibItem jLibItem) {
        this.addItem(position, jLibItem);
    }

    /**
     * Adds a JLibItem to this GUI
     * @param jLibItem The JLibItem
     */
    public final void addItem(JLibItem jLibItem) {
        this.addItem(jLibItem.getGuiPosition(), jLibItem);
    }

    /**
     * Adds a JLibItem to this GUI
     * @param position The position of the JLibItem
     * @param jLibItem The JLibItem
     */
    public void addItem(int position, JLibItem jLibItem) {
        this.items.put(position, jLibItem);
    }

    /**
     * Opens the Gui for a player
     * @param player The player
     */
    public final void open(Player player) {
        GuiOpenEvent guiOpenEvent = new GuiOpenEvent(player, this);
        Bukkit.getPluginManager().callEvent(guiOpenEvent);
        if(!guiOpenEvent.isCancelled()) player.openInventory(Bukkit.createInventory(null, this.rows * 9, this.name));
    }
}
