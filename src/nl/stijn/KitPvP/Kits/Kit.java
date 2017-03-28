package nl.stijn.KitPvP.Kits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

public abstract class Kit {
	
	public abstract String name();
	
	public abstract ArrayList<String> lore();
	
	public abstract List<ItemStack> items();	
	
	public abstract ArmorContents armor();
	
	public abstract ItemStack logo();
	
	public abstract int money();

}
