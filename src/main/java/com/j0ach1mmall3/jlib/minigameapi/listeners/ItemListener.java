package com.j0ach1mmall3.jlib.minigameapi.listeners;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import com.j0ach1mmall3.jlib.minigameapi.game.GameRuleSet;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 6/09/15
 */
public class ItemListener implements Listener {
    private final Main plugin;

    /**
     * Initialises the ItemListener
     * @param plugin Main plugin
     */
    public ItemListener(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * The PlayerDropItemEvent Listener
     * @param e The PlayerDropItemEvent
     * @see Game
     * @see GameRuleSet
     */
    @EventHandler
    @SuppressWarnings("deprecation")
    public void onDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if(this.plugin.getApi().isInGame(p)) {
            Game game = this.plugin.getApi().getGame(p);
            if(game.getRuleSet().getDropAble().contains(e.getItemDrop().getItemStack().getData())) e.setCancelled(true);
        }
    }

    /**
     * The PlayerPickupItemEvent Listener
     * @param e The PlayerPickupItemEvent
     * @see Game
     * @see GameRuleSet
     */
    @EventHandler
    @SuppressWarnings("deprecation")
    public void onPickup(PlayerPickupItemEvent e) {
        Player p = e.getPlayer();
        if(this.plugin.getApi().isInGame(p)) {
            Game game = this.plugin.getApi().getGame(p);
            if(game.getRuleSet().getPickupAble().contains(e.getItem().getItemStack().getData())) e.setCancelled(true);
        }
    }
}
