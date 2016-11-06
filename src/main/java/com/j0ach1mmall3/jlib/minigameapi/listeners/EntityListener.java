package com.j0ach1mmall3.jlib.minigameapi.listeners;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.hanging.HangingBreakEvent;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/09/15
 */
public final class EntityListener implements Listener {
    private final Main plugin;

    /**
     * Initialises the EntityListener
     *
     * @param plugin Main plugin
     */
    public EntityListener(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * The EntityDamageByEntityEvent Listener
     *
     * @param e The EntityDamageByEntityEvent
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            if (this.plugin.getApi().isInGame(p)) {
                Game game = this.plugin.getApi().getGame(p);
                if (!game.getCurrGameState().getRuleSet().getDamagable().contains(e.getEntity().getType()))
                    e.setCancelled(true);
            }
        }
    }

    /**
     * The EntityExplodeEvent Listener
     *
     * @param e The EntityExplodeEvent
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityExplode(EntityExplodeEvent e) {
        for (Game game : this.plugin.getApi().getGames()) {
            if (game.getMap().getWorld().getName().equals(e.getLocation().getWorld().getName()) && !game.getCurrGameState().getRuleSet().isExplosionDamage())
                e.setCancelled(true);
            else {
                for (Block b : e.blockList()) {
                    game.getMap().getArena().getRestorer().addBlock(b.getLocation(), b.getState());
                }
            }
        }
    }

    /**
     * The HangingBreakEvent Listener
     *
     * @param e The HangingBreakEvent
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onHangingBreakEvent(HangingBreakEvent e) {
        this.plugin.getApi().getGames().stream().filter(game -> game.getMap().getArena().getSelection().isInArena(e.getEntity().getLocation())).forEach(game -> e.setCancelled(true));
    }
}
