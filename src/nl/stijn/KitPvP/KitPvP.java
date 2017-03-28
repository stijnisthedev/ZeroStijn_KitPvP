package nl.stijn.KitPvP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import nl.stijn.KitPvP.Abilities.AbilityListener;
import nl.stijn.KitPvP.Abilities.AbilityManager;
import nl.stijn.KitPvP.Abilities.AbilityType;
import nl.stijn.KitPvP.Commands.CommandManager;
import nl.stijn.KitPvP.Database.MySQL.MySQL;
import nl.stijn.KitPvP.Kits.KitManager;
import nl.stijn.KitPvP.Listeners.JoinListener;
import nl.stijn.KitPvP.Managers.AmmoManager;
import nl.stijn.KitPvP.Managers.KitSQLManager;
import nl.stijn.KitPvP.Map.MapManager;

public class KitPvP extends JavaPlugin {

	public static KitPvP pvp;

	public static MySQL MySQL = new MySQL("localhost", "3306", "kitpvp", "root", "chocolade123");
	public static Connection c = null;

	public static boolean works = true;
	public static String nmsver;

	public void onEnable() {
		pvp = this;
		getCommand("kitpvp").setExecutor(new CommandManager());

		nmsver = Bukkit.getServer().getClass().getPackage().getName();
		nmsver = nmsver.substring(nmsver.lastIndexOf(".") + 1);
		try {
			c = MySQL.openConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		AbilityManager.setup();

		AbilityListener.ABSlots.put(10, AbilityType.ICEWIND);
		AbilityListener.ABSlots.put(12, AbilityType.DEATHADDER);
		AbilityListener.ABSlots.put(14, AbilityType.FIREBLADE);
		AbilityListener.ABSlots.put(16, AbilityType.FLAREWIND);
		AmmoManager.getInstance().setup();
		KitSQLManager.setup();
		MapManager.getInstance().setup();
		Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new AbilityManager(), this);
		Bukkit.getPluginManager().registerEvents(new AbilityListener(), this);
		Bukkit.getPluginManager().registerEvents(new KitManager(), this);
		Bukkit.getPluginManager().registerEvents(new MapManager(), this);
	}


	public void onDisable() {

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("addcolum")) {
			if(!(sender instanceof Player)) {
				if(args.length == 0) {
					sender.sendMessage("Please define a name!");
					return true;
				}
				try {
					PreparedStatement ps = c.prepareStatement("ALTER TABLE `ammo` ADD test int");
					ps.executeUpdate();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

}
