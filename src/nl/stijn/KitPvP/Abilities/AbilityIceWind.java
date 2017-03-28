package nl.stijn.KitPvP.Abilities;

import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import nl.stijn.KitPvP.Utils.ParticleEffect;

public class AbilityIceWind extends Ability {
	
	@Override
	public void onDrop(Player p) {
		double radiusSquared = 7*7;

		List<Entity> entities = p.getNearbyEntities(7, 7, 7);
		for (final Entity entity : entities) {
			if(entity.getLocation().distanceSquared(p.getLocation()) > radiusSquared) continue;

			if(entity instanceof Player) {
				((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 145, 2));
				((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 80, 0));
			}
		} 
		p.getWorld().playSound(p.getLocation(), Sound.ZOMBIE_REMEDY, 1, 2);
		ParticleEffect.SNOWBALL.display(3, 3, 3, 0, 100,  p.getLocation(), 100);
		ParticleEffect.ENCHANTMENT_TABLE.display(3, 3, 3, 0, 100,  p.getLocation(), 100);
		ParticleEffect.CLOUD.display(3, 3, 3, 0, 100,  p.getLocation(), 100);
		}

		@Override
		public AbilityType type() {
			return AbilityType.ICEWIND;
		}

		@Override
		public int cooldown() {
			return 15;
		}

	}