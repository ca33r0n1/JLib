package com.j0ach1mmall3.jlib.integration.protocollib;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import org.bukkit.Bukkit;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 17/02/16
 */
public final class ProtocolLibHook {
    private final boolean present;

    /**
     * Constructs a new ProtocolLib
     */
    public ProtocolLibHook() {
        this.present = Bukkit.getPluginManager().getPlugin("ProtocolLib") != null;
    }

    /**
     * Returns whether ProtocolLib is present
     *
     * @return Whether ProtocolLib is present
     */
    public boolean isPresent() {
        return this.present;
    }

    /**
     * Adds a PacketAdapter
     *
     * @param packetAdapter The PacketAdapter to add
     */
    public void addPacketAdapter(PacketAdapter packetAdapter) {
        if (!this.present) throw new UnsupportedOperationException("ProtocolLib isn't present");
        ProtocolLibrary.getProtocolManager().addPacketListener(packetAdapter);
    }
}
