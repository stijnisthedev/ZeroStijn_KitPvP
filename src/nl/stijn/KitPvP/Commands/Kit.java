package nl.stijn.KitPvP.Commands;

import org.bukkit.entity.Player;

import nl.stijn.KitPvP.Utils.ScreenUtil;

@CommandInfo(description = "Select a kit.", usage = "<kitName>", aliases = { "k" }, perm = "kitpvp.kit")
public class Kit extends GameCommand {

	ScreenUtil mes = ScreenUtil.getInstance;
	@Override
	public void onCommand(Player p, String[] args) {
		if(args.length < 1) {
			p.sendMessage(ScreenUtil.prefix + "Please define a kit!");
			return;
		}
		//TODO Kit systeem intergreren.
		
		
	}

}
