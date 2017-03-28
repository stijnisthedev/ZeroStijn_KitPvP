package nl.stijn.KitPvP.Commands;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.stijn.KitPvP.Utils.ScreenUtil;

public class CommandManager implements CommandExecutor {

	private ArrayList<GameCommand> cmds;
	
	public CommandManager() {
		cmds = new ArrayList<>();
		cmds.add(new CreateMap());
		cmds.add(new Maps());
		cmds.add(new AddSpawn());
		cmds.add(new RemoveMap());
		cmds.add(new GiveAmmo());
	}
	
	ScreenUtil mes = ScreenUtil.getInstance;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ScreenUtil.prefix + "Only players!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("kitpvp")) {
			if (args.length == 0) {
				for (GameCommand gcmd : cmds) {
					CommandInfo info = gcmd.getClass().getAnnotation(CommandInfo.class);
					p.sendMessage(ScreenUtil.prefix + "/kitpvp (" + StringUtils.join(info.aliases(), " ").trim() + ") " + info.usage() + " - " + info.description());
				}
				
				return true;
			}
			
			GameCommand wanted = null;
			
			for (GameCommand gcmd : cmds) {
				CommandInfo info = gcmd.getClass().getAnnotation(CommandInfo.class);
				for (String alias : info.aliases()) {
					if (alias.equals(args[0])) {
						wanted = gcmd;
						break;
					}
				}
			}
			
			if (wanted == null) {
				p.sendMessage(ScreenUtil.prefix + "Command doesnt exist!");
				return true;
			}
			
			if(!p.hasPermission(wanted.getClass().getAnnotation(CommandInfo.class).perm())) {
				p.sendMessage(ScreenUtil.prefix + "You have permission to do this!");
				return true;
			}
			
			ArrayList<String> newArgs = new ArrayList<String>();
			Collections.addAll(newArgs, args);
			newArgs.remove(0);
			args = newArgs.toArray(new String[newArgs.size()]);
			
			wanted.onCommand(p, args);
		}
		
		return true;
	}
}