package com.nox.nekoCore.managers;

import com.nox.nekoCore.NekoCore;

public class ConfigManager {
    private final NekoCore plugin;

    public ConfigManager(NekoCore plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
    }

    public String getPrefix() {
        return plugin.getConfig().getString("prefix", "§6[§dNekoCore§6] §f");
    }

    public String getMessage(String path) {
        return plugin.getConfig().getString("messages." + path, "Message not found.");
    }
}
