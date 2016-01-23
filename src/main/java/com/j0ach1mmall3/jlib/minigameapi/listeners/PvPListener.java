package com.j0ach1mmall3.jlib.minigameapi.listeners;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import com.j0ach1mmall3.jlib.minigameapi.game.GamePvPType;
import com.j0ach1mmall3.jlib.minigameapi.team.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/09/15
 */
public class PvPListener implements Listener {
    private final Main plugin;

    /**
     * Initialises the PvPListener
     * @param plugin Main plugin
     */
    public PvPListener(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * The EntityDamageByEntityEvent Listener
     * @param e The EntityDamageByEntityEvent
     * @see Game
     * @see GamePvPType
     */
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            if(this.plugin.getApi().isInGame(p)) {
                Game game = this.plugin.getApi().getGame(p);
                if(e.getEntity() instanceof Player) {
                    Player entity = (Player) e.getEntity();
                    GamePvPType type = game.getPvpType();
                    if(type == GamePvPType.DISABLED) {
                        e.setCancelled(true);
                        return;
                    }
                    Team team = game.getTeam(p);
                    if(type == GamePvPType.ANTITEAM && !team.containsMember(entity)) e.setCancelled(true);
                } else {
                    if(!game.getRuleSet().getDamageAble().contains(e.getEntity().getType())) e.setCancelled(true);
                }
            }
        }
    }
}
