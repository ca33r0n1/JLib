package com.j0ach1mmall3.jlib.nms.pathfinding;

import com.j0ach1mmall3.jlib.methods.ReflectionAPI;

import java.lang.reflect.Constructor;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/06/2016
 */
public final class WrappedPathfinderGoalSelectorItem {
    private Object pathfinderGoal;
    private int priority;

    /**
     * Constructs a new WrappedPathfinderGoalSelectorItem
     * @param pathfinderGoalItem The PathfinderGoalItem to wrap
     * @throws Exception if an exception occurs
     */
    public WrappedPathfinderGoalSelectorItem(Object pathfinderGoalItem) throws Exception {
        this.pathfinderGoal = ReflectionAPI.getField(ReflectionAPI.getNmsClass("PathfinderGoalSelector$PathfinderGoalSelectorItem"), pathfinderGoalItem, "a");
        this.priority = (int) ReflectionAPI.getField(ReflectionAPI.getNmsClass("PathfinderGoalSelector$PathfinderGoalSelectorItem"), pathfinderGoalItem, "b");
    }

    /**
     * Constructs a new WrappedPathfinderGoalSelectorItem
     * @param priority The priority of the WrappedPathfinderGoalSelectorItem
     * @param pathfinderGoal The PathfinderGoal
     */
    public WrappedPathfinderGoalSelectorItem(int priority, Object pathfinderGoal) {
        this.priority = priority;
        this.pathfinderGoal = pathfinderGoal;
    }

    /**
     * Returns the PathfinderGoal
     * @return The PathfinderGoal
     */
    public Object getPathfinderGoal() {
        return this.pathfinderGoal;
    }

    /**
     * Sets the PathfinderGoal
     * @param pathfinderGoal The PathfinderGoal
     */
    public void setPathfinderGoal(Object pathfinderGoal) {
        this.pathfinderGoal = pathfinderGoal;
    }

    /**
     * Returns the priority
     * @return The priority
     */
    public int getPriority() {
        return this.priority;
    }

    /**
     * Sets the priority
     * @param priority The priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Converts this WrappedPathfinderGoalSelectorItem to a PathfinderGoalItem
     * @param pathfinderGoalSelector The PathfinderGoalSelector to use
     * @return The PathfinderGoalItem
     * @throws Exception if an exception occurs
     */
    public Object toPathfinderGoalItem(Object pathfinderGoalSelector) throws Exception {
        Constructor constructor = ReflectionAPI.getNmsClass("PathfinderGoalSelector$PathfinderGoalSelectorItem").getDeclaredConstructor(ReflectionAPI.getNmsClass("PathfinderGoalSelector"), int.class, ReflectionAPI.getNmsClass("PathfinderGoal"));
        constructor.setAccessible(true);
        return constructor.newInstance(pathfinderGoalSelector, this.priority, this.pathfinderGoal);
    }
}
