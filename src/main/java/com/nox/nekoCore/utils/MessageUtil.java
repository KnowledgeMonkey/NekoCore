package com.nox.nekoCore.utils;

import org.bukkit.command.CommandSender;

public class MessageUtil {
    public static void sendMessage(CommandSender sender, String prefix, String message) {
        sender.sendMessage(prefix + message);
    }
}
