package nl.stijn.KitPvP.Kits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import nl.stijn.KitPvP.Kits.Kits.Tank;
import nl.stijn.KitPvP.Kits.Kits.Warrior;
import nl.stijn.KitPvP.Managers.KitSQLManager;
import nl.stijn.KitPvP.Utils.ItemFactory;
import nl.stijn.KitPvP.Utils.ScreenUtil;

public class KitManager implements Listener {

	private static KitManager instance = new KitManager();

	public static KitManager getInstance() {
		return instance;
	}

	public ArrayList<Kit> kits;

	public HashMap<UUID, Kit> curKit;

	ScreenUtil msg = ScreenUtil.getInstance;

	public KitManager() {
		this.kits = new ArrayList<Kit>();
		this.curKit = new HashMap<UUID, Kit>();
		this.kits.add(new Warrior());
		this.kits.add(new Tank());

	}

	public void giveKit(Player p, Kit k) {
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		for(ItemStack item : k.items()) {
			p.getInventory().addItem(item);
		}
		p.getInventory().setHelmet(k.armor().getHelmet());
		p.getInventory().setChestplate(k.armor().getChestplate());
		p.getInventory().setLeggings(k.armor().getLeggings());
		p.getInventory().setBoots(k.armor().getBoots());
		return;
	}

	public Kit getKit(String name) {
		for(Kit kit : this.kits) {
			if(kit.name().equalsIgnoreCase(name)) {
				return kit;
			}
		}
		return null;
	}

	public Kit getKit(Player p, ItemStack item) {
		for(Kit k : this.kits) {
			if(item(p, k).equals(item)) {
				return k;
			}
		}
		return null;
	}

	public boolean isKit(Player p, ItemStack item) {
		for(Kit k : this.kits) {
			if(item(p, k).equals(item)) {
				return true;
			}
		}
		return false;
	}

	public void openKitMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.RED + "Kit Selector");
		for(Kit kit : this.kits) {
			if(curKit.containsKey(p.getUniqueId())) {
				if(kit.equals(curKit.get(p.getUniqueId()))) {
					inv.addItem(ItemFactory.addGlow(item(p, kit)));
				} else {
					inv.addItem(item(p, kit));
				}
			} else {
				inv.addItem(item(p, kit));
			}
		}
		p.openInventory(inv);
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
			if(p.getItemInHand().equals(Items.kitSelector())) {
				openKitMenu(p);
			}
		}
	}

	@EventHandler
	public void click(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();

		if(e.getInventory().getTitle().equals(ChatColor.RED + "Kit Selector")) {
			e.setCancelled(true);
			if(isKit(p, e.getCurrentItem())) {
				Kit kit = getKit(p, e.getCurrentItem());
				if(!KitSQLManager.hasKit(p, kit)) {
					p.sendMessage(ScreenUtil.prefix + "You don't have this kit!");
					return;
				}						
				p.closeInventory();
				curKit.put(p.getUniqueId(), kit);
				p.sendMessage(ScreenUtil.prefix + "Succesfully selected kit " + kit.name().toLowerCase() + "!");
				return;
			}

		}
	}

	public ItemStack item(Player p, Kit kit) {
		ItemStack is = kit.logo().clone();
		ItemMeta meta = is.getItemMeta();

		meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + kit.name());
		ArrayList<String> lor = kit.lore();
		if(KitSQLManager.hasKit(p, kit)) {
			lor.add(" ");
			lor.add(ChatColor.GREEN + "" + ChatColor.BOLD + "UNLOCKED");
		} else {
			lor.add(" ");
			lor.add(ChatColor.RED + "" + ChatColor.BOLD + "LOCKED");
		}
		if(curKit.containsKey(p.getUniqueId())) {
			if(kit.equals(curKit.get(p.getUniqueId()))) {
				lor.add(ChatColor.BLUE + "Selected");
			}
		}
		meta.setLore(lor);

		is.setItemMeta(meta);
		return is;
	}

}
