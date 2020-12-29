package de.pascalschreiber.among.us.tjc;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		createDefaults();
		getServer().getPluginManager().registerEvents(new ChatListener(this), this);
	}
	
	private void createDefaults() {
        getConfig().addDefault("messages.chat-only-in-meeting", "Chatting and speaking is only permitted in a meeting during a round");
        getConfig().options().copyDefaults(true);
        saveConfig();
	}
}
