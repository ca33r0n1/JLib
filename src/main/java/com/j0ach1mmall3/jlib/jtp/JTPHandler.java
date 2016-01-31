package com.j0ach1mmall3.jlib.jtp;

import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Set;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 17/01/16
 */
public final class JTPHandler {
    private final Server server;
    private final Client client;

    /**
     * Constructs a new JTPHandler, the main handler for the JTP
     * @param plugin The Plugin associated with this JTPHandler
     * @param tag The tag to use
     * @param port The port to use
     * @param remoteServers The remote servers to connect to
     */
    public JTPHandler(Plugin plugin, String tag, int port, List<RemoteServer> remoteServers) {
        this.server = new Server(plugin, tag, port);
        this.client = new Client(plugin, remoteServers);
    }

    /**
     * Sends a String of data to a remote server
     * @param remoteServer The remote server
     * @param data The data
     */
    public void sendData(RemoteServer remoteServer, String data) {
        this.client.sendData(remoteServer, data);
    }

    /**
     * Receives a String of data from a remote client
     * @param remoteClient The remote client
     * @return The data
     */
    public String receiveData(RemoteClient remoteClient) {
        return this.server.receiveData(remoteClient);
    }

    /**
     * Stops the JTPHandler
     */
    public void stop() {
        this.server.stop();
        this.client.stop();
    }

    /**
     * Returns the connected remote clients
     * @return The remote clients
     */
    public Set<RemoteClient> getConnectedClients() {
        return this.server.getRemotes();
    }

    /**
     * Returns the connected remote servers
     * @return The remote servers
     */
    public Set<RemoteServer> getConnectedServers() {
        return this.client.getRemotes();
    }

    /**
     * Returns whether this JTPHandler is alive
     * @return Whether this JTPHandler is alive
     */
    public boolean isAlive() {
        return this.server.isAlive() && this.client.isAlive();
    }

    /**
     * Returns the RemoteClient instance associated with a RemoteServer
     * @param remoteServer The RemoteServer
     * @return The RemoteClient
     */
    public RemoteClient getRemoteClient(RemoteServer remoteServer) {
        for(RemoteClient remoteClient : this.server.getRemotes()) {
            if(remoteClient.isRelative(remoteServer)) return remoteClient;
        }
        return null;
    }

    /**
     * Returns the RemoteServer instance associated with a RemoteClient
     * @param remoteClient The RemoteClient
     * @return The RemoteServer
     */
    public RemoteServer getRemoteServer(RemoteClient remoteClient) {
        for(RemoteServer remoteServer : this.client.getRemotes()) {
            if(remoteServer.isRelative(remoteClient)) return remoteServer;
        }
        return null;
    }
}
