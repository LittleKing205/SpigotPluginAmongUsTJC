package de.pascalschreiber.among.us.tjc;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		createDefaults();
		
		getCommand("reset").setExecutor(new ResetCommand());
		getCommand("color").setExecutor(new ChangeColorCommand(this));
		
		getServer().getPluginManager().registerEvents(new ChatListener(this), this);
		
		if (getConfig().getBoolean("chunkloader.enabled"))
			new ChunkLoader(this);
	}
	
	private void createDefaults() {
		saveResource("config.yml", false);
        getConfig().options().copyDefaults(true);
        saveConfig();
	}
}