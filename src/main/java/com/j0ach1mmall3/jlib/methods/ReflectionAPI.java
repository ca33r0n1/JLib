package com.j0ach1mmall3.jlib.methods;

import com.j0ach1mmall3.jlib.logging.JLogger;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
     * Returns the NMS class by name
     * @param name The name of the class
     * @return The class
     */
    public static Class<?> getNmsClass(String name){
        String className = "net.minecraft.server." + getNmsVersion() + '.' + name;
        Class<?> clazz = null;
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
    public static Class<?> getObcClass(String name){
        String className = "org.bukkit.craftbukkit." + getNmsVersion() + '.' + name;
        Class<?> clazz = null;
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
            return getObcClass("CraftItemStack").getMethod("asNMSCopy", ItemStack.class).invoke(null, is);
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
            Method m = getNmsClass("PlayerConnection").getDeclaredMethod("sendPacket", getNmsClass("Packet"));
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
        Field f = o.getClass().getDeclaredField(field);
        f.setAccessible(true);
        f.set(o, value);
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
}
