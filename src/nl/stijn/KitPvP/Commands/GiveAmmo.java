package nl.stijn.KitPvP.Commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import nl.stijn.KitPvP.Abilities.AbilityManager;
import nl.stijn.KitPvP.Abilities.AbilityType;
import nl.stijn.KitPvP.Managers.AmmoManager;
import nl.stijn.KitPvP.Utils.ScreenUtil;

@CommandInfo(description = "Give ammo to someone.", usage = "<player> <type> <remove/set/add> <ammo>", aliases = { "giveammo", "ga" }, perm = "kitpvp.giveammo")
public class GiveAmmo extends GameCommand {

	ScreenUtil mes = ScreenUtil.getInstance;
	
	@Override
	public void onCommand(Player p, String[] args) {
		if(args.length < 4) {
			p.sendMessage(ScreenUtil.prefix + "Usage: " + GiveAmmo.class.getAnnotation(CommandInfo.class).usage());
			return;
		}
		String player = args[0];
		String type = args[1];
		String executable = args[2];
		int amount;
		if(!Bukkit.getPlayer(player).isOnline() || Bukkit.getPlayer(player) == null) {
			p.sendMessage(ScreenUtil.prefix + "The player " + player + " is not online!");
			return;
		}
		if(AbilityManager.getType(type) == null) {
			p.sendMessage(ScreenUtil.prefix + "The type " + type + " doesn't exist!");
		}
		Player pl = Bukkit.getPlayer(player);
		AbilityType at = AbilityManager.getType(type);
		if(!executable.equalsIgnoreCase("remove") && !executable.equalsIgnoreCase("set") && !executable.equalsIgnoreCase("add")) {
			p.sendMessage(ScreenUtil.prefix + executable + " is not remove/set/add!");
			return;
		}
		try {
			amount = Integer.parseInt(args[3]);
		} catch(Exception e) {
			p.sendMessage(ScreenUtil.prefix + "Thats not a number!");
			return;
		}
		
		if(executable.equalsIgnoreCase("remove")) {
			AmmoManager.updateAmmo(pl.getUniqueId(), at, amount, true);
			p.sendMessage(ScreenUtil.prefix + "You've removed " + amount + " ammo from " + pl.getName() + " from the type " + at.getName() + "!");
			return;
		}
		if(executable.equalsIgnoreCase("set")) {
			AmmoManager.updateAmmo(pl.getUniqueId(), at, AmmoManager.getAmmo(pl.getUniqueId(), at.getName()), true);
			AmmoManager.updateAmmo(pl.getUniqueId(), at, amount, false);
			p.sendMessage(ScreenUtil.prefix + "You've set the ammo from " + pl.getName() + " to " + AmmoManager.getAmmo(pl.getUniqueId(), at.getName()) + " from the type " + at.getName() + "!");
			return;
		}
		if(executable.equalsIgnoreCase("add")) {
			AmmoManager.updateAmmo(pl.getUniqueId(), at, amount, false);
			p.sendMessage(ScreenUtil.prefix + "You've added " + amount + " ammo to " + pl.getName() + " from the type " + at.getName() + "!");
			return;
		}

		return;
		
	}

}
