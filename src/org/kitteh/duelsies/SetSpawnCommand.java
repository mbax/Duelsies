package org.kitteh.duelsies;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    private final Duelsies plugin;

    public SetSpawnCommand(Duelsies plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            final Location loc = ((Player) sender).getLocation();
            this.plugin.getServer().getWorld("world").setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
            this.plugin.spawn = this.plugin.getServer().getWorld("world").getSpawnLocation();
        }
        return true;
    }
}
