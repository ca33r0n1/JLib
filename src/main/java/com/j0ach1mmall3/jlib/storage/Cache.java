package com.j0ach1mmall3.jlib.storage;

import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 18/01/16
 */
public abstract class Cache<C> {
    private final Map<Player, C> cache = new HashMap<>();

    /**
     * Loads a player into the Cache
     *
     * @param player The player
     */
    public final void load(Player player) {
        this.existsOffline(player.getUniqueId().toString(), o -> {
            if (o) {
                this.getOffline(player.getUniqueId().toString(), o1 -> this.cache.put(player, o1));
            } else this.cache.put(player, this.createOffline(player.getUniqueId().toString()));
        });
    }

    /**
     * Unloads a player from the Cache
     *
     * @param player The player to unload
     */
    public final void unload(Player player) {
        C c = this.cache.get(player);
        this.cache.remove(player);
        this.setOffline(player.getUniqueId().toString(), c);
    }

    /**
     * Returns the cached value of a player
     *
     * @param player The player
     * @return The cached value
     */
    public final C get(Player player) {
        return this.cache.get(player);
    }

    /**
     * Sets the cached value of a player
     *
     * @param player The player
     * @param c      The cached value
     */
    public final void set(Player player, C c) {
        this.cache.put(player, c);
    }

    /**
     * Returns whether a player is loaded into the Cache
     *
     * @param player The player
     * @return Whether the player is loaded
     */
    public final boolean isLoaded(Player player) {
        return this.cache.containsKey(player);
    }

    /**
     * Gets the offline value for a player
     *
     * @param player          The player
     * @param callbackHandler The CallbackHandler to call back to
     */
    public abstract void getOffline(String player, CallbackHandler<C> callbackHandler);

    /**
     * Sets the offline value for a player
     *
     * @param player The player
     * @param c      The value
     */
    public abstract void setOffline(String player, C c);

    /**
     * Checks whether an offline value exists for a player
     *
     * @param player          The player
     * @param callbackHandler The CallbackHandler to call back to
     */
    public abstract void existsOffline(String player, CallbackHandler<Boolean> callbackHandler);

    /**
     * Creates the offline value for a player
     *
     * @param player The player
     * @return The value (Default value)
     */
    public abstract C createOffline(String player);
}
