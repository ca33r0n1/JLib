package com.j0ach1mmall3.jlib.integration.vault;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/15
 */
public final class EconomyHook implements VaultHook {
    private RegisteredServiceProvider<?> provider;
    private final boolean vaultEconomy;

    /**
     * Constructs a new EconomyHook instance
     */
    public EconomyHook() {
        this.vaultEconomy = Bukkit.getPluginManager().getPlugin("Vault") != null;
        if(this.vaultEconomy) this.provider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
    }

    /**
     * Returns the Vault Economy instance
     * @return The Vault Economy instance
     *
     */
    public net.milkbowl.vault.economy.Economy getEconomy(){
        if(this.vaultEconomy) return (net.milkbowl.vault.economy.Economy) this.provider.getProvider();
        return null;
    }

    /**
     * Returns whether there's a valid Registration for Economy.class
     * @return Wether there's a valid Registration for Economy.class
     */
    @Override
    public boolean isRegistered() {
        return this.vaultEconomy && this.provider != null && this.provider.getProvider() != null;
    }
}
