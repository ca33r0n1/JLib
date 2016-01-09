package com.j0ach1mmall3.jlib.integration.profilefetcher;

import java.util.UUID;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/01/16
 */
public final class PlayerProfile {
    private final String name;
    private final UUID uuid;

    /**
     * Constructs a new PlayerProfile
     * @param name The name of this PlayerProfile
     * @param uuid The uuid of this PlayerProfile
     */
    public PlayerProfile(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    /**
     * Returns the name of this PlayerProfile
     * @return The name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the uuid of this PlayerProfile
     * @return The uuid
     */
    public UUID getUuid() {
        return this.uuid;
    }
}
