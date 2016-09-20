package com.gildedgames.aether.common.world.island.logic;

import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.util.OpenSimplexNoise;
import com.gildedgames.aether.common.world.GenUtil;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

import java.util.Random;

import static org.apache.http.HttpHeaders.DEPTH;

public class WorldGeneratorIsland
{

	private final NoiseGeneratorPerlin heightNoiseGen;

	private final OpenSimplexNoise simplex;

	private final Random rand;

	private final World world;

	public WorldGeneratorIsland(World world, Random rand)
	{
		this.world = world;
		this.rand = rand;
		this.heightNoiseGen = new NoiseGeneratorPerlin(rand, 1);
		this.simplex = new OpenSimplexNoise(world.getSeed());
	}

	public void genIslandForChunk(ChunkPrimer primer, IslandData data, IslandSector sector, int chunkX, int chunkZ)
	{
		int posX = chunkX * 16;
		int posZ = chunkZ * 16;

		double width = (double)data.getBounds().width;
		double height = data.getHeight();
		double length = (double)data.getBounds().height;

		double minY = 10;

		for (double x = 0; x < 16; x++)
		{
			for (double z = 0; z < 16; z++)
			{
				for (double y = minY; y < minY + height; y++)
				{
					double stepX = (double) posX - data.getBounds().getMinX() + x;
					double stepY = y - minY;
					double stepZ = (double) posZ - data.getBounds().getMinY() + z;

					double nx = (stepX) / width - 0.5 + (double) sector.getSectorX(); // normalize coords
					double ny = (stepY) / height - 0.5;
					double nz = (stepZ) / length - 0.5 + (double) sector.getSectorY();

					double value = GenUtil.octavedNoise3D(this.simplex, 4, 0.7D, 2.5D, nx, ny / 1.8D, nz);

					double distNX = nx - (double) sector.getSectorX(); // Subtract sector coords from nx/ny so that the noise is within range of the island center
					double distNZ = nz - (double) sector.getSectorY();

					double dist = 2.0 * Math.sqrt((distNX * distNX) + (ny * ny) + (distNZ * distNZ)); // Get distance from center of Island

					value = (value + 0.10) - (2.00 * Math.pow(dist, 1.50)); // Apply formula to shape noise into island, noise decreases in value the further the coord is from the center

					value = Math.max(-1.0D, value); // Prevents noise from dropping below its minimum value

					//value = Math.pow(value, 0.5);

					if (value > -0.8)
					{
						primer.setBlockState((int)x, (int)y, (int)z, BlocksAether.holystone.getDefaultState());
					}
				}
			}
		}
	}

	public static int to1D(int x, int y, int z)
	{
		return x << 12 | z << 8 | y;
	}

}
