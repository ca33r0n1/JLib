package com.j0ach1mmall3.jlib.minigameapi.map;

import com.j0ach1mmall3.jlib.methods.Parsing;
import com.j0ach1mmall3.jlib.methods.Random;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/02/16
 */
public final class RestockChest {
    private final Location location;
    private final java.util.Map<ItemStack, Integer> chances;

    /**
     * Constructs a new RestockChest
     * @param location The Location of this RestockChest
     * @param chances The chances of each ItemStack (in promille, / 1000)
     */
    public RestockChest(Location location, java.util.Map<ItemStack, Integer> chances) {
        this.location = location;
        this.chances = chances;
    }

    /**
     * Constructs a new RestockChest
     * @param location The Location of this RestockChest
     * @param items The ItemStacks
     * @param chances The chances of each ItemStack (in promille, / 1000)
     */
    public RestockChest(Location location, String[] items, Integer[] chances) {
        this.location = location;
        this.chances = new HashMap<>();
        for(int i = 0;i < items.length;i++) {
            this.chances.put(Parsing.parseItemStack(items[i]), chances[i]);
        }
    }

    /**
     * Returns the Location of this RestockChest
     * @return The Location
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Restocks this RestockChest
     */
    public void restock() {
        BlockState blockState = this.location.getWorld().getBlockAt(this.location).getState();
        if(blockState instanceof Chest) {
            Chest chest = (Chest) blockState;
            for(Map.Entry<ItemStack, Integer> entry : this.chances.entrySet()) {
                if(entry.getValue() <= Random.getInt(1, 1000)) chest.getBlockInventory().setItem(Random.getInt(0, 26), entry.getKey());
            }
        }
    }

    /**
     * Returns whether this RestockChest equals another RestockChest
     * @param restockChest The other RestockChest
     * @return Whether this RestockChest equals another RestockChest
     */
    public boolean equals(RestockChest restockChest) {
        return this.location.equals(restockChest.location) && this.chances.equals(restockChest.chances);
    }
}
