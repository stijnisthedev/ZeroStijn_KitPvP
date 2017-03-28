package nl.stijn.KitPvP.Utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class ItemFactory {

	@SuppressWarnings("deprecation")
	public static ItemStack create(Material material, byte data, String displayName, String... lore) {
		ItemStack itemStack = new MaterialData(material, data).toItemStack(1);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(displayName);
		if (lore != null) {
			List<String> finalLore = new ArrayList<>();
			for (String s : lore)
				if (s != null)
					finalLore.add(s.replace("&", "§"));
			itemMeta.setLore(finalLore);
		}
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public static void fillInventory(Inventory inventory) {
			MaterialData materialData = getMaterialData("wood");
			org.bukkit.inventory.ItemStack itemStack = materialData.toItemStack(1);
			ItemMeta itemMeta = itemStack.getItemMeta();
			itemMeta.setDisplayName("7");
			itemStack.setItemMeta(itemMeta);
			for (int i = 0; i < inventory.getSize(); i++) {
				if ((inventory.getItem(i) == null) || (inventory.getItem(i).getType() == Material.AIR)) {
					inventory.setItem(i, itemStack);
				}
			}
		}
	

	@SuppressWarnings("deprecation")
	private static MaterialData getMaterialData(String name) {
		return new MaterialData(Integer.parseInt(name.split(":")[0]),
				(name.split(":").length > 1 ? (byte) Integer.parseInt(name.split(":")[1]) : (byte) 0));
	}

	public static ItemStack createSkull(String urlToFormat, String name) {

		String url = urlToFormat;
		ItemStack head = create(Material.SKULL_ITEM, (byte) 3, ChatColor.GOLD + "" + ChatColor.BOLD + name);

		if (url.isEmpty()) return head;

		SkullMeta headMeta = (SkullMeta) head.getItemMeta();
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		profile.getProperties().put("textures", new Property("textures", url));
		Field profileField;
		try {
			profileField = headMeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(headMeta, profile);
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
		head.setItemMeta(headMeta);
		return head;
	}

	public static ItemStack createColouredLeatherRGB(Material armourPart, int red, int green, int blue) {
		ItemStack itemStack = new ItemStack(armourPart);
		LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
		leatherArmorMeta.setColor(Color.fromRGB(red, green, blue));
		itemStack.setItemMeta(leatherArmorMeta);
		return itemStack;
	}

	public static ItemStack createColouredLeather(Material armourPart, Color color) {
		ItemStack itemStack = new ItemStack(armourPart);
		LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
		leatherArmorMeta.setColor(color);
		itemStack.setItemMeta(leatherArmorMeta);
		return itemStack;
	}

	public static ItemStack addGlow(ItemStack item) {
		net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		NBTTagCompound tag = null;
		if (!nmsStack.hasTag()) {
			tag = new NBTTagCompound();
			nmsStack.setTag(tag);
		}
		if (tag == null) tag = nmsStack.getTag();
		NBTTagList ench = new NBTTagList();
		tag.set("ench", ench);
		nmsStack.setTag(tag);
		return CraftItemStack.asCraftMirror(nmsStack);
	}
}