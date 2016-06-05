package com.j0ach1mmall3.jlib.nms.pathfinding;

import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import org.bukkit.entity.Creature;

import java.util.HashSet;
import java.util.Set;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/06/2016
 */
public final class WrappedPathfinderGoalSelector {
    private static final Class<?> PGSCLASS = ReflectionAPI.getNmsClass("PathfinderGoalSelector");
    private static final Class<?> ENTITYINSENTIENTCLASS = ReflectionAPI.getNmsClass("EntityInsentient");

    private final Set<WrappedPathfinderGoalItem> active = new HashSet<>();
    private final Set<WrappedPathfinderGoalItem> inactive = new HashSet<>();

    public WrappedPathfinderGoalSelector(Creature creature) {

    }

    public Set<WrappedPathfinderGoalItem> getActive() {
        return this.active;
    }

    public Set<Object> getActivePathfinderGoalItems() {
        Set<Object> active = new HashSet<>();
        for(WrappedPathfinderGoalItem wrappedPathfinderGoalItem : this.active) {
            active.add(wrappedPathfinderGoalItem.getPathfinderGoal());
        }
        return active;
    }

    public Set<WrappedPathfinderGoalItem> getInactive() {
        return this.inactive;
    }

    public Set<Object> getInactivePathfinderGoalItems() {
        Set<Object> inactive = new HashSet<>();
        for(WrappedPathfinderGoalItem wrappedPathfinderGoalItem : this.inactive) {
            inactive.add(wrappedPathfinderGoalItem.getPathfinderGoal());
        }
        return inactive;
    }

    public void add(int priority, Object pathfinderGoal) {
        this.active.add(new WrappedPathfinderGoalItem(priority, pathfinderGoal));
    }

    public void apply(Creature creature) throws Exception {
        this.applyToGoalSelector(ReflectionAPI.getField(ENTITYINSENTIENTCLASS, ReflectionAPI.getHandle((Object) creature), "goalSelector"));
        this.applyToTargetSelector(ReflectionAPI.getField(ENTITYINSENTIENTCLASS, ReflectionAPI.getHandle((Object) creature), "targetSelector"));
    }

    public void applyToGoalSelector(Object goalSelector) throws Exception {
        ReflectionAPI.setField(PGSCLASS, goalSelector, "b", this.getActivePathfinderGoalItems());
        ReflectionAPI.setField(PGSCLASS, goalSelector, "c", this.getInactivePathfinderGoalItems());
    }

    public void applyToTargetSelector(Object targetSelector) throws Exception {
        ReflectionAPI.setField(PGSCLASS, targetSelector, "b", this.getActivePathfinderGoalItems());
        ReflectionAPI.setField(PGSCLASS, targetSelector, "c", this.getInactivePathfinderGoalItems());
    }
}
