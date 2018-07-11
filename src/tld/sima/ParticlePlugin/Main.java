package tld.sima.ParticlePlugin;

import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin{
	
	@Override
	public void onEnable() {

		getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "\r\nParticle Plugin has been enabled\r\n");
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
}
