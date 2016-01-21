package com.j0ach1mmall3.jlib.jtp;

import java.net.Socket;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 17/01/16
 */
public abstract class Remote {
    private final String ip;
    private Socket socket;

    /**
     * Constructs a new Remote instance
     * @param ip The IP Address associated with this Remote instance
     */
    public Remote(String ip) {
        this.ip = ip;
    }

    /**
     * Returns the IP Address associated with this Remote instance
     * @return The IP Address
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * Returns the Socket associated with this Remote instance
     * @return The Socket
     */
    public Socket getSocket() {
        return this.socket;
    }

    /**
     * Sets the Socket associated with this Remote instance
     * @param socket The Socket
     */
    void setSocket(Socket socket) {
        if(this.socket != null) return;
        this.socket = socket;
    }
}
