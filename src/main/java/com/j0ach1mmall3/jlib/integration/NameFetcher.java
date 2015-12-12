package com.j0ach1mmall3.jlib.integration;

import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import org.bukkit.Bukkit;
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
 * @since 4/12/2015
 */
public final class NameFetcher {
    private final UUID uuid;

    /**
     * Creates a new NameFetcher instance with the given UUID
     * @param uuid The UUID
     */
    public NameFetcher(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Returns the name of the UUID this NameFetcher instance is associated with
     * @return The name of the UUID
     * @throws IOException Thrown when we can't connect to the session servers
     * @deprecated {@link NameFetcher#getNameAsync(JavaPlugin, CallbackHandler)}
     */
    @Deprecated
    public String getName() throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + this.uuid.toString().replace("-", "")).openConnection();
        JSONObject response = (JSONObject) new JSONParser().parse(new InputStreamReader(connection.getInputStream()));
        return (String) response.get("name");
    }

    /**
     * Calls back the name of the UUID this NameFetcher instance is associated with
     * @param plugin The JavaPlugin to fetch the name for
     * @param callbackHandler The CallbackHandler to call back to
     */
    @SuppressWarnings("deprecation")
    public void getNameAsync(JavaPlugin plugin, final CallbackHandler<String> callbackHandler) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    callbackHandler.callback(NameFetcher.this.getName());
                } catch (Exception e) {
                    callbackHandler.callback("");
                }
            }
        });
    }

    /**
     * Returns the name of a specified UUID
     * @param uuid The UUID
     * @return The name of the UUID
     * @throws IOException Thrown when we can't connect to the session servers
     * @deprecated {@link NameFetcher#getNameAsync(JavaPlugin, CallbackHandler)}
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public static String getNameOf(UUID uuid) throws Exception {
        return new NameFetcher(uuid).getName();
    }
}
