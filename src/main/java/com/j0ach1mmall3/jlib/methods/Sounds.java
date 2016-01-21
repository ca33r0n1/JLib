package com.j0ach1mmall3.jlib.methods;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.Location;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/11/2015
 */
public final class Sounds {

	/**
	 * Let nobody instantiate this class
	 */
	private Sounds() {
	}

    /**
     * Plays a Sound for a player at a Location
     * @param player The player for whom the Sound would play
     * @param sound The Sound that should be played
     * @param location The Location where the Sound should be played
     * @see Sound
     */
    public static void playSound(Player player, Sound sound, Location location){
        player.playSound(location, sound, 0.5f, 1);
	}

    /**
     * Broadcasts a Sound at a Location
     * @param sound The Sound that should be played
     * @param location The Location where the Sound should be played
     * @see Sound
     */
    public static void broadcastSound(Sound sound, Location location){
		for(Player player : Bukkit.getOnlinePlayers()){
			playSound(player, sound, location);
		}
	}

    /**
     * Plays a Sound for a player at his current Location
     * @param player The player for whom the Sound would play
     * @param sound The Sound that should be played
     * @see Sound
     */
	public static void playSound(Player player, Sound sound){
		playSound(player, sound, player.getEyeLocation());
	}

    /**
     * Broadcasts a Sound
     * @param sound The Sound that should be played
     * @see Sound
     */
	public static void broadcastSound(Sound sound){
		for(Player player : Bukkit.getOnlinePlayers()){
			playSound(player, sound);
		}
	}
}
