package tld.sima.Conversations;

import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Player;

import tld.sima.ParticlePlugin.Inventory.CustomInventory;

public class MaterialConversation extends StringPrompt {
	
	Bat bat;
	Player player;
	
	@SuppressWarnings("unused")
	@Override
	public Prompt acceptInput(ConversationContext con, String message) {
		String delims = "[:]";
		String[] materialdata = message.split(delims);
		boolean flag = true;
		int ID = 1;
		try {
			ID = Integer.parseInt(materialdata[0]); 
		} catch (NumberFormatException e) {
			flag = false;
			ID = 1;
		}
		if (flag == true) {
			if(materialdata.length == 2) {
				byte META = 0;
				try {
					META = Byte.parseByte(materialdata[1]); 
				} catch (NumberFormatException e) {
					flag = false;
					META = 0;
				}
			}
			if (flag == true) {
				con.getForWhom().sendRawMessage(ChatColor.GOLD + "Formatted correctly. ID set to: " + message);
				delims = "[ ]";
				String[] tokens = bat.getName().split(delims);
				tokens[7] = message;
				String batName = tokens[0];
				for (int j = 1; j < tokens.length; j++) {
					batName = batName + " " + tokens[j];
				}
				bat.setCustomName(batName);
				CustomInventory i = new CustomInventory();

				i.mainInventoryMenu(player, bat);
				return null;
			}
		}
		con.getForWhom().sendRawMessage(ChatColor.GOLD + "Formatted Incorrectly");
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
		String string = (ChatColor.GOLD + "Input material ID you wish for your emitter to emit. Formatted as" + ChatColor.WHITE + "ID:META" + "\n" + ChatColor.GOLD + "EG. " + ChatColor.WHITE + "1:2" + ChatColor.GOLD + " = Polished Granite.");
		return string;
	}
	
}
