package nl.stijn.KitPvP.Managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import nl.stijn.KitPvP.KitPvP;

public class KillManager {
	
	public static void updateKill(UUID uuid, int kills, boolean remove) {
		int ckills = getKills(uuid);
		
		if(remove) {
			kills-=ckills;
		} else {
			kills+=ckills;
		}
		try {
			PreparedStatement ps = KitPvP.c.prepareStatement("UPDATE kd SET kills = ? WHERE uuid = ?");
			ps.setInt(1, kills);
			ps.setString(2, uuid.toString());
			ps.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateDeath(UUID uuid, int deaths, boolean remove) {
		int cdeaths = getDeaths(uuid);
		
		if(remove) {
			deaths-=cdeaths;
		} else {
			deaths+=cdeaths;
		}
		try {
			PreparedStatement ps = KitPvP.c.prepareStatement("UPDATE kd SET deaths = ? WHERE uuid = ?");
			ps.setInt(1, deaths);
			ps.setString(2, uuid.toString());
			ps.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Integer getKills(UUID uuid) {
		try {
			PreparedStatement ps = KitPvP.c.prepareStatement("SELECT kills FROM kd WHERE uuid = ?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt("kills");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static Integer getDeaths(UUID uuid) {
		try {
			PreparedStatement ps = KitPvP.c.prepareStatement("SELECT deaths FROM kd WHERE uuid = ?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt("deaths");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
