package com.j0ach1mmall3.jlib.storage.file.json;

import com.j0ach1mmall3.jlib.storage.StorageLoader;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/2015
 */
public class JsonLoader extends StorageLoader {
    protected final JsonConfig json;

    /**
     * Constructs a new JsonLoader, use this by extending the JsonLoader
     * @param name The name of the Json file
     * @param plugin The JavaPlugin associated with the Json file
     * @see JsonConfig
     */
    protected JsonLoader(JavaPlugin plugin, String name) {
        super(plugin, name);
        this.json = new JsonConfig(name, plugin);
    }
}
