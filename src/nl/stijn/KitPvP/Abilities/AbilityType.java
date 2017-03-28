package nl.stijn.KitPvP.Abilities;

import org.bukkit.Material;

public enum AbilityType {

	ICEWIND("IceWind", "Gives players Slowness and weakness!", Material.PACKED_ICE, "&b&lIceWind"),
	DEATHADDER("DeathAdder", "Gives players Blindness and damage!", Material.PUMPKIN, "&6&lDeathAdder"),
	FIREBLADE("FireBlade", "Gives players Fire and alot of internal damage!", Material.LAVA_BUCKET, "&c&lFireBlade"),
	FLAREWIND("FlareWind", "Gives players Knockback and Nausea!", Material.STRING, "&f&lFlareWind"),
	TEST("Test", "Test", Material.BARRIER, "Test");
	
	private String name;
	private String description;
	private Material material;
	private String dname;
	
	private AbilityType(String name, String description, Material material, String dname) {
		this.name = name;
		this.description = description;
		this.material = material;
		this.dname = dname;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Material getMaterial() {
		return material;
	}
	
	public String getDName() {
		return dname;
	}
	
}
