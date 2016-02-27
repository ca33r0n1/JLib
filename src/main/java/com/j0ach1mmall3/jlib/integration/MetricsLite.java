package com.j0ach1mmall3.jlib.integration;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.UUID;
import java.util.zip.GZIPOutputStream;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 20/02/16
 */
public class MetricsLite {
    private static final int REVISION = 7;
    private static final String BASE_URL = "http://report.mcstats.org";
    private static final String REPORT_URL = "/plugin/%s";
    private static final int PING_INTERVAL = 15;
    private static final Gson GSON = new Gson();

    private final Plugin plugin;
    private final YamlConfiguration configuration;
    private final File configurationFile;
    private final String guid;

    private BukkitTask task = null;

    /**
     * Constructs a new MetricsLite instance
     * @param plugin The Plugin
     */
    public MetricsLite(Plugin plugin) {
        if (plugin == null) throw new IllegalArgumentException("Plugin cannot be null");

        this.plugin = plugin;
        this.configurationFile = new File(new File(plugin.getDataFolder().getParentFile(), "PluginMetrics"), "config.yml");
        this.configuration = YamlConfiguration.loadConfiguration(this.configurationFile);

        this.configuration.addDefault("opt-out", false);
        this.configuration.addDefault("guid", UUID.randomUUID().toString());
        this.configuration.addDefault("debug", false);

        if (this.configuration.getString("guid") == null) {
            this.configuration.options().header("http://mcstats.org").copyDefaults(true);
            try {
                this.configuration.save(this.configurationFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.guid = this.configuration.getString("guid");
    }

    /**
     * Start measuring statistics. This will immediately create an async repeating task as the plugin and send
     * the initial data to the metrics backend, and then after that it will post in increments of
     * PING_INTERVAL * 1200 ticks.
     */
    public void start() {
        this.task = this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(this.plugin, new Runnable() {
            private boolean firstPost = true;

            @Override
            public void run() {
                try {
                    MetricsLite.this.postPlugin(!this.firstPost);
                    this.firstPost = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, PING_INTERVAL * 1200);
    }

    /**
     * Gets the online player (backwards compatibility)
     * @return online player amount
     */
    private int getOnlinePlayers() {
        try {
            Method onlinePlayerMethod = Server.class.getMethod("getOnlinePlayers");
            if(onlinePlayerMethod.getReturnType().equals(Collection.class)) return ((Collection<?>)onlinePlayerMethod.invoke(Bukkit.getServer())).size();
            else return ((Player[])onlinePlayerMethod.invoke(Bukkit.getServer())).length;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Generic method that posts a plugin to the metrics website
     * @param isPing Whether we are pinging
     * @throws Exception when an Exception occurs
     */
    private void postPlugin(boolean isPing) throws Exception {
        String pluginName = this.plugin.getDescription().getName();

        Data data = new Data(isPing ? "1" : "0");

        URLConnection connection = new URL(BASE_URL + String.format(REPORT_URL, this.urlEncode(pluginName))).openConnection();

        byte[] compressed = this.gzip(GSON.toJson(data));

        connection.addRequestProperty("User-Agent", "MCStats/" + REVISION);
        connection.addRequestProperty("Content-Type", "application/json");
        connection.addRequestProperty("Content-Encoding", "gzip");
        connection.addRequestProperty("Content-Length", Integer.toString(compressed.length));
        connection.addRequestProperty("Accept", "application/json");
        connection.addRequestProperty("Connection", "close");
        connection.setDoOutput(true);

        OutputStream os = connection.getOutputStream();
        os.write(compressed);
        os.flush();
        os.close();
    }

    /**
     * GZip compress a string of bytes
     * @param input The input string
     * @return The compressed byte array
     * @throws Exception when an Exception occurs
     */
    private byte[] gzip(String input) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream gzos = new GZIPOutputStream(baos);
        gzos.write(input.getBytes("UTF-8"));
        gzos.close();
        return baos.toByteArray();
    }

    /**
     * Encode text as UTF-8
     * @param text the text to encode
     * @return the encoded text, as UTF-8
     * @throws Exception when it failed to encode the text
     */
    private String urlEncode(final String text) throws Exception {
        return URLEncoder.encode(text, "UTF-8");
    }

    private class Data {
        private final String guid = MetricsLite.this.guid;
        private final String plugin_version = MetricsLite.this.plugin.getDescription().getVersion();
        private final String server_version = Bukkit.getVersion();
        private final String players_online = Integer.toString(MetricsLite.this.getOnlinePlayers());
        private final String osname = System.getProperty("os.name");
        private final String osarch = System.getProperty("os.arch").equals("amd64") ? "x86_64" : System.getProperty("os.arch");
        private final String osversion = System.getProperty("os.version");
        private final String cores = Integer.toString(Runtime.getRuntime().availableProcessors());
        private final String auth_mode = Bukkit.getServer().getOnlineMode() ? "1" : "0";
        private final String java_version = System.getProperty("java.version");
        private final String ping;

        /**
         * Constructs a new Data object
         * @param ping Whether we are pinging for the first time
         */
        public Data(String ping) {
            this.ping = ping;
        }
    }
}