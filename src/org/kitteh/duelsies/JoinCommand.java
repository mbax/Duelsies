package org.kitteh.duelsies;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinCommand implements CommandExecutor {
    private final Duelsies plugin;

    public JoinCommand(Duelsies plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            final String name = sender.getName().toLowerCase();
            if (this.plugin.blacklist.contains(name)) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "Already on lists");
                return true;
            }
            if (this.plugin.mainlist.contains(name)) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "Already on tourney list!");
                return true;
            }
            if (this.plugin.mainlist.contains(name)) {
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "Already on waitlist!");
                return true;
            }
            if (!this.plugin.hasStarted() && (this.plugin.mainlist.size() < 32)) {
                this.plugin.mainlist.add(name);
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "Added to tourney!");
                this.plugin.getServer().broadcast(this.plugin.mainlist.size() + " joined", "duelsies.control");
                if (this.plugin.mainlist.size() == 32) {
                    this.plugin.getServer().broadcastMessage(ChatColor.BLUE + "32 players signed up. Still waitlist room!");
                }
            } else {
                this.plugin.waitlist.add(name);
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "Added to waitlist, spot " + this.plugin.waitlist.size());
            }
        }
        return true;
    }
}
