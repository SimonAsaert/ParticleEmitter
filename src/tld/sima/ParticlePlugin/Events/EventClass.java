package tld.sima.ParticlePlugin.Events;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;
import tld.sima.Conversations.ExtraConversation;
import tld.sima.Conversations.MaterialConversation;
import tld.sima.Conversations.NumberConversation;
import tld.sima.Conversations.ParticleOffsetConversation;
import tld.sima.Conversations.PosConversation;
import tld.sima.ParticlePlugin.Main;
import tld.sima.ParticlePlugin.ParticleEmitter;
import tld.sima.ParticlePlugin.HashMaps.PlayerEntityHM;
import tld.sima.ParticlePlugin.Inventory.CustomInventory;

public class EventClass implements Listener {
	private Plugin plugin = Main.getPlugin(Main.class);


	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Bat) {
			Bat bat = (Bat) event.getEntity();
			String name = bat.getCustomName();

			String delims = "[ ]";
			String[] tokens = name.split(delims);
			
			if (tokens[0].equalsIgnoreCase("particle")) {
				event.setCancelled(true);
			}
		}
	}

	public PlayerEntityHM bmp = new PlayerEntityHM();
	public PlayerEntityHM getBMP() {return bmp;}

	@EventHandler
	public void onEntityInteract(PlayerInteractAtEntityEvent event) {
		Player player = event.getPlayer();
		if (event.getRightClicked() instanceof Bat) {
			Bat bat = (Bat)event.getRightClicked();
			String name = bat.getCustomName();

			String delims = "[ ]";
			String[] tokens = name.split(delims);
			
			if (tokens[0].equalsIgnoreCase("particle")) {
				if (player.hasPermission("ParticlePlugin.interact"))
					event.setCancelled(true);
					bmp.addToMap(player.toString(),bat);
	
					CustomInventory i = new CustomInventory();
					i.mainInventoryMenu(player, bat);
			}
		}
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		String prefixMain = (ChatColor.DARK_BLUE + "Particle GUI");
		String prefixParticle = (ChatColor.DARK_BLUE + "Particle Type GUI");
		Player player = (Player) event.getWhoClicked();

		Bat bat = bmp.getEntity(player.toString());

		Inventory open = event.getClickedInventory();
		ItemStack item = event.getCurrentItem();
		
		
		if(open == null){
			return;
		}
		if(open.getName().equals(prefixMain)){
			event.setCancelled(true);
			if(item.equals(null)){
				player.sendMessage("You have clicked something null!");
				return;
			}else if ( !item.hasItemMeta()){
				player.sendMessage("You have clicked something without meta data!");
				return;
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Delete particle emitter")){
				bat.remove();
				player.sendMessage(ChatColor.GOLD + "Particle Emitter removed.");
				player.closeInventory();
				return;
			}else if(item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Entity type")) {
				CustomInventory i = new CustomInventory();
				player.closeInventory();
				i.particleTypeMenu(player, bat);
			}else if(item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Entity delay")) {
				player.closeInventory();
				ConversationFactory cf = new ConversationFactory(plugin);
				NumberConversation numconv = new NumberConversation();
				numconv.inputData(player, bat);
				Conversation conv = cf.withFirstPrompt(numconv).withLocalEcho(true).buildConversation(player);
				conv.begin();
			}else if(item.getItemMeta().getDisplayName().equals( ChatColor.WHITE + "Position")) {
				player.closeInventory();
				ConversationFactory cf = new ConversationFactory(plugin);
				PosConversation posconv = new PosConversation();
				posconv.inputData(player, bat);
				Conversation conv = cf.withFirstPrompt(posconv).withLocalEcho(true).buildConversation(player);
				conv.begin();
			}else if(item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Offset")) {
				player.closeInventory();
				ConversationFactory cf = new ConversationFactory(plugin);
				ParticleOffsetConversation poffconv = new ParticleOffsetConversation();
				poffconv.inputData(player, bat);
				Conversation conv = cf.withFirstPrompt(poffconv).withLocalEcho(true).buildConversation(player);
				conv.begin();
			}else if(item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Extra data")) {
				player.closeInventory();
				ConversationFactory cf = new ConversationFactory(plugin);
				ExtraConversation extconv = new ExtraConversation();
				extconv.inputData(player, bat);
				Conversation conv = cf.withFirstPrompt(extconv).withLocalEcho(true).buildConversation(player);
				conv.begin();
			}else if(item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Material Data")) {
				player.closeInventory();
				ConversationFactory cf = new ConversationFactory(plugin);
				MaterialConversation matconv = new MaterialConversation();
				matconv.inputData(player, bat);
				Conversation conv = cf.withFirstPrompt(matconv).withLocalEcho(true).buildConversation(player);
				conv.begin();
			}
			else {
				return;
			}
		}else if(open.getName().equals(prefixParticle)) {
			event.setCancelled(true);
			String name = bat.getCustomName();

			String delims = "[ ]";
			String[] tokens = name.split(delims);
			
			if(item.equals(null)){
				player.sendMessage("Clicked something null!");
				return;
			}else if ( !item.hasItemMeta()){
				player.sendMessage("Clicked something without meta!");
				return;
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Block Crack particle")){
				tokens[1] = "BLOCK_CRACK";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Cloud particle")){
				tokens[1] = "CLOUD";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Crit particle")){
				tokens[1] = "CRIT";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Crit Magic particle")){
				tokens[1] = "CRIT_MAGIC";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Damage Indicator particle")){
				tokens[1] = "DAMAGE_INDICATOR";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Dragon Breath particle")){
				tokens[1] = "DRAGON_BREATH";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Lava Drip particle")){
				tokens[1] = "DRIP_LAVA";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Water Drip particle")){
				tokens[1] = "DRIP_WATER";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Enchantment Table particle")){
				tokens[1] = "ENCHANTMENT_TABLE";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "EndRod particle")){
				tokens[1] = "END_ROD";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Huge Explosion particle")){
				tokens[1] = "EXPLOSION_HUGE";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Large Explosion particle")){
				tokens[1] = "EXPLOSION_LARGE";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Normal Explosion particle")){
				tokens[1] = "EXPLOSION_NORMAL";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Falling Dust particle")){
				tokens[1] = "FALLING_DUST";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Firework Spark particle")){
				tokens[1] = "FIREWORKS_SPARK";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Flame particle")){
				tokens[1] = "FLAME";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Footstep particle")){
				tokens[1] = "FOOTSTEP";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Heart particle")){
				tokens[1] = "HEART";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Item Crack particle")){
				tokens[1] = "ITEM_CRACK";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Item Take particle")){
				tokens[1] = "ITEM_TAKE";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Lava particle")){
				tokens[1] = "LAVA";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Mob Appearance particle")){
				tokens[1] = "MOB_APPEARANCE";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Note particle")){
				tokens[1] = "NOTE";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Portal particle")){
				tokens[1] = "PORTAL";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Redstone particle")){
				tokens[1] = "REDSTONE";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Slime particle")){
				tokens[1] = "SLIME";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Large Smoke particle")){
				tokens[1] = "SMOKE_LARGE";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Normal Smoke particle")){
				tokens[1] = "SMOKE_NORMAL";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Snow Shovel particle")){
				tokens[1] = "SNOW_SHOVEL";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Snowball particle")){
				tokens[1] = "SNOWBALL";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Spell particle")){
				tokens[1] = "SPELL";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Instant Spell particle")){
				tokens[1] = "SPELL_INSTANT";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Mob Spell particle")){
				tokens[1] = "SPELL_MOB";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Mob Ambient Spell particle")){
				tokens[1] = "SPELL_MOB_AMBIENT";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Witch Spell particle")){
				tokens[1] = "SPELL_WITCH";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Spit particle")){
				tokens[1] = "SPIT";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Suspended particle")){
				tokens[1] = "SUSPENDED";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Suspended Depth particle")){
				tokens[1] = "SUSPENDED_DEPTH";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Sweep Attack particle")){
				tokens[1] = "SWEEP_ATTACK";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Totem particle")){
				tokens[1] = "TOTEM";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Town Aura particle")){
				tokens[1] = "TOWN_AURA";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Angry Villager particle")){
				tokens[1] = "VILLAGER_ANGRY";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Happy Villager particle")){
				tokens[1] = "VILLAGER_HAPPY";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Water Bubble particle")){
				tokens[1] = "WATER_BUBBLE";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Water Splash particle")){
				tokens[1] = "WATER_DROP";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Water Drop particle")){
				tokens[1] = "WATER_SPLASH";
			}else if (item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Water Wake particle")){
				tokens[1] = "WATER_WAKE";
			}
			else {
				player.sendMessage("Somehow you clicked something else... How?");
				return;
			}
			String batName = tokens[0];
			for (int j = 1; j < tokens.length; j++) {
				batName = batName + " " + tokens[j];
			}
			
			bat.setCustomName(batName);
			CustomInventory i = new CustomInventory();
			player.closeInventory();
			i.mainInventoryMenu(player, bat);
		}
	}
	
	HashMap<String, Boolean> delay = new HashMap<String, Boolean>();
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player player = event.getPlayer();
			if (player.hasPermission("ParticlePlugin.particle"));
				if((!delay.containsKey(player.toString()))){
					ItemStack tool = new ItemStack(Material.STICK);
					ItemMeta toolM = tool.getItemMeta();
					toolM.setDisplayName(ChatColor.GREEN + "Particle Summoner");
					
					ArrayList<String> toolL = new ArrayList<String>();
					toolL.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "Summons Emitter");
					
					toolM.setLore(toolL);
					toolM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					
					tool.setItemMeta(toolM);
					
					if(player.getInventory().getItemInMainHand().isSimilar(tool)) {
						delay.put(player.toString(), true);
						event.setCancelled(true);
						
						Location loc = event.getClickedBlock().getLocation();
						loc.add(0.5,1,0.5);
						
						ParticleEmitter particle = new ParticleEmitter();
						particle.createEntity(player, loc);
						
						
						new BukkitRunnable() {
							@Override
							public void run() {
								delay.remove(player.toString());
							}
						}.runTaskLater(plugin, 5);
					}
				}
		}
	}
	
}
