package nl.stijn.KitPvP.Abilities;

import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import nl.stijn.KitPvP.Utils.ParticleEffect;

public class AbilityDeathAdder extends Ability {
	
	@Override
	public void onDrop(Player p) {
		double radiusSquared = 7*7;

		List<Entity> entities = p.getNearbyEntities(7, 7, 7);
		for (final Entity entity : entities) {

			if(entity.getLocation().distanceSquared(p.getLocation()) > radiusSquared) continue;

			if(entity instanceof Player){

				((Player) entity).damage(1.0);
				((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 120, 2));
				((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 160, 1));

			}
		}
		p.getWorld().playSound(p.getLocation(), Sound.ZOMBIE_UNFECT, 1, 2);
		ParticleEffect.PORTAL.display(3, 3, 3, 0, 100,  p.getLocation(), 100);
		ParticleEffect.SMOKE_LARGE.display(3, 3, 3, 0, 5000, p.getLocation(), 1000);
		ParticleEffect.SMOKE_NORMAL.display(3, 3, 3, 0, 5000, p.getLocation(), 1000);

	}

	@Override
	public AbilityType type() {
		return AbilityType.DEATHADDER;
	}

	@Override
	public int cooldown() {
		return 15;
	}
}
