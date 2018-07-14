package tld.sima.ParticlePlugin.HashMaps;

import java.util.HashMap;

import org.bukkit.entity.Bat;

public class PlayerEntityHM {

	private HashMap<String, Bat> batMap = new HashMap<String, Bat>();
	public HashMap<String, Bat> getASMap(){return batMap;}
	
	public void addToMap(String player, Bat bat){
		batMap.put(player, bat);
	}
	
	public Bat getEntity(String player){
		return batMap.get(player);
	}
}
