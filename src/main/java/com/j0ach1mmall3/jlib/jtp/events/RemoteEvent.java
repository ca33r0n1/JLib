package com.j0ach1mmall3.jlib.jtp.events;

import com.j0ach1mmall3.jlib.jtp.Remote;
import com.j0ach1mmall3.jlib.jtp.RemoteHolder;
import org.bukkit.event.Event;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 3/02/16
 */
public abstract class RemoteEvent extends Event {
    private final RemoteHolder remoteHolder;
    private final Remote remote;

    /**
     * Constructs a new RemoteEvent
     * @param remoteHolder The RemoteHolder
     * @param remote The Remote
     */
    public RemoteEvent(RemoteHolder remoteHolder, Remote remote) {
        this.remoteHolder = remoteHolder;
        this.remote = remote;
    }

    /**
     * Returns the RemoteHolder
     * @return The RemoteHolder
     */
    public RemoteHolder getRemoteHolder() {
        return this.remoteHolder;
    }

    /**
     * Returns the Remote
     * @return The Remote
     */
    public Remote getRemote() {
        return this.remote;
    }
}
