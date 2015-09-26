package com.j0ach1mmall3.jlib.inventory;

import com.j0ach1mmall3.jlib.integration.Placeholders;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by j0ach1mmall3 on 14:05 19/08/2015 using IntelliJ IDEA.
 */
public class GUI {
    private String name;
    private List<ItemStack> items = new ArrayList<>();
    private int size;
    private Inventory inventory;

    public GUI(String name, ItemStack... items) {
        this.name = name;
        for(ItemStack is : items) {
            this.items.add(is);
        }
        this.size = roundUp(items.length, 9);
        this.inventory = Bukkit.createInventory(null, size, Placeholders.parse(name, null));
    }

    public GUI(String name, int size) {
        this.name = name;
        this.items = null;
        this.size = roundUp(size, 9);
        this.inventory = Bukkit.createInventory(null, size, Placeholders.parse(name, null));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemStack> getItems() {
        return items;
    }

    public void setItems(ItemStack... items) {
        this.items.clear();
        for(ItemStack is : items) {
            this.items.add(is);
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private int roundUp(int from, int to) {
        return (from + (to-1)) / to * to;
    }

    public void setItem(int position, ItemStack item) {
        this.inventory.setItem(position, item);
    }

    public void open(Player p) {
        if(items != null) {
            for(int a=0;a<items.size();a++){
                if(items.get(a) == null){
                    inventory.setItem(a, new ItemStack(Material.AIR));
                } else {
                    inventory.setItem(a, items.get(a));
                }
            }
        }
        p.openInventory(inventory);
    }

    public boolean hasClicked(InventoryClickEvent e) {
        if(e.getView().getTopInventory() != null){
            if(e.getView().getTopInventory().getName().equalsIgnoreCase(Placeholders.parse(name, (Player) e.getWhoClicked()))){
                if(e.getCurrentItem() != null){
                    if(e.getCurrentItem().getType() != Material.AIR){
                        if(e.getRawSlot() > e.getInventory().getSize()){
                            e.setCancelled(true);
                            return false;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
