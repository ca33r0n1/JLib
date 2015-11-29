package com.j0ach1mmall3.jlib.inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 14/10/2015
 */
public final class PlayerInventory {
    private final Player p;

    /**
     * Constructs a new PlayerInventory
     * @param player The player this PlayerInventory is associated with
     */
    public PlayerInventory(Player player) {
        this.p = player;
    }

    /**
     * Returns the player this PlayerInventory is associated with
     * @return The player
     */
    public Player getPlayer() {
        return this.p;
    }

    /**
     * Returns if the Inventory contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return If the Inventory contains the provided ItemStack
     */
    public boolean inInventory(ItemStack itemStack) {
        for(ItemStack item : this.p.getInventory().getContents()) {
            if(item != null && item.isSimilar(itemStack)) return true;
        }
        return false;
    }

    /**
     * Returns if the Hotbar contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return If the Hotbar contains the provided ItemStack
     */
    public boolean inHotbar(ItemStack itemStack) {
        for(int i = 0; i < 9; i++) {
            ItemStack item = this.p.getInventory().getItem(i);
            if(item != null && item.isSimilar(itemStack)) return true;
        }
        return false;
    }

    /**
     * Returns if the Hand contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return If the Hand contains the provided ItemStack
     */
    public boolean inHand(ItemStack itemStack) {
        return this.p.getItemInHand().isSimilar(itemStack);
    }

    /**
     * Returns if the Armor contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return If the Armor contains the provided ItemStack
     */
    public boolean inArmor(ItemStack itemStack) {
        return this.inHelmet(itemStack) || this.inChestplate(itemStack) || this.inLeggings(itemStack) || this.inBoots(itemStack);
    }

    /**
     * Returns if the Helmet contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return If the Helmet contains the provided ItemStack
     */
    public boolean inHelmet(ItemStack itemStack) {
        return this.p.getInventory().getHelmet() != null && this.p.getInventory().getHelmet().isSimilar(itemStack);
    }

    /**
     * Returns if the Chestplate contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return If the Chestplate contains the provided ItemStack
     */
    public boolean inChestplate(ItemStack itemStack) {
        return this.p.getInventory().getChestplate() != null && this.p.getInventory().getChestplate().isSimilar(itemStack);
    }

    /**
     * Returns if the Leggings contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return If the Leggings contains the provided ItemStack
     */
    public boolean inLeggings(ItemStack itemStack) {
        return this.p.getInventory().getLeggings() != null && this.p.getInventory().getLeggings().isSimilar(itemStack);
    }

    /**
     * Returns if the Boots contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return If the Boots contains the provided ItemStack
     */
    public boolean inBoots(ItemStack itemStack) {
        return this.p.getInventory().getBoots() != null && this.p.getInventory().getBoots().isSimilar(itemStack);
    }

    /**
     * Returns if the Inventory contains the provided AsteriskItem
     * @param asteriskItem The AsteriskItem
     * @return If the Inventory contains the provided AsteriskItem
     * @see AsteriskItem
     */
    public boolean inInventory(AsteriskItem asteriskItem) {
        for(ItemStack item : this.p.getInventory().getContents()) {
            if(item != null && asteriskItem.isItem(item)) return true;
        }
        return false;
    }

    /**
     * Returns if the Hotbar contains the provided AsteriskItem
     * @param asteriskItem The AsteriskItem
     * @return If the Hotbar contains the provided AsteriskItem
     * @see AsteriskItem
     */
    public boolean inHotbar(AsteriskItem asteriskItem) {
        for(int i = 0; i < 9; i++) {
            ItemStack item = this.p.getInventory().getItem(i);
            if(item != null && asteriskItem.isItem(item)) return true;
        }
        return false;
    }

    /**
     * Returns if the Hand contains the provided AsteriskItem
     * @param asteriskItem The AsteriskItem
     * @return If the Hand contains the provided AsteriskItem
     * @see AsteriskItem
     */
    public boolean inHand(AsteriskItem asteriskItem) {
        return asteriskItem.isItem(this.p.getItemInHand());
    }

    /**
     * Returns if the Armor contains the provided AsteriskItem
     * @param asteriskItem The AsteriskItem
     * @return If the Armor contains the provided AsteriskItem
     * @see AsteriskItem
     */
    public boolean inArmor(AsteriskItem asteriskItem) {
        return this.inHelmet(asteriskItem) || this.inChestplate(asteriskItem) || this.inLeggings(asteriskItem) || this.inBoots(asteriskItem);
    }

    /**
     * Returns if the Helmet contains the provided AsteriskItem
     * @param asteriskItem The AsteriskItem
     * @return If the Helmet contains the provided AsteriskItem
     * @see AsteriskItem
     */
    public boolean inHelmet(AsteriskItem asteriskItem) {
        return this.p.getInventory().getHelmet() != null && asteriskItem.isItem(this.p.getInventory().getHelmet());
    }

    /**
     * Returns if the Chestplate contains the provided AsteriskItem
     * @param asteriskItem The AsteriskItem
     * @return If the Chestplate contains the provided AsteriskItem
     * @see AsteriskItem
     */
    public boolean inChestplate(AsteriskItem asteriskItem) {
        return this.p.getInventory().getChestplate() != null && asteriskItem.isItem(this.p.getInventory().getChestplate());
    }

    /**
     * Returns if the Leggings contains the provided AsteriskItem
     * @param asteriskItem The AsteriskItem
     * @return If the Leggings contains the provided AsteriskItem
     * @see AsteriskItem
     */
    public boolean inLeggings(AsteriskItem asteriskItem) {
        return this.p.getInventory().getLeggings() != null && asteriskItem.isItem(this.p.getInventory().getLeggings());
    }

    /**
     * Returns if the Boots contains the provided AsteriskItem
     * @param asteriskItem The AsteriskItem
     * @return If the Boots contains the provided AsteriskItem
     * @see AsteriskItem
     */
    public boolean inBoots(AsteriskItem asteriskItem) {
        return this.p.getInventory().getBoots() != null && asteriskItem.isItem(this.p.getInventory().getBoots());
    }
}
