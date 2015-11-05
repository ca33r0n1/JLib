package com.j0ach1mmall3.jlib.integration.vault;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 5/11/2015
 */
public interface VaultHook {
    /**
     * Returns if there's a valid Registration for a Vault Class
     * @return If there's a valid Registration for a Vault Class
     */
    boolean isRegistered();
}
