package de.pascalschreiber.among.us.tjc;

import org.bukkit.Chunk;

public class ChunkLoader {

	private Main plugin;

	private int[] xCoords = {-59, -44, -27, -10, 5, 23, 36, 52, 72, 87};
	private int[] zCoords = {-56, -36, -26, -7, 7, 25, 39, 56, 73, 88, 104, 114, 130};
	
	public ChunkLoader(Main plugin) {
		this.plugin = plugin;
		//int secondsToLoad = plugin.getConfig().getInt("main.chunk-check", 60);
		
		this.plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new Runnable() {
			@Override
			public void run() {
				for (int xChunk : xCoords) {
					for (int zChunk : zCoords) {
						Chunk chunk = plugin.getServer().getWorld("world").getChunkAt(zChunk, xChunk);
						if (!chunk.isForceLoaded()) {
							chunk.setForceLoaded(true);
						}
						if (!chunk.isLoaded()) {
							chunk.load();
						}
					}
				}
			}
		}, 20l, 20+60l);
	}
}


