package com.j0ach1mmall3.jlib.inventory;

import com.j0ach1mmall3.jlib.methods.Parsing;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 9/10/2015
 */
public final class AsteriskItem {
    private final MaterialData data;
    private final boolean all;

    /**
     * Constructs a new AsteriskItem
     * An AsteriskItem is an item notation with an item ID and potentially data or an Asterisk
     * Examples:
     * - 1 (Represents Stone)
     * - 35:14 (Represents Red Wool)
     * - 278:* (Represents all forms of a Diamond Pickaxe)
     * @param id The ID of the item
     * @param data The data of the item
     * @param all Equivalent of the Asterisk: Should all data values be accepted
     */
    @SuppressWarnings("deprecation")
    public AsteriskItem(int id, byte data, boolean all) {
        this(new MaterialData(id, data), all);
    }

    /**
     * Constructs a new AsteriskItem
     * @param data The MaterialData of the item
     * @param all Equivalent of the Asterisk: Should all data values be accepted
     */
    public AsteriskItem(MaterialData data, boolean all) {
        this.data = data;
        this.all = all;
    }

    /**
     * Constructs a new AsteriskItem
     * @param data The MaterialData of the item
     */
    public AsteriskItem(MaterialData data) {
        this.data = data;
        this.all = false;
    }

    /**
     * Constructs a new AsteriskItem
     * @param item The String notation of the Item
     * @see AsteriskItem#AsteriskItem(int, byte, boolean)
     */
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

    /**
     * Returns the ItemStack represented by this AsteriskItem
     * @return The ItemStack
     */
    @SuppressWarnings("deprecation")
    public ItemStack getItem() {
        return new ItemStack(this.data.getItemType(), 1, this.data.getData());
    }

    /**
     * Returns the Item ID of this AsteriskItem
     * @return The Item ID
     */
    @SuppressWarnings("deprecation")
    public int getId() {
        return this.data.getItemTypeId();
    }

    /**
     * Returns the Data value of this AsteriskItem
     * @return The Data value
     */
    @SuppressWarnings("deprecation")
    public byte getData() {
        return this.data.getData();
    }

    /**
     * Returns MaterialData of this AsteriskItem
     * @return The MaterialData
     * @see MaterialData
     */
    public MaterialData getMaterialData() {
        return this.data;
    }

    /**
     * Returns if this AsteriskItem represents all possible Data values
     * @return If this AsteriskItem represents all possible Data values
     */
    public boolean isAll() {
        return this.all;
    }

    /**
     * Returns if the supplied ItemStack matches this AsteriskItem
     * @param itemStack The ItemStack
     * @return If the supplied ItemStack matches this AsteriskItem
     */
    public boolean isItem(ItemStack itemStack) {
        return this.isItem(itemStack.getData());
    }

    /**
     * Returns if the supplied Block matches this AsteriskItem
     * @param block The Block
     * @return If the supplied Block matches this AsteriskItem
     */
    @SuppressWarnings("deprecation")
    public boolean isItem(Block block) {
        return this.isItem(block.getTypeId(), block.getData());
    }

    /**
     * Returns if the supplied MaterialData matches this AsteriskItem
     * @param data The MaterialData
     * @return If the supplied MaterialData matches this AsteriskItem
     */
    @SuppressWarnings("deprecation")
    public boolean isItem(MaterialData data) {
        return this.isItem(data.getItemTypeId(), data.getData());
    }

    /**
     * Returns if the supplied Item ID and Data value match this AsteriskItem
     * @param id The Item ID
     * @param data The Data value
     * @return If the supplied Item ID and Data value match this AsteriskItem
     */
    @SuppressWarnings("deprecation")
    public boolean isItem(int id, byte data) {
        return id == this.data.getItemTypeId() && (this.all || data == this.data.getData());
    }

    /**
     * Returns if the suplied AsteriskItem is equal to the current AsteriskItem
     * @param item The AsteriskItem to compare to
     * @return If they are equal
     */
    public boolean equals(AsteriskItem item) {
        return item.getMaterialData().equals(this.getMaterialData()) && item.isAll() == this.all;
    }
}
