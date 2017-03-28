package nl.stijn.KitPvP.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import nl.stijn.KitPvP.Map.Map;
import nl.stijn.KitPvP.Map.MapManager;
import nl.stijn.KitPvP.Map.Spawn;
import nl.stijn.KitPvP.Utils.ScreenUtil;

@CommandInfo(description = "Add a spawn.", usage = "<map>", aliases = { "addspawn", "as" }, perm = "kitpvp.addspawn")
public class AddSpawn extends GameCommand {

	ScreenUtil mes = ScreenUtil.getInstance;
	@Override
	public void onCommand(Player p, String[] args) {
		if(args.length < 1) {
			p.sendMessage(ScreenUtil.prefix + "Please define a map!");
			return;
		}
		if(MapManager.getInstance().getMap(args[0]) == null) {
			p.sendMessage(ScreenUtil.prefix + "Map doesnt exist!");
			return;
		}
		Map m = MapManager.getInstance().getMap(args[0]);
		List<String> spaw = m.getConfig().getStringList("spawns");
		spaw.add(p.getLocation().getWorld().getName() + " - " + p.getLocation().getX() + " - " + p.getLocation().getY() + " - " + p.getLocation().getZ() 
				+ " - " + p.getLocation().getYaw() + " - " + p.getLocation().getPitch());
		m.getConfig().set("spawns", spaw);
		m.saveConfig();
		List<Spawn> spaws = new ArrayList<Spawn>();
		for(Spawn s : m.getSpawns()) {
			spaws.add(s);
		}
		spaws.add(new Spawn(p.getLocation().getWorld(),p.getLocation().getX(),p.getLocation().getY(), p.getLocation().getZ() 
				,p.getLocation().getYaw(), p.getLocation().getPitch()));
		Spawn[] spawnArray = new Spawn[spaws.size()];
		spawnArray = spaws.toArray(spawnArray);
		m.setSpawns(spawnArray);
		
		p.sendMessage(ScreenUtil.prefix + "Spawn succesfully added to the map " + m.getName() + "!");
		return;
		
	}

}
