package com.j0ach1mmall3.jlib.gui;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.gui.events.GuiClickEvent;
import com.j0ach1mmall3.jlib.gui.events.GuiCloseEvent;
import com.j0ach1mmall3.jlib.gui.events.GuiOpenEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 2/09/2016
 */
public final class GuiListener implements Listener {
    private final Map<Player, Gui> guis = new HashMap<>();

    /**
     * Constructs a new GuiListener
     * @param plugin The Main plugin
     */
    public GuiListener(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * The GuiOpenEvent listener
     * @param e The GuiOpenEvent
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onGuiOpen(GuiOpenEvent e) {
        this.guis.put(e.getPlayer(), e.getGui());
    }

    /**
     * The InventoryClickEvent listener
     * @param e The InventoryClickEvent
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(!this.guis.containsKey(p)) return;

        Gui gui = this.guis.get(p);
        if(e.getView().getTopInventory() != null && e.getView().getTopInventory().getName().equals(gui.getName())) {
            if(e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
                if(e.getRawSlot() > e.getInventory().getSize()) e.setCancelled(true);

                GuiClickEvent guiClickEvent = new GuiClickEvent(p, gui, e);
                Bukkit.getPluginManager().callEvent(guiClickEvent);
                if(guiClickEvent.isCancelled()) e.setCancelled(true);
            }
        }
    }

    /**
     * The InventoryCloseEvent listener
     * @param e The InventoryCloseEvent
     */
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        if(!this.guis.containsKey(p)) return;

        Gui gui = this.guis.get(p);
        GuiCloseEvent guiCloseEvent = new GuiCloseEvent(p, gui);
        Bukkit.getPluginManager().callEvent(guiCloseEvent);
        if(guiCloseEvent.isCancelled()) gui.open(p);
    }
}
