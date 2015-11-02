package com.j0ach1mmall3.jlib.inventory;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by j0ach1mmall3 on 17:18 18/08/2015 using IntelliJ IDEA.
 */
public class CustomItem extends ItemStack {
    public CustomItem(Material material, int amount, int durability, String name, List<String> lore, Enchantment e){
        super(new ItemStack(material, amount));
        this.setName(name);
        this.setLore(lore);
        this.setDurability((short)durability);
        if(e != null) this.addEnchantment(e, 1);
    }

    public CustomItem(Material material, int amount, int durability, String name, String[] lore, Enchantment e){
        this(material, amount, durability, name, Arrays.asList(lore), e);
    }

    @SuppressWarnings("SameParameterValue")
    public CustomItem(Material material, int amount, int durability, String name, String lore, Enchantment e){
        this(material, amount, durability, name, lore.split("\\|"), e);
    }

    public CustomItem(Material material, int amount, int durability, String name, List<String> lore){
        this(material, amount, durability, name, lore, null);
    }

    public CustomItem(Material material, int amount, int durability, String name, String[] lore){
        this(material, amount, durability, name, lore, null);
    }

    public CustomItem(Material material, int amount, int durability, String name, String lore){
        this(material, amount, durability, name, lore, null);
    }

    public CustomItem(Material material, int amount, int durability, String name){
        this(material, amount, durability, name, "", null);
    }

    public CustomItem(Material material, int amount, int durability){
        this(material, amount, durability, "", "", null);
    }

    public CustomItem(Material material, int amount) {
        super(new ItemStack(material, amount));
    }

    public CustomItem(ItemStack is) {
        super(is);
    }

    public void setName(String name){
        ItemMeta im = this.getItemMeta();
        im.setDisplayName(name);
        this.setItemMeta(im);
    }

    public void setLore(List<String> lore){
        ItemMeta im = this.getItemMeta();
        List<String> lines = new ArrayList<>();
        for(String line : lore){
            if(!line.equalsIgnoreCase("")) lines.add(line);
        }
        im.setLore(lines);
        this.setItemMeta(im);
    }

    public void setLore(String[] lore){
        this.setLore(Arrays.asList(lore));
    }

    public void setLore(String lore){
        if(!lore.equalsIgnoreCase("")) this.setLore(lore.split("\\|"));
    }
}
