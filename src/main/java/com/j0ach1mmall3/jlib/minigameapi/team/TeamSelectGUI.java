package com.j0ach1mmall3.jlib.minigameapi.team;

import com.j0ach1mmall3.jlib.gui.Gui;
import com.j0ach1mmall3.jlib.gui.events.GuiClickEvent;
import com.j0ach1mmall3.jlib.inventory.JLibItem;
import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import com.j0ach1mmall3.jlib.minigameapi.game.events.PlayerSelectTeamEvent;
import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
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
     * @param name The name of the TeamSelectGUI
     * @param rows The amount of rows of the TeamSelectGUI
     * @param game The Game this TeamSelectGUI belongs to
     * @param teams The inventory position Team mapping
     */
    public TeamSelectGUI(String name, int rows, Game game, Map<Integer, Team> teams) {
        super(name, rows);
        this.game = game;
        this.teams = teams;
    }

    @Override
    public void addItem(int position, JLibItem jLibItem) {
        jLibItem.setOnGuiClick(new CallbackHandler<GuiClickEvent>() {
            @Override
            public void callback(GuiClickEvent o) {
                Team team = TeamSelectGUI.this.teams.get(o.getInventoryClickEvent().getSlot());
                Player p = o.getPlayer();
                o.setCancelled(true);
                PlayerSelectTeamEvent event = new PlayerSelectTeamEvent(TeamSelectGUI.this.game, team, p);
                if(team.getMaxPlayers() <= TeamSelectGUI.this.game.getPlayersInTeam(team).size()) event.setResult(PlayerSelectTeamEvent.Result.FULL);
                if(TeamSelectGUI.this.game.getTeamProperties().isBalanceTeams() && !TeamSelectGUI.this.game.areTeamsBalanced(team)) event.setResult(PlayerSelectTeamEvent.Result.UNBALANCED);
                Bukkit.getPluginManager().callEvent(event);
                if(!event.isCancelled() && event.getResult() == PlayerSelectTeamEvent.Result.SUCCESS) {
                    p.closeInventory();
                    if(TeamSelectGUI.this.game.containsPlayer(p)) TeamSelectGUI.this.game.setTeam(p, event.getTeam());
                    else TeamSelectGUI.this.game.addPlayer(p, event.getTeam());
                }
            }
        });
        super.addItem(position, jLibItem);
    }
}
