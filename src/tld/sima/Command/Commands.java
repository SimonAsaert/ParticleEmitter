package tld.sima.Command;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class Commands implements Listener,CommandExecutor  {
	
	public String cmd1 = "particle";
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase(cmd1)) {
			if(sender instanceof Player) {
				ItemStack tool = new ItemStack(Material.STICK);
				ItemMeta toolM = tool.getItemMeta();
				toolM.setDisplayName(ChatColor.GREEN + "Particle Summoner");
				
				ArrayList<String> toolL = new ArrayList<String>();
				toolL.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "Summons Emitter");
				
				toolM.setLore(toolL);
				toolM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				
				tool.setItemMeta(toolM);
				
				Player player = (Player)sender;
				
				if (player.getInventory().contains(tool)){
				}else{
					player.getInventory().addItem(tool);
				}
				return true;
			}else {
				sender.sendMessage(ChatColor.RED + "You have got to be a player to use this command.");
				return true;
			}
		}
		
		return false;
	}
	
}
