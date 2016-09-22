package com.gildedgames.aether.common.world.biome.highlands;

import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.blocks.natural.BlockAetherLog;
import com.gildedgames.aether.common.world.biome.BiomeAetherBase;
import com.gildedgames.aether.common.world.features.trees.WorldGenFruitTree;
import com.gildedgames.aether.common.world.features.trees.WorldGenLargeTree;
import com.gildedgames.aether.common.world.features.trees.WorldGenMassiveSkyrootTree;
import com.gildedgames.aether.common.world.features.trees.WorldGenSkyrootTree;
import net.minecraft.block.BlockLog;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class BiomeHighlands extends BiomeAetherBase
{

	public static final WorldGenSkyrootTree genGreenSkyrootTree = new WorldGenSkyrootTree(BlocksAether.skyroot_log.getDefaultState().withProperty(BlockAetherLog.PROPERTY_LOG_AXIS, BlockLog.EnumAxis.Y),
			BlocksAether.green_skyroot_leaves.getDefaultState());

	public static final WorldGenSkyrootTree genBlueSkyrootTree = new WorldGenSkyrootTree(BlocksAether.skyroot_log.getDefaultState().withProperty(BlockAetherLog.PROPERTY_LOG_AXIS, BlockLog.EnumAxis.Y),
			BlocksAether.blue_skyroot_leaves.getDefaultState());

	public static final WorldGenMassiveSkyrootTree genBlueMassiveSkyrootTree = new WorldGenMassiveSkyrootTree(BlocksAether.blue_skyroot_leaves.getDefaultState(), 8, false);

	public static final WorldGenMassiveSkyrootTree genGreenMassiveSkyrootTree1 = new WorldGenMassiveSkyrootTree(BlocksAether.green_skyroot_leaves.getDefaultState(), 8, false);

	public static final WorldGenMassiveSkyrootTree genGreenMassiveSkyrootTree2 = new WorldGenMassiveSkyrootTree(BlocksAether.green_skyroot_leaves.getDefaultState(), 20, false);

	public static final WorldGenLargeTree genGoldenOakTree = new WorldGenLargeTree(BlocksAether.golden_oak_log.getDefaultState(), BlocksAether.golden_oak_leaves.getDefaultState());

	public static final WorldGenLargeTree genGreenLargeSkyrootTree = new WorldGenLargeTree(BlocksAether.skyroot_log.getDefaultState(), BlocksAether.green_skyroot_leaves.getDefaultState());

	public BiomeHighlands()
	{
		super(new Biome.BiomeProperties("Highlands").setRainDisabled().setTemperature(0.5f));

		//this.topBlock = BlocksAether.holystone.getDefaultState();

		this.setRegistryName(AetherCore.getResource("aether_highlands"));
	}

	@Override
	public WorldGenAbstractTree genBigTreeChance(Random random)
	{
		int ratio = random.nextInt(100);

		if (ratio <= 9)
		{
			return genGreenSkyrootTree;
		}
		else if (ratio > 9 && ratio <= 18)
		{
			return genGreenLargeSkyrootTree;
		}
		else if (ratio > 18 && ratio <= 35)
		{
			return genBlueSkyrootTree;
		}
		else if (ratio > 35 && ratio <= 63)
		{
			return genGreenMassiveSkyrootTree1;
		}
		else if (ratio > 63 && ratio <= 80)
		{
			return genBlueMassiveSkyrootTree;
		}
		else if (ratio > 80 && ratio <= 85)
		{
			return genGoldenOakTree;
		}
		else if (ratio > 85 && ratio <= 90)
		{
			return genGreenMassiveSkyrootTree2;
		}

		return genGreenMassiveSkyrootTree2;
	}

}
