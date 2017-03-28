package nl.stijn.KitPvP.Abilities;

import org.bukkit.entity.Player;

public abstract class Ability {
	
	 public abstract void onDrop(Player p);

	 public abstract AbilityType type();
	 
	 public abstract int cooldown();

}
