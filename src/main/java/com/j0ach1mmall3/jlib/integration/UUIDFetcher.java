package com.j0ach1mmall3.jlib.integration;

import com.j0ach1mmall3.jlib.logging.JLogger;
import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import com.j0ach1mmall3.jlib.integration.profilefetcher.ProfileFetcher;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/12/15
 * @deprecated {@link ProfileFetcher}
 */
@Deprecated
@SuppressWarnings("deprecation")
public final class UUIDFetcher {
    private final String name;
    private final JLogger jLogger = new JLogger();

    /**
     * Constructs a new UUIDFetcher with the given name
     * @param name The name
     * @deprecated {@link ProfileFetcher#ProfileFetcher(Plugin)}
     */
    @Deprecated
    public UUIDFetcher(String name) {
        this.name = name;
    }

    /**
     * Returns the UUID of the name this UUIDFetcher instance is associated with
     * @return The UUID of the name
     * @throws IOException Thrown when we can't connect to the api servers
     * @deprecated {@link ProfileFetcher#getByName(String, CallbackHandler)}
     */
    @Deprecated
    public UUID getUniqueId() throws Exception {
        this.jLogger.deprecation();
        this.jLogger.warnIfSync();
        HttpURLConnection connection = (HttpURLConnection) new URL("https://api.mojang.com/users/profiles/minecraft/" + this.name).openConnection();
        return this.getUUID((String) ((JSONObject) new JSONParser().parse(new InputStreamReader(connection.getInputStream()))).get("id"));
    }

    /**
     * Calls back the name of the UUID this NameFetcher instance is associated with
     * @param plugin The JavaPlugin to fetch the name for
     * @param callbackHandler The CallbackHandler to call back to
     * @deprecated {@link ProfileFetcher#getByName(String, CallbackHandler)}
     */
    @Deprecated
    public void getUniqueIdAsync(JavaPlugin plugin, final CallbackHandler<UUID> callbackHandler) {
        this.jLogger.deprecation();
        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL("https://api.mojang.com/users/profiles/minecraft/" + UUIDFetcher.this.name).openConnection();
                    callbackHandler.callback(UUIDFetcher.this.getUUID((String) ((JSONObject) new JSONParser().parse(new InputStreamReader(connection.getInputStream()))).get("id")));
                } catch (Exception e) {
                    e.printStackTrace();
                    callbackHandler.callback(null);
                }
            }
        }, 0L);
    }

    /**
     * Creates a formatted UUID
     * @param id The ID to format
     * @return The formatted UUID
     */
    private UUID getUUID(String id) {
        return UUID.fromString(id.substring(0, 8) + '-' + id.substring(8, 12) + '-' + id.substring(12, 16) + '-' + id.substring(16, 20) + '-' +id.substring(20, 32));
    }

    /**
     * Returns the UUID of a specified name
     * @param name The UUID
     * @return The UUID of the name
     * @throws IOException Thrown when we can't connect to the api servers
     * @deprecated {@link ProfileFetcher#getByName(String, CallbackHandler)}
     */
    @Deprecated
    public static UUID getUUIDOf(String name) throws Exception {
        new JLogger().deprecation();
        return new UUIDFetcher(name).getUniqueId();
    }
}