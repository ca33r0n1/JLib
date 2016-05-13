package com.j0ach1mmall3.jlib.inventory;

import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 13/05/2016
 */
public final class SpawnEgg {
    private static final Class<?> CRAFT_ITEM_STACK_CLASS = ReflectionAPI.getObcClass("inventory.CraftItemStack");
    private static final Class<?> ITEM_STACK_CLASS = ReflectionAPI.getNmsClass("ItemStack");
    private static final Class<?> NBT_TAG_COMPOUND_CLASS = ReflectionAPI.getNmsClass("NBTTagCompound");
    private static final Class<?> NBT_BASE_CLASS = ReflectionAPI.getNmsClass("NBTBase");

    private EntityType type;

    /**
     * Constructs a new 1.9 Spawn egg
     * @param type The EntityType
     */
    public SpawnEgg(EntityType type) {
        if (type.isAlive()) this.type = type;
        else this.type = EntityType.PIG;
    }

    /**
     * Returns the EntityType this egg will spawn
     * @return The EntityType
     */
    public EntityType getSpawnedType() {
        return this.type;
    }

    /**
     * Set the EntityType this egg will spawn
     * @param type The EntityType
     */
    public void setSpawnedType(EntityType type) {
        if (type.isAlive()) this.type = type;
    }


    /**
     * Modifies an ItemStack
     * @param itemStack the ItemStack to modify
     * @return The ItemStack
     */
    @SuppressWarnings("deprecation")
    public ItemStack toItemStack(ItemStack itemStack) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object stack = CRAFT_ITEM_STACK_CLASS.getMethod("asNMSCopy", ItemStack.class).invoke(null, itemStack);
        Object tagCompound = ITEM_STACK_CLASS.getMethod("getTag").invoke(stack);
        if(tagCompound == null) tagCompound = NBT_TAG_COMPOUND_CLASS.newInstance();

        Object id = NBT_TAG_COMPOUND_CLASS.newInstance();
        NBT_TAG_COMPOUND_CLASS.getMethod("setString", String.class, String.class).invoke(id, "id", this.type.getName());
        NBT_TAG_COMPOUND_CLASS.getMethod("set", String.class, NBT_BASE_CLASS).invoke(tagCompound, "EntityTag", id);

        ITEM_STACK_CLASS.getMethod("setTag", NBT_TAG_COMPOUND_CLASS).invoke(stack, tagCompound);
        return (ItemStack) CRAFT_ITEM_STACK_CLASS.getMethod("asBukkitCopy", ITEM_STACK_CLASS).invoke(null, stack);
    }
}
