package com.j0ach1mmall3.jlib.protocol.mmp;

import com.j0ach1mmall3.jlib.integration.pinger.PingResponse;
import com.j0ach1mmall3.jlib.integration.pinger.Pinger;
import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import com.j0ach1mmall3.jlib.storage.serialization.JSerializable;
import org.bukkit.plugin.Plugin;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 27/03/2016
 */
public final class MMPClient {
    private final Plugin plugin;
    private final Set<Remote> servers;

    /**
     * Constructs a new MMP (Motd Messaging Protocol) Client
     * @param plugin The Plugin to use
     * @param servers The Servers to connect to
     */
    public MMPClient(Plugin plugin, Set<Remote> servers) {
        this.plugin = plugin;
        this.servers = servers;
    }

    /**
     * Constructs a new MMP (Motd Messaging Protocol) Client
     * @param plugin The Plugin to use
     */
    public MMPClient(Plugin plugin) {
        this.plugin = plugin;
        this.servers = new HashSet<>();
    }

    /**
     * Returns the Servers to connect to
     * @return The Servers
     */
    public Set<Remote> getServers() {
        return this.servers;
    }

    /**
     * Adds a Server to connect to
     * @param server The Server
     */
    public void addServer(Remote server) {
        this.servers.add(server);
    }

    /**
     * Removes a Server
     * @param server The Server
     */
    public void removeServer(Remote server) {
        this.servers.remove(server);
    }

    /**
     * Returns a Server by IP
     * @param ip The IP
     * @return The Server
     */
    public Remote getServerByIP(String ip) {
        for(Remote server : this.servers) {
            if(server.getHost().equals(ip)) return server;
        }
        return null;
    }

    /**
     * Fetches Data from a Server
     * @param server The Server
     * @param callbackHandler The CallbackHandler to call back to
     */
    public void fetchData(final Remote server, final CallbackHandler<HashMap<String, String>> callbackHandler) {
        Pinger pinger = new Pinger(server.getHost(), server.getPort(), 60);
        pinger.ping(this.plugin, new CallbackHandler<PingResponse>() {
            @Override
            public void callback(PingResponse o) {
                String encrypted = o.getDescription().getText().substring(108, o.getDescription().getText().length());
                try {
                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.DECRYPT_MODE, server.getKey());
                    callbackHandler.callback(new JSerializable<HashMap>(new String(cipher.doFinal(DatatypeConverter.parseBase64Binary(encrypted)))).getObject());
                } catch (Exception e) {
                    e.printStackTrace();
                    callbackHandler.callback(new HashMap<String, String>());
                }
            }
        });
    }
}
