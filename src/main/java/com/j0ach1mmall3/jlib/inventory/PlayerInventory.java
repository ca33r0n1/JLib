package com.j0ach1mmall3.jlib.inventory;

import com.j0ach1mmall3.jlib.methods.General;
import com.j0ach1mmall3.jlib.player.JLibPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 14/10/15
 * @deprecated {@link JLibPlayer}
 */
@Deprecated
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
     * Returns whether the Inventory contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return Wether the Inventory contains the provided ItemStack
     */
    public boolean inInventory(ItemStack itemStack) {
        for(ItemStack item : this.p.getInventory().getContents()) {
            if(item != null && General.areSimilar(item, itemStack)) return true;
        }
        return false;
    }

    /**
     * Returns whether the Hotbar contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return Wether the Hotbar contains the provided ItemStack
     */
    public boolean inHotbar(ItemStack itemStack) {
        for(int i = 0; i < 9; i++) {
            ItemStack item = this.p.getInventory().getItem(i);
            if(item != null && General.areSimilar(item, itemStack)) return true;
        }
        return false;
    }

    /**
     * Returns whether the Hand contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return Wether the Hand contains the provided ItemStack
     */
    @SuppressWarnings("deprecation")
    public boolean inHand(ItemStack itemStack) {
        return General.areSimilar(this.p.getItemInHand(), itemStack);
    }

    /**
     * Returns whether the Armor contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return Wether the Armor contains the provided ItemStack
     */
    public boolean inArmor(ItemStack itemStack) {
        return this.inHelmet(itemStack) || this.inChestplate(itemStack) || this.inLeggings(itemStack) || this.inBoots(itemStack);
    }

    /**
     * Returns whether the Helmet contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return Wether the Helmet contains the provided ItemStack
     */
    public boolean inHelmet(ItemStack itemStack) {
        return this.p.getInventory().getHelmet() != null && General.areSimilar(this.p.getInventory().getHelmet(), itemStack);
    }

    /**
     * Returns whether the Chestplate contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return Wether the Chestplate contains the provided ItemStack
     */
    public boolean inChestplate(ItemStack itemStack) {
        return this.p.getInventory().getChestplate() != null && General.areSimilar(this.p.getInventory().getChestplate(), itemStack);
    }

    /**
     * Returns whether the Leggings contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return Wether the Leggings contains the provided ItemStack
     */
    public boolean inLeggings(ItemStack itemStack) {
        return this.p.getInventory().getLeggings() != null && General.areSimilar(this.p.getInventory().getLeggings(), itemStack);
    }

    /**
     * Returns whether the Boots contains the provided ItemStack
     * @param itemStack The ItemStack
     * @return Wether the Boots contains the provided ItemStack
     */
    public boolean inBoots(ItemStack itemStack) {
        return this.p.getInventory().getBoots() != null && General.areSimilar(this.p.getInventory().getBoots(), itemStack);
    }

    /**
     * Returns whether the Inventory contains the provided AsteriskItem
     * @param asteriskItem The AsteriskItem
     * @return Wether the Inventory contains the provided AsteriskItem
     */
    public boolean inInventory(AsteriskItem asteriskItem) {
        for(ItemStack item : this.p.getInventory().getContents()) {
            if(item != null && asteriskItem.isItem(item)) return true;
        }
        return false;
    }

    /**
     * Returns whether the Hotbar contains the provided AsteriskItem
     * @param asteriskItem The AsteriskItem
     * @return Wether the Hotbar contains the provided AsteriskItem
     */
    public boolean inHotbar(AsteriskItem asteriskItem) {
        for(int i = 0; i < 9; i++) {
            ItemStack item = this.p.getInventory().getItem(i);
            if(item != null && asteriskItem.isItem(item)) return true;
        }
        return false;
    }

    /**
     * Returns whether the Hand contains the provided AsteriskItem
     * @param asteriskItem The AsteriskItem
     * @return Wether the Hand contains the provided AsteriskItem
     */
    @SuppressWarnings("deprecation")
    public boolean inHand(AsteriskItem asteriskItem) {
        return asteriskItem.isItem(this.p.getItemInHand());
    }

    /**
     * Returns whether the Armor contains the provided AsteriskItem
     * @param asteriskItem The AsteriskItem
     * @return Wether the Armor contains the provided AsteriskItem
     */
    public boolean inArmor(AsteriskItem asteriskItem) {
        return this.inHelmet(asteriskItem) || this.inChestplate(asteriskItem) || this.inLeggings(asteriskItem) || this.inBoots(asteriskItem);
    }

    /**
     * Returns whether the Helmet contains the provided AsteriskItem
     * @param asteriskItem The AsteriskItem
     * @return Wether the Helmet contains the provided AsteriskItem
     */
    public boolean inHelmet(AsteriskItem asteriskItem) {
        return this.p.getInventory().getHelmet() != null && asteriskItem.isItem(this.p.getInventory().getHelmet());
    }

    /**
     * Returns whether the Chestplate contains the provided AsteriskItem
     * @param asteriskItem The AsteriskItem
     * @return Wether the Chestplate contains the provided AsteriskItem
     */
    public boolean inChestplate(AsteriskItem asteriskItem) {
        return this.p.getInventory().getChestplate() != null && asteriskItem.isItem(this.p.getInventory().getChestplate());
    }

    /**
     * Returns whether the Leggings contains the provided AsteriskItem
     * @param asteriskItem The AsteriskItem
     * @return Wether the Leggings contains the provided AsteriskItem
     */
    public boolean inLeggings(AsteriskItem asteriskItem) {
        return this.p.getInventory().getLeggings() != null && asteriskItem.isItem(this.p.getInventory().getLeggings());
    }

    /**
     * Returns whether the Boots contains the provided AsteriskItem
     * @param asteriskItem The AsteriskItem
     * @return Wether the Boots contains the provided AsteriskItem
     */
    public boolean inBoots(AsteriskItem asteriskItem) {
        return this.p.getInventory().getBoots() != null && asteriskItem.isItem(this.p.getInventory().getBoots());
    }
}
