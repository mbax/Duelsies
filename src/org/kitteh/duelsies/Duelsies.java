package org.kitteh.duelsies;

import java.util.ArrayList;
import java.util.HashSet;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class Duelsies extends JavaPlugin implements Listener {
<<<<<<< HEAD
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

    private DuelConfig config;
    
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
        inv1.setHelmet(new ItemStack(config.getArmor().helm));
        inv1.setBoots(new ItemStack(config.getArmor().feetsies));
        inv1.setChestplate(new ItemStack(config.getArmor().chest));
        inv1.setLeggings(new ItemStack(config.getArmor().leg));
        inv1.addItem(new ItemStack(config.getSword()));

        this.duel2.teleport(this.start2);
        this.duel2.setHealth(this.duel2.getMaxHealth());
        this.duel2.setFoodLevel(7);
        final PlayerInventory inv2 = this.duel2.getInventory();
        inv2.clear();
        inv2.setHelmet(new ItemStack(config.getArmor().helm));
        inv2.setBoots(new ItemStack(config.getArmor().feetsies));
        inv2.setChestplate(new ItemStack(config.getArmor().chest));
        inv2.setLeggings(new ItemStack(config.getArmor().leg));
        inv2.addItem(new ItemStack(config.getSword()));
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
    	this.config = new DuelConfig(this);
        this.getServer().getPluginManager().registerEvents(this, this);
        this.spawn = this.getServer().getWorld("world").getSpawnLocation();
        this.start1 = config.getStart2();
        this.start2 = config.getStart2();
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
=======
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

	private DuelConfig config;

	public boolean hasStarted(){
		return this.started;
	}

	public void newDuel(Player p1, Player p2){
		duelSpawn();
		this.duel1 = p1;
		this.duel2 = p2;

		this.duel1.teleport(this.start1);
		this.duel1.setHealth(this.duel1.getMaxHealth());
		this.duel1.setFoodLevel(7);
		final PlayerInventory inv1 = this.duel1.getInventory();
		inv1.clear();
		inv1.setHelmet(new ItemStack(config.getArmor().helm));
		inv1.setBoots(new ItemStack(config.getArmor().feetsies));
		inv1.setChestplate(new ItemStack(config.getArmor().chest));
		inv1.setLeggings(new ItemStack(config.getArmor().leg));
		inv1.addItem(new ItemStack(config.getSword()));

		this.duel2.teleport(this.start2);
		this.duel2.setHealth(this.duel2.getMaxHealth());
		this.duel2.setFoodLevel(7);
		final PlayerInventory inv2 = this.duel2.getInventory();
		inv2.clear();
		inv2.setHelmet(new ItemStack(config.getArmor().helm));
		inv2.setBoots(new ItemStack(config.getArmor().feetsies));
		inv2.setChestplate(new ItemStack(config.getArmor().chest));
		inv2.setLeggings(new ItemStack(config.getArmor().leg));
		inv2.addItem(new ItemStack(config.getSword()));
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent event){
		final Player player = event.getEntity();
		if(player.equals(this.duel1)){
			getServer().broadcastMessage(ChatColor.AQUA + this.duel2.getName() + " wins!");
			duelSpawn();
		}
		if(player.equals(this.duel2)){
			getServer().broadcastMessage(ChatColor.AQUA + this.duel1.getName() + " wins!");
			duelSpawn();
		}
	}

	@Override
	public void onEnable(){
		config = new DuelConfig(this);
		getServer().getPluginManager().registerEvents(this, this);
		this.spawn = getServer().getWorld("world").getSpawnLocation();
		this.start1 = config.getStart1();
		this.start2 = config.getStart2();
		getCommand("duel").setExecutor(new DuelCommand(this));
		getCommand("next").setExecutor(new NextCommand(this));
		getCommand("join").setExecutor(new JoinCommand(this));
		getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
		getCommand("round1over").setExecutor(new Round1OverCommand(this));
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event){
		final Player player = event.getPlayer();
		if(player.equals(this.duel1)){
			getServer().broadcastMessage(ChatColor.AQUA + this.duel2.getName() + " wins!");
			this.blacklist.add(player.getName());
		}
		if(player.equals(this.duel2)){
			getServer().broadcastMessage(ChatColor.AQUA + this.duel1.getName() + " wins!");
			this.blacklist.add(player.getName());
		}
	}

	public void start(){
		this.started = true;
	}

	private void duelSpawn(){
		if(this.duel1 != null){
			this.duel1.teleport(this.spawn);
			this.duel1.sendMessage(ChatColor.RED + "Good work, sending you to spawn");
		}
		if(this.duel2 != null){
			this.duel2.teleport(this.spawn);
			this.duel2.sendMessage(ChatColor.RED + "Good work, sending you to spawn");
		}
		this.duel1 = null;
		this.duel2 = null;
	}

}
>>>>>>> parent of d241a10... Fix line endings (part 1)
