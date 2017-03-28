package nl.stijn.KitPvP.Map;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MapItems {
	
	public static ItemStack mapItem(Map map) {
		@SuppressWarnings("deprecation")
		ItemStack item = new ItemStack(Material.getMaterial(map.getLogo().getId()));
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + map.getName());
		meta.setLore(Arrays.asList(ChatColor.GREEN + "Click to select", ChatColor.GREEN + "this map!"));
		
		item.setItemMeta(meta);
		return item;
	}

}
