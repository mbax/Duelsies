package org.kitteh.duelsies;

import java.util.ArrayList;
import java.util.HashSet;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class Duelsies extends JavaPlugin implements Listener {
    public boolean round1 = true;

    private Player duel1;
    private Player duel2;
    private boolean started = false;
    public Location spawn;
    private Location start1;
    private Location start2;
    public ArrayList<String> mainlist = new ArrayList<String>();

    public ArrayList<String> waitlist = new ArrayList<String>();

    public HashSet<String> blacklist = new HashSet<String>();

    public boolean hasStarted() {
        return this.started;
    }

    public void newDuel(Player p1, Player p2) {
        this.duelSpawn();
        this.duel1 = p1;
        this.duel2 = p2;

        this.duel1.teleport(this.start1);
        this.duel1.setHealth(this.duel1.getMaxHealth());
        this.duel1.setFoodLevel(7);
        final PlayerInventory inv1 = this.duel1.getInventory();
        inv1.clear();
        inv1.setHelmet(new ItemStack(Material.IRON_HELMET));
        inv1.setBoots(new ItemStack(Material.IRON_BOOTS));
        inv1.setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        inv1.setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        inv1.addItem(new ItemStack(Material.STONE_SWORD));

        this.duel2.teleport(this.start2);
        this.duel2.setHealth(this.duel2.getMaxHealth());
        this.duel2.setFoodLevel(7);
        final PlayerInventory inv2 = this.duel2.getInventory();
        inv2.clear();
        inv2.setHelmet(new ItemStack(Material.IRON_HELMET));
        inv2.setBoots(new ItemStack(Material.IRON_BOOTS));
        inv2.setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        inv2.setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        inv2.addItem(new ItemStack(Material.STONE_SWORD));
    }
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        final Player player = event.getEntity();
        if (player.equals(this.duel1)) {
            this.getServer().broadcastMessage(ChatColor.AQUA + this.duel2.getName() + " wins!");
            this.duelSpawn();
        }
        if (player.equals(this.duel2)) {
            this.getServer().broadcastMessage(ChatColor.AQUA + this.duel1.getName() + " wins!");
            this.duelSpawn();
        }
    }
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        this.spawn = this.getServer().getWorld("world").getSpawnLocation();
        this.start1 = new Location(this.getServer().getWorld("world"), -512, 65.5, -154, 0, 0);
        this.start2 = new Location(this.getServer().getWorld("world"), -496, 65.5, -154, 0, 0);
        this.getCommand("duel").setExecutor(new DuelCommand(this));
        this.getCommand("next").setExecutor(new NextCommand(this));
        this.getCommand("join").setExecutor(new JoinCommand(this));
        this.getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        this.getCommand("round1over").setExecutor(new Round1OverCommand(this));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        if (player.equals(this.duel1)) {
            this.getServer().broadcastMessage(ChatColor.AQUA + this.duel2.getName() + " wins!");
            this.blacklist.add(player.getName());
        }
        if (player.equals(this.duel2)) {
            this.getServer().broadcastMessage(ChatColor.AQUA + this.duel1.getName() + " wins!");
            this.blacklist.add(player.getName());
        }
    }

    public void start() {
        this.started = true;
    }

    private void duelSpawn() {
        if (this.duel1 != null) {
            this.duel1.teleport(this.spawn);
            this.duel1.sendMessage(ChatColor.RED + "Good work, sending you to spawn");
        }
        if (this.duel2 != null) {
            this.duel2.teleport(this.spawn);
            this.duel2.sendMessage(ChatColor.RED + "Good work, sending you to spawn");
        }
        this.duel1 = null;
        this.duel2 = null;
    }

}
