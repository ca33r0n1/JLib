package com.j0ach1mmall3.jlib.minigameapi.team;

import com.j0ach1mmall3.jlib.minigameapi.team.events.PlayerJoinTeamEvent;
import com.j0ach1mmall3.jlib.minigameapi.team.events.PlayerLeaveTeamEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by j0ach1mmall3 on 19:28 4/09/2015 using IntelliJ IDEA.
 */
public final class Team {
    private final String identifier;
    private final String name;
    private final String prefix;
    private final boolean canChat;
    private final List<Player> members = new ArrayList<>();

    public Team(String identifier, String name, String prefix, boolean canChat) {
        this.identifier = identifier;
        this.name = name;
        this.prefix = prefix;
        this.canChat = canChat;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getName() {
        return this.name;
    }

    public String getPrefix() {
        return this.prefix;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean canChat() {
        return this.canChat;
    }

    public void addMember(Player p) {
        PlayerJoinTeamEvent event = new PlayerJoinTeamEvent(p, this);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled()) this.members.add(p);
    }

    public void removeMember(Player p) {
        PlayerLeaveTeamEvent event = new PlayerLeaveTeamEvent(p, this);
        Bukkit.getPluginManager().callEvent(event);
        if(event.isCancelled()) this.members.remove(p);
    }

    public boolean containsMember(Player p) {
        return this.members.contains(p);
    }

    public boolean isMember(Player p) {
        return this.members.contains(p);
    }
}
