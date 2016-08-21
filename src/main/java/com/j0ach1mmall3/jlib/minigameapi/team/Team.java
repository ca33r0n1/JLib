package com.j0ach1mmall3.jlib.minigameapi.team;

import org.bukkit.scoreboard.NameTagVisibility;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/09/15
 */
public final class Team {
    private final String identifier;
    private final String name;
    private final int maxPlayers;
    private final String prefix;
    private final String suffix;
    private final boolean friendlyFire;
    private final boolean seeFriendlyInvisibles;
    @SuppressWarnings("deprecation")
    private final NameTagVisibility nameTagVisibility;
    private final boolean chat;

    /**
     * Constructs a new Team
     * @param identifier The identifier of the Team
     * @param name The name of the Team
     * @param maxPlayers The maximum amount of players in this team
     * @param prefix The prefix in Chat of the Team
     * @param suffix The suffix in Chat of the Team
     * @param friendlyFire Whether Friendly Fire should be allowed
     * @param seeFriendlyInvisibles Whether players in this Team should see invisible players of the same Team
     * @param nameTagVisibility The NameTagVisibility of the Team
     * @param chat Whether the Team can chat
     */
    @SuppressWarnings("deprecation")
    public Team(String identifier, String name, int maxPlayers, String prefix, String suffix, boolean friendlyFire, boolean seeFriendlyInvisibles, NameTagVisibility nameTagVisibility, boolean chat) {
        this.identifier = identifier;
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.prefix = prefix;
        this.suffix = suffix;
        this.friendlyFire = friendlyFire;
        this.seeFriendlyInvisibles = seeFriendlyInvisibles;
        this.nameTagVisibility = nameTagVisibility;
        this.chat = chat;
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
     * Returns the maximum amount of players in this Team
     * @return The amount of players
     */
    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    /**
     * Returns the prefix in Chat of the Team
     * @return The prefix in Chat
     */
    public String getPrefix() {
        return this.prefix;
    }

    /**
     * Returns the suffix in Chat of the Team
     * @return The suffix in Chat
     */
    public String getSuffix() {
        return this.suffix;
    }

    /**
     * Returns whether Friendly Fire should be allowed
     * @return Whether Friendly Fire should be allowed
     */
    public boolean isFriendlyFire() {
        return this.friendlyFire;
    }

    /**
     * Returns whether players in this Team should see invisible players of the same Team
     * @return Whether players in this Team should see invisible players of the same Team
     */
    public boolean isSeeFriendlyInvisibles() {
        return this.seeFriendlyInvisibles;
    }

    /**
     * Returns the NameTagVisibility of the Team
     * @return The NameTagVisibility of the Team
     */
    @SuppressWarnings("deprecation")
    public NameTagVisibility getNameTagVisibility() {
        return this.nameTagVisibility;
    }

    /**
     * Returns whether the Team can chat
     * @return Wether the Team can chat
     */
    public boolean isChat() {
        return this.chat;
    }

    /**
     * Returns whether this Team equals another Team
     * @param team The other Team
     * @return Whether this Team equals another Team
     */
    public boolean equals(Team team) {
        return this.identifier.equals(team.identifier);
    }
}
