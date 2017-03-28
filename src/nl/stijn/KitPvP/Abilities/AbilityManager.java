package nl.stijn.KitPvP.Abilities;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import nl.stijn.KitPvP.Managers.AmmoManager;

public class AbilityManager implements Listener {

	public static ArrayList<Ability> ability = new ArrayList<Ability>();
	
	public static HashMap<String, Long> cooldown = new HashMap<String, Long>();

	public static void setup() {
		ability.add(new AbilityIceWind());
		ability.add(new AbilityDeathAdder());
		ability.add(new AbilityFireblade());
		ability.add(new AbilityFlareWind());
		
	}
	
	public static AbilityType getType(String name) {
		for(Ability ab : ability) {
			if(ab.type().getName().equalsIgnoreCase(name)) {
				return ab.type();
			}
		}
		return null;
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if(!AbilityListener.inAbility.containsKey(p.getName())) {
			p.sendMessage(ChatColor.RED + "You don't have selected an ability!");
			e.setCancelled(true);
			return;
		}
		for(Ability a : ability) {
			if(a.type().equals(AbilityListener.inAbility.get(p.getName()))) {
				if(AmmoManager.getAmmo(p.getUniqueId(), a.type().getName()) < 1) {
					p.sendMessage(ChatColor.RED + "You don't have enough ammo for the ability " + a.type().getName() + "!");
					return;
				}
				int cooldownTime = a.cooldown();
		        if(cooldown.containsKey(p.getName())) {
		            long secondsLeft = ((cooldown.get(p.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
		            if(secondsLeft>0) {
		            	p.playSound(p.getLocation(), Sound.VILLAGER_IDLE, 1, 1);
		                p.sendMessage(ChatColor.RED + "You must wait " + secondsLeft + " seconds before using this ability again!");
		                e.setCancelled(true);
		                return;
		            }
		        }
		        cooldown.put(p.getName(), System.currentTimeMillis());
				e.setCancelled(true);
				a.onDrop(p);
				AmmoManager.updateAmmo(p.getUniqueId(), a.type(), 1, true);
				p.sendMessage(ChatColor.RED + "You've used your ability!" + " Ammo left: " + ChatColor.GREEN + AmmoManager.getAmmo(p.getUniqueId(), a.type().getName()) + "!");
			}
		}
	}
}
