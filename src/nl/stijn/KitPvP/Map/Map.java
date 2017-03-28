package nl.stijn.KitPvP.Map;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Map {
	
	private int id;
	private UUID uuid;
	private String name;
	private int maxplayers;
	private int onplayers;
	private boolean online;
	private Spawn[] spawns;
	private File file;
	private FileConfiguration config;
	private Material logo;
	
	public Map(int id, UUID uuid, String name, int maxplayers, int onplayers, boolean online, Spawn[] spawns, File file, FileConfiguration config, Material logo) {
		this.id = id;
		this.uuid = uuid;
		this.name = name;
		this.maxplayers = maxplayers;
		this.onplayers = onplayers;
		this.online = online;
		this.spawns = spawns;
		this.file = file;
		this.config = YamlConfiguration.loadConfiguration(this.file);
		this.logo = logo;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public UUID getUUID() {
		return uuid;
	}
	
	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxplayers() {
		return maxplayers;
	}

	public void setMaxplayers(int maxplayers) {
		this.maxplayers = maxplayers;
	}

	public int getOnplayers() {
		return onplayers;
	}

	public void setOnplayers(int onplayers) {
		this.onplayers = onplayers;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public Spawn[] getSpawns() {
		return spawns;
	}

	public void setSpawns(Spawn[] spawns) {
		this.spawns = spawns;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public FileConfiguration getConfig() {
		return config;
	}

	public void setConfig(FileConfiguration config) {
		this.config = config;
	}
	
	public void saveConfig() {
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Material getLogo() {
		return logo;
	}
	
	public void setLogo(Material logo) {
		this.logo = logo;
	}
}
