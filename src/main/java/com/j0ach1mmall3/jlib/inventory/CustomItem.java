package com.j0ach1mmall3.jlib.inventory;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

/**
 * @author j0ach1mmall3
 * @since 18/08/2015 (business.j0ach1mmall3@gmail.com)
 */
public final class CustomItem extends ItemStack {

    /**
     * Constructs a new CustomItem (Which can also be used as ItemStack)
     * @param material The Material of the ItemStack
     * @param amount The amount of the ItemStack
     * @param durability The durability of the ItemStack
     * @param name The Displayname of the ItemStack
     * @param lore The Lore of the ItemStack
     * @param enchantments Enchantments (Map) that should be added to this ItemStack
     */
    public CustomItem(Material material, int amount, int durability, String name, List<String> lore, Map<Enchantment, Integer> enchantments){
        super(new ItemStack(material, amount));
        this.setName(name);
        this.setLore(lore);
        this.setDurability((short) durability);
        for(Enchantment enchantment : enchantments.keySet()) {
            this.addUnsafeEnchantment(enchantment, enchantments.get(enchantment));
        }
    }

    /**
     * Constructs a new CustomItem (Which can also be used as ItemStack)
     * @param material The Material of the ItemStack
     * @param amount The amount of the ItemStack
     * @param durability The durability of the ItemStack
     * @param name The Displayname of the ItemStack
     * @param lore The Lore of the ItemStack
     * @param enchantments Enchantments that should be added to this ItemStack
     */
    public CustomItem(Material material, int amount, int durability, String name, String[] lore, Map<Enchantment, Integer> enchantments){
        this(material, amount, durability, name, Arrays.asList(lore), enchantments);
    }

    /**
     * Constructs a new CustomItem (Which can also be used as ItemStack)
     * @param material The Material of the ItemStack
     * @param amount The amount of the ItemStack
     * @param durability The durability of the ItemStack
     * @param name The Displayname of the ItemStack
     * @param lore The Lore of the ItemStack (| sign denotes the end of a line)
     * @param enchantments Enchantments that should be added to this ItemStack
     */
    public CustomItem(Material material, int amount, int durability, String name, String lore, Map<Enchantment, Integer> enchantments){
        this(material, amount, durability, name, lore.split("\\|"), enchantments);
    }

    /**
     * Constructs a new CustomItem (Which can also be used as ItemStack)
     * @param material The Material of the ItemStack
     * @param amount The amount of the ItemStack
     * @param durability The durability of the ItemStack
     * @param name The Displayname of the ItemStack
     * @param lore The Lore of the ItemStack
     * @param enchantments Enchantments that should be added to this ItemStack (Every Enchantment level will be 1)
     */
    public CustomItem(Material material, int amount, int durability, String name, List<String> lore, List<Enchantment> enchantments){
        super(new ItemStack(material, amount));
        this.setName(name);
        this.setLore(lore);
        this.setDurability((short) durability);
        for(Enchantment enchantment : enchantments) {
            this.addUnsafeEnchantment(enchantment, 1);
        }
    }

    /**
     * Constructs a new CustomItem (Which can also be used as ItemStack)
     * @param material The Material of the ItemStack
     * @param amount The amount of the ItemStack
     * @param durability The durability of the ItemStack
     * @param name The Displayname of the ItemStack
     * @param lore The Lore of the ItemStack
     * @param enchantments Enchantments that should be added to this ItemStack (Every Enchantment level will be 1)
     */
    public CustomItem(Material material, int amount, int durability, String name, String[] lore, List<Enchantment> enchantments){
        this(material, amount, durability, name, Arrays.asList(lore), enchantments);
    }

    /**
     * Constructs a new CustomItem (Which can also be used as ItemStack)
     * @param material The Material of the ItemStack
     * @param amount The amount of the ItemStack
     * @param durability The durability of the ItemStack
     * @param name The Displayname of the ItemStack
     * @param lore The Lore of the ItemStack (| sign denotes the end of a line)
     * @param enchantments Enchantments that should be added to this ItemStack (Every Enchantment level will be 1)
     */
    public CustomItem(Material material, int amount, int durability, String name, String lore, List<Enchantment> enchantments){
        this(material, amount, durability, name, lore.split("\\|"), enchantments);
    }

