package com.j0ach1mmall3.jlib.minigameapi.game.state;

import org.bukkit.entity.EntityType;
import org.bukkit.material.MaterialData;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/09/15
 */
public final class GameRuleSet {
    @SuppressWarnings("deprecation")
    public static final Set<MaterialData> ALL_MATERIAL_DATAS = Collections.singleton(new MaterialData(-1));
    public static final Set<String> ALL_COMMANDS = Collections.singleton("%%%all_commands%%%");

    private final Set<MaterialData> breakable;
    private final Set<MaterialData> placeable;
    private final Set<MaterialData> dropable;
    private final Set<MaterialData> pickupable;
    private final EnumSet<EntityType> damagable;
    private final Set<String> executableCommands;
    private final boolean explosionDamage;
    private final boolean hunger;

    /**
     * Constructs a new RuleSet
     * @param breakable The MaterialDatas that should be breakable
     * @param placeable The MaterialDatas that should be placeable
     * @param dropable The MaterialDatas that should be dropable
     * @param pickupable The MaterialDatas that should be pickupable
     * @param damagable The EntityTypes that should be damageable
     * @param executableCommands The Commands that should be executable (Not case sensitive)
     * @param explosionDamage Whether Explosion Damage should be enabled
     * @param hunger Whether Hunger should be enabled
     */
    public GameRuleSet(Set<MaterialData> breakable, Set<MaterialData> placeable, Set<MaterialData> dropable, Set<MaterialData> pickupable, EnumSet<EntityType> damagable, Set<String> executableCommands, boolean explosionDamage, boolean hunger) {
        this.breakable = breakable;
        this.placeable = placeable;
        this.dropable = dropable;
        this.pickupable = pickupable;
        this.damagable = damagable;
        this.executableCommands = executableCommands;
        this.explosionDamage = explosionDamage;
        this.hunger = hunger;
    }

    /**
     * Returns the Set of MaterialDatas that are Breakable
     * @return The Set of MaterialDatas
     */
    public Set<MaterialData> getBreakable() {
        return this.breakable;
    }

    /**
     * Returns the Set of MaterialDatas that are Placeable
     * @return The Set of MaterialDatas
     */
    public Set<MaterialData> getPlaceable() {
        return this.placeable;
    }

    /**
     * Returns the Set of MaterialDatas that are Pickupable
     * @return The Set of MaterialDatas
     */
    public Set<MaterialData> getPickupable() {
        return this.pickupable;
    }

    /**
     * Returns the Set of MaterialDatas that are Dropable
     * @return The Set of MaterialDatas
     */
    public Set<MaterialData> getDropable() {
        return this.dropable;
    }

    /**
     * Returns the Set of EntityTypes that are Damageable
     * @return The Set of EntityTypes
     */
    public EnumSet<EntityType> getDamagable() {
        return this.damagable;
    }

    /**
     * Returns Set of Coommands that are Executable (Not case sensitive)
     * @return The Set of Coommands
     */
    public Set<String> getExecutableCommands() {
        return this.executableCommands;
    }

    /**
     * Returns whether Explosion Damage should be enabled
     * @return Whether Explosion Damage should be enabled
     */
    public boolean isExplosionDamage() {
        return this.explosionDamage;
    }

    /**
     * Returns whether Hunger should be enabled
     * @return Whether Hunger should be enabled
     */
    public boolean isHunger() {
        return this.hunger;
    }
}
