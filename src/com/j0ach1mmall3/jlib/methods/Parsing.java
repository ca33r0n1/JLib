package com.j0ach1mmall3.jlib.methods;

import com.j0ach1mmall3.jlib.inventory.CustomItem;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

import java.util.Arrays;
import java.util.List;

public class Parsing {
	public static int parseString(String s){
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
