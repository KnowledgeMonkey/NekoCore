package com.nox.nekoCore.commands;

import com.nox.nekoCore.NekoCore;
import com.nox.nekoCore.utils.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class NekoCoreCommand implements CommandExecutor {
    private final NekoCore plugin;

    public NekoCoreCommand(NekoCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = plugin.getConfigManager().getPrefix();

        // Check for arguments
        if (args.length == 0) {
            // Display plugin info
            sender.sendMessage("");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix) + ChatColor.RESET);
            sender.sendMessage(ChatColor.GOLD + "--------------------------------");
            sender.sendMessage(ChatColor.AQUA + "Plugin: " + ChatColor.BOLD + "NekoCore");
            sender.sendMessage(ChatColor.AQUA + "Author: " + ChatColor.LIGHT_PURPLE + "Nox");
            sender.sendMessage(ChatColor.AQUA + "Description: " + ChatColor.WHITE + "A core plugin for managing server essentials.");
            sender.sendMessage("");
            sender.sendMessage(ChatColor.AQUA + "Commands:");
            sender.sendMessage(ChatColor.GRAY + "  - " + ChatColor.YELLOW + "/nekocore reload" + ChatColor.WHITE + " - Reloads the plugin configuration.");
            sender.sendMessage(ChatColor.GRAY + "  - " + ChatColor.YELLOW + "/broadcast" + ChatColor.WHITE + " - Broadcast a message to all players.");
            sender.sendMessage(ChatColor.GRAY + "  - " + ChatColor.YELLOW + "/setspawn" + ChatColor.WHITE + " - Set the server spawn point.");
            sender.sendMessage(ChatColor.GRAY + "  - " + ChatColor.YELLOW + "/spawn" + ChatColor.WHITE + " - Teleport to the spawn point.");
            sender.sendMessage(ChatColor.GOLD + "--------------------------------");
            sender.sendMessage("");
            return true;
        }

        // Check if the argument is "reload"
        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("nekocore.admin")) {
                MessageUtil.sendMessage(sender, prefix, plugin.getConfigManager().getMessage("no-permission"));
                return true;
            }

            // Reload the configuration
            plugin.reloadConfig();
            plugin.getConfigManager().loadConfig(); // Ensure ConfigManager updates
            MessageUtil.sendMessage(sender, prefix, "§aConfiguration reloaded successfully!");
            return true;
        }

        // If the argument is invalid
        MessageUtil.sendMessage(sender, prefix, "§cInvalid argument. Use §e/nekocore §cor §e/nekocore reload§c.");
        return true;
    }
}
