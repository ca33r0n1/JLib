package com.j0ach1mmall3.jlib.methods;

import com.j0ach1mmall3.jlib.integration.Placeholders;
import com.j0ach1mmall3.jlib.inventory.JLibItem;
import com.j0ach1mmall3.jlib.logging.JLogger;
import org.bukkit.*;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.*;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since Unknown
 */
public final class Parsing {

    /**
     * Let nobody instantiate this class
     */
    private Parsing() {
    }

    /**
     * Parses a String to an int safely
     *
     * @param s The String you want to parse
     * @return The int
     * @deprecated {@link Parsing#parseInt(String)}
     */
    @Deprecated
    public static int parseString(String s) {
        new JLogger().deprecation();
        return parseInt(s);
    }

    /**
     * Parses a String to a double safely
     *
     * @param s The String you want to parse
     * @return The double
     */
    public static double parseDouble(String s) {
        double d;
        try {
            d = Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return 0;
        }
        return d;
    }

    /**
     * Parses a String to a long safely
     *
     * @param s The String you want to parse
     * @return The long
     */
    public static long parseLong(String s) {
        long l;
        try {
            l = Long.parseLong(s);
        } catch (NumberFormatException e) {
            return 0;
        }
        return l;
    }

    /**
     * Parses a String to a float safely
     *
     * @param s The String you want to parse
     * @return The float
     */
    public static float parseFloat(String s) {
        float f;
        try {
            f = Float.parseFloat(s);
        } catch (NumberFormatException e) {
            return 0;
        }
        return f;
    }

    /**
     * Parses a String to a boolean safely
     *
     * @param s The String you want to parse
     * @return The boolean
     */
    public static boolean parseBoolean(String s) {
        return Boolean.parseBoolean(s);
    }

    /**
     * Parses a String to a byte safely
     *
     * @param s The String you want to parse
     * @return The byte
     */
    public static byte parseByte(String s) {
        byte i;
        try {
            i = Byte.parseByte(s);
        } catch (NumberFormatException e) {
            return 0;
        }
        return i;
    }

    /**
     * Parses a String to a short safely
     *
     * @param s The String you want to parse
     * @return The short
     */
    public static short parseShort(String s) {
        short i;
        try {
            i = Short.parseShort(s);
        } catch (NumberFormatException e) {
            return 0;
        }
        return i;
    }

    /**
     * Parses a String to an int safely
     *
     * @param s The String you want to parse
     * @return The int
     */
    public static int parseInt(String s) {
        int i;
        try {
            i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0;
        }
        return i;
    }

    /**
     * Parses an int to a String safely
     *
     * @param i The int you want to parse
     * @return The String
     * @deprecated Use {@link String#valueOf(int)} instead
     */
    @Deprecated
    public static String parseInt(int i) {
        new JLogger().deprecation();
        return String.valueOf(i);
    }

    /**
     * Parses a String Item notation to a Material
     * Examples:
     * - 1 (Material.STONE)
     * - 35:14 (Material.WOOL)
     *
     * @param item The Item notation
     * @return The Material
     */
    @SuppressWarnings("deprecation")
    public static Material parseMaterial(String item) {
        return item == null || item.isEmpty() ? Material.AIR : Material.getMaterial(parseInt(item.split(":")[0]));
    }

    /**
     * Parses a String Item notation to a Data value
     * Examples:
     * - 1 (0)
     * - 35:14 (14)
     *
     * @param item The Item notation
     * @return The Data value
     */
    public static int parseData(String item) {
        return item == null || item.isEmpty() || !item.contains(":") || item.endsWith(":") ? 0 : parseInt(item.split(":")[1]);
    }

    /**
     * Parses a String Item notation to a MaterialData
     * Examples:
     * - 1 (Material.STONE, 0)
     * - 35:14 (Material.WOOL, 14)
     *
     * @param item The Item notation
     * @return The MaterialData
     */
    @SuppressWarnings("deprecation")
    public static MaterialData parseMaterialData(String item) {
        return new MaterialData(parseMaterial(item), (byte) parseData(item));
    }

    /**
     * Parses an ItemStack from an Item
     *
     * @param item The Item
     * @return The ItemStack
     */
    public static ItemStack parseItemStack(String item) {
        if (item == null || item.isEmpty()) return new ItemStack(Material.AIR);
        String idAndData;
        idAndData = item.contains(" ") ? item.split(" ")[0] : item;
        JLibItem.Builder builder = new JLibItem.Builder().withType(parseMaterial(idAndData)).withAmount(1).withDurability((short) parseData(idAndData));
        String[] splitted = item.split(" ");
        JLogger jLogger = new JLogger();
        for (String node : splitted) {
            try {
                parseNode(node, builder);
            } catch (Exception e) {
                jLogger.log(ChatColor.RED + "Invalid node '" + node + "' for '" + item + "'!", JLogger.LogLevel.MINIMAL);
                e.printStackTrace();
            }
        }
        return builder.build().getItemStack();
    }

