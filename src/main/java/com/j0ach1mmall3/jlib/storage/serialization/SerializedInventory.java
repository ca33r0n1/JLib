package com.j0ach1mmall3.jlib.storage.serialization;

import com.j0ach1mmall3.jlib.inventory.GuiItem;
import com.j0ach1mmall3.jlib.methods.Parsing;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/11/2015
 */
public final class SerializedInventory implements JLibSerializable {
    private final Inventory inventory;
    private String string;

    /**
     * Constructs a new SerializedInventory
     * @param inventory The Inventory
     * @see Inventory
     */
    public SerializedInventory(Inventory inventory) {
        this.inventory = inventory;
        this.string = inventory.getSize() + "|||||" + inventory.getTitle() + "|||||";
        for(int i=0;i<inventory.getContents().length;i++) {
            this.string = this.string + new SerializedGuiItem(new GuiItem(inventory.getContents()[i], i)).getString() + "|||||";
        }
    }

    /**
     * Constructs a new SerializedInventory
     * @param string The String
     */
    public SerializedInventory(String string) {
        String[] splitted = string.split("\\|\\|\\|\\|\\|");
        this.inventory = Bukkit.createInventory(null, Parsing.parseInt(splitted[0]), splitted[1]);
        for(int i=2;i<splitted.length;i++) {
            SerializedGuiItem item = new SerializedGuiItem(splitted[i]);
            this.inventory.setItem(item.getItem().getPosition(), item.getItem().getItem());
        }
        this.string = string;
    }

    /**
     * Returns the Inventory
     * @return The Inventory
     * @see Inventory
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * @see JLibSerializable#getString()
     */
    public String getString() {
        return this.string;
    }
}
