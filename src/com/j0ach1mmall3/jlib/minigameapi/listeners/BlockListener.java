package com.j0ach1mmall3.jlib.minigameapi.listeners;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.minigameapi.MinigameAPI;
import com.j0ach1mmall3.jlib.minigameapi.arena.Arena;
import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.List;

/**
 * Created by j0ach1mmall3 on 16:19 5/09/2015 using IntelliJ IDEA.
 */
public class BlockListener implements Listener {
    private Main plugin;
    public BlockListener(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if(MinigameAPI.isInGame(p)) {
            Game game = MinigameAPI.getGame(p);
            Arena arena = game.getArena();
            List<Material> breakAble = game.getRuleSet().getBreakAble();
            if(!breakAble.contains(e.getBlock().getType()) && arena.isInArena(e.getBlock().getLocation())) {
                e.setCancelled(true);
            } else {
                arena.getRestorer().addBlock(e.getBlock().getLocation(), e.getBlock().getState());
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if(MinigameAPI.isInGame(p)) {
            Game game = MinigameAPI.getGame(p);
            Arena arena = game.getArena();
            List<Material> placeAble = game.getRuleSet().getPlaceAble();
            if(!placeAble.contains(e.getBlock().getType()) && arena.isInArena(e.getBlock().getLocation())) {
                e.setCancelled(true);
            } else {
                arena.getRestorer().addBlock(e.getBlock().getLocation(), e.getBlock().getState());
            }
        }
    }
}
