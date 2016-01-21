package com.j0ach1mmall3.jlib.jtp;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 17/01/16
 */
final class DisconnectRunnable implements Runnable {
    private final Client client;

    /**
     * Constructs a new DisconnectRunnable
     * @param client The Client associated with this Runnable
     */
    DisconnectRunnable(Client client) {
        this.client = client;
    }

    @Override
    public void run(){
        while(this.client.isAlive()) {
            for(RemoteServer remoteServer : this.client.getRemotes()) {
                if(!this.client.isConnected(remoteServer)) this.client.disconnect(remoteServer);
            }
        }
        for(RemoteServer remoteServer : this.client.getRemotes()) {
            this.client.disconnect(remoteServer);
        }
    }
}
