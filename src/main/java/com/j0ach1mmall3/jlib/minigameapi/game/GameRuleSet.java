package com.j0ach1mmall3.jlib.minigameapi.game;

import org.bukkit.entity.EntityType;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 4/09/15
 */
public final class GameRuleSet {
    private final List<MaterialData> breakAble;
    private final List<MaterialData> placeAble;
    private final List<MaterialData> dropAble;
    private final List<MaterialData> pickupAble;
    private final List<EntityType> damageAble;

    /**
     * Constructs a new RuleSet
     * @param breakAble The MaterialDatas that should be breakable
     * @param placeAble The MaterialDatas that should be placeable
     * @param dropAble The MaterialDatas that should be dropable
     * @param pickupAble The MaterialDatas that should be pickupable
     * @param damageAble The EntityTypes that should be damageable
     * @see MaterialData
     * @see EntityType
     */
    public GameRuleSet(List<MaterialData> breakAble, List<MaterialData> placeAble, List<MaterialData> dropAble, List<MaterialData> pickupAble, List<EntityType> damageAble) {
        this.breakAble = breakAble!=null?breakAble:new ArrayList<MaterialData>();
        this.placeAble = placeAble!=null?placeAble:new ArrayList<MaterialData>();
        this.dropAble = dropAble!=null?dropAble:new ArrayList<MaterialData>();
        this.pickupAble = pickupAble!=null?pickupAble:new ArrayList<MaterialData>();
        this.damageAble = damageAble!=null?damageAble:new ArrayList<EntityType>();
    }

    /**
     * Returns the List of MaterialDatas that are Breakable
     * @return The List of MaterialDatas
     * @see MaterialData
     */
    public List<MaterialData> getBreakAble() {
        return this.breakAble;
    }

    /**
     * Returns the List of MaterialDatas that are Placeable
     * @return The List of MaterialDatas
     * @see MaterialData
     */
    public List<MaterialData> getPlaceAble() {
        return this.placeAble;
    }

    /**
     * Returns the List of MaterialDatas that are Pickupable
     * @return The List of MaterialDatas
     * @see MaterialData
     */
    public List<MaterialData> getPickupAble() {
        return this.pickupAble;
    }

    /**
     * Returns the List of MaterialDatas that are Dropable
     * @return The List of MaterialDatas
     * @see MaterialData
     */
    public List<MaterialData> getDropAble() {
        return this.dropAble;
    }

    /**
     * Returns the List of EntityTypes that are Damageable
     * @return The List of EntityTypes
     * @see EntityType
     */
    public List<EntityType> getDamageAble() {
        return this.damageAble;
    }
}
