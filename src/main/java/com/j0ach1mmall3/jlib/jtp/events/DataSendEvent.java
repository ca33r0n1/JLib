package com.j0ach1mmall3.jlib.jtp.events;

import com.j0ach1mmall3.jlib.jtp.Client;
import com.j0ach1mmall3.jlib.jtp.RemoteServer;
import org.bukkit.event.HandlerList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 17/01/16
 */
public final class DataSendEvent extends DataEvent {
    private static final HandlerList HANDLERS = new HandlerList();

    /**
     * Constructs a new DataSendEvent
     * @param client The Server that receives the data
     * @param remoteServer The remote client that sent the data
     * @param data The data
     */
    public DataSendEvent(Client client, RemoteServer remoteServer, String data) {
        super(client, remoteServer, data);
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
