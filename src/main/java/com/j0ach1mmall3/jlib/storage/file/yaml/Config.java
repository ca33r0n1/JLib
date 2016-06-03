package com.j0ach1mmall3.jlib.storage.file.yaml;

import com.google.common.collect.Lists;
import com.j0ach1mmall3.jlib.inventory.CustomItem;
import com.j0ach1mmall3.jlib.inventory.GUI;
import com.j0ach1mmall3.jlib.inventory.GuiItem;
import com.j0ach1mmall3.jlib.methods.Parsing;
import com.j0ach1mmall3.jlib.storage.Storage;
import com.j0ach1mmall3.jlib.storage.StorageAction;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 29/06/15
 */
public final class Config<P extends JavaPlugin> extends Storage<P> {
    private final File file;

    /**
     * Constructs a new Config, shouldn't be used externally, use {@link ConfigLoader} instead
     * @param plugin The JavaPlugin associated with this Config
     * @param sourcePath The Source Path of the Config file
     * @param destinationPath The Destination Path of the Config file
     */
    Config(P plugin, String sourcePath, String destinationPath) {
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
     * Returns the Bukkit FileConfiguration instance
     * @return The Bukkit FileConfiguration instance
     */
    public FileConfiguration getConfig() {
        StorageAction storageAction = new StorageAction(StorageAction.Type.FILE_GET, this.file.getPath());
        FileConfiguration cfg = null;
        try {
            cfg = YamlConfiguration.loadConfiguration(this.file);
            storageAction.setSuccess(true);
        } catch (Exception e) {
            storageAction.setSuccess(false);
        }
        this.actions.add(storageAction);
        return cfg;
    }

    /**
     * Saves the Config
     * @param config The FileConfiguration to save
     */
    public void saveConfig(FileConfiguration config) {
        StorageAction storageAction = new StorageAction(StorageAction.Type.FILE_SAVE, this.file.getPath(), config.getName());
        try {
            config.save(this.file);
            storageAction.setSuccess(true);
        } catch(Exception e) {
            storageAction.setSuccess(false);
        }
        this.actions.add(storageAction);
    }

    /**
     * Reloads the Config
     */
    public void reloadConfig() {
        if(this.plugin.getResource(this.name) != null) {
            StorageAction storageAction = new StorageAction(StorageAction.Type.FILE_RELOAD, this.file.getPath());
            try {
                FileConfiguration config = YamlConfiguration.loadConfiguration(this.file);
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(this.plugin.getResource(this.name)));
                config.setDefaults(defConfig);
                storageAction.setSuccess(true);
            } catch (Exception e) {
                storageAction.setSuccess(false);
            }
            this.actions.add(storageAction);
        }
    }

    /**
     * Saves the default Config
     */
    public void saveDefaultConfig() {
        if (!this.file.exists()) {
            StorageAction storageAction = new StorageAction(StorageAction.Type.FILE_SAVEDEFAULT, this.file.getPath());
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
     * Returns the Keys in the Config file of a Section
     * @param section The Section
     * @return The Keys
     */
    public List<String> getKeys(String section) {
        List<String> keys = new ArrayList<>();
        StorageAction storageAction = new StorageAction(StorageAction.Type.FILE_GETKEYS, this.file.getPath(), section);
        try {
            ConfigurationSection cfgsection = this.getConfig().getConfigurationSection(section);
            if(cfgsection == null ) return new ArrayList<>();
            keys = Lists.newArrayList(cfgsection.getKeys(false));
            storageAction.setSuccess(true);
        } catch (Exception e) {
            storageAction.setSuccess(false);
        }
        this.actions.add(storageAction);
        return keys;
    }

    /**
     * Returns a ItemStack specified in the Config File
     * @param config The Config
     * @param path The path to the ItemStack
     * @return The ItemStack
     * @deprecated {@link Config#getItemNew(FileConfiguration, String)}
     */
    @Deprecated
    public ItemStack getItem(FileConfiguration config, String path) {
        return new CustomItem(
                Parsing.parseMaterial(config.getString(path + ".Item")),
                1,
                Parsing.parseData(config.getString(path + ".Item")),
                ChatColor.translateAlternateColorCodes('&', config.getString(path + ".Name")),
                ChatColor.translateAlternateColorCodes('&', config.getString(path + ".Lore")));
    }

    /**
     * Returns an ItemStack specified in the Config File (New format)
     * @param config The Config
     * @param path The path to the ItemStack
     * @return The ItemStack
     */
    public ItemStack getItemNew(FileConfiguration config, String path) {
        return Parsing.parseItemStack(config.getString(path));
    }

    /**
     * Returns a GuiItem specified in the Config File
     * @param config The Config
     * @param path The path to the GuiItem
     * @return The GuiItem
     * @deprecated {@link Config#getGuiItemNew(FileConfiguration, String)}
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public GuiItem getGuiItem(FileConfiguration config, String path) {
        return new GuiItem(this.getItem(config, path), config.getInt(path + ".Position"));
    }

    /**
     * Returns a GuiItem specified in the Config File (New format)
     * @param config The Config
     * @param path The path to the GuiItem
     * @return The GuiItem
     */
    public GuiItem getGuiItemNew(FileConfiguration config, String path) {
        return new GuiItem(this.getItemNew(config, path + ".Item"), config.getInt(path + ".Position"));
    }

    /**
     * Returns a GUI specified in the Config File
     * @param config The Config
     * @param path The path to the GUI
     * @return The GUI
     * @deprecated {@link Config#getGuiNew(FileConfiguration, String)}
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public GUI getGui(FileConfiguration config, String path) {
        GUI gui = new GUI(ChatColor.translateAlternateColorCodes('&', config.getString(path + ".Name")), config.getInt(path + ".Size"));
        for(String s : this.getKeys(path + ".Items")) {
            gui.setItem(Parsing.parseInt(s), this.getItem(config, path + ".Items." + s));
        }
        return gui;
    }

    /**
     * Returns a GUI specified in the Config File (New format)
     * @param config The Config
     * @param path The path to the GUI
     * @return The GUI
     */
    public GUI getGuiNew(FileConfiguration config, String path) {
        GUI gui = new GUI(ChatColor.translateAlternateColorCodes('&', config.getString(path + ".Name")), config.getInt(path + ".Size"));
        for(String s : this.getKeys(path + ".Items")) {
            gui.setItem(Parsing.parseInt(s), this.getItemNew(config, path + ".Items." + s));
        }
        return gui;
    }

    /**
     * Returns the File associated with this Config
     * @return The File
     */
    public File getFile() {
        return this.file;
    }
}
