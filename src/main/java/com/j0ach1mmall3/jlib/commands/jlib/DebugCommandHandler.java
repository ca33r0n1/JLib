package com.j0ach1mmall3.jlib.commands.jlib;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.commands.CommandHandler;
import com.j0ach1mmall3.jlib.logging.DebugInfo;
import com.j0ach1mmall3.jlib.plugin.JLibPlugin;
import com.j0ach1mmall3.jlib.storage.database.CallbackHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 15/04/2016
 */
public final class DebugCommandHandler extends CommandHandler {
    private final Main plugin;

    public DebugCommandHandler(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    protected boolean handleCommand(final CommandSender sender, String[] args) {
        for(final Map.Entry<JLibPlugin, DebugInfo> entry : this.plugin.getAllDebugInfo()) {
            entry.getKey().getjLogger().dumpDebug(entry.getValue(), new CallbackHandler<String>() {
                @Override
                public void callback(String o) {
                    sender.sendMessage(ChatColor.GREEN.toString() + entry.getKey() + " debug dump: " + o);
                }
            });
        }
        return true;
    }
}
