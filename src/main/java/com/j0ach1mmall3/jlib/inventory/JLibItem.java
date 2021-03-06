package com.j0ach1mmall3.jlib.inventory;

import com.j0ach1mmall3.jlib.gui.events.GuiClickEvent;
import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import com.j0ach1mmall3.jlib.nms.nbt.NBTTag;
import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import org.bukkit.*;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
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
    private CallbackHandler<GuiClickEvent> guiClickHandler;

    /**
     * Constructs a new JLibItem
     *
     * @param itemStack The ItemStack
     */
    public JLibItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Constructs a new JLibItem
     *
     * @param itemStack    The ItemStack
     * @param asteriskItem Whether this JLibItem is an AsteriskItem
     */
    public JLibItem(ItemStack itemStack, boolean asteriskItem) {
        this.itemStack = itemStack;
        this.asteriskItem = asteriskItem;
    }

    /**
     * Constructs a new JLibItem
     *
     * @param itemStack   The ItemStack
     * @param guiPosition The Gui position for this JLibItem
     */
    public JLibItem(ItemStack itemStack, int guiPosition) {
        this.itemStack = itemStack;
        this.guiPosition = guiPosition;
    }

    /**
     * Constructs a new JLibItem
     *
     * @param itemStack       The ItemStack
     * @param guiClickHandler The CallbackHandler to call back to when this item gets clicked in a GUI
     */
    public JLibItem(ItemStack itemStack, CallbackHandler<GuiClickEvent> guiClickHandler) {
        this.itemStack = itemStack;
        this.guiClickHandler = guiClickHandler;
    }

    /**
     * Constructs a new JLibItem
     *
     * @param itemStack    The ItemStack
     * @param asteriskItem Whether this JLibItem is an AsteriskItem
     * @param guiPosition  The Gui position for this JLibItem
     */
    public JLibItem(ItemStack itemStack, boolean asteriskItem, int guiPosition) {
        this.itemStack = itemStack;
        this.asteriskItem = asteriskItem;
        this.guiPosition = guiPosition;
    }

    /**
     * Constructs a new JLibItem
     *
     * @param itemStack       The ItemStack
     * @param asteriskItem    Whether this JLibItem is an AsteriskItem
     * @param guiClickHandler The CallbackHandler to call back to when this item gets clicked in a GUI
     */
    public JLibItem(ItemStack itemStack, boolean asteriskItem, CallbackHandler<GuiClickEvent> guiClickHandler) {
        this.itemStack = itemStack;
        this.asteriskItem = asteriskItem;
        this.guiClickHandler = guiClickHandler;
    }

    /**
     * Constructs a new JLibItem
     *
     * @param itemStack       The ItemStack
     * @param guiPosition     The Gui position for this JLibItem
     * @param guiClickHandler The CallbackHandler to call back to when this item gets clicked in a GUI
     */
    public JLibItem(ItemStack itemStack, int guiPosition, CallbackHandler<GuiClickEvent> guiClickHandler) {
        this.itemStack = itemStack;
        this.guiPosition = guiPosition;
        this.guiClickHandler = guiClickHandler;
    }

    /**
     * Constructs a new JLibItem
     *
     * @param itemStack       The ItemStack
     * @param asteriskItem    Whether this JLibItem is an AsteriskItem
     * @param guiPosition     The Gui position for this JLibItem
     * @param guiClickHandler The CallbackHandler to call back to when this item gets clicked in a GUI
     */
    public JLibItem(ItemStack itemStack, boolean asteriskItem, int guiPosition, CallbackHandler<GuiClickEvent> guiClickHandler) {
        this.itemStack = itemStack;
        this.asteriskItem = asteriskItem;
        this.guiPosition = guiPosition;
        this.guiClickHandler = guiClickHandler;
    }

    /**
     * Returns the ItemStack
     *
     * @return The ItemStack
     */
    public ItemStack getItemStack() {
        return this.itemStack;
    }

    /**
     * Sets the ItemStack
     *
     * @param itemStack The ItemStack
     */
    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Returns whether this JLibItem is an AsteriskItem
     *
     * @return Whether this JLibItem is an AsteriskItem
     */
    public boolean isAsteriskItem() {
        return this.asteriskItem;
    }

    /**
     * Sets whether this JLibItem is an AsteriskItem
     *
     * @param asteriskItem Whether this JLibItem is an AsteriskItem
     */
    public void setAsteriskItem(boolean asteriskItem) {
        this.asteriskItem = asteriskItem;
    }

    /**
     * Returns the Gui position for this JLibItem
     *
     * @return The Gui position
     */
    public int getGuiPosition() {
        return this.guiPosition;
    }

    /**
     * Sets the Gui position for this JLibItem
     *
     * @param guiPosition The Gui Position
     */
    public void setGuiPosition(int guiPosition) {
        this.guiPosition = guiPosition;
    }

    /**
     * Returns the CallbackHandler to call back to when this item gets clicked in a GUI
     *
     * @return The CallbackHandler
     */
    public CallbackHandler<GuiClickEvent> getGuiClickHandler() {
        return this.guiClickHandler;
    }

    /**
     * Sets the CallbackHandler to call back to when this item gets clicked in a GUI
     *
     * @param guiClickHandler The CallbackHandler
     */
    public void setGuiClickHandler(CallbackHandler<GuiClickEvent> guiClickHandler) {
        this.guiClickHandler = guiClickHandler;
    }

    /**
     * Returns whether this ItemStack is similar to another one
     *
     * @param itemStack The other ItemStack
     * @return Whether this ItemStack is similar
     */
    public boolean isSimilar(ItemStack itemStack) {
        return this.isSimilar(itemStack, this.asteriskItem);
    }

    /**
     * Returns whether this ItemStack is similar to another one
     *
     * @param itemStack        The other ItemStack
     * @param ignoreDurability Whether we should ignore durability
     * @return Whether this ItemStack is similar
     */
    public boolean isSimilar(ItemStack itemStack, boolean ignoreDurability) {
        if (this.itemStack == null || itemStack == null) return Objects.equals(this.itemStack, itemStack);
        if (this.itemStack.getType() != itemStack.getType()) return false;
        if (this.itemStack.getDurability() != itemStack.getDurability() && !ignoreDurability) return false;
        if (this.itemStack.getItemMeta() instanceof SkullMeta || itemStack.getItemMeta() instanceof SkullMeta)
            return ((SkullMeta) this.itemStack.getItemMeta()).hasOwner() ? ((SkullMeta) this.itemStack.getItemMeta()).getOwner().equals(((SkullMeta) itemStack.getItemMeta()).getOwner()) : !((SkullMeta) itemStack.getItemMeta()).hasOwner();
        else if (this.itemStack.getItemMeta() instanceof PotionMeta || itemStack.getItemMeta() instanceof PotionMeta)
            return ((PotionMeta) this.itemStack.getItemMeta()).hasCustomEffects() ? ((PotionMeta) this.itemStack.getItemMeta()).getCustomEffects().equals(((PotionMeta) itemStack.getItemMeta()).getCustomEffects()) : !((PotionMeta) itemStack.getItemMeta()).hasCustomEffects();
        else if (this.itemStack.getItemMeta() instanceof BookMeta || itemStack.getItemMeta() instanceof BookMeta) {
            boolean title = ((BookMeta) this.itemStack.getItemMeta()).hasTitle() ? ((BookMeta) this.itemStack.getItemMeta()).getTitle().equals(((BookMeta) itemStack.getItemMeta()).getTitle()) : !((BookMeta) itemStack.getItemMeta()).hasTitle();
            boolean author = ((BookMeta) this.itemStack.getItemMeta()).hasAuthor() ? ((BookMeta) this.itemStack.getItemMeta()).getAuthor().equals(((BookMeta) itemStack.getItemMeta()).getAuthor()) : !((BookMeta) itemStack.getItemMeta()).hasAuthor();
            boolean pages = ((BookMeta) this.itemStack.getItemMeta()).hasPages() ? ((BookMeta) this.itemStack.getItemMeta()).getPages().equals(((BookMeta) itemStack.getItemMeta()).getPages()) : !((BookMeta) itemStack.getItemMeta()).hasPages();
            return title && author && pages;
        } else return Bukkit.getItemFactory().equals(this.itemStack.getItemMeta(), itemStack.getItemMeta());
    }

    /**
     * Returns the NBTTag of this JLibItem
     *
     * @return The NBTTag
     * @throws Exception if an exception occurs
     */
    public NBTTag getNBTTag() throws Exception {
        Object stack = ReflectionAPI.getNmsItemStack(this.itemStack);
        Object tag = ReflectionAPI.getNmsClass("ItemStack").getMethod("getTag").invoke(stack);
        return new NBTTag(tag == null ? ReflectionAPI.getNmsClass("NBTTagCompound").newInstance() : tag);
    }

    /**
     * Sets the NBTTag of this JLibItem
     *
     * @param nbtTag The NBTTag
     * @throws Exception if an exception occurs
     */
    public void setNbtTag(NBTTag nbtTag) throws Exception {
        Object stack = ReflectionAPI.getNmsItemStack(this.itemStack);
        ReflectionAPI.getNmsClass("ItemStack").getMethod("setTag", ReflectionAPI.getNmsClass("NBTTagCompound")).invoke(stack, nbtTag.getNbtTag());
        this.itemStack = (ItemStack) ReflectionAPI.getObcClass("inventory.CraftItemStack").getMethod("asCraftMirror", ReflectionAPI.getNmsClass("ItemStack")).invoke(null, stack);
    }

    public static final class Builder {
        private Material type;
        private int amount;
        private short durability;
        private String name;
        private final List<String> lore = new ArrayList<>();
        private final Map<Enchantment, Integer> enchantments = new HashMap<>();
        private final Set itemFlags = new HashSet();
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
        private String texture;
        private EntityType entityType;
        private final Map<String, NBTTag> additionalTags = new HashMap<>();
        private boolean asteriskItem;
        private boolean unbreakable;
        private int guiPosition;
        private CallbackHandler<GuiClickEvent> guiClickHandler;

        /**
         * Sets the type
         *
         * @param type The type
         * @return The Builder
         */
        public Builder withType(Material type) {
            this.type = type;
            return this;
        }
        
        public Builder withUnbreakable(boolean unbreakable) {
            this.unbreakable = unbreakable;
            return this;
        }

        /**
         * Sets the amount
         *
         * @param amount The amount
         * @return The Builder
         */
        public Builder withAmount(int amount) {
            this.amount = amount;
            return this;
        }

        /**
         * Sets the durability
         *
         * @param durability durability
         * @return The Builder
         */
        public Builder withDurability(short durability) {
            this.durability = durability;
            return this;
        }

        /**
         * Sets the name
         *
         * @param name The name
         * @return The Builder
         */
        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Adds lore
         *
         * @param lore The lore
         * @return The Builder
         */
        public Builder withLore(String... lore) {
            return this.withLore(Arrays.asList(lore));
        }

        /**
         * Adds lore
         *
         * @param lore The lore
         * @return The Builder
         */
        public Builder withLore(Collection<String> lore) {
            this.lore.addAll(lore);
            return this;
        }

        /**
         * Adds an enchantment
         *
         * @param enchantment The enchantment
         * @param level       The level
         * @return The Builder
         */
        public Builder withEnchantment(Enchantment enchantment, int level) {
            this.enchantments.put(enchantment, level);
            return this;
        }

        /**
         * Adds enchantments
         *
         * @param enchantments The enchantments
         * @return The Builder
         */
        public Builder withEnchantments(Map<Enchantment, Integer> enchantments) {
            this.enchantments.putAll(enchantments);
            return this;
        }

        /**
         * Adds ItemFlags
         *
         * @param itemFlags The ItemFlags
         * @return The Builder
         */
        public Builder withItemFlags(Object... itemFlags) {
            return this.withItemFlags(Arrays.asList(itemFlags));
        }

        /**
         * Adds ItemFlags
         *
         * @param itemFlags The ItemFlags
         * @return The Builder
         */
        public Builder withItemFlags(Collection<Object> itemFlags) {
            this.itemFlags.addAll(itemFlags);
            return this;
        }

        /**
         * Sets the base color
         *
         * @param baseColor The base color
         * @return The Builder
         */
        public Builder withBaseColor(DyeColor baseColor) {
            this.baseColor = baseColor;
            return this;
        }

        /**
         * Adds Patterns
         *
         * @param patterns The Patterns
         * @return The Builder
         */
        public Builder withPatterns(Pattern... patterns) {
            return this.withPatterns(Arrays.asList(patterns));
        }

        /**
         * Adds Patterns
         *
         * @param patterns The Patterns
         * @return The Builder
         */
        public Builder withPatterns(Collection<Pattern> patterns) {
            this.patterns.addAll(patterns);
            return this;
        }

        /**
         * Sets the title
         *
         * @param title The title
         * @return The Builder
         */
        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Sets the author
         *
         * @param author The author
         * @return The Builder
         */
        public Builder withAuthor(String author) {
            this.author = author;
            return this;
        }

        /**
         * Adds pages
         *
         * @param pages The pages
         * @return The Builder
         */
        public Builder withPages(String... pages) {
            return this.withPages(Arrays.asList(pages));
        }

        /**
         * Adds pages
         *
         * @param pages The pages
         * @return The Builder
         */
        public Builder withPages(Collection<String> pages) {
            this.pages.addAll(pages);
            return this;
        }

        /**
         * Sets the power
         *
         * @param power The power
         * @return The Builder
         */
        public Builder withPower(int power) {
            this.power = power;
            return this;
        }

        /**
         * Adds FireworkEffects
         *
         * @param fireworkEffects The FireworkEffects
         * @return The Builder
         */
        public Builder withFireworkEffects(FireworkEffect... fireworkEffects) {
            return this.withFireworkEffects(Arrays.asList(fireworkEffects));
        }

        /**
         * Adds FireworkEffects
         *
         * @param fireworkEffects The FireworkEffects
         * @return The Builder
         */
        public Builder withFireworkEffects(Collection<FireworkEffect> fireworkEffects) {
            this.fireworkEffects.addAll(fireworkEffects);
            return this;
        }

        /**
         * Sets the color
         *
         * @param color The color
         * @return The Builder
         */
        public Builder withColor(Color color) {
            this.color = color;
            return this;
        }

        /**
         * Sets the scaling
         *
         * @param scaling The scaling
         * @return The Builder
         */
        public Builder withScaling(boolean scaling) {
            this.scaling = scaling;
            return this;
        }

        /**
         * Sets the base PotionData
         *
         * @param basePotionData The base PotionData
         * @return The Builder
         */
        public Builder withBasePotionData(PotionData basePotionData) {
            this.basePotionData = basePotionData;
            return this;
        }

        /**
         * Sets the mainEffect
         *
         * @param mainEffect The mainEffect
         * @return The Builder
         */
        public Builder withMainEffect(PotionEffectType mainEffect) {
            this.mainEffect = mainEffect;
            return this;
        }

        /**
         * Adds custom PotionEffects
         *
         * @param customEffects The custom PotionEffects
         * @return The Builder
         */
        public Builder withCustomEffects(PotionEffect... customEffects) {
            return this.withCustomEffects(Arrays.asList(customEffects));
        }

        /**
         * Adds custom PotionEffects
         *
         * @param customEffects The custom PotionEffects
         * @return The Builder
         */
        public Builder withCustomEffects(Collection<PotionEffect> customEffects) {
            this.customEffects.addAll(customEffects);
            return this;
        }

        /**
         * Sets the owner
         *
         * @param owner The owner
         * @return The Builder
         */
        public Builder withOwner(String owner) {
            this.owner = owner;
            return this;
        }

        /**
         * Sets the texture
         *
         * @param owner The texture
         * @return The Builder
         */
        public Builder withTexture(String texture) {
            this.texture = texture;
            return this;
        }

        /**
         * Sets the EntityType
         *
         * @param entityType The EntityType
         * @return The Builder
         */
        public Builder withEntityType(EntityType entityType) {
            this.entityType = entityType;
            return this;
        }

        /**
         * Adds an additional NBTTag
         *
         * @param name          The name of the NBTTag
         * @param additionalTag The additional NBTTag
         * @return The Builder
         */
        public Builder withAdditionalTag(String name, NBTTag additionalTag) {
            this.additionalTags.put(name, additionalTag);
            return this;
        }

        /**
         * Adds additional NBTTags
         *
         * @param additionalTags The additional NBTTags
         * @return The Builder
         */
        public Builder withAdditionalTags(Map<String, NBTTag> additionalTags) {
            this.additionalTags.putAll(additionalTags);
            return this;
        }

        /**
         * Sets whether this is an AsteriskItem
         *
         * @param asteriskItem Whether this is an AsteriskItem
         * @return The Builder
         */
        public Builder withAsteriskItem(boolean asteriskItem) {
            this.asteriskItem = asteriskItem;
            return this;
        }

        /**
         * Sets the Gui position
         *
         * @param guiPosition The Gui position
         * @return The Builder
         */
        public Builder withGuiPosition(int guiPosition) {
            this.guiPosition = guiPosition;
            return this;
        }

        /**
         * Sets the Gui ClickHandler
         *
         * @param guiClickHandler The Gui ClickHandler
         * @return The Builder
         */
        public Builder withGuiHandler(CallbackHandler<GuiClickEvent> guiClickHandler) {
            this.guiClickHandler = guiClickHandler;
            return this;
        }

        /**
         * Sets the ItemStack
         *
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

            if (itemStack.hasItemMeta()) this.withItemMeta(itemStack.getItemMeta());
            return this;
        }

        /**
         * Sets the ItemMeta
         *
         * @param itemMeta The ItemMeta
         * @return The Builder
         */
        public Builder withItemMeta(ItemMeta itemMeta) {
            if (itemMeta.hasDisplayName()) this.withName(itemMeta.getDisplayName());
            if (itemMeta.hasLore()) this.withLore(itemMeta.getLore().toArray(new String[itemMeta.getLore().size()]));
            this.withEnchantments(itemMeta.getEnchants());
            this.withItemFlags(itemMeta.getItemFlags());
            return this;
        }

        /**
         * Builds a JLibItem
         *
         * @return The JLibItem
         */
        @SuppressWarnings("deprecation")
        public JLibItem build() {
            JLibItem jLibItem = new JLibItem(new ItemStack(Material.AIR));
            if (this.type != null) jLibItem.itemStack.setType(this.type);
            if (this.amount != 0) jLibItem.itemStack.setAmount(this.amount);
            if (this.durability != 0) jLibItem.itemStack.setDurability(this.durability);

            ItemMeta itemMeta = jLibItem.itemStack.getItemMeta();
            itemMeta.setUnbreakable(this.unbreakable);
            if (this.name != null) itemMeta.setDisplayName(this.name);
            if (!this.lore.isEmpty()) itemMeta.setLore(this.lore);
            for (Map.Entry<Enchantment, Integer> entry : this.enchantments.entrySet()) {
                if (itemMeta instanceof EnchantmentStorageMeta)
                    ((EnchantmentStorageMeta) itemMeta).addStoredEnchant(entry.getKey(), entry.getValue(), true);
                else itemMeta.addEnchant(entry.getKey(), entry.getValue(), true);
            }
            for (Object itemFlag : this.itemFlags) {
                itemMeta.addItemFlags((ItemFlag) itemFlag);
            }
            if (this.baseColor != null) ((BannerMeta) itemMeta).setBaseColor(this.baseColor);
            if (!this.patterns.isEmpty()) ((BannerMeta) itemMeta).setPatterns(this.patterns);
            if (this.title != null) ((BookMeta) itemMeta).setTitle(this.title);
            if (this.author != null) ((BookMeta) itemMeta).setAuthor(this.author);
            if (!this.pages.isEmpty()) ((BookMeta) itemMeta).setPages(this.pages);
            if (this.power != 0) ((FireworkMeta) itemMeta).setPower(this.power);
            if (!this.fireworkEffects.isEmpty()) {
                if (itemMeta instanceof FireworkEffectMeta)
                    ((FireworkEffectMeta) itemMeta).setEffect(this.fireworkEffects.get(0));
                else ((FireworkMeta) itemMeta).addEffects(this.fireworkEffects);
            }
            if (this.color != null) ((LeatherArmorMeta) itemMeta).setColor(this.color);
            if (this.scaling) ((MapMeta) itemMeta).setScaling(true);
            if (this.basePotionData != null)
                ((PotionMeta) itemMeta).setBasePotionData(this.basePotionData);
            if (this.mainEffect != null)
                ((PotionMeta) itemMeta).setMainEffect(this.mainEffect);
            for (PotionEffect customEffect : this.customEffects) {
                ((PotionMeta) itemMeta).addCustomEffect(customEffect, true);
            }
            if (this.owner != null) ((SkullMeta) itemMeta).setOwner(this.owner);
            jLibItem.itemStack.setItemMeta(itemMeta);

            try {
                NBTTag nbtTag = jLibItem.getNBTTag();
                Map<String, NBTTag> map = nbtTag.getMap();
                for (Map.Entry<String, NBTTag> entry : this.additionalTags.entrySet()) {
                    map.put(entry.getKey(), entry.getValue());
                }
                if (this.entityType != null) {
                    Map<String, NBTTag> m = new HashMap<>();
                    m.put("id", new NBTTag(NBTTag.STRING, this.entityType.getName()));
                    map.put("EntityTag", new NBTTag(m));
                }
                if (this.texture != null) {
                    Map<String, NBTTag> m = new HashMap<>();
                    m.put("Value", new NBTTag(NBTTag.STRING, this.texture));
                    Map<String, NBTTag> m1 = new HashMap<>();
                    m1.put("textures", new NBTTag(Collections.singletonList(new NBTTag(m))));
                    Map<String, NBTTag> m2 = new HashMap<>();
                    m2.put("Id", new NBTTag(NBTTag.STRING, "00000000-0000-0000-0000-000000000000"));
                    m2.put("Properties", new NBTTag(m1));
                    map.put("SkullOwner", new NBTTag(m2));
                }
                nbtTag.setMap(map);
                jLibItem.setNbtTag(nbtTag);
            } catch (Exception e) {
                if (this.type != Material.AIR) e.printStackTrace();
            }

            if (this.asteriskItem) jLibItem.setAsteriskItem(true);
            if (this.guiPosition != 0) jLibItem.setGuiPosition(this.guiPosition);
            if (this.guiClickHandler != null) jLibItem.setGuiClickHandler(this.guiClickHandler);

            return jLibItem;
        }


    }
}
