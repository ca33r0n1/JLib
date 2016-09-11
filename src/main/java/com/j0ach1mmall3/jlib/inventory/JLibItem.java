package com.j0ach1mmall3.jlib.inventory;

import com.j0ach1mmall3.jlib.gui.events.GuiClickEvent;
import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import com.j0ach1mmall3.jlib.nms.nbt.NBTTag;
import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import org.bukkit.*;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 2/09/2016
 */
public final class JLibItem {
    private ItemStack itemStack;
    private boolean asteriskItem;
    private int guiPosition;
    private CallbackHandler<GuiClickEvent> onGuiClick;

    /**
     * Constructs a new JLibItem
     * @param itemStack The ItemStack
     */
    public JLibItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Constructs a new JLibItem
     * @param itemStack The ItemStack
     * @param asteriskItem Whether this JLibItem is an AsteriskItem
     */
    public JLibItem(ItemStack itemStack, boolean asteriskItem) {
        this.itemStack = itemStack;
        this.asteriskItem = asteriskItem;
    }

    /**
     * Constructs a new JLibItem
     * @param itemStack The ItemStack
     * @param guiPosition The Gui position for this JLibItem
     */
    public JLibItem(ItemStack itemStack, int guiPosition) {
        this.itemStack = itemStack;
        this.guiPosition = guiPosition;
    }

    /**
     * Constructs a new JLibItem
     * @param itemStack The ItemStack
     * @param asteriskItem Whether this JLibItem is an AsteriskItem
     * @param guiPosition The Gui position for this JLibItem
     */
    public JLibItem(ItemStack itemStack, boolean asteriskItem, int guiPosition) {
        this.itemStack = itemStack;
        this.asteriskItem = asteriskItem;
        this.guiPosition = guiPosition;
    }

    /**
     * Constructs a new JLibItem
     * @param itemStack The ItemStack
     * @param asteriskItem Whether this JLibItem is an AsteriskItem
     * @param guiPosition The Gui position for this JLibItem
     * @param onGuiClick The CallbackHandler to call back to when this item gets clicked in a GUI
     */
    public JLibItem(ItemStack itemStack, boolean asteriskItem, int guiPosition, CallbackHandler<GuiClickEvent> onGuiClick) {
        this.itemStack = itemStack;
        this.asteriskItem = asteriskItem;
        this.guiPosition = guiPosition;
        this.onGuiClick = onGuiClick;
    }

    /**
     * Returns the ItemStack
     * @return The ItemStack
     */
    public ItemStack getItemStack() {
        return this.itemStack;
    }

    /**
     * Sets the ItemStack
     * @param itemStack The ItemStack
     */
    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
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
     * Returns the CallbackHandler to call back to when this item gets clicked in a GUI
     * @return The CallbackHandler
     */
    public CallbackHandler<GuiClickEvent> getOnGuiClick() {
        return this.onGuiClick;
    }

