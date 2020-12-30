package de.pascalschreiber.among.us.tjc;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		createDefaults();
		
		getCommand("reset").setExecutor(new ResetCommand());
		
		getServer().getPluginManager().registerEvents(new ChatListener(this), this);
		
		if (getConfig().getBoolean("chunkloader.enabled"))
			new ChunkLoader(this);
	}
	
	private void createDefaults() {
		getConfig().addDefault("chunkloader.enabled", false);
        getConfig().addDefault("chunkloader.check-time", 60);
        getConfig().addDefault("messages.chat-only-in-meeting", "Chatting and speaking is only permitted in a meeting during a round");
        getConfig().options().copyDefaults(true);
        saveConfig();
	}
}
