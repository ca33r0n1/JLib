package com.j0ach1mmall3.jlib.minigameapi.listeners;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.minigameapi.classes.ClassProperties;
import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import com.j0ach1mmall3.jlib.minigameapi.team.TeamProperties;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/02/16
 */
public final class InventoryListener implements Listener {
    private final Main plugin;

    /**
     * Initialises the InventoryListener
     * @param plugin Main plugin
     */
    public InventoryListener(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * The InventoryClickEvent Listener
     * @param e The InventoryClickEvent
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClick(InventoryClickEvent e) {
        for(Game game : this.plugin.getApi().getGames()) {
            TeamProperties teamProperties = game.getTeamProperties();
            if(teamProperties != null) {
                if(!teamProperties.isMoveSelectItem()) {
                    if(teamProperties.getTeamSelectItem().isSimilar(e.getCurrentItem())) {
                        e.setCancelled(true);
                        return;
                    }
                }
            }

            ClassProperties classProperties = game.getClassProperties();
            if(classProperties != null) {
                if(!classProperties.isMoveSelectItem()) {
                    if(classProperties.getClassSelectItem().isSimilar(e.getCurrentItem())) {
                        e.setCancelled(true);
                        return;
                    }
                }
            }
        }
    }
}