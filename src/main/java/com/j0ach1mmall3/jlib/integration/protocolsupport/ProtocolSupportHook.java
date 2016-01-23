package com.j0ach1mmall3.jlib.integration.protocolsupport;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 29/12/15
 */
public final class ProtocolSupportHook {
    private final boolean present;

    /**
     * Constructs a new ProtocolSupportHook
     */
    public ProtocolSupportHook() {
        this.present = Bukkit.getPluginManager().getPlugin("ProtocolSupport") != null;
    }

    /**
     * Returns whether ProtocolSupport is present
     * @return Whether ProtocolSupport is present
     */
    public boolean isPresent() {
        return this.present;
    }

    /**
     * Returns the Protocol version of a player
     * @param player The player
     * @return The Protocol version
     */
    public String getVersion(Player player) {
        return protocolsupport.api.ProtocolSupportAPI.getProtocolVersion(player).getName();
    }
}
