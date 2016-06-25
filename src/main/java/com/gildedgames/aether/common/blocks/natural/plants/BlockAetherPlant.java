package com.gildedgames.aether.common.blocks.natural.plants;

import com.gildedgames.aether.common.blocks.BlocksAether;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class BlockAetherPlant extends Block
{
	protected static final AxisAlignedBB PLANT_AABB = new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 0.6D, 0.7D);

	public BlockAetherPlant(Material material)
	{
		super(material);
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos)
	{
		IBlockState soilBlock = world.getBlockState(pos.down());

		return this.isSuitableSoilBlock(soilBlock);
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block)
	{
		this.validatePosition(world, pos, state);
	}

	public void validatePosition(World world, BlockPos pos, IBlockState state)
	{
		if (!this.canPlaceBlockAt(world, pos))
		{
			this.invalidateBlock(world, pos, state);
		}
	}

	protected void invalidateBlock(World world, BlockPos pos, IBlockState state)
	{
		this.dropBlockAsItem(world, pos, state, 0);

		world.setBlockToAir(pos);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return PLANT_AABB;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos)
	{
		return NULL_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	public boolean isSuitableSoilBlock(IBlockState state)
	{
		return state.getBlock() == BlocksAether.aether_grass || state.getBlock() == BlocksAether.aether_dirt;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}
}
