package tld.sima.Conversations;

import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Player;

import tld.sima.ParticlePlugin.Inventory.CustomInventory;

public class NumberConversation extends StringPrompt{

	private Bat bat;
	private Player player;
	
	@Override
	public Prompt acceptInput(ConversationContext con, String message) {
		boolean flag = true;
		try{
			Integer.parseInt(message);
		}catch(NumberFormatException e){
			flag = false;
		}
		if (flag == true) {
			int count = Integer.parseInt(message);
			if (count < 1) {
				count = 1;
				con.getForWhom().sendRawMessage(ChatColor.GOLD + "Count too low. Resetting to 1");
			}
			String name = bat.getCustomName();

			String delims = "[ ]";
			String[] tokens = name.split(delims);
			
			tokens[2] = Integer.toString(count);
			
			String batName = tokens[0];
			for (int j = 1; j < tokens.length; j++) {
				batName = batName + " " + tokens[j];
			}
			
			bat.setCustomName(batName);
			
			con.getForWhom().sendRawMessage(ChatColor.GOLD + "Set Delay between each particle at " + ChatColor.WHITE + count + ChatColor.GOLD + " ticks");
			
			CustomInventory i = new CustomInventory();
			i.mainInventoryMenu(player, bat);
			
			
		}
		
		return null;
	}
	
	public void inputData(Player playerIn,Bat batIn) {
		bat = batIn;
		player = playerIn;
	}
	
	@Override
	public String getPromptText(ConversationContext arg0) {
		String string = (ChatColor.GOLD + "Input the delay between each particle in ticks. Minimum 1 ticks delay.");
		
		return string;
	}

}
