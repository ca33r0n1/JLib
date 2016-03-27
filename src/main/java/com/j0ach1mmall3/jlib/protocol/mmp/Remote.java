package com.j0ach1mmall3.jlib.protocol.mmp;

import java.security.Key;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 27/03/2016
 */
public final class Remote {
    private final String host;
    private final int port;
    private final Key key;

    /**
     * Constructs a new Remote instance (Can be Client or Server)
     * @param host The Host address
     * @param port The Port
     * @param key The Encryption Key
     */
    public Remote(String host, int port, Key key) {
        this.host = host;
        this.port = port;
        this.key = key;
    }

    /**
     * Returns the Host address
     * @return The Host
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Returns the Port
     * @return The Port
     */
    public int getPort() {
        return this.port;
    }

    /**
     * Returns the Encryption Key
     * @return The Key
     */
    public Key getKey() {
        return this.key;
    }
}
