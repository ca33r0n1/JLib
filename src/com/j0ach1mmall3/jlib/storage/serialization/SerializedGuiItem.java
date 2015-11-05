package com.j0ach1mmall3.jlib.storage.serialization;

import com.j0ach1mmall3.jlib.inventory.GuiItem;
import com.j0ach1mmall3.jlib.methods.Parsing;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/11/2015
 */
public final class SerializedGuiItem implements JLibSerializable {
    private GuiItem item;
    private String string;

    /**
     * Constructs a new SerializedGuiItem
     * @param item The GuiItem
     * @see GuiItem
     */
    public SerializedGuiItem(GuiItem item) {
        this.item = item;
        this.string = new SerializedItemStack(item.getItem()).getString() + "||||" + item.getPosition();
    }

    /**
     * Constructs a new SerializedGuiItem
     * @param string The String
     * @see GuiItem
     */
    public SerializedGuiItem(String string) {
        String[] splitted = string.split("\\|\\|\\|\\|");
        this.item = new GuiItem(new SerializedItemStack(splitted[0]).getItemStack(), Parsing.parseInt(splitted[1]));
        this.string = string;
    }

    /**
     * Returns the GuiItem
     * @return The GuiItem
     * @see GuiItem
     */
    public GuiItem getItem() {
        return item;
    }

    /**
     * @see JLibSerializable#getString()
     */
    public String getString() {
        return string;
    }
}
