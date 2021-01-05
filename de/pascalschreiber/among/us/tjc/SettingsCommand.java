package de.pascalschreiber.among.us.tjc;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class SettingsCommand implements CommandExecutor, TabCompleter {
	
	private List<String> settings; 
	
	public SettingsCommand(Main plugin) {
		plugin.getCommand("settings").setTabCompleter(this);
		settings = new ArrayList<>();
		settings.add("spezial-tasks");
		settings.add("common-tasks");
		settings.add("long-tasks");
		settings.add("voting-time");
		settings.add("kill-cooldown");
		settings.sort(String.CASE_INSENSITIVE_ORDER);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player))
			return new ArrayList<>();
		if (args.length == 1) {
			if (args[0].length() == 0) {
				return settings;
			} else {
				List<String> list = new ArrayList<>();
				for (String setting : settings) {
					if (setting.startsWith(args[0]))
						list.add(setting);
				}
				return list;
			}
		}
		
		if (args.length == 2) {
			
		} 
		
		return new ArrayList<>();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			System.out.println("Dieser Befehl kann noch nicht in der Konsole verwendet werden."); 
			return true;
		}
		Player player = (Player) sender;
		if (args.length == 2) {
			
		} else {
			
		}
		return false;
	}
}
