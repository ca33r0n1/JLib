package com.j0ach1mmall3.jlib.inventory;

import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import com.j0ach1mmall3.jlib.nms.nbt.NBTTag;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 2/09/2016
 */
public final class JLibItem {
    private final ItemStack itemStack;
    private boolean asteriskItem;
    private int guiPosition;

    /**
     * Constructs a new JLibItem
     * @param itemStack The ItemStack
     */
    public JLibItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Constructs a new JLibItem
     * @param material The material
     */
    public JLibItem(Material material) {
        this(material, 1);
    }

    /**
     * Constructs a new JLibItem
     * @param material The material
     * @param amount The amount
     */
    public JLibItem(Material material, int amount) {
        this(material, amount, (short) 0);
    }

    /**
     * Constructs a new JLibItem
     * @param material The material
     * @param durability The durability value
     */
    public JLibItem(Material material, short durability) {
        this(material, 1, durability);
    }

    /**
     * Constructs a new JLibItem
     * @param material The material
     * @param amount The amount
     * @param durability The durability value
     */
    public JLibItem(Material material, int amount, short durability) {
        this(material, amount, durability, null, (String) null);
    }

    /**
     * Constructs a new JLibItem
     * @param material The material
     * @param amount The amount
     * @param durability The durability value
     * @param name The name
     * @param lore The lore
     */
    public JLibItem(Material material, int amount, short durability, String name, List<String> lore) {
        this(new ItemStack(material, amount, durability));
        this.setName(name);
        this.setLore(lore);
    }

    /**
     * Constructs a new JLibItem
     * @param material The material
     * @param amount The amount
     * @param durability The durability value
     * @param name The name
     * @param lore The lore
     */
    public JLibItem(Material material, int amount, short durability, String name, String[] lore) {
        this(new ItemStack(material, amount, durability));
        this.setName(name);
        this.setLore(lore);
    }

    /**
     * Constructs a new JLibItem
     * @param material The material
     * @param amount The amount
     * @param durability The durability value
     * @param name The name
     * @param lore The lore, lines separated with |
     */
    public JLibItem(Material material, int amount, short durability, String name, String lore) {
        this(new ItemStack(material, amount, durability));
        this.setName(name);
        this.setLore(lore);
    }

    /**
     * Returns the ItemStack
     * @return The ItemStack
     */
    public ItemStack getItemStack() {
        return this.itemStack;
    }

    /**
     * Returns whether this JLibItem is an AsteriskItem
     * @return Whether this JLibItem is an AsteriskItem
     */
    public boolean isAsteriskItem() {
        return this.asteriskItem;
    }

    /**
     * Sets whether this JLibItem is an AsteriskItem
     * @param asteriskItem Whether this JLibItem is an AsteriskItem
     */
    public void setAsteriskItem(boolean asteriskItem) {
        this.asteriskItem = asteriskItem;
    }

    /**
     * Returns the Gui position for this JLibItem
     * @return The Gui position
     */
    public int getGuiPosition() {
        return this.guiPosition;
    }

    /**
     * Sets the Gui position for this JLibItem
     * @param guiPosition The Gui Position
     */
    public void setGuiPosition(int guiPosition) {
        this.guiPosition = guiPosition;
    }

    /**
     * Returns the name
     * @return The name
     */
    public String getName() {
        return this.itemStack.hasItemMeta() ? this.itemStack.getItemMeta().hasDisplayName() ? this.itemStack.getItemMeta().getDisplayName() : null : null;
    }

