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
public class CustomEnchantment {
    private static int count = 255;
    private static final HashMap<String, Integer> idMap = new HashMap<>();
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemStack> getEnchantables() {
        return enchantables;
    }

    public void setEnchantables(List<ItemStack> enchantables) {
        this.enchantables = enchantables;
    }

    public EnchantmentTarget getEnchantmentTarget() {
        return enchantmentTarget;
    }

    public void setEnchantmentTarget(EnchantmentTarget enchantmentTarget) {
        this.enchantmentTarget = enchantmentTarget;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getStartLevel() {
        return startLevel;
    }

    public void setStartLevel(int startLevel) {
        this.startLevel = startLevel;
    }

    public void register() {
        try{
            Enchantment.registerEnchantment(getEnchantment());
        }catch (Exception e){
            // Enchantment is already registered
        }
    }

    public Enchantment getEnchantment() {
        int id = count;
        if(idMap.containsKey(name)){
            id = idMap.get(name);
        } else {
            idMap.put(name, id);
            count--;
        }
        return new Enchantment(id) {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public int getMaxLevel() {
                return maxLevel;
            }

            @Override
            public int getStartLevel() {
                return startLevel;
            }

            @Override
            public EnchantmentTarget getItemTarget() {
                return enchantmentTarget;
            }

            @Override
            public boolean conflictsWith(Enchantment enchantment) {
                return false;
            }

            @Override
            public boolean canEnchantItem(ItemStack itemStack) {
                return enchantables == null || enchantables.contains(itemStack);
            }
        };
    }
}
