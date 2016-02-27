package com.j0ach1mmall3.jlib.minigameapi.classes;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 17/02/16
 */
public final class Class {
    private final String identifier;
    private final String ability;
    private final ItemStack[] startingItems;
    private final Collection<PotionEffect> potionEffects;

    /**
     * Constructs a new Class
     * @param identifier The identifier of the Class
     * @param ability The ability of the Class
     * @param startingItems The starting items of the Class
     * @param potionEffects The potion effects of the Class
     */
    public Class(String identifier, String ability, ItemStack[] startingItems, Collection<PotionEffect> potionEffects) {
        this.identifier = identifier;
        this.ability = ability;
        this.startingItems = startingItems;
        this.potionEffects = potionEffects;
    }

    /**
     * Returns the identifier of the Class
     * @return The identifier
     */
    public String getIdentifier() {
        return this.identifier;
    }

    /**
     * Returns the ability of the Class
     * @return The ability
     */
    public String getAbility() {
        return this.ability;
    }

    /**
     * Returns the starting items of the Class
     * @return The starting items
     */
    public ItemStack[] getStartingItems() {
        return this.startingItems;
    }

    /**
     * Returns the potion effects of the Class
     * @return The potion effects
     */
    public Collection<PotionEffect> getPotionEffects() {
        return this.potionEffects;
    }

    /**
     * Gives this class to a player
     * @param player The player
     */
    public void give(Player player) {
        player.getInventory().addItem(this.startingItems);
        player.addPotionEffects(this.potionEffects);
    }

    /**
     * Returns whether this Class equals another Class
     * @param clazz The other Class
     * @return Whether this Class equals another Class
     */
    public boolean equals(Class clazz) {
        return this.identifier.equals(clazz.identifier) && this.ability.equals(clazz.ability) && Arrays.deepEquals(this.startingItems, clazz.startingItems) && this.potionEffects.equals(clazz.potionEffects);
    }
}
