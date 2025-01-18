package com.nox.nekoCore.commands;

import com.nox.nekoCore.NekoCore;
import com.nox.nekoCore.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    private final NekoCore plugin;

    public SpawnCommand(NekoCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            MessageUtil.sendMessage(sender, plugin.getConfigManager().getPrefix(), "§cOnly players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("nekocore.spawn")) {
            MessageUtil.sendMessage(player, plugin.getConfigManager().getPrefix(), plugin.getConfigManager().getMessage("no-permission"));
            return true;
        }

        if (!plugin.getConfig().contains("spawn.world")) {
            MessageUtil.sendMessage(player, plugin.getConfigManager().getPrefix(), plugin.getConfigManager().getMessage("spawn-not-set"));
            return true;
        }

        String worldName = plugin.getConfig().getString("spawn.world");
        double x = plugin.getConfig().getDouble("spawn.x");
        double y = plugin.getConfig().getDouble("spawn.y");
        double z = plugin.getConfig().getDouble("spawn.z");
        float yaw = (float) plugin.getConfig().getDouble("spawn.yaw");
        float pitch = (float) plugin.getConfig().getDouble("spawn.pitch");

        Location spawnLocation = new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);

        if (spawnLocation.getWorld() == null) {
            MessageUtil.sendMessage(player, plugin.getConfigManager().getPrefix(), plugin.getConfigManager().getMessage("invalid-world"));
            return true;
        }

        player.teleport(spawnLocation);
        MessageUtil.sendMessage(player, plugin.getConfigManager().getPrefix(), plugin.getConfigManager().getMessage("spawn-teleport"));
        return true;
    }
}
