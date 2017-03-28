package nl.stijn.KitPvP.Abilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import nl.stijn.KitPvP.Managers.AmmoManager;

public class AbilityListener implements Listener{
	
	public static HashMap<String, AbilityType> inAbility = new HashMap<String, AbilityType>();
	
	public static int[] SLOTS = {10, 12, 14, 16};
	
	public static HashMap<Integer, AbilityType> ABSlots = new HashMap<Integer, AbilityType>();
	
	public static ItemStack ability(AbilityType ability, Player p) {
		ItemStack item = new ItemStack(ability.getMaterial());
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',  ability.getDName()) + " " + ChatColor.GRAY + "" + ChatColor.BOLD + "Left Click");
		if(inAbility.containsKey(p.getName())) {
			if(inAbility.get(p.getName()).equals(ability)) {
				meta.addEnchant(Enchantment.DURABILITY, 10, false);
				meta.setLore(Arrays.asList("",ChatColor.GREEN + ability.getDescription(), ChatColor.RED + "Press 'Q' To Activate The Ability",ChatColor.YELLOW + "" + ChatColor.BOLD + "Ammo: " + AmmoManager.getAmmo(p.getUniqueId(), ability.getName()), ""));
			} else {
				meta.setLore(Arrays.asList("",ChatColor.GREEN + ability.getDescription(), ChatColor.RED + "Press 'Q' To Activate The Ability",ChatColor.YELLOW + "" + ChatColor.BOLD + "Ammo: " + AmmoManager.getAmmo(p.getUniqueId(), ability.getName()), ""));
			}
		} else {
			meta.setLore(Arrays.asList("",ChatColor.GREEN + ability.getDescription(), ChatColor.RED + "Press 'Q' To Activate The Ability",ChatColor.YELLOW + "" + ChatColor.BOLD + "Ammo: " + AmmoManager.getAmmo(p.getUniqueId(), ability.getName()), ""));
		}


		item.setItemMeta(meta);
		return item;
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if(p.getLocation().getWorld().getName().equals("kitpvpmap")) {
			
		}
		
	}
	
	public static ItemStack glass() {
	     ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE);
	     ItemMeta meta = item.getItemMeta();
	     meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Kit");
	     item.setItemMeta(meta);
	     return item;
	}
	
	public static ItemStack glass2() {
	     ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 3);
	     ItemMeta meta = item.getItemMeta();
	     
	     meta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "PvP");
	     item.setItemMeta(meta);
	     return item;
	}
	
	public static void openMenu(Player p) {
		Inventory abillityselectorinv = Bukkit.createInventory(null, 27, ChatColor.GREEN + "" + ChatColor.BOLD + "Ability Selector");
		
		abillityselectorinv.setItem(0, glass());
		abillityselectorinv.setItem(1, glass2());
		abillityselectorinv.setItem(3, glass2());
		abillityselectorinv.setItem(4, glass());
		abillityselectorinv.setItem(5, glass2());
		abillityselectorinv.setItem(7, glass2());
		abillityselectorinv.setItem(8, glass());
		abillityselectorinv.setItem(9, glass2());
		abillityselectorinv.setItem(17, glass2());
		abillityselectorinv.setItem(18, glass());
		abillityselectorinv.setItem(19, glass2());
		abillityselectorinv.setItem(21, glass2());
		abillityselectorinv.setItem(22, glass());
		abillityselectorinv.setItem(23, glass2());
		abillityselectorinv.setItem(25, glass2());
		abillityselectorinv.setItem(26, glass());
		
		Iterator<Integer> ire = ABSlots.keySet().iterator();
		while (ire.hasNext()) {
			Integer se = ire.next();
			abillityselectorinv.setItem(se, ability(ABSlots.get(se), p));
		}
		p.openInventory(abillityselectorinv);
		return;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getTitle().equals(ChatColor.GREEN + "" + ChatColor.BOLD + "Ability Selector")) {
			if(e.getCurrentItem() == null) {
				return;
			}
			if(e.getCurrentItem() == null) {
				return;
			}
			String name = e.getCurrentItem().getItemMeta().getDisplayName();
			name = ChatColor.stripColor(name);
			name = name.replaceAll(" Left Click", "");
			if(AbilityListener.getAbilityByName(name) != null) {
				AbilityType a = AbilityListener.getAbilityByName(name);
				if(AmmoManager.getAmmo(p.getUniqueId(), a.getName()) <= 0) {
					p.sendMessage(ChatColor.RED + "You don't have ammo for this ability!");
					p.closeInventory();
					return;
				}
				p.playSound(p.getLocation(), Sound.BAT_TAKEOFF, 5, 5);
				AbilityListener.inAbility.put(p.getName(), a);
				p.sendMessage(ChatColor.GREEN + "You have selected the ability " + a.getName());
				p.closeInventory();
				return;
				
			}
			return;
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			if(p.getItemInHand().equals(abilityselector())) {
				openMenu(p);
				return;
			}
		}
	
	}
	
	public static ItemStack abilityselector() {
	     ItemStack item = new ItemStack(Material.BOOK);
	     ItemMeta meta = item.getItemMeta();
	     
	     meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Ability Selector");
	     ArrayList<String> lore = new ArrayList<String>();
	     lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Click to select");
	     lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "an ability!");
	     meta.setLore(lore);
	     item.setItemMeta(meta);
	     return item;
	    }
	
    public static AbilityType getAbilityByName(String name) {
        for (AbilityType ab : AbilityType.values()) {
            if (ab.getName().replace(" ", "").equals(name.replace(" ", "")))
                return ab;
        }
        return null;
    }

}
