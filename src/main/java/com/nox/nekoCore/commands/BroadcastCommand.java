package com.nox.nekoCore.commands;

import com.nox.nekoCore.NekoCore;
import com.nox.nekoCore.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadcastCommand implements CommandExecutor {
    private final NekoCore plugin;

    public BroadcastCommand(NekoCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("nekocore.broadcast")) {
            MessageUtil.sendMessage(sender, plugin.getConfigManager().getPrefix(), plugin.getConfigManager().getMessage("no-permission"));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§cUsage: /broadcast <message>");
            return true;
        }

        String message = String.join(" ", args);
        String broadcastMessage = plugin.getConfigManager().getMessage("broadcast-format").replace("%message%", message);

        Bukkit.broadcastMessage(broadcastMessage);
        return true;
    }
}
