package com.gildedgames.aether.common.blocks.natural.plants;

import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.blocks.util.variants.IAetherBlockWithSubtypes;
import com.gildedgames.aether.common.blocks.util.variants.blockstates.BlockVariant;
import com.gildedgames.aether.common.blocks.util.variants.blockstates.PropertyVariant;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BlockAetherSapling extends BlockBush implements IGrowable, IAetherBlockWithSubtypes
{
	public static final BlockVariant
			BLUE_SKYROOT_SAPLING = new BlockVariant(0, "blue_skyroot"),
			GREEN_SKYROOT_SAPLING = new BlockVariant(1, "green_skyroot"),
			DARK_BLUE_SKYROOT_SAPLING = new BlockVariant(2, "dark_blue_skyroot"),
			GOLDEN_OAK_SAPLING = new BlockVariant(3, "golden_oak"),
			PURPLE_CRYSTAL_SAPLING = new BlockVariant(4, "purple_crystal");

	public static final PropertyVariant PROPERTY_VARIANT = PropertyVariant.create("variant", BLUE_SKYROOT_SAPLING, GREEN_SKYROOT_SAPLING, DARK_BLUE_SKYROOT_SAPLING,
			GOLDEN_OAK_SAPLING, PURPLE_CRYSTAL_SAPLING);

	public BlockAetherSapling()
	{
		super(Material.plants);

		this.setStepSound(Block.soundTypeGrass);

		this.setBlockBounds(0.1f, 0.0F, 0.1f, 0.9f, 0.8f, 0.9f);

		this.setDefaultState(this.getBlockState().getBaseState().withProperty(PROPERTY_VARIANT, BLUE_SKYROOT_SAPLING));
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		for (BlockVariant variant : PROPERTY_VARIANT.getAllowedValues())
		{
			list.add(new ItemStack(item, 1, variant.getMeta()));
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
	{
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.CUTOUT;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}

	@Override
	public boolean isPassable(IBlockAccess world, BlockPos pos)
	{
		return true;
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos)
	{
		return this.canPlaceBlockOn(world.getBlockState(pos.down()).getBlock());
	}

	@Override
	public boolean canPlaceBlockOn(Block ground)
	{
		return ground == BlocksAether.aether_grass || ground == this;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(PROPERTY_VARIANT, PROPERTY_VARIANT.fromMeta(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((BlockVariant) state.getValue(PROPERTY_VARIANT)).getMeta();
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, PROPERTY_VARIANT);
	}

	@Override
	public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient)
	{
		return false;
	}

	@Override
	public boolean canUseBonemeal(World world, Random rand, BlockPos pos, IBlockState state)
	{
		return false;
	}

	@Override
	public void grow(World world, Random rand, BlockPos pos, IBlockState state)
	{
		// TODO
	}

	@Override
	public String getSubtypeUnlocalizedName(ItemStack stack)
	{
		return PROPERTY_VARIANT.fromMeta(stack.getMetadata()).getName();
	}
}