    /**
     * Constructs a new CustomItem (Which can also be used as ItemStack)
     * @param material The Material of the ItemStack
     * @param amount The amount of the ItemStack
     * @param durability The durability of the ItemStack
     * @param name The Displayname of the ItemStack
     * @param lore The Lore of the ItemStack
     * @param enchantments Enchantments that should be added to this ItemStack (Every Enchantment level will be 1)
     */
    public CustomItem(Material material, int amount, int durability, String name, List<String> lore, Enchantment[] enchantments){
        this(material, amount, durability, name, lore, Arrays.asList(enchantments));
    }

    /**
     * Constructs a new CustomItem (Which can also be used as ItemStack)
     * @param material The Material of the ItemStack
     * @param amount The amount of the ItemStack
     * @param durability The durability of the ItemStack
     * @param name The Displayname of the ItemStack
     * @param lore The Lore of the ItemStack
     * @param enchantments Enchantments that should be added to this ItemStack (Every Enchantment level will be 1)
     */
    public CustomItem(Material material, int amount, int durability, String name, String[] lore, Enchantment[] enchantments){
        this(material, amount, durability, name, Arrays.asList(lore), Arrays.asList(enchantments));
    }

    /**
     * Constructs a new CustomItem (Which can also be used as ItemStack)
     * @param material The Material of the ItemStack
     * @param amount The amount of the ItemStack
     * @param durability The durability of the ItemStack
     * @param name The Displayname of the ItemStack
     * @param lore The Lore of the ItemStack (| sign denotes the end of a line)
     * @param enchantments Enchantments that should be added to this ItemStack (Every Enchantment level will be 1)
     */
    public CustomItem(Material material, int amount, int durability, String name, String lore, Enchantment[] enchantments){
        this(material, amount, durability, name, lore.split("\\|"), Arrays.asList(enchantments));
    }

    /**
     * Constructs a new CustomItem (Which can also be used as ItemStack)
     * @param material The Material of the ItemStack
     * @param amount The amount of the ItemStack
     * @param durability The durability of the ItemStack
     * @param name The Displayname of the ItemStack
     * @param lore The Lore of the ItemStack
     * @param enchantment Enchantment that should be added to this ItemStack (Will be level 1)
     */
    public CustomItem(Material material, int amount, int durability, String name, List<String> lore, Enchantment enchantment){
        this(material, amount, durability, name, lore, Collections.singletonList(enchantment));
    }

    /**
     * Constructs a new CustomItem (Which can also be used as ItemStack)
     * @param material The Material of the ItemStack
     * @param amount The amount of the ItemStack
     * @param durability The durability of the ItemStack
     * @param name The Displayname of the ItemStack
     * @param lore The Lore of the ItemStack
     * @param enchantment Enchantment that should be added to this ItemStack (Will be level 1)
     */
    public CustomItem(Material material, int amount, int durability, String name, String[] lore, Enchantment enchantment){
        this(material, amount, durability, name, Arrays.asList(lore), enchantment);
    }

    /**
     * Constructs a new CustomItem (Which can also be used as ItemStack)
     * @param material The Material of the ItemStack
     * @param amount The amount of the ItemStack
     * @param durability The durability of the ItemStack
     * @param name The Displayname of the ItemStack
     * @param lore The Lore of the ItemStack (| sign denotes the end of a line)
     * @param enchantment Enchantment that should be added to this ItemStack (Will be level 1)
     */
    public CustomItem(Material material, int amount, int durability, String name, String lore, Enchantment enchantment){
        this(material, amount, durability, name, lore.split("\\|"), enchantment);
    }

    /**
     * Constructs a new CustomItem (Which can also be used as ItemStack)
     * @param material The Material of the ItemStack
     * @param amount The amount of the ItemStack
     * @param durability The durability of the ItemStack
     * @param name The Displayname of the ItemStack
     * @param lore The Lore of the ItemStack
     */
    public CustomItem(Material material, int amount, int durability, String name, List<String> lore){
        this(material, amount, durability, name, lore, new ArrayList<Enchantment>());
    }

    /**
     * Constructs a new CustomItem (Which can also be used as ItemStack)
     * @param material The Material of the ItemStack
     * @param amount The amount of the ItemStack
     * @param durability The durability of the ItemStack
     * @param name The Displayname of the ItemStack
     * @param lore The Lore of the ItemStack
     */
    public CustomItem(Material material, int amount, int durability, String name, String[] lore){
        this(material, amount, durability, name, Arrays.asList(lore));
    }

