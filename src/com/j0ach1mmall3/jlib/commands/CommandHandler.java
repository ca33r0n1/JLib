package com.j0ach1mmall3.jlib.commands;

import com.j0ach1mmall3.jlib.methods.General;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

/**
 * Created by j0ach1mmall3 on 14:53 27/09/2015 using IntelliJ IDEA.
 */
public abstract class CommandHandler implements CommandExecutor {
    private Command command;
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
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

    public void registerCommand(Command command) {
        this.command = command;
        if(command.getPlugin().getCommand(command.getName()) != null) {
            command.getPlugin().getCommand(command.getName()).setExecutor(this);
        } else {
            General.sendColoredMessage(command.getPlugin(), "Failed to set CommandHandler for Command + " + command.getName() + "!", ChatColor.RED);
        }
    }

    protected abstract boolean handleCommand(CommandSender sender, String[] args);
}
