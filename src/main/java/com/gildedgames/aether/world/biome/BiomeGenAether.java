package com.gildedgames.aether.world.biome;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenAether extends BiomeGenBase
{
	public BiomeGenAether(int id)
	{
		super(id);

		this.setBiomeName("Aether");
		this.setDisableRain();
		this.setTemperatureRainfall(0.5f, 0f);
	}

	@Override
	public int getSkyColorByTemp(float temp)
	{
		return 0xc0c0ff;
	}

	@Override
	public int getWaterColorMultiplier()
	{
		return 0x70DB70;
	}

}
