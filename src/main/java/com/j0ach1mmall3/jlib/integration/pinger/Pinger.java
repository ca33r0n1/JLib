package com.j0ach1mmall3.jlib.integration.pinger;

import com.google.gson.Gson;
import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 21/02/16
 */
public final class Pinger {
    private static final Gson GSON = new Gson();

    private final String ip;
    private final int port;
    private final int timeout;

    /**
     * Constructs a new Pinger instance
     *
     * @param ip      The IP to ping
     * @param port    The port to ping
     * @param timeout The timeout in milliseconds
     */
    public Pinger(String ip, int port, int timeout) {
        this.ip = ip;
        this.port = port;
        this.timeout = timeout;
    }

    /**
     * Constructs a new Pinger instance
     *
     * @param ip   The IP to ping
     * @param port The port to ping
     */
    public Pinger(String ip, int port) {
        this(ip, port, 7000);
    }

    /**
     * Constructs a new Pinger instance (Default port to 25565)
     *
     * @param ip The IP to ping
     */
    public Pinger(String ip) {
        this(ip, 25565);
    }

    /**
     * Pings the specified server
     *
     * @param plugin          The plugin to ping with
     * @param callbackHandler The callbackhandler to call back to
     */
    public void ping(Plugin plugin, CallbackHandler<PingResponse> callbackHandler) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try (Socket socket = new Socket(this.ip, this.port)) {
                socket.setSoTimeout(this.timeout);

                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

                ByteArrayOutputStream b = new ByteArrayOutputStream();
                DataOutputStream handshake = new DataOutputStream(b);
                handshake.writeByte(0x00);
                this.writeVarInt(handshake, 4);
                this.writeVarInt(handshake, this.ip.length());
                handshake.writeBytes(this.ip);
                handshake.writeShort(this.port);
                this.writeVarInt(handshake, 1);

                this.writeVarInt(dataOutputStream, b.size());
                dataOutputStream.write(b.toByteArray());

                dataOutputStream.writeByte(0x01);
                dataOutputStream.writeByte(0x00);

                this.readVarInt(dataInputStream);
                int id = this.readVarInt(dataInputStream);

                byte[] in = new byte[this.readVarInt(dataInputStream)];
                dataInputStream.readFully(in);
                PingResponse pingResponse = GSON.fromJson(new String(in), PingResponse.class);

                dataOutputStream.close();
                dataInputStream.close();
                socket.close();

                callbackHandler.callback(pingResponse);
            } catch (Exception e) {
                callbackHandler.callback(null);
            }
        });
    }

    /**
     * Reads a VarInt from a DataInputStream
     *
     * @param in The DataInputStream
     * @return The VarInt
     * @throws IOException If an exception occurs
     */
    private int readVarInt(DataInputStream in) throws IOException {
        int i = 0;
        int j = 0;
        while (true) {
            int k = in.readByte();
            i |= (k & 0x7F) << j++ * 7;
            if (j > 5) throw new IOException("VarInt too big");
            if ((k & 0x80) != 128) break;
        }
        return i;
    }

    /**
     * Writes a VarInt to a DataOutputStream
     *
     * @param out      The DataOutputStream
     * @param paramInt The VarInt
     * @throws IOException If an exception occurs
     */
    private void writeVarInt(DataOutputStream out, int paramInt) throws IOException {
        while (true) {
            if ((paramInt & 0xFFFFFF80) == 0) {
                out.writeByte(paramInt);
                return;
            }
            out.writeByte(paramInt & 0x7F | 0x80);
            paramInt >>>= 7;
        }
    }
}
