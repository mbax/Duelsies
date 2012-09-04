package org.kitteh.duelsies;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NextCommand implements CommandExecutor {
    private final Duelsies plugin;

    public NextCommand(Duelsies plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;
        while (this.plugin.waitlist.size() > 0) {
            final String name = this.plugin.waitlist.remove(0);
            player = this.plugin.getServer().getPlayer(name);
            if (player != null) {
                break;
            }
        }
        if (player != null) {
            this.plugin.getServer().broadcastMessage(ChatColor.AQUA + "From the waitlist, grabbing " + player.getName());
        } else {
            sender.sendMessage("No more");
        }
        return true;
    }
}
