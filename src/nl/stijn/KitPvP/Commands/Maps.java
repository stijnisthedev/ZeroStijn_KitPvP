package nl.stijn.KitPvP.Commands;

import org.bukkit.entity.Player;

import nl.stijn.KitPvP.Kits.KitManager;
import nl.stijn.KitPvP.Kits.Kits.Warrior;
import nl.stijn.KitPvP.Map.Map;
import nl.stijn.KitPvP.Map.MapManager;
import nl.stijn.KitPvP.Utils.ScreenUtil;

@CommandInfo(description = "List of all maps.", usage = "", aliases = { "maps", "ma" }, perm = "kitpvp.maps")
public class Maps extends GameCommand {

	ScreenUtil mes = ScreenUtil.getInstance;
	
	@Override
	public void onCommand(Player p, String[] args) {
		for(Map m : MapManager.getInstance().maps) {
			p.sendMessage(m.getName());
		}
		KitManager.getInstance().giveKit(p, new Warrior());
		return;
		
		
	}

}
