package com.j0ach1mmall3.jlib.minigameapi.arena;

import org.bukkit.Location;
import org.bukkit.block.BlockState;

import java.util.HashMap;
import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/09/15
 */
public final class ArenaBlockRestorer {
    private final Map<Location, BlockState> blocks = new HashMap<>();

    /**
     * Constructs an ArenaBlockRestorer, shouldn't be used externally
     */
    ArenaBlockRestorer() {

    }

    /**
     * Adds a BlockState to restore to the ArenaBlockRestorer
     *
     * @param location   The Location of the BlockState
     * @param blockState The BlockState
     */
    public void addBlock(Location location, BlockState blockState) {
        if (this.blocks.containsKey(location)) return;
        this.blocks.put(location, blockState);
    }

    /**
     * Restores all the BlockStates
     */
    public void restore() {
        this.blocks.values().forEach(BlockState::update);
        this.blocks.clear();
    }
}
