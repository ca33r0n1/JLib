package com.j0ach1mmall3.jlib;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/09/2015
 */
public class JoinListener implements Listener {
    private final Map<Player, Long> lastMoved = new HashMap<>();
    private final Map<Player, Long> lastWalked = new HashMap<>();
    private final Main plugin;

    public JoinListener(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().removePotionEffect(PotionEffectType.JUMP);

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        this.lastMoved.remove(e.getPlayer());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        this.lastMoved.put(e.getPlayer(), System.currentTimeMillis());
        if(e.getFrom().getX() != e.getTo().getX() || e.getFrom().getY() != e.getTo().getY() || e.getFrom().getZ() != e.getTo().getZ()) this.lastWalked.put(e.getPlayer(), System.currentTimeMillis());
    }

    public long getLastMoved(Player player) {
        return this.lastMoved.get(player);
    }

    public long getLastWalked(Player player) {
        return this.lastWalked.get(player);
    }
}
