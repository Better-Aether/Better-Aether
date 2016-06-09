package com.gildedgames.aether.common.tile_entities.multiblock;

import com.gildedgames.aether.common.blocks.BlocksAether;
import com.gildedgames.aether.common.blocks.util.multiblock.BlockMultiController;
import com.gildedgames.util.core.util.GGHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class TileEntityMultiblockController extends TileEntity implements TileEntityMultiblockInterface
{
	private final BlockMultiController block;

	public TileEntityMultiblockController(BlockMultiController block)
	{
		this.block = block;
	}

	public void rebuild()
	{
		for (BlockPos.MutableBlockPos pos : block.getMultiblockVolumeIterator(this.pos, this.getWorld()))
		{
			if (this.worldObj.getTileEntity(pos) == this)
			{
				continue;
			}

			this.worldObj.setBlockState(pos, BlocksAether.multiblock_dummy.getDefaultState());

			TileEntityMultiblockDummy te = (TileEntityMultiblockDummy) this.worldObj.getTileEntity(pos);
			te.linkController(new BlockPos(this.pos));
		}
	}

	@Override
	public void onDestroyed()
	{
		for (BlockPos.MutableBlockPos pos : block.getMultiblockVolumeIterator(this.pos, this.getWorld()))
		{
			if (this.doesControllerOwn(pos))
			{
				this.worldObj.setBlockToAir(pos);
			}
		}
	}

	public boolean doesControllerOwn(BlockPos pos)
	{
		TileEntity entity = this.worldObj.getTileEntity(pos);

		if (entity instanceof TileEntityMultiblockController)
		{
			return true;
		}
		else if (entity instanceof TileEntityMultiblockDummy)
		{
			TileEntityMultiblockDummy dummy = (TileEntityMultiblockDummy) entity;

			return dummy.getLinkedController().equals(this.pos);
		}

		return false;
	}
}
