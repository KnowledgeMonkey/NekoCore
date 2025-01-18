package com.nox.nekoCore.commands;

import com.nox.nekoCore.NekoCore;
import com.nox.nekoCore.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {
    private final NekoCore plugin;

    public TeleportCommand(NekoCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("nekocore.teleport")) {
            MessageUtil.sendMessage(sender, plugin.getConfigManager().getPrefix(), plugin.getConfigManager().getMessage("no-permission"));
            return true;
        }

        if (args.length < 2) {
            MessageUtil.sendMessage(sender, plugin.getConfigManager().getPrefix(), "§cUsage: /teleport <player> <target>");
            return true;
        }

        Player player = Bukkit.getPlayer(args[0]);
        Player target = Bukkit.getPlayer(args[1]);

        if (player == null) {
            String playerNotFound = plugin.getConfigManager().getMessage("player-not-found").replace("%player%", args[0]);
            MessageUtil.sendMessage(sender, plugin.getConfigManager().getPrefix(), playerNotFound);
            return true;
        }

        if (target == null) {
            String targetNotFound = plugin.getConfigManager().getMessage("player-not-found").replace("%player%", args[1]);
            MessageUtil.sendMessage(sender, plugin.getConfigManager().getPrefix(), targetNotFound);
            return true;
        }

        player.teleport(target.getLocation());
        String teleportMessage = "§aTeleported §e" + player.getName() + " §ato §e" + target.getName() + "§a.";
        MessageUtil.sendMessage(sender, plugin.getConfigManager().getPrefix(), teleportMessage);
        return true;
    }
}