    /**
     * Parses a node to a JLibItem Builder
     *
     * @param node    The node
     * @param builder The Builder
     */
    private static void parseNode(String node, JLibItem.Builder builder) {
        String[] splitted = node.split(":");
        
        if(node.startsWith("unbreakable:")) builder.withUnbreakable(parseBoolean(node.replace("unbreakable:", "")));
        if (node.startsWith("amount:")) builder.withAmount(parseInt(node.replace("amount:", "")));
        if (node.startsWith("name:")) builder.withName(Placeholders.parse(node.replace("name:", "")).replace("_", " "));
        if (node.startsWith("lore:"))
            builder.withLore(Placeholders.parse(node.replace("lore:", "")).replace("_", " ").split("\\|"));
        if (node.startsWith("basecolor:"))
            builder.withBaseColor(DyeColor.valueOf(node.replace("basecolor:", "").toUpperCase()));
        if (node.startsWith("title:"))
            builder.withTitle(Placeholders.parse(node.replace("title:", "")).replace("_", " "));
        if (node.startsWith("author:"))
            builder.withAuthor(Placeholders.parse(node.replace("author:", "").replace("_", " ")));
        if (node.startsWith("page:"))
            builder.withPages(Placeholders.parse(node.replace("page:", "")).replace("_", " "));
        if (node.startsWith("power:")) builder.withPower(parseInt(node.replace("power:", "")));
        if (node.startsWith("color:")) builder.withColor(getColor(node.replace("color:", ""), "\\|"));
        if (node.startsWith("potiontype:")) {
            if (ReflectionAPI.verBiggerThan(1, 9))
                builder.withBasePotionData(new PotionData(PotionType.valueOf(node.replace("potiontype:", "").toUpperCase())));
            else builder.withMainEffect(PotionEffectType.getByName(node.replace("potiontype:", "").toUpperCase()));
        }
        if (node.startsWith("owner:")) builder.withOwner(node.replace("owner:", ""));
        if (node.startsWith("itemflag:"))
            builder.withItemFlags(ItemFlag.valueOf(node.replace("itemflag:", "").toUpperCase()));
        if (node.startsWith("entitytype:"))
            builder.withEntityType(EntityType.valueOf(node.replace("entitytype:", "").toUpperCase()));
        if (node.startsWith("enchantment_"))
            builder.withEnchantment(Enchantment.getByName(splitted[0].replace("enchantment_", "").toUpperCase()), parseInt(splitted[1]));
        if (splitted[0].startsWith("pattern_"))
            builder.withPatterns(new Pattern(DyeColor.valueOf(splitted[1].toUpperCase()), PatternType.valueOf(splitted[0].replace("pattern_", "").toUpperCase())));
        if (splitted[0].startsWith("fireworkeffect_")) {
            FireworkEffect.Type type = FireworkEffect.Type.valueOf(splitted[0].replace("fireworkeffect_", "").toUpperCase());
            boolean flicker = parseBoolean(splitted[1].split("\\|")[0]);
            boolean trail = parseBoolean(splitted[1].split("\\|")[1]);
            List<Color> colors = new ArrayList<>();
            for (String s : splitted[1].split("\\|")[2].split(",")) {
                colors.add(getColor(s, "\\."));
            }
            List<Color> fades = new ArrayList<>();
            for (String s : splitted[1].split("\\|")[3].split(",")) {
                fades.add(getColor(s, "\\."));
            }
            builder.withFireworkEffects(FireworkEffect.builder().with(type).flicker(flicker).trail(trail).withColor(colors).withFade(fades).build());
        }
        if (node.startsWith("potioneffect_"))
            builder.withCustomEffects(new PotionEffect(PotionEffectType.getByName(splitted[0].replace("potioneffect_", "").toUpperCase()), parseInt(splitted[1].split("\\|")[0]), parseInt(splitted[1].split("\\|")[1]), parseBoolean(splitted[1].split("\\|")[2]), parseBoolean(splitted[1].split("\\|")[3])));
    }

    /**
     * Returns a Color from a String
     *
     * @param rgb        The String
     * @param splitChars The Chars that split
     * @return The Color
     */
    private static Color getColor(String rgb, String splitChars) {
        return Color.fromRGB(parseInt(rgb.split(splitChars)[0]), parseInt(rgb.split(splitChars)[1]), parseInt(rgb.split(splitChars)[2]));
    }

    /**
     * Parses a String from an ItemStack
     *
     * @param item The ItemStack
     * @return The String
     */
    @SuppressWarnings("deprecation")
    public static String parseString(ItemStack item) {
        String s = String.valueOf(item.getTypeId());
        if (item.getDurability() != 0) s += ":" + item.getDurability();

        if (item.getAmount() > 1) s += " amount:" + item.getAmount();

        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta.hasDisplayName())
            s += " name:" + itemMeta.getDisplayName().replace(" ", "_").replace(String.valueOf(ChatColor.COLOR_CHAR), "&");

