package com.j0ach1mmall3.jlib.commands;

import com.j0ach1mmall3.jlib.logging.JLogger;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 27/09/15
 */
public abstract class CommandHandler<P extends JavaPlugin> implements CommandExecutor {
    protected final P plugin;
    protected Command command;

    /**
     * Constructs a new CommandHandler
     *
     * @deprecated {@link CommandHandler#CommandHandler(JavaPlugin)}
     */
    @Deprecated
    protected CommandHandler() {
        this.plugin = null;
    }

    /**
     * Constructs a new CommandHandler
     *
     * @param plugin The plugins this CommandHandler is associated with
     */
    protected CommandHandler(P plugin) {
        this.plugin = plugin;
    }

    @Override
    public final boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase(this.command.getName())) {
            if (commandSender instanceof ConsoleCommandSender && !this.command.isConsole()) {
                commandSender.sendMessage(ChatColor.RED + "You need to be a player to execute this Command!");
                return true;
            }
            if (!this.command.getPermission().isEmpty() && !commandSender.hasPermission(this.command.getPermission())) {
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.command.getNoPermissionMessage()));
                return true;
            }
            if (strings.length == 0 && !this.command.getArguments().isEmpty()) {
                commandSender.sendMessage(ChatColor.RED + "Usage: " + this.command.getUsage());
                return true;
            }
            if (strings.length > 0 && !this.command.getArguments().isEmpty() && !this.command.getArguments().contains(strings[0])) {
                commandSender.sendMessage(ChatColor.RED + "Usage: " + this.command.getUsage());
                return true;
            }
            return this.handleCommand(commandSender, strings);
        }
        return false;
    }

    /**
     * Registers the command variable for this CommandHandler
     *
     * @param command The command variable
     */
    @SuppressWarnings("deprecation")
    public final void registerCommand(Command command) {
        this.command = command;
        JavaPlugin plugin = this.plugin;
        if (plugin == null) plugin = command.getPlugin();
        if (plugin.getCommand(command.getName()) == null)
            new JLogger(plugin).log(ChatColor.RED + "Failed to set CommandHandler for Command " + command.getName() + '!', JLogger.LogLevel.MINIMAL);
        else plugin.getCommand(command.getName()).setExecutor(this);
    }

    /**
     * The abstract method to handle the Command, provided by the overriding CommandHandler
     * This will get called when all the basic checks are complete
     *
     * @param sender The CommandSender that executed the command
     * @param args   The arguments provided
     * @return Wether the command is executed properly
     */
    protected abstract boolean handleCommand(CommandSender sender, String[] args);

    /**
     * Returns the Command for this CommandHandler
     *
     * @return The Command
     */
    protected final Command getCommand() {
        return this.command;
    }
}
