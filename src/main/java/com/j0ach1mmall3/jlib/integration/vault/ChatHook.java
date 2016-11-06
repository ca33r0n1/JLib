package com.j0ach1mmall3.jlib.integration.vault;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/15
 */
public final class ChatHook implements VaultHook {
    private RegisteredServiceProvider provider;
    private final boolean vaultChat;

    /**
     * Constructs a new ChatHook instance
     */
    public ChatHook() {
        this.vaultChat = Bukkit.getPluginManager().getPlugin("Vault") != null;
        if (this.vaultChat)
            this.provider = Bukkit.getServer().getServicesManager().getRegistration(Chat.class);
    }

    /**
     * Returns the Vault Chat instance
     *
     * @return The Vault Chat instance
     */
    public Chat getChat() {
        if (this.vaultChat) return (Chat) this.provider.getProvider();
        return null;
    }

    /**
     * Returns whether there's a valid Registration for Chat.class
     *
     * @return Wether there's a valid Registration for Chat.class
     */
    @Override
    public boolean isRegistered() {
        return this.vaultChat && this.provider != null && this.provider.getProvider() != null;
    }
}
