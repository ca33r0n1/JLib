package com.j0ach1mmall3.jlib.jtp;

import com.j0ach1mmall3.jlib.jtp.events.RemoteConnectEvent;
import com.j0ach1mmall3.jlib.jtp.events.RemoteDisconnectEvent;
import com.j0ach1mmall3.jlib.logging.JLogger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.net.Socket;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 17/01/16
 */
final class ServerConnectRunnable implements Runnable {
    private final Server server;

    /**
     * Constructs a new ServerConnectRunnable
     * @param server The Server this Runnable is associated with
     */
    ServerConnectRunnable(Server server) {
        this.server = server;
    }

    @Override
    public void run(){
        while(this.server.isAlive()) {
            Socket socket;
            try {
                socket = this.server.getServerSocket().accept();
            } catch (Exception e) {
                continue;
            }
            if(socket != null) {
                RemoteClient remoteClient = new RemoteClient(socket.getInetAddress().getHostAddress());
                remoteClient.setSocket(socket);
                RemoteConnectEvent event = new RemoteConnectEvent(this.server, remoteClient);
                Bukkit.getPluginManager().callEvent(event);
                if(event.isCancelled()) this.server.disconnect(remoteClient, RemoteDisconnectEvent.Reason.EVENT_CANCELLED);
                else {
                    new JLogger(this.server.plugin).log(ChatColor.GREEN + "Connected to Remote Client " + remoteClient.getIp() + '!');
                    this.server.addRemote(remoteClient);
                }
            }
        }
    }
}
