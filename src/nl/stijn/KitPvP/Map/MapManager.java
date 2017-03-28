package nl.stijn.KitPvP.Map;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import nl.stijn.KitPvP.KitPvP;
import nl.stijn.KitPvP.Kits.Items;
import nl.stijn.KitPvP.Utils.ScreenUtil;

public class MapManager implements Listener {

	private static MapManager instance = new MapManager();

	public static MapManager getInstance() {
		return instance;
	}

	public ArrayList<Map> maps;

	public HashMap<UUID, Map> ingame;
	public HashMap<UUID, Map> selected;

	ScreenUtil msg = ScreenUtil.getInstance;

	public MapManager() {
		this.maps = new ArrayList<Map>();
		this.ingame = new HashMap<UUID, Map>();
		this.selected = new HashMap<UUID, Map>();
	}

	public void setup() {
		maps.clear();

		File[] files = new File(KitPvP.pvp.getDataFolder() + "/maps").listFiles();
		if(files == null) {
			return;
		}

		for(File file : files) {
			try {
				FileConfiguration c = YamlConfiguration.loadConfiguration(file);
				List<String> spawn = c.getStringList("spawns");
				ArrayList<Spawn> spawns = new ArrayList<Spawn>();
				for (int i = 0; i < spawn.size(); ++i) {
					World W = Bukkit.getWorld(spawn.get(i).split(" - ")[0]);
					double X = Double.parseDouble(spawn.get(i).split(" - ")[1]);
					double Y = Double.parseDouble(spawn.get(i).split(" - ")[2]);
					double Z = Double.parseDouble(spawn.get(i).split(" - ")[3]);
					float P = Float.parseFloat(spawn.get(i).split(" - ")[4]);
					float YA = Float.parseFloat(spawn.get(i).split(" - ")[5]);
					Spawn loc = new Spawn(W,X,Y,Z,YA,P);
					spawns.add(loc);
				}
				Spawn[] spawnArray = new Spawn[spawns.size()];
				spawnArray = spawns.toArray(spawnArray);
				Map map = new Map(c.getInt("id"), UUID.fromString(c.getString("UUID")),c.getString("name"), c.getInt("maxplayers"), c.getInt("players"), c.getBoolean("online"), spawnArray, file, c, Material.getMaterial(c.getString("logo").toUpperCase()));
				this.maps.add(map);
			} catch(Exception e) {
				System.out.println("An error occured!");
				e.printStackTrace();
			}
		}
	}

	public Map getMap(String name) {
		for(Map m : maps) {
			if(m.getName().equals(name)) {
				return m;
			}
		}
		return null;
	}

	public void setupNewMap(String name, int maxplayers, Player p, Material logo) {
		int id = 0;
		if(this.maps.isEmpty()) {

		} else {
			id = getLastMap();
		}

		String num = String.valueOf(id);
		byte[] result = num.getBytes();
		UUID uuid = UUID.nameUUIDFromBytes(result);

		File file = new File(KitPvP.pvp.getDataFolder() + "/maps", uuid + ".yml");
		FileConfiguration c = YamlConfiguration.loadConfiguration(file);

		c.set("id", id);
		c.set("UUID", uuid.toString());
		c.set("name", name);
		c.set("maxplayers", maxplayers);
		c.set("players", 0);
		c.set("logo", logo.toString().toLowerCase());
		c.set("online", false);

		try {
			c.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map map = new Map(id, uuid, name, maxplayers, 0, false, null, file, c, logo);
		maps.add(map);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch(IOException e) {
				System.out.println(ScreenUtil.prefix + "Error creating map");
			}
		}
		p.sendMessage(ScreenUtil.prefix + "Created map " + name + "!");
		return;
	}

	public void removeMap(Map map) {
		File[] files = new File(KitPvP.pvp.getDataFolder() + "/maps").listFiles();
		if(files == null) {
			return;
		}
		maps.remove(map);
		File filetoremove = null;
		for(File file : files) {
			if(file.getName().replace(".yml", "").equals(map.getUUID().toString())) {
				filetoremove = file;
			}
		}

		filetoremove.delete();
		return;
	}

	public static ArrayList<Integer> findMissingNumbers(Integer[] a, int first, int last) {
		ArrayList<Integer> r = new ArrayList<Integer>();
		for (int i = first; i < a[0]; i++) {
			r.add(i);
		}
		for (int i = 1; i < a.length; i++) {
			for (int j = 1+a[i-1]; j < a[i]; j++) {
				r.add(j);
			}
		}
		for (int i = 1+a[a.length-1]; i <= last; i++) {
			r.add(i);
		}
		return r;
	}

	public int getLastMap() {
		ArrayList<Integer> mps = new ArrayList<Integer>();
		for(Map map : this.maps) {
			mps.add(map.getID());
		}
		Collections.sort(mps);

		Integer[] arr = new Integer[mps.size()];
		arr = mps.toArray(arr);

		ArrayList<Integer> ints = findMissingNumbers(arr, 0, mps.size());

		return ints.get(0);
	}

	public ItemStack glass() {
		ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Kit");
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack glass2() {
		ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 3);
		ItemMeta meta = item.getItemMeta();

		meta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "PvP");
		item.setItemMeta(meta);
		return item;
	}

	public void openMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GREEN + "" + ChatColor.BOLD + "Choose a map.");
		
		inv.setItem(0, MapItems.mapItem(this.maps.get(0)));

		p.openInventory(inv);
		return;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(p.getItemInHand().equals(Items.mapSelector())) {
				openMenu(p);
			}
		}
	}


}
