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
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 29/06/15
 */
public final class Config extends Storage {
    private final File file;

    /**
     * Constructs a new Config, shouldn't be used externally, use {@link ConfigLoader} instead
     * @param plugin The JavaPlugin associated with this Config
     * @param sourcePath The Source Path of the Config file
     * @param destinationPath The Destination Path of the Config file
     */
    Config(JavaPlugin plugin, String sourcePath, String destinationPath) {
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
     * @see FileConfiguration
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
     * @see FileConfiguration
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
            try {
                File parent = new File(this.file.getParent());
                if(!parent.exists()) parent.mkdirs();
                InputStream in = this.plugin.getResource(this.name);
                OutputStream out = new FileOutputStream(this.file);
                byte[] buf = new byte[1024];
                int len;
                while((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.close();
                in.close();
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
    public List<String> getKeys(String section){
        ConfigurationSection cfgsection = this.getConfig().getConfigurationSection(section);
        if(cfgsection == null ) return new ArrayList<>();
        return Lists.newArrayList(cfgsection.getKeys(false));
    }

    /**
     * Returns a CustomItem specified in the Config File
     * @param config The Config
     * @param path The path to the CustomItem
     * @return The CustomItem
     */
    public CustomItem getItem(FileConfiguration config, String path) {
        return new CustomItem(
                Parsing.parseMaterial(config.getString(path + ".Item")),
                1,
                Parsing.parseData(config.getString(path + ".Item")),
                ChatColor.translateAlternateColorCodes('&', config.getString(path + ".Name")),
                ChatColor.translateAlternateColorCodes('&', config.getString(path + ".Lore")));
    }

    /**
     * Returns a GuiItem specified in the Config File
     * @param config The Config
     * @param path The path to the GuiItem
     * @return The GuiItem
     */
    public GuiItem getGuiItem(FileConfiguration config, String path) {
        return new GuiItem(this.getItem(config, path), config.getInt(path + ".Position"));
    }

    /**
     * Returns a GUI specified in the Config File
     * @param config The Config
     * @param path The path to the GUI
     * @return The GUI
     */
    public GUI getGui(FileConfiguration config, String path) {
        GUI gui = new GUI(ChatColor.translateAlternateColorCodes('&', config.getString(path + ".Name")), config.getInt(path + ".Size"));
        for(String s : this.getKeys(path + ".Items")) {
            gui.setItem(Parsing.parseInt(s), this.getItem(config, path + ".Items." + s));
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
