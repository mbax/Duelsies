package org.kitteh.duelsies;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Round1OverCommand implements CommandExecutor {
    private final Duelsies plugin;

    public Round1OverCommand(Duelsies plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            this.plugin.round1 = false;
            sender.sendMessage(ChatColor.AQUA + "Round 1 over!");
        }
        return true;
    }
}
