package com.j0ach1mmall3.jlib.jtp.events;

import com.j0ach1mmall3.jlib.jtp.Client;
import com.j0ach1mmall3.jlib.jtp.RemoteServer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 17/01/16
 */
public class DataSendEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Client client;
    private final RemoteServer remoteServer;
    private String data;
    private boolean cancelled;

    /**
     * Constructs a new DataSendEvent
     * @param client The Client that sent the data
     * @param remoteServer The remote server that receives the data
     * @param data The data
     */
    public DataSendEvent(Client client, RemoteServer remoteServer, String data) {
        this.client = client;
        this.remoteServer = remoteServer;
        this.data = data;
    }

    /**
     * Returns the Client that sent the data
     * @return The Client
     */
    public Client getClient() {
        return this.client;
    }

    /**
     * Returns the remote server that receives the data
     * @return The remote server
     */
    public RemoteServer getRemoteServer() {
        return this.remoteServer;
    }

    /**
     * Returns the data
     * @return The data
     */
    public String getData() {
        return this.data;
    }

    /**
     * Sets the data
     * @param data The data
     */
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public final HandlerList getHandlers() {
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
