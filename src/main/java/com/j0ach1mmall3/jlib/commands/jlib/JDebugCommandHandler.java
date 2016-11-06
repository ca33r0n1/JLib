package com.j0ach1mmall3.jlib.commands.jlib;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.commands.CommandHandler;
import com.j0ach1mmall3.jlib.logging.DebugInfo;
import com.j0ach1mmall3.jlib.plugin.JLibPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 15/04/2016
 */
public final class JDebugCommandHandler extends CommandHandler<Main> {
    /**
     * Constructs a new JDebugCommandHandler
     *
     * @param plugin The Main plugin
     */
    public JDebugCommandHandler(Main plugin) {
        super(plugin);
    }

    @Override
    protected boolean handleCommand(CommandSender sender, String[] args) {
        for (Map.Entry<JLibPlugin, DebugInfo> entry : this.plugin.getAllDebugInfo()) {
            entry.getKey().getjLogger().dumpDebug(entry.getValue(), o -> sender.sendMessage(ChatColor.GREEN.toString() + entry.getKey() + " debug dump: " + o));
        }
        return true;
    }
}
