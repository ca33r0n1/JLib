package com.j0ach1mmall3.jlib.jtp;

import com.j0ach1mmall3.jlib.jtp.events.DataReceiveEvent;
import com.j0ach1mmall3.jlib.jtp.events.RemoteDisconnectEvent;
import com.j0ach1mmall3.jlib.logging.JLogger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 17/01/16
 */
public final class Server extends RemoteHolder<RemoteClient> {
    private final String tag;
    private final ServerSocket serverSocket;

    /**
     * Constructs a new Server
     * @param plugin The Plugin associated with this Client
     * @param tag The tag to use
     * @param port The port to use
     */
    @SuppressWarnings("deprecation")
    Server(Plugin plugin, String tag, int port) {
        super(plugin);
        this.tag = tag;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            new JLogger(plugin).log(ChatColor.RED + "Failed to set up Server at port " + port + '!');
            e.printStackTrace();
        }
        this.serverSocket = serverSocket;
        if(this.serverSocket == null) return;

        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new ServerConnectRunnable(this), 0L);
        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new DataReceiveRunnable(this), 0L);
    }

    /**
     * Receives a String of data from a remote client
     * @param remoteClient The remote client
     * @return The data
     */
    String receiveData(RemoteClient remoteClient) {
        if(!this.alive || remoteClient == null || remoteClient.getSocket() == null) return null;
        Socket socket = remoteClient.getSocket();
        if(socket.isClosed()) {
            this.disconnect(remoteClient);
            return null;
        }
        try {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String readTag = dataInputStream.readUTF();
            String data = dataInputStream.readUTF();
            if(DatatypeConverter.printHexBinary(MessageDigest.getInstance("SHA-256").digest(this.tag.getBytes())).equals(readTag)) {
                byte[] encryptedData = DatatypeConverter.parseHexBinary(data);
                Cipher c = Cipher.getInstance("AES");
                c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(this.tag.getBytes(), "AES"));
                byte[] bytes = c.doFinal(encryptedData);
                DataReceiveEvent event = new DataReceiveEvent(this, remoteClient, new String(bytes));
                Bukkit.getPluginManager().callEvent(event);
                if(event.isCancelled()) return null;
                data = event.getData();
                return data;
            } else new JLogger().log(ChatColor.RED + "Invalid Tag '" + readTag + "' sent by " + remoteClient.getIp() + "!");
        } catch (Exception e) {
            this.disconnect(remoteClient);
        }
        return null;
    }

    /**
     * Stops the Server
     */
    void stop() {
        this.alive = false;
        try {
            this.serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new JLogger(this.plugin).log(ChatColor.GREEN + "Stopped Server!");
    }

    /**
     * Returns the ServerSocket
     * @return The ServerSocket
     */
    ServerSocket getServerSocket() {
        return this.serverSocket;
    }

    /**
     * Not used
     */
    void connect(RemoteClient remoteClient) {
        // NOP
    }

    /**
     * Disconnects from a remote client
     * @param remoteClient The remote client
     */
    void disconnect(RemoteClient remoteClient) {
        RemoteDisconnectEvent event = new RemoteDisconnectEvent(this, remoteClient);
        Bukkit.getPluginManager().callEvent(event);
        Socket socket = remoteClient.getSocket();
        if(socket == null) return;
        try {
            socket.close();
            new JLogger(this.plugin).log(ChatColor.RED + "Disconnected from Remote Client " + remoteClient.getIp() + '!');
        } catch (Exception e) {
            // Socket was already closed
        }
        this.remotes.remove(remoteClient);
    }
}
