package com.j0ach1mmall3.jlib;

import com.j0ach1mmall3.jlib.methods.General;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/09/15
 */
public final class JoinListener implements Listener {
    private final Map<Player, Long> lastMoved = new HashMap<>();
    private final Map<Player, Long> lastWalked = new HashMap<>();
    private final Main plugin;

    /**
     * Initialises the JoinListener
     * @param plugin Main plugin
     */
    public JoinListener(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * The PlayerJoinEvent listener
     * @param e The PlayerJoinEvent
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        
    }

    /**
     * The PlayerQuitEvent listener
     * @param e The PlayerQuitEvent
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        General.unfreezePlayer(e.getPlayer());
        this.lastMoved.remove(e.getPlayer());
    }

    /**
     * The PlayerKickEvent listener
     * @param e The PlayerKickEvent
     */
    @EventHandler
    public void onPlayerKick(PlayerKickEvent e) {
        General.unfreezePlayer(e.getPlayer());
        this.lastMoved.remove(e.getPlayer());
    }

    /**
     * The PlayerMoveEvent listener
     * @param e The PlayerMoveEvent
     */
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        this.lastMoved.put(e.getPlayer(), System.currentTimeMillis());
        if(e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockY() != e.getTo().getBlockY() || e.getFrom().getBlockZ() != e.getTo().getBlockZ()) this.lastWalked.put(e.getPlayer(), System.currentTimeMillis());
    }

    /**
     * Returns when a player last moved, in milliseconds
     * @param player The player
     * @return When the player last moved, 0 if he hasn't moved yet
     */
    public long getLastMoved(Player player) {
        return this.lastMoved.containsKey(player) ? this.lastMoved.get(player) : 0;
    }

    /**
     * Returns when a player last walked, in milliseconds
     * @param player The player
     * @return When the player last walked, 0 if he hasn't walked yet
     */
    public long getLastWalked(Player player) {
        return this.lastWalked.containsKey(player) ? this.lastWalked.get(player) : 0;
    }
}