    /**
     * Constructs a new CustomItem (Which can also be used as ItemStack)
     * @param material The Material of the ItemStack
     * @param amount The amount of the ItemStack
     * @param durability The durability of the ItemStack
     * @param name The Displayname of the ItemStack
     * @param lore The Lore of the ItemStack (| sign denotes the end of a line)
     */
    public CustomItem(Material material, int amount, int durability, String name, String lore){
        this(material, amount, durability, name, lore.split("\\|"));
    }

    /**
     * Constructs a new CustomItem (Which can also be used as ItemStack)
     * @param material The Material of the ItemStack
     * @param amount The amount of the ItemStack
     * @param durability The durability of the ItemStack
     * @param name The Displayname of the ItemStack
     */
    public CustomItem(Material material, int amount, int durability, String name){
        this(material, amount, durability, name, "");
    }

    /**
     * Constructs a new CustomItem (Which can also be used as ItemStack)
     * @param material The Material of the ItemStack
     * @param amount The amount of the ItemStack
     * @param durability The durability of the ItemStack
     */
    public CustomItem(Material material, int amount, int durability){
        super(material, amount, (short) durability);
    }

    /**
     * Constructs a new CustomItem (Which can also be used as ItemStack)
     * @param material The Material of the ItemStack
     * @param amount The amount of the ItemStack
     */
    public CustomItem(Material material, int amount) {
        super(new ItemStack(material, amount));
    }

    /**
     * Constructs a new CustomItem (Which can also be used as ItemStack)
     * @param material The Material of the ItemStack
     */
    public CustomItem(Material material) {
        super(material);
    }

    /**
     * ItemStack wrapper
     * @param itemStack The ItemStack that will be converted to a CustomItem
     */
    public CustomItem(ItemStack itemStack) {
        super(itemStack);
    }

    /**
     * Sets the Displayname of this ItemStack
     * @param name The new Displayname
     */
    public void setName(String name){
        ItemMeta im = this.getItemMeta();
        im.setDisplayName(name);
        this.setItemMeta(im);
    }

    /**
     * Sets the Lore of this ItemStack
     * @param lore The new Lore
     */
    public void setLore(List<String> lore){
        if(lore==null || lore.isEmpty()) return;
        ItemMeta im = this.getItemMeta();
        List<String> lines = new ArrayList<>();
        for(String line : lore){
            if(!line.equalsIgnoreCase("")) lines.add(line);
        }
        im.setLore(lines);
        this.setItemMeta(im);
    }

    /**
     * Sets the Lore of this ItemStack
     * @param lore The new Lore
     */
    public void setLore(String[] lore){
        this.setLore(Arrays.asList(lore));
    }

    /**
     * Sets the Lore of this ItemStack
     * @param lore The new Lore (| sign denotes the end of a line)
     */
    public void setLore(String lore){
        this.setLore(lore.split("\\|"));
    }

    /**
     * Sets the Enchantments of this ItemStack
     * @param enchantments The Enchantments
     */
    public void setEnchantments(Map<Enchantment, Integer> enchantments) {
        for(Enchantment enchantment : this.getEnchantments().keySet()) {
            this.removeEnchantment(enchantment);
        }
        for(Enchantment enchantment : enchantments.keySet()) {
            this.addUnsafeEnchantment(enchantment, enchantments.get(enchantment));
        }
    }

    /**
     * Sets the Enchantments of this ItemStack
     * @param enchantments The Enchantments (Every Enchantment level will be 1)
     */
    public void setEnchantments(List<Enchantment> enchantments) {
        for(Enchantment enchantment : this.getEnchantments().keySet()) {
            this.removeEnchantment(enchantment);
        }
        for(Enchantment enchantment : enchantments) {
            this.addUnsafeEnchantment(enchantment, 1);
        }
    }

    /**
     * Sets the Enchantments of this ItemStack
     * @param enchantments The Enchantments (Every Enchantment level will be 1)
     */
    public void setEnchantments(Enchantment[] enchantments) {
        this.setEnchantments(Arrays.asList(enchantments));
    }

    /**
     * Sets the Enchantments of this ItemStack
     * @param enchantment The Enchantment (Will be level 1)
     */
    public void setEnchantment(Enchantment enchantment) {
        this.setEnchantments(Collections.singletonList(enchantment));
    }
}
