package com.gildedgames.aether.common.world.biome.blighted;

import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.blocks.natural.BlockAetherGrass;
import com.gildedgames.aether.common.blocks.natural.BlockAetherLog;
import com.gildedgames.aether.common.world.biome.BiomeAetherBase;
import com.gildedgames.aether.common.world.features.TemplatePipeline;
import com.gildedgames.aether.common.world.features.WorldGenTemplate;
import com.gildedgames.aether.common.world.features.trees.WorldGenFruitTree;
import com.gildedgames.aether.common.world.features.trees.WorldGenMassiveSkyrootTree;
import com.gildedgames.aether.common.world.features.trees.WorldGenSkyrootTree;
import net.minecraft.block.BlockLog;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

import static com.gildedgames.aether.common.world.biome.enchanted.BiomeEnchantedForest.*;
import static com.gildedgames.aether.common.world.biome.highlands.BiomeHighlands.*;

public class BiomeBlightedHighlands extends BiomeAetherBase
{

	public static final WorldGenMassiveSkyrootTree dark_blue_tree = new WorldGenMassiveSkyrootTree(BlocksAether.dark_blue_skyroot_leaves.getDefaultState(), 35, true);

	public static final WorldGenFruitTree tree = new WorldGenFruitTree(BlocksAether.blighted_leaves.getDefaultState(), BlocksAether.blighted_leaves.getDefaultState(), 50, 5, true);

	public static final WorldGenSkyrootTree blightwillow_tree = new WorldGenSkyrootTree(BlocksAether.blightwillow_log.getDefaultState().withProperty(BlockAetherLog.PROPERTY_LOG_AXIS, BlockLog.EnumAxis.Y),
			BlocksAether.blightwillow_leaves.getDefaultState());

	public static final WorldGenSkyrootTree blighted_tree = new WorldGenSkyrootTree(BlocksAether.skyroot_log.getDefaultState().withProperty(BlockAetherLog.PROPERTY_LOG_AXIS, BlockLog.EnumAxis.Y),
			BlocksAether.blighted_leaves.getDefaultState());

	public BiomeBlightedHighlands()
	{
		super(new BiomeProperties("Blighted Highlands").setRainDisabled().setTemperature(0.5f), AetherCore.getResource("aether_blighted_highlands"));

		this.topBlock = BlocksAether.aether_grass.getDefaultState().withProperty(BlockAetherGrass.PROPERTY_VARIANT, BlockAetherGrass.BLIGHTED);
	}

	/*@Override
	public WorldGenAbstractTree genBigTreeChance(Random random)
	{
		return blightwillow_tree;
	}*/

}
