package com.j0ach1mmall3.jlib.inventory;

import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 18/08/15
 */
public final class CustomEnchantment {
    private static final HashMap<String, Integer> IDMAP = new HashMap<>();

    static {
        try {
            ReflectionAPI.setField(Enchantment.class, null, "acceptingNew", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final String name;
    private final int maxLevel;
    private final int startLevel;
    private final EnchantmentTarget enchantmentTarget;
    private final List<ItemStack> enchantables;

    /**
     * Constructs a new CustomEnchantment
     * CustomEnchantments can be used to create non-bukkit Enchantments.
     * A perfect example of this is a 'Glow' Enchantment
     *
     * @param name              The identifier of this Enchantment (Should be unique)
     * @param enchantables      The ItemStacks that can be enchanted by this Enchantment
     * @param enchantmentTarget The target of this Enchantment (If you don't know what this means, just use EnchantmentTarget.ALL)
     * @param startLevel        The starting level of this Enchantment
     * @param maxLevel          The max level of this Enchantment
     */
    public CustomEnchantment(String name, List<ItemStack> enchantables, EnchantmentTarget enchantmentTarget, int startLevel, int maxLevel) {
        this.name = name;
        this.enchantables = enchantables;
        this.enchantmentTarget = enchantmentTarget;
        this.startLevel = startLevel;
        this.maxLevel = maxLevel;
    }

    /**
     * Returns the name of this Enchantment
     *
     * @return The name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the Enchantables list of this Enchantment
     *
     * @return The Enchantables list
     */
    public List<ItemStack> getEnchantables() {
        return this.enchantables;
    }

    /**
     * Returns the EnchantmentTarget of this Enchantment
     *
     * @return The EnchantmentTarget
     */
    public EnchantmentTarget getEnchantmentTarget() {
        return this.enchantmentTarget;
    }

    /**
     * Returns the max level of this Enchantment
     *
     * @return The max level
     */
    public int getMaxLevel() {
        return this.maxLevel;
    }

    /**
     * Returns the starting level of this Enchantment
     *
     * @return The starting level
     */
    public int getStartLevel() {
        return this.startLevel;
    }

    /**
     * Registers the Enchantment in Bukkit's system
     */
    public void register() {
        try {
            Enchantment.registerEnchantment(this.getEnchantment());
        } catch (Exception e) {
            // Enchantment is already registered
        }
    }

    /**
     * Returns the Bukkit Enchantment represented by this CustomEnchantment
     *
     * @return The Bukkit Enchantment
     */
    public Enchantment getEnchantment() {
        int id = 255 - IDMAP.size();
        if (IDMAP.containsKey(this.name)) id = IDMAP.get(this.name);
        else IDMAP.put(this.name, id);
        return new Enchantment(id) {
            @Override
            public String getName() {
                return CustomEnchantment.this.name;
            }

            @Override
            public int getMaxLevel() {
                return CustomEnchantment.this.maxLevel;
            }

            @Override
            public int getStartLevel() {
                return CustomEnchantment.this.startLevel;
            }

            @Override
            public EnchantmentTarget getItemTarget() {
                return CustomEnchantment.this.enchantmentTarget;
            }

            @Override
            public boolean conflictsWith(Enchantment enchantment) {
                return false;
            }

            @Override
            public boolean canEnchantItem(ItemStack itemStack) {
                return CustomEnchantment.this.enchantables == null || CustomEnchantment.this.enchantables.contains(itemStack);
            }
        };
    }
}
