package com.j0ach1mmall3.jlib.methods;

import com.j0ach1mmall3.jlib.integration.Placeholders;
import com.j0ach1mmall3.jlib.logging.JLogger;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * @param s The String you want to parse
     * @return The boolean
     */
    public static boolean parseBoolean(String s) {
        return Boolean.parseBoolean(s);
    }

    /**
     * Parses a String to a byte safely
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
     * @param i The int you want to parse
     * @return The String
     * @deprecated Use {@link String#valueOf(int)} instead
     */
    @Deprecated
    public static String parseInt(int i){
        new JLogger().deprecation();
        return String.valueOf(i);
    }

    /**
     * Parses a String Item notation to a Material
     * Examples:
     * - 1 (Material.STONE)
     * - 35:14 (Material.WOOL)
     * @param item The Item notation
     * @return The Material
     */
    @SuppressWarnings("deprecation")
    public static Material parseMaterial(String item){
        return (item == null || item.isEmpty()) ? Material.AIR : Material.getMaterial(parseInt(item.split(":")[0]));
    }

    /**
     * Parses a String Item notation to a Data value
     * Examples:
     * - 1 (0)
     * - 35:14 (14)
     * @param item The Item notation
     * @return The Data value
     */
    public static int parseData(String item){
        return (item == null || item.isEmpty() || !item.contains(":") || item.endsWith(":")) ? 0 : parseInt(item.split(":")[1]);
    }

    /**
     * Parses a String Item notation to a MaterialData
     * Examples:
     * - 1 (Material.STONE, 0)
     * - 35:14 (Material.WOOL, 14)
     * @param item The Item notation
     * @return The MaterialData
     */
    @SuppressWarnings("deprecation")
    public static MaterialData parseMaterialData(String item) {
        return new MaterialData(parseMaterial(item), (byte) parseData(item));
    }

    /**
     * Parses an ItemStack from an Item
     * @param item The Item
     * @return The ItemStack
     */
    public static ItemStack parseItemStack(String item) {
        if(item == null || item.isEmpty()) return new ItemStack(Material.AIR);
        String idAndData;
        if(item.contains(" ")) idAndData = item.split(" ")[0];
        else idAndData = item;
        ItemStack itemStack = new ItemStack(parseMaterial(idAndData), 1, (byte) parseData(idAndData));
        String[] splitted = item.split(" ");
        JLogger jLogger = new JLogger();
        for(String node : splitted) {
            try {
                itemStack.setItemMeta(parseNode(node, itemStack));
            } catch (Exception e) {
                jLogger.log(ChatColor.RED + "Invalid node '" + node + "' for '" + item + "'!", JLogger.LogLevel.MINIMAL);
            }
        }
        return itemStack;
    }

    /**
     * Parses a node to ItemMeta
     * @param node The node
     * @param itemStack The ItemStack
     * @return The updated ItemMeta
     */
    @SuppressWarnings("deprecation")
    private static ItemMeta parseNode(String node, ItemStack itemStack) {
        String[] splitted = node.split(":");
        ItemMeta itemMeta = itemStack.getItemMeta();
        if(node.startsWith("amount:")) itemStack.setAmount(parseInt(node.replace("amount:","")));
        if(node.startsWith("name:")) itemMeta.setDisplayName(Placeholders.parse(node.replace("name:", "")).replace("_", " "));
        if(node.startsWith("lore:")) itemMeta.setLore(Arrays.asList(Placeholders.parse(node.replace("lore:", "")).replace("_", " ").split("\\|")));
        if(node.startsWith("basecolor:")) ((org.bukkit.inventory.meta.BannerMeta) itemMeta).setBaseColor(DyeColor.valueOf(node.replace("basecolor:", "").toUpperCase()));
        if(node.startsWith("title:")) ((org.bukkit.inventory.meta.BookMeta) itemMeta).setTitle(Placeholders.parse(node.replace("title:", "")).replace("_", " "));
        if(node.startsWith("author:")) ((org.bukkit.inventory.meta.BookMeta) itemMeta).setAuthor(node.replace("author:", "").replace("_", " "));
        if(node.startsWith("page:")) ((org.bukkit.inventory.meta.BookMeta) itemMeta).addPage(Placeholders.parse(node.replace("page:", "")).replace("_", " "));
        if(node.startsWith("power:")) ((org.bukkit.inventory.meta.FireworkMeta) itemMeta).setPower(parseInt(node.replace("power:", "")));
        if(node.startsWith("color:")) ((org.bukkit.inventory.meta.LeatherArmorMeta) itemMeta).setColor(getColor(node.replace("color:", ""), "\\|"));
        if(node.startsWith("potiontype:")) {
            if(ReflectionAPI.verBiggerThan(1, 9)) ((org.bukkit.inventory.meta.PotionMeta) itemMeta).setBasePotionData(new org.bukkit.potion.PotionData(PotionType.valueOf(node.replace("potiontype:", "").toUpperCase())));
            else ((org.bukkit.inventory.meta.PotionMeta) itemMeta).setMainEffect(PotionEffectType.getByName(node.replace("potiontype:", "").toUpperCase()));
        }
        if(node.startsWith("owner:")) ((org.bukkit.inventory.meta.SkullMeta) itemMeta).setOwner(node.replace("owner:", ""));
        if(node.startsWith("itemflag:")) itemMeta.addItemFlags(org.bukkit.inventory.ItemFlag.valueOf(node.replace("itemflag:", "").toUpperCase()));
        if(node.startsWith("enchantment_")) {
            if(itemMeta instanceof org.bukkit.inventory.meta.EnchantmentStorageMeta) ((org.bukkit.inventory.meta.EnchantmentStorageMeta) itemMeta).addStoredEnchant(Enchantment.getByName(node.replace("enchantment_", "").toUpperCase()), parseInt(splitted[1]), true);
            else itemMeta.addEnchant(Enchantment.getByName(splitted[0].replace("enchantment_", "").toUpperCase()), parseInt(splitted[1]), true);
        }
        if(splitted[0].startsWith("pattern_")) ((org.bukkit.inventory.meta.BannerMeta) itemMeta).addPattern(new org.bukkit.block.banner.Pattern(DyeColor.valueOf(splitted[1].toUpperCase()), org.bukkit.block.banner.PatternType.valueOf(splitted[0].replace("pattern_", "").toUpperCase())));
        if(splitted[0].startsWith("fireworkeffect_")) {
            org.bukkit.FireworkEffect.Type type = org.bukkit.FireworkEffect.Type.valueOf(splitted[0].replace("fireworkeffect_", "").toUpperCase());
            boolean flicker = parseBoolean(splitted[1].split("\\|")[0]);
            boolean trail = parseBoolean(splitted[1].split("\\|")[1]);
            List<Color> colors = new ArrayList<>();
            for(String s : splitted[1].split("\\|")[2].split(",")) {
                colors.add(getColor(s, "\\."));
            }
            List<Color> fades = new ArrayList<>();
            for(String s : splitted[1].split("\\|")[3].split(",")) {
                fades.add(getColor(s, "\\."));
            }
            if(itemMeta instanceof org.bukkit.inventory.meta.FireworkEffectMeta) ((org.bukkit.inventory.meta.FireworkEffectMeta) itemMeta).setEffect(org.bukkit.FireworkEffect.builder().with(type).flicker(flicker).trail(trail).withColor(colors).withFade(fades).build());
            else ((org.bukkit.inventory.meta.FireworkMeta) itemMeta).addEffect(org.bukkit.FireworkEffect.builder().with(type).flicker(flicker).trail(trail).withColor(colors).withFade(fades).build());
        }
        if(node.startsWith("potioneffect_")) ((org.bukkit.inventory.meta.PotionMeta) itemMeta).addCustomEffect(new PotionEffect(PotionEffectType.getByName(splitted[0].replace("potioneffect_", "").toUpperCase()), parseInt(splitted[1].split("\\|")[0]), parseInt(splitted[1].split("\\|")[1]), parseBoolean(splitted[1].split("\\|")[2]), parseBoolean(splitted[1].split("\\|")[3])), true);

        return itemMeta;
    }

    /**
     * Returns a Color from a String
     * @param rgb The String
     * @param splitChars The Chars that split
     * @return The Color
     */
    private static Color getColor(String rgb, String splitChars) {
        return Color.fromRGB(parseInt(rgb.split(splitChars)[0]), parseInt(rgb.split(splitChars)[1]), parseInt(rgb.split(splitChars)[2]));
    }

    /**
     * Parses a StringList and a CustomItem to a Shaped Recipe
     * Examples:
     * - {264 0 264, 264 264 264, 264 264 264}, new ItemStack(Material.DIAMOND_CHESTPLATE) (Diamond Chestplate Recipe)
     * @param recipe The StringList for the Recipe
     * @param result The ItemStack result
     * @return The ShapedRecipe
     */
    public static ShapedRecipe parseShapedRecipe(List<String> recipe, ItemStack result) {
        List<Character> chars = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i');
        if(recipe.size() < 3) {
            for(int a=0;a<3-recipe.size();a++) recipe.add("0 0 0");
        }
        ShapedRecipe shapedRecipe = new ShapedRecipe(result);
        shapedRecipe.shape("abc", "def", "ghi");
        int e=0;
        for(String recipee : recipe) {
            String[] splitted = recipee.split(" ");
            for(String split : splitted) {
                if(parseMaterial(split) != Material.AIR) shapedRecipe.setIngredient(chars.get(e), parseMaterialData(split));
                e++;
            }
        }
        return shapedRecipe;
    }
}
