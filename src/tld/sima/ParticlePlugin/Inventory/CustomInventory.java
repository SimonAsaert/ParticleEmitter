package tld.sima.ParticlePlugin.Inventory;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import tld.sima.ParticlePlugin.Main;

public class CustomInventory implements Listener {

	private Plugin plugin = Main.getPlugin(Main.class);

	private ItemStack createItem(ItemStack item, String disName, String loreName) {

		ItemMeta itemM = item.getItemMeta();
		itemM.setDisplayName(disName);
		ArrayList<String> itemL = new ArrayList<String>();
		itemL.add(loreName);
		itemM.setLore(itemL);
		itemM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		itemM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		itemM.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		item.setItemMeta(itemM);

		return item;
	}

	public void mainInventoryMenu(Player player, Bat bat){
		Inventory i = plugin.getServer().createInventory(null, 27, ChatColor.DARK_BLUE + "Particle GUI");

		ItemStack delete = new ItemStack(Material.SKULL_ITEM, 1, (short) 0);
		delete = createItem(delete, ChatColor.WHITE + "Delete particle emitter", ChatColor.GRAY + "" + ChatColor.ITALIC + "Deletes particle emitter");
		
		ItemStack type = new ItemStack(Material.EMERALD, 1);
		type = createItem(type, ChatColor.WHITE + "Entity type", ChatColor.GRAY + "" + ChatColor.ITALIC + "Change what kind of particle the emitter emits");
		
		ItemStack number = new ItemStack(Material.DIAMOND, 1);
		number = createItem(number, ChatColor.WHITE + "Entity delay", ChatColor.GRAY + "" + ChatColor.ITALIC + "Change delay between emission of each particle");

		ItemStack position = new ItemStack(Material.STONE, 1);
		position = createItem(position, ChatColor.WHITE + "Position", ChatColor.GRAY + "" + ChatColor.ITALIC + "Current Position: " + bat.getLocation().getX() + bat.getLocation().getY() + bat.getLocation().getZ());
		
		ItemStack offset = new ItemStack(Material.ARROW, 1);
		String name = bat.getCustomName();
		String delims = "[ ]";
		String[] tokens = name.split(delims);
		if (tokens.length >= 5) {
			offset = createItem(offset, ChatColor.WHITE + "Offset", ChatColor.GRAY + "" + ChatColor.ITALIC + "Sets max offset for particles. Current offset: " + tokens[3] + " " + tokens[4] + " " + tokens[5]);
		}else {
			offset = createItem(offset, ChatColor.WHITE + "Offset", ChatColor.GRAY + "" + ChatColor.ITALIC + "Sets max offset for particles.");
		}
		ItemStack extra = new ItemStack(Material.EMERALD, 1);
		if (tokens.length >= 6) {
			extra = createItem(extra, ChatColor.WHITE + "Extra data", ChatColor.GRAY + "" + ChatColor.ITALIC + "Sets extra data, usually reserved for speed. Current speed: " + tokens[6]);
		}else {
			extra = createItem(extra, ChatColor.WHITE + "Extra data", ChatColor.GRAY + "" + ChatColor.ITALIC + "Sets extra data, usually reserved for speed.");
		}
		
		
		i.setItem(26, delete);
		i.setItem(10, type);
		i.setItem(11, number);
		i.setItem(12, offset);
		i.setItem(13, extra);
		i.setItem(16, position);
		
		player.openInventory(i);

	}
	
