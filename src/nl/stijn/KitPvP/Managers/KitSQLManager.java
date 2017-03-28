package nl.stijn.KitPvP.Managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.entity.Player;

import nl.stijn.KitPvP.KitPvP;
import nl.stijn.KitPvP.Kits.Kit;

public class KitSQLManager {

	public static void addKit(Player p, Kit kit) {
		try {
			PreparedStatement ps = KitPvP.c.prepareStatement("INSERT INTO kits (uuid, name, kit) VALUES (?, ?, ?)");
			ps.setString(1, p.getUniqueId().toString());
			ps.setString(2, p.getName());
			ps.setString(3, kit.name().toLowerCase());
			ps.executeUpdate();
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static boolean hasKit(Player p, Kit kit) {
		try {
			PreparedStatement ps = KitPvP.c.prepareStatement("SELECT kit FROM kits WHERE uuid = ?");
			ps.setString(1, p.getUniqueId().toString());
			ResultSet rs = ps.executeQuery();
			if(rs.next() == true) {
				if(rs.getString("kit").equals(kit.name().toLowerCase()))  {
					return true;
				} else {
					return false;
				}

			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void setup() {
		try {
			PreparedStatement ps = KitPvP.c.prepareStatement("CREATE TABLE IF NOT EXISTS kits (uuid varchar(40), name varchar(40), kit varchar(40))");
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}

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
