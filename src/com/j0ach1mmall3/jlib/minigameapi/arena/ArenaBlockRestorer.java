package com.j0ach1mmall3.jlib.minigameapi.arena;

import org.bukkit.Location;
import org.bukkit.block.BlockState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by j0ach1mmall3 on 16:38 5/09/2015 using IntelliJ IDEA.
 */
public class ArenaBlockRestorer {
    private final List<HashMap<Location, BlockState>> blocks = new ArrayList<>();

    public void addBlock(Location l, BlockState from) {
        HashMap<Location, BlockState> blockz = new HashMap<>();
        blockz.put(l, from);
        blocks.add(blockz);
    }

    public void restore() {
        for(HashMap<Location, BlockState> map : blocks) {
            for(Location l : map.keySet()) {
                map.get(l).update();
            }
        }
        blocks.clear();
    }
}
