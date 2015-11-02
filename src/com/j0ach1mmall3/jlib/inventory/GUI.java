package com.j0ach1mmall3.jlib.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by j0ach1mmall3 on 14:05 19/08/2015 using IntelliJ IDEA.
 */
public class GUI {
    private Inventory inventory;

    @SuppressWarnings("OverloadedVarargsMethod")
    public GUI(String name, ItemStack... items) {
        this.inventory = Bukkit.createInventory(null, roundUp(items.length, 9), ChatColor.translateAlternateColorCodes('&', name));
        this.inventory.addItem(items);
    }

    public GUI(String name, List<ItemStack> items) {
        this.inventory = Bukkit.createInventory(null, roundUp(items.size(), 9), ChatColor.translateAlternateColorCodes('&', name));
        for(ItemStack is : items) {
            this.inventory.addItem(is);
        }
    }

    public GUI(String name, int size) {
        this.inventory = Bukkit.createInventory(null, size, ChatColor.translateAlternateColorCodes('&', name));
    }

    public String getName() {
        return this.inventory.getName();
    }

    public ItemStack[] getContents() {
        return this.inventory.getContents();
    }

    public void setContents(ItemStack... items) {
        this.inventory.clear();
        this.inventory.addItem(items);
    }

    public int getSize() {
        return this.inventory.getSize();
    }

    private int roundUp(int from, int to) {
        return (from + (9 -1)) / 9 * 9;
    }

    public void setItem(int position, ItemStack item) {
        if(position < 0) return;
        this.inventory.setItem(position, item);
    }

    public void setItem(GuiItem item) {
        if(item.getPosition() < 0) return;
        this.inventory.setItem(item.getPosition(), item.getItem()); }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void open(Player p) {
        p.openInventory(inventory);
    }

    public boolean hasClicked(InventoryClickEvent e) {
        if(e.getView().getTopInventory() != null){
            if(e.getView().getTopInventory().getName().equals(this.inventory.getName())){
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
