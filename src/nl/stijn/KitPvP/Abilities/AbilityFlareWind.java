package nl.stijn.KitPvP.Abilities;

import java.util.List;

import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import nl.stijn.KitPvP.Utils.ParticleEffect;

public class AbilityFlareWind extends Ability {
	
	@SuppressWarnings("deprecation")
	@Override
	public void onDrop(Player p) {
		double radiusSquared = 7*7;

		List<Entity> entities = p.getNearbyEntities(7, 7, 7);
		for (final Entity entity : entities) {

			if(entity.getLocation().distanceSquared(p.getLocation()) > radiusSquared) continue;

			if(entity instanceof Player) {
				Player pl = (Player) entity;
				if(pl.isOnGround()) {
					((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 150, 4));
					pl.setVelocity(p.getLocation().getDirection().add(new Vector(0,1,0)));
					((Player) entity).setVelocity(pl.getEyeLocation().getDirection().multiply(-2));
					((Player) entity).damage(1.5);
				}
				((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 150, 4));
				((Player) entity).setVelocity(pl.getEyeLocation().getDirection().multiply(-2));
				((Player) entity).playEffect(p.getLocation(), Effect.CLOUD, 500);
				((Player) entity).damage(1.5);
				
			}

		}
		p.getWorld().playSound(p.getLocation(), Sound.BAT_LOOP, 1, 2);
		ParticleEffect.SPELL_INSTANT.display(3, 3, 3, 0, 100,  p.getLocation(), 100);
		ParticleEffect.CRIT.display(3, 3, 3, 1, 100, p.getLocation(), 1000);
		ParticleEffect.CRIT_MAGIC.display(3, 3, 3, 1, 100, p.getLocation(), 1000);
	}

	@Override
	public AbilityType type() {
		return AbilityType.FLAREWIND;
	}

	@Override
	public int cooldown() {
		return 10;
	}
}
