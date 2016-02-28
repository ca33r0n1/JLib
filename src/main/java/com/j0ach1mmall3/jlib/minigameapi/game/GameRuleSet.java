package com.j0ach1mmall3.jlib.minigameapi.game;

import org.bukkit.entity.EntityType;
import org.bukkit.material.MaterialData;

import java.util.Collections;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/09/15
 */
public final class GameRuleSet {
    @SuppressWarnings("deprecation")
    public static final List<MaterialData> ALL_MATERIAL_DATAS = Collections.singletonList(new MaterialData(-1));

    private final List<MaterialData> breakable;
    private final List<MaterialData> placeable;
    private final List<MaterialData> dropable;
    private final List<MaterialData> pickupable;
    private final List<EntityType> damagable;
    private final boolean explosionDamage;
    private final boolean hunger;

    /**
     * Constructs a new RuleSet
     * @param breakable The MaterialDatas that should be breakable
     * @param placeable The MaterialDatas that should be placeable
     * @param dropable The MaterialDatas that should be dropable
     * @param pickupable The MaterialDatas that should be pickupable
     * @param damagable The EntityTypes that should be damageable
     * @param explosionDamage Whether Explosion Damage should be enabled
     * @param hunger Whether Hunger should be enabled
     */
    public GameRuleSet(List<MaterialData> breakable, List<MaterialData> placeable, List<MaterialData> dropable, List<MaterialData> pickupable, List<EntityType> damagable, boolean explosionDamage, boolean hunger) {
        this.breakable = breakable;
        this.placeable = placeable;
        this.dropable = dropable;
        this.pickupable = pickupable;
        this.damagable = damagable;
        this.explosionDamage = explosionDamage;
        this.hunger = hunger;
    }

    /**
     * Returns the List of MaterialDatas that are Breakable
     * @return The List of MaterialDatas
     */
    public List<MaterialData> getBreakable() {
        return this.breakable;
    }

    /**
     * Returns the List of MaterialDatas that are Placeable
     * @return The List of MaterialDatas
     */
    public List<MaterialData> getPlaceable() {
        return this.placeable;
    }

    /**
     * Returns the List of MaterialDatas that are Pickupable
     * @return The List of MaterialDatas
     */
    public List<MaterialData> getPickupable() {
        return this.pickupable;
    }

    /**
     * Returns the List of MaterialDatas that are Dropable
     * @return The List of MaterialDatas
     */
    public List<MaterialData> getDropable() {
        return this.dropable;
    }

    /**
     * Returns the List of EntityTypes that are Damageable
     * @return The List of EntityTypes
     */
    public List<EntityType> getDamagable() {
        return this.damagable;
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
