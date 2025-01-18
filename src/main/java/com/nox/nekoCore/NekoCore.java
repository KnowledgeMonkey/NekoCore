package com.nox.nekoCore;

import com.nox.nekoCore.commands.*;
import com.nox.nekoCore.listeners.JoinLeaveListener;
import com.nox.nekoCore.managers.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NekoCore extends JavaPlugin {
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        // Initialize ConfigManager
        configManager = new ConfigManager(this);
        configManager.loadConfig();

        // Register Commands
        getCommand("broadcast").setExecutor(new BroadcastCommand(this));
        getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        getCommand("spawn").setExecutor(new SpawnCommand(this));
        getCommand("teleport").setExecutor(new TeleportCommand(this));
        getCommand("nekocore").setExecutor(new NekoCoreCommand(this));
        getCommand("nekocore").setTabCompleter(new NekoCoreTabCompleter());

        // Register Events

        getServer().getPluginManager().registerEvents(new JoinLeaveListener(this), this);


        getLogger().info("NekoCore has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("NekoCore has been disabled.");
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}
