package com.j0ach1mmall3.jlib.inventory;

import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import com.j0ach1mmall3.jlib.nms.nbt.NBTTag;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 13/05/2016
 * @deprecated {@link JLibItem#setNbtTag(NBTTag)}
 */
@Deprecated
@SuppressWarnings("deprecation")
public final class SpawnEgg {
    private EntityType type;

    /**
     * Constructs a new 1.9 Spawn egg
     *
     * @param type The EntityType
     */
    public SpawnEgg(EntityType type) {
        this.type = type.isAlive() ? type : EntityType.PIG;
    }

    /**
     * Returns the EntityType this egg will spawn
     *
     * @return The EntityType
     */
    public EntityType getSpawnedType() {
        return this.type;
    }

    /**
     * Set the EntityType this egg will spawn
     *
     * @param type The EntityType
     */
    public void setSpawnedType(EntityType type) {
        if (type.isAlive()) this.type = type;
    }


    /**
     * Modifies an ItemStack
     *
     * @param itemStack the ItemStack to modify
     * @return The ItemStack
     * @throws Exception When an exception occurs
     */
    public ItemStack toItemStack(ItemStack itemStack) throws Exception {
        Object stack = ReflectionAPI.getObcClass("inventory.CraftItemStack").getMethod("asNMSCopy", ItemStack.class).invoke(null, itemStack);
        Object tagCompound = ReflectionAPI.getNmsClass("ItemStack").getMethod("getTag").invoke(stack);
        if (tagCompound == null) tagCompound = ReflectionAPI.getNmsClass("NBTTagCompound").newInstance();

        Object id = ReflectionAPI.getNmsClass("NBTTagCompound").newInstance();
        ReflectionAPI.getNmsClass("NBTTagCompound").getMethod("setString", String.class, String.class).invoke(id, "id", this.type.getName());
        ReflectionAPI.getNmsClass("NBTTagCompound").getMethod("set", String.class, ReflectionAPI.getNmsClass("NBTBase")).invoke(tagCompound, "EntityTag", id);

        ReflectionAPI.getNmsClass("ItemStack").getMethod("setTag", ReflectionAPI.getNmsClass("NBTTagCompound")).invoke(stack, tagCompound);
        return (ItemStack) ReflectionAPI.getObcClass("inventory.CraftItemStack").getMethod("asBukkitCopy", ReflectionAPI.getNmsClass("ItemStack")).invoke(null, stack);
    }
}