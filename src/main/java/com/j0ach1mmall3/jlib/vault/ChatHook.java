package com.j0ach1mmall3.jlib.vault;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/2015
 */
public class ChatHook implements VaultHook {
    private RegisteredServiceProvider<?> provider;
    private boolean vaultChat;

    /**
     * Constructs a new ChatHook instance
     */
    public ChatHook() {
        vaultChat = Bukkit.getPluginManager().getPlugin("Vault") != null;
        provider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
    }

    /**
     * Returns the Vault Chat instance
     * @return The Vault Chat instance
     *
     */
    public net.milkbowl.vault.chat.Chat getChat(){
        if(vaultChat) return (net.milkbowl.vault.chat.Chat) provider.getProvider();
        return null;
    }

    /**
     * Returns if there's a valid Registration for Chat.class
     * @return If there's a valid Registration for Chat.class
     */
    public boolean isRegistered() {
        return vaultChat && provider != null && provider.getProvider() != null;
    }
}
