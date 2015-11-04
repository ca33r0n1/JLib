package com.j0ach1mmall3.jlib.inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by j0ach1mmall3 on 17:37 14/10/2015 using IntelliJ IDEA.
 */
public final class PlayerInventory {
    private Player p;

    public PlayerInventory(Player p) {
        this.p = p;
    }

    public boolean inInventory(ItemStack is) {
        for(ItemStack item : p.getInventory().getContents()) {
            if(item != null && item.isSimilar(is)) return true;
        }
        return false;
    }

    public boolean inHotbar(ItemStack is) {
        for(int i = 0; i < 9; i++) {
            ItemStack item = p.getInventory().getItem(i);
            if(item != null && item.isSimilar(is)) return true;
        }
        return false;
    }

    public boolean inHand(ItemStack is) {
        return p.getItemInHand().isSimilar(is);
    }

    public boolean inArmor(ItemStack is) {
        return inHelmet(is) || inChestplate(is) || inLeggings(is) || inBoots(is);
    }

    public boolean inHelmet(ItemStack is) {
        return p.getInventory().getHelmet() != null && p.getInventory().getHelmet().isSimilar(is);
    }

    public boolean inChestplate(ItemStack is) {
        return p.getInventory().getChestplate() != null && p.getInventory().getChestplate().isSimilar(is);
    }

    public boolean inLeggings(ItemStack is) {
        return p.getInventory().getLeggings() != null && p.getInventory().getLeggings().isSimilar(is);
    }

    public boolean inBoots(ItemStack is) {
        return p.getInventory().getBoots() != null && p.getInventory().getBoots().isSimilar(is);
    }

    public boolean inInventory(AsteriskItem is) {
        for(ItemStack item : p.getInventory().getContents()) {
            if(item != null && is.isItem(item)) return true;
        }
        return false;
    }

    public boolean inHotbar(AsteriskItem is) {
        for(int i = 0; i < 9; i++) {
            ItemStack item = p.getInventory().getItem(i);
            if(item != null && is.isItem(item)) return true;
        }
        return false;
    }

    public boolean inHand(AsteriskItem is) {
        return is.isItem(p.getItemInHand());
    }

    public boolean inArmor(AsteriskItem is) {
        return inHelmet(is) || inChestplate(is) || inLeggings(is) || inBoots(is);
    }

    public boolean inHelmet(AsteriskItem is) {
        return p.getInventory().getHelmet() != null && is.isItem(p.getInventory().getHelmet());
    }

    public boolean inChestplate(AsteriskItem is) {
        return p.getInventory().getChestplate() != null && is.isItem(p.getInventory().getChestplate());
    }

    public boolean inLeggings(AsteriskItem is) {
        return p.getInventory().getLeggings() != null && is.isItem(p.getInventory().getLeggings());
    }

    public boolean inBoots(AsteriskItem is) {
        return p.getInventory().getBoots() != null && is.isItem(p.getInventory().getBoots());
    }
}
