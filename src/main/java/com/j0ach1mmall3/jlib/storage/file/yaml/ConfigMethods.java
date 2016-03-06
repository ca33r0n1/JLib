package com.j0ach1mmall3.jlib.storage.file.yaml;

import com.j0ach1mmall3.jlib.logging.JLogger;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since Unknown
 * @deprecated {@link ConfigLoader}
 */
@Deprecated
public final class ConfigMethods {

    /**
     * Let nobody instantiate this class
     */
    private ConfigMethods() {
    }

    /**
     * Returns the Keys in a Config file of a Section
     * @param config The Config File
     * @param section The Section
     * @return The Keys
     * @deprecated {@link Config#getKeys(String)}
     */
    @Deprecated
    public static List<String> getKeys(FileConfiguration config, String section) {
        new JLogger().deprecation();
        List<String> keysList = new ArrayList<>();
        Set<String> keys = config.getConfigurationSection(section).getKeys(false);
        for(String key : keys){
            keysList.add(key);
        }
        return keysList;
    }
}
