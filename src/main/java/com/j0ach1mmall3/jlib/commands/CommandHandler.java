package com.j0ach1mmall3.jlib.commands;

import com.j0ach1mmall3.jlib.methods.General;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 27/09/2015
 */
public abstract class CommandHandler implements CommandExecutor {
    private Command command;

    public final boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase(this.command.getName())) {
            if(sender instanceof ConsoleCommandSender && !this.command.isConsole()) {
                sender.sendMessage(ChatColor.RED + "You need to be a player to execute this Command!");
                return true;
            }
            if(!this.command.getPermission().equals("") && !sender.hasPermission(this.command.getPermission())) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.command.getNoPermissionMessage()));
                return true;
            }
            if(args.length == 0 && !this.command.getArguments().isEmpty()) {
                sender.sendMessage(ChatColor.RED + "Usage: " + this.command.getUsage());
                return true;
            }
            if(args.length > 0 && !this.command.getArguments().isEmpty() && !this.command.getArguments().contains(args[0])) {
                sender.sendMessage(ChatColor.RED + "Usage: " + this.command.getUsage());
                return true;
            }
            return this.handleCommand(sender, args);
        }
        return false;
    }

    /**
     * Registers the command variable for this CommandHandler
     * @param command The command variable
     */
    public final void registerCommand(Command command) {
        this.command = command;
        if(command.getPlugin().getCommand(command.getName()) != null) {
            command.getPlugin().getCommand(command.getName()).setExecutor(this);
        } else {
            General.sendColoredMessage(command.getPlugin(), "Failed to set CommandHandler for Command + " + command.getName() + "!", ChatColor.RED);
        }
    }

    /**
     * The abstract method to handle the Command, provided by the overriding CommandHandler
     * This will get called when all the basic checks are complete
     * @param sender The CommandSender that executed the command
     * @param args The arguments provided
     * @return Wether the command is executed properly
     */
    protected abstract boolean handleCommand(CommandSender sender, String[] args);

    /**
     * Returns the Command for this CommandHandler
     * @return The Command
     */
    private Command getCommand() {
        return this.command;
    }

    /**
     * Returns whether the suplied CommandHandler is equal to the current CommandHandler
     * @param commandHandler The CommandHandler to compare to
     * @return Wether they are equal
     */
    public boolean equals(CommandHandler commandHandler) {
        return commandHandler.getCommand().equals(this.command);
    }
}
