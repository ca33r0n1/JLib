package com.j0ach1mmall3.jlib.inventory;

import com.j0ach1mmall3.jlib.events.PlayerOpenGUIEvent;
import com.j0ach1mmall3.jlib.methods.General;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public class GUI {
    private Inventory inventory;

    /**
     * Constructs a new GUI instance
     * @param name The name of the GUI
     * @param items The items in the GUI
     */
    public GUI(String name, List<ItemStack> items) {
        this.inventory = Bukkit.createInventory(null, General.roundUp(items.size(), 9), ChatColor.translateAlternateColorCodes('&', name));
        for(ItemStack is : items) {
            this.inventory.addItem(is);
        }
    }

    /**
     * Constructs a new GUI instance
     * @param name The name of the GUI
     * @param items The items in the GUI
     */
    public GUI(String name, ItemStack... items) {
        this(name, Arrays.asList(items));
    }

    /**
     * Constructs a new GUI instance
     * @param name The name of the GUI
     * @param size The size of the GUI
     */
    public GUI(String name, int size) {
        this.inventory = Bukkit.createInventory(null, General.roundUp(size, 9), ChatColor.translateAlternateColorCodes('&', name));
    }

    /**
     * Constructs a new GUI instance
     * @param gui The parent GUI
     */
    public GUI(GUI gui) {
        this.inventory = gui.getInventory();
    }

    /**
     * Returns the name of the GUI
     * @return The name
     */
    public String getName() {
        return this.inventory.getName();
    }

    /**
     * Returns the contents of the GUI
     * @return The contents
     */
    public ItemStack[] getContents() {
        return this.inventory.getContents();
    }

    /**
     * Sets the contents of the GUI
     * @param items The new contents
     */
    public void setContents(ItemStack... items) {
        this.inventory.clear();
        this.inventory.addItem(items);
    }

    /**
     * Returns the size of the GUI
     * @return The size
     */
    public int getSize() {
        return this.inventory.getSize();
    }

    /**
     * Sets an ItemStack at a position in the GUI
     * @param position The position
     * @param itemStack The item
     */
    public void setItem(int position, ItemStack itemStack) {
        if(position < 0) return;
        this.inventory.setItem(position, itemStack);
    }

    /**
     * Sets a GuiItem in the GUI
     * @param item The GuiItem
     * @see GuiItem
     */
    public void setItem(GuiItem item) {
        this.setItem(item.getPosition(), item.getItem());
    }

    /**
     * Returns the Inventory represented by this GUI
     * @return The Inventory
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Sets the Inventory represented by this GUI
     * @param inventory The new Inventory
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Opens this GUI for a player
     * @param player The player
     */
    public void open(Player player) {
        PlayerOpenGUIEvent event = new PlayerOpenGUIEvent(player, this);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled()) return;
        this.inventory = event.getGui().getInventory();
        player.openInventory(this.inventory);
    }

    /**
     * Determines whether a player has legitimately clicked in this GUI
     * @param event The InventoryClickEvent
     * @return Wether the player has clicked in the GUI
     */
    public boolean hasClicked(InventoryClickEvent event) {
        if(event.getView().getTopInventory() != null && event.getView().getTopInventory().getName().equals(this.inventory.getName())){
            if(event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR){
                if(event.getRawSlot() > event.getInventory().getSize()){
                    event.setCancelled(true);
                    return false;
                }
                return true;
            }
        }
        return false;
    }
}
