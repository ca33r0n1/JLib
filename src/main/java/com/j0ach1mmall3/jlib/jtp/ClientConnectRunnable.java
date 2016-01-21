package com.j0ach1mmall3.jlib.jtp;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 17/01/16
 */
final class ClientConnectRunnable implements Runnable {
    private final Client client;

    /**
     * Constructs a new ClientConnectRunnable
     * @param client The Client this Runnable is associated with
     */
    ClientConnectRunnable(Client client) {
        this.client = client;
    }


    @Override
    public void run(){
        for(RemoteServer remoteServer : this.client.getUnconnected()) {
            this.client.connect(remoteServer);
        }
    }
}
