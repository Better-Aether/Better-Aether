package com.gildedgames.aether.common.blocks.util.multiblock;

import com.gildedgames.aether.common.tile_entities.multiblock.TileEntityMultiblockController;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class BlockMultiController extends BlockMultiBase
{
	protected BlockMultiController(Material material)
	{
		super(material);
	}

	public abstract Iterable<BlockPos.MutableBlockPos> getMultiblockVolumeIterator(BlockPos pos, World world);

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos placePos)
	{
		for (BlockPos pos : this.getMultiblockVolumeIterator(placePos, world))
		{
			IBlockState state = world.getBlockState(pos);

			if (!state.getBlock().isReplaceable(world, pos))
			{
				return false;
			}
		}

		return true;
	}

	@Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
		TileEntity te = world.getTileEntity(pos);

		if (te instanceof TileEntityMultiblockController)
		{
			TileEntityMultiblockController controller = (TileEntityMultiblockController) te;

			controller.rebuild();
		}
    }
	
	@Override
	public int getRenderType()
	{
		return 2;
	}
}
