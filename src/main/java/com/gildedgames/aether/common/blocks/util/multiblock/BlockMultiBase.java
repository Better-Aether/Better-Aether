package com.gildedgames.aether.common.blocks.util.multiblock;

import com.gildedgames.aether.common.tile_entities.multiblock.TileEntityMultiblockInterface;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public abstract class BlockMultiBase extends BlockContainer
{
	public BlockMultiBase(Material material)
	{
		super(material);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (worldIn.isRemote)
		{
			return true;
		}

		TileEntity entity = worldIn.getTileEntity(pos);

		if (entity instanceof TileEntityMultiblockInterface)
		{
			((TileEntityMultiblockInterface) entity).onInteract(playerIn);
		}

		return true;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		TileEntity te = worldIn.getTileEntity(pos);

		if (te instanceof TileEntityMultiblockInterface)
		{
			TileEntityMultiblockInterface controller = (TileEntityMultiblockInterface) te;

			controller.onDestroyed();
		}

		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public abstract TileEntity createNewTileEntity(World worldIn, int meta);

	@Override
	public ItemStack getItem(World world, BlockPos pos, IBlockState state)
	{
		TileEntity te = world.getTileEntity(pos);

		if (te instanceof TileEntityMultiblockInterface)
		{
			TileEntityMultiblockInterface controller = (TileEntityMultiblockInterface) te;

			return controller.getPickedStack(world, pos, state);
		}

		return super.getItem(world, pos, state);
	}

}
