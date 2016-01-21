package com.j0ach1mmall3.jlib.jtp;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 17/01/16
 */
public final class RemoteServer extends Remote {
    private final int port;
    private final String tag;

    /**
     * Constructs a new remote server instance
     * @param ip The IP Address associated with this remote server
     * @param port The port associated with this remote server
     * @param tag The tag associated with this remote server
     */
    public RemoteServer(String ip, int port, String tag) {
        super(ip);
        this.port = port;
        this.tag = tag;
    }

    /**
     * Returns the port associated with this remote server
     * @return The port
     */
    public int getPort() {
        return this.port;
    }

    /**
     * Returns the tag associated with this remote server
     * @return The tag
     */
    public String getTag() {
        return this.tag;
    }
}
