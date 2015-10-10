package com.j0ach1mmall3.jlib.inventory;

import com.j0ach1mmall3.jlib.methods.Parsing;
import org.bukkit.block.Block;
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

    public AsteriskItem(String item) {
        if(!item.contains(":")) {
            this.id = Parsing.parseString(item);
            this.data = 0;
            this.all = false;
            return;
        }
        if(item.startsWith(":") || item.endsWith(":")) {
            this.id = Parsing.parseString(item.replace(":", ""));
            this.data = 0;
            this.all = false;
            return;
        }
        String[] splitted = item.split(":");
        if(splitted[1].equals("*")) {
            this.id = Parsing.parseString(splitted[0]);
            this.data = 0;
            this.all = true;
            return;
        }
        this.id = Parsing.parseString(splitted[0]);
        this.data = Parsing.parseString(splitted[1]);
        this.all = false;
    }

    @SuppressWarnings("deprecation")
    public ItemStack getItem() {
        return new ItemStack(id, 1, (short) data);
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

    @SuppressWarnings("deprecation")
    public boolean isItem(Block b) {
        return b.getTypeId() == id && (all || b.getData() == data);
    }
}
