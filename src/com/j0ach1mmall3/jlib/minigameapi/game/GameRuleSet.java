package com.j0ach1mmall3.jlib.minigameapi.game;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by j0ach1mmall3 on 19:17 4/09/2015 using IntelliJ IDEA.
 */
public class GameRuleSet {
    private List<Material> breakAble;
    private List<Material> placeAble;
    private List<EntityType> damageAble;
    private List<Material> dropAble;
    private List<Material> pickupAble;

    public GameRuleSet(List<Material> breakAble, List<EntityType> damageAble, List<Material> placeAble, List<Material> dropAble, List<Material> pickupAble) {
        this.breakAble = breakAble;
        if(breakAble == null) this.breakAble = new ArrayList<>();
        this.damageAble = damageAble;
        if(damageAble == null) this.damageAble = new ArrayList<>();
        this.placeAble = placeAble;
        if(placeAble == null) this.placeAble = new ArrayList<>();
        this.dropAble = dropAble;
        if(dropAble == null) this.dropAble = new ArrayList<>();
        this.pickupAble = pickupAble;
        if(pickupAble == null) this.pickupAble = new ArrayList<>();
    }

    public List<Material> getBreakAble() {
        return this.breakAble;
    }

    public List<Material> getPlaceAble() {
        return this.placeAble;
    }

    public List<EntityType> getDamageAble() {
        return this.damageAble;
    }

    public List<Material> getPickupAble() {
        return this.pickupAble;
    }

    public List<Material> getDropAble() {
        return this.dropAble;
    }
}
