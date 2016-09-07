package com.j0ach1mmall3.jlib.integration.vault;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/15
 */
public final class PermissionHook implements VaultHook {
    private RegisteredServiceProvider provider;
    private final boolean vaultPermission;

    /**
     * Constructs a new PermissionHook instance
     */
    public PermissionHook() {
        this.vaultPermission = Bukkit.getPluginManager().getPlugin("Vault") != null;
        if(this.vaultPermission) this.provider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
    }

    /**
     * Returns the Vault Permission instance
     * @return The Vault Permission instance
     *
     */
    public net.milkbowl.vault.permission.Permission getPermission(){
        if(this.vaultPermission) return (net.milkbowl.vault.permission.Permission) this.provider.getProvider();
        return null;
    }

    /**
     * Returns whether there's a valid Registration for Permission.class
     * @return Wether there's a valid Registration for Permission.class
     */
    @Override
    public boolean isRegistered() {
        return this.vaultPermission && this.provider != null && this.provider.getProvider() != null;
    }
}
