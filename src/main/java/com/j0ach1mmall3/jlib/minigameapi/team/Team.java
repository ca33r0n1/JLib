package com.j0ach1mmall3.jlib.minigameapi.team;

import com.j0ach1mmall3.jlib.minigameapi.team.events.PlayerJoinTeamEvent;
import com.j0ach1mmall3.jlib.minigameapi.team.events.PlayerLeaveTeamEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/09/15
 */
public final class Team {
    private final String identifier;
    private final String name;
    private final String prefix;
    private final boolean canChat;
    private final List<Player> members = new ArrayList<>();

    /**
     * Constructs a new Team
     * @param identifier The identifier of the Team
     * @param name The name of the Team
     * @param prefix The prefix in Chat of the Team
     * @param canChat If the Team can chat
     */
    public Team(String identifier, String name, String prefix, boolean canChat) {
        this.identifier = identifier;
        this.name = name;
        this.prefix = prefix;
        this.canChat = canChat;
    }

    /**
     * Returns the identifier of the Team
     * @return The identifier of the Team
     */
    public String getIdentifier() {
        return this.identifier;
    }

    /**
     * Returns the name of the Team
     * @return The name of the Team
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the prefix in Chat of the Team
     * @return The prefix in Chat
     */
    public String getPrefix() {
        return this.prefix;
    }

    /**
     * Returns whether the Team can chat
     * @return Wether the Team can chat
     */
    public boolean canChat() {
        return this.canChat;
    }

    /**
     * Adds a member to this Team
     * @param player The member
     * @see PlayerJoinTeamEvent
     */
    public void addMember(Player player) {
        PlayerJoinTeamEvent event = new PlayerJoinTeamEvent(player, this);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled()) this.members.add(player);
    }

    /**
     * Adds a member to this Team
     * @param player The member
     * @see PlayerLeaveTeamEvent
     */
    public void removeMember(Player player) {
        PlayerLeaveTeamEvent event = new PlayerLeaveTeamEvent(player, this);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled()) this.members.remove(player);
    }

    /**
     * Returns whether the Team contains a member
     * @param player The member
     * @return Wether the Team contains the member
     */
    public boolean containsMember(Player player) {
        return this.members.contains(player);
    }
}
