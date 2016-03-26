package com.j0ach1mmall3.jlib.minigameapi.map;

import org.bukkit.block.Sign;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 11/03/2016
 */
public final class GameSign {
    private final String[] lines;
    private final Runnable onClick;

    /**
     * Constructs a new GameSign
     * @param lines The lines
     * @param onClick The runnable to run upon click
     */
    public GameSign(String[] lines, Runnable onClick) {
        this.lines = lines;
        this.onClick = onClick;
    }

    /**
     * Handles a PlayerInteractEvent
     * @param e The PlayerInteractEvent
     */
    public void handleClick(PlayerInteractEvent e) {
        if(e.getClickedBlock() != null && e.getClickedBlock().getState() instanceof Sign && Arrays.equals(((Sign) e.getClickedBlock().getState()).getLines(), this.lines)) this.onClick.run();
    }
}
