package com.j0ach1mmall3.jlib.storage.file.json;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/2015
 */
public class JsonConfig {
    private final JavaPlugin plugin;
    private final File path;
    private final String name;
    private final File file;

    /**
     * Constructs a new JsonConfig, shouldn't be used externally, use JsonLoader instead
     */
    private JsonConfig(String name, String path, JavaPlugin plugin) {
        this.plugin = plugin;
        this.path = new File(path);
        this.name = name;
        this.file = new File(path, name);
    }

    /**
     * Constructs a new JsonConfig, shouldn't be used externally, use JsonLoader instead
     */
    JsonConfig(String name, JavaPlugin plugin){
        this(name, plugin.getDataFolder().getPath(), plugin);
    }
}
