package com.j0ach1mmall3.jlib.minigameapi.listeners;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.minigameapi.arena.Arena;
import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import com.j0ach1mmall3.jlib.minigameapi.game.GameRuleSet;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.material.MaterialData;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/09/15
 */
public class BlockListener implements Listener {
    private final Main plugin;

    /**
     * Initialises the BlockListener
     * @param plugin Main plugin
     */
    public BlockListener(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * The BlockBreakEvent Listener
     * @param e The BlockBreakEvent
     * @see Game
     * @see Arena
     * @see GameRuleSet
     */
    @EventHandler
    @SuppressWarnings("deprecation")
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if(this.plugin.getApi().isInGame(p)) {
            Game game = this.plugin.getApi().getGame(p);
            Arena arena = game.getArena();
            List<MaterialData> breakAble = game.getRuleSet().getBreakAble();
            if(!breakAble.contains(new MaterialData(e.getBlock().getType(), e.getBlock().getData())) && arena.getSelection().isInArena(e.getBlock().getLocation())) {
                e.setCancelled(true);
            } else {
                arena.getRestorer().addBlock(e.getBlock().getLocation(), e.getBlock().getState());
            }
        }
    }

    /**
     * The BlockPlaceEvent Listener
     * @param e The BlockPlaceEvent
     * @see Game
     * @see Arena
     * @see GameRuleSet
     */
    @EventHandler
    @SuppressWarnings("deprecation")
    public void onBlockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if(this.plugin.getApi().isInGame(p)) {
            Game game = this.plugin.getApi().getGame(p);
            Arena arena = game.getArena();
            List<MaterialData> placeAble = game.getRuleSet().getPlaceAble();
            if(!placeAble.contains(new MaterialData(e.getBlock().getType(), e.getBlock().getData())) && arena.getSelection().isInArena(e.getBlock().getLocation())) {
                e.setCancelled(true);
            } else {
                arena.getRestorer().addBlock(e.getBlock().getLocation(), e.getBlock().getState());
            }
        }
    }
}
