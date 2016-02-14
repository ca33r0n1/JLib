package com.j0ach1mmall3.jlib.jtp.events;

import com.j0ach1mmall3.jlib.jtp.Remote;
import com.j0ach1mmall3.jlib.jtp.RemoteHolder;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 3/02/16
 */
public abstract class DataEvent extends Event implements Cancellable {
    private final RemoteHolder remoteHolder;
    private final Remote remote;
    private String data;
    private boolean cancelled;

    /**
     * Constructs a new DataEvent
     * @param remoteHolder The RemoteHolder that sends/receives the data
     * @param remote The Remote that sends/receives the data
     * @param data The data
     */
    public DataEvent(RemoteHolder remoteHolder, Remote remote, String data) {
        this.remoteHolder = remoteHolder;
        this.remote = remote;
        this.data = data;
    }

    /**
     * Returns the RemoteHolder that sends/receives the data
     * @return The RemoteHolder
     */
    public RemoteHolder getRemoteHolder() {
        return this.remoteHolder;
    }

    /**
     * Returns the Remote that sends/receives the data
     * @return The Remote
     */
    public Remote getRemote() {
        return this.remote;
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
}
