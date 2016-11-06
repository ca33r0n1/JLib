package com.j0ach1mmall3.jlib.gui;

import com.j0ach1mmall3.jlib.inventory.JLibItem;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 18/09/2016
 */
public final class GuiPage {
    private final Map<Integer, JLibItem> items = new HashMap<>();
    private String name;
    private int rows;

    /**
     * Constructs a new GuiPage
     *
     * @param name The name of the GuiPage
     * @param rows The amount of rows of the GuiPage
     */
    public GuiPage(String name, int rows) {
        this.name = name;
        this.rows = rows;
    }

    /**
     * Returns the registered items
     *
     * @return The registered items
     */
    public Map<Integer, JLibItem> getItems() {
        return this.items;
    }

    /**
     * Returns the name of the GuiPage
     *
     * @return The name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the GuiPage
     *
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the amount of rows of the GuiPage
     *
     * @return The amount of rows
     */
    public int getRows() {
        return this.rows;
    }

    /**
     * Sets the amount of rows of the GuiPage
     *
     * @param rows The amount of rows
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Adds a JLibItem to this GuiPage
     *
     * @param jLibItem The JLibItem
     */
    public void setItem(JLibItem jLibItem) {
        this.addItem(jLibItem);
    }

    /**
     * Adds a JLibItem to this GuiPage
     *
     * @param position The position of the JLibItem
     * @param jLibItem The JLibItem
     */
    public void setItem(int position, JLibItem jLibItem) {
        this.addItem(position, jLibItem);
    }

    /**
     * Adds a JLibItem to this GuiPage
     *
     * @param jLibItem The JLibItem
     */
    public void addItem(JLibItem jLibItem) {
        this.addItem(jLibItem.getGuiPosition(), jLibItem);
    }

    /**
     * Adds a JLibItem to this GuiPage
     *
     * @param position The position of the JLibItem
     * @param jLibItem The JLibItem
     */
    public void addItem(int position, JLibItem jLibItem) {
        this.items.put(position, jLibItem);
    }

    /**
     * Returns the Inventory described by this GuiPage
     *
     * @return The Inventory
     */
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(null, this.rows * 9, this.name);
        for (Map.Entry<Integer, JLibItem> entry : this.items.entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue().getItemStack());
        }
        return inventory;
    }
}
