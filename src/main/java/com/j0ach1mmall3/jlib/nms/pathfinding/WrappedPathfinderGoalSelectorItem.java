package com.j0ach1mmall3.jlib.nms.pathfinding;

import com.j0ach1mmall3.jlib.methods.ReflectionAPI;

import java.lang.reflect.Constructor;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/06/2016
 */
public final class WrappedPathfinderGoalSelectorItem {
    private static final Class<?> PGSCLASS = ReflectionAPI.getNmsClass("PathfinderGoalSelector");
    private static final Class<?> PGICLASS = ReflectionAPI.getNmsClass("PathfinderGoalSelector$PathfinderGoalSelectorItem");
    private static final Class<?> PATHFINDERGOALCLASS = ReflectionAPI.getNmsClass("PathfinderGoal");

    private Object pathfinderGoal;
    private int priority;

    public WrappedPathfinderGoalSelectorItem(Object pathfinderGoalItem) throws Exception {
        this.pathfinderGoal = ReflectionAPI.getField(PGICLASS, pathfinderGoalItem, "a");
        this.priority = (int) ReflectionAPI.getField(PGICLASS, pathfinderGoalItem, "b");
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
        Constructor constructor = PGICLASS.getDeclaredConstructor(PGSCLASS, int.class, PATHFINDERGOALCLASS);
        constructor.setAccessible(true);
        return constructor.newInstance(pathfinderGoalSelector, this.priority, this.pathfinderGoal);
    }
}
