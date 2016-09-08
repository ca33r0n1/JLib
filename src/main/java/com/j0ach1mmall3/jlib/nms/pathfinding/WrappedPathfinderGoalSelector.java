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
    private final Type type;
    private final Object goalSelector;
    private final Set<WrappedPathfinderGoalSelectorItem> active = new HashSet<>();
    private final Set<WrappedPathfinderGoalSelectorItem> inactive = new HashSet<>();

    /**
     * Constructs a new WrappedPathfinderGoalSelector
     * @param type The Type of WrappedPathfinderGoalSelector
     * @param creature The Creature this WrappedPathfinderGoalSelector is for
     * @throws Exception if an exception occurs
     */
    public WrappedPathfinderGoalSelector(Type type, Creature creature) throws Exception {
        this.type = type;
        this.goalSelector = ReflectionAPI.getField(ReflectionAPI.getNmsClass("EntityInsentient"), ReflectionAPI.getHandle((Object) creature), type.getFieldName());
        for(Object o : (Collection) ReflectionAPI.getField(ReflectionAPI.getNmsClass("PathfinderGoalSelector"), this.goalSelector, "b")) {
            this.active.add(new WrappedPathfinderGoalSelectorItem(o));
        }
        for(Object o : (Collection) ReflectionAPI.getField(ReflectionAPI.getNmsClass("PathfinderGoalSelector"), this.goalSelector, "c")) {
            this.inactive.add(new WrappedPathfinderGoalSelectorItem(o));
        }
    }

    /**
     * Returns the Type
     * @return The Type
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Returns the active WrappedPathfinderGoalSelectorItems
     * @return The active WrappedPathfinderGoalSelectorItems
     */
    public Set<WrappedPathfinderGoalSelectorItem> getActive() {
        return this.active;
    }

    /**
     * Returns the active PathfinderGoalItems
     * @return The active PathfinderGoalItems
     * @throws Exception if an exception occurs
     */
    public Set<Object> getActivePathfinderGoalItems() throws Exception {
        Set<Object> active = new HashSet<>();
        for(WrappedPathfinderGoalSelectorItem wrappedPathfinderGoalItem : this.active) {
            active.add(wrappedPathfinderGoalItem.toPathfinderGoalItem(this.goalSelector));
        }
        return active;
    }

    /**
     * Returns the inactive WrappedPathfinderGoalSelectorItems
     * @return The inactive WrappedPathfinderGoalSelectorItems
     */
    public Set<WrappedPathfinderGoalSelectorItem> getInactive() {
        return this.inactive;
    }

    /**
     * Returns the inactive PathfinderGoalItems
     * @return The inactive PathfinderGoalItems
     * @throws Exception if an exception occurs
     */
    public Set<Object> getInactivePathfinderGoalItems() throws Exception {
        Set<Object> inactive = new HashSet<>();
        for(WrappedPathfinderGoalSelectorItem wrappedPathfinderGoalItem : this.inactive) {
            inactive.add(wrappedPathfinderGoalItem.toPathfinderGoalItem(this.goalSelector));
        }
        return inactive;
    }

    /**
     * Adds a new active PathfinderGoal
     * @param priority The priority
     * @param pathfinderGoal The PathfinderGoal
     */
    public void add(int priority, Object pathfinderGoal) {
        this.active.add(new WrappedPathfinderGoalSelectorItem(priority, pathfinderGoal));
    }

    /**
     * Applies this WrappedPathfinderGoalSelector to a Creature
     * @param creature The Creature
     * @throws Exception if an exception occurs
     */
    public void apply(Creature creature) throws Exception {
        Object goalSelector = ReflectionAPI.getField(ReflectionAPI.getNmsClass("EntityInsentient"), ReflectionAPI.getHandle((Object) creature), this.type.getFieldName());
        Collection<Object> b = (Collection) ReflectionAPI.getField(ReflectionAPI.getNmsClass("PathfinderGoalSelector"), goalSelector, "b");
        b.clear();
        b.addAll(this.getActivePathfinderGoalItems());
        Collection<Object> c = (Collection) ReflectionAPI.getField(ReflectionAPI.getNmsClass("PathfinderGoalSelector"), goalSelector, "c");
        c.clear();
        c.addAll(this.getInactivePathfinderGoalItems());
    }

    public enum Type {
        GOAL_SELECTOR("goalSelector"),
        TARGET_SELECTOR("targetSelector");

        private final String fieldName;

        /**
         * Constructs a new Type
         * @param fieldName The Field name
         */
        Type(String fieldName) {
            this.fieldName = fieldName;
        }

        /**
         * Returns the Field name
         * @return The Field name
         */
        public String getFieldName() {
            return this.fieldName;
        }
    }
}
