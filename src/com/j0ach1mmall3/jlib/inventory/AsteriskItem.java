package com.j0ach1mmall3.jlib.inventory;

import com.j0ach1mmall3.jlib.methods.Parsing;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

/**
 * Created by j0ach1mmall3 on 17:13 9/10/2015 using IntelliJ IDEA.
 */
public class AsteriskItem {
    private final MaterialData data;
    private final boolean all;

    @SuppressWarnings("deprecation")
    public AsteriskItem(int id, byte data, boolean all) {
        this.data = new MaterialData(id, data);
        this.all = all;
    }

    public AsteriskItem(MaterialData data) {
        this.data = data;
        this.all = false;
    }

    @SuppressWarnings("deprecation")
    public AsteriskItem(String item) {
        if(!item.contains(":")) {
            this.data = new MaterialData(Parsing.parseInt(item), (byte) 0);
            this.all = false;
            return;
        }
        if(item.startsWith(":") || item.endsWith(":")) {
            this.data = new MaterialData(Parsing.parseInt(item.replace(":", "")), (byte) 0);
            this.all = false;
            return;
        }
        String[] splitted = item.split(":");
        if(splitted[1].equals("*")) {
            this.data = new MaterialData(Parsing.parseInt(splitted[0]), (byte) 0);
            this.all = true;
            return;
        }
        this.data = new MaterialData(Parsing.parseInt(splitted[0]), Parsing.parseByte(splitted[1]));
        this.all = false;
    }

    @SuppressWarnings("deprecation")
    public ItemStack getItem() {
        return new ItemStack(this.data.getItemType(), 1, this.data.getData());
    }

    @SuppressWarnings("deprecation")
    @Deprecated
    public int getId() {
        return this.data.getItemTypeId();
    }

    @SuppressWarnings("deprecation")
    @Deprecated
    public byte getData() {
        return this.data.getData();
    }

    public MaterialData getMaterialData() {
        return this.data;
    }

    public boolean isAll() {
        return this.all;
    }

    public boolean isItem(ItemStack is) {
        return isItem(is.getData());
    }

    @SuppressWarnings("deprecation")
    public boolean isItem(Block b) {
        return isItem(b.getTypeId(), b.getData());
    }

    @SuppressWarnings("deprecation")
    public boolean isItem(MaterialData data) {
        return isItem(data.getItemTypeId(), data.getData());
    }

    @SuppressWarnings("deprecation")
    public boolean isItem(int id, byte data) {
        return id == this.data.getItemTypeId() && (this.all || data == this.data.getData());
    }
}
