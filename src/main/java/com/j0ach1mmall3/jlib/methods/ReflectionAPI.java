package com.j0ach1mmall3.jlib.methods;

import com.j0ach1mmall3.jlib.logging.JLogger;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since Unknown
 */
public final class ReflectionAPI {
    /**
     * Let nobody instantiate this class
     */
    private ReflectionAPI() {
    }
    /**
     * Returns the current NMS version
     * @return The NMS version
     * @deprecated {@link ReflectionAPI#getNmsVersion()}
     */
    @Deprecated
    public static String getVersion(){
        new JLogger().deprecation();
        return getNmsVersion();
    }

    /**
     * Returns whether Spigot is used
     * @return Wether Spigot is used
     */
    public static boolean useSpigot(){
        String path = "org.spigotmc.Metrics";
        try{
            Class.forName(path);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    /**
     * Returns whether KCauldron is used
     * @return Wether KCauldron is used
     */
    public static boolean useKCauldron() {
        String path = "kcauldron.updater.KVersionRetriever";
        try{
            Class.forName(path);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    /**
     * Returns whether Thermos (KCauldron fork) is used
     * @return Wether Thermos is used
     */
    public static boolean useThermos() {
        String path = "thermos.updater.TVersionRetriever";
        try{
            Class.forName(path);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    /**
     * Returns the NMS class by name
     * @param name The name of the class
     * @return The class
     */
    public static Class getNmsClass(String name){
        String className = "net.minecraft.server." + getNmsVersion() + '.' + name;
        Class clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return clazz;
    }

    /**
     * Returns the OBC class by name
     * @param name The name of the class
     * @return The class
     */
    public static Class getObcClass(String name){
        String className = "org.bukkit.craftbukkit." + getNmsVersion() + '.' + name;
        Class clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return clazz;
    }

    /**
     * Returns the Handle of an Entity
     * @param entity The Entity
     * @return The Handle
     * @deprecated {@link ReflectionAPI#getHandle(Object)}
     */
    @Deprecated
    public static Object getHandle(Entity entity) {
        new JLogger().deprecation();
        try {
            return entity.getClass().getMethod("getHandle").invoke(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns the Handle of a World
     * @param world The World
     * @return The Handle
     * @deprecated {@link ReflectionAPI#getHandle(Object)}
     */
    @Deprecated
    public static Object getHandle(World world) {
        new JLogger().deprecation();
        try {
            return world.getClass().getMethod("getHandle").invoke(world);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns the Handle of an Object
     * @param o The Object
     * @return The Handle
     */
    public static Object getHandle(Object o) {
        try {
            return o.getClass().getMethod("getHandle").invoke(o);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns the NMS version of an ItemStack
     * @param is The ItemStack
     * @return The NMS version of the ItemStack
     */
    public static Object getNmsItemStack(ItemStack is) {
        try {
            return getObcClass("inventory.CraftItemStack").getMethod("asNMSCopy", ItemStack.class).invoke(null, is);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Sends a Packet to a Player
     * @param player The player to send the Packet to
     * @param packet The Packet to send
     */
    public static void sendPacket(Player player, Object packet) {
        try {
            Method m = getNmsClass("PlayerConnection").getMethod("sendPacket", getNmsClass("Packet"));
            if(m != null) m.invoke(getNmsClass("EntityPlayer").getField("playerConnection").get(getHandle((Object) player)), packet);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchFieldException | NoSuchMethodException e){
            e.printStackTrace();
        }
    }

    /**
     * Sets a Field for an Object
     * @param o The Object
     * @param field The Field name
     * @param value The Field value
     * @throws Exception When an Exception occurs
     */
    public static void setField(Object o, String field, Object value) throws Exception {
        setField(o.getClass(), o, field, value);
    }

    /**
     * Sets a Field of a Class for an Object
     * @param clazz The Class
     * @param o The Object
     * @param field The Field name
     * @param value The Field value
     * @throws Exception When an Exception occurs
     */
    public static void setField(Class clazz, Object o, String field, Object value) throws Exception {
        Field f = clazz.getDeclaredField(field);
        f.setAccessible(true);
        f.set(o, value);
    }

    /**
     * Returns a Field for an Object
     * @param o The Object
     * @param field The Field name
     * @return The Field value
     * @throws Exception When an Exception occurs
     */
    public static Object getField(Object o, String field) throws Exception {
        return getField(o.getClass(), o, field);
    }

    /**
     * Returns a Field of a Class for an Object
     * @param clazz The Class
     * @param o The Object
     * @param field The Field name
     * @return The Field value
     * @throws Exception When an Exception occurs
     */
    public static Object getField(Class clazz, Object o, String field) throws Exception {
        Field f = clazz.getDeclaredField(field);
        f.setAccessible(true);
        return f.get(o);
    }

    /**
     * Invokes a private method on an Object
     * @param o The Object
     * @param name The name of the method
     * @param argTypes The argument types of the method
     * @param args The arguments to invoke the method with
     * @return The Object returned by the method
     * @throws Exception if an exception occurs
     */
    public static Object invokeMethod(Object o, String name, Class[] argTypes, Object... args) throws Exception {
        return invokeMethod(o.getClass(), o, name, argTypes, args);
    }

    /**
     * Invokes a private method on an Object
     * @param clazz The Class that declares the method
     * @param o The Object
     * @param name The name of the method
     * @param argTypes The argument types of the method
     * @param args The arguments to invoke the method with
     * @return The Object returned by the method
     * @throws Exception if an exception occurs
     */
    public static Object invokeMethod(Class clazz, Object o, String name, Class[] argTypes, Object... args) throws Exception {
        Method m = clazz.getDeclaredMethod(name, argTypes);
        m.setAccessible(true);
        return m.invoke(o, args);
    }

    /**
     * Invokes a private constructor of a Class
     * @param clazz The Class that declares the constructor
     * @param argTypes The argument types of the constructor
     * @param args The arguments to invoke the constructor with
     * @return The Object returned by the constructor
     * @throws Exception if an exception occurs
     */
    public static Object invokeConstructor(Class clazz, Class[] argTypes, Object... args) throws Exception {
        Constructor constructor = clazz.getDeclaredConstructor(argTypes);
        constructor.setAccessible(true);
        return constructor.newInstance(args);
    }

    /**
     * Returns whether the Version is bigger than or equal to a number at a certain depth
     * Examples:
     * - Bukkit version=1.8.3, depth=2, number=3 returns true
     * - Bukkit version=1.7.2, depth=1, number=8 returns false
     * @param depth The depth (position) of the number we should compare
     * @param number The number we should compare the version to
     * @return Wether the Version is bigger than or equal to a number at a certain depth
     */
    public static boolean verBiggerThan(int depth, int number) {
        String[] splitted = Bukkit.getBukkitVersion().split("\\-")[0].split("\\.");
        return Parsing.parseInt(splitted[depth]) >= number;
    }

    /**
     * Returns the Bukkit Version
     * @return The Bukkit Version
     */
    public static String getBukkitVersion() {
        return Bukkit.getBukkitVersion().split("\\-")[0];
    }

    /**
     * Returns the current NMS version
     * @return The NMS version
     */
    public static String getNmsVersion() {
        String[] array = Bukkit.getServer().getClass().getPackage().getName().split("\\.");
        if (array.length == 4) return array[3];
        return null;
    }

    /**
     * Returns the ChatSerializer Class
     * @return The ChatSerializer Class
     */
    public static Class getChatSerializerClass() {
        return verBiggerThan(1, 9) || (verBiggerThan(1, 8) && verBiggerThan(2, 3)) ? getNmsClass("IChatBaseComponent$ChatSerializer") : getNmsClass("ChatSerializer");
    }

    /**
     * Returns the EnumWorldBorderAction Class
     * @return The EnumWorldBorderAction Class
     */
    public static Class getEnumWorldBorderActionClass() {
        return verBiggerThan(1, 9) || (verBiggerThan(1, 8) && verBiggerThan(2, 3)) ? getNmsClass("PacketPlayOutWorldBorder$EnumWorldBorderAction") : getNmsClass("EnumWorldBorderAction");
    }
}
