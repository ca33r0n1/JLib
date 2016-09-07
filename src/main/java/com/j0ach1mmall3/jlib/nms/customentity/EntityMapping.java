package com.j0ach1mmall3.jlib.nms.customentity;

import com.j0ach1mmall3.jlib.methods.ReflectionAPI;

import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 27/06/2016
 */
public final class EntityMapping {
    /**
     * Let nobody instantiate this class
     */
    private EntityMapping() {
    }

    /**
     * Registers a custom entity to the server mapping
     * @param clazz The Class
     * @param name The name of the entity
     * @param id The id of the 'parent' entity
     * @throws Exception when an exception occurs
     */
    public static void register(Class clazz, String name, int id) throws Exception {
        ((Map) ReflectionAPI.getField(ReflectionAPI.getNmsClass("EntityTypes"), null, "c")).put(name, clazz);
        ((Map) ReflectionAPI.getField(ReflectionAPI.getNmsClass("EntityTypes"), null, "d")).put(clazz, name);
        ((Map) ReflectionAPI.getField(ReflectionAPI.getNmsClass("EntityTypes"), null, "e")).put(id, clazz);
        ((Map) ReflectionAPI.getField(ReflectionAPI.getNmsClass("EntityTypes"), null, "f")).put(clazz, id);
        ((Map) ReflectionAPI.getField(ReflectionAPI.getNmsClass("EntityTypes"), null, "g")).put(name, id);
    }
}