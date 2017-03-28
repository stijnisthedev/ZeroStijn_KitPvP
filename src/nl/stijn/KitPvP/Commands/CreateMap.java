package nl.stijn.KitPvP.Commands;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import nl.stijn.KitPvP.Map.MapManager;
import nl.stijn.KitPvP.Utils.ScreenUtil;

@CommandInfo(description = "Create a map.", usage = "<Name>", aliases = { "createmap", "cm" }, perm = "kitpvp.createmap")
public class CreateMap extends GameCommand {

	ScreenUtil mes = ScreenUtil.getInstance;
	
	@Override
	public void onCommand(Player p, String[] args) {
		if(args.length == 0) {
			p.sendMessage(ScreenUtil.prefix + "Please define a name!");
			return;
		}
		if(args.length == 1) {
			p.sendMessage(ScreenUtil.prefix + "Please define the max players!");
			return;
		}
		if(args.length == 2) {
			p.sendMessage(ScreenUtil.prefix + "Please define a logo material!");
			return;
		}
		int i;
		try {
			i = Integer.parseInt(args[1]);
		} catch(Exception e) {
			p.sendMessage(ScreenUtil.prefix + "Thats not a number!");
			return;
		}
		if(Material.getMaterial(args[2].toUpperCase()) == null) {
			p.sendMessage(ScreenUtil.prefix + "Thats not a valid material!");
			return;
		}
		
		MapManager.getInstance().setupNewMap(args[0], i, p, Material.getMaterial(args[2].toUpperCase()));
		return;
		
		
	}

}