    /**
     * Sets the CallbackHandler to call back to when this item gets clicked in a GUI
     * @param onGuiClick The CallbackHandler
     */
    public void setOnGuiClick(CallbackHandler<GuiClickEvent> onGuiClick) {
        this.onGuiClick = onGuiClick;
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

    /**
     * Returns the NBTTag of this JLibItem
     * @return The NBTTag
     * @throws Exception if an exception occurs
     */
    public NBTTag getNBTTag() throws Exception {
        Object stack = this.itemStack.getClass() == ItemStack.class ? ReflectionAPI.getObcClass("inventory.CraftItemStack").getMethod("asNMSCopy", ItemStack.class).invoke(null, this.itemStack) : ReflectionAPI.getField(ReflectionAPI.getObcClass("inventory.CraftItemStack"), this.itemStack, "handle");
        Object tag = ReflectionAPI.getNmsClass("ItemStack").getMethod("getTag").invoke(stack);
        return new NBTTag(tag == null ? ReflectionAPI.getNmsClass("NBTTagCompound").newInstance() : tag);
    }

    /**
     * Sets the NBTTag of this JLibItem
     * @param nbtTag The NBTTag
     * @throws Exception if an exception occurs
     */
    public void setNbtTag(NBTTag nbtTag) throws Exception {
        Object stack = this.itemStack.getClass() == ItemStack.class ? ReflectionAPI.getObcClass("inventory.CraftItemStack").getMethod("asNMSCopy", ItemStack.class).invoke(null, this.itemStack) : ReflectionAPI.getField(ReflectionAPI.getObcClass("inventory.CraftItemStack"), this.itemStack, "handle");
        ReflectionAPI.getNmsClass("ItemStack").getMethod("setTag", ReflectionAPI.getNmsClass("NBTTagCompound")).invoke(stack, nbtTag.getNbtTag());
        this.itemStack = (ItemStack) ReflectionAPI.getObcClass("inventory.CraftItemStack").getMethod("asBukkitCopy", ReflectionAPI.getNmsClass("ItemStack")).invoke(null, stack);
    }

    public static final class Builder {
        private Material type;
        private int amount;
        private short durability;
        private String name;
        private final List<String> lore = new ArrayList<>();
        private final Map<Enchantment, Integer> enchantments = new HashMap<>();
        private final Set<ItemFlag> itemFlags = EnumSet.noneOf(ItemFlag.class);
        private DyeColor baseColor;
        private final List<Pattern> patterns = new ArrayList<>();
        private String title;
        private String author;
        private final List<String> pages = new ArrayList<>();
        private int power;
        private final List<FireworkEffect> fireworkEffects = new ArrayList<>();
        private Color color;
        private boolean scaling;
        private PotionData basePotionData;
        private PotionEffectType mainEffect;
        private final Set<PotionEffect> customEffects = new HashSet<>();
        private String owner;
        private final Map<String, NBTTag> additionalTags = new HashMap<>();

        /**
         * Sets the type
         * @param type The type
         * @return The Builder
         */
        public Builder withType(Material type) {
            this.type = type;
            return this;
        }

        /**
         * Sets the amount
         * @param amount The amount
         * @return The Builder
         */
        public Builder withAmount(int amount) {
            this.amount = amount;
            return this;
        }

        /**
         * Sets the durability
         * @param durability durability
         * @return The Builder
         */
        public Builder withDurability(short durability) {
            this.durability = durability;
            return this;
        }

        /**
         * Sets the name
         * @param name The name
         * @return The Builder
         */
        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Adds lore
         * @param lore The lore
         * @return The Builder
         */
        public Builder withLore(String... lore) {
            return this.withLore(Arrays.asList(lore));
        }

        /**
         * Adds lore
         * @param lore The lore
         * @return The Builder
         */
        public Builder withLore(Collection<String> lore) {
            this.lore.addAll(lore);
            return this;
        }

        /**
         * Adds an enchantment
         * @param enchantment The enchantment
         * @param level The level
         * @return The Builder
         */
        public Builder withEnchantment(Enchantment enchantment, int level) {
            this.enchantments.put(enchantment, level);
            return this;
        }

        /**
         * Adds enchantments
         * @param enchantments The enchantments
         * @return The Builder
         */
        public Builder withEnchantments(Map<Enchantment, Integer> enchantments) {
            this.enchantments.putAll(enchantments);
            return this;
        }

        /**
         * Adds ItemFlags
         * @param itemFlags The ItemFlags
         * @return The Builder
         */
        public Builder withItemFlags(ItemFlag... itemFlags) {
            return this.withItemFlags(Arrays.asList(itemFlags));
        }

        /**
         * Adds ItemFlags
         * @param itemFlags The ItemFlags
         * @return The Builder
         */
        public Builder withItemFlags(Collection<ItemFlag> itemFlags) {
            this.itemFlags.addAll(itemFlags);
            return this;
        }

        /**
         * Sets the base color
         * @param baseColor The base color
         * @return The Builder
         */
        public Builder withBaseColor(DyeColor baseColor) {
            this.baseColor = baseColor;
            return this;
        }

        /**
         * Adds Patterns
         * @param patterns The Patterns
         * @return The Builder
         */
        public Builder withPatterns(Pattern... patterns) {
            return this.withPatterns(Arrays.asList(patterns));
        }

        /**
         * Adds Patterns
         * @param patterns The Patterns
         * @return The Builder
         */
        public Builder withPatterns(Collection<Pattern> patterns) {
            this.patterns.addAll(patterns);
            return this;
        }

        /**
         * Sets the title
         * @param title The title
         * @return The Builder
         */
        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Sets the author
         * @param author The author
         * @return The Builder
         */
        public Builder withAuthor(String author) {
            this.author = author;
            return this;
        }

        /**
         * Adds pages
         * @param pages The pages
         * @return The Builder
         */
        public Builder withPages(String... pages) {
            return this.withPages(Arrays.asList(pages));
        }

        /**
         * Adds pages
         * @param pages The pages
         * @return The Builder
         */
        public Builder withPages(Collection<String> pages) {
            this.pages.addAll(pages);
            return this;
        }

        /**
         * Sets the power
         * @param power The power
         * @return The Builder
         */
        public Builder withPower(int power) {
            this.power = power;
            return this;
        }

        /**
         * Adds FireworkEffects
         * @param fireworkEffects The FireworkEffects
         * @return The Builder
         */
        public Builder withFireworkEffects(FireworkEffect... fireworkEffects) {
            return this.withFireworkEffects(Arrays.asList(fireworkEffects));
        }

        /**
         * Adds FireworkEffects
         * @param fireworkEffects The FireworkEffects
         * @return The Builder
         */
        public Builder withFireworkEffects(Collection<FireworkEffect> fireworkEffects) {
            this.fireworkEffects.addAll(fireworkEffects);
            return this;
        }

        /**
         * Sets the color
         * @param color The color
         * @return The Builder
         */
        public Builder withColor(Color color) {
            this.color = color;
            return this;
        }

        /**
         * Sets the scaling
         * @param scaling The scaling
         * @return The Builder
         */
        public Builder withScaling(boolean scaling) {
            this.scaling = scaling;
            return this;
        }

        /**
         * Sets the base PotionData
         * @param basePotionData The base PotionData
         * @return The Builder
         */
        public Builder withBasePotionData(PotionData basePotionData) {
            this.basePotionData = basePotionData;
            return this;
        }

        /**
         * Sets the mainEffect
         * @param mainEffect The mainEffect
         * @return The Builder
         */
        public Builder withMainEffect(PotionEffectType mainEffect) {
            this.mainEffect = mainEffect;
            return this;
        }

        /**
         * Adds custom PotionEffects
         * @param customEffects The custom PotionEffects
         * @return The Builder
         */
        public Builder withCustomEffects(PotionEffect... customEffects) {
            return this.withCustomEffects(Arrays.asList(customEffects));
        }

        /**
         * Adds custom PotionEffects
         * @param customEffects The custom PotionEffects
         * @return The Builder
         */
        public Builder withCustomEffects(Collection<PotionEffect> customEffects) {
            this.customEffects.addAll(customEffects);
            return this;
        }

        /**
         * Sets the owner
         * @param owner The owner
         * @return The Builder
         */
        public Builder withOwner(String owner) {
            this.owner = owner;
            return this;
        }

        /**
         * Adds an additional NBTTag
         * @param name The name of the NBTTag
         * @param additionalTag The additional NBTTag
         * @return The Builder
         */
        public Builder withAdditionalTag(String name, NBTTag additionalTag) {
            this.additionalTags.put(name, additionalTag);
            return this;
        }

        /**
         * Adds additional NBTTags
         * @param additionalTags The additional NBTTags
         * @return The Builder
         */
        public Builder withAdditionalTags(Map<String, NBTTag> additionalTags) {
            this.additionalTags.putAll(additionalTags);
            return this;
        }

        /**
         * Sets the ItemStack
         * @param itemStack The ItemStack
         * @return The Builder
         */
        public Builder withItemStack(ItemStack itemStack) {
            this.withType(itemStack.getType());
            this.withAmount(itemStack.getAmount());
            this.withDurability(itemStack.getDurability());
            this.type = itemStack.getType();
            this.amount = itemStack.getAmount();
            this.durability = itemStack.getDurability();

            if(itemStack.hasItemMeta()) this.withItemMeta(itemStack.getItemMeta());
            return this;
        }

        /**
         * Sets the ItemMeta
         * @param itemMeta The ItemMeta
         * @return The Builder
         */
        public Builder withItemMeta(ItemMeta itemMeta) {
            if(itemMeta.hasDisplayName()) this.withName(itemMeta.getDisplayName());
            if(itemMeta.hasLore()) this.withLore(itemMeta.getLore().toArray(new String[itemMeta.getLore().size()]));
            this.withEnchantments(itemMeta.getEnchants());
            this.withItemFlags(itemMeta.getItemFlags());
            return this;
        }

        /**
         * Builds a JLibItem
         * @return The JLibItem
         */
        @SuppressWarnings("deprecation")
        public JLibItem build() {
            JLibItem jLibItem = new JLibItem(new ItemStack(Material.AIR));
            if(this.type != null) jLibItem.itemStack.setType(this.type);
            if(this.amount != 0) jLibItem.itemStack.setAmount(this.amount);
            if(this.durability != 0) jLibItem.itemStack.setDurability(this.durability);

            ItemMeta itemMeta = jLibItem.itemStack.getItemMeta();
            if(this.name != null) itemMeta.setDisplayName(this.name);
            if(!this.lore.isEmpty()) itemMeta.setLore(this.lore);
            for(Map.Entry<Enchantment, Integer> entry : this.enchantments.entrySet()) {
                if(itemMeta instanceof org.bukkit.inventory.meta.EnchantmentStorageMeta) ((org.bukkit.inventory.meta.EnchantmentStorageMeta) itemMeta).addStoredEnchant(entry.getKey(), entry.getValue(), true);
                else itemMeta.addEnchant(entry.getKey(), entry.getValue(), true);
            }
            for(ItemFlag itemFlag : this.itemFlags) {
                itemMeta.addItemFlags(itemFlag);
            }
            if(this.baseColor != null) ((org.bukkit.inventory.meta.BannerMeta) itemMeta).setBaseColor(this.baseColor);
            if(!this.patterns.isEmpty()) ((org.bukkit.inventory.meta.BannerMeta) itemMeta).setPatterns(this.patterns);
            if(this.title != null) ((org.bukkit.inventory.meta.BookMeta) itemMeta).setTitle(this.title);
            if(this.author != null) ((org.bukkit.inventory.meta.BookMeta) itemMeta).setAuthor(this.author);
            if(!this.pages.isEmpty()) ((org.bukkit.inventory.meta.BookMeta) itemMeta).setPages(this.pages);
            if(this.power != 0) ((org.bukkit.inventory.meta.FireworkMeta) itemMeta).setPower(this.power);
            if(!this.fireworkEffects.isEmpty()) {
                if(itemMeta instanceof org.bukkit.inventory.meta.FireworkEffectMeta) ((org.bukkit.inventory.meta.FireworkEffectMeta) itemMeta).setEffect(this.fireworkEffects.get(0));
                else ((org.bukkit.inventory.meta.FireworkMeta) itemMeta).addEffects(this.fireworkEffects);
            }
            if(this.color != null) ((org.bukkit.inventory.meta.LeatherArmorMeta) itemMeta).setColor(this.color);
            if(this.scaling) ((org.bukkit.inventory.meta.MapMeta) itemMeta).setScaling(true);
            if(this.basePotionData != null) ((org.bukkit.inventory.meta.PotionMeta) itemMeta).setBasePotionData(this.basePotionData);
            if(this.mainEffect != null) ((org.bukkit.inventory.meta.PotionMeta) itemMeta).setMainEffect(this.mainEffect);
            for(PotionEffect customEffect : this.customEffects) {
                ((org.bukkit.inventory.meta.PotionMeta) itemMeta).addCustomEffect(customEffect, true);
            }
            if(this.owner != null) ((org.bukkit.inventory.meta.SkullMeta) itemMeta).setOwner(this.owner);
            jLibItem.itemStack.setItemMeta(itemMeta);

            try {
                NBTTag nbtTag = jLibItem.getNBTTag();
                Map<String, NBTTag> map = nbtTag.getMap();
                for(Map.Entry<String, NBTTag> entry : this.additionalTags.entrySet()) {
                    map.put(entry.getKey(), entry.getValue());
                }
                nbtTag.setMap(map);
                jLibItem.setNbtTag(nbtTag);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return jLibItem;
        }
    }
}
