package com.gildedgames.aether.common.world.dimensions.aether.features;

import com.gildedgames.aether.common.blocks.BlocksAether;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenQuicksoil extends WorldGenerator
{
	private final IBlockState quicksoil;

	public WorldGenQuicksoil()
	{
		this.quicksoil = BlocksAether.quicksoil.getDefaultState();
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		BlockPos.MutableBlockPos nextPos = new BlockPos.MutableBlockPos();

		for (int x = pos.getX() - 3; x < pos.getX() + 4; x++)
		{
			for (int z = pos.getZ() - 3; z < pos.getZ() + 4; z++)
			{
				nextPos.setPos(x, pos.getY(), z);

				if (world.isAirBlock(nextPos) && ((x - pos.getX()) * (x - pos.getX()) + (z - pos.getZ()) * (z - pos.getZ())) < 12)
				{
					world.setBlockState(nextPos, this.quicksoil);
				}
			}
		}

		return true;
	}
}
