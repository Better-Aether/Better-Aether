package com.gildedgames.aether.client.models.blocks;

import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.blocks.construction.BlockAltar;
import com.gildedgames.aether.common.blocks.construction.BlockCustomDoor;
import com.gildedgames.aether.common.blocks.construction.signs.BlockStandingSkyrootSign;
import com.gildedgames.aether.common.blocks.construction.signs.BlockWallSkyrootSign;
import com.gildedgames.aether.common.blocks.construction.walls.BlockSkyrootWall;
import com.gildedgames.aether.common.blocks.dungeon.BlockLabyrinthDoor;
import com.gildedgames.aether.common.blocks.natural.BlockAercloud;
import com.gildedgames.aether.common.blocks.natural.BlockAetherLeaves;
import com.gildedgames.aether.common.blocks.natural.plants.BlockAetherSapling;
import com.gildedgames.aether.common.blocks.natural.plants.BlockOrangeTree;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;

import java.util.LinkedHashMap;

public class AetherBlockModels
{
	public static void preInit()
	{
		registerStateMappers();
	}

	private static void registerStateMappers()
	{
		StateMap leavesMapper = new StateMap.Builder().ignore(BlockAetherLeaves.PROPERTY_CHECK_DECAY, BlockAetherLeaves.PROPERTY_DECAYABLE).build();

		ModelLoader.setCustomStateMapper(BlocksAether.blue_skyroot_leaves, leavesMapper);
		ModelLoader.setCustomStateMapper(BlocksAether.green_skyroot_leaves, leavesMapper);
		ModelLoader.setCustomStateMapper(BlocksAether.dark_blue_skyroot_leaves, leavesMapper);
		ModelLoader.setCustomStateMapper(BlocksAether.golden_oak_leaves, leavesMapper);
		ModelLoader.setCustomStateMapper(BlocksAether.purple_crystal_leaves, leavesMapper);
		ModelLoader.setCustomStateMapper(BlocksAether.purple_fruit_leaves, leavesMapper);

		ModelLoader.setCustomStateMapper(BlocksAether.aether_sapling, new StateMap.Builder().ignore(BlockAetherSapling.PROPERTY_STAGE).build());

		ModelLoader.setCustomStateMapper(BlocksAether.skyroot_door, new StateMap.Builder().ignore(BlockCustomDoor.POWERED).build());

		ModelLoader.setCustomStateMapper(BlocksAether.arkenium_door, new StateMap.Builder().ignore(BlockCustomDoor.POWERED).build());

		ModelLoader.setCustomStateMapper(BlocksAether.skyroot_chest, new StateMap.Builder().ignore(BlockChest.FACING).build());

		ModelLoader.setCustomStateMapper(BlocksAether.skyroot_log_wall, new StateMap.Builder().ignore(BlockSkyrootWall.PROPERTY_GENERATED).build());

		ModelLoader.setCustomStateMapper(BlocksAether.aercloud, new StateMapperBase()
		{
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state)
			{
				LinkedHashMap<IProperty<?>, Comparable<?>> mappings = Maps.newLinkedHashMap(state.getProperties());

				if (state.getValue(BlockAercloud.PROPERTY_VARIANT) != BlockAercloud.PURPLE_AERCLOUD)
				{
					mappings.remove(BlockAercloud.PROPERTY_FACING);
				}

				ResourceLocation resource = Block.REGISTRY.getNameForObject(state.getBlock());

				return new ModelResourceLocation(resource, this.getPropertyString(mappings));
			}
		});

		ModelLoader.setCustomStateMapper(BlocksAether.orange_tree, new StateMapperBase()
		{
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state)
			{
				LinkedHashMap<IProperty<?>, Comparable<?>> mappings = Maps.newLinkedHashMap(state.getProperties());

				if (state.getValue(BlockOrangeTree.PROPERTY_IS_TOP_BLOCK))
				{
					if (state.getValue(BlockOrangeTree.PROPERTY_STAGE) < 3)
					{
						mappings.remove(BlockOrangeTree.PROPERTY_IS_TOP_BLOCK);
						mappings.remove(BlockOrangeTree.PROPERTY_STAGE);
					}
				}

				ResourceLocation resourceLocation = Block.REGISTRY.getNameForObject(state.getBlock());

				return new ModelResourceLocation(resourceLocation, this.getPropertyString(mappings));
			}
		});

		ModelLoader.setCustomStateMapper(BlocksAether.altar, new StateMap.Builder().ignore(BlockAltar.PROPERTY_FACING).build());

		ModelLoader.setCustomStateMapper(BlocksAether.standing_skyroot_sign, new StateMap.Builder().ignore(BlockStandingSkyrootSign.ROTATION).build());
		ModelLoader.setCustomStateMapper(BlocksAether.wall_skyroot_sign, new StateMap.Builder().ignore(BlockWallSkyrootSign.FACING).build());
		ModelLoader.setCustomStateMapper(BlocksAether.skyroot_fence_gate, new StateMap.Builder().ignore(BlockFenceGate.POWERED).build());
		ModelLoader.setCustomStateMapper(BlocksAether.labyrinth_door, new StateMap.Builder().ignore(BlockLabyrinthDoor.PROPERTY_FACING).build());

		ModelLoader.setCustomStateMapper(BlocksAether.sentry_gel, (new StateMap.Builder()).ignore(BlockFluidBase.LEVEL).build());
	}
}
