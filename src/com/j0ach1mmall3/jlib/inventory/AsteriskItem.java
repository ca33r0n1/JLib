package com.j0ach1mmall3.jlib.inventory;

import com.j0ach1mmall3.jlib.methods.Parsing;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

/**
 * Created by j0ach1mmall3 on 17:13 9/10/2015 using IntelliJ IDEA.
 */
public class AsteriskItem {
    private final int id;
    private final int data;
    private boolean all;

    public AsteriskItem(int id, int data, boolean all) {
        this.id = id;
        this.data = data;
        this.all = all;
    }

    @SuppressWarnings("deprecation")
    public AsteriskItem(MaterialData data) {
        this.id = data.getItemTypeId();
        this.data = data.getData();
    }

    public AsteriskItem(String item) {
        if(!item.contains(":")) {
            this.id = Parsing.parseInt(item);
            this.data = 0;
            this.all = false;
            return;
        }
        if(item.startsWith(":") || item.endsWith(":")) {
            this.id = Parsing.parseInt(item.replace(":", ""));
            this.data = 0;
            this.all = false;
            return;
        }
        String[] splitted = item.split(":");
        if(splitted[1].equals("*")) {
            this.id = Parsing.parseInt(splitted[0]);
            this.data = 0;
            this.all = true;
            return;
        }
        this.id = Parsing.parseInt(splitted[0]);
        this.data = Parsing.parseInt(splitted[1]);
        this.all = false;
    }

    @SuppressWarnings("deprecation")
    public ItemStack getItem() {
        return new ItemStack(this.id, 1, (short) this.data);
    }

    public int getId() {
        return this.id;
    }

    public int getData() {
        return this.data;
    }

    public boolean isAll() {
        return this.all;
    }

    @SuppressWarnings("deprecation")
    public boolean isItem(ItemStack is) {
        return is.getTypeId() == this.id && (this.all || is.getDurability() == this.data);
    }

    @SuppressWarnings("deprecation")
    public boolean isItem(Block b) {
        return b.getTypeId() == this.id && (this.all || b.getData() == this.data);
    }

    @SuppressWarnings("deprecation")
    public boolean isItem(MaterialData data) {
        return data.getItemTypeId() == this.id && (this.all || data.getData() == this.data);
    }

    public boolean isItem(int id, int data) {
        return id == this.id && (this.all || data == this.data);
    }
}
