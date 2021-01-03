package de.pascalschreiber.among.us.tjc;

import org.bukkit.Chunk;

public class ChunkLoader {

	private int[] xCoords = {-59, -44, -27, -10, 5, 23, 36, 52, 72, 87};
	private int[] zCoords = {-56, -36, -26, -7, 7, 25, 39, 56, 73, 88, 104, 114, 130};
	
	public ChunkLoader(Main plugin) {
		plugin.getServer().getScheduler().runTaskAsynchronously(plugin, new Runnable() {
			@Override
			public void run() {
				for (int xChunk : xCoords) {
					for (int zChunk : zCoords) {
						Chunk chunk = plugin.getServer().getWorld(plugin.getConfig().getString("chunkloader.world")).getChunkAt(zChunk, xChunk);
						if (!chunk.isForceLoaded()) {
							chunk.setForceLoaded(true);
						}
					}
				}
				System.out.println("Force for Among Us is loading is finished.");
			}
		});
	}
}


