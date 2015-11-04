package com.j0ach1mmall3.jlib;

import com.j0ach1mmall3.jlib.storage.serialization.SerializedInventory;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by j0ach1mmall3 on 18:35 5/09/2015 using IntelliJ IDEA.
 */
public class JoinListener implements Listener {
    private final Main plugin;
    public JoinListener(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @SuppressWarnings({"EmptyMethod", "UnusedParameters"})
    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final SerializedInventory inventory = new SerializedInventory(e.getPlayer().getInventory());
        Bukkit.broadcastMessage(inventory.getString());
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                e.getPlayer().openInventory(inventory.getInventory());
            }
        }, 60L);
    }
}
