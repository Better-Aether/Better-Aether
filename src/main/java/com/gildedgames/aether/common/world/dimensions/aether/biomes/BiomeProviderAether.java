package com.gildedgames.aether.common.world.dimensions.aether.biomes;

import com.gildedgames.aether.common.registry.content.BiomesAether;
import net.minecraft.world.biome.BiomeProviderSingle;

public class BiomeProviderAether extends BiomeProviderSingle
{
	public BiomeProviderAether()
	{
		super(BiomesAether.HIGHLANDS);
	}
}