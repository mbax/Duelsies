package org.kitteh.duelsies;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class DuelConfig {

	public enum ArmorType{
		NONE(Material.AIR, Material.AIR, Material.AIR, Material.AIR),
		LEATHER(Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS),
		IRON(Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS),
		GOLD(Material.GOLD_HELMET, Material.GOLD_CHESTPLATE, Material.GOLD_LEGGINGS, Material.GOLD_BOOTS),
		DIAMOND(Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS);

		public final Material helm, chest, leg, feetsies;

		private ArmorType(Material helm, Material chest, Material leg, Material feet){
			this.helm = helm;
			this.chest = chest;
			this.leg = leg;
			this.feetsies = feet;
		}

		public static ArmorType getTypeFromConfig(String value){
			if(value.equalsIgnoreCase("iron")){
				return IRON;
			}else if(value.equalsIgnoreCase("leather") || value.equalsIgnoreCase("cow")){
				return LEATHER;
			}else if(value.equalsIgnoreCase("gold")){
				return GOLD;
			}else if(value.equalsIgnoreCase("diamond")){
				return DIAMOND;
			}else if(value.equalsIgnoreCase("none")){
				return NONE;
			}else{
				return null;
			}
		}

	}

	private ArmorType type;
	private Material sword;
	private Duelsies plugin;
	private Location s1, s2;

	public DuelConfig(Duelsies plugin){
		this.plugin = plugin;
		reload();
	}

	public void reload(){
		plugin.getConfig().options().copyDefaults(true);
		type = ArmorType.getTypeFromConfig(plugin.getConfig().getString("armor"));
		if(type == null){
			type = ArmorType.IRON;
			plugin.getLogger().warning("Armor type unsupported. Please choose IRON, GOLD, DIAMOND, LEATHER, or NONE");
			plugin.getLogger().warning("Assuming IRON");
		}
		sword = Material.matchMaterial(plugin.getConfig().getString("sword"));
		if(sword == null){
			sword = Material.STONE_SWORD;
			plugin.getLogger().warning("Sword type unsupported.");
			plugin.getLogger().warning("Assuming STONE SWORD");
		}
		World world = Bukkit.getWorld(plugin.getConfig().getString("start.world"));
		if(world == null){
			world = Bukkit.getWorlds().get(0);
			plugin.getLogger().warning("World not found, assuming '" + world.getName() + "'");
		}
		double x1, x2, y1, y2, z1, z2;
		x1 = plugin.getConfig().getDouble("start.player1.x", 0);
		y1 = plugin.getConfig().getDouble("start.player1.y", 0);
		z1 = plugin.getConfig().getDouble("start.player1.z", 0);
		x2 = plugin.getConfig().getDouble("start.player2.x", 0);
		y2 = plugin.getConfig().getDouble("start.player2.y", 0);
		z2 = plugin.getConfig().getDouble("start.player2.z", 0);
		s1 = new Location(world, x1, y1, z1);
		s2 = new Location(world, x2, y2, z2);
	}

	public ArmorType getArmor(){
		return type;
	}

	public Material getSword(){
		return sword;
	}

	public Location getStart1(){
		return s1;
	}

	public Location getStart2(){
		return s2;
	}

}
