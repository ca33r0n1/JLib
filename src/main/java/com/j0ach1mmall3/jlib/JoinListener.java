package com.j0ach1mmall3.jlib;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/09/2015
 */
public class JoinListener implements Listener {
    private final Main plugin;

    public JoinListener(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
    }
}
