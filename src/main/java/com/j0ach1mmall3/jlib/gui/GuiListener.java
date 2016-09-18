package com.j0ach1mmall3.jlib.gui;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.gui.events.GuiClickEvent;
import com.j0ach1mmall3.jlib.gui.events.GuiCloseEvent;
import com.j0ach1mmall3.jlib.gui.events.GuiOpenEvent;
import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
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
    private final Map<Player, GuiPage> guiPages = new HashMap<>();

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
        this.guiPages.put(e.getPlayer(), e.getGuiPage());
    }

    /**
     * The InventoryClickEvent listener
     * @param e The InventoryClickEvent
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(!this.guis.containsKey(p) || !this.guiPages.containsKey(p)) return;

        Gui gui = this.guis.get(p);
        GuiPage guiPage = this.guiPages.get(p);
        if(e.getView().getTopInventory() != null && e.getView().getTopInventory().getName().equals(guiPage.getName()) && e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
            e.setCancelled(true);
            if(e.getRawSlot() <= e.getInventory().getSize()) {
                GuiClickEvent guiClickEvent = new GuiClickEvent(p, gui, guiPage, e);
                Bukkit.getPluginManager().callEvent(guiClickEvent);
                if(!guiClickEvent.isCancelled()) {
                    CallbackHandler<GuiClickEvent> guiClickHandler = guiPage.getItems().get(e.getSlot()).getGuiClickHandler();
                    if(guiClickHandler != null) guiClickHandler.callback(guiClickEvent);
                }
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
        if(!this.guis.containsKey(p) || !this.guiPages.containsKey(p)) return;

        Gui gui = this.guis.get(p);
        GuiPage guiPage = this.guiPages.get(p);
        GuiCloseEvent guiCloseEvent = new GuiCloseEvent(p, gui, guiPage);
        Bukkit.getPluginManager().callEvent(guiCloseEvent);
        if(guiCloseEvent.isCancelled()) gui.open(p, gui.getGuiPages().indexOf(guiPage));
    }
}
