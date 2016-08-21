package com.j0ach1mmall3.jlib.integration.dropfile;

import com.j0ach1mmall3.jlib.logging.JLogger;
import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/06/2016
 */
public final class DropFileUploader {
    private static final String BOUNDARY = "---------------------------00000000000000000000000000000";

    private final DropFile dropFile;

    /**
     * Constructs a new DropFileUploader
     * @param dropFile The DropFile associated with this DropFileUploader
     */
    public DropFileUploader(DropFile dropFile) {
        this.dropFile = dropFile;
    }

    /**
     * Uploads the DropFile
     * @param plugin The plugin to upload this with
     * @param callbackHandler The CallbackHandler to call back the URL to
     */
    @SuppressWarnings("deprecation")
    public void upload(Plugin plugin, final CallbackHandler<String> callbackHandler) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                callbackHandler.callback(DropFileUploader.this.upload());
            }
        });
    }

    /**
     * Uploads the DropFile sync
     * @return The URL
     */
    public String upload() {
        new JLogger().warnIfSync();
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL("https://d1.dropfile.to/upload").openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

            try(OutputStream outputStream = conn.getOutputStream()) {
                outputStream.write(("--" + BOUNDARY + "\r\n").getBytes());
                outputStream.write(("Content-Disposition: form-data; name=\"files[]\"; filename=\"" + this.dropFile.getName() + "\"\r\n").getBytes());
                outputStream.write("Content-Type: text/plain\r\n".getBytes());
                outputStream.write("\r\n".getBytes());
                outputStream.write(this.dropFile.getData());
                outputStream.write("\r\n".getBytes());
                outputStream.write(("--" + BOUNDARY + "--").getBytes());
            }
            try(Scanner scanner = new Scanner(conn.getInputStream())) {
                return scanner.next().split("\"url\":\"")[1].split("\"")[0].replace("\\", "");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ChatColor.RED + "Error!";
        }
    }
}
