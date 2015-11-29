package com.j0ach1mmall3.jlib.storage.serialization;

import org.bukkit.inventory.ItemFlag;

import java.util.HashSet;
import java.util.Set;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/11/2015
 */
public final class SerializedItemFlags implements JLibSerializable {
    private final Set<ItemFlag> itemFlags;
    private String string;

    /**
     * Constructs a new SerializedItemFlags
     * @param itemFlags The ItemFlags Set
     * @see ItemFlag
     */
    public SerializedItemFlags(Set<ItemFlag> itemFlags) {
        this.itemFlags = itemFlags;
        this.string = "";
        for(ItemFlag itemFlag : itemFlags) {
            this.string = this.string + itemFlag.name() + "|";
        }
    }

    /**
     * Constructs a new SerializedItemFlags
     * @param string The String
     */
    public SerializedItemFlags(String string) {
        this.itemFlags = new HashSet<>();
        for(String splittedString : string.split("\\|")) {
            this.itemFlags.add(ItemFlag.valueOf(splittedString));
        }
        this.string = string;
    }

    /**
     * Returns the ItemFlags Set
     * @return The ItemFlags Set
     * @see ItemFlag
     */
    public Set<ItemFlag> getItemFlags() {
        return this.itemFlags;
    }

    /**
     * @see JLibSerializable#getString()
     */
    public String getString() {
        return this.string;
    }
}
