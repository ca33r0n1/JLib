package com.j0ach1mmall3.jlib.inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by j0ach1mmall3 on 17:37 14/10/2015 using IntelliJ IDEA.
 */
public class PlayerInventory {
    public static boolean inInventory(Player p, ItemStack is) {
        for(ItemStack item : p.getInventory().getContents()) {
            if(item != null && item.isSimilar(is)) return true;
        }
        return false;
    }

    public static boolean inHotbar(Player p, ItemStack is) {
        for(int i = 0; i < 9; i++) {
            ItemStack item = p.getInventory().getItem(i);
            if(item != null && item.isSimilar(is)) return true;
        }
        return false;
    }

    public static boolean inHand(Player p, ItemStack is) {
        return p.getItemInHand().isSimilar(is);
    }

    public static boolean inArmor(Player p, ItemStack is) {
        return inHelmet(p, is) || inChestplate(p, is) || inLeggings(p, is) || inBoots(p, is);
    }

    public static boolean inHelmet(Player p, ItemStack is) {
        return p.getInventory().getHelmet() != null && p.getInventory().getHelmet().isSimilar(is);
    }

    public static boolean inChestplate(Player p, ItemStack is) {
        return p.getInventory().getChestplate() != null && p.getInventory().getChestplate().isSimilar(is);
    }

    public static boolean inLeggings(Player p, ItemStack is) {
        return p.getInventory().getLeggings() != null && p.getInventory().getLeggings().isSimilar(is);
    }

    public static boolean inBoots(Player p, ItemStack is) {
        return p.getInventory().getBoots() != null && p.getInventory().getBoots().isSimilar(is);
    }

    public static boolean inInventory(Player p, AsteriskItem is) {
        for(ItemStack item : p.getInventory().getContents()) {
            if(item != null && is.isItem(item)) return true;
        }
        return false;
    }

    public static boolean inHotbar(Player p, AsteriskItem is) {
        for(int i = 0; i < 9; i++) {
            ItemStack item = p.getInventory().getItem(i);
            if(item != null && is.isItem(item)) return true;
        }
        return false;
    }

    public static boolean inHand(Player p, AsteriskItem is) {
        return is.isItem(p.getItemInHand());
    }

    public static boolean inArmor(Player p, AsteriskItem is) {
        return inHelmet(p, is) || inChestplate(p, is) || inLeggings(p, is) || inBoots(p, is);
    }

    public static boolean inHelmet(Player p, AsteriskItem is) {
        return p.getInventory().getHelmet() != null && is.isItem(p.getInventory().getHelmet());
    }

    public static boolean inChestplate(Player p, AsteriskItem is) {
        return p.getInventory().getChestplate() != null && is.isItem(p.getInventory().getChestplate());
    }

    public static boolean inLeggings(Player p, AsteriskItem is) {
        return p.getInventory().getLeggings() != null && is.isItem(p.getInventory().getLeggings());
    }

    public static boolean inBoots(Player p, AsteriskItem is) {
        return p.getInventory().getBoots() != null && is.isItem(p.getInventory().getBoots());
    }
}
