package nl.stijn.KitPvP.Managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import nl.stijn.KitPvP.KitPvP;

public class MySQLManager {
	
	public static boolean userExists(UUID uuid) {
		try {
			PreparedStatement ps = KitPvP.c.prepareStatement("SELECT name FROM kd WHERE uuid = ?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean userExists2(UUID uuid) {
		try {
			PreparedStatement ps = KitPvP.c.prepareStatement("SELECT name FROM ammo WHERE uuid = ?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