    /**
     * Sets the name
     * @param name The name
     */
    public void setName(String name) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        if(name != null && !name.isEmpty()) itemMeta.setDisplayName(name);
        this.itemStack.setItemMeta(itemMeta);
    }

    /**
     * Returns the lore
     * @return The lore
     */
    public List<String> getLore() {
        return this.itemStack.hasItemMeta() ? this.itemStack.getItemMeta().hasLore() ? this.itemStack.getItemMeta().getLore() : null : null;
    }

    /**
     * Sets the lore, separated by |
     * @param lore The lore
     */
    public void setLore(String lore) {
        this.setLore(lore == null ? null : lore.split("\\|"));
    }

    /**
     * Sets the lore
     * @param lore The lore
     */
    public void setLore(String[] lore) {
        this.setLore(lore == null ? null : Arrays.asList(lore));
    }

    /**
     * Sets the lore
     * @param lore The lore
     */
    public void setLore(List<String> lore) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        if(lore != null && !lore.isEmpty()) {
            List<String> l = new ArrayList<>();
            for(String s : lore) {
                if(!s.isEmpty()) l.add(s);
            }
            itemMeta.setLore(l);
        }
        this.itemStack.setItemMeta(itemMeta);
    }

    /**
     * Returns whether this ItemStack is similar to another one
     * @param itemStack The other ItemStack
     * @return Whether this ItemStack is similar
     */
    public boolean isSimilar(ItemStack itemStack) {
        return this.isSimilar(itemStack, this.asteriskItem);
    }

    /**
     * Returns whether this ItemStack is similar to another one
     * @param itemStack The other ItemStack
     * @param ignoreDurability Whether we should ignore durability
     * @return Whether this ItemStack is similar
     */
    public boolean isSimilar(ItemStack itemStack, boolean ignoreDurability) {
        if(this.itemStack == null || itemStack == null) return Objects.equals(this.itemStack, itemStack);
        if(this.itemStack.getType() != itemStack.getType()) return false;
        if(this.itemStack.getDurability() != itemStack.getDurability() && !ignoreDurability) return false;
        if(this.itemStack.getItemMeta() instanceof org.bukkit.inventory.meta.SkullMeta || itemStack.getItemMeta() instanceof org.bukkit.inventory.meta.SkullMeta) return ((org.bukkit.inventory.meta.SkullMeta) this.itemStack.getItemMeta()).hasOwner() ? ((org.bukkit.inventory.meta.SkullMeta) this.itemStack.getItemMeta()).getOwner().equals(((org.bukkit.inventory.meta.SkullMeta) itemStack.getItemMeta()).getOwner()) : !((org.bukkit.inventory.meta.SkullMeta) itemStack.getItemMeta()).hasOwner();
        else if(this.itemStack.getItemMeta() instanceof org.bukkit.inventory.meta.PotionMeta || itemStack.getItemMeta() instanceof org.bukkit.inventory.meta.PotionMeta) return ((org.bukkit.inventory.meta.PotionMeta) this.itemStack.getItemMeta()).hasCustomEffects() ? ((org.bukkit.inventory.meta.PotionMeta) this.itemStack.getItemMeta()).getCustomEffects().equals(((org.bukkit.inventory.meta.PotionMeta) itemStack.getItemMeta()).getCustomEffects()) : !((org.bukkit.inventory.meta.PotionMeta) itemStack.getItemMeta()).hasCustomEffects();
        else if(this.itemStack.getItemMeta() instanceof org.bukkit.inventory.meta.BookMeta || itemStack.getItemMeta() instanceof org.bukkit.inventory.meta.BookMeta) {
            boolean title = ((org.bukkit.inventory.meta.BookMeta) this.itemStack.getItemMeta()).hasTitle() ? ((org.bukkit.inventory.meta.BookMeta) this.itemStack.getItemMeta()).getTitle().equals(((org.bukkit.inventory.meta.BookMeta) itemStack.getItemMeta()).getTitle()) : !((org.bukkit.inventory.meta.BookMeta) itemStack.getItemMeta()).hasTitle();
            boolean author = ((org.bukkit.inventory.meta.BookMeta) this.itemStack.getItemMeta()).hasAuthor() ? ((org.bukkit.inventory.meta.BookMeta) this.itemStack.getItemMeta()).getAuthor().equals(((org.bukkit.inventory.meta.BookMeta) itemStack.getItemMeta()).getAuthor()) : !((org.bukkit.inventory.meta.BookMeta) itemStack.getItemMeta()).hasAuthor();
            boolean pages = ((org.bukkit.inventory.meta.BookMeta) this.itemStack.getItemMeta()).hasPages() ? ((org.bukkit.inventory.meta.BookMeta) this.itemStack.getItemMeta()).getPages().equals(((org.bukkit.inventory.meta.BookMeta) itemStack.getItemMeta()).getPages()) : !((org.bukkit.inventory.meta.BookMeta) itemStack.getItemMeta()).hasPages();
            return title && author && pages;
        } else return Bukkit.getItemFactory().equals(this.itemStack.getItemMeta(), itemStack.getItemMeta());
    }

    public NBTTag getNBTTag() throws Exception {
        Object stack = ReflectionAPI.getField(ReflectionAPI.getObcClass("inventory.CraftItemStack"), this.itemStack, "handle");
        Object tag = ReflectionAPI.getNmsClass("ItemStack").getMethod("getTag").invoke(stack);
        return new NBTTag(tag == null ? ReflectionAPI.getNmsClass("NBTTagCompound").newInstance() : tag);
    }

    public void setNbtTag(NBTTag nbtTag) throws Exception {
        Object stack = ReflectionAPI.getField(ReflectionAPI.getObcClass("inventory.CraftItemStack"), this.itemStack, "handle");
        ReflectionAPI.getNmsClass("ItemStack").getMethod("setTag", ReflectionAPI.getNmsClass("NBTTagCompound")).invoke(stack, nbtTag.getNbtTag());
    }
}
