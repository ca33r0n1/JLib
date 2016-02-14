package com.j0ach1mmall3.jlib.jtp;

import com.j0ach1mmall3.jlib.jtp.events.RemoteDisconnectEvent;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 17/01/16
 */
final class DataReceiveRunnable implements Runnable {
    private final Server server;

    /**
     * Returns a new DataReceiveRunnable
     * @param server The Server associated with this Runnable
     */
    DataReceiveRunnable(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        while (this.server.isAlive()) {
            for(RemoteClient remoteClient : this.server.getRemotes()) {
                this.server.receiveData(remoteClient);
            }
        }
        for(RemoteClient remoteClient : this.server.getRemotes()) {
            this.server.disconnect(remoteClient, RemoteDisconnectEvent.Reason.SERVER_STOPPED);
        }
    }
}
