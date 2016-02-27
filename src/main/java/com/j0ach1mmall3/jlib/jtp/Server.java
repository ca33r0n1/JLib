package com.j0ach1mmall3.jlib.jtp;

import com.j0ach1mmall3.jlib.jtp.events.DataReceiveEvent;
import com.j0ach1mmall3.jlib.jtp.events.RemoteDisconnectEvent;
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
    private final MessageDigest md;
    private final Cipher cipher;

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
        MessageDigest md = null;
        Cipher cipher = null;

        try {
            serverSocket = new ServerSocket(port);
            md = MessageDigest.getInstance("SHA-256");
            cipher = Cipher.getInstance("AES");
        } catch (Exception e) {
            this.jLogger.log(ChatColor.RED + "Failed to set up Server at port " + port + '!');
            e.printStackTrace();
        }

        this.serverSocket = serverSocket;
        this.md = md;
        this.cipher = cipher;

        if(this.serverSocket == null || this.md == null || this.cipher == null) return;

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
            this.disconnect(remoteClient, RemoteDisconnectEvent.Reason.REMOTE_CLOSEDSOCKET);
            return null;
        }
        try {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String saltAndHash = dataInputStream.readUTF();
            String salt = saltAndHash.split("\\|")[0];
            String hash = saltAndHash.split("\\|")[1];
            String data = dataInputStream.readUTF();
            if(DatatypeConverter.printHexBinary(this.md.digest((this.tag + salt).getBytes())).equals(hash)) {
                byte[] encryptedData = DatatypeConverter.parseHexBinary(data);
                this.cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(this.tag.getBytes(), "AES"));
                byte[] bytes = this.cipher.doFinal(encryptedData);
                DataReceiveEvent event = new DataReceiveEvent(this, remoteClient, new String(bytes));
                Bukkit.getPluginManager().callEvent(event);
                if(event.isCancelled()) return null;
                data = event.getData();
                return data;
            } else this.jLogger.log(ChatColor.RED + "Invalid Hash '" + hash + "' (Salt: '" + salt + "' sent by " + remoteClient.getIp() + '!');
        } catch (Exception e) {
            this.disconnect(remoteClient, RemoteDisconnectEvent.Reason.SERVER_DATA_RECEIVE_ERROR);
        }
        return null;
    }

    /**
     * Returns the ServerSocket
     * @return The ServerSocket
     */
    ServerSocket getServerSocket() {
        return this.serverSocket;
    }

    @Override
    void stop() {
        this.alive = false;
        try {
            this.serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.jLogger.log(ChatColor.GREEN + "Stopped Server!");
    }

    @Override
    void connect(RemoteClient remote) {
        // NOP
    }

    @Override
    void disconnect(RemoteClient remote, RemoteDisconnectEvent.Reason reason) {
        RemoteDisconnectEvent event = new RemoteDisconnectEvent(this, remote, reason);
        Bukkit.getPluginManager().callEvent(event);
        Socket socket = remote.getSocket();
        if(socket == null) return;
        try {
            socket.close();
            this.jLogger.log(ChatColor.RED + "Disconnected from Remote Client " + remote.getIp() + '!');
        } catch (Exception e) {
            // Socket was already closed
        }
        this.remotes.remove(remote);
    }
}
