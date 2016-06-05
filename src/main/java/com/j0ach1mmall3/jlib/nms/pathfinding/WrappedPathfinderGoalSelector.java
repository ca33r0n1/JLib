package com.j0ach1mmall3.jlib.nms.pathfinding;

import com.j0ach1mmall3.jlib.methods.ReflectionAPI;
import org.bukkit.entity.Creature;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/06/2016
 */
public final class WrappedPathfinderGoalSelector {
    private static final Class<?> PGSCLASS = ReflectionAPI.getNmsClass("PathfinderGoalSelector");
    private static final Class<?> ENTITYINSENTIENTCLASS = ReflectionAPI.getNmsClass("EntityInsentient");

    private final Type type;
    private final Object goalSelector;
    private final Set<WrappedPathfinderGoalSelectorItem> active = new HashSet<>();
    private final Set<WrappedPathfinderGoalSelectorItem> inactive = new HashSet<>();

    public WrappedPathfinderGoalSelector(Type type, Creature creature) throws Exception {
        this.type = type;
        this.goalSelector = ReflectionAPI.getField(ENTITYINSENTIENTCLASS, ReflectionAPI.getHandle((Object) creature), type.getFieldName());
        for(Object o : (Collection) ReflectionAPI.getField(PGSCLASS, this.goalSelector, "b")) {
            this.active.add(new WrappedPathfinderGoalSelectorItem(o));
        }
        for(Object o : (Collection) ReflectionAPI.getField(PGSCLASS, this.goalSelector, "c")) {
            this.inactive.add(new WrappedPathfinderGoalSelectorItem(o));
        }
    }

    public Type getType() {
        return this.type;
    }

    public Set<WrappedPathfinderGoalSelectorItem> getActive() {
        return this.active;
    }

    public Set<Object> getActivePathfinderGoalItems() throws Exception {
        Set<Object> active = new HashSet<>();
        for(WrappedPathfinderGoalSelectorItem wrappedPathfinderGoalItem : this.active) {
            active.add(wrappedPathfinderGoalItem.toPathfinderGoalItem(this.goalSelector));
        }
        return active;
    }

    public Set<WrappedPathfinderGoalSelectorItem> getInactive() {
        return this.inactive;
    }

    public Set<Object> getInactivePathfinderGoalItems() throws Exception {
        Set<Object> inactive = new HashSet<>();
        for(WrappedPathfinderGoalSelectorItem wrappedPathfinderGoalItem : this.inactive) {
            inactive.add(wrappedPathfinderGoalItem.toPathfinderGoalItem(this.goalSelector));
        }
        return inactive;
    }

    public void add(int priority, Object pathfinderGoal) {
        this.active.add(new WrappedPathfinderGoalSelectorItem(priority, pathfinderGoal));
    }

    public void apply(Creature creature) throws Exception {
        Object goalSelector = ReflectionAPI.getField(ENTITYINSENTIENTCLASS, ReflectionAPI.getHandle((Object) creature), this.type.getFieldName());
        ReflectionAPI.setField(PGSCLASS, goalSelector, "b", this.getActivePathfinderGoalItems());
        ReflectionAPI.setField(PGSCLASS, goalSelector, "c", this.getInactivePathfinderGoalItems());
    }

    public enum Type {
        GOAL_SELECTOR("goalSelector"),
        TARGET_SELECTOR("targetSelector");

        private final String fieldName;

        Type(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFieldName() {
            return this.fieldName;
        }
    }
}
