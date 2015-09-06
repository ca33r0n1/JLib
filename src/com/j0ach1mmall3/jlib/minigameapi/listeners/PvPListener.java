package com.j0ach1mmall3.jlib.minigameapi.listeners;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.minigameapi.MinigameAPI;
import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import com.j0ach1mmall3.jlib.minigameapi.game.GamePvPType;
import com.j0ach1mmall3.jlib.minigameapi.team.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by j0ach1mmall3 on 20:23 4/09/2015 using IntelliJ IDEA.
 */
public class PvPListener implements Listener {
    private Main plugin;
    public PvPListener(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            if(MinigameAPI.isInGame(p)) {
                Game game = MinigameAPI.getGame(p);
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