        if (itemMeta.hasLore()) {
            s += " lore:";
            for (String t : itemMeta.getLore()) {
                s += t.replace(" ", "_").replace(String.valueOf(ChatColor.COLOR_CHAR), "&");
                s += '|';
            }
            s = s.substring(0, s.length() - 1);
        }

        if (itemMeta instanceof BannerMeta)
            s += " basecolor:" + ((BannerMeta) itemMeta).getBaseColor();

        if (itemMeta instanceof BookMeta)
            s += " title:" + ((BookMeta) itemMeta).getTitle().replace(" ", "_").replace(String.valueOf(ChatColor.COLOR_CHAR), "&");

        if (itemMeta instanceof BookMeta)
            s += " author:" + ((BookMeta) itemMeta).getAuthor().replace(" ", "_").replace(String.valueOf(ChatColor.COLOR_CHAR), "&");

        if (itemMeta instanceof BookMeta) {
            for (String t : ((BookMeta) itemMeta).getPages()) {
                s += " page:" + t.replace(" ", "_").replace(String.valueOf(ChatColor.COLOR_CHAR), "&");
            }
        }

        if (itemMeta instanceof FireworkMeta)
            s += " power:" + ((FireworkMeta) itemMeta).getPower();

        if (itemMeta instanceof LeatherArmorMeta)
            s += " color:" + getString(((LeatherArmorMeta) itemMeta).getColor(), "|");

        if (itemMeta instanceof PotionMeta) {
            s += " potiontype:" + (ReflectionAPI.verBiggerThan(1, 9) ? ((PotionMeta) itemMeta).getBasePotionData().getType() : ((PotionMeta) itemMeta).getCustomEffects().get(0).getType());
        }
        if (itemMeta instanceof SkullMeta)
            s += " owner:" + ((SkullMeta) itemMeta).getOwner();

        if (ReflectionAPI.verBiggerThan(1, 8)) {
            for (Object i : itemMeta.getItemFlags()) {
                s += " itemflag:" + i;
            }
        }

        for (Map.Entry<Enchantment, Integer> enchantment : itemMeta.getEnchants().entrySet()) {
            s += " enchantment_" + enchantment.getKey().getName() + ':' + enchantment.getValue();
        }

        if (itemMeta instanceof BannerMeta) {
            for (Pattern pattern : ((BannerMeta) itemMeta).getPatterns()) {
                s += " pattern_" + pattern.getPattern() + ':' + pattern.getColor();
            }
        }

        if (itemMeta instanceof FireworkMeta) {
            for (FireworkEffect fireworkEffect : ((FireworkMeta) itemMeta).getEffects()) {
                s += " fireworkeffect_" + fireworkEffect.getType() + ':' + fireworkEffect.hasFlicker() + '|' + fireworkEffect.hasTrail() + '|';
                for (Color color : fireworkEffect.getColors()) {
                    s += getString(color, ".");
                    s += ',';
                }
                s = s.substring(0, s.length() - 2);
                s += '|';
                for (Color color : fireworkEffect.getFadeColors()) {
                    s += getString(color, ".");
                    s += ',';
                }
                s = s.substring(0, s.length() - 2);
            }
        }

        if (itemMeta instanceof PotionMeta) {
            for (PotionEffect potionEffect : ((PotionMeta) itemMeta).getCustomEffects()) {
                s += " potioneffect_" + potionEffect.getType().getName() + ':' + potionEffect.getDuration() + '|' + potionEffect.getAmplifier() + '|' + potionEffect.isAmbient() + '|' + potionEffect.hasParticles();
            }
        }

        return s;
    }

    /**
     * Returns a String from a Color
     *
     * @param color      The Color
     * @param splitChars The Chars that split
     * @return The String
     */
    private static String getString(Color color, String splitChars) {
        return color.getRed() + splitChars + color.getGreen() + splitChars + color.getBlue();
    }

    /**
     * Parses a StringList and a CustomItem to a Shaped Recipe
     * Examples:
     * - {264 0 264, 264 264 264, 264 264 264}, new ItemStack(Material.DIAMOND_CHESTPLATE) (Diamond Chestplate Recipe)
     *
     * @param recipe The StringList for the Recipe
     * @param result The ItemStack result
     * @return The ShapedRecipe
     */
    public static ShapedRecipe parseShapedRecipe(List<String> recipe, ItemStack result) {
        List<Character> chars = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i');
        if (recipe.size() < 3) {
            for (int a = 0; a < 3 - recipe.size(); a++) recipe.add("0 0 0");
        }
        ShapedRecipe shapedRecipe = new ShapedRecipe(result);
        shapedRecipe.shape("abc", "def", "ghi");
        int e = 0;
        for (String recipee : recipe) {
            String[] splitted = recipee.split(" ");
            for (String split : splitted) {
                if (parseMaterial(split) != Material.AIR)
                    shapedRecipe.setIngredient(chars.get(e++), parseMaterialData(split));
            }
        }
        return shapedRecipe;
    }
}
