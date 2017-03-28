package nl.stijn.KitPvP.Commands;

import org.bukkit.entity.Player;

import nl.stijn.KitPvP.Map.Map;
import nl.stijn.KitPvP.Map.MapManager;
import nl.stijn.KitPvP.Utils.ScreenUtil;

@CommandInfo(description = "Remove a map.", usage = "<Name>", aliases = { "removemap", "rm" }, perm = "kitpvp.removemap")
public class RemoveMap extends GameCommand {

	ScreenUtil mes = ScreenUtil.getInstance;
	
	@Override
	public void onCommand(Player p, String[] args) {
		if(args.length == 0) {
			p.sendMessage(ScreenUtil.prefix + "Please define a name!");
			return;
		}
		if(MapManager.getInstance().getMap(args[0]) == null) {
			p.sendMessage(ScreenUtil.prefix + "Map doesnt exist!");
			return;
		}
		Map m = MapManager.getInstance().getMap(args[0]);
		MapManager.getInstance().removeMap(m);
		p.sendMessage(ScreenUtil.prefix + "Succesfully removed map " + args[0] + "!");
		return;
		
		
	}

}
