package de.pascalschreiber.among.us.tjc;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ChatListener implements Listener {
	private Main plugin;
	
	public ChatListener(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		
		event.setCancelled(true);
		Player player = event.getPlayer();
		Scoreboard scoreboard = player.getScoreboard();
		Team team = scoreboard.getEntryTeam(player.getDisplayName());
		boolean meeting = (scoreboard.getObjective("data").getScore("#meeting").getScore() == 1);
		Score playerScore = scoreboard.getObjective("player").getScore(player.getDisplayName());
		if (team == null) {
			if (playerScore.isScoreSet()) {
				hubChat(player, event.getMessage());
			} else {
				lobbyChat(player, event.getMessage());
			}
		} else if (team.getName().equals("ghost")) {
			ghostChat(player, event.getMessage());
		} else {
			if (meeting) {
				meetingChat(player, event.getMessage());
			} else {
				player.sendMessage(ChatColor.GREEN + "[SERVER] " + ChatColor.RED + plugin.getConfig().getString("messages.chat-only-in-meeting"));
			}
		}
	}
	
	/**
	 * Im Spiel registriete Spieler, die auf eine neue Runde warten.
	 * @param fromPlayer
	 * @param msg
	 */
	private void hubChat(Player fromPlayer, String msg) {
		Objective playerObj = fromPlayer.getScoreboard().getObjective("player");
		for(Player p : plugin.getServer().getOnlinePlayers()) {
			Team team = fromPlayer.getScoreboard().getEntryTeam(fromPlayer.getDisplayName());
			if (team == null && playerObj.getScore(p.getDisplayName()).isScoreSet()) {
				p.sendMessage(formatMessage(fromPlayer, msg, "HUB", ChatColor.YELLOW));
			}
		}
	}
	
	/**
	 * Gerde auf dem Server eingeloggte Spieler
	 * @param fromPlayer
	 * @param msg
	 */
	private void lobbyChat(Player fromPlayer, String msg) {
		Objective playerObj = fromPlayer.getScoreboard().getObjective("player");
		for(Player p : plugin.getServer().getOnlinePlayers()) {
			Team team = fromPlayer.getScoreboard().getEntryTeam(fromPlayer.getDisplayName());
			if (team == null && playerObj.getScore(p.getDisplayName()).isScoreSet() == false) {
				p.sendMessage(formatMessage(fromPlayer, msg, "LOBBY", ChatColor.WHITE));
			}
		}
	}
	
	/**
	 * Chat, der während eines Mettings läuft
	 * @param fromPlayer
	 * @param msg
	 */
	private void meetingChat(Player fromPlayer, String msg) {
		for(Player p : plugin.getServer().getOnlinePlayers()) {
			Team team = fromPlayer.getScoreboard().getEntryTeam(fromPlayer.getDisplayName());
			if (team != null) {
				p.sendMessage(formatMessage(fromPlayer, msg, "MEETING", ChatColor.RED));
			}
		}
	}
	
	/**
	 * Chatnachrichten, die von einem Geist ausgeführt werden.
	 * @param fromPlayer
	 * @param msg
	 */
	private void ghostChat(Player fromPlayer, String msg) {
		for (String s : fromPlayer.getScoreboard().getTeam("ghost").getEntries() ) {
			plugin.getServer().getPlayer(s).sendMessage(formatMessage(fromPlayer, msg, "GHOST", ChatColor.GRAY));
		}
	}
	
	/**
	 * Formatiert eine Chatnachricht
	 * @param player
	 * @param msg
	 * @param channel
	 * @param color
	 * @return
	 */
	private String formatMessage(Player player, String msg, String channel, ChatColor color) {
		return "[" + ChatColor.GREEN + player.getDisplayName() + ChatColor.WHITE + "] [" + color + channel + ChatColor.WHITE + "] " + msg;
	}
}
