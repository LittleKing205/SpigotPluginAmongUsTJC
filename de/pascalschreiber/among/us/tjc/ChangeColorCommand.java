package de.pascalschreiber.among.us.tjc;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

public class ChangeColorCommand implements CommandExecutor, TabCompleter {
	
	Main plugin;
	
	public ChangeColorCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("color").setTabCompleter(this);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 1) {
			if (args[0].length() == 0) {
				return plugin.getConfig().getStringList("player-colors");
			} else {
				List<String> list = new ArrayList<>();
				for (String color : plugin.getConfig().getStringList("player-colors")) {
					if (color.startsWith(args[0]))
						list.add(color);
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
		Score score = player.getScoreboard().getObjective("player").getScore(player.getDisplayName());
		
		if (score.isScoreSet()) {
			if (args.length == 1) {
				int colorInt = plugin.getConfig().getStringList("player-colors").indexOf(args[0].toLowerCase()) + 1;
				if (colorInt == score.getScore()) {
					player.sendMessage(ChatColor.AQUA + "[AmongUs] " + ChatColor.WHITE + plugin.getConfig().getString("messages.color.already-assigned"));
					return true;
				}
				if (colorInt > 0) {
					if (isColorFree(player, colorInt)) {
						score.setScore(colorInt);
						player.sendMessage(ChatColor.AQUA + "[AmongUs] " + ChatColor.GREEN + plugin.getConfig().getString("messages.color.color-changed"));
					} else {
						player.sendMessage(ChatColor.AQUA + "[AmongUs] " + ChatColor.WHITE + plugin.getConfig().getString("messages.color.color-not-changed"));
					}
				} else {
					player.sendMessage(ChatColor.AQUA + "[AmongUs] " + ChatColor.RED + plugin.getConfig().getString("messages.color.wrong-params"));
					player.sendMessage(ChatColor.YELLOW + "/color {" + getColorList() + "}");
				}
			} else {
				player.sendMessage(ChatColor.AQUA + "[AmongUs] " + ChatColor.RED + plugin.getConfig().getString("messages.color.no-params"));
				player.sendMessage(ChatColor.YELLOW + "/color {" + getColorList() + "}");
			}
		} else {
			player.sendMessage(ChatColor.AQUA + "[AmongUs] " + ChatColor.RED + plugin.getConfig().getString("messages.color.not-in-hub"));
		}
		return true;
	}

	private boolean isColorFree(Player player, int color) {
		Objective objective = player.getScoreboard().getObjective("player");
		for (Player p : plugin.getServer().getOnlinePlayers()) {
			Score score = objective.getScore(p.getDisplayName());
			if (score.isScoreSet()) {
				if (score.getScore() == color) {
					return false;
				}
			}
		}
		return true;
	}
	
	private String getColorList() {
		String ret = "";
		for (String color : plugin.getConfig().getStringList("player-colors")) {
			ret += color + ", ";
		}
		return ret.substring(0, ret.length() - 2);
	}
}
