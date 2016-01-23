package com.j0ach1mmall3.jlib.jtp.events;

import com.j0ach1mmall3.jlib.jtp.RemoteClient;
import com.j0ach1mmall3.jlib.jtp.Server;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 17/01/16
 */
public class DataReceiveEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Server server;
    private final RemoteClient remoteClient;
    private String data;
    private boolean cancelled;

    /**
     * Constructs a new DataReceiveEvent
     * @param server The Server that receives the data
     * @param remoteClient The remote client that sent the data
     * @param data The data
     */
    public DataReceiveEvent(Server server, RemoteClient remoteClient, String data) {
        this.server = server;
        this.remoteClient = remoteClient;
        this.data = data;
    }

    /**
     * Returns the Server that receives the data
     * @return The Server
     */
    public Server getServer() {
        return this.server;
    }

    /**
     * Returns the remote client that sent the data
     * @return The remote client
     */
    public RemoteClient getRemoteClient() {
        return this.remoteClient;
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
