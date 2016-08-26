package com.gildedgames.aether.common.blocks.dungeon;

import com.gildedgames.aether.common.blocks.util.multiblock.BlockMultiController;
import com.gildedgames.aether.common.tile_entities.TileEntityLabyrinthTotem;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockLabyrinthTotem extends BlockMultiController
{
	public BlockLabyrinthTotem(Material materialIn)
	{
		super(materialIn);

		this.setBlockUnbreakable();
	}

	@Override
	public Iterable<BlockPos.MutableBlockPos> getMultiblockVolumeIterator(BlockPos pos, World world)
	{
		return BlockPos.getAllInBoxMutable(pos, pos.up());
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityLabyrinthTotem();
	}
}
