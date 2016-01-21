package com.j0ach1mmall3.jlib.jtp;

import com.j0ach1mmall3.jlib.jtp.events.DataSendEvent;
import com.j0ach1mmall3.jlib.jtp.events.RemoteConnectEvent;
import com.j0ach1mmall3.jlib.jtp.events.RemoteDisconnectEvent;
import com.j0ach1mmall3.jlib.logging.JLogger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 17/01/16
 */
public final class Client extends RemoteHolder<RemoteServer> {
    private final Set<RemoteServer> unconnected = new HashSet<>();

    /**
     * Constructs a new Client
     * @param plugin The Plugin associated with this Client
     * @param remoteServers The remote servers to connect to
     */
    @SuppressWarnings("deprecation")
    Client(Plugin plugin, List<RemoteServer> remoteServers) {
        super(plugin);
        for(RemoteServer remoteServer : remoteServers) {
            this.connect(remoteServer);
        }

        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new DisconnectRunnable(this), 0L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new ClientConnectRunnable(this), 20L, 20L);
    }

    /**
     * Returns the unconnected remote servers
     * @return The remote servers
     */
    Set<RemoteServer> getUnconnected() {
        return this.unconnected;
    }

    /**
     * Sends a String of data to a remote server
     * @param remoteServer The remote server
     * @param data The data
     */
    void sendData(RemoteServer remoteServer, String data) {
        if(!this.alive) return;
        DataSendEvent event = new DataSendEvent(this, remoteServer, data);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled()) return;
        data = event.getData();
        Socket socket = remoteServer.getSocket();
        if(socket.isClosed()) this.disconnect(remoteServer);
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(remoteServer.getTag());
            dataOutputStream.writeUTF(data);
        } catch (Exception e) {
            this.disconnect(remoteServer);
        }
    }

    /**
     * Stops the Client
     */
    void stop() {
        this.alive = false;
        new JLogger(this.plugin).log(ChatColor.GREEN + "Stopped Client!");
    }

    /**
     * Connects to a remote server
     * @param remoteServer The remote server to connect to
     */
    void connect(final RemoteServer remoteServer) {
        if(!this.alive) return;
        RemoteConnectEvent event = new RemoteConnectEvent(this, remoteServer);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled()) return;
        Socket socket = null;
        JLogger jLogger = new JLogger(this.plugin);
        try {
            socket = new Socket(remoteServer.getIp(), remoteServer.getPort());
            remoteServer.setSocket(socket);
            jLogger.log(ChatColor.GREEN + "Connected to Remote Server " + remoteServer.getIp() + ':' + remoteServer.getPort() + '!');
            this.unconnected.remove(remoteServer);
        } catch (Exception e) {
            this.unconnected.add(remoteServer);
        }
        if(socket != null) this.remotes.add(remoteServer);
    }

    /**
     * Disconnects from a remote server
     * @param remoteServer The remote server to disconnect from
     */
    void disconnect(RemoteServer remoteServer) {
        RemoteDisconnectEvent event = new RemoteDisconnectEvent(this, remoteServer);
        Bukkit.getPluginManager().callEvent(event);
        Socket socket = remoteServer.getSocket();
        if(socket == null) return;
        try {
            socket.close();
            new JLogger(this.plugin).log(ChatColor.RED + "Disconnected from Remote Server " + remoteServer.getIp() + ':' + remoteServer.getPort() + '!');
            this.unconnected.add(remoteServer);
        } catch (Exception e) {
            // Socket was already closed
        }
        this.remotes.remove(remoteServer);
    }

    /**
     * Returns whether this Client is connected to a remote server
     * @param remoteServer The remote server to check
     * @return Whether this Client is connected
     */
    boolean isConnected(RemoteServer remoteServer) {
        if(remoteServer.getSocket().isClosed() || !this.remotes.contains(remoteServer)) return false;
        try {
            return new DataInputStream(remoteServer.getSocket().getInputStream()).read() != -1;
        } catch (Exception e) {
            return false;
        }
    }
}
