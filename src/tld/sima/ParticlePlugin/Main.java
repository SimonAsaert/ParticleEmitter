package tld.sima.ParticlePlugin;

import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import tld.sima.Command.Commands;
import tld.sima.ParticlePlugin.Events.EventClass;

public class Main extends JavaPlugin{

	private Commands commands = new Commands();

	@Override
	public void onEnable() {
		
		getCommand(commands.cmd1).setExecutor(commands);
		getServer().getPluginManager().registerEvents(new EventClass(), this);
		
		getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "Particle Plugin has been enabled\r\n");
	}

	@Override
	public void onDisable() {
		
	}
	
	
	
}
