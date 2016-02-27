package com.j0ach1mmall3.jlib.jtp.events;

import com.j0ach1mmall3.jlib.jtp.RemoteClient;
import com.j0ach1mmall3.jlib.jtp.Server;
import org.bukkit.event.HandlerList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 17/01/16
 */
public final class DataReceiveEvent extends DataEvent {
    private static final HandlerList HANDLERS = new HandlerList();

    /**
     * Constructs a new DataReceiveEvent
     * @param server The Server that receives the data
     * @param remoteClient The remote client that sent the data
     * @param data The data
     */
    public DataReceiveEvent(Server server, RemoteClient remoteClient, String data) {
        super(server, remoteClient, data);
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    /**
     * Returns the HandlerList (Bukkit method)
     * @return The HandlerList
     */
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
