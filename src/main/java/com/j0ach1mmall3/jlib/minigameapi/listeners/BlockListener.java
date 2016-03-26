package com.j0ach1mmall3.jlib.minigameapi.listeners;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.minigameapi.arena.Arena;
import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import com.j0ach1mmall3.jlib.minigameapi.game.state.GameRuleSet;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.material.MaterialData;

import java.util.Set;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/09/15
 */
public final class BlockListener implements Listener {
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
     */
    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("deprecation")
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if(this.plugin.getApi().isInGame(p)) {
            Game game = this.plugin.getApi().getGame(p);
            Arena arena = game.getMap().getArena();
            Set<MaterialData> breakable = game.getCurrGameState().getRuleSet().getBreakable();
            if(breakable.equals(GameRuleSet.ALL_MATERIAL_DATAS) || (breakable.contains(new MaterialData(e.getBlock().getType(), e.getBlock().getData())) && arena.getSelection().isInArena(e.getBlock().getLocation()))) arena.getRestorer().addBlock(e.getBlock().getLocation(), e.getBlock().getState());
            else e.setCancelled(true);
        }
    }

    /**
     * The BlockPlaceEvent Listener
     * @param e The BlockPlaceEvent
     */
    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("deprecation")
    public void onBlockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if(this.plugin.getApi().isInGame(p)) {
            Game game = this.plugin.getApi().getGame(p);
            Arena arena = game.getMap().getArena();
            Set<MaterialData> placeable = game.getCurrGameState().getRuleSet().getPlaceable();
            if(placeable.equals(GameRuleSet.ALL_MATERIAL_DATAS) || (placeable.contains(new MaterialData(e.getBlock().getType(), e.getBlock().getData())) && arena.getSelection().isInArena(e.getBlock().getLocation()))) arena.getRestorer().addBlock(e.getBlock().getLocation(), e.getBlock().getState());
            else e.setCancelled(true);
        }
    }

    /**
     * The BlockExplodeEvent Listener
     * @param e The BlockExplodeEvent
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockExplode(BlockExplodeEvent e) {
        for(Game game : this.plugin.getApi().getGames()) {
            if(game.getMap().getWorld().getName().equals(e.getBlock().getWorld().getName()) && !game.getCurrGameState().getRuleSet().isExplosionDamage()) e.setCancelled(true);
            else {
                for(Block b : e.blockList()) {
                    game.getMap().getArena().getRestorer().addBlock(b.getLocation(), b.getState());
                }
            }
        }
    }
}
