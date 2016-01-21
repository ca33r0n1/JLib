package com.j0ach1mmall3.jlib.jtp;

import com.google.common.collect.Sets;
import org.bukkit.plugin.Plugin;

import java.util.Set;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 17/01/16
 */
public abstract class RemoteHolder<R extends Remote> {
    protected final Plugin plugin;
    protected boolean alive = true;
    protected final Set<R> remotes = Sets.newConcurrentHashSet();

    /**
     * Constructs a new RemoteHolder, an instance that holds Remote instances
     * @param plugin The Plugin associated with this RemoteHolder
     */
    protected RemoteHolder(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Adds a Remote instance to this RemoteHolder
     * @param remote The Remote instance
     */
    final void addRemote(R remote) {
        this.remotes.add(remote);
    }

    /**
     * Returns the Remote instances in the RemoteHolder
     * @return The Remote instances
     */
    final Set<R> getRemotes() {
        return this.remotes;
    }

    /**
     * Returns whether this RemoteHolder is alive
     * @return Whether this RemoteHolder is alive
     */
    final boolean isAlive() {
        return this.alive;
    }

    /**
     * Connects the RemoteHolder to a Remote instance
     * @param remote The Remote instance to connect to
     */
    abstract void connect(R remote);

    /**
     * Disconnects the RemoteHolder from a Remote instance
     * @param remote The Remote instance to disconnect from
     */
    abstract void disconnect(R remote);

    /**
     * Stops the RemoteHolder
     */
    abstract void stop();
}
