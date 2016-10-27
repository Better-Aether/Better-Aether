package com.gildedgames.aether.common.world.biome.highlands;

import com.gildedgames.aether.common.AetherCore;
import com.gildedgames.aether.common.world.biome.Ecosystem;
import com.gildedgames.aether.common.world.biome.WorldDecoration;
import com.gildedgames.aether.common.world.features.WorldGenTemplate;
import com.gildedgames.aether.common.world.features.WorldGenTemplateGroup;
import com.gildedgames.aether.common.world.features.placement_conditions.FlatGroundPlacementCondition;
import com.gildedgames.aether.common.world.features.placement_conditions.ReplaceablePlacementCondition;
import com.google.common.collect.Lists;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class EcosystemHighlands implements Ecosystem
{

	public static WorldGenTemplate blue_skyroot_tree_1, blue_skyroot_tree_2, blue_skyroot_tree_3;

	public static WorldGenTemplate green_skyroot_tree_1, green_skyroot_tree_2, green_skyroot_tree_3;

	public static WorldGenTemplate green_skyroot_oak_1, green_skyroot_oak_2;

	public static WorldGenTemplate golden_oak_1, golden_oak_2;

	public static WorldGenTemplateGroup blue_skyroot_tree, green_skyroot_tree, golden_oak, green_skyroot_oak;

	private List<WorldDecoration> decorations = Lists.newArrayList();

	private boolean hasInit;

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
		return 0.0;
	}

	@Override
	public double getDesiredMoisture()
	{
		return 0.0;
	}

	@Override
	public List<WorldDecoration> fetchDecorations()
	{
		return this.decorations;
	}

	@Override
	public void initDecorations(WorldServer world, TemplateManager manager)
	{
		MinecraftServer server = world.getMinecraftServer();

		this.blue_skyroot_tree_1 = new WorldGenTemplate(manager.getTemplate(server, new ResourceLocation(AetherCore.MOD_ID, "highlands/trees/normal/blue/blue_skyroot_tree_1")), new FlatGroundPlacementCondition(), new ReplaceablePlacementCondition());
		this.blue_skyroot_tree_2 = new WorldGenTemplate(manager.getTemplate(server, new ResourceLocation(AetherCore.MOD_ID, "highlands/trees/normal/blue/blue_skyroot_tree_2")), new FlatGroundPlacementCondition(), new ReplaceablePlacementCondition());
		this.blue_skyroot_tree_3 = new WorldGenTemplate(manager.getTemplate(server, new ResourceLocation(AetherCore.MOD_ID, "highlands/trees/normal/blue/blue_skyroot_tree_3")), new FlatGroundPlacementCondition(), new ReplaceablePlacementCondition());

		this.green_skyroot_tree_1 = new WorldGenTemplate(manager.getTemplate(server, new ResourceLocation(AetherCore.MOD_ID, "highlands/trees/normal/green/green_skyroot_tree_1")), new FlatGroundPlacementCondition(), new ReplaceablePlacementCondition());
		this.green_skyroot_tree_2 = new WorldGenTemplate(manager.getTemplate(server, new ResourceLocation(AetherCore.MOD_ID, "highlands/trees/normal/green/green_skyroot_tree_2")), new FlatGroundPlacementCondition(), new ReplaceablePlacementCondition());
		this.green_skyroot_tree_3 = new WorldGenTemplate(manager.getTemplate(server, new ResourceLocation(AetherCore.MOD_ID, "highlands/trees/normal/green/green_skyroot_tree_3")), new FlatGroundPlacementCondition(), new ReplaceablePlacementCondition());

		this.green_skyroot_oak_1 = new WorldGenTemplate(manager.getTemplate(server, new ResourceLocation(AetherCore.MOD_ID, "highlands/trees/oak/green/green_skyroot_oak_1")), new FlatGroundPlacementCondition(), new ReplaceablePlacementCondition());
		this.green_skyroot_oak_2 = new WorldGenTemplate(manager.getTemplate(server, new ResourceLocation(AetherCore.MOD_ID, "highlands/trees/oak/green/green_skyroot_oak_2")), new FlatGroundPlacementCondition(), new ReplaceablePlacementCondition());

		this.golden_oak_1 = new WorldGenTemplate(manager.getTemplate(server, new ResourceLocation(AetherCore.MOD_ID, "highlands/trees/oak/golden/golden_oak_1")), new FlatGroundPlacementCondition(), new ReplaceablePlacementCondition());
		this.golden_oak_2 = new WorldGenTemplate(manager.getTemplate(server, new ResourceLocation(AetherCore.MOD_ID, "highlands/trees/oak/golden/golden_oak_2")), new FlatGroundPlacementCondition(), new ReplaceablePlacementCondition());

		this.blue_skyroot_tree = new WorldGenTemplateGroup(this.blue_skyroot_tree_1, this.blue_skyroot_tree_2, this.blue_skyroot_tree_3);
		this.green_skyroot_tree = new WorldGenTemplateGroup(this.green_skyroot_tree_1, this.green_skyroot_tree_2, this.green_skyroot_tree_3);
		this.golden_oak = new WorldGenTemplateGroup(this.golden_oak_1, this.golden_oak_2);
		this.green_skyroot_oak = new WorldGenTemplateGroup(this.green_skyroot_oak_1, this.green_skyroot_oak_2);

		this.decorations.add(new WorldDecoration()
		{
			@Override
			public boolean shouldGenerate(Random random)
			{
				return true;
			}

			@Override
			public int getGenerationCount()
			{
				return 4;
			}

			@Override public WorldGenerator getGenerator()
			{
				return EcosystemHighlands.this.blue_skyroot_tree;
			}
		});

		this.decorations.add(new WorldDecoration()
		{
			@Override
			public boolean shouldGenerate(Random random)
			{
				return true;
			}

			@Override
			public int getGenerationCount()
			{
				return 3;
			}

			@Override public WorldGenerator getGenerator()
			{
				return EcosystemHighlands.this.green_skyroot_tree;
			}
		});

		this.decorations.add(new WorldDecoration()
		{
			@Override
			public boolean shouldGenerate(Random random)
			{
				return true;
			}

			@Override
			public int getGenerationCount()
			{
				return 2;
			}

			@Override public WorldGenerator getGenerator()
			{
				return EcosystemHighlands.this.green_skyroot_oak;
			}
		});


		this.decorations.add(new WorldDecoration()
		{
			@Override
			public boolean shouldGenerate(Random random)
			{
				return true;
			}

			@Override
			public int getGenerationCount()
			{
				return 2;
			}

			@Override public WorldGenerator getGenerator()
			{
				return EcosystemHighlands.this.golden_oak;
			}
		});
	}

	@Override
	public boolean hasInitDecorations()
	{
		return this.hasInit;
	}

	@Override
	public void setInitDecorations(boolean flag)
	{
		this.hasInit = flag;
	}

}
