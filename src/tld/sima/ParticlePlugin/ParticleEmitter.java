package tld.sima.ParticlePlugin;

import org.bukkit.Location;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ParticleEmitter{
	
	public Bat createEntity(Player player, Location loc) {
		
		Bat bat = (Bat) loc.getWorld().spawnEntity(loc, EntityType.BAT);
		
		bat.setAI(false);
		bat.setSilent(true);
		PotionEffect invis = new PotionEffect(PotionEffectType.INVISIBILITY, 999999999, 1, true, false);
		bat.addPotionEffect(invis);
		
		bat.setCustomName("particle NOTE 2 0 0 0 0");
		bat.setCustomNameVisible(false);
		
		bat.setAwake(true);
		
		return bat;
	}
	
}
