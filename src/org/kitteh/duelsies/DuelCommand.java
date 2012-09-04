package org.kitteh.duelsies;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DuelCommand implements CommandExecutor {
    private final Duelsies plugin;

    public DuelCommand(Duelsies plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {

            //Duel start
            if ((args.length > 0) && args[0].equals("start")) {
                this.plugin.start();
                this.plugin.getServer().broadcastMessage(ChatColor.AQUA + "Duel night has started!");
                this.plugin.getServer().broadcastMessage(ChatColor.AQUA + "Creating the tourney list now");
                this.plugin.getServer().broadcastMessage(ChatColor.AQUA + "Only waitlist signups now possible");
                final StringBuilder builder = new StringBuilder();
                int count = 0;
                for (final String name : this.plugin.mainlist) {
                    if (this.plugin.getServer().getPlayer(name) == null) {
                        continue;
                    }
                    count++;
                    builder.append(name).append(", ");
                }
                while ((count < 32) && (this.plugin.waitlist.size() > 0)) {
                    builder.append(this.plugin.waitlist.remove(0)).append(", ");
                }
                builder.setLength(builder.length() - 2);
                this.plugin.getServer().broadcast("List o' " + count + " players", "duelsies.control");
                this.plugin.getServer().broadcast(builder.toString(), "duelsies.control");
                return true;
            }
            //Halp
            if (this.plugin.hasStarted()) {
                sender.sendMessage("/duel player1 player2");
            } else {
                sender.sendMessage("To close main list signups: /duel start");
            }
            return true;
        }

        //Duel duel
        Player p1 = this.plugin.getServer().getPlayer(args[0]);
        Player p2 = this.plugin.getServer().getPlayer(args[1]);
        if ((p1 == null) || (p2 == null)) {
            if (p1 == null) {
                this.plugin.getServer().broadcastMessage(ChatColor.AQUA + "Missing: " + args[0]);
            }
            if (p2 == null) {
                this.plugin.getServer().broadcastMessage(ChatColor.AQUA + "Missing: " + args[1]);
            }
            if (this.plugin.round1) {
                while ((this.plugin.waitlist.size() > 0) && (p1 == null)) {

                    p1 = this.plugin.getServer().getPlayer(this.plugin.waitlist.remove(0));
                }
                while ((this.plugin.waitlist.size() > 0) && (p2 == null)) {
                    p2 = this.plugin.getServer().getPlayer(this.plugin.waitlist.remove(0));
                }
            }
            if ((p1 == null) && (p2 == null)) {
                this.plugin.getServer().broadcastMessage(ChatColor.AQUA + "Forfeit on both sides!");
            } else if (p1 == null) {
                this.plugin.getServer().broadcastMessage(ChatColor.AQUA + "Forfeit! " + p2.getName() + " wins!");
                this.plugin.blacklist.add(args[0].toLowerCase());
            } else {
                this.plugin.getServer().broadcastMessage(ChatColor.AQUA + "Forfeit! " + p1.getName() + " wins!");
                this.plugin.blacklist.add(args[1].toLowerCase());
            }
        } else {
            this.plugin.getServer().broadcastMessage(ChatColor.AQUA + "Duel time! " + ChatColor.GREEN + p1.getName() + ChatColor.AQUA + " vs " + ChatColor.GREEN + p2.getName());
            this.plugin.newDuel(p1, p2);
        }
        return true;
    }
}
