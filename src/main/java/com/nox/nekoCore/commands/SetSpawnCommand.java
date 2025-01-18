package com.nox.nekoCore.commands;

import com.nox.nekoCore.NekoCore;
import com.nox.nekoCore.utils.MessageUtil;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    private final NekoCore plugin;

    public SetSpawnCommand(NekoCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            MessageUtil.sendMessage(sender, plugin.getConfigManager().getPrefix(), "§cOnly players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("nekocore.setspawn")) {
            MessageUtil.sendMessage(player, plugin.getConfigManager().getPrefix(), plugin.getConfigManager().getMessage("no-permission"));
            return true;
        }

        Location location = player.getLocation();
        plugin.getConfig().set("spawn.world", location.getWorld().getName());
        plugin.getConfig().set("spawn.x", location.getX());
        plugin.getConfig().set("spawn.y", location.getY());
        plugin.getConfig().set("spawn.z", location.getZ());
        plugin.getConfig().set("spawn.yaw", location.getYaw());
        plugin.getConfig().set("spawn.pitch", location.getPitch());
        plugin.saveConfig();

        MessageUtil.sendMessage(player, plugin.getConfigManager().getPrefix(), plugin.getConfigManager().getMessage("spawn-set"));
        return true;
    }
}
