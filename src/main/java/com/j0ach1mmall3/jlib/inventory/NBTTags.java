package com.j0ach1mmall3.jlib.inventory;

import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import org.bukkit.inventory.ItemStack;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 14/05/2016
 */
public final class NBTTags {
    private static final Class<?> CRAFT_ITEM_STACK_CLASS = ReflectionAPI.getObcClass("inventory.CraftItemStack");
    private static final Class<?> ITEM_STACK_CLASS = ReflectionAPI.getNmsClass("ItemStack");
    private static final Class<?> NBT_TAG_COMPOUND_CLASS = ReflectionAPI.getNmsClass("NBTTagCompound");
    private static final Class<?> NBT_BASE_CLASS = ReflectionAPI.getNmsClass("NBTBase");

    /**
     * Let nobody instantiate this class
     */
    private NBTTags() {
    }

    /**
     * Sets a custom NBT Tag to an ItemStack
     * @param itemStack The ItemStack
     * @param tagName The name of the NBT Tag
     * @param value The value to set
     * @return The modified ItemStack
     * @throws Exception When an exception occurs
     */
    public static ItemStack setNbtTag(ItemStack itemStack, String tagName, String value) throws Exception {
        Object stack = CRAFT_ITEM_STACK_CLASS.getMethod("asNMSCopy", ItemStack.class).invoke(null, itemStack);
        Object tagCompound = ITEM_STACK_CLASS.getMethod("getTag").invoke(stack);
        if(tagCompound == null) tagCompound = NBT_TAG_COMPOUND_CLASS.newInstance();

        NBT_TAG_COMPOUND_CLASS.getMethod("setString", String.class, String.class).invoke(tagCompound, tagName, value);

        ITEM_STACK_CLASS.getMethod("setTag", NBT_TAG_COMPOUND_CLASS).invoke(stack, tagCompound);
        return (ItemStack) CRAFT_ITEM_STACK_CLASS.getMethod("asBukkitCopy", ITEM_STACK_CLASS).invoke(null, stack);
    }

    /**
     * Returns a custom NBT Tag from an ItemStack
     * @param itemStack The ItemStack
     * @param tagName The name of the NBT Tag
     * @return The value
     * @throws Exception When an exception occurs
     */
    public static String getNbtTag(ItemStack itemStack, String tagName) throws Exception {
        Object stack = CRAFT_ITEM_STACK_CLASS.getMethod("asNMSCopy", ItemStack.class).invoke(null, itemStack);
        Object tagCompound = ITEM_STACK_CLASS.getMethod("getTag").invoke(stack);
        if(tagCompound == null) return null;

        return (String) NBT_TAG_COMPOUND_CLASS.getMethod("getString", String.class).invoke(tagCompound, tagName);
    }
}
