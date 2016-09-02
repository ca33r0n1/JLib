package com.j0ach1mmall3.jlib.methods;

import com.j0ach1mmall3.jlib.player.JLibPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.Location;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/11/15
 * @see {JLibPlayer}
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
     * @deprecated {@link JLibPlayer#playSound(Sound, Location)}
     */
    @Deprecated
    public static void playSound(Player player, Sound sound, Location location){
        new JLibPlayer(player).playSound(sound, location);
    }

    /**
     * Broadcasts a Sound at a Location
     * @param sound The Sound that should be played
     * @param location The Location where the Sound should be played
     */
    public static void broadcastSound(Sound sound, Location location){
        for(Player player : Bukkit.getOnlinePlayers()){
            new JLibPlayer(player).playSound(sound, location);
        }
    }

    /**
     * Plays a Sound for a player at his current Location
     * @param player The player for whom the Sound would play
     * @param sound The Sound that should be played
     * @deprecated {@link JLibPlayer#playSound(Sound)}
     */
    @Deprecated
    public static void playSound(Player player, Sound sound){
        new JLibPlayer(player).playSound(sound);
    }

    /**
     * Broadcasts a Sound
     * @param sound The Sound that should be played
     */
    public static void broadcastSound(Sound sound){
        for(Player player : Bukkit.getOnlinePlayers()){
            new JLibPlayer(player).playSound(sound);
        }
    }
}
