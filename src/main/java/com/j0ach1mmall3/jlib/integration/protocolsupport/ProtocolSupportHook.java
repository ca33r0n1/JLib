package com.j0ach1mmall3.jlib.integration.protocolsupport;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 29/12/15
 */
public final class ProtocolSupportHook {
    private boolean present;

    public ProtocolSupportHook() {
        this.present = Bukkit.getPluginManager().getPlugin("ProtocolSupport") != null;
    }

    public boolean isPresent() {
        return this.present;
    }

    public static String getVersion(Player p) {
        return protocolsupport.api.ProtocolSupportAPI.getProtocolVersion(p).getName();
    }
}
