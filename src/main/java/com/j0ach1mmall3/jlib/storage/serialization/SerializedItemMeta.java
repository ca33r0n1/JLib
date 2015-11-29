package com.j0ach1mmall3.jlib.storage.serialization;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/11/2015
 */
public final class SerializedItemMeta implements JLibSerializable {
    private final ItemMeta itemMeta;
    private final String string;

    /**
     * Constructs a new SerializedItemMeta
     * @param itemMeta The ItemMeta
     * @see ItemMeta
     */
    public SerializedItemMeta(ItemMeta itemMeta) {
        this.itemMeta = itemMeta;
        this.string = itemMeta==null?"":String.valueOf(itemMeta.getDisplayName()) + "||" + new SerializedList(itemMeta.getLore()).getString() + "||" + new SerializedEnchantments(itemMeta.getEnchants()).getString() + "||" + new SerializedItemFlags(itemMeta.getItemFlags()).getString();
    }

    /**
     * Constructs a new SerializedItemMeta
     * @param string The String
     */
    public SerializedItemMeta(String string) {
        String[] splitted = string.split("\\|\\|");
        this.itemMeta = new ItemStack(Material.AIR).getItemMeta();
        this.itemMeta.setDisplayName(splitted[0].equals("null") ? null : splitted[0]);
        this.itemMeta.setLore(new SerializedList(splitted[1]).getCollection());
        Map<Enchantment, Integer> enchantments = new SerializedEnchantments(splitted[2]).getEnchantments();
        for(Enchantment enchantment : enchantments.keySet()) {
            this.itemMeta.addEnchant(enchantment, enchantments.get(enchantment), true);
        }
        this.itemMeta.addItemFlags(new SerializedItemFlags(splitted[3]).getItemFlags().toArray(new ItemFlag[new SerializedItemFlags(splitted[3]).getItemFlags().size()]));
        this.string = string;
    }

    /**
     * Returns the ItemMeta
     * @return The ItemMeta
     * @see ItemMeta
     */
    public ItemMeta getItemMeta() {
        return this.itemMeta;
    }

    /**
     * @see JLibSerializable#getString()
     */
    public String getString() {
        return this.string;
    }
}
