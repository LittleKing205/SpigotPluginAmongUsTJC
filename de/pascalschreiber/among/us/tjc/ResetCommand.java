package de.pascalschreiber.among.us.tjc;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			System.out.println("Dieser Befehl kann noch nicht in der Konsole verwendet werden."); 
			return true;
		}
		Player player = (Player) sender;
		Location loc = new Location(player.getLocation().getWorld(), 3, 57, -2);
		loc.getBlock().breakNaturally();
		return true;
	}
	
}
