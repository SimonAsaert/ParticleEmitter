package tld.sima.Conversations;

import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Player;

import tld.sima.ParticlePlugin.Inventory.CustomInventory;

public class ExtraConversation extends StringPrompt {

	private Player player;
	private Bat bat;
	
	@Override
	public Prompt acceptInput(ConversationContext con, String message) {
		boolean flag = true;
		
		try {
			Double.parseDouble(message);
		}catch(NumberFormatException e) {
			flag = false;
		}
		
		if (flag == true) {
			double count = Double.parseDouble(message);

			String delims = "[ ]";
			String[] tokens = bat.getCustomName().split(delims);

			if(count > 10) {
				con.getForWhom().sendRawMessage(ChatColor.GOLD + "Count too high, resetting to 10");
				tokens[6] = "10";
			}else if(count < 0){
				con.getForWhom().sendRawMessage(ChatColor.GOLD + "Count too low, resetting to 0");
				tokens[6] = "0";
			}else {
				tokens[6] = message;
			}
			
			String batName = tokens[0];
			
			for (int j = 1; j < tokens.length; j++) {
				batName = batName + " " + tokens[j];
			}
			bat.setCustomName(batName);
			con.getForWhom().sendRawMessage(ChatColor.GOLD + "Formatted correctly");
		}else {
			con.getForWhom().sendRawMessage(ChatColor.GOLD + "Formatted incorrectly");
		}
		
		CustomInventory i = new CustomInventory();
		i.mainInventoryMenu(player, bat);
		
		return null;
	}

	public void inputData(Player playerIn, Bat batIn) {
		player = playerIn;
		bat = batIn;
	}
	
	@Override
	public String getPromptText(ConversationContext arg0) {
		String string = (ChatColor.GOLD + "Input max speed");
		return string;
	}

}
