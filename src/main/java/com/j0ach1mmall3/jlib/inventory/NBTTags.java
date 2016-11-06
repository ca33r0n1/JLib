package com.j0ach1mmall3.jlib.inventory;

import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import org.bukkit.inventory.ItemStack;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 14/05/2016
 * @deprecated {@link JLibItem#getNBTTag()}
 */
@Deprecated
public final class NBTTags {

    /**
     * Let nobody instantiate this class
     */
    private NBTTags() {
    }

    /**
     * Sets a custom NBT Tag to an ItemStack
     *
     * @param itemStack The ItemStack
     * @param tagName   The name of the NBT Tag
     * @param value     The value to set
     * @return The modified ItemStack
     * @throws Exception When an exception occurs
     */
    public static ItemStack setNbtTag(ItemStack itemStack, String tagName, String value) throws Exception {
        Object stack = ReflectionAPI.getObcClass("inventory.CraftItemStack").getMethod("asNMSCopy", ItemStack.class).invoke(null, itemStack);
        Object tagCompound = ReflectionAPI.getNmsClass("ItemStack").getMethod("getTag").invoke(stack);
        if (tagCompound == null) tagCompound = ReflectionAPI.getNmsClass("NBTTagCompound").newInstance();

        ReflectionAPI.getNmsClass("NBTTagCompound").getMethod("setString", String.class, String.class).invoke(tagCompound, tagName, value);

        ReflectionAPI.getNmsClass("ItemStack").getMethod("setTag", ReflectionAPI.getNmsClass("NBTTagCompound")).invoke(stack, tagCompound);
        return (ItemStack) ReflectionAPI.getObcClass("inventory.CraftItemStack").getMethod("asBukkitCopy", ReflectionAPI.getNmsClass("ItemStack")).invoke(null, stack);
    }

    /**
     * Returns a custom NBT Tag from an ItemStack
     *
     * @param itemStack The ItemStack
     * @param tagName   The name of the NBT Tag
     * @return The value
     * @throws Exception When an exception occurs
     */
    public static String getNbtTag(ItemStack itemStack, String tagName) throws Exception {
        Object stack = ReflectionAPI.getObcClass("inventory.CraftItemStack").getMethod("asNMSCopy", ItemStack.class).invoke(null, itemStack);
        Object tagCompound = ReflectionAPI.getNmsClass("ItemStack").getMethod("getTag").invoke(stack);
        if (tagCompound == null) return null;

        return (String) ReflectionAPI.getNmsClass("NBTTagCompound").getMethod("getString", String.class).invoke(tagCompound, tagName);
    }
}
