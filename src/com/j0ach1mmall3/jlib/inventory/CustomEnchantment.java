package com.j0ach1mmall3.jlib.inventory;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

/**
 * Created by j0ach1mmall3 on 9:06 18/08/2015 using IntelliJ IDEA.
 */
public final class CustomEnchantment {
    private static int count = 255;
    private static final HashMap<String, Integer> IDMAP = new HashMap<>();
    private String name;
    private int maxLevel;
    private int startLevel;
    private EnchantmentTarget enchantmentTarget;
    private List<ItemStack> enchantables;

    public CustomEnchantment(String name, List<ItemStack> enchantables, EnchantmentTarget enchantmentTarget, int startLevel, int maxLevel) {
        this.name = name;
        this.enchantables = enchantables;
        this.enchantmentTarget = enchantmentTarget;
        this.startLevel = startLevel;
        this.maxLevel = maxLevel;
        try	{
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null , true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemStack> getEnchantables() {
        return this.enchantables;
    }

    public void setEnchantables(List<ItemStack> enchantables) {
        this.enchantables = enchantables;
    }

    public EnchantmentTarget getEnchantmentTarget() {
        return this.enchantmentTarget;
    }

    public void setEnchantmentTarget(EnchantmentTarget enchantmentTarget) {
        this.enchantmentTarget = enchantmentTarget;
    }

    public int getMaxLevel() {
        return this.maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getStartLevel() {
        return this.startLevel;
    }

    public void setStartLevel(int startLevel) {
        this.startLevel = startLevel;
    }

    public void register() {
        try{
            Enchantment.registerEnchantment(this.getEnchantment());
        }catch (Exception e){
            // Enchantment is already registered
        }
    }

    public Enchantment getEnchantment() {
        int id = count;
        if(IDMAP.containsKey(this.name)){
            id = IDMAP.get(this.name);
        } else {
            IDMAP.put(this.name, id);
            count--;
        }
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
