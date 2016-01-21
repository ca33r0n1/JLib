package com.j0ach1mmall3.jlib.integration.profilefetcher;

import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
     * @param plugin The Plugin associated with this ProfileFetcher
     */
    public ProfileFetcher(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Fetches the PlayerProfile from a name
     * @param name The name
     * @param callbackHandler The CallbackHandler to call back to
     */
    @SuppressWarnings("deprecation")
    public void getByName(final String name, final CallbackHandler<PlayerProfile> callbackHandler) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL("http://mcuuid.com/api/" + name).openConnection();
                    JSONObject jsonObject = (JSONObject) new JSONParser().parse(new InputStreamReader(connection.getInputStream()));
                    String name = (String) jsonObject.get("name");
                    String uuid = (String) jsonObject.get("uuid_formatted");
                    callbackHandler.callback(new PlayerProfile(name, uuid.equals("----")?null:UUID.fromString(uuid)));
                } catch (Exception e) {
                    e.printStackTrace();
                    callbackHandler.callback(null);
                }
            }
        }, 0L);
    }

    /**
     * Fetches the PlayerProfile from a uuid
     * @param uuid The uuid
     * @param callbackHandler The CallbackHandler to call back to
     */
    @SuppressWarnings("deprecation")
    public void getByUUID(final UUID uuid, final CallbackHandler<PlayerProfile> callbackHandler) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL("http://mcuuid.com/api/" + uuid).openConnection();
                    JSONObject jsonObject = (JSONObject) new JSONParser().parse(new InputStreamReader(connection.getInputStream()));
                    String name = (String) jsonObject.get("name");
                    String uuid = (String) jsonObject.get("uuid_formatted");
                    callbackHandler.callback(new PlayerProfile(name, uuid.equals("----")?null:UUID.fromString(uuid)));
                } catch (Exception e) {
                    e.printStackTrace();
                    callbackHandler.callback(null);
                }
            }
        }, 0L);
    }
}
