package com.gildedgames.aether.common.world.biome;

import java.util.Random;

import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.world.features.trees.WorldGenSkyrootTree;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenAether extends BiomeGenAetherBase
{
	private final WorldGenSkyrootTree genGreenSkyrootTree = new WorldGenSkyrootTree(BlocksAether.skyroot_log, BlocksAether.green_skyroot_leaves, 1, 4, 4);

	private final WorldGenSkyrootTree genBlueSkyrootTree = new WorldGenSkyrootTree(BlocksAether.skyroot_log, BlocksAether.blue_skyroot_leaves, 1, 4, 4);

	public BiomeGenAether(int id)
	{
		super(id);

	}

	@Override
	public WorldGenAbstractTree genBigTreeChance(Random random)
	{
		int chance = random.nextInt(100);

		if (chance < 50)
		{
			return this.genBlueSkyrootTree;
		}
		else
		{
			return this.genGreenSkyrootTree;
		}

		// TODO: _actual_ chances
	}

}
