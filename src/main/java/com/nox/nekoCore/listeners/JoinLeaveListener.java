package com.nox.nekoCore.listeners;

import com.nox.nekoCore.NekoCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener {
    private final NekoCore plugin;

    public JoinLeaveListener(NekoCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String joinMessage = plugin.getConfig().getString("messages.join-message", "§a[+] §e%player%");
        joinMessage = joinMessage.replace("%player%", event.getPlayer().getName());
        event.setJoinMessage(joinMessage); // Sets the join message (null = no join message)
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        String leaveMessage = plugin.getConfig().getString("messages.leave-message", "§c[-] §e%player%");
        leaveMessage = leaveMessage.replace("%player%", event.getPlayer().getName());
        event.setQuitMessage(leaveMessage); // Sets the leave message (null = no quit message)
    }
}
