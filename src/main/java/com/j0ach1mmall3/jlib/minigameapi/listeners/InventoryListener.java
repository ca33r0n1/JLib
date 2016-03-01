package com.j0ach1mmall3.jlib.minigameapi.listeners;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import com.j0ach1mmall3.jlib.minigameapi.team.Team;
import com.j0ach1mmall3.jlib.minigameapi.team.TeamProperties;
import com.j0ach1mmall3.jlib.minigameapi.team.TeamSelectGUI;
import com.j0ach1mmall3.jlib.minigameapi.team.events.PlayerSelectTeamEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/02/16
 */
public final class InventoryListener implements Listener {
    private final Main plugin;

    /**
     * Initialises the InventoryListener
     * @param plugin Main plugin
     */
    public InventoryListener(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * The InventoryClickEvent Listener
     * @param e The InventoryClickEvent
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClick(InventoryClickEvent e) {
        for(Game game : this.plugin.getApi().getGames()) {
            TeamProperties teamProperties = game.getTeamProperties();
            if(!teamProperties.isMoveSelectItem()) {
                ItemStack teamSelectItem = teamProperties.getTeamSelectItem().getItem();
                if(teamSelectItem.isSimilar(e.getCurrentItem())) {
                    e.setCancelled(true);
                    return;
                }
            }
            if(teamProperties.getTeamSelectGUI() != null) {
                TeamSelectGUI teamSelectGUI = teamProperties.getTeamSelectGUI();
                if(teamSelectGUI.getGui().hasClicked(e)) {
                    Team team = teamSelectGUI.getTeam(e.getSlot());
                    Player p = (Player) e.getWhoClicked();
                    e.setCancelled(true);
                    PlayerSelectTeamEvent event = new PlayerSelectTeamEvent(team, p);
                    event.setSuccess(!teamProperties.isBalanceTeams() || this.areTeamsBalanced(game, team));
                    Bukkit.getPluginManager().callEvent(event);
                    if(!event.isCancelled() && event.isSuccess()) {
                        p.closeInventory();
                        if(game.containsPlayer(p)) game.setTeam(p, event.getTeam());
                        else game.addPlayer(p, event.getTeam());
                    }
                }
            }
        }
    }

    /**
     * Checks whether Teams are balanced
     * @param game The Game to check
     * @param selectedTeam The Team that's selected
     * @return Whether the Teams are balancede
     */
    private boolean areTeamsBalanced(Game game, Team selectedTeam) {
        int playersInSelected = game.getPlayersInTeam(selectedTeam).size();
        for(Team team : game.getTeams()) {
            if(game.getPlayersInTeam(team).size() < playersInSelected && !team.equals(selectedTeam)) return false;
        }
        return true;
    }
}
