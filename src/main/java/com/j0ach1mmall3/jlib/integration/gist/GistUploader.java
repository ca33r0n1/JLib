package com.j0ach1mmall3.jlib.integration.gist;

import com.google.gson.Gson;
import com.j0ach1mmall3.jlib.logging.JLogger;
import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 8/01/16
 */
public final class GistUploader {
    private static final Gson GSON = new Gson();

    private final Gist gist;

    /**
     * Constructs a new GistUploader
     * @param gist The Gist associated with this GistUploader
     */
    public GistUploader(Gist gist) {
        this.gist = gist;
    }

    /**
     * Uploads the Gist
     * @param plugin The plugin to upload this with
     * @param callbackHandler The CallbackHandler to call back the URL to
     */
    @SuppressWarnings("deprecation")
    public void upload(Plugin plugin, final CallbackHandler<String> callbackHandler) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                callbackHandler.callback(GistUploader.this.upload());
            }
        });
    }

    /**
     * Uploads the Gist sync
     * @return The link
     */
    public String upload() {
        new JLogger().warnIfSync();
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.github.com/gists").openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            try(OutputStream outputStream = conn.getOutputStream()) {
                outputStream.write(GSON.toJson(this.gist).replace("publik", "public").getBytes("UTF-8"));
            }
            return conn.getHeaderField("Location").replace("api.github.com/gists", "gist.github.com/anonymous");
        } catch (Exception e) {
            e.printStackTrace();
            return ChatColor.RED + "Error!";
        }
    }
}