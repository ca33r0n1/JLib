package com.j0ach1mmall3.jlib.methods;

import com.j0ach1mmall3.jlib.inventory.CustomItem;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

import java.util.Arrays;
import java.util.List;

public final class Parsing {
    @Deprecated
	public static int parseString(String s){
        return parseInt(s);
	}

    public static double parseDouble(String s) {
        double d;
        try {
            d = Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return 0;
        }
        return d;
    }

    public static long parseLong(String s) {
        long l;
        try {
            l = Long.parseLong(s);
        } catch (NumberFormatException e) {
            return 0;
        }
        return l;
    }

    public static float parseFloat(String s) {
        float f;
        try {
            f = Float.parseFloat(s);
        } catch (NumberFormatException e) {
            return 0;
        }
        return f;
    }

    public static boolean parseBoolean(String s) {
        boolean b;
        try {
            b = Boolean.parseBoolean(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return b;
    }

    public static byte parseByte(String s) {
        byte i;
        try {
            i = Byte.parseByte(s);
        } catch (NumberFormatException e) {
            return 0;
        }
        return i;
    }

    public static short parseShort(String s) {
        short i;
        try {
            i = Short.parseShort(s);
        } catch (NumberFormatException e) {
            return 0;
        }
        return i;
    }

    public static int parseInt(String s) {
        int i;
        try {
            i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0;
        }
        return i;
    }

    @Deprecated
	public static String parseInt(int i){
		return String.valueOf(i);
	}
	
	@SuppressWarnings("deprecation")
    public static Material parseMaterial(String item){
        if(item == null) return Material.AIR;
        if(item.equals("")) return Material.AIR;
		return Material.getMaterial(parseString(item.split(":")[0]));
	}

    public static int parseData(String item){
        if(item == null) return 0;
		if(item.equals("")) return 0;
		if(!item.contains(":")) return 0;
		if(item.endsWith(":")) return 0;
		return Integer.valueOf(item.split(":")[1]);
	}

    @SuppressWarnings("deprecation")
    public static ShapedRecipe parseShapedRecipe(List<String> recipe, CustomItem result) {
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
                if(parseMaterial(split) != Material.AIR) shapedRecipe.setIngredient(chars.get(e), parseMaterial(split), parseData(split));
                e++;
            }
        }
        return shapedRecipe;
    }
}
