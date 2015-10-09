package com.j0ach1mmall3.jlib.inventory;

import org.bukkit.inventory.ItemStack;

/**
 * Created by j0ach1mmall3 on 17:14 9/10/2015 using IntelliJ IDEA.
 */
public class GuiItem {
    private ItemStack is;
    private int position;

    public GuiItem(ItemStack is, int position) {
        this.is = is;
        this.position = position;
    }

    public ItemStack getIs() {
        return is;
    }

    public int getPosition() {
        return position;
    }
}
