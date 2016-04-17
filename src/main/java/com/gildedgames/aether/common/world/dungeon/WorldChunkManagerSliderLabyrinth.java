package com.gildedgames.aether.common.world.dungeon;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;

import com.gildedgames.aether.common.AetherCore;

public class WorldChunkManagerSliderLabyrinth extends WorldChunkManager
{
	private final BiomeGenBase biomeGenerator = new BiomeGenSliderLabyrinth(AetherCore.CONFIG.getSliderLabyrinthBiomeID());

	@Override
	public boolean areBiomesViable(int i, int j, int k, List list)
	{
		return list.contains(this.biomeGenerator);
	}

	@Override
	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] listToReuse, int x, int z, int width, int length, boolean cacheFlag)
	{
		return this.loadBlockGeneratorData(listToReuse, x, z, width, length);
	}

	@Override
	public BlockPos findBiomePosition(int x, int z, int range, List biomes, Random random)
	{
		if (biomes.contains(this.biomeGenerator))
		{
			return new BlockPos(x - range + random.nextInt(range * 2 + 1), 0, z - range + random.nextInt(range * 2 + 1));
		}

		return null;
	}

	@Override
	public float[] getRainfall(float[] array, int x, int z, int width, int length)
	{
		int size = width * length;

		if (array == null || array.length < size)
		{
			array = new float[size];
		}

		Arrays.fill(array, 0, size, 0);

		return array;
	}

	@Override
	public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] array, int x, int z, int width, int depth)
	{
		int size = width * depth;

		if (array == null || array.length < size)
		{
			array = new BiomeGenBase[size];
		}

		Arrays.fill(array, 0, size, this.biomeGenerator);

		return array;
	}
}