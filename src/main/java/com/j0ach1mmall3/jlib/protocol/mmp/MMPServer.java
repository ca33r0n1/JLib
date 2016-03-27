package com.j0ach1mmall3.jlib.protocol.mmp;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedServerPing;
import com.j0ach1mmall3.jlib.integration.protocollib.ProtocolLibHook;
import com.j0ach1mmall3.jlib.storage.serialization.JSerializable;
import org.bukkit.plugin.Plugin;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.HashMap;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 27/03/2016
 */
public abstract class MMPServer {
    private final Plugin plugin;
    private final Key key;

    /**
     * Constructs a new MMP (Motd Messaging Protocol) Server
     * @param plugin The Plugin to use
     * @param key The Encryption key to use
     * @param motd The motd that'll be used
     */
    public MMPServer(Plugin plugin, Key key, String motd) {
        ProtocolLibHook hook = new ProtocolLibHook();
        if(!hook.isPresent()) throw new IllegalStateException("protocollib not found!");

        this.plugin = plugin;
        this.key = key;

        final StringBuilder paddedMotd = new StringBuilder(motd);
        while (paddedMotd.length() < 108) {
            paddedMotd.append(' ');
        }

        hook.addPacketAdapter(new PacketAdapter(plugin, PacketType.Status.Server.SERVER_INFO) {
            @Override
            public void onPacketSending(PacketEvent event) {
                PacketContainer packetContainer = event.getPacket();
                HashMap<String, String> data = MMPServer.this.onDataRequest(new Remote(event.getPlayer().getAddress().getHostName(), event.getPlayer().getAddress().getPort(), MMPServer.this.key));
                WrappedServerPing wrappedServerPing = packetContainer.getServerPings().read(0);
                try {
                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.ENCRYPT_MODE, MMPServer.this.key);
                    String encrypted = DatatypeConverter.printBase64Binary(cipher.doFinal(new JSerializable<>(data).getString().getBytes()));
                    wrappedServerPing.setMotD(paddedMotd + encrypted);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                packetContainer.getServerPings().write(0, wrappedServerPing);
                event.setPacket(packetContainer);
            }
        });
    }

    /**
     * Method that will return Data when requested by a Client
     * @param client The Client
     * @return The Data
     */
    protected abstract HashMap<String, String> onDataRequest(Remote client);
}
