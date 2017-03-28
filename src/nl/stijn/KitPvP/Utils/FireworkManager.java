package nl.stijn.KitPvP.Utils;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftFirework;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

public class FireworkManager {
	

	public static void instantFirework(Location loc, FireworkEffect fwe){
		Firework firework = loc.getWorld().spawn(loc, Firework.class);
		FireworkMeta data = firework.getFireworkMeta();
		data.addEffect(fwe);
		firework.setFireworkMeta(data);
        NBTTagCompound nbtData = new NBTTagCompound();
        nbtData.setInt("Life", 1);
        nbtData.setInt("LifeTime", 2);
        ((CraftFirework) firework).getHandle().a(nbtData);
	}
	
	public static void delayedFirework(Location loc, FireworkEffect fwe, int power){
		Firework firework = loc.getWorld().spawn(loc, Firework.class);
		FireworkMeta data = firework.getFireworkMeta();
		data.addEffect(fwe);
		data.setPower(power);
		firework.setFireworkMeta(data);
	}
	
}
