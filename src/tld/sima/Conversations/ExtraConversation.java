package tld.sima.Conversations;

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
			String delims = "[ ]";
			String[] tokens = bat.getCustomName().split(delims);
			
			tokens[6] = message;
			
			String batName = tokens[0];
			
			for (int j = 1; j < tokens.length; j++) {
				batName = batName + " " + tokens[j];
			}
			bat.setCustomName(batName);
			con.getForWhom().sendRawMessage("Formatted correctly");
		}else {
			con.getForWhom().sendRawMessage("Formatted incorrectly");
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
		String string = "Input max speed";
		return string;
	}

}
