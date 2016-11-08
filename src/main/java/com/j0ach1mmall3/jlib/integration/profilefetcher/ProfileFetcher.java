package com.j0ach1mmall3.jlib.integration.profilefetcher;

import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/01/16
 */
public final class ProfileFetcher {
    private final Plugin plugin;

    /**
     * Constructs a new ProfileFetcher
     *
     * @param plugin The Plugin associated with this ProfileFetcher
     */
    public ProfileFetcher(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Fetches the PlayerProfile from a name
     *
     * @param name            The name
     * @param callbackHandler The CallbackHandler to call back to
     */
    @SuppressWarnings("deprecation")
    public void getByName(String name, CallbackHandler<PlayerProfile> callbackHandler) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, () -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL("https://api.mcuuid.com/txt/uuid/" + name).openConnection();
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    callbackHandler.callback(new PlayerProfile(name, UUID.fromString(bufferedReader.readLine())));
                }
            } catch (Exception e) {
                e.printStackTrace();
                callbackHandler.callback(null);
            }
        }, 0L);
    }

    /**
     * Fetches the PlayerProfile from a uuid
     *
     * @param uuid            The uuid
     * @param callbackHandler The CallbackHandler to call back to
     */
    @SuppressWarnings("deprecation")
    public void getByUUID(UUID uuid, CallbackHandler<PlayerProfile> callbackHandler) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, () -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL("https://api.mcuuid.com/txt/name/" + uuid).openConnection();
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    callbackHandler.callback(new PlayerProfile(bufferedReader.readLine(), uuid));
                }
            } catch (Exception e) {
                e.printStackTrace();
                callbackHandler.callback(null);
            }
        }, 0L);
    }
}
