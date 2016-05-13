package com.j0ach1mmall3.jlib.plugin.modularization;

import com.j0ach1mmall3.jlib.plugin.JLibPlugin;
import com.j0ach1mmall3.jlib.storage.file.yaml.ConfigLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 8/03/2016
 */
public abstract class ModularizedPlugin<C extends ConfigLoader> extends JLibPlugin<C> {
    protected final List<PluginModule> modules = new ArrayList<>();

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
    public List<PluginModule> getModules() {
        return this.modules;
    }

    /**
     * Returns all the enabled PluginModules
     * @return The enabled PluginModules
     */
    public List<PluginModule> getEnabledModules() {
        List<PluginModule> modules = new ArrayList<>();
        for(PluginModule module : this.modules) {
            if(module.isEnabled()) modules.add(module);
        }
        return modules;
    }
}
