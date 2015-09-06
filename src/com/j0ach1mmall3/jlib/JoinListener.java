package com.j0ach1mmall3.jlib;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by j0ach1mmall3 on 18:35 5/09/2015 using IntelliJ IDEA.
 */
public class JoinListener implements Listener {
    private Main plugin;
    public JoinListener(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        //Testing zone :o
    }
}
