package nl.stijn.KitPvP.Kits;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {

	public static ItemStack kitSelector() {
		ItemStack item = new ItemStack(Material.EMERALD);
		ItemMeta meta = item.getItemMeta();

		meta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Kit Selector");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Right click");
		lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "to choose a kit!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack mapSelector() {
		ItemStack item = new ItemStack(Material.BOOK);
		ItemMeta meta = item.getItemMeta();

		meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Map Selector");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Right click");
		lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "to choose a map!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack Spawn() {
		ItemStack item = new ItemStack(Material.COMPASS);
		ItemMeta meta = item.getItemMeta();

		meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Spawn");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Right click");
		lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "to spawn!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
}
