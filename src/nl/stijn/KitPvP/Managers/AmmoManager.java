package nl.stijn.KitPvP.Managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import nl.stijn.KitPvP.KitPvP;
import nl.stijn.KitPvP.Abilities.Ability;
import nl.stijn.KitPvP.Abilities.AbilityManager;
import nl.stijn.KitPvP.Abilities.AbilityType;
import nl.stijn.KitPvP.Utils.ScreenUtil;

public class AmmoManager {
	
	private static AmmoManager instance = new AmmoManager();

	public static AmmoManager getInstance() {
		return instance;
	}
	
	ScreenUtil msg = ScreenUtil.getInstance;
	
	public void setup() {
		ArrayList<String> strings = new ArrayList<String>();
		for(Ability ab : AbilityManager.ability) {
			strings.add(ab.type().getName() + " int");
		}
		String st = strings.toString();
		st = st.replaceAll("[\\[\\]]", "");
		st = st.toLowerCase();
		try {
			PreparedStatement ps = KitPvP.c.prepareStatement("CREATE TABLE IF NOT EXISTS ammo (uuid varchar(40), name varchar(40), " + st +")");
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void updateAmmo(UUID uuid, AbilityType type, int ammo, boolean remove) {
		int cammo = getAmmo(uuid, type.getName());
		
		if(remove) {
			ammo=cammo-ammo;
		} else {
			ammo+=cammo;
		}
		try {
			PreparedStatement ps = KitPvP.c.prepareStatement("UPDATE ammo SET " + type.getName().toLowerCase() +  " = ? WHERE uuid = ?");
			ps.setInt(1, ammo);
			ps.setString(2, uuid.toString());
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Integer getAmmo(UUID uuid, String abname) {
		abname = abname.toLowerCase();
		try {
			PreparedStatement ps = KitPvP.c.prepareStatement("SELECT " + abname +" FROM ammo WHERE uuid = ?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt(abname);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
