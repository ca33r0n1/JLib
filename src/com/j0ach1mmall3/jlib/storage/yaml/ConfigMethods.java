package com.j0ach1mmall3.jlib.storage.yaml;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ConfigMethods {
    @Deprecated
	public static List<String> getKeys(FileConfiguration config, String section){
		List<String> keysList = new ArrayList<String>();
		Set<String> keys = config.getConfigurationSection(section).getKeys(false);
		for(String key : keys){
            keysList.add(key);
		}
		return keysList;
	}
}
