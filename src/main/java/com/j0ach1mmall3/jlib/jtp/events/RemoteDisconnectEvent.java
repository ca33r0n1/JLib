package com.j0ach1mmall3.jlib.jtp.events;

import com.j0ach1mmall3.jlib.jtp.Remote;
import com.j0ach1mmall3.jlib.jtp.RemoteHolder;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 17/01/16
 */
public class RemoteDisconnectEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final RemoteHolder remoteHolder;
    private final Remote remote;

    public RemoteDisconnectEvent(RemoteHolder remoteHolder, Remote remote) {
        this.remoteHolder = remoteHolder;
        this.remote = remote;
    }

    public RemoteHolder getRemoteHolder() {
        return this.remoteHolder;
    }

    public Remote getRemote() {
        return this.remote;
    }

    @Override
    public final HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
