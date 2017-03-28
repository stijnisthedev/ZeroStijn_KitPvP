package nl.stijn.KitPvP.Kits.Kits;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import nl.stijn.KitPvP.Kits.ArmorContents;
import nl.stijn.KitPvP.Kits.Kit;

public class Warrior extends Kit {

	@Override
	public String name() {
		return "Warrior";
	}

	@Override
	public ArmorContents armor() {
		return new ArmorContents(new ItemStack(Material.IRON_HELMET), new ItemStack(Material.IRON_CHESTPLATE), new ItemStack(Material.IRON_LEGGINGS), new ItemStack(Material.IRON_BOOTS));
	}

	@Override
	public List<ItemStack> items() {
		List<ItemStack> item = new ArrayList<ItemStack>();
		item.add(new ItemStack(Material.DIAMOND_SWORD));
		item.add(new ItemStack(Material.GOLDEN_APPLE));
		return item;
	}

	@Override
	public ArrayList<String> lore() {
		ArrayList<String> lor = new ArrayList<String>();
		lor.add(ChatColor.RED + "Contains: ");
		lor.add(ChatColor.RED + "Nothing");
		return lor;
	}

	@Override
	public ItemStack logo() {
		return new ItemStack(Material.DIAMOND_SWORD);
	}

	@Override
	public int money() {
		return 0;
	}

}
