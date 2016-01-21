package com.j0ach1mmall3.jlib.integration.gist;

import com.google.gson.Gson;
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
public class GistUploader {
    private final static Gson GSON = new Gson();
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
                try {
                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.github.com/gists").openConnection();
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    OutputStream out = conn.getOutputStream();
                    out.write(GSON.toJson(GistUploader.this.gist).replace("publik", "public").getBytes("UTF-8"));
                    out.close();
                    conn.getInputStream().close();
                    String link = conn.getHeaderField("Location").replace("api.github.com/gists", "gist.github.com/anonymous");
                    callbackHandler.callback(link);
                } catch (Exception e) {
                    e.printStackTrace();
                    callbackHandler.callback(ChatColor.RED + "Error!");
                }
            }
        });
    }
}
