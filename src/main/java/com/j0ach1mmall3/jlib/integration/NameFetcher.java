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
public final class NameFetcher {
    private final UUID uuid;
    private final JLogger jLogger = new JLogger();

    /**
     * Constructs a new NameFetcher instance with the given UUID
     * @param uuid The UUID
     * @deprecated {@link ProfileFetcher#ProfileFetcher(Plugin)}
     */
    @Deprecated
    public NameFetcher(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Returns the name of the UUID this NameFetcher instance is associated with
     * @return The name of the UUID
     * @throws IOException Thrown when we can't connect to the session servers
     * @deprecated {@link ProfileFetcher#getByUUID(UUID, CallbackHandler)}
     */
    @Deprecated
    public String getName() throws Exception {
        this.jLogger.deprecation();
        this.jLogger.warnIfSync();
        HttpURLConnection connection = (HttpURLConnection) new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + this.uuid.toString().replace("-", "")).openConnection();
        JSONObject response = (JSONObject) new JSONParser().parse(new InputStreamReader(connection.getInputStream()));
        return (String) response.get("name");
    }

    /**
     * Calls back the name of the UUID this NameFetcher instance is associated with
     * @param plugin The JavaPlugin to fetch the name for
     * @param callbackHandler The CallbackHandler to call back to
     * @deprecated {@link ProfileFetcher#getByUUID(UUID, CallbackHandler)}
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public void getNameAsync(JavaPlugin plugin, final CallbackHandler<String> callbackHandler) {
        this.jLogger.deprecation();
        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + NameFetcher.this.uuid.toString().replace("-", "")).openConnection();
                    JSONObject response = (JSONObject) new JSONParser().parse(new InputStreamReader(connection.getInputStream()));
                    callbackHandler.callback((String) response.get("name"));
                } catch (Exception e) {
                    e.printStackTrace();
                    callbackHandler.callback("");
                }
            }
        }, 0L);
    }

    /**
     * Returns the name of a specified UUID
     * @param uuid The UUID
     * @return The name of the UUID
     * @throws IOException Thrown when we can't connect to the session servers
     * @deprecated {@link ProfileFetcher#getByUUID(UUID, CallbackHandler)}
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public static String getNameOf(UUID uuid) throws Exception {
        return new NameFetcher(uuid).getName();
    }
}
