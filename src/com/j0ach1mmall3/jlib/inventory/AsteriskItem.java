package com.j0ach1mmall3.jlib.inventory;

import org.bukkit.inventory.ItemStack;

/**
 * Created by j0ach1mmall3 on 17:13 9/10/2015 using IntelliJ IDEA.
 */
public class AsteriskItem {
    private int id;
    private int data;
    private boolean all;

    public AsteriskItem(int id, int data, boolean all) {
        this.id = id;
        this.data = data;
        this.all = all;
    }

    public int getId() {
        return id;
    }

    public int getData() {
        return data;
    }

    public boolean isAll() {
        return all;
    }

    @SuppressWarnings("deprecation")
    public boolean isItem(ItemStack is) {
        return is.getTypeId() == id && (all || is.getDurability() == data);
    }
}
