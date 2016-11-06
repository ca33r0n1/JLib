package com.j0ach1mmall3.jlib.commands;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 27/09/15
 */
public final class Command {
    private JavaPlugin plugin;
    private final String name;
    private final String permission;
    private final List<String> arguments;
    private final String usage;
    private final boolean console;
    private final String noPermissionMessage;

    /**
     * Constructs a new Command instance
     *
     * @param plugin              The JavaPlugin instance of this command
     * @param name                The name of the command specified in the plugin.yml
     * @param permission          The permission to use this command
     * @param arguments           All the possible arguments for this command
     * @param usage               The message that should be sent when a player types a wrong/no argument
     * @param console             Should the console also be able to execute this command?
     * @param noPermissionMessage The message that should be sent when a player doesn't have permission to execute this command
     */
    @Deprecated
    public Command(JavaPlugin plugin, String name, String permission, List<String> arguments, String usage, boolean console, String noPermissionMessage) {
        this.plugin = plugin;
        this.name = name;
        this.permission = permission;
        this.arguments = arguments;
        this.usage = usage;
        this.console = console;
        this.noPermissionMessage = noPermissionMessage;
    }

    /**
     * Constructs a new Command instance
     *
     * @param plugin              The JavaPlugin instance of this command
     * @param name                The name of the command specified in the plugin.yml
     * @param permission          The permission to use this command
     * @param usage               The message that should be sent when a player types a wrong/no argument
     * @param console             Should the console also be able to execute this command?
     * @param noPermissionMessage The message that should be sent when a player doesn't have permission to execute this command
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public Command(JavaPlugin plugin, String name, String permission, String usage, boolean console, String noPermissionMessage) {
        this(plugin, name, permission, new ArrayList<>(), usage, console, noPermissionMessage);
    }

    /**
     * Constructs a new Command instance
     *
     * @param plugin              The JavaPlugin instance of this command
     * @param name                The name of the command specified in the plugin.yml
     * @param permission          The permission to use this command
     * @param arguments           All the possible arguments for this command
     * @param usage               The message that should be sent when a player types a wrong/no argument
     * @param noPermissionMessage The message that should be sent when a player doesn't have permission to execute this command
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public Command(JavaPlugin plugin, String name, String permission, List<String> arguments, String usage, String noPermissionMessage) {
        this(plugin, name, permission, arguments, usage, true, noPermissionMessage);
    }

    /**
     * Constructs a new Command instance
     *
     * @param plugin              The JavaPlugin instance of this command
     * @param name                The name of the command specified in the plugin.yml
     * @param arguments           All the possible arguments for this command
     * @param usage               The message that should be sent when a player types a wrong/no argument
     * @param noPermissionMessage The message that should be sent when a player doesn't have permission to execute this command
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public Command(JavaPlugin plugin, String name, List<String> arguments, String usage, String noPermissionMessage) {
        this(plugin, name, "", arguments, usage, true, noPermissionMessage);
    }

    /**
     * Constructs a new Command instance
     *
     * @param plugin    The JavaPlugin instance of this command
     * @param name      The name of the command specified in the plugin.yml
     * @param arguments All the possible arguments for this command
     * @param usage     The message that should be sent when a player types a wrong/no argument
     * @param console   Should the console also be able to execute this command?
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public Command(JavaPlugin plugin, String name, List<String> arguments, String usage, boolean console) {
        this(plugin, name, "", arguments, usage, console, ChatColor.RED + "You don't have permission to do this!");
    }

    /**
     * Constructs a new Command instance
     *
     * @param plugin     The JavaPlugin instance of this command
     * @param name       The name of the command specified in the plugin.yml
     * @param permission The permission to use this command
     * @param usage      The message that should be sent when a player types a wrong/no argument
     * @param console    Should the console also be able to execute this command?
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public Command(JavaPlugin plugin, String name, String permission, String usage, boolean console) {
        this(plugin, name, permission, new ArrayList<>(), usage, console, ChatColor.RED + "You don't have permission to do this!");
    }

    /**
     * Constructs a new Command instance
     *
     * @param plugin     The JavaPlugin instance of this command
     * @param name       The name of the command specified in the plugin.yml
     * @param permission The permission to use this command
     * @param arguments  All the possible arguments for this command
     * @param usage      The message that should be sent when a player types a wrong/no argument
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public Command(JavaPlugin plugin, String name, String permission, List<String> arguments, String usage) {
        this(plugin, name, permission, arguments, usage, true, ChatColor.RED + "You don't have permission to do this!");
    }

    /**
     * Constructs a new Command instance
     *
     * @param plugin              The JavaPlugin instance of this command
     * @param name                The name of the command specified in the plugin.yml
     * @param permission          The permission to use this command
     * @param usage               The message that should be sent when a player types a wrong/no argument
     * @param noPermissionMessage The message that should be sent when a player doesn't have permission to execute this command
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public Command(JavaPlugin plugin, String name, String permission, String usage, String noPermissionMessage) {
        this(plugin, name, permission, new ArrayList<>(), usage, true, noPermissionMessage);
    }

    /**
     * Constructs a new Command instance
     *
     * @param plugin  The JavaPlugin instance of this command
     * @param name    The name of the command specified in the plugin.yml
     * @param usage   The message that should be sent when a player types a wrong/no argument
     * @param console Should the console also be able to execute this command?
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public Command(JavaPlugin plugin, String name, String usage, boolean console) {
        this(plugin, name, "", new ArrayList<>(), usage, console, ChatColor.RED + "You don't have permission to do this!");
    }

    /**
     * Constructs a new Command instance
     *
     * @param plugin    The JavaPlugin instance of this command
     * @param name      The name of the command specified in the plugin.yml
     * @param arguments All the possible arguments for this command
     * @param usage     The message that should be sent when a player types a wrong/no argument
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public Command(JavaPlugin plugin, String name, String usage, List<String> arguments) {
        this(plugin, name, "", arguments, usage, true, ChatColor.RED + "You don't have permission to do this!");
    }

    /**
     * Constructs a new Command instance
     *
     * @param plugin     The JavaPlugin instance of this command
     * @param name       The name of the command specified in the plugin.yml
     * @param permission The permission to use this command
     * @param usage      The message that should be sent when a player types a wrong/no argument
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public Command(JavaPlugin plugin, String name, String permission, String usage) {
        this(plugin, name, permission, new ArrayList<>(), usage, true, ChatColor.RED + "You don't have permission to do this!");
    }

    /**
     * Constructs a new Command instance
     *
     * @param plugin The JavaPlugin instance of this command
     * @param name   The name of the command specified in the plugin.yml
     * @param usage  The message that should be sent when a player types a wrong/no argument
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public Command(JavaPlugin plugin, String name, String usage) {
        this(plugin, name, "", new ArrayList<>(), usage, true, ChatColor.RED + "You don't have permission to do this!");
    }

    /**
     * Constructs a new Command instance
     *
     * @param name                The name of the command specified in the plugin.yml
     * @param permission          The permission to use this command
     * @param arguments           All the possible arguments for this command
     * @param usage               The message that should be sent when a player types a wrong/no argument
     * @param console             Whether the console should be able to execute this command
     * @param noPermissionMessage The message that should be sent when a player doesn't have permission to execute this command
     */
    public Command(String name, String permission, List<String> arguments, String usage, boolean console, String noPermissionMessage) {
        this.name = name;
        this.permission = permission;
        this.arguments = arguments;
        this.usage = usage;
        this.console = console;
        this.noPermissionMessage = noPermissionMessage;
    }

