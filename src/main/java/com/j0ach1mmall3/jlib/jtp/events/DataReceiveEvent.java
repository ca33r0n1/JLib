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

    public DataReceiveEvent(Server server, RemoteClient remoteClient, String data) {
        this.server = server;
        this.remoteClient = remoteClient;
        this.data = data;
    }

    public Server getServer() {
        return this.server;
    }

    public RemoteClient getRemoteClient() {
        return this.remoteClient;
    }

    public String getData() {
        return this.data;
    }

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

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
