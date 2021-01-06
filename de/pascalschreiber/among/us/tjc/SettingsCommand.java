package de.pascalschreiber.among.us.tjc;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class SettingsCommand implements CommandExecutor, TabCompleter {

	private Main plugin;
	private List<String> settings;
	
	public SettingsCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("settings").setTabCompleter(this);
		settings = new ArrayList<>();
		settings.add("spezial-tasks");
		settings.add("common-tasks");
		settings.add("long-tasks");
		settings.add("voting-time");
		settings.add("kill-cooldown");
		settings.add("emergency-call-cooldown");
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
		return new ArrayList<>();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			System.out.println("Dieser Befehl kann noch nicht in der Konsole verwendet werden."); 
			return true;
		}
		Player player = (Player) sender;
		
		if (player.getScoreboard().getObjective("data").getScore("#gameon").getScore() == 1) {
			player.sendMessage(Chat.ERROR + plugin.getConfig().getString("messages.general.game-is-running"));
			return true;
		}
		
		if (args.length == 2) {
			try {
				int value = Integer.valueOf(args[1]);
				switch (args[0]) {
					case "spezial-tasks":
						setSetting(player, "#special_tasks", value, 1, 8, 18, 57, -20);
						break;
					case "common-tasks":
						setSetting(player, "#standard_tasks", value, 1, 17, 18, 57, -19);
						break;
					case "long-tasks":
						setSetting(player, "#long_tasks", value, 1, 4, 18, 57, -18);
						break;
					case "voting-time":
						setSetting(player, "#voting_time", value, 15, 180, 18, 57, -17);
						break;
					case "emergency-call-cooldown":
						setSetting(player, "#emergency_timeout", value, 1, 180, 18, 57, -16);
						break;
					case "kill-cooldown":
						setSetting(player, "#kill_cooldown", value, 15, 90, 18, 57, -15);
						break;
					default:
						player.sendMessage(Chat.ERROR + plugin.getConfig().getString("messages.settings.wrong-setting").replaceAll("%name%", args[0]));
						player.sendMessage(Chat.COMMAND + "/settings {" + getSettingList() + "} {value}");
						break;
				}
			} catch(NumberFormatException e) {
				player.sendMessage(Chat.ERROR + plugin.getConfig().getString("messages.value-must-be-a-umber"));
			}
		} else {
			player.sendMessage(Chat.ERROR + plugin.getConfig().getString("messages.settings.wrong-parameter-length"));
			player.sendMessage(Chat.COMMAND + "/settings {" + getSettingList() + "} {value}");
		}
		return true;
	}
	
	private void updateSettingsDisplay(Player player) {
		player.getWorld().getBlockAt(new Location(player.getLocation().getWorld(), 20, 54, -21)).setType(Material.REDSTONE_BLOCK);
		player.getWorld().getBlockAt(new Location(player.getLocation().getWorld(), 21, 54, -21)).setType(Material.REDSTONE_BLOCK);
		player.getWorld().getBlockAt(new Location(player.getLocation().getWorld(), 22, 54, -21)).setType(Material.REDSTONE_BLOCK);
		player.getWorld().getBlockAt(new Location(player.getLocation().getWorld(), 23, 54, -21)).setType(Material.REDSTONE_BLOCK);
		player.getWorld().getBlockAt(new Location(player.getLocation().getWorld(), 24, 54, -21)).setType(Material.REDSTONE_BLOCK);
		player.getWorld().getBlockAt(new Location(player.getLocation().getWorld(), 18, 54, -21)).setType(Material.AIR);
	}
	
	private void setSetting(Player player, String setting, int value, int min, int max, int x, int y, int z) {
		if (value >= min && value <= max) {
			player.getScoreboard().getObjective("settings").getScore(setting).setScore(value);
			updateSettingsDisplay(player);
		} else {
			player.sendMessage(Chat.ERROR + plugin.getConfig().getString("messages.settings.value-must-between")
					.replace("%name%", setting)
					.replace("%min%", String.valueOf(min))
					.replace("%max%", String.valueOf(max))
			);
		}
	}
	
	private String getSettingList() {
		String ret = "";
		for (String setting : settings) {
			ret += setting + ", ";
		}
		return ret.substring(0, ret.length() - 2);
	}
}
