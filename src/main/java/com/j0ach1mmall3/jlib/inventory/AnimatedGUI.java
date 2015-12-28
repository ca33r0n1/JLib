package com.j0ach1mmall3.jlib.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 27/12/15
 */
public final class AnimatedGUI {
    private final List<? extends GUI> guis;
    private final List<Player> players = new ArrayList<>();
    private final long interval;
    private final boolean repeat;
    private int taskId;
    private int count;

    /**
     * Creates a new Animated GUI
     * @param guis The GUIs that make up this Animated GUI
     * @param interval The interval between the updates
     * @param repeat Whether the sequence should repeat itself after all the GUIs are shown
     */
    public AnimatedGUI(List<? extends GUI> guis, long interval, boolean repeat) {
        this.guis = guis;
        this.interval = interval;
        this.repeat = repeat;
    }

    /**
     * Creates a new Animated GUI
     * @param guis The GUIs that make up this Animated GUI
     * @param interval The interval between the updates
     */
    public AnimatedGUI(List<? extends GUI> guis, long interval) {
        this(guis, interval, false);
    }

    /**     * Returns the list of GUIs that make up this Animated GUI
     * @return The list of GUIs
     */
    public List<? extends GUI> getGuis() {
        return this.guis;
    }

    /**
     * Adds a Player to this GUI
     * @param player The Player
     */
    public void addPlayer(Player player) {
        this.players.add(player);
        this.open(player);
    }

    /**
     * Removes a Player from this GUI
     * @param player The Player
     */
    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    /**
     * Returns the interval between the updates
     * @return the interval between the updates
     */
    public long getInterval() {
        return this.interval;
    }

    /**
     * Returns whether the sequence should repeat itself after all the GUIs are shown
     * @return whether the sequence should repeat itself
     */
    public boolean isRepeat() {
        return this.repeat;
    }

    /**
     * Starts the task of showing the GUIs
     * @param plugin The plugin that is associated with this task
     */
    public void start(final Plugin plugin) {
        this.taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                if(AnimatedGUI.this.count >= AnimatedGUI.this.guis.size()) {
                    if(AnimatedGUI.this.repeat) AnimatedGUI.this.count = 0;
                    else {
                        Bukkit.getScheduler().cancelTask(AnimatedGUI.this.taskId);
                        return;
                    }
                }
                AnimatedGUI.this.count++;
                for(Player p : new ArrayList<>(AnimatedGUI.this.players)) {
                    if(p.isOnline() && p.getOpenInventory() != null && AnimatedGUI.this.isInventory(p.getOpenInventory().getTopInventory())) AnimatedGUI.this.open(p);
                    else AnimatedGUI.this.players.remove(p);
                }
            }
        }, 0, this.interval);
    }

    /**
     * Stops the task of showing the GUIs
     */
    public void stop() {
        Bukkit.getScheduler().cancelTask(AnimatedGUI.this.taskId);
    }

    /**
     * Determines whether a player has legitimately clicked in this Animated GUI
     * @param event The InventoryClickEvent
     * @return Wether the player has clicked in the Animated GUI
     */
    public boolean hasClicked(InventoryClickEvent event) {
        for(GUI gui : this.guis) {
            if(gui.hasClicked(event)) return true;
        }
        return false;
    }

    /**
     * Returns whether a specified Inventory is part of this Animated GUI
     * @param inventory The Inventory to check
     * @return Whether it's part of this Animated GUI
     */
    public boolean isInventory(Inventory inventory) {
        for(GUI gui : this.guis) {
            if(gui.getName().equals(inventory.getName())) return true;
        }
        return false;
    }


    private void open(Player p) {
        int id = this.count-1;
        AnimatedGUI.this.guis.get(id).open(p);
    }
}
