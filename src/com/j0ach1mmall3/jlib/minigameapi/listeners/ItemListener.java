package com.j0ach1mmall3.jlib.minigameapi.listeners;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.minigameapi.MinigameAPI;
import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * Created by j0ach1mmall3 on 8:57 6/09/2015 using IntelliJ IDEA.
 */
public class ItemListener implements Listener {
    private Main plugin;
    public ItemListener(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if(MinigameAPI.isInGame(p)) {
            Game game = MinigameAPI.getGame(p);
            if(game.getRuleSet().getDropAble().contains(e.getItemDrop().getItemStack().getType())) e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        Player p = e.getPlayer();
        if(MinigameAPI.isInGame(p)) {
            Game game = MinigameAPI.getGame(p);
            if(game.getRuleSet().getPickupAble().contains(e.getItem().getItemStack().getType())) e.setCancelled(true);
        }
    }
}
