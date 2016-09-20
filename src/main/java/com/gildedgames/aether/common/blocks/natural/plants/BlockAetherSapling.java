package com.gildedgames.aether.common.blocks.natural.plants;

import com.gildedgames.aether.common.blocks.util.variants.IBlockVariants;
import com.gildedgames.aether.common.blocks.util.variants.blockstates.BlockVariant;
import com.gildedgames.aether.common.blocks.util.variants.blockstates.PropertyVariant;
import com.gildedgames.aether.common.world.biome.highlands.BiomeHighlands;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BlockAetherSapling extends BlockAetherPlant implements IGrowable, IBlockVariants
{
	public static final BlockVariant
			BLUE_SKYROOT_SAPLING = new BlockVariant(0, "blue_skyroot"),
			GREEN_SKYROOT_SAPLING = new BlockVariant(1, "green_skyroot"),
			DARK_BLUE_SKYROOT_SAPLING = new BlockVariant(2, "dark_blue_skyroot"),
			GOLDEN_OAK_SAPLING = new BlockVariant(3, "golden_oak"),
			PURPLE_CRYSTAL_SAPLING = new BlockVariant(4, "purple_crystal");

	public static final PropertyVariant PROPERTY_VARIANT = PropertyVariant.create("variant", BLUE_SKYROOT_SAPLING, GREEN_SKYROOT_SAPLING, DARK_BLUE_SKYROOT_SAPLING,
			GOLDEN_OAK_SAPLING, PURPLE_CRYSTAL_SAPLING);

	public static final PropertyInteger PROPERTY_STAGE = PropertyInteger.create("stage", 0, 1);

	private static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.8D, 0.9D);

	public BlockAetherSapling()
	{
		super(Material.PLANTS);

		this.setSoundType(SoundType.PLANT);

		this.setTickRandomly(true);

		this.setDefaultState(this.getBlockState().getBaseState().withProperty(PROPERTY_VARIANT, BLUE_SKYROOT_SAPLING)
				.withProperty(PROPERTY_STAGE, 0));
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return SAPLING_AABB;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
	{
		for (BlockVariant variant : PROPERTY_VARIANT.getAllowedValues())
		{
			list.add(new ItemStack(item, 1, variant.getMeta()));
		}
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if (!worldIn.isRemote)
		{
			super.updateTick(worldIn, pos, state, rand);

			if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
			{
				this.grow(worldIn, rand, pos, state);
			}
		}
	}

	public void generateTree(World world, BlockPos pos, IBlockState state, Random random)
	{
		if (TerrainGen.saplingGrowTree(world, random, pos))
		{
			int meta = state.getValue(PROPERTY_VARIANT).getMeta();

			WorldGenerator treeGenerator = null;

			if (meta == BLUE_SKYROOT_SAPLING.getMeta())
			{
				treeGenerator = BiomeHighlands.genBlueSkyrootTree;
			}
			else if (meta == GREEN_SKYROOT_SAPLING.getMeta())
			{
				treeGenerator = BiomeHighlands.genGreenSkyrootTree;
			}
			else if (meta == GOLDEN_OAK_SAPLING.getMeta())
			{
				treeGenerator = BiomeHighlands.genGoldenOakTree;
			}
			else if (meta == PURPLE_CRYSTAL_SAPLING.getMeta())
			{
				treeGenerator = BiomeHighlands.genPurpleFruitTree;
			}
			else if (meta == DARK_BLUE_SKYROOT_SAPLING.getMeta())
			{
				treeGenerator = BiomeHighlands.genDarkBlueMassiveSkyrootTree;
			}

			if (treeGenerator != null)
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);

				if (!treeGenerator.generate(world, random, pos))
				{
					world.setBlockState(pos, state, 4);
				}
			}
		}
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return state.getValue(PROPERTY_VARIANT).getMeta();
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(PROPERTY_VARIANT, PROPERTY_VARIANT.fromMeta(meta & 7))
				.withProperty(PROPERTY_STAGE, (meta & 8) >> 3);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(PROPERTY_VARIANT).getMeta() | (state.getValue(PROPERTY_STAGE) << 3);
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, PROPERTY_VARIANT, PROPERTY_STAGE);
	}

	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
	{
		return true;
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
	{
		return world.rand.nextFloat() < 0.45f;
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state)
	{
		if (state.getValue(PROPERTY_STAGE) == 0)
		{
			world.setBlockState(pos, state.cycleProperty(PROPERTY_STAGE), 4);
		}
		else
		{
			this.generateTree(world, pos, state, rand);
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return PROPERTY_VARIANT.fromMeta(stack.getMetadata()).getName();
	}
}
