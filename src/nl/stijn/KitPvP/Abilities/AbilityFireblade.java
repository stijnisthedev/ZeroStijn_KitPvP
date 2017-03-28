package nl.stijn.KitPvP.Abilities;

import java.util.List;

import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import nl.stijn.KitPvP.Utils.ParticleEffect;

public class AbilityFireblade extends Ability {
	
	@SuppressWarnings("deprecation")
	@Override
	public void onDrop(Player p) {
		ParticleEffect.FLAME.display(2, 2, 2, 0, 100, p.getLocation(), 100);
		double radiusSquared = 7*7;

		List<Entity> entities = p.getNearbyEntities(7, 7, 7);
		for (final Entity entity : entities) {

			if(entity.getLocation().distanceSquared(p.getLocation()) > radiusSquared) continue;

			if(entity instanceof Player){

				((Player) entity).damage(3.0);
				((Player) entity).setFireTicks(140);
				((Player) entity).playEffect(p.getLocation(), Effect.LAVADRIP, 50);
				((Player) entity).playEffect(p.getLocation(), Effect.LAVADRIP, 50);
				((Player) entity).playEffect(p.getLocation(), Effect.LAVA_POP, 50);
				((Player) entity).playEffect(p.getLocation(), Effect.LAVA_POP, 50);
				((Player) entity).playEffect(p.getLocation(), Effect.LAVA_POP, 50);
			}

		}
		p.getWorld().playSound(p.getLocation(), Sound.ZOMBIE_REMEDY, 1, 2);
		ParticleEffect.REDSTONE.display(3, 3, 3, 0, 100,  p.getLocation(), 100);
		ParticleEffect.LAVA.display(3, 3, 3, 1, 100, p.getLocation(), 1000);
	}

	@Override
	public AbilityType type() {
		return AbilityType.FIREBLADE;
	}

	@Override
	public int cooldown() {
		return 15;
	}

}
