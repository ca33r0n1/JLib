package com.j0ach1mmall3.jlib.nms.pathfinding;

import com.j0ach1mmall3.jlib.methods.ReflectionAPI;

import java.lang.reflect.Constructor;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/06/2016
 */
public final class WrappedPathfinderGoalItem {
    private static final Class<?> PGICLASS = ReflectionAPI.getNmsClass("PathfinderGoalSelector.PathfinderGoalItem");
    private static final Class<?> PATHFINDERGOALCLASS = ReflectionAPI.getNmsClass("PathfinderGoal");

    private int priority;
    private Object pathfinderGoal;

    public WrappedPathfinderGoalItem(Object pathfinderGoalItem) throws Exception {
        this.priority = (int) ReflectionAPI.getField(PGICLASS, pathfinderGoalItem, "a");
        this.pathfinderGoal = ReflectionAPI.getField(PGICLASS, pathfinderGoalItem, "b");
    }

    public WrappedPathfinderGoalItem(int priority, Object pathfinderGoal) {
        this.priority = priority;
        this.pathfinderGoal = pathfinderGoal;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Object getPathfinderGoal() {
        return this.pathfinderGoal;
    }

    public void setPathfinderGoal(Object pathfinderGoal) {
        this.pathfinderGoal = pathfinderGoal;
    }

    public Object toPathfinderGoalItem() throws Exception {
        Constructor constructor = PGICLASS.getConstructor(int.class, PATHFINDERGOALCLASS);
        return constructor.newInstance(this.priority, this.pathfinderGoal);
    }
}