	public void particleTypeMenu(Player player, Bat bat) {
		Inventory i = plugin.getServer().createInventory(null, 54, ChatColor.DARK_BLUE + "Particle Type GUI");
		
		ItemStack barrier = new ItemStack(Material.BARRIER, 1);
		barrier = createItem(barrier, ChatColor.WHITE + "Barrier particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Barrier particle type");
		
		ItemStack blockcrack = new ItemStack(Material.STONE, 1);
		blockcrack = createItem(blockcrack, ChatColor.WHITE + "Block Crack particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Block Crack particle type");
		
		ItemStack blockdust = new ItemStack(Material.STONE, 1);
		blockdust = createItem(blockdust,ChatColor.WHITE + "Block Dust particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Block Dust particle type");
		
		ItemStack cloud = new ItemStack(Material.STAINED_GLASS, 1);
		cloud = createItem(cloud, ChatColor.WHITE + "Cloud particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Cloud particle type");
		
		ItemStack crit = new ItemStack(Material.CYAN_GLAZED_TERRACOTTA, 1);
		crit = createItem(crit, ChatColor.WHITE + "Crit particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Crit particle type");
		
		ItemStack critmagic = new ItemStack(Material.ORANGE_GLAZED_TERRACOTTA, 1);
		critmagic = createItem(critmagic, ChatColor.WHITE + "Crit Magic particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Crit Magic particle type");
		
		ItemStack damageindicator = new ItemStack(Material.RED_GLAZED_TERRACOTTA, 1);
		damageindicator = createItem(damageindicator, ChatColor.WHITE + "Damage Indicator particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Damage Indicator particle type");
		
		ItemStack dragonbreath = new ItemStack(Material.PURPLE_GLAZED_TERRACOTTA, 1);
		dragonbreath = createItem(dragonbreath, ChatColor.WHITE + "Dragon Breath particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Dragon Breath particle type");
		
		ItemStack driplava = new ItemStack(Material.LAVA_BUCKET, 1);
		driplava = createItem(driplava, ChatColor.WHITE + "Lava Drip particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Lava Drip particle type");
		
		ItemStack dripwater = new ItemStack(Material.WATER_BUCKET, 1);
		dripwater = createItem(dripwater, ChatColor.WHITE + "Water Drip particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Water Drip particle type");
		
		ItemStack enchantmenttable = new ItemStack(Material.ENCHANTMENT_TABLE, 1);
		enchantmenttable = createItem(enchantmenttable, ChatColor.WHITE + "Enchantment Table particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Enchantment Table particle type");
		
		ItemStack endrod = new ItemStack(Material.END_ROD, 1);
		endrod = createItem(endrod, ChatColor.WHITE + "EndRod particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "EndRod Particle type");
		
		ItemStack explosionhuge = new ItemStack(Material.TNT, 1);
		explosionhuge = createItem(explosionhuge, ChatColor.WHITE + "Huge Explosion particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Huge Explosion particle type");
		
		ItemStack explosionlarge = new ItemStack(Material.TNT, 1);
		explosionlarge = createItem(explosionlarge, ChatColor.WHITE + "Large Explosion particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Large Explosion particle type");
		
		ItemStack explosionnormal = new ItemStack(Material.TNT, 1);
		explosionnormal = createItem(explosionnormal, ChatColor.WHITE + "Normal Explosion particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Normal Explosion particle type");
		
		ItemStack fallingdust = new ItemStack(Material.FEATHER, 1);
		fallingdust = createItem(fallingdust, ChatColor.WHITE + "Falling Dust particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Falling Dust particle type");
		
		ItemStack fireworkspark = new ItemStack(Material.FIREWORK, 1);
		fireworkspark = createItem(fireworkspark, ChatColor.WHITE + "Firework Spark particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Firework Spark particle type");
		
		ItemStack flame = new ItemStack(Material.FLINT_AND_STEEL, 1);
		flame = createItem(flame, ChatColor.WHITE + "Flame particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Flame particle type");
		
		ItemStack footstep = new ItemStack(Material.ARMOR_STAND, 1);
		footstep = createItem(footstep, ChatColor.WHITE + "Footstep particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Footstep particle type");
		
		ItemStack heart = new ItemStack(Material.PINK_GLAZED_TERRACOTTA, 1);
		heart = createItem(heart, ChatColor.WHITE + "Heart particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Heart particle type");
		
		ItemStack itemcrack = new ItemStack(Material.STONE, 1);
		itemcrack = createItem(itemcrack, ChatColor.WHITE + "Item Crack particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Item Crack particle type");
		
		ItemStack itemtake = new ItemStack(Material.CHEST, 1);
		itemtake = createItem(itemtake, ChatColor.WHITE + "Item Take particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Item Tack particle type");
		
		ItemStack lava = new ItemStack(Material.LAVA_BUCKET, 1);
		lava = createItem(lava, ChatColor.WHITE + "Lava particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Lava particle type");
		
		ItemStack mobappearance = new ItemStack(Material.SKULL_ITEM, 1);
		mobappearance = createItem(mobappearance, ChatColor.WHITE + "Mob Appearance particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Mob Appearance particle type");
		
		ItemStack note = new ItemStack(Material.JUKEBOX, 1);
		note = createItem(note, ChatColor.WHITE + "Note particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Note particle type");	
		
		ItemStack portal = new ItemStack(Material.OBSIDIAN, 1);
		portal = createItem(portal, ChatColor.WHITE + "Portal particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Portal particle type");
		
		ItemStack redstone = new ItemStack(Material.REDSTONE, 1);
		redstone = createItem(redstone, ChatColor.WHITE + "Redstone particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Redstone particle type");
		
		ItemStack slime = new ItemStack(Material.SLIME_BALL, 1);
		slime = createItem(slime, ChatColor.WHITE + "Slime particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Slime particle type");
		
		ItemStack smokelarge = new ItemStack(Material.WEB, 1);
		smokelarge = createItem(smokelarge, ChatColor.WHITE + "Large Smoke particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Large Smoke particle type");
		
		ItemStack smokenormal = new ItemStack(Material.WEB, 1);
		smokenormal = createItem(smokenormal, ChatColor.WHITE + "Normal Smoke particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Normal Smoke particle type");
		
		ItemStack snowshovel = new ItemStack(Material.SNOW, 1);
		snowshovel = createItem(snowshovel, ChatColor.WHITE + "Snow Shovel particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Snow Shovel particle type");
		
		ItemStack snowball = new ItemStack(Material.SNOW_BALL, 1);
		snowball = createItem(snowball, ChatColor.WHITE + "Snowball particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Snowball particle type");
		
		ItemStack spell = new ItemStack(Material.ENCHANTED_BOOK, 1);
		spell = createItem(spell, ChatColor.WHITE + "Spell particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Spell particle type");
		
		ItemStack spellinstant = new ItemStack(Material.ENCHANTED_BOOK, 1);
		spellinstant = createItem(spellinstant, ChatColor.WHITE + "Instant Spell particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Instant Spell particle type");
		
		ItemStack spellmob = new ItemStack(Material.SKULL_ITEM, 1, (byte) 2);
		spellmob = createItem(spellmob, ChatColor.WHITE + "Mob Spell particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Mob Spell particle type");
		
		ItemStack spellmobambient = new ItemStack(Material.SKULL_ITEM, 1, (byte) 2);
		spellmobambient = createItem(spellmobambient, ChatColor.WHITE + "Mob Ambient Spell particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Mob Ambient Spell particle type");
		
		ItemStack spellwitch = new ItemStack(Material.SKULL_ITEM, 1, (byte) 1);
		spellwitch = createItem(spellwitch, ChatColor.WHITE + "Witch Spell particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Witch Spell particle type");
		
		ItemStack spit = new ItemStack(Material.WATER_BUCKET, 1);
		spit = createItem(spit, ChatColor.WHITE + "Spit particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Spit particle type");
		
		ItemStack suspended = new ItemStack(Material.RAILS, 1);
		suspended = createItem(suspended, ChatColor.WHITE + "Suspended particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Suspended particle type");
		
		ItemStack suspendeddepth = new ItemStack(Material.ACTIVATOR_RAIL, 1);
		suspendeddepth = createItem(suspendeddepth, ChatColor.WHITE + "Suspended Depth particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Suspended Depth particle type");
		
		ItemStack sweepattack = new ItemStack(Material.IRON_SWORD, 1);
		sweepattack = createItem(sweepattack, ChatColor.WHITE + "Sweep Attack particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Sweep Attack particle type");
		
		ItemStack totem = new ItemStack(Material.TOTEM, 1);
		totem = createItem(totem, ChatColor.WHITE + "Totem particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Totem particle type");
		
		ItemStack townaura = new ItemStack(Material.PUMPKIN, 1);
		townaura = createItem(townaura, ChatColor.WHITE + "Town Aura particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Town Aura particle type");
		
		ItemStack villagerangry = new ItemStack(Material.REDSTONE_BLOCK, 1);
		villagerangry = createItem(villagerangry, ChatColor.WHITE + "Angry Villager particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Angry Villager particle type");
		
		ItemStack villagerhappy = new ItemStack(Material.EMERALD_BLOCK, 1);
		villagerhappy = createItem(villagerhappy, ChatColor.WHITE + "Happy Villager particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Happy Villager particle type");

		ItemStack waterbubble = new ItemStack(Material.WATER_BUCKET, 1);
		waterbubble = createItem(waterbubble, ChatColor.WHITE + "Water Bubble particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Water Bubble particle type");

		ItemStack watersplash = new ItemStack(Material.WATER_BUCKET, 1);
		watersplash = createItem(watersplash, ChatColor.WHITE + "Water Splash particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Water Splash particle type");

		ItemStack waterdrop = new ItemStack(Material.WATER_BUCKET, 1);
		waterdrop = createItem(waterdrop, ChatColor.WHITE + "Water Drop particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Water Drop particle type");

		ItemStack waterwake = new ItemStack(Material.WATER_BUCKET, 1);
		waterwake = createItem(waterwake, ChatColor.WHITE + "Water Wake particle", ChatColor.GRAY + "" + ChatColor.ITALIC + "Water Wake particle type");
		
		i.setItem(0, blockcrack);
		i.setItem(1, cloud);
		i.setItem(2, crit);
		i.setItem(3, critmagic);
		i.setItem(4, damageindicator);
		i.setItem(5, dragonbreath);
		i.setItem(6, driplava);
		i.setItem(7, dripwater);
		i.setItem(8, enchantmenttable);
		i.setItem(9, endrod);
		i.setItem(10, explosionhuge);
		i.setItem(11, explosionlarge);
		i.setItem(12, explosionnormal);
		i.setItem(13, fallingdust);
		i.setItem(14, fireworkspark);
		i.setItem(15, flame);
		i.setItem(16, footstep);
		i.setItem(17, heart);
		i.setItem(18, itemcrack);
		i.setItem(19, itemtake);
		i.setItem(20, lava);
		i.setItem(21, mobappearance);
		i.setItem(22, note);
		i.setItem(23, portal);
		i.setItem(24, redstone);
		i.setItem(25, slime);
		i.setItem(26, smokelarge);
		i.setItem(27, smokenormal);
		i.setItem(28, snowshovel);
		i.setItem(29, snowball);
		i.setItem(30, spell);
		i.setItem(31, spellinstant);
		i.setItem(32, spellmob);
		i.setItem(33, spellmobambient);
		i.setItem(34, spellwitch);
		i.setItem(35, spit);
		i.setItem(36, suspended);
		i.setItem(37, suspendeddepth);
		i.setItem(38, sweepattack);
		i.setItem(39, totem);
		i.setItem(40, townaura);
		i.setItem(41, villagerangry);
		i.setItem(42, villagerhappy);
		i.setItem(43, waterbubble);
		i.setItem(44, waterdrop);
		i.setItem(45, watersplash);
		i.setItem(46, waterwake);
		
		player.openInventory(i);
	}
}
