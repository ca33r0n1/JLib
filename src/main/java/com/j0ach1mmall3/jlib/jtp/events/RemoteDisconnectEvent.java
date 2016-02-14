package com.j0ach1mmall3.jlib.jtp.events;

import com.j0ach1mmall3.jlib.jtp.Remote;
import com.j0ach1mmall3.jlib.jtp.RemoteHolder;
import org.bukkit.event.HandlerList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 17/01/16
 */
public class RemoteDisconnectEvent extends RemoteEvent {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Reason reason;

    /**
     * Constructs a new RemoteDisconnectEvent
     * @param remoteHolder The RemoteHolder that disconnected
     * @param remote The Remote that the RemoteHolder disconnected from
     * @param reason The Reason for disconnecting
     */
    public RemoteDisconnectEvent(RemoteHolder remoteHolder, Remote remote, Reason reason) {
        super(remoteHolder, remote);
        this.reason = reason;
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

    public enum Reason {
        REMOTE_CLOSEDSOCKET,
        EVENT_CANCELLED,
        CLIENT_DATA_SEND_ERROR,
        CLIENT_STOPPED,
        CLIENT_LOSTCONNECTION,
        SERVER_DATA_RECEIVE_ERROR,
        SERVER_STOPPED
    }
}
