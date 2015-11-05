package com.j0ach1mmall3.jlib.inventory;

import org.bukkit.inventory.ItemStack;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 9/10/2015
 */
public final class GuiItem {
    private final ItemStack item;
    private final int position;

    /**
     * Constructs a new GuiItem
     * @param item The ItemStack of this GuiItem
     * @param position The Position of this GuiItem
     */
    public GuiItem(ItemStack item, int position) {
        this.item = item;
        this.position = position;
    }

    /**
     * Returns the ItemStack of this GuiItem
     * @return The ItemStack
     */
    public ItemStack getItem() {
        return this.item;
    }

    /**
     * Returns the Position of this GuiItem
     * @return The Position
     */
    public int getPosition() {
        return this.position;
    }
}
