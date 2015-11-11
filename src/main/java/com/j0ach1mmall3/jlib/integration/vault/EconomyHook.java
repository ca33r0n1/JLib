package com.j0ach1mmall3.jlib.integration.vault;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/2015
 */
public class EconomyHook implements VaultHook {
    private RegisteredServiceProvider<?> provider;
    private boolean vaultEconomy;

    /**
     * Constructs a new EconomyHook instance
     */
    public EconomyHook() {
        vaultEconomy = Bukkit.getPluginManager().getPlugin("Vault") != null;
        if(vaultEconomy) provider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
    }

    /**
     * Returns the Vault Economy instance
     * @return The Vault Economy instance
     *
     */
    public net.milkbowl.vault.economy.Economy getEconomy(){
        if(vaultEconomy) return (net.milkbowl.vault.economy.Economy) provider.getProvider();
        return null;
    }

    /**
     * Returns if there's a valid Registration for Economy.class
     * @return If there's a valid Registration for Economy.class
     */
    public boolean isRegistered() {
        return vaultEconomy && provider != null && provider.getProvider() != null;
    }
}