    /**
     * Constructs a new Command instance
     *
     * @param name       The name of the command specified in the plugin.yml
     * @param permission The permission to use this command
     * @param arguments  All the possible arguments for this command
     * @param usage      The message that should be sent when a player types a wrong/no argument
     * @param console    Whether the console should be able to execute this command
     */
    public Command(String name, String permission, List<String> arguments, String usage, boolean console) {
        this(name, permission, arguments, usage, console, ChatColor.RED + "You don't have permission to do this!");
    }

    /**
     * Constructs a new Command instance
     *
     * @param name       The name of the command specified in the plugin.yml
     * @param permission The permission to use this command
     * @param arguments  All the possible arguments for this command
     * @param usage      The message that should be sent when a player types a wrong/no argument
     */
    public Command(String name, String permission, List<String> arguments, String usage) {
        this(name, permission, arguments, usage, true);
    }

    /**
     * Constructs a new Command instance
     *
     * @param name       The name of the command specified in the plugin.yml
     * @param permission The permission to use this command
     * @param usage      The message that should be sent when a player types a wrong/no argument
     */
    public Command(String name, String permission, String usage) {
        this(name, permission, new ArrayList<>(), usage);
    }

    /**
     * Constructs a new Command instance
     *
     * @param name                The name of the command specified in the plugin.yml
     * @param permission          The permission to use this command
     * @param arguments           All the possible arguments for this command
     * @param usage               The message that should be sent when a player types a wrong/no argument
     * @param noPermissionMessage The message that should be sent when a player doesn't have permission to execute this command
     */
    public Command(String name, String permission, List<String> arguments, String usage, String noPermissionMessage) {
        this(name, permission, arguments, usage, true, noPermissionMessage);
    }

    /**
     * Constructs a new Command instance
     *
     * @param name                The name of the command specified in the plugin.yml
     * @param permission          The permission to use this command
     * @param usage               The message that should be sent when a player types a wrong/no argument
     * @param noPermissionMessage The message that should be sent when a player doesn't have permission to execute this command
     */
    public Command(String name, String permission, String usage, String noPermissionMessage) {
        this(name, permission, new ArrayList<>(), usage, true, noPermissionMessage);
    }

    /**
     * Returns the JavaPlugin instance of this command
     *
     * @return The JavaPlugin instance
     */
    @Deprecated
    public JavaPlugin getPlugin() {
        return this.plugin;
    }

    /**
     * Returns the name of this command
     *
     * @return The name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the permission of this command
     *
     * @return The permission
     */
    public String getPermission() {
        return this.permission;
    }

    /**
     * Returns the arguments of this command
     *
     * @return The arguments
     */
    public List<String> getArguments() {
        return this.arguments;
    }

    /**
     * Returns the usage of this command
     *
     * @return The usage
     */
    public String getUsage() {
        return this.usage;
    }

    /**
     * Returns whether the command should be executable by the console
     *
     * @return Wether the command should be executable by the console
     */
    public boolean isConsole() {
        return this.console;
    }

    /**
     * Returns the message that will be sent when a player doesn't have permission to use this command
     *
     * @return The NoPermissionMessage
     */
    public String getNoPermissionMessage() {
        return this.noPermissionMessage;
    }
}
