package com.j0ach1mmall3.jlib.vault;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/2015
 */
public class PermissionHook implements VaultHook {
    private RegisteredServiceProvider<?> provider;
    private boolean vaultPermission;

    /**
     * Constructs a new PermissionHook instance
     */
    public PermissionHook() {
        vaultPermission = Bukkit.getPluginManager().getPlugin("Vault") != null;
        provider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
    }

    /**
     * Returns the Vault Permission instance
     * @return The Vault Permission instance
     *
     */
    public net.milkbowl.vault.permission.Permission getPermission(){
        if(vaultPermission) return (net.milkbowl.vault.permission.Permission) provider.getProvider();
        return null;
    }

    /**
     * Returns if there's a valid Registration for Permission.class
     * @return If there's a valid Registration for Permission.class
     */
    public boolean isRegistered() {
        return vaultPermission && provider != null && provider.getProvider() != null;
    }
}
