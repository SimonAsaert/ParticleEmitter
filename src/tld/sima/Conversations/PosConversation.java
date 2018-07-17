package tld.sima.Conversations;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Player;

import tld.sima.ParticlePlugin.Inventory.CustomInventory;

public class PosConversation extends StringPrompt {

	private Bat bat;
	private Player player;
	
	@Override
	public Prompt acceptInput(ConversationContext con, String message) {
		
		String delims = "[ ]";
		String[] tokens = message.split(delims);
		boolean flag = true;
		if (tokens.length == 3){
			for (int j = 0; j < tokens.length; j++){
				try{
					Double.parseDouble(tokens[j]);
				}catch(NumberFormatException e){
					flag = false;
					break;
				}
			}
		} else {
			flag = false;
		}
		
		if (flag == true) {
			String string = (ChatColor.GOLD + "Formatted correctly.");
			con.getForWhom().sendRawMessage(string);

			Location loc = bat.getLocation().clone();

			loc.setX(Double.parseDouble(tokens[0]) + bat.getLocation().getX());
			loc.setY(Double.parseDouble(tokens[1]) + bat.getLocation().getY());
			loc.setZ(Double.parseDouble(tokens[2]) + bat.getLocation().getZ());

			bat.teleport(loc);

			CustomInventory i = new CustomInventory();

			i.mainInventoryMenu(player, bat);

		}else {
			String string = (ChatColor.GOLD + "Formatted incorrectly.");
			con.getForWhom().sendRawMessage(string);
		}
		
		
		return null;
	}

	public void inputData(Player playerIn, Bat batIn) {
		player = playerIn;
		bat = batIn;
	}
	
	@Override
	public String getPromptText(ConversationContext arg0) {
		
		String string = (ChatColor.GOLD + "Input position using following format: " + ChatColor.WHITE + "X Y Z" + ChatColor.GOLD + "\nFor Example: " + ChatColor.WHITE + "0 1 -0.5");
		
		return string;
	}
}
