package com.j0ach1mmall3.jlib.jtp;

import com.j0ach1mmall3.jlib.jtp.events.DataSendEvent;
import com.j0ach1mmall3.jlib.jtp.events.RemoteConnectEvent;
import com.j0ach1mmall3.jlib.jtp.events.RemoteDisconnectEvent;
import com.j0ach1mmall3.jlib.methods.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.DataOutputStream;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 17/01/16
 */
public final class Client extends RemoteHolder<RemoteServer> {
    private final Set<RemoteServer> unconnected = new HashSet<>();
    private final int runnableId;
    private final MessageDigest md;
    private final Cipher cipher;

    /**
     * Constructs a new Client
     * @param plugin The Plugin associated with this Client
     * @param remoteServers The remote servers to connect to
     */
    @SuppressWarnings("deprecation")
    Client(Plugin plugin, List<RemoteServer> remoteServers) {
        super(plugin);

        MessageDigest md = null;
        Cipher cipher = null;

        try {
            md = MessageDigest.getInstance("SHA-256");
            cipher = Cipher.getInstance("AES");
        } catch (Exception e) {
            this.jLogger.log(ChatColor.RED + "Failed to initialize encryption algorithms!");
            e.printStackTrace();
        }

        this.md = md;
        this.cipher = cipher;
        this.runnableId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new ClientConnectRunnable(this), 20L, 20L);
        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new DisconnectRunnable(this), 0L);

        if(this.md == null || this.cipher == null) return;

        for(RemoteServer remoteServer : remoteServers) {
            this.connect(remoteServer);
        }
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
        if(!this.alive || remoteServer == null || remoteServer.getSocket() == null) return;
        DataSendEvent event = new DataSendEvent(this, remoteServer, data);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled()) return;
        data = event.getData();
        Socket socket = remoteServer.getSocket();
        if(socket.isClosed()) {
            this.disconnect(remoteServer, RemoteDisconnectEvent.Reason.REMOTE_CLOSEDSOCKET);
            return;
        }
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            String salt = Random.getString(16, true, true);
            String hash = DatatypeConverter.printHexBinary(this.md.digest((remoteServer.getTag() + salt).getBytes()));
            String saltAndHash = salt + "|" + hash;
            dataOutputStream.writeUTF(saltAndHash);
            byte[] bytes = data.getBytes();
            this.cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(remoteServer.getTag().getBytes(), "AES"));
            byte[] encryptedData = this.cipher.doFinal(bytes);
            dataOutputStream.writeUTF(DatatypeConverter.printHexBinary(encryptedData));
        } catch (Exception e) {
            this.disconnect(remoteServer, RemoteDisconnectEvent.Reason.CLIENT_DATA_SEND_ERROR);
        }
    }

    /**
     * Returns whether this Client is connected to a remote server
     * @param remoteServer The remote server to check
     * @return Whether this Client is connected
     */
    boolean isConnected(RemoteServer remoteServer) {
        if(remoteServer.getSocket().isClosed() || !this.remotes.contains(remoteServer)) return false;
        try {
            return remoteServer.getSocket().getInputStream().read() != -1;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    void stop() {
        this.alive = false;
        Bukkit.getScheduler().cancelTask(this.runnableId);
        this.jLogger.log(ChatColor.GREEN + "Stopped Client!");
    }

    @Override
    void connect(final RemoteServer remoteServer) {
        if(!this.alive) return;
        RemoteConnectEvent event = new RemoteConnectEvent(this, remoteServer);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled()) return;
        Socket socket = null;
        try {
            socket = new Socket(remoteServer.getIp(), remoteServer.getPort());
            remoteServer.setSocket(socket);
            this.jLogger.log(ChatColor.GREEN + "Connected to Remote Server " + remoteServer.getIp() + ':' + remoteServer.getPort() + '!');
            this.unconnected.remove(remoteServer);
        } catch (Exception e) {
            this.unconnected.add(remoteServer);
        }
        if(socket != null) this.remotes.add(remoteServer);
    }

    @Override
    void disconnect(RemoteServer remoteServer, RemoteDisconnectEvent.Reason reason) {
        RemoteDisconnectEvent event = new RemoteDisconnectEvent(this, remoteServer, reason);
        Bukkit.getPluginManager().callEvent(event);
        Socket socket = remoteServer.getSocket();
        if(socket == null) return;
        try {
            socket.close();
            this.jLogger.log(ChatColor.RED + "Disconnected from Remote Server " + remoteServer.getIp() + ':' + remoteServer.getPort() + '!');
            this.unconnected.add(remoteServer);
        } catch (Exception e) {
            // Socket was already closed
        }
        this.remotes.remove(remoteServer);
    }
}
