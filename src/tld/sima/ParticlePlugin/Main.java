package tld.sima.ParticlePlugin;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.intellectualcrafters.plot.api.PlotAPI;

import tld.sima.Command.Commands;
import tld.sima.ParticlePlugin.Events.EventClass;

public class Main extends JavaPlugin{

	private Commands commands = new Commands();
	public static PlotAPI api;
	public static boolean PlotFlag;
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		getCommand(commands.cmd1).setExecutor(commands);

		if ((getServer().getPluginManager().getPlugin("PlotSquared") != null) && !(getServer().getPluginManager().getPlugin("PlotSquared").isEnabled()) ) {
			getServer().getConsoleSender().sendMessage(ChatColor.RED + "PlotSquared doesn't appear to be running.");
			PlotFlag = false;
		}else {
			PlotFlag = true;
			api = new PlotAPI();
		}
		
		getServer().getPluginManager().registerEvents(new EventClass(), this);

		getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "Particle Plugin has been enabled");
		
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(this,
		new BukkitRunnable() {
			@Override
			public void run() {
				for (World world : Bukkit.getServer().getWorlds()) {
					Long time = world.getFullTime();
					try {
						for (Entity entity : world.getEntities()) {
							if(entity instanceof Bat) {
								Bat bat = (Bat)entity;
								if(bat.getCustomName() != null) {
									String name = bat.getCustomName();

									String delims = "[ ]";
									String[] tokens = name.split(delims);

									if (tokens[0].equalsIgnoreCase("particle")) {
										Location loc = bat.getLocation();
										int delay = 20;
										try {
											Integer.parseInt(tokens[2]);
										}catch(NumberFormatException e){
											continue;
										}
										delay = Integer.parseInt(tokens[2]);

										if (time % delay == 0) {
											if (tokens.length == 8) {
												if(Double.parseDouble(tokens[6]) > 10) {
													tokens[6] = "10";
													String batName = tokens[0];
													for(int j=1; j < tokens.length ; j++) {
														batName = batName + " " + tokens[j];
													}
													bat.setCustomName(batName);
												}else if (Double.parseDouble(tokens[6]) < 0) {
													tokens[6] = "0";
													String batName = tokens[0];

													for(int j=1; j < tokens.length ; j++) {
														batName = batName + " " + tokens[j];
													}
													bat.setCustomName(batName);
												}
												if ((tokens[1].equalsIgnoreCase("BLOCK_CRACK"))){
													Particle particle = getParticle(tokens[1]);
													delims = "[:]";
													String[] materialData = tokens[7].split(delims);
													int ID = 1;
													byte META = 0;
													try {
														ID = Integer.parseInt(materialData[0]);
													}catch(NumberFormatException e) {
														ID = 1;
													}
													if (materialData.length == 2) {
														try {
															META = Byte.parseByte(materialData[1]);
														}catch(NumberFormatException e) {
															META = 0;
														}
													}
													MaterialData material = new MaterialData(ID, META);
													world.spawnParticle(particle, loc, 1, Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]), Double.parseDouble(tokens[5]), Double.parseDouble(tokens[6]), material);
												}else if((tokens[1].equals("ITEM_CRACK"))){
													Particle particle = getParticle(tokens[1]);
													delims = "[:]";
													String[] materialData = tokens[7].split(delims);
													int ID = 1;
													byte META = 0;
													try {
														ID = Integer.parseInt(materialData[0]);
													}catch(NumberFormatException e) {
														ID = 1;
													}
													if (materialData.length == 2) {
														try {
															META = Byte.parseByte(materialData[1]);
														}catch(NumberFormatException e) {
															META = 0;
														}
													}
													
													ItemStack item = new ItemStack(ID, 1, (short) 0, META);
													world.spawnParticle(particle, loc, 1, Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]), Double.parseDouble(tokens[5]), Double.parseDouble(tokens[6]), item);
													
												}else{
													world.spawnParticle(getParticle(tokens[1]), loc, 1, Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]), Double.parseDouble(tokens[5]), Double.parseDouble(tokens[6]));
												}
											}else if (tokens.length > 5) {
												ArrayList<String> newName = new ArrayList<String>();
												newName.add(tokens[0]);
												newName.add(tokens[1]);
												newName.add(tokens[2]);
												newName.add(tokens[3]);
												newName.add(tokens[4]);
												newName.add(tokens[5]);
												newName.add(tokens[6]);
												newName.add("1");
												String batName = newName.get(0);
												for (int j = 1; j < newName.size(); j++) {
													batName = batName + " " + newName.get(j);
												}
												bat.setCustomName(batName);
												world.spawnParticle(getParticle(tokens[1]), loc, 1, Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]), Double.parseDouble(tokens[5]), Double.parseDouble(tokens[6]));
											}else {
												ArrayList<String> newName = new ArrayList<String>();
												newName.add(tokens[0]);
												newName.add(tokens[1]);
												newName.add(tokens[2]);
												newName.add("0");
												newName.add("0");
												newName.add("0");
												newName.add("0");
												newName.add("1");
												String batName = newName.get(0);
												for (int j = 1; j < newName.size(); j++) {
													batName = batName + " " + newName.get(j);
												}
												bat.setCustomName(batName);
												world.spawnParticle(getParticle(tokens[1]), loc, 1);
											}
										}
									}
								}
							}
						}
					}catch(Exception e) {
						// DoNothing
					}
				}
			}
		}, 20, 1);
	}

	@Override
	public void onDisable() {
		
	}
	
	private Particle getParticle(String name) {
		
		Particle particle = Particle.BARRIER;
		
		switch(name) {
			case "BARRIER": particle = Particle.BARRIER;
							break;
			case "BLOCK_CRACK": particle = Particle.BLOCK_CRACK;
								break;
			case "BLOCK_DUST":  particle = Particle.BLOCK_DUST;
								break;
			case "CLOUD":	particle = Particle.CLOUD;
							break;
			case "CRIT":	particle = Particle.CRIT;
							break;
			case "CRIT_MAGIC":	particle = Particle.CRIT_MAGIC;
								break;
			case "DAMAGE_INDICATOR":	particle = Particle.DAMAGE_INDICATOR;
										break;
			case "DRAGON_BREATH":	particle = Particle.DRAGON_BREATH;
									break;
			case "DRIP_LAVA":	particle = Particle.DRIP_LAVA;
								break;
			case "DRIP_WATER":	particle = Particle.DRIP_WATER;
								break;
			case "ENCHANTMENT_TABLE":	particle = Particle.ENCHANTMENT_TABLE;
										break;
			case "END_ROD":	particle = Particle.END_ROD;
							break;
			case "EXPLOSION_HUGE":	particle = Particle.EXPLOSION_HUGE;
									break;
			case "EXPLOSION_LARGE":	particle = Particle.EXPLOSION_LARGE;
									break;
			case "EXPLOSION_NORMAL":particle = Particle.EXPLOSION_NORMAL;
									break;
			case "FALLING_DUST":	particle = Particle.FALLING_DUST;
									break;
			case "FIREWORKS_SPARK": particle = Particle.FIREWORKS_SPARK;
									break;
			case "FLAME":	particle = Particle.FLAME;
							break;
			case "FOOTSTEP":particle = Particle.FOOTSTEP;
							break;
			case "HEART":	particle = Particle.HEART;
							break;
			case "ITEM_CRACK":	particle = Particle.ITEM_CRACK;
								break;
			case "ITEM_TAKE":	particle = Particle.ITEM_TAKE;
								break;
			case "LAVA":	particle = Particle.LAVA;
							break;
			case "MOB_APPEARANCE":	particle = Particle.MOB_APPEARANCE;
									break;
			case "NOTE":	particle = Particle.NOTE;
							break;
			case "PORTAL":	particle = Particle.PORTAL;
							break;
			case "REDSTONE":particle = Particle.REDSTONE;
							break;
			case "SLIME":	particle = Particle.SLIME;
							break;
			case "SMOKE_LARGE":	particle = Particle.SMOKE_LARGE;
								break;
			case "SMOKE_NORMAL":particle = Particle.SMOKE_NORMAL;
								break;
			case "SNOW_SHOVEL":	particle = Particle.SNOW_SHOVEL;
								break;
			case "SNOWBALL":	particle = Particle.SNOWBALL;
								break;
			case "SPELL":	particle = Particle.SPELL;
							break;
			case "SPELL_INSTANT":	particle = Particle.SPELL_INSTANT;
									break;
			case "SPELL_MOB":	particle = Particle.SPELL_MOB;
								break;
			case "SPELL_MOB_AMBIENT":	particle = Particle.SPELL_MOB_AMBIENT;
										break;
			case "SPELL_WITCH":	particle = Particle.SPELL_WITCH;
								break;
			case "SPIT":	particle = Particle.SPIT;
							break;
			case "SUSPENDED":	particle = Particle.SUSPENDED;
								break;
			case "SUSPENDED_DEPTH":	particle = Particle.SUSPENDED_DEPTH;
									break;
			case "SWEEP_ATTACK":	particle = Particle.SWEEP_ATTACK;
									break;
			case "TOTEM":	particle = Particle.TOTEM;
							break;
			case "TOWN_AURA":	particle = Particle.TOWN_AURA;
								break;
			case "VILLAGER_ANGRY":	particle = Particle.VILLAGER_ANGRY;
									break;
			case "VILLAGER_HAPPY":	particle = Particle.VILLAGER_HAPPY;
									break;
			case "WATER_BUBBLE":	particle = Particle.WATER_BUBBLE;
									break;
			case "WATER_DROP":	particle = Particle.WATER_DROP;
								break;
			case "WATER_SPLASH":particle = Particle.WATER_SPLASH;
								break;
			case "WATER_WAKE":	particle = Particle.WATER_WAKE;
								break;
			default:	particle = Particle.NOTE;
						break;
		}
		return particle;
	}
	
	
}
