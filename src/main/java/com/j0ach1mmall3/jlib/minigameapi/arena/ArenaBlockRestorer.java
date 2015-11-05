package com.j0ach1mmall3.jlib.minigameapi.arena;

import org.bukkit.Location;
import org.bukkit.block.BlockState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/09/2015
 */
public final class ArenaBlockRestorer {
    private final List<Map<Location, BlockState>> blocks = new ArrayList<>();

    /**
     * Constructs an ArenaBlockRestorer, shouldn't be used externally
     */
    ArenaBlockRestorer() {

    }

    /**
     * Adds a BlockState to restore to the ArenaBlockRestorer
     * @param location The Location of the BlockState
     * @param blockState The BlockState
     * @see BlockState
     */
    public void addBlock(Location location, BlockState blockState) {
        Map<Location, BlockState> blockz = new HashMap<>();
        blockz.put(location, blockState);
        this.blocks.add(blockz);
    }

    /**
     * Restores all the BlockStates
     * @see BlockState
     */
    public void restore() {
        for(Map<Location, BlockState> map : this.blocks) {
            for(Location l : map.keySet()) {
                map.get(l).update();
            }
        }
        this.blocks.clear();
    }
}
