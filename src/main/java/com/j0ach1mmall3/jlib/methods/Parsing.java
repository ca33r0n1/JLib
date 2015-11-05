package com.j0ach1mmall3.jlib.methods;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.material.MaterialData;

import java.util.Arrays;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since Unknown
 */
public final class Parsing {
    /**
     * Parses a String to an int safely
     * @param string The String you want to parse
     * @return The int
     * @deprecated Replaced by {@link Parsing#parseInt(String)}
     */
    @Deprecated
	public static int parseString(String string){
        return parseInt(string);
	}

    /**
     * Parses a String to a double safely
     * @param string The String you want to parse
     * @return The double
     */
    public static double parseDouble(String string) {
        double d;
        try {
            d = Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return 0;
        }
        return d;
    }

    /**
     * Parses a String to a long safely
     * @param string The String you want to parse
     * @return The long
     */
    public static long parseLong(String string) {
        long l;
        try {
            l = Long.parseLong(string);
        } catch (NumberFormatException e) {
            return 0;
        }
        return l;
    }

    /**
     * Parses a String to a float safely
     * @param string The String you want to parse
     * @return The float
     */
    public static float parseFloat(String string) {
        float f;
        try {
            f = Float.parseFloat(string);
        } catch (NumberFormatException e) {
            return 0;
        }
        return f;
    }

    /**
     * Parses a String to a boolean safely
     * @param string The String you want to parse
     * @return The boolean
     */
    public static boolean parseBoolean(String string) {
        boolean b;
        try {
            b = Boolean.parseBoolean(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return b;
    }

    /**
     * Parses a String to a byte safely
     * @param string The String you want to parse
     * @return The byte
     */
    public static byte parseByte(String string) {
        byte i;
        try {
            i = Byte.parseByte(string);
        } catch (NumberFormatException e) {
            return 0;
        }
        return i;
    }

    /**
     * Parses a String to a short safely
     * @param string The String you want to parse
     * @return The short
     */
    public static short parseShort(String string) {
        short i;
        try {
            i = Short.parseShort(string);
        } catch (NumberFormatException e) {
            return 0;
        }
        return i;
    }

    /**
     * Parses a String to an int safely
     * @param string The String you want to parse
     * @return The int
     */
    public static int parseInt(String string) {
        int i;
        try {
            i = Integer.parseInt(string);
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
        return (item == null || item.isEmpty())?Material.AIR:Material.getMaterial(parseString(item.split(":")[0]));
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
		return (item == null || item.isEmpty() || !item.contains(":") || item.endsWith(":"))?0:Integer.valueOf(item.split(":")[1]);
	}

    /**
     * Parses a String Item notation to a MaterialData
     * Examples:
     * - 1 (Material.STONE, 0)
     * - 35:14 (Material.WOOL, 14)
     * @param item The Item notation
     * @return The MaterialData
     * @see MaterialData
     */
    @SuppressWarnings("deprecation")
    public static MaterialData parseMaterialData(String item) {
        return new MaterialData(parseMaterial(item), (byte) parseData(item));
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