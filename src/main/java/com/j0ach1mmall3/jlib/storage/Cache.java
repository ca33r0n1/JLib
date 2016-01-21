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

    public final void load(final Player player) {
        this.create(player.getUniqueId().toString());
        this.getOffline(player.getUniqueId().toString(), new CallbackHandler<C>() {
            @Override
            public void callback(C c) {
                Cache.this.cache.put(player, c);
            }
        });
    }

    public final void unload(Player player) {
        this.setOffline(player.getUniqueId().toString(), this.cache.get(player));
    }

    public final C get(Player player) {
        return this.cache.get(player);
    }

    public final void set(Player player, C c) {
        this.cache.put(player, c);
    }

    public final boolean isLoaded(Player player) { return this.cache.containsKey(player); }

    public abstract void getOffline(String player, CallbackHandler<C> callbackHandler);

    public abstract void setOffline(String player, C c);

    public abstract void create(String player);
}
