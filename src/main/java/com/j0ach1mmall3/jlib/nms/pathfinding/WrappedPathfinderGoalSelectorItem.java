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

    public WrappedPathfinderGoalSelectorItem(Object pathfinderGoalItem) throws Exception {
        this.pathfinderGoal = ReflectionAPI.getField(ReflectionAPI.getNmsClass("PathfinderGoalSelector$PathfinderGoalSelectorItem"), pathfinderGoalItem, "a");
        this.priority = (int) ReflectionAPI.getField(ReflectionAPI.getNmsClass("PathfinderGoalSelector$PathfinderGoalSelectorItem"), pathfinderGoalItem, "b");
    }

    public WrappedPathfinderGoalSelectorItem(int priority, Object pathfinderGoal) {
        this.priority = priority;
        this.pathfinderGoal = pathfinderGoal;
    }

    public Object getPathfinderGoal() {
        return this.pathfinderGoal;
    }

    public void setPathfinderGoal(Object pathfinderGoal) {
        this.pathfinderGoal = pathfinderGoal;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Object toPathfinderGoalItem(Object pathfinderGoalSelector) throws Exception {
        Constructor constructor = ReflectionAPI.getNmsClass("PathfinderGoalSelector$PathfinderGoalSelectorItem").getDeclaredConstructor(ReflectionAPI.getNmsClass("PathfinderGoalSelector"), int.class, ReflectionAPI.getNmsClass("PathfinderGoal"));
        constructor.setAccessible(true);
        return constructor.newInstance(pathfinderGoalSelector, this.priority, this.pathfinderGoal);
    }
}
