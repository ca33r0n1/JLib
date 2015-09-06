package com.j0ach1mmall3.jlib.storage.yaml;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigMethods {
	public static List<String> getKeys(FileConfiguration config, String section){
		List<String> keysList = new ArrayList<String>();
		Set<String> keys = config.getConfigurationSection(section).getKeys(false);
		for(String key : keys){
            keysList.add(key);
		}
		return keysList;
	}
}
