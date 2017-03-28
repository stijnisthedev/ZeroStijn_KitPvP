package nl.stijn.KitPvP.Map;

import org.bukkit.Location;
import org.bukkit.World;

public class Spawn {

	private World w;
	private Double x;
	private Double y;
	private Double z;
	private Float ya;
	private Float p;
	
	public Spawn(World w, Double x, Double y, Double z, Float ya, Float p) {
		this.w = w;
		this.x = x;
		this.y = y;
		this.z = z;
		this.ya = ya;
		this.p = p;
	}

	public World getW() {
		return w;
	}
	
	public Location getLocation() {
		return new Location(w, x, y, z, ya, p);
	}

	public void setW(World w) {
		this.w = w;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getZ() {
		return z;
	}

	public void setZ(Double z) {
		this.z = z;
	}

	public Float getYa() {
		return ya;
	}

	public void setYa(Float ya) {
		this.ya = ya;
	}

	public Float getP() {
		return p;
	}

	public void setP(Float p) {
		this.p = p;
	}
	
}
