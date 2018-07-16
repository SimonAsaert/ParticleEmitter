package tld.sima.Conversations;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import tld.sima.ParticlePlugin.Inventory.CustomInventory;

public class ParticleOffsetConversation extends StringPrompt {

	private Player player;
	private Bat bat;

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
			con.getForWhom().sendRawMessage(ChatColor.GOLD + "Formatted correctly");
			String[] name = bat.getCustomName().split(delims);
			for(int j = 0; j < tokens.length; j++) {
				name[j + 3] = tokens[j];
			}
			
			String batName = name[0];
			for (int j = 1; j < name.length; j++) {
				batName = batName + " " + name[j];
			}
			bat.setCustomName(batName);
			
		}else {
			con.getForWhom().sendRawMessage(ChatColor.GOLD + "Incorrect formatting");
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
		
		String string = (ChatColor.GOLD + "Input the amount you with the MAX offset of the particle in all directions to be. Formatted as :" + ChatColor.WHITE + "X Y Z");
		
		return string;
	}

}
