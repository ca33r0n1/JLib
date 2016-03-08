package com.j0ach1mmall3.jlib.plugin.modularization;

import com.j0ach1mmall3.jlib.plugin.JlibPlugin;

import java.util.HashSet;
import java.util.Set;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 8/03/2016
 */
public abstract class ModularizedPlugin extends JlibPlugin {
    protected final Set<PluginModule> modules = new HashSet<>();

    /**
     * Registers a PluginModule to this ModularizedPlugin
     * @param module The PluginModule
     */
    protected void registerModule(PluginModule module) {
        this.modules.add(module);
    }

    /**
     * Returns all the PluginModules
     * @return The PluginModules
     */
    public Set<PluginModule> getModules() {
        return this.modules;
    }

    /**
     * Returns all the enabled PluginModules
     * @return The enabled PluginModules
     */
    public Set<PluginModule> getEnabledModules() {
        Set<PluginModule> modules = new HashSet<>();
        for(PluginModule module : this.modules) {
            if(module.isEnabled()) modules.add(module);
        }
        return modules;
    }
}
