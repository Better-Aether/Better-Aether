package com.gildedgames.aether.common.world.dimensions.aether.biomes.highlands;

import com.gildedgames.aether.common.registry.GenerationAether;
import com.gildedgames.aether.common.world.biome.Ecosystem;
import com.gildedgames.aether.common.world.biome.WorldDecoration;
import com.gildedgames.aether.common.world.biome.WorldDecorationSimple;
import com.google.common.collect.Lists;

import java.util.List;

public class EcosystemCrystalHighlands implements Ecosystem
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
		return -0.3;
	}

	@Override
	public double getDesiredMoisture()
	{
		return -0.3;
	}

	@Override
	public List<WorldDecoration> getDecorations()
	{
		if (this.decorations == null)
		{
			this.decorations = Lists.newArrayList();

			this.decorations.add(new WorldDecorationSimple(4, GenerationAether.blue_skyroot_tree));
			this.decorations.add(new WorldDecorationSimple(3, GenerationAether.dark_blue_skyroot_tree));
			this.decorations.add(new WorldDecorationSimple(2, GenerationAether.dark_blue_skyroot_oak));
		}

		return this.decorations;
	}

}
