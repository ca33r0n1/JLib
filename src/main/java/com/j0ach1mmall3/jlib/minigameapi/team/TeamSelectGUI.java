package com.j0ach1mmall3.jlib.minigameapi.team;

import com.j0ach1mmall3.jlib.gui.Gui;
import com.j0ach1mmall3.jlib.gui.GuiPage;
import com.j0ach1mmall3.jlib.inventory.JLibItem;
import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import com.j0ach1mmall3.jlib.minigameapi.game.events.PlayerSelectTeamEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/02/16
 */
public final class TeamSelectGUI extends Gui {
    private final Game game;
    private final Map<Integer, Team> teams;

    /**
     * Constructs a new TeamSelectGUI
     *
     * @param game     The Game this TeamSelectGUI belongs to
     * @param teams    The inventory position Team mapping
     * @param guiPages The GuiPages in this TeamSelectGUI
     */
    public TeamSelectGUI(Game game, Map<Integer, Team> teams, GuiPage... guiPages) {
        super(guiPages);
        this.game = game;
        this.teams = teams;
    }

    /**
     * Adds a JLibItem to this TeamSelectGUI
     *
     * @param page     The page the JLibItem is in
     * @param position The position of the JLibItem
     * @param jLibItem The JLibItem
     */
    public void addItem(int page, int position, JLibItem jLibItem) {
        jLibItem.setGuiClickHandler(o -> {
            Team team = this.teams.get(o.getInventoryClickEvent().getSlot());
            Player p = o.getPlayer();
            o.setCancelled(true);
            PlayerSelectTeamEvent event = new PlayerSelectTeamEvent(this.game, team, p);
            if (team.getMaxPlayers() <= this.game.getPlayersInTeam(team).size())
                event.setResult(PlayerSelectTeamEvent.Result.FULL);
            if (this.game.getTeamProperties().isBalanceTeams() && !this.game.areTeamsBalanced(team))
                event.setResult(PlayerSelectTeamEvent.Result.UNBALANCED);
            Bukkit.getPluginManager().callEvent(event);
            if (!event.isCancelled() && event.getResult() == PlayerSelectTeamEvent.Result.SUCCESS) {
                p.closeInventory();
                if (this.game.containsPlayer(p)) this.game.setTeam(p, event.getTeam());
                else this.game.addPlayer(p, event.getTeam());
            }
        });
        this.guiPages.get(page).addItem(position, jLibItem);
    }
}
