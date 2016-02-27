package com.j0ach1mmall3.jlib.minigameapi.listeners;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.inventory.GuiItem;
import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import com.j0ach1mmall3.jlib.minigameapi.game.GameChatType;
import com.j0ach1mmall3.jlib.minigameapi.game.GameRuleSet;
import com.j0ach1mmall3.jlib.minigameapi.game.events.PlayerLeaveGameEvent;
import com.j0ach1mmall3.jlib.minigameapi.team.Team;
import com.j0ach1mmall3.jlib.minigameapi.team.TeamProperties;
import com.j0ach1mmall3.jlib.minigameapi.team.TeamSelectGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 21/02/16
 */
public final class PlayerListener implements Listener {
    private final Main plugin;

    /**
     * Initialises the PlayerListener
     * @param plugin Main plugin
     */
    public PlayerListener(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * The PlayerJoinEvent Listener
     * @param e The PlayerJoinEvent
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        for(Game game : this.plugin.getApi().getGames()) {
            TeamProperties teamProperties = game.getTeamProperties();
            if(teamProperties.isGiveSelectItem()) {
                GuiItem teamSelectItem = teamProperties.getTeamSelectItem();
                e.getPlayer().getInventory().setItem(teamSelectItem.getPosition(), teamSelectItem.getItem());
            }
            if(game.getMap().getLobbySpawn() != null) e.getPlayer().teleport(game.getMap().getLobbySpawn());
        }
    }

    /**
     * The PlayerQuitEvent Listener
     * @param e The PlayerQuitEvent
     */
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if(this.plugin.getApi().isInGame(p)) this.plugin.getApi().getGame(p).removePlayer(p, PlayerLeaveGameEvent.Reason.QUIT);
    }

    /**
     * The PlayerKickEvent Listener
     * @param e The PlayerKickEvent
     */
    @EventHandler
    public void onKick(PlayerKickEvent e) {
        Player p = e.getPlayer();
        if(this.plugin.getApi().isInGame(p)) this.plugin.getApi().getGame(p).removePlayer(p, PlayerLeaveGameEvent.Reason.KICK);
    }

    /**
     * The PlayerInteractEvent Listener
     * @param e The PlayerInteractEvent
     */
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if(e.getAction() == Action.PHYSICAL) return;
        for(Game game : this.plugin.getApi().getGames()) {
            TeamProperties teamProperties = game.getTeamProperties();
            GuiItem teamSelectItem = teamProperties.getTeamSelectItem();
            if(teamSelectItem != null && teamSelectItem.getItem().isSimilar(e.getItem())) {
                TeamSelectGUI teamSelectGUI = teamProperties.getTeamSelectGUI();
                teamSelectGUI.getGui().open(e.getPlayer());
                e.setCancelled(true);
            }
        }
    }

    /**
     * The PlayerDropItemEvent Listener
     * @param e The PlayerDropItemEvent
     */
    @EventHandler
    @SuppressWarnings("deprecation")
    public void onDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if(this.plugin.getApi().isInGame(p)) {
            Game game = this.plugin.getApi().getGame(p);
            List<MaterialData> dropable = game.getRuleSet().getDropable();
            if(!dropable.equals(GameRuleSet.ALL_MATERIAL_DATAS) && !dropable.contains(e.getItemDrop().getItemStack().getData())) e.setCancelled(true);
        }
        for(Game game : this.plugin.getApi().getGames()) {
            TeamProperties teamProperties = game.getTeamProperties();
            if(!teamProperties.isDropSelectItem()) {
                ItemStack teamSelectItem = teamProperties.getTeamSelectItem().getItem();
                if(teamSelectItem.isSimilar(e.getItemDrop().getItemStack())) e.setCancelled(true);
            }
        }
    }

    /**
     * The PlayerPickupItemEvent Listener
     * @param e The PlayerPickupItemEvent
     */
    @EventHandler
    @SuppressWarnings("deprecation")
    public void onPickup(PlayerPickupItemEvent e) {
        Player p = e.getPlayer();
        if(this.plugin.getApi().isInGame(p)) {
            Game game = this.plugin.getApi().getGame(p);
            List<MaterialData> pickupable = game.getRuleSet().getPickupable();
            if(!pickupable.equals(GameRuleSet.ALL_MATERIAL_DATAS) && !pickupable.contains(e.getItem().getItemStack().getData())) e.setCancelled(true);
        }
    }

    /**
     * The AsyncPlayerChatEvent Listener
     * @param e The AsyncPlayerChatEvent
     * @see Game
     * @see GameChatType
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if(this.plugin.getApi().isInGame(p)) {
            Game game = this.plugin.getApi().getGame(p);
            Team team = game.getTeam(p);
            GameChatType type = game.getChatType();
            if(type == GameChatType.DISABLED || !team.canChat()) e.setCancelled(true);
            else if(type == GameChatType.PLAYER) {
                e.getRecipients().clear();
                e.getRecipients().add(p);
            } else if(type == GameChatType.TEAM) {
                List<Player> recipients = new ArrayList<>(e.getRecipients());
                for(Player recipient : recipients) {
                    if(!game.areInSameTeam(p, recipient)) recipients.remove(recipient);
                }
            }
        }
    }
}
