package com.j0ach1mmall3.jlib.storage.file.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.j0ach1mmall3.jlib.storage.Storage;
import com.j0ach1mmall3.jlib.storage.StorageAction;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/15
 */
public final class JsonConfig<P extends JavaPlugin> extends Storage<P> {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final File file;

    /**
     * Constructs a new JsonConfig, shouldn't be used externally, use {@link JsonConfigLoader} instead
     * @param plugin The JavaPlugin associated with this JsonConfig
     * @param sourcePath The Source Path of the JsonConfig file
     * @param destinationPath The Destination Path of the JsonConfig file
     */
    JsonConfig(P plugin, String sourcePath, String destinationPath) {
        super(plugin, sourcePath);
        this.file = new File(destinationPath);
    }

    /**
     * Connects to the File, NOP
     */
    @Override
    public void connect() {
        // NOP
    }

    /**
     * Disconnects from the File, NOP
     */
    @Override
    public void disconnect() {
        // NOP
    }

    /**
     * Returns the JsonConfiguration instance associated with this JsonConfig
     * @param reference The reference Class
     * @return The JsonConfiguration instance associated with this JsonConfig
     */
    public JsonConfiguration getConfig(Class<? extends JsonConfiguration> reference) {
        StorageAction storageAction = new StorageAction(StorageAction.Type.JSON_GET, this.file.getPath(), reference.getName());
        JsonConfiguration cfg = null;
        try {
            String lines = new String(Files.readAllBytes(this.file.toPath()));
            cfg = this.gson.fromJson(lines, reference);
            storageAction.setSuccess(true);
        } catch (Exception e) {
            storageAction.setSuccess(false);
        }
        this.actions.add(storageAction);
        return cfg;
    }

    /**
     * Saves the JsonConfig
     * @param config The JsonConfiguration to save
     */
    public void saveConfig(JsonConfiguration config) {
        StorageAction storageAction = new StorageAction(StorageAction.Type.JSON_SAVE, this.file.getPath(), config.getClass().getName());
        try {
            PrintWriter printWriter = new PrintWriter(this.file);
            printWriter.write(this.gson.toJson(config));
            printWriter.close();
            storageAction.setSuccess(true);
        } catch(Exception e) {
            storageAction.setSuccess(false);
        }
        this.actions.add(storageAction);
    }

    /**
     * Saves the default JsonConfig
     */
    public void saveDefaultConfig() {
        if (!this.file.exists()) {
            StorageAction storageAction = new StorageAction(StorageAction.Type.JSON_SAVEDEFAULT, this.file.getPath());
            try (InputStream in = this.plugin.getResource(this.name)) {
                File parent = new File(this.file.getParent());
                if(!parent.exists()) parent.mkdirs();

                Files.copy(in, this.file.toPath());

                storageAction.setSuccess(true);
            } catch (Exception e) {
                storageAction.setSuccess(false);
            }
            this.actions.add(storageAction);
        }
    }

    /**
     * Returns the File associated with this JsonConfig
     * @return The File
     */
    public File getFile() {
        return this.file;
    }
}
