package com.j0ach1mmall3.jlib.methods;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ReflectionAPI {
    @Deprecated
	public static String getVersion(){
        return getNmsVersion();
	}
	
	public static boolean useSpigot(){
		String path = "org.spigotmc.Metrics";
		try{
			Class.forName(path);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		    return false;
		}
	}
	
	public static Class<?> getNmsClass(String name){
		String className = "net.minecraft.server." + getNmsVersion() + "." + name;
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		}
		catch (ClassNotFoundException e){
            e.printStackTrace();
        }
		return clazz;
	}

	public static Class<?> getObcClass(String name){
		String className = "org.bukkit.craftbukkit." + getNmsVersion() + "." + name;
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		}
		catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		return clazz;
	}

    public static Object getHandle(Entity entity){
		HashMap<Class<? extends Entity>, Method> handles = new HashMap<>();
		try {
			if (handles.get(entity.getClass()) != null)
				return handles.get(entity.getClass()).invoke(entity);
			else {
				Method getHandle = entity.getClass().getMethod("getHandle");
				handles.put(entity.getClass(), getHandle);
				return getHandle.invoke(entity);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Object getHandle(World world){
		Class<?> craftWorldClass = getObcClass("CraftWorld");
		try {
			return craftWorldClass.getMethod("getHandle").invoke(world);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void sendPacket(Player p, Object packet) throws IllegalArgumentException {
		Method sendPacket = null;
		try {
            Object handle = getHandle(p);
            if(handle != null) {
                Class c = handle.getClass();
                if (c != null) {
                    Field playerConnection = c.getField("playerConnection");
                    for (Method m : playerConnection.get(ReflectionAPI.getHandle(p)).getClass().getMethods()) {
                        if (m.getName().equalsIgnoreCase("sendPacket")) {
                            sendPacket = m;
                        }
                    }
                    if(sendPacket != null) sendPacket.invoke(playerConnection.get(handle), packet);
                }
            }
		} catch (IllegalAccessException | InvocationTargetException | NoSuchFieldException e){
			e.printStackTrace();
		}
	}

    public static boolean verBiggerThan(int depth, int version) {
        return Parsing.parseString(Bukkit.getBukkitVersion().split("\\-")[0].split("\\.")[depth]) >= version;
    }

    public static String getBukkitVersion() {
        return Bukkit.getBukkitVersion().split("\\-")[0];
    }

    public static String getNmsVersion() {
        String[] array = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",");
        if (array.length == 4) return array[3];
        return null;
    }
}
