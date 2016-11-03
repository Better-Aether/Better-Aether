package com.gildedgames.aether.common.world.dimensions.aether.biomes.highlands;

import com.gildedgames.aether.common.registry.GenerationAether;
import com.gildedgames.aether.common.world.biome.Ecosystem;
import com.gildedgames.aether.common.world.biome.WorldDecoration;
import com.gildedgames.aether.common.world.biome.WorldDecorationSimple;
import com.google.common.collect.Lists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class EcosystemHighlandForest implements Ecosystem
{

	private List<WorldDecoration> decorations;

	@Override
	public boolean hasDesiredTemperature()
	{
		return true;
	}

	@Override
	public boolean hasDesiredMoisture()
	{
		return true;
	}

	@Override
	public double getDesiredTemperature()
	{
		return 0.3;
	}

	@Override
	public double getDesiredMoisture()
	{
		return 0.3;
	}

	@Override
	public List<WorldDecoration> getDecorations()
	{
		if (this.decorations == null)
		{
			this.decorations = Lists.newArrayList();

			this.decorations.add(new WorldDecorationSimple(3, GenerationAether.large_green_skyroot_pine));
			this.decorations.add(new WorldDecorationSimple(15, GenerationAether.green_skyroot_pine));
			this.decorations.add(new WorldDecorationSimple(1, GenerationAether.green_aercloud, 0.06F)
			{
				@Override
				public BlockPos findPositionToPlace(World world, Random rand, BlockPos pos)
				{
					int width = 16;
					int minY = 90;
					int maxY = 130;

					return pos.add(rand.nextInt(width), minY + rand.nextInt(maxY - minY), rand.nextInt(width));
				}
			});
		}

		return this.decorations;
	}

}
