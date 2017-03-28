package nl.stijn.KitPvP.Listeners;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import nl.stijn.KitPvP.KitPvP;
import nl.stijn.KitPvP.Abilities.Ability;
import nl.stijn.KitPvP.Abilities.AbilityManager;
import nl.stijn.KitPvP.Kits.Items;
import nl.stijn.KitPvP.Managers.MySQLManager;
import nl.stijn.KitPvP.Map.MapManager;

public class JoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(!MySQLManager.userExists(p.getUniqueId())) {
			try {
				PreparedStatement ps = KitPvP.c.prepareStatement("INSERT INTO kd (uuid, name, kills, deaths) VALUES (?,?,?,?)");
				ps.setString(1, p.getUniqueId().toString());
				ps.setString(2, p.getName());
				ps.setInt(3, 0);
				ps.setInt(4, 0);
				ps.executeUpdate();
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		if(!MySQLManager.userExists2(p.getUniqueId())) {
			ArrayList<String> strings = new ArrayList<String>();
			for(Ability ab : AbilityManager.ability) {
				strings.add(ab.type().getName() + " ");
			}
			String st = strings.toString();
			st = st.replaceAll("[\\[\\]]", "");
			st = st.toLowerCase();
			
			String qu = "";
			
			for(@SuppressWarnings("unused") String s : strings) {
				qu = qu + "?, ";
			}
			
			qu = qu.substring(0, qu.length() - 2);
			try {
				PreparedStatement ps = KitPvP.c.prepareStatement("INSERT INTO ammo (uuid, name, " + st +") VALUES (?, ?, " + qu + ")");
				ps.setString(1, p.getUniqueId().toString());
				ps.setString(2, p.getName());
				for (int i = 0; i < strings.size(); ++i) {
					ps.setInt(i + 3, 0);
				}
				ps.executeUpdate();
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		if(MapManager.getInstance().ingame.containsKey(p.getUniqueId())) {
			MapManager.getInstance().ingame.remove(p.getUniqueId());
		}
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		
		p.getInventory().setItem(2, Items.kitSelector());
		p.getInventory().setItem(4, Items.Spawn());
		p.getInventory().setItem(6, Items.mapSelector());
	}
}
