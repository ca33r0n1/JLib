package com.j0ach1mmall3.jlib.commands;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by j0ach1mmall3 on 14:44 27/09/2015 using IntelliJ IDEA.
 */
public final class Command {
    private final JavaPlugin plugin;
    private final String name;
    private final String permission;
    private final List<String> arguments;
    private final String usage;
    private final boolean console;
    private final String noPermissionMessage;

    public Command(JavaPlugin plugin, String name, String permission, List<String> arguments, String usage, boolean console, String noPermissionMessage) {
        this.plugin = plugin;
        this.name = name;
        this.permission = permission;
        this.arguments = arguments;
        this.usage = usage;
        this.console = console;
        this.noPermissionMessage = noPermissionMessage;
    }

    public Command(JavaPlugin plugin, String name, String permission, String usage, boolean console, String noPermissionMessage) {
        this(plugin, name, permission, new ArrayList<String>(), usage, console, noPermissionMessage);
    }

    public Command(JavaPlugin plugin, String name, String permission, List<String> arguments, String usage, String noPermissionMessage) {
        this(plugin, name, permission, arguments, usage, true, noPermissionMessage);
    }

    public Command(JavaPlugin plugin, String name, List<String> arguments, String usage, String noPermissionMessage) {
        this(plugin, name, "", arguments, usage, true, noPermissionMessage);
    }

    public Command(JavaPlugin plugin, String name, List<String> arguments, String usage, boolean console) {
        this(plugin, name, "", arguments, usage, console, ChatColor.RED + "You don't have permission to do this!");
    }

    public Command(JavaPlugin plugin, String name, String permission, String usage, boolean console) {
        this(plugin, name, permission, new ArrayList<String>(), usage, console, ChatColor.RED + "You don't have permission to do this!");
    }

    public Command(JavaPlugin plugin, String name, String permission, List<String> arguments, String usage) {
        this(plugin, name, permission, arguments, usage, true, ChatColor.RED + "You don't have permission to do this!");
    }

    public Command(JavaPlugin plugin, String name, String permission, String usage, String noPermissionMessage) {
        this(plugin, name, permission, new ArrayList<String>(), usage, true, noPermissionMessage);
    }

    public Command(JavaPlugin plugin, String name, String usage, boolean console) {
        this(plugin, name, "", new ArrayList<String>(), usage, console, ChatColor.RED + "You don't have permission to do this!");
    }

    public Command(JavaPlugin plugin, String name, String usage, List<String> arguments) {
        this(plugin, name, "", arguments, usage, true, ChatColor.RED + "You don't have permission to do this!");
    }

    public Command(JavaPlugin plugin, String name, String permission, String usage) {
        this(plugin, name, permission, new ArrayList<String>(), usage, true, ChatColor.RED + "You don't have permission to do this!");
    }

    public Command(JavaPlugin plugin, String name, String usage) {
        this(plugin, name, "", new ArrayList<String>(), usage, true, ChatColor.RED + "You don't have permission to do this!");
    }

    public JavaPlugin getPlugin() {
        return this.plugin;
    }

    public String getName() {
        return this.name;
    }

    public String getPermission() {
        return this.permission;
    }

    public List<String> getArguments() {
        return this.arguments;
    }

    public String getUsage() {
        return this.usage;
    }

    public boolean isConsole() {
        return this.console;
    }

    public String getNoPermissionMessage() {
        return this.noPermissionMessage;
    }
}
